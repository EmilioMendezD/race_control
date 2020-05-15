package modelo;

import java.util.List;
import java.util.Arrays;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import controlador.GarajeControl;

public class Carrera implements Serializable {

	// Variables de Clase

	private int id;
	private String premio;
	private Garaje garaje = null;
	private final ArrayList<Coche> competidores = new ArrayList();
	
	private TipoCarrera tipo;

	// Constructor Vacio

	public Carrera() {
		// TODO Auto-generated constructor stub
	}

	// Constructor Parametrizado

	public Carrera(int id, String premio, TipoCarrera tipo) {
		this.id = id;
		this.premio = premio;
		this.tipo = tipo;
	}

	// Metodos Getter y Setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPremio() {
		return premio;
	}

	public void setPremio(String premio) {
		this.premio = premio;
	}

	public Garaje getGaraje() {
		return garaje;
	}

	public void setGaraje(Garaje garaje) {
		this.garaje = garaje;
	}

	public ArrayList<Coche> getCompetidores() {
		return competidores;
	}

	public TipoCarrera getTipo() {
		return tipo;
	}

	public String getDuracion() {
		String tiempo = "";
		switch (tipo) {
		case STANDARD:
			tiempo = "3 Horas.";
			break;
		case ELIMINACION:
			tiempo = (competidores.size() - 1) + " Minutos.";
			break;
		}
		
		return tiempo;
	}

	// Funciones

	public void registrarCompetidores() {
		competidores.clear();
		if (garaje == null) {
			GarajeControl.getInstance().getGarajes().stream()
			.filter(g -> g.getCoches().size() > 0).forEach(g -> competidores.add(g.getCoches().get(new Random().nextInt(g.getCoches().size()))));
		} else
			competidores.addAll(garaje.getCoches());
	}

	public List<Coche> simular() {
		ArrayList<Coche> posiciones = new ArrayList<>();
		List<Coche> ganadores = new ArrayList<>();
		posiciones.addAll(competidores);
		switch (tipo) {
		case STANDARD:
			Collections.shuffle(posiciones);
			ganadores = posiciones.subList(0, Math.min(posiciones.size(), 3));
			break;

		case ELIMINACION:
			Coche posicion1 = null;
			Coche posicion2 = null;
			Coche posicion3 = null;
			for (int vueltas = competidores.size() - 1; vueltas >= 0; vueltas--) {
				Collections.shuffle(competidores);
				switch (vueltas) {
				case 0:
					posicion1 = posiciones.get(0);
					break;
				case 1:
					posicion2 = posiciones.get(1);
					break;
				case 2:
					posicion3 = posiciones.get(2);
					break;
				}
				
				if (posiciones.size() > 1)
					posiciones.remove(posiciones.size() - 1);
				else
					break;
			}
			
			if (posicion2 == null)
				ganadores = Collections.singletonList(posicion1);
			else if (posicion3 == null)
				ganadores = Arrays.asList(posicion1, posicion2);
			else
				ganadores = Arrays.asList(posicion1, posicion2,posicion3);
			break;
		}

		return ganadores;
	}
	
    @Override
    public String toString() {
        return "Carrera{" + "id=" + id + ", premio=" + premio + ", garaje=" + garaje + ", tipo=" + tipo + ", competidores=" + competidores + '}';
    }




}    
