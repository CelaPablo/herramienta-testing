package service;

import model.Metodo;

public class MetodoService {
	/*
	 * Calcula todo menos el fan in, ya que para eso preciso
	 * información de afuera del método
	 */
	public static void calcularMetodo(Metodo metodo){
		Double porcentajeComentarios = CodigoService.calcularPorcentajeComantarios(metodo.getCodigo());
		Integer complejidad = CodigoService.calcularComplejidadCiclomatica(metodo.getCodigo());
		Integer fanOut = metodo.getMetodosLlamados().size();
                Integer longitud = CodigoService.calcularLongitud(metodo.getCodigo());
                Double volumen = CodigoService.calcularVolumen(metodo.getCodigo());
		
                metodo.getEstadisticas().setVolumen(volumen);
                metodo.getEstadisticas().setLongitud(longitud);
		metodo.getEstadisticas().setPorcentajeComentarios(porcentajeComentarios);
		metodo.getEstadisticas().setComplejidad(complejidad);
		metodo.getEstadisticas().setFanOut(fanOut);
		//Notar que falta el FanIn
	}
}
