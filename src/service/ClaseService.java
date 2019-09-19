package service;

import java.io.IOException;

import model.Archivo;
import model.Clase;
import model.Codigo;
import model.Metodo;

public class ClaseService {
	
	/*
	 * Recorre el archivo y va tomando todas las clases que están 
	 * definidas en él
	 */
	public static void cargarClases(Archivo archivo){
		String texto;
		try {
			texto = CodigoService.readFile(archivo.getPath());
			Codigo codigo = new Codigo(texto);
			CodigoHelper codigoHelper = new CodigoHelper(codigo);
			Clase nuevaClase = codigoHelper.getNextClass();
			while( nuevaClase != null ){
				ClaseService.calcularClase(nuevaClase, codigo);
				archivo.getClases().add(nuevaClase);
				nuevaClase = codigoHelper.getNextClass();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Recorre la clase y va creando objetos Metodo
	 * que los agrega a la lista de métodos de la clase
	 */
	public static void calcularClase(Clase clase, Codigo codigo) {
		MetodoHelper metodoHelper = new MetodoHelper(codigo.getCodigo().substring(clase.getFirstLine(), clase.getLastLine()));
		Metodo metodo = metodoHelper.getNextMetodo();
                int i = 0;
		while( metodo != null ){
                        metodo.setId(i++);
			clase.getMetodos().add(metodo);
			metodo = metodoHelper.getNextMetodo();
		}
	}
}
