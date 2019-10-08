package service;

import model.Metodo;

public class MetodoService {

	public static void calcularMetodo(Metodo metodo){
		Double porcentajeComentarios = CodigoService.calcularPorcentajeComantarios(metodo.getCodigo());
		Integer complejidad = CodigoService.calcularComplejidadCiclomatica(metodo.getCodigo());
		Integer fanOut = metodo.getMetodosLlamados().size();
		Integer longitud = CodigoService.calcularLongitud(metodo.getCodigo());
		Double volumen = CodigoService.calcularVolumen(metodo.getCodigo());
		Integer lineasTotales = CodigoService.calcularLineasTotales(metodo.getCodigo());
		Integer lineasComentadas = CodigoService.calcularLineasComentadas(metodo.getCodigo());

      	metodo.getEstadisticas().setVolumen(volumen);
      	metodo.getEstadisticas().setLongitud(longitud);
		metodo.getEstadisticas().setPorcentajeComentarios(porcentajeComentarios);
		metodo.getEstadisticas().setComplejidad(complejidad);
		metodo.getEstadisticas().setFanOut(fanOut);
		metodo.getEstadisticas().setTotales(lineasTotales);
		metodo.getEstadisticas().setLineasComentadas(lineasComentadas);
	}
}
