package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Directorio {
	List<Archivo> archivos;
	List<Directorio> directorios;
	Map<String, Integer> metodos;
	String path;
	
	public Directorio(String path){
		this.path = path;
		archivos = new ArrayList<Archivo>();
		directorios = new ArrayList<Directorio>();
		metodos = new HashMap<String, Integer>();
	}
	
	public List<Archivo> getArchivos() {
		return archivos;
	}
	public void setArchivos(List<Archivo> archivos) {
		this.archivos = archivos;
	}
	public Map<String, Integer> getMetodos() {
		return metodos;
	}
	public void setMetodos(Map<String, Integer> metodos) {
		this.metodos = metodos;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<Directorio> getDirectorios() {
		return directorios;
	}
	public void setDirectorios(List<Directorio> directorios) {
		this.directorios = directorios;
	}

	@Override
	public String toString() {
		return "Directorio [archivos=" + archivos + ", directorios="
				+ directorios + "]";
	}
}
