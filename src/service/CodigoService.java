package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import model.Codigo;
import model.Comentario;
import model.Token;

@SuppressWarnings("deprecation")
public class CodigoService {

	public static final String[] controladoresDeFlujo = {"if", "switch","while", "for"};
        public static final String[] listaOperadores = {"+", "-","*", "/", "{", "(", ":", "<", ">", "=", ";", ","};
        public static final String[] otrasPalabrasReservadas = {"private", "public", "protected", "static",
                    "int", "String", "double", "float", "Float", "Double", "Integer", "Character", "char", "return", "}", ")" };

	public static Codigo sacarComentarios(Codigo codigo){
		Codigo codigoPodado = new Codigo();
		Comentario comentario = new Comentario();
		Character ultimoLeido = null;
		for( Character c : codigo.toCharArray() ){
                        if( c == null ){
                            continue;
                        }
			if( ultimoLeido != null ){
				if( !comentario.isInComentario() && !ultimoLeido.equals('/') ){
					codigoPodado.getSb().append(ultimoLeido);
				}else if( !comentario.isInComentario() ){
					if( c.equals('/') ){
						comentario.setIntoSimpleComment(true);
					}else if( c.equals('*') ){
						comentario.setIntoMultipleComment(true);
					}else{
						codigoPodado.getSb().append(ultimoLeido);
					}
				}else if(comentario.isIntoSimpleComment() && c.equals('\n') ){
					comentario.setIntoSimpleComment(false);
				}else if(comentario.isIntoMultipleComment() && ultimoLeido.equals('*') && c.equals('/') ){
					comentario.setIntoMultipleComment(false);
					c = null;
				}
			}
			ultimoLeido = c;
		}
		codigoPodado.buildCode();
		return codigoPodado;
	}

	public static Double calcularPorcentajeComantarios(Codigo codigo){
		Comentario comentario = new Comentario();
		Character ultimoLeido = null;
                Integer lineasTotales = 0;
		Integer lineasComentarios = 0;
		boolean huboCodigo = false;
		boolean huboComentarios = false;
		for( Character c : codigo.toCharArray() ){
                        if( c == null ){
                            continue;
                        }
			if( ultimoLeido != null ){
				if( !comentario.isInComentario() && !ultimoLeido.equals('/')){
					if( ! new Character('/').equals(c) ){
						huboCodigo = true;
					}
				}else if( !comentario.isInComentario() ){
					if( c.equals('/') ){
						comentario.setIntoSimpleComment(true);
						huboComentarios = true;
					}else if( c.equals('*') ){
						comentario.setIntoMultipleComment(true);
						huboComentarios = true;
					}else{
						huboCodigo = true;
					}
				}else if(comentario.isIntoSimpleComment() && c.equals('\n') ){
					comentario.setIntoSimpleComment(false);
				}else if(comentario.isIntoMultipleComment() && ultimoLeido.equals('*') && c.equals('/') ){
					comentario.setIntoMultipleComment(false);
					huboComentarios = true;
					c = null;
				}else if( comentario.isInComentario() ){
					huboComentarios = true;
				}
			}
			if( new Character('\n').equals(c) ){
				if( huboComentarios ){
					lineasComentarios ++;
				}
        if( huboCodigo || huboComentarios ){
            lineasTotales ++;
        }
				huboCodigo = false;
				huboComentarios = false;
			}
			ultimoLeido = c;
		}
		return  (double) lineasComentarios  * 100.0 / ( (double) lineasTotales );
	}

	/* Calcular lineas totales de codigo */
	public static int calcularLineasTotales(Codigo codigo){
		Comentario comentario = new Comentario();
		Character ultimoLeido = null;
		Integer lineasTotales = 0;
		boolean huboCodigo = false;
		boolean huboComentarios = false;

		for( Character c : codigo.toCharArray() ){
			if( c == null ){ continue; }
			if( ultimoLeido != null ){
				if( !comentario.isInComentario() && !ultimoLeido.equals('/')){
					if( ! new Character('/').equals(c) ){
						huboCodigo = true;
					}
				}else if( !comentario.isInComentario() ){
					if( c.equals('/') ){
						comentario.setIntoSimpleComment(true);
						huboComentarios = true;
					}else if( c.equals('*') ){
						comentario.setIntoMultipleComment(true);
						huboComentarios = true;
					}else{
						huboCodigo = true;
					}
				}else if(comentario.isIntoSimpleComment() && c.equals('\n') ){
					comentario.setIntoSimpleComment(false);
				}else if(comentario.isIntoMultipleComment() && ultimoLeido.equals('*') && c.equals('/') ){
					comentario.setIntoMultipleComment(false);
					huboComentarios = true;
					c = null;
				}else if( comentario.isInComentario() ){
					huboComentarios = true;
				}
			}

			if( new Character('\n').equals(c) ){
                if( huboCodigo || huboComentarios ){
                    lineasTotales ++;
                }
				huboCodigo = false;
				huboComentarios = false;
			}
			ultimoLeido = c;
		}
		return lineasTotales;
	}
	
	/* Calcular lineas comentadas */
	public static int calcularLineasComentadas(Codigo codigo){
		Comentario comentario = new Comentario();
		Character ultimoLeido = null;

		Integer lineasComentarios = 0;

		boolean huboComentarios = false;
		for( Character c : codigo.toCharArray() ){
			if( c == null ){ continue; }
			if( ultimoLeido != null ){
				if( !comentario.isInComentario() && !ultimoLeido.equals('/')){
					
				}else if( !comentario.isInComentario() ){
					if( c.equals('/') ){
						comentario.setIntoSimpleComment(true);
						huboComentarios = true;
					}else if( c.equals('*') ){
						comentario.setIntoMultipleComment(true);
						huboComentarios = true;
					}
					
				}else if(comentario.isIntoSimpleComment() && c.equals('\n') ){
					comentario.setIntoSimpleComment(false);
				}else if(comentario.isIntoMultipleComment() && ultimoLeido.equals('*') && c.equals('/') ){
					comentario.setIntoMultipleComment(false);
					huboComentarios = true;
					c = null;
				}else if( comentario.isInComentario() ){
					huboComentarios = true;
				}
			}
			
			if( new Character('\n').equals(c) ){
				if( huboComentarios ){ lineasComentarios ++; }
				huboComentarios = false;
			}
			ultimoLeido = c;
		}
		return lineasComentarios;
	}

	/*
	 * En principio va a contar las cláusulas
	 * 		1) IF
	 * 		2) ELSE IF
	 * 		3) CASE
	 * 		4) WHILE
	 * 		5) FOR
	 */
	public static Integer calcularComplejidadCiclomatica(Codigo metodo){
		MetodoHelper mh = new MetodoHelper( metodo.getCodigo() );
		Token palabra = mh.primeraPalabra();
		int complejidad = 1;
		while( palabra != null ){
			if( CodigoService.esControladorDeFlujo(palabra.getValor()) ){
				complejidad ++;
			}
			palabra = mh.siguientePalabraSinComentario();
		}
		return complejidad;
	}

    static void calcularOperadoresYOperandos(Map<String, Integer> operadores, Map<String, Integer> operandos, Codigo metodo){
        MetodoHelper mh = new MetodoHelper( metodo.getCodigo() );
        Token palabra = mh.primeraPalabra();
        while( palabra != null ){
            if( CodigoService.esOperador(palabra.getValor()) ){
                DirectorioService.addToMap(operadores, palabra.getValor(), 1);
            }else if( CodigoService.esOtraPalabraReservada(palabra.getValor()) ){
                DirectorioService.addToMap(operandos, palabra.getValor(), 1);
            }else if( CodigoService.esOperando(palabra.getValor()) ){
                DirectorioService.addToMap(operandos, palabra.getValor(), 1);
            }
            palabra = mh.siguientePalabraSinComentario();
        }
    }

	static Integer calcularLongitud(Codigo metodo) {
        Map<String, Integer> operadores = new HashMap<>();
        Map<String, Integer> operandos = new HashMap<>();
        CodigoService.calcularOperadoresYOperandos(operadores, operandos, metodo);
        int N1 = CodigoService.sumarTodosLosValores(operadores);
        int N2 = CodigoService.sumarTodosLosValores(operandos);
        return N1 + N2;
    }

    static Double calcularVolumen(Codigo metodo) {
        Map<String, Integer> operadores = new HashMap<>();
        Map<String, Integer> operandos = new HashMap<>();
        CodigoService.calcularOperadoresYOperandos(operadores, operandos, metodo);
        int N1 = CodigoService.sumarTodosLosValores(operadores);
        int N2 = CodigoService.sumarTodosLosValores(operandos);
        int n1 = operadores.size();
        int n2 = operandos.size();
        return (N1 + N2) * logInBase(n1+n2, 2);
    }

    static Double logInBase(Integer number, Integer base){
        return Math.log(number) / Math.log(base);
    }

    static int sumarTodosLosValores(Map< ? extends Object, Integer> mapa){
        int suma = 0;
        for( Object o : mapa.keySet() ){
            suma += mapa.get(o);
        }
        return suma;
    }

	public static boolean esControladorDeFlujo(String palabra){
		palabra = palabra.trim();
		for( String s : controladoresDeFlujo ){
			if( s.equals(palabra) ){
				return true;
			}
		}
		return false;
	}

	public static String readFile(String fileName) throws IOException {
	    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    }
	}

	public static void main( String[] args ) throws IOException{
	}

    public static boolean esOperando(String valor) {
        return true;
    }

    public static boolean esOperador(String palabra) {
        return CodigoService.isInArray(palabra, listaOperadores);
    }

    public static boolean esOtraPalabraReservada(String palabra) {
        return CodigoService.isInArray(palabra, otrasPalabrasReservadas);
    }

    private static boolean isInArray(String valor, String[] array){
        valor = valor.trim();
        for( String s : array ){
                if( s.equals(valor) ){
                        return true;
                }
        }
        return false;
    }
}
