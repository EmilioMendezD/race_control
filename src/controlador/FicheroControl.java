package controlador;

import modelo.Carrera;
import modelo.Coche;
import modelo.Garaje;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FicheroControl {

	// pulir patron singleton
	private static final CocheControl coche = CocheControl.getInstance();
	private static final GarajeControl garaje = GarajeControl.getInstance();
	private static final CarreraControl carrera = CarreraControl.getInstance();

	public static boolean leerDatos(Path path) {
		try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) {
			CocheControl.getInstance().setId(in.readInt());
			CocheControl.getInstance().setCochesHM((HashMap<Integer, Coche>) in.readObject());
			garaje.setId(in.readInt());
			garaje.setGarajesHM((HashMap<Integer, Garaje>) in.readObject());
			carrera.setId(in.readInt());
			carrera.setCarrerasHM((HashMap<Integer, Carrera>) in.readObject());

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean escribirDatos(Path path) {
		try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) {
			out.writeInt(coche.getId());
			out.writeObject(coche.getCochesHM());
			out.writeInt(garaje.getId());
			out.writeObject(garaje.getGarajesHM());
			out.writeInt(carrera.getId());
			out.writeObject(carrera.getCarrerasHM());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean escribirGanadoresCarrera(Path path, Carrera carrera, List<Coche> ganadores) {

		try (PrintWriter out = new PrintWriter(Files.newOutputStream(path))) {
			out.println("Premio disputado: " + carrera.getPremio());
			out.println("Tipo de carrera: " + carrera.getTipo());
			out.println("Nº de Competidores: " + carrera.getCompetidores().size());
			out.println("Duracion: " + carrera.getDuracion());

			for (int i = 0; i < ganadores.size(); i++) {
				Coche coche = ganadores.get(i);
				out.println("Posicion Nº " + (i + 1) + ": " + coche.toString());
			}
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
