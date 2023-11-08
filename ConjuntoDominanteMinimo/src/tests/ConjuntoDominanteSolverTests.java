package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import grafo.Grafo;
import grafo.Vertice;
import modelo.ConjuntoDominanteSolver;

class ConjuntoDominanteSolverTests {
	static Grafo<Integer> grafo;

	static Vertice<Integer> vertice0;
	static Vertice<Integer> vertice1;
	static Vertice<Integer> vertice2;
	static Vertice<Integer> vertice3;
	static Vertice<Integer> vertice4;
	static Vertice<Integer> vertice5;
	static Vertice<Integer> vertice6;

	static ArrayList<Vertice<Integer>> vertices;

	static ConjuntoDominanteSolver<Integer> cdSolver;

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

		cdSolver = new ConjuntoDominanteSolver<>(grafo);
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

		cdSolver = new ConjuntoDominanteSolver<>(grafo);
	}

	@Test
	void esConjuntoDominanteTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);

		grafo.agregarArista(vertice0, vertice1);
		grafo.agregarArista(vertice0, vertice2);
		grafo.agregarArista(vertice1, vertice3);

		HashSet<Vertice<Integer>> conjuntoDominante = new HashSet<Vertice<Integer>>();

		conjuntoDominante.add(vertice0);
		conjuntoDominante.add(vertice1);

		assertTrue(ConjuntoDominanteSolver.esConjuntoDominante(grafo, conjuntoDominante));
	}

	@Test
	void esConjuntoDominanteConGrafoCompletoTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);

		grafo.agregarArista(vertice0, vertice1);
		grafo.agregarArista(vertice0, vertice2);
		grafo.agregarArista(vertice0, vertice3);
		grafo.agregarArista(vertice1, vertice2);
		grafo.agregarArista(vertice1, vertice3);
		grafo.agregarArista(vertice2, vertice3);

		HashSet<Vertice<Integer>> conjuntoDominante = new HashSet<Vertice<Integer>>();

		conjuntoDominante.add(vertice0);

		assertTrue(ConjuntoDominanteSolver.esConjuntoDominante(grafo, conjuntoDominante));
	}

	@Test
	void verticeUniversalEsConjuntoDominanteTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);

		grafo.agregarArista(vertice0, vertice1);
		grafo.agregarArista(vertice0, vertice2);
		grafo.agregarArista(vertice0, vertice3);

		HashSet<Vertice<Integer>> conjuntoDominante = new HashSet<Vertice<Integer>>();

		conjuntoDominante.add(vertice0);

		assertTrue(ConjuntoDominanteSolver.esConjuntoDominante(grafo, conjuntoDominante));
	}

	@Test
	void noEsConjuntoDominanteTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);

		grafo.agregarArista(vertice0, vertice1);
		grafo.agregarArista(vertice0, vertice2);

		HashSet<Vertice<Integer>> conjuntoDominante = new HashSet<Vertice<Integer>>();

		conjuntoDominante.add(vertice0);

		assertFalse(ConjuntoDominanteSolver.esConjuntoDominante(grafo, conjuntoDominante));
	}

	@Test
	void conjuntoVacioNoEsConjuntoDominanteTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);

		grafo.agregarArista(vertice0, vertice1);
		grafo.agregarArista(vertice0, vertice2);

		HashSet<Vertice<Integer>> conjuntoDominante = new HashSet<Vertice<Integer>>();

		assertFalse(ConjuntoDominanteSolver.esConjuntoDominante(grafo, conjuntoDominante));
	}

	@Test
	void obtenerConjuntoDominanteMinimoTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);

		grafo.agregarArista(vertice0, vertice1);
		grafo.agregarArista(vertice0, vertice2);
		grafo.agregarArista(vertice1, vertice3);

		HashSet<Vertice<Integer>> conjuntoDominanteEsperado = new HashSet<Vertice<Integer>>();

		conjuntoDominanteEsperado.add(vertice0);
		conjuntoDominanteEsperado.add(vertice1);

		Set<Vertice<Integer>> conjuntoDominanteObtenido = cdSolver.obtenerConjuntoDominanteMinimo();

		boolean sonIguales = Asserts.compararColecciones(conjuntoDominanteEsperado, conjuntoDominanteObtenido);

		assertTrue(sonIguales);
	}
	
	@Test
	void obtenerConjuntoDominanteMinimoEnGrafoNoConexoTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);
		
		grafo.agregarArista(vertice0, vertice1);
		grafo.agregarArista(vertice0, vertice2);
		
		HashSet<Vertice<Integer>> conjuntoDominanteEsperado = new HashSet<Vertice<Integer>>();
		
		conjuntoDominanteEsperado.add(vertice0);
		conjuntoDominanteEsperado.add(vertice1);
		conjuntoDominanteEsperado.add(vertice2);
		conjuntoDominanteEsperado.add(vertice3);
		
		Set<Vertice<Integer>> conjuntoDominanteObtenido = cdSolver.obtenerConjuntoDominanteMinimo();
		
		boolean sonIguales = Asserts.compararColecciones(conjuntoDominanteEsperado, conjuntoDominanteObtenido);
		
		assertTrue(sonIguales);
	}
	
	@Test
	void obtenerConjuntoDominanteMinimoEnGrafoConVerticeUniversalTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);

		grafo.agregarArista(vertice3, vertice0);
		grafo.agregarArista(vertice3, vertice1);
		grafo.agregarArista(vertice3, vertice2);

		HashSet<Vertice<Integer>> conjuntoDominanteEsperado = new HashSet<Vertice<Integer>>();

		conjuntoDominanteEsperado.add(vertice3);

		Set<Vertice<Integer>> conjuntoDominanteObtenido = cdSolver.obtenerConjuntoDominanteMinimo();

		boolean sonIguales = Asserts.compararColecciones(conjuntoDominanteEsperado, conjuntoDominanteObtenido);

		assertTrue(sonIguales);
	}
}