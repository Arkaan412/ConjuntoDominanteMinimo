package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import grafo.Arista;
import grafo.Grafo;
import grafo.Vertice;

class GrafoTests {

	private static Grafo<Integer> grafo;

	private static Vertice<Integer> vertice0;
	private static Vertice<Integer> vertice1;
	private static Vertice<Integer> vertice2;
	private static Vertice<Integer> vertice3;
	private static Vertice<Integer> vertice4;
	private static Vertice<Integer> vertice5;
	private static Vertice<Integer> vertice6;

	@BeforeAll
	static void setUpBeforeClass() {
		grafo = new Grafo<Integer>();

		vertice0 = new Vertice<Integer>();
		vertice1 = new Vertice<Integer>();
		vertice2 = new Vertice<Integer>();
		vertice3 = new Vertice<Integer>();
		vertice4 = new Vertice<Integer>();
		vertice5 = new Vertice<Integer>();
		vertice6 = new Vertice<Integer>();
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
	}

	@Test
	void grafoVacioAlPrincipioTest() {
		assertTrue(grafo.estaVacio());
	}

	@Test
	void grafoSinAristasAlPrincipioTest() {
		assertEquals(0, grafo.obtenerAristas().size());
	}

	@Test
	void verticeQueNoPerteneceAlGrafoTest() {
		assertThrows(IllegalArgumentException.class, () -> grafo.obtenerVecinos(vertice0));
	}

	@Test
	void agregarVerticeTest() {
		grafo.agregarVertice(vertice0);

		assertEquals(1, grafo.tamanio());
	}

	@Test
	void existeVerticeTest() {
		grafo.agregarVertice(vertice0);

		assertTrue(grafo.existeVertice(vertice0));
	}

	@Test
	void verticeSinVecinosAlPrincipioTest() {
		grafo.agregarVertice(vertice0);

		assertTrue(grafo.noTieneVecinos(vertice0));
	}

	@Test
	void noSeAgreganVerticesDuplicadosTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice0);

		assertTrue(grafo.tamanio() == 1);
	}

	@Test
	void crearAristaConVerticeQueNoPerteneceAlGrafoTest() {
		grafo.agregarVertice(vertice0);

		assertThrows(IllegalArgumentException.class, () -> grafo.agregarArista(vertice0, vertice1));
	}

	@Test
	void verticeNoPuedeSerVecinoDeSiMismoTest() {
		grafo.agregarVertice(vertice0);

		assertThrows(IllegalArgumentException.class, () -> grafo.agregarArista(vertice0, vertice0));
	}

	@Test
	void agregarAristaTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);

		grafo.agregarArista(vertice0, vertice1);

		assertTrue(grafo.sonVecinos(vertice0, vertice1));
		assertTrue(grafo.sonVecinos(vertice1, vertice0));
	}

	@Test
	void agregarVariasAristasTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);

		grafo.agregarArista(vertice0, vertice1);
		grafo.agregarArista(vertice0, vertice2);
		grafo.agregarArista(vertice0, vertice3);

		assertTrue(grafo.sonVecinos(vertice0, vertice1));
		assertTrue(grafo.sonVecinos(vertice0, vertice2));
		assertTrue(grafo.sonVecinos(vertice0, vertice3));
	}

	@Test
	void agregarAristaMedianteUnaInstanciaDeAristaTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);

		Arista<Integer> arista01 = new Arista<>(vertice0, vertice1);

		grafo.agregarArista(arista01);

		assertTrue(grafo.existeArista(arista01));
	}

	@Test
	void agregarVariasAristasMedianteInstanciasDeAristaTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);

		Arista<Integer> arista01 = new Arista<>(vertice0, vertice1);
		Arista<Integer> arista02 = new Arista<>(vertice0, vertice2);
		Arista<Integer> arista03 = new Arista<>(vertice0, vertice3);

		grafo.agregarArista(arista01);
		grafo.agregarArista(arista02);
		grafo.agregarArista(arista03);

		assertTrue(grafo.sonVecinos(vertice0, vertice1));
		assertTrue(grafo.sonVecinos(vertice0, vertice2));
		assertTrue(grafo.sonVecinos(vertice0, vertice3));
	}

	@Test
	void tieneVecinosCuandoSeAgregaAristaTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);

		grafo.agregarArista(vertice0, vertice1);

		assertEquals(1, grafo.obtenerCantidadVecinos(vertice0));
		assertEquals(1, grafo.obtenerCantidadVecinos(vertice1));
	}

	@Test
	void noSeAgreganVecinosDuplicadosTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);

		grafo.agregarArista(vertice0, vertice1);
		grafo.agregarArista(vertice0, vertice1);

		assertEquals(1, grafo.obtenerCantidadVecinos(vertice0));
		assertEquals(1, grafo.obtenerCantidadVecinos(vertice1));
	}

	@Test
	void noSeAgreganVecinosDuplicadosConAristaInvertidaTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);

		grafo.agregarArista(vertice0, vertice1);
		grafo.agregarArista(vertice1, vertice0);

		assertEquals(1, grafo.obtenerCantidadVecinos(vertice0));
		assertEquals(1, grafo.obtenerCantidadVecinos(vertice1));
	}

	@Test
	void existeAristaTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);

		grafo.agregarArista(vertice0, vertice1);

		Arista<Integer> arista01 = new Arista<>(vertice0, vertice1);
		Arista<Integer> arista10 = new Arista<>(vertice1, vertice0);

		assertTrue(grafo.existeArista(arista01));
		assertTrue(grafo.existeArista(arista10));
	}

	@Test
	void noExisteAristaTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);

		Arista<Integer> arista01 = new Arista<>(vertice0, vertice1);
		Arista<Integer> arista10 = new Arista<>(vertice1, vertice0);

		assertFalse(grafo.existeArista(arista01));
		assertFalse(grafo.existeArista(arista10));
	}

	@Test
	void aristaInstanciadaCorrectamenteTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);

		int cargaArista = 15;

		grafo.agregarArista(vertice0, vertice1, cargaArista);

		Arista<Integer> arista = grafo.obtenerAristas().get(0);

		Vertice<Integer> verticeA = arista.getVerticeA();
		Vertice<Integer> verticeB = arista.getVerticeB();

		assertTrue(verticeA.equals(vertice0));
		assertTrue(verticeB.equals(vertice1));
		assertEquals(cargaArista, arista.getCarga());
	}

	@Test
	void agregarAristasAPartirDeAristasYaInstanciadas() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);

		Arista<Integer> arista12 = new Arista<>(vertice0, vertice1);
		Arista<Integer> arista24 = new Arista<>(vertice1, vertice3);
		Arista<Integer> arista34 = new Arista<>(vertice2, vertice3);

		grafo.agregarArista(arista12);
		grafo.agregarArista(arista24);
		grafo.agregarArista(arista34);

		assertTrue(grafo.sonVecinos(vertice0, vertice1));
		assertTrue(grafo.sonVecinos(vertice1, vertice3));
		assertTrue(grafo.sonVecinos(vertice2, vertice3));
	}

	@Test
	void noSeAgreganAristasDuplicadosConAristaInvertidaTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);

		grafo.agregarArista(vertice0, vertice1);
		grafo.agregarArista(vertice1, vertice0);

		int cantidadDeAristas = grafo.obtenerAristas().size();

		assertEquals(1, cantidadDeAristas);
	}

	@Test
	void obtenerAristasDeVerticeSinVecinosTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);
		
		List<Arista<Integer>> aristasDe0 = grafo.obtenerAristasDeVertice(vertice0);
		
		int cantidadDeAristasDe0 = aristasDe0.size();
		
		assertEquals(0, cantidadDeAristasDe0);
	}
	
	@Test
	void obtenerAristasDeVerticeTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);
		
		grafo.agregarArista(vertice0, vertice1);
		grafo.agregarArista(vertice0, vertice2);
		grafo.agregarArista(vertice0, vertice3);
		
		List<Arista<Integer>> aristasEsperadas = new ArrayList<>();
		
		aristasEsperadas.add(new Arista<Integer>(vertice0, vertice1));
		aristasEsperadas.add(new Arista<Integer>(vertice0, vertice2));
		aristasEsperadas.add(new Arista<Integer>(vertice0, vertice3));
		
		List<Arista<Integer>> aristasDe0 = grafo.obtenerAristasDeVertice(vertice0);
		
		int cantidadDeAristasDe0 = aristasDe0.size();
		
		boolean sonIguales = Asserts.compararColecciones(aristasEsperadas, aristasDe0);
		
		assertEquals(3, cantidadDeAristasDe0);
		assertTrue(sonIguales);
	}
	
	@Test
	void obtenerAristasDeVerticeEnGrafoCompletoTest() {
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

		List<Arista<Integer>> aristasEsperadas = new ArrayList<>();

		aristasEsperadas.add(new Arista<Integer>(vertice0, vertice1));
		aristasEsperadas.add(new Arista<Integer>(vertice0, vertice2));
		aristasEsperadas.add(new Arista<Integer>(vertice0, vertice3));
		
		List<Arista<Integer>> aristasDe0 = grafo.obtenerAristasDeVertice(vertice0);

		int cantidadDeAristasDe0 = aristasDe0.size();

		boolean sonIguales = Asserts.compararColecciones(aristasEsperadas, aristasDe0);

		assertEquals(3, cantidadDeAristasDe0);
		assertTrue(sonIguales);
	}

	@Test
	void eliminarAristaBorraLosVecinosCorrespondientesTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);
				
		grafo.agregarArista(vertice0, vertice3);
		grafo.agregarArista(vertice1, vertice3);
		grafo.agregarArista(vertice2, vertice3);
		
		Arista<Integer> arista13 = new Arista<>(vertice1, vertice3);
		
		grafo.eliminarArista(arista13);
		
		assertFalse(grafo.sonVecinos(vertice1, vertice3));
	}
	
	@Test
	void eliminarAristaMedianteUnaInstanciaDeAristaBorraLosVecinosCorrespondientesTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);

		Arista<Integer> arista01 = new Arista<>(vertice0, vertice1);
		Arista<Integer> arista02 = new Arista<>(vertice0, vertice2);
		Arista<Integer> arista03 = new Arista<>(vertice0, vertice3);

		grafo.agregarArista(arista01);
		grafo.agregarArista(arista02);
		grafo.agregarArista(arista03);
		
		grafo.eliminarArista(arista01);
		
		assertFalse(grafo.sonVecinos(vertice0, vertice1));
	}

	@Test
	void eliminarVerticeTest() {
		grafo.agregarVertice(vertice0);

		grafo.eliminarVertice(vertice0);

		assertTrue(grafo.estaVacio());
	}

	@Test
	void eliminarVerticeDeListasDeVecinosTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);

		grafo.agregarArista(vertice0, vertice1);
		grafo.agregarArista(vertice0, vertice2);

		grafo.eliminarVertice(vertice0);

		assertFalse(grafo.sonVecinos(vertice0, vertice1));
		assertFalse(grafo.sonVecinos(vertice0, vertice2));
	}

	@Test
	void eliminarVerticeBorraLasAristasCorrespondientesTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);

		grafo.agregarArista(vertice0, vertice1);
		grafo.agregarArista(vertice0, vertice2);

		grafo.eliminarVertice(vertice0);

		Arista<Integer> arista01 = new Arista<>(vertice0, vertice1);
		Arista<Integer> arista02 = new Arista<>(vertice0, vertice2);

		assertFalse(grafo.existeArista(arista01));
		assertFalse(grafo.existeArista(arista02));
	}

	@Test
	void eliminarTodosLosVerticesBorraTodasLasAristasTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);

		grafo.eliminarVertice(vertice0);
		grafo.eliminarVertice(vertice1);
		grafo.eliminarVertice(vertice2);
		grafo.eliminarVertice(vertice3);

		int cantidadDeAristas = grafo.obtenerAristas().size();

		assertEquals(0, cantidadDeAristas);
		assertTrue(grafo.estaVacio());
	}
}