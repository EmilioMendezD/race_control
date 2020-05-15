package modelo;

import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import controlador.GarajeControl;

public class Torneo implements Serializable {

	// Variables de Clase

	private String nombre;
	private final Carrera[] carreras = new Carrera[10];
	private final HashMap<Coche, Integer> competidores = new HashMap<>();
	private Garaje garaje = null;
	private TipoCarrera tipo;

	// Constructor Vacio
	public Torneo() {
		// TODO Auto-generated constructor stub
	}

	// Constructor Parametrizado
	public Torneo(String nombre, TipoCarrera tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
	}

	// Metodos Getter y Setter
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Carrera[] getCarreras() {
		return carreras;
	}

	public HashMap<Coche, Integer> getCompetidores() {
		return competidores;
	}

	public Garaje getGaraje() {
		return garaje;
	}

	public void setGaraje(Garaje garaje) {
		this.garaje = garaje;
	}

	public TipoCarrera getTipo() {
		return tipo;
	}

	public void setTipo(TipoCarrera tipo) {
		this.tipo = tipo;
	}

	// Funciones

	public void buscarParticipantes() {
		competidores.clear();
		if (garaje == null) {
			GarajeControl.getInstance().getGarajes().stream().filter(garaje1 -> garaje1.getCoches().size() > 0)

					.forEach(garaje1 -> competidores
							.put(garaje1.getCoches().get(new Random().nextInt(garaje1.getCoches().size())), 0));

		} else
			garaje.getCoches().forEach(coche -> competidores.put(coche, 0));
		System.out.println("Participantes: " + competidores.keySet().size());
	}

	public Map<Coche, Integer> simular() {
		//code
		return competidores;
	}

}
