package service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Archivo;
import model.Clase;
import model.Directorio;
import model.Metodo;

@SuppressWarnings("unused")
public class DirectorioService {
	
	public static Directorio leerDirectorio(String path){
		Directorio directorio = new Directorio(path);
		final File folder = new File(path);
                if( folder == null ){
                    throw new NullPointerException();
                }
                if( esJavaSource(path)){
                    directorio = new Directorio("");
                    directorio.getArchivos().add(ArchivoService.leerArchivo(path));
                }else{
                    for (final File fileEntry : folder.listFiles()) {
                        if (fileEntry.isDirectory()) {
                                Directorio subDirectorio = leerDirectorio(fileEntry.getPath());
                            directorio.getDirectorios().add(subDirectorio);
                        } else {
                                if( DirectorioService.esJavaSource(fileEntry.getPath()) )
                                        directorio.getArchivos().add(ArchivoService.leerArchivo(fileEntry.getPath()));
                        }
                    }
                }
		DirectorioService.calcularEstadisticas(directorio);
		return directorio;
	}

	private static boolean esJavaSource(String path) {
		return path.trim().endsWith(".java");
	}

	/*
	 * Calcula para cada método de cada clase 
	 * 		1) el % de comentarios 
	 * 		2) el Fan In 
	 * 		3) el Fan Out
	 * 		4) la complejidad ciclomática
	 */
	private static void calcularEstadisticas(Directorio directorio) {
		Map<String, Integer> mapaMetodos = directorio.getMetodos();
		/*
		 * Recorro todos los métodos de todas las clases de todos los archivos
		 * que contiene el directorio y cargo el mapa con Nombre de Clase -> Llamados
		 */
		for( Archivo archivo : DirectorioService.getAllArchivos(directorio) ){
			for( Clase clase : archivo.getClases() ){
				for( Metodo metodo : clase.getMetodos() ){
					DirectorioService.addToMap(mapaMetodos, metodo.getNombre(), 0);
					MetodoService.calcularMetodo(metodo);
					for( String metodoLlamado : metodo.getMetodosLlamados() ){
						DirectorioService.addToMap(mapaMetodos, metodoLlamado, 1);
					}
				}
			}
		}
		/*
		 * Recorro los métodos y busco en el mapa cuántas veces fue llamado
		 */
		for( Archivo archivo : DirectorioService.getAllArchivos(directorio) ){
			for( Clase clase : archivo.getClases() ){
				for( Metodo metodo : clase.getMetodos() ){
					DirectorioService.setFanIn(mapaMetodos, metodo);
				}
			}
		}
	}
	
	/*
	 * Método recursivo!
	 * Devuelve una lista que contiene todos sus archivos más los 
	 * archivos de sus subdirectorios
	 */
	public static List<Archivo> getAllArchivos(Directorio directorio){
		List<Archivo>archivos = new ArrayList<Archivo>();
		for( Directorio subDirectorio : directorio.getDirectorios() ){
			List<Archivo>subArchivos = DirectorioService.getAllArchivos(subDirectorio);
			if( subArchivos != null ){
				archivos.addAll(subArchivos);
			}
		}
		for( Archivo archivo : directorio.getArchivos() ){
			archivos.add(archivo);
		}
		return archivos;
	}


	private static void setFanIn(Map<String, Integer> mapaMetodos, Metodo metodo) {
		int fanIn = mapaMetodos.get(metodo.getNombre());
		metodo.getEstadisticas().setFanIn(fanIn);
	}

	public static void addToMap(Map<String, Integer> mapaMetodos, String nombre, int i) {
		nombre = nombre.trim();
		if( mapaMetodos.get(nombre) != null ){
			mapaMetodos.put(nombre, mapaMetodos.get(nombre) + i);
		}else{
			mapaMetodos.put(nombre, i);
		}
	}

	public static void main( String[] args ){
        }
}