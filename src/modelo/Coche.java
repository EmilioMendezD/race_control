package modelo;

import java.io.Serializable;

public class Coche implements Serializable {

	// Variables de Clase

	private int id;
	private static int velocidadMaxima = 180;
	private String marca;
	private String modelo;
	private Garaje garaje;

	// Constructor Vacio

	public Coche() {
		// TODO Auto-generated constructor stub
	}

	// Constructor Parametrizado

	public Coche(int id, String marca, String modelo) {
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
	}

	// Metodos Getter y Setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getVelocidadMaxima() {
		return velocidadMaxima;
	}

	public static void setVelocidadMaxima(int velocidadMaxima) {
		Coche.velocidadMaxima = velocidadMaxima;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Garaje getGaraje() {
		return garaje;
	}

	public void setGaraje(Garaje garaje) {
		garaje = garaje;
	}
	
    @Override
    public String toString() {
        return "Coche{" + "id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", garaje=" + garaje + '}';
    }

}
