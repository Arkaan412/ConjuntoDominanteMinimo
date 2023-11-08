package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.ConstructorDeGrafo;
import modelo.VerticeConNombre;

class ConstructorDeGrafoTests {
	static ConstructorDeGrafo constructorDeGrafo;

	static VerticeConNombre vertice0;
	static VerticeConNombre vertice1;
	static VerticeConNombre vertice2;
	static VerticeConNombre vertice3;
	static VerticeConNombre vertice4;
	static VerticeConNombre vertice5;
	static VerticeConNombre vertice6;

	@BeforeAll
	static void setUpBeforeClass() {
		constructorDeGrafo = new ConstructorDeGrafo();

		vertice0 = new VerticeConNombre("0");
		vertice1 = new VerticeConNombre("1");
		vertice2 = new VerticeConNombre("2");
		vertice3 = new VerticeConNombre("3");
		vertice4 = new VerticeConNombre("4");
		vertice5 = new VerticeConNombre("5");
		vertice6 = new VerticeConNombre("6");
	}

	@AfterEach
	void tearDown() {
		constructorDeGrafo = new ConstructorDeGrafo();
	}

	@Test
	void obtenerVerticesDisponiblesParaVerticeQueNoPerteneceAlGrafoTest() {
		assertThrows(IllegalArgumentException.class, () -> constructorDeGrafo.obtenerVerticesDisponibles(vertice0));
	}

	@Test
	void obtenerVerticesDisponiblesTest() {
		constructorDeGrafo.agregarVertice(vertice0);
		constructorDeGrafo.agregarVertice(vertice1);
		constructorDeGrafo.agregarVertice(vertice2);
		constructorDeGrafo.agregarVertice(vertice3);

		constructorDeGrafo.agregarArista(vertice0, vertice1);
		constructorDeGrafo.agregarArista(vertice0, vertice2);

		List<VerticeConNombre> verticesEsperados = new ArrayList<>();
		verticesEsperados.add(vertice3);

		List<VerticeConNombre> verticesObtenidos = constructorDeGrafo.obtenerVerticesDisponibles(vertice0);

		boolean sonIguales = Asserts.compararColecciones(verticesEsperados, verticesObtenidos);

		assertTrue(sonIguales);
	}

	@Test
	void obtenerVerticesDisponiblesParaVerticeSinVecinosTest() {
		constructorDeGrafo.agregarVertice(vertice0);
		constructorDeGrafo.agregarVertice(vertice1);
		constructorDeGrafo.agregarVertice(vertice2);
		constructorDeGrafo.agregarVertice(vertice3);

		List<VerticeConNombre> verticesEsperados = new ArrayList<>();
		verticesEsperados.add(vertice1);
		verticesEsperados.add(vertice2);
		verticesEsperados.add(vertice3);

		List<VerticeConNombre> verticesObtenidos = constructorDeGrafo.obtenerVerticesDisponibles(vertice0);

		boolean sonIguales = Asserts.compararColecciones(verticesEsperados, verticesObtenidos);

		assertTrue(sonIguales);
	}

	@Test
	void obtenerVerticesDisponiblesParaVerticeUniversalTest() {
		constructorDeGrafo.agregarVertice(vertice0);
		constructorDeGrafo.agregarVertice(vertice1);
		constructorDeGrafo.agregarVertice(vertice2);
		constructorDeGrafo.agregarVertice(vertice3);

		constructorDeGrafo.agregarArista(vertice0, vertice1);
		constructorDeGrafo.agregarArista(vertice0, vertice2);
		constructorDeGrafo.agregarArista(vertice0, vertice3);

		List<VerticeConNombre> verticesObtenidos = constructorDeGrafo.obtenerVerticesDisponibles(vertice0);

		assertTrue(verticesObtenidos.isEmpty());
	}

}
