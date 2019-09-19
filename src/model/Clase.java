package model;

import java.util.ArrayList;
import java.util.List;

public class Clase {
	String nombre;
	List<Metodo> metodos;
	int firstLine;
	int lastLine;
	public Clase(){
		this.nombre = "";
		this.firstLine = -1;
		this.lastLine = -1;
		metodos = new ArrayList<Metodo>();
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
	public List<Metodo> getMetodos() {
		return metodos;
	}
	public void setMetodos(List<Metodo> metodos) {
		this.metodos = metodos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		String met = "[";
		for( Metodo m : metodos ){
			met += m + ", ";
		}
		met += "]";
		return "Clase [nombre=" + nombre + ", metodos=" + met
				+ ", firstLine=" + firstLine + ", lastLine=" + lastLine + "]";
	}
	
}
