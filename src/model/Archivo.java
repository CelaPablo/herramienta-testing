package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Archivo {
	List<Clase> clases;
	String path;
	
	public Archivo(String path){
		this.path = path;
		this.clases = new ArrayList<Clase>();
	}
	
	public List<Clase> getClases() {
		return clases;
	}
	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

        public String getNombre(){
            if( path == null ){
                return "";
            }
            return path.substring(path.lastIndexOf(File.separator) + 1, path.lastIndexOf("."));
        }
        
	@Override
	public String toString() {
		String clasesString = "[";
		for( Clase c : clases ){
			clasesString += c + ", ";
		}
		clasesString += "]";
		return "Archivo [clases=" + clasesString + ", path=" + path + "]";
	}
}
