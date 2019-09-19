package model;

public class Estadisticas {
	Integer fanIn;
	Integer fanOut;
	Integer longitud;
        Double volumen;
        Integer complejidad;
	Double porcentajeComentarios;
	public Integer getFanIn() {
		return fanIn;
	}
	public void setFanIn(Integer fanIn) {
		this.fanIn = fanIn;
	}
	public Integer getFanOut() {
		return fanOut;
	}
	public void setFanOut(Integer fanOut) {
		this.fanOut = fanOut;
	}
	public Integer getComplejidad() {
		return complejidad;
	}
	public void setComplejidad(Integer complejidad) {
		this.complejidad = complejidad;
	}
	public Double getPorcentajeComentarios() {
		return porcentajeComentarios;
	}
	public void setPorcentajeComentarios(Double porcentajeComentarios) {
		this.porcentajeComentarios = porcentajeComentarios;
	}

        public Integer getLongitud() {
            return longitud;
        }

        public Double getVolumen() {
            return volumen;
        }

        public void setLongitud(Integer longitud) {
            this.longitud = longitud;
        }

        public void setVolumen(Double volumen) {
            this.volumen = volumen;
        }

	@Override
	public String toString() {
		return "Estadisticas [fanIn=" + fanIn + ", fanOut=" + fanOut
				+ ", complejidad=" + complejidad + ", porcentajeComentarios="
				+ porcentajeComentarios + "]";
	}
	
}