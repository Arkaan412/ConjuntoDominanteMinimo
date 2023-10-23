package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import grafo.Grafo;
import grafo.Vertice;
import modelo.CDMSolver;

class CDMSolverTests {
	static Grafo<Integer> grafo;

	static Vertice<Integer> vertice0;
	static Vertice<Integer> vertice1;
	static Vertice<Integer> vertice2;
	static Vertice<Integer> vertice3;
	static Vertice<Integer> vertice4;
	static Vertice<Integer> vertice5;
	static Vertice<Integer> vertice6;

	static ArrayList<Vertice<Integer>> vertices;

	static CDMSolver<Integer> cdmSolver;

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

		cdmSolver = new CDMSolver<>(grafo);
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

		cdmSolver = new CDMSolver<>(grafo);
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

		List<Vertice<Integer>> verticesObtenidos = cdmSolver.obtenerVerticesOrdenados();
		
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
	
	@Test
	void esCDMTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);
		
		grafo.agregarArista(vertice0, vertice1);
		grafo.agregarArista(vertice0, vertice2);
		grafo.agregarArista(vertice1, vertice3);
		
		HashSet<Vertice<Integer>> cdm = new HashSet<Vertice<Integer>>();
		
		cdm.add(vertice0);
		cdm.add(vertice1);
		
		cdmSolver.setCDM(cdm);
		
		assertTrue(cdmSolver.esCDM());		
	}
	
	@Test
	void esCDMConGrafoCompletoTest() {
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
		
		HashSet<Vertice<Integer>> cdm = new HashSet<Vertice<Integer>>();
		
		cdm.add(vertice0);
		
		cdmSolver.setCDM(cdm);
		
		assertTrue(cdmSolver.esCDM());		
	}
	
	@Test
	void verticeUniversalEsCDMTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);
		
		grafo.agregarArista(vertice0, vertice1);
		grafo.agregarArista(vertice0, vertice2);
		grafo.agregarArista(vertice0, vertice3);
		
		HashSet<Vertice<Integer>> cdm = new HashSet<Vertice<Integer>>();
		
		cdm.add(vertice0);
		
		cdmSolver.setCDM(cdm);
		
		assertTrue(cdmSolver.esCDM());
	}
	
	@Test
	void noEsCDMTest() {
		grafo.agregarVertice(vertice0);
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);

		grafo.agregarArista(vertice0, vertice1);
		grafo.agregarArista(vertice0, vertice2);

		HashSet<Vertice<Integer>> cdm = new HashSet<Vertice<Integer>>();
		
		cdm.add(vertice0);
		
		cdmSolver.setCDM(cdm);
		
		assertFalse(cdmSolver.esCDM());
	}
}
