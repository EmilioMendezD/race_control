package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Garaje implements Serializable {

	// Variables de Clase

	private int id;
	private String escuderia;
	private final ArrayList<Coche> coches = new ArrayList();

	// Constructor Vacio
	public Garaje() {
		// TODO Auto-generated constructor stub
	}

	// Constructor Parametrizado
	public Garaje(int id, String escuderia) {
		// TODO Auto-generated constructor stub
	}

	// Metodos Getter y Setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEscuderia() {
		return escuderia;
	}

	public void setEscuderia(String escuderia) {
		this.escuderia = escuderia;
	}

	public ArrayList<Coche> getCoches() {
		return coches;
	}
	
    @Override
    public String toString() {
        return "Garaje{" + "id=" + id + ", escuderia=" + escuderia + ", coches=" + coches + '}' + "\n";
    }

}
