package controlador;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

import modelo.Coche;
import modelo.Garaje;

public class GarajeControl implements Serializable {

	// Instacia Singleton

	private static GarajeControl instance;

	// Contructor Vacio

	private GarajeControl() {
		// TODO Auto-generated constructor stub
	}

	// Singleton controlador

	public static GarajeControl getInstance() {
		if (instance == null) {
			synchronized (GarajeControl.class) {
				if (instance == null) {
					instance = new GarajeControl();
				}
			}
		}
		return instance;
	}

	// Variables de Clase
	private int id = 0;
	private HashMap<Integer, Garaje> garajes = new HashMap<>();

	// Metodos Getter y Setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HashMap<Integer, Garaje> getGarajesHM() {
		return garajes;
	}
	
	public void setGarajesHM(HashMap<Integer, Garaje> garajes) {
		this.garajes = garajes;
	}
	
	// funciones

	// Metodo para insertar un nuevo garaje con su id correspondiente
	public void insertarGaraje(String escuderia) {
		garajes.put(id, new Garaje(id, escuderia));
		id++;
	}

	// Metodo para obtener los garajes almacenados
	public Collection<Garaje> getGarajes() {
		return garajes.values();
	}
    
}
