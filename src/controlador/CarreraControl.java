package controlador;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import modelo.Carrera;
import modelo.Coche;
import modelo.Garaje;
import modelo.TipoCarrera;

public class CarreraControl implements Serializable {

	// Instacia Singleton

	private static CarreraControl instance;

	// Contructor Vacio

	private CarreraControl() {
		// TODO Auto-generated constructor stub
	}

	// Singleton controlador

	public static CarreraControl getInstance() {
		if (instance == null) {
			synchronized (CarreraControl.class) {
				if (instance == null) {
					instance = new CarreraControl();
				}
			}
		}
		return instance;
	}

	// Variables de Clase
	private int id = 0;
	private HashMap<Integer, Carrera> carreras = new HashMap<>();

	// Metodos Getter y Setter

	int getId() {
		return id;
	}

	void setId(int id) {
		this.id = id;
	}

	HashMap<Integer, Carrera> getCarrerasHM() {
		return carreras;
	}

	void setCarrerasHM(HashMap<Integer, Carrera> carreras) {
		this.carreras = carreras;
	}

	// funciones

	// Metodo para insertar una nueva carrera con su id correspondiente
	public Carrera insertarCarrera(String premio, TipoCarrera tipoCarrera) {
		Carrera carrera =new Carrera(id, premio, tipoCarrera); 
		carreras.put(id, carrera);
		id++;
		return carrera;
	}

	// Metodo para obtener los garajes almacenados
	public Collection<Carrera> getCarreras() {
		return carreras.values();
	}

}