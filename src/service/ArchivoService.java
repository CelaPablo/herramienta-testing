package service;

import model.Archivo;

public class ArchivoService {
	/*
	 * Crea y devuelve un objeto archivo que contendrá
	 * todas las clases que se encuentren en ese archivo
	 */
	public static Archivo leerArchivo(String path){
		Archivo archivo = new Archivo(path);
		ClaseService.cargarClases(archivo);
		return archivo;
	}
	
	public static void main( String[] args){
	}
}