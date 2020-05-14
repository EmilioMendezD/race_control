package vista;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import controlador.CarreraControl;
import controlador.CocheControl;
import controlador.FicheroControl;
import controlador.GarajeControl;
import modelo.Carrera;
import modelo.Coche;
import modelo.Garaje;
import modelo.TipoCarrera;

public class MainVista {

	private static final String ayuda = " HELP:"
										+ "\n\n"
										+ ">crear coche <marca> <modelo>,  Ej. >crear coche ferrari enzo"
										+ "\n"
										+ ">crear garaje <escuderia>,  Ej. >crear garaje McLaren" 
										+ "\n"
										+ ">agregar coche <coche_id> <garaje_id>,  Ej. >agregar coche 1 McLaren" 
										+ "\n"
										+ ">crear carrera <nombre> <tipo>,  Ej1. >crear carrera standard, Ej2. >crear carrera eliminacion" 
										+ "\n"
										+ ">crear carrera <nombre> <tipo> <garaje_id>,  Ej. >crear carrera eliminacion McLaren" 
										+ "\n"
										+ ">listar coches,  Lista todos los coches almacenados" 
										+ "\n"
										+ ">listar coches <garaje_id>,  Ej. >listar coches McLaren,  Lista todos los coches almacenados en un garaje"  
										+ "\n"
										+ ">listar garajes,  lista todos los garajes almacenados";

	private static String input;
	
	public static final String path = "data.dat";
	
	public static void main(String[] args) {

		File file = new File(path);
		if(file.exists()) {
			 System.out.println("Cargando datos...");
			 FicheroControl.leerDatos(Paths.get(path));
			 System.out.println("Correcta recuperacion de datos.");
		}else
			System.out.println("Error de recuperacion de datos.");
		
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("race-control>");
			input = scanner.nextLine();
			String[] myargs = input.split(" ");
			try {
				switch (myargs[0]) {
				case "crear":
					switch (myargs[1]) {
					case "coche":
						if (myargs.length == 4){
							CocheControl.getInstance().insertarCoche(myargs[2], myargs[3]);
							System.out.println("Creando coche: " + myargs[2] + ", " + myargs[3]);
						}else
							System.out.println(ayuda);	
						break;
					case "garaje":
						GarajeControl.getInstance().insertarGaraje(myargs[2]);
						System.out.println("Creando garaje: " + myargs[2]);
						break;
					case "carrera":
						TipoCarrera tipo;
						if (myargs[3].equalsIgnoreCase("eliminacion")) {
							tipo = TipoCarrera.ELIMINACION;
						}else
							tipo = TipoCarrera.STANDARD;
						Carrera carrera = CarreraControl.getInstance().insertarCarrera(myargs[2], tipo);
						if (myargs.length == 4) {
							Garaje garaje = GarajeControl.getInstance().getGarajesHM().get(myargs[3]);
							carrera.setGaraje(garaje);
						}
					default:
						System.out.println(ayuda);
						break;
					}
					break;
				case "listar":
					switch (myargs[1]) {
					case "coches":
						if (myargs.length == 3) {
							Garaje garaje = GarajeControl.getInstance().getGarajesHM().get(Integer.parseInt(myargs[2]));
							
							for(Coche coche : garaje.getCoches()) {
								System.out.println(coche);
							}		
						} else {
							for(Coche coche : CocheControl.getInstance().getCoches()) {
								System.out.println(coche);
							}
						}	
						break;
					case "garajes":
						for(Garaje garaje : GarajeControl.getInstance().getGarajes()) {
							System.out.println(garaje);
						}
					}
					break;
					default:
						System.out.println(ayuda);
				case "agregar":
					switch (myargs[1]) {
						case "coche":
							Coche coche = CocheControl.getInstance().getCochesHM().get(Integer.parseInt(myargs[2]));
							Garaje garaje = GarajeControl.getInstance().getGarajesHM().get(Integer.parseInt(myargs[3]));
							garaje.getCoches().add(coche);
							System.out.println("Coche " + coche.getModelo() + "Agregado correctamente al garaje ");
							break;
						}
//					default:
					//verificar size of array
				case "guardar":
					boolean bl = FicheroControl.escribirDatos(Paths.get(myargs[2]));
					if (bl) {
						System.out.println("Guardado correctamente.");
					} else
						System.out.println("ERROR! No guardado, asegurate que la ruta este correcta.");
					break;
				case "cargar":
					boolean bool = FicheroControl.leerDatos(Paths.get(myargs[2])); 
					if (bool) {
						System.out.println("Carga exitosa!");
					} else 
						System.out.println("ERROR!, asegurate que la ruta este correcta.");
					break;
				case "Salir":
					System.exit(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
				//System.out.println(ayuda);
			}
		}

	}
}