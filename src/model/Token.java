package model;

public class Token {
	public static final int NINGUN_TIPO = 0;
	public static final int TIPO_PRIVACIDAD = 1;
	public static final int TIPO_MODIFICADOR = 2;
	public static final int TIPO_NOMBRE = 3;
	public static final int TIPO_ELEMENTO_GENERAL = 4;
	public static final int TIPO_COMENTARIO = 5;
	public static final int TIPO_LLAVE_ABIERTA = 6;
	public static final int TIPO_LLAVE_CERRADA = 7;
	public static final int TIPO_PARENTESIS_ABIERTA = 8;
	public static final int TIPO_PARENTESIS_CERRADA = 9;
        public static final int TIPO_CONSTANTE_STRING = 10;

	private static final String[] privacidades = {"public", "private", "protected", "abstract", "final", "static"};
	private static final String[] modificadores = {"abstract", "final", "static"};
	
	int tipo;
	String valor;
	
	public Token(String valor){
		this.valor = valor;
		tipo = this.evaluarTipo();
	}
	
	public Integer getTipo() {
		return tipo;
	}

	public String getValor() {
		return valor;
	}

	private int evaluarTipo() {
		if( esLlaveAbierta() ){
			return Token.TIPO_LLAVE_ABIERTA;
		}
		if( esLlaveCerrada() ){
			return Token.TIPO_LLAVE_CERRADA;
		}
		if( esParentesisAbierta() ){
			return Token.TIPO_PARENTESIS_ABIERTA;
		}
		if( esParentesisCerrada() ){
			return Token.TIPO_PARENTESIS_CERRADA;
		}
		if( esPrivacidad() ){
			return Token.TIPO_PRIVACIDAD;
		}
		if( esModificador() ){
			return Token.TIPO_PRIVACIDAD;
		}
		if( esComentario() ){
			return Token.TIPO_COMENTARIO;
		}
                if( esConstanteString() ){
                    return Token.TIPO_CONSTANTE_STRING;
                }
		if( esNombre() ){
			return Token.TIPO_NOMBRE;
		}
		return Token.TIPO_ELEMENTO_GENERAL;
	}

	private boolean esLlaveCerrada() {
		return valor.equals("}");
	}

	private boolean esLlaveAbierta() {
		return valor.equals("{");
	}
	
	private boolean esParentesisAbierta() {
		return valor.equals("(");
	}
	
	private boolean esParentesisCerrada() {
		return valor.equals(")");
	}

	private boolean esNombre() {
		return this.valor.indexOf("<") == -1 && this.valor.indexOf("[") == -1 && 
                        this.valor.indexOf("{") == -1 && this.valor.indexOf(";") == -1;
	}

	private boolean esComentario() {
		return this.valor.trim().startsWith("/*") || this.valor.trim().startsWith("//");
	}

	private boolean esModificador() {
		for( String privacidad : Token.privacidades ){
			if( this.valor.equals(privacidad)){
				return true;
			}
		}
		return false;
	}

	private boolean esPrivacidad() {
		for( String modificador : Token.modificadores ){
			if( this.valor.equals(modificador)){
				return true;
			}
		}
		return false;
	}

    private boolean esConstanteString() {
        return valor.trim().startsWith("\"") && valor.trim().endsWith("\"");
    }
	
}
