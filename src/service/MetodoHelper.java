package service;

import java.io.IOException;

import model.Codigo;
import model.Comentario;
import model.Metodo;
import model.Token;

public class MetodoHelper {
	Codigo codigoClase;
	char[] arrayCodigo;
	int nextIndex;
	
	
	private static final int NO_METODO = 0; //Aún no entré en el método
	private static final int PRE_METODO = 1; //Estoy en la línea de definición
	private static final int IN_METODO = 2; //Estoy dentro del método
	
	public MetodoHelper(String codigo) {
		this.codigoClase = new Codigo(codigo);
		this.arrayCodigo = codigo.toCharArray();
		this.nextIndex = 0;
	}

        /*
         * Devuelve el siguiente método o el primero si no se detectó ninguno aún
         */
	public Metodo getNextMetodo() {
                if( this.nextIndex == 0 ){
                    //Si lo primero que hay en la clase es un método, se mezcan los modificadores
                    this.salterarComienzoClase();
                }
		Metodo metodo = new Metodo();
		int inMetodo = MetodoHelper.NO_METODO;
		String nombre = null;
		int parentesis = 0;
		int llaves = 0;
		Token siguientePalabra;
		int lastFinalizer = this.nextIndex;
		while( (siguientePalabra = this.siguientePalabra()) != null ){
			if( siguientePalabra.getTipo() == Token.TIPO_COMENTARIO ){
                                /*
                                 * No tengo en cuenta los comentarios para evaluar los tipos
                                 * porque pueden aparecer en cualquier lado
                                 */
				continue;
			}
			if( inMetodo == MetodoHelper.NO_METODO ){
				/* Previo a encontrar el nombre del método */
				if( siguientePalabra.getTipo() == Token.TIPO_LLAVE_CERRADA || siguientePalabra.getValor().trim().equals(";") ){
                                        //Para saltera bloques estáticos o declaración de variables
					lastFinalizer = this.nextIndex;
				}
				if( siguientePalabra.getTipo() == Token.TIPO_NOMBRE ){
					nombre = siguientePalabra.getValor();
				}else if(nombre != null && siguientePalabra.getTipo() == Token.TIPO_PARENTESIS_ABIERTA ){
					metodo.setNombre(nombre);
					metodo.setFirstLine(lastFinalizer);
					inMetodo = MetodoHelper.PRE_METODO;
					parentesis ++;
					nombre = null;
				}else{
					nombre = null;
				}
			}else if( inMetodo == MetodoHelper.PRE_METODO ){
				//Dentro de la definición del método -- Ya encontró nombre
				if( siguientePalabra.getTipo() == Token.TIPO_PARENTESIS_ABIERTA ){
					parentesis ++;
				}else if( siguientePalabra.getTipo() == Token.TIPO_PARENTESIS_CERRADA ){
					parentesis --;
				}else if( parentesis == 0 && siguientePalabra.getTipo() == Token.TIPO_LLAVE_ABIERTA ){
					inMetodo = MetodoHelper.IN_METODO;
					llaves ++;
				}
			}else{
				/* Dentro del método */
				if( siguientePalabra.getTipo() == Token.TIPO_LLAVE_ABIERTA ){
					llaves ++;
				}
				if( siguientePalabra.getTipo() == Token.TIPO_LLAVE_CERRADA ){
					llaves --;
                                        //Si se cerró la primera llave del método es porque tenrminó
					if( llaves ==  0 ){
						metodo.setLastLine(this.nextIndex);
						metodo.setCodigo(new Codigo(codigoClase.getCodigo().substring(metodo.getFirstLine(), metodo.getLastLine())));
                                                //Entonces devuelvo el método
						return metodo;
					}
				} else if(  siguientePalabra.getTipo() == Token.TIPO_NOMBRE ){
					nombre = siguientePalabra.getValor();
				}else if(nombre != null && siguientePalabra.getTipo() == Token.TIPO_PARENTESIS_ABIERTA ){
					if( !CodigoService.esControladorDeFlujo(nombre) ){
						metodo.getMetodosLlamados().add(nombre);
					}
					nombre = null;
				}
			}
		}
		return null;
	}
        
        private void salterarComienzoClase(){
            Token siguientePalabra = siguientePalabra();
            while( siguientePalabra != null && siguientePalabra.getTipo() != Token.TIPO_LLAVE_ABIERTA ){
                siguientePalabra = siguientePalabra();
            }
        }

        public Token siguientePalabra(){
            Token t = siguientePalabraReal();
            return t;
        }
        
	public Token siguientePalabraReal() {
		Character ultimoLeido = null;
		Comentario comentario = new Comentario();
		StringBuilder sb = new StringBuilder();
                boolean inString = false;
		for( ; this.nextIndex < this.arrayCodigo.length ; this.nextIndex++){
			if( ultimoLeido != null ){
                                //Si estoy en un string, lo único que espero es el fin de string
                                if( inString ){
                                    if( this.actual() == '"' && ultimoLeido != '\\'){
                                        sb.append(this.actual());
                                        this.nextIndex++;
                                        return MetodoHelper.buildToken(sb);
                                    }
                                }else if( !comentario.isInComentario() && !ultimoLeido.equals('/') || ( this.actual() != '/' && this.actual() != '*' ) ){
                                    if( this.actual() == '\'' ){
                                        return this.nextCharConstant();
                                    }
                                    //Si no estoy en un comentario y no se reconoció '/*' o '//', lo que está adentro vale
                                    if( this.actual() == '"' && ultimoLeido != '\'' ){
                                        inString = true;
                                    }else if( MetodoHelper.noEsLetra( this.actual() ) ){
                                            //Si reconozco una cadena no vacía, la devuelvo.
                                            if( !isBlankString(sb.toString()) ){
                                                    return MetodoHelper.buildToken(sb);
                                            }else{
                                                //Sino reseteo el String Builder
                                                sb = new StringBuilder();
                                            }
                                    }
				}else if( !comentario.isInComentario() ){
					//Si no es comentario quiere decir que se reconoció un comienzo de comentario (Sino hubiera entrado en el if anterior)
					if( this.actual() == '/' ){
						comentario.setIntoSimpleComment(true);
					}else{ //Por descarte viene un asterisco
						comentario.setIntoMultipleComment(true);
					}
				}else if(comentario.isIntoSimpleComment() && this.actual() == '\n' ){
                                        //Comentario simple termina con nueva línea
					return MetodoHelper.buildToken(sb);
				}else if(comentario.isIntoMultipleComment() && ultimoLeido.equals('*') && this.actual() == '/' ){
                                        //Comentario múltiple termina con asterisco barra
					return MetodoHelper.buildToken(sb);
				}
                            /* A partid de acá el último leido es NULL o sea es el primero que leo */
			}else if(MetodoHelper.isBlankString("" + this.actual())){
                            continue;
                        }else if(this.actual() == '"'){
                            //Si lo primero que leo es una comilla doble
                            inString = true;
                        }else if(this.actual() == '\''){
                            //Si de la nada aparece una comilla simple es porque es una constante caracter
                            return this.nextCharConstant();
                        }else if( MetodoHelper.noEsLetra( this.actual() ) && !MetodoHelper.isBlankString("" + this.actual())){
                                //Si el primer caracter que leo no es una letra, lo devuelvo
				String actual = this.actual() + "";
				this.nextIndex ++;
				return MetodoHelper.buildToken(actual);
			}
                        //El actual pasa a ser el último cuando termina el ciclo
			ultimoLeido = this.actual();
                        //Si no terminó la palabra, agrego el caracter actual a la cadena resultante
			sb.append(this.actual());
		}
		return null;
	}
	/*
         * Devuelve la siguiente palabra que no sea un comentario
         */
	public Token siguientePalabraSinComentario(){
		Token palabra = siguientePalabra();
		while( palabra != null && palabra.getTipo() == Token.TIPO_COMENTARIO ){
			palabra = siguientePalabra();
		}
		return palabra;
	}
	/*
         * Primera palabra dentro del método
         */
	public Token primeraPalabra() {
		Token palabra = siguientePalabraSinComentario();
		boolean reconocida = false;
		int parentesisAbiertos = 0;
		//Avanzo hasta llegar adentro del método
		while( !reconocida && palabra != null ){
			//El método tiene que tener un nombre
			while( palabra != null && palabra.getTipo() != Token.TIPO_NOMBRE ){
				palabra = siguientePalabraSinComentario();
			}
			if( palabra == null ){
				return null;
			}
			palabra = siguientePalabraSinComentario();

			if( palabra == null ){
				return null;
			}
			
			//Tiene que seguir con un paréntesis abierto
			if( palabra.getTipo() != Token.TIPO_PARENTESIS_ABIERTA ){
				continue;
			}
			palabra = siguientePalabraSinComentario();
			parentesisAbiertos = 1;

			if( palabra == null ){
				return null;
			}
			
			while( !reconocida && palabra != null ){
				if( palabra.getTipo() == Token.TIPO_PARENTESIS_ABIERTA ){
					parentesisAbiertos ++;
				}
				if( palabra.getTipo() == Token.TIPO_PARENTESIS_CERRADA ){
					parentesisAbiertos --;
					if( parentesisAbiertos == 0 ){
						reconocida = true;
					}
				}
				palabra = siguientePalabra();
			}

			if( palabra == null ){
				return null;
			}
		}
		return palabra;
	}
	
        private static Token buildToken( StringBuilder sb ){
            return buildToken(sb.toString());
        }
        /*
         * Contruye un token a partir de un String
         */
        private static Token buildToken(String s){
            return new Token(s.trim());
        }
        
	private static boolean noEsLetra(char actual) { 
		return !Character.isLetterOrDigit(actual) && actual != '_' && actual != '-' && actual != '.';
	}

	private char actual(){
		return this.arrayCodigo[this.nextIndex];
	}
        private char siguiente(){
		return this.arrayCodigo[this.nextIndex];
	}
	public static boolean isBlankString(String s){
		return s.trim().isEmpty() || s.trim().equals(" ") || s.trim().equals("\t") || s.trim().equals("\n");
	}
        
        private Token nextCharConstant() {
            StringBuilder sb = new StringBuilder();
            int longitud = this.siguiente() == '\\' ? 4 : 3; // '\t' o 'a'
            int maximo = this.nextIndex + longitud;
            for( ; this.nextIndex < maximo && this.nextIndex < this.arrayCodigo.length  ; this.nextIndex ++ ){
                sb.append(actual());
            }
            this.nextIndex ++;
            return buildToken(sb);
        }
	
        public static void main (String[] args ) throws IOException{
	}
}
