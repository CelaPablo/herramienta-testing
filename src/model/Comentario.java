package model;

public class Comentario {
	
	public static final String SIMPLE = "SIMPLE";
	public static final String MULTIPLE = "MULTIPLE";
	public static final String NINGUNO = "NINGUNO";
	
	boolean intoSimpleComment;
	boolean intoMultipleComment;
	boolean intoComment;
	
	
	public Comentario(){
		intoSimpleComment = false;
		intoMultipleComment = false;
		intoComment = false;
	}
	
	public boolean isIntoSimpleComment() {
		return intoSimpleComment;
	}
	public void setIntoSimpleComment(boolean intoSimpleComment) {
		this.intoSimpleComment = intoSimpleComment;
	}
	public boolean isIntoMultipleComment() {
		return intoMultipleComment;
	}
	public void setIntoMultipleComment(boolean intoMultipleComment) {
		this.intoMultipleComment = intoMultipleComment;
	}
	public boolean isIntoComment() {
		return intoComment;
	}
	public void setIntoComment(boolean intoComment) {
		this.intoComment = intoComment;
	}
	public boolean isInComentario(){
		return intoMultipleComment || intoSimpleComment;
	}
	public String getTipoComentario(){
		if( intoMultipleComment ){
			return Comentario.MULTIPLE;
		}
		if( intoSimpleComment ){
			return Comentario.SIMPLE;
		}
		return Comentario.NINGUNO;
	}
}
