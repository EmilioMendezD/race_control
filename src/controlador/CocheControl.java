package controlador;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import modelo.Coche;
import modelo.Garaje;

public class CocheControl implements Serializable {

	// Instacia Singleton

	private static CocheControl instance;

	// Contructor Vacio

	private CocheControl() {
		// TODO Auto-generated constructor stub
	}

	// Singleton controlador

	public static CocheControl getInstance() {
		if (instance == null) {
			synchronized (CocheControl.class) {
				if (instance == null) {
					instance = new CocheControl();
				}
			}
		}
		return instance;
	}

	// Variables de Clase
	private int id = 0;
	private HashMap<Integer, Coche> coches = new HashMap<>();

	// Metodos Getter y Setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HashMap<Integer, Coche> getCochesHM() {
		return coches;
	}
	
	public void setCochesHM(HashMap<Integer, Coche> coches) {
		this.coches = coches;
	}

	// funciones

	// Metodo para insertar un nuevo coche con su id correspondiente
	public void insertarCoche(String marca, String modelo) {
		coches.put(id, new Coche(id, marca, modelo));
		id++;
	}

	// Metodo para obtener los coches almacenados
	public Collection<Coche> getCoches() {
		return coches.values();
	}
	
	// Metodo para obtener los coches del fichero de recuperacion
	public int insertarCoches(File file, Garaje garaje) {
		AtomicInteger count = new AtomicInteger(0);
		try {
			Files.lines(file.toPath()).forEach(objeto -> {
				String[] atributos = objeto.split(",");
				if (atributos[0].length() > 0 && atributos[1].length() > 0) {
					Coche coche = new Coche(id, atributos[0], atributos[1]);
					if (garaje != null)
						coche.setGaraje(garaje);
					coches.put(id, coche);
					id++;
					count.incrementAndGet();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count.get();
	}

}
