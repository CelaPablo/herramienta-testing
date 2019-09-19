package model;

import java.util.ArrayList;
import java.util.List;

public class Metodo {
	String nombre;
	Codigo codigo;
	Estadisticas estadisticas;
	List<String> metodosLlamados;
	int firstLine;
	int lastLine;
	int id;
	public List<String> getMetodosLlamados() {
		if( metodosLlamados == null ){
			metodosLlamados = new ArrayList<String>();
		}
		return metodosLlamados;
	}
	public void setMetodosLlamados(List<String> metodosLlamados) {
		this.metodosLlamados = metodosLlamados;
	}
	public int getFirstLine() {
		return firstLine;
	}
	public void setFirstLine(int firstLine) {
		this.firstLine = firstLine;
	}
	public int getLastLine() {
		return lastLine;
	}
	public void setLastLine(int lastLine) {
		this.lastLine = lastLine;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Codigo getCodigo() {
		return codigo;
	}
	public void setCodigo(Codigo codigo) {
		this.codigo = codigo;
	}

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
	public Estadisticas getEstadisticas() {
		if( estadisticas == null ){
			estadisticas = new Estadisticas();
		}
		return estadisticas;
	}
	public void setEstadisticas(Estadisticas estadisticas) {
		this.estadisticas = estadisticas;
	}
	@Override
	public String toString() {
                return nombre;
	}
        public String debugToString(){
		String metodos = "[";
		for( String s : metodosLlamados ){
			metodos += s + ", ";
		}
		metodos += "]";
		return "Metodo [nombre=" + nombre + ", codigo=" + codigo
				+ ", estadisticas=" + estadisticas + ", metodosLlamados="
				+ metodos + ", firstLine=" + firstLine + ", lastLine="
				+ lastLine + "]";
        }
	
}