package controlador;

import modelo.ConstructorDeGrafo;
import vista.PantallaPrincipal;

public class Main {

	public static void main(String[] args) {
		ConstructorDeGrafo constructorDeGrafo = new ConstructorDeGrafo();
		Controlador controlador = new Controlador(constructorDeGrafo);

		try {
			PantallaPrincipal pantallaPrincipal = new PantallaPrincipal(controlador);

			pantallaPrincipal.mostrar();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
