package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import grafo.Grafo;
import grafo.Vertice;
import modelo.ConjuntoDominante;

class ConjuntoDominanteTests {
	static Grafo<Integer> grafo;

	static Vertice<Integer> vertice0;
	static Vertice<Integer> vertice1;
	static Vertice<Integer> vertice2;
	static Vertice<Integer> vertice3;
	static Vertice<Integer> vertice4;
	static Vertice<Integer> vertice5;
	static Vertice<Integer> vertice6;

	static ArrayList<Vertice<Integer>> vertices;

	static ConjuntoDominante<Integer> conjuntoDominante;

	@BeforeAll
	static void setUpBeforeClass() {
		grafo = new Grafo<>();

		vertice0 = new Vertice<>();
		vertice1 = new Vertice<>();
		vertice2 = new Vertice<>();
		vertice3 = new Vertice<>();
		vertice4 = new Vertice<>();
		vertice5 = new Vertice<>();
		vertice6 = new Vertice<>();

		conjuntoDominante = new ConjuntoDominante<>(grafo);
	}

	@AfterEach
	void tearDown() {
		grafo.eliminarVertice(vertice0);
		grafo.eliminarVertice(vertice1);
		grafo.eliminarVertice(vertice2);
		grafo.eliminarVertice(vertice3);
		grafo.eliminarVertice(vertice4);
		grafo.eliminarVertice(vertice5);
		grafo.eliminarVertice(vertice6);

		conjuntoDominante = new ConjuntoDominante<>(grafo);
	}

	@Test
	void ordenarVerticesTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);

		grafo.agregarArista(vertice0, vertice1);
		grafo.agregarArista(vertice0, vertice2);
		grafo.agregarArista(vertice1, vertice2);

		List<Vertice<Integer>> verticesObtenidos = conjuntoDominante.obtenerVerticesOrdenados();
		
		boolean estaOrdenada = estaOrdenadaDeMayorAMenor(verticesObtenidos);

		assertTrue(estaOrdenada);
	}
	
	public static <T> boolean estaOrdenadaDeMayorAMenor(List<Vertice<Integer>> lista) {
		if (lista == null)
			return false;

		for (int i = 0; i < lista.size() - 1; i++) {
			int j = i + 1;
			
			int cantidadDeVecinosDeI = grafo.obtenerCantidadVecinos(lista.get(i));
			int cantidadDeVecinosDeJ = grafo.obtenerCantidadVecinos(lista.get(j));
			
			if (cantidadDeVecinosDeI < cantidadDeVecinosDeJ) {
				return false;
			}
		}
		return true;
	}
}
