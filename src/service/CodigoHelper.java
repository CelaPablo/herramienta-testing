package service;

import java.io.IOException;

import model.Clase;
import model.Codigo;
import model.Comentario;

public class CodigoHelper {
	Codigo archivo;
	char[] arrayCodigo;
	int nextIndex;
	int currentLine;
	
	private static String[] modificadores = {"public","abstract", "final"};
	private static final int NO_CLASE = 0; //Aún no entré en la clase
	private static final int PRE_CLASE = 1; //Estoy en la línea de definición
	private static final int IN_CLASE = 2; //Estoy dentro de la clase
	
	private static boolean isModificador(String s){
		for(String modificador : modificadores){
			if( modificador.equals(s) ){
				return true;
			}
		}
		return false;
	}
	
	public CodigoHelper(Codigo codigo){
		this.archivo = codigo;
		this.arrayCodigo = codigo.toCharArray();
		this.nextIndex = 0;
		this.currentLine = 1;
		this.saltearEspacios();
	}
	
	public void saltearEspacios(){
		for( ; this.nextIndex < this.arrayCodigo.length && ( CodigoHelper.esBlanco(this.arrayCodigo[this.nextIndex]) ); this.nextIndex++){
			if( this.actual() == '\n'){
				this.currentLine ++;
			}
		}		
	}
	
	public static boolean esBlanco(char c){
		if( c == ' ' || c ==  '\t' || c == '\n' ){
			return true;
		}
		return false;
	}

	
	
	public Clase getNextClass() {
		Clase clase = new Clase();
		Comentario comentario = new Comentario();
		Character ultimoLeido = null;
		StringBuilder sb = new StringBuilder();
		boolean evitar = false;
		int inClase = CodigoHelper.NO_CLASE;
		int firstModifierIndex = -1;
		int classFirstLine = -1;
		int cantidadLlaves = 0;
                boolean inString = false;
		for( ; this.nextIndex < this.arrayCodigo.length ; this.nextIndex++){
			if( ultimoLeido != null ){
                                if( inString ){
                                    if( this.actual() == '"' && ultimoLeido != '\\'){
                                        inString = false;
                                    }
                                //Si no estoy en un comentario y no se reconoció '/*' o '//', lo que está adentro vale
                                }else if( !comentario.isInComentario() && !ultimoLeido.equals('/') || ( this.actual() != '/' && this.actual() != '*' ) ){
					//Si no encontró una clase pero reconoció una palabra
					if( inClase == CodigoHelper.NO_CLASE && CodigoHelper.esBlanco( this.actual() ) ){
						String nombre = sb.toString().trim().toLowerCase();
						if( !CodigoHelper.isModificador(nombre)){
							//Si una palabra no es un modificador pero es la palabra clase, ahí comienza la clase
							if( nombre.equals("class")){
								inClase = CodigoHelper.PRE_CLASE;
								//Si la clase no tiene modificadores, comienza en esa palabra
								if( firstModifierIndex == -1 ){
									firstModifierIndex = this.nextIndex - nombre.length();
									//classFirstLine = this.currentLine;
									classFirstLine = this.nextIndex - nombre.length();
								}
								//La clase comienza con el primer modificador o la palabra class
								clase.setFirstLine(classFirstLine);
							}
							else{
								//Si no era un modificador ni la palabra class, es otra cosa y no me importa
								firstModifierIndex = -1;
							}
						}else{
							//Sino quiere decir que es un modificador. Si es el primero, guardo los datos
							if( firstModifierIndex == -1 ){
								firstModifierIndex = this.nextIndex - nombre.length();
								//classFirstLine = this.currentLine;
								classFirstLine = this.nextIndex - nombre.length();
							}
						}
						//Cuando reconocí una palabra reseteo el String Builder
						sb = new StringBuilder();
					}else if( inClase == CodigoHelper.PRE_CLASE && CodigoHelper.esBlanco( this.actual() ) ){
						//Busco el nombre de la clase que es lo primero que viene después de la palabra class
						nombrarClase(clase, sb);
					}else if( inClase == CodigoHelper.PRE_CLASE && this.actual() == '{' ){
						//Si estoy en la definición de la clase y aparece una llave que abre
						//Entonces empieza ahí el contenido
						cantidadLlaves = 1;
						inClase = CodigoHelper.IN_CLASE;
                                                nombrarClase(clase, sb);
					}else if( inClase == CodigoHelper.IN_CLASE ){
                                        //Si estoy adentro de la clase
                                                if( !inString && this.actual() == '"' && ultimoLeido != '\''){
                                                    //Si no está en un String pero hay una comilla válida
                                                    inString = true;
                                                }else if( this.actual() == '{' && !this.esConstanteCaracter()){
							 cantidadLlaves ++;
						 }else if( this.actual() == '}' && !this.esConstanteCaracter()){
							 cantidadLlaves --;
							 //Si se cerraron todas las llaves que se abrieron, entonces terminó la clase
							 if( cantidadLlaves == 0 ){
								 //clase.setLastLine(this.currentLine);
								 clase.setLastLine(this.nextIndex);
								 //La clase ya tiene nombre, línea inicial y final. Por lo tanto la devuelvo
								 return clase;
							 }
						 }
					}else{
						sb.append(this.actual());
					}
				}else if( !comentario.isInComentario() ){
					//Si no es comentario quiere decir que se reconoció un comienzo de comentario (Sino hubiera entrado en el if anterior)
					if( this.actual() == '/' ){
						comentario.setIntoSimpleComment(true);
					}else{ //Por descarte viene un asterisco
						comentario.setIntoMultipleComment(true);
					}
				}else if(comentario.isIntoSimpleComment() && this.actual() == '\n' ){
					comentario.setIntoSimpleComment(false);
				}else if(comentario.isIntoMultipleComment() && ultimoLeido.equals('*') && this.actual() == '/' ){
					comentario.setIntoMultipleComment(false);
					//Evito guardar la barra '/' del  caracter de fin de comentario
					evitar = true;
				}
			}
			if( evitar ){
				evitar = false;
			}else{
				ultimoLeido = this.actual();
			}
			if( this.actual() == '\n'){
				this.currentLine ++;
			}
		}
		return null;
	}
        
        private boolean esConstanteCaracter(){
            if( this.nextIndex == 0 ){
                return false;
            }
            return ( this.siguiente() == '\'' ) && ( this.anterior() == '\'' ) ;
        }
        
	private char actual(){
		return this.arrayCodigo[this.nextIndex];
	}
	
        private char siguiente(){
		return this.arrayCodigo[this.nextIndex + 1];
	}
        private char anterior(){
		return this.arrayCodigo[this.nextIndex - 1];
	}
        private static void nombrarClase(Clase clase, StringBuilder sb){
            String nombre = sb.toString().trim();
            if( clase.getNombre().isEmpty() ){
                    clase.setNombre(nombre);
            }
        }
	public static void main (String[] args ) throws IOException{
	}
}
