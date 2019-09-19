package model;

public class Codigo {
	String codigo;
	StringBuilder sb;

	public Codigo(){
		this.codigo = "";
		sb = new StringBuilder();
	}
	
	public Codigo(String codigo){
		this.codigo = codigo;
		sb = new StringBuilder();
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public StringBuilder getSb() {
		return sb;
	}

	public void setSb(StringBuilder sb) {
		this.sb = sb;
	}

	public void buildCode(){
		codigo = sb.toString();
	}
	
	public char[] toCharArray(){
		if(codigo == null){
			return null;
		}
		return codigo.toCharArray();
	}
	
	@Override
	public String toString(){
		return "Codigo[" + codigo + "]";
	}
}
