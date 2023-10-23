package modelo;

import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import grafo.Grafo;
import grafo.Vertice;

public class CDMSolver<T> {
	private Grafo<T> grafo;
	private Set<Vertice<T>> cdm;

	public CDMSolver(Grafo<T> grafo) {
		this.grafo = grafo;

		cdm = new HashSet<Vertice<T>>();
	}

	public List<Vertice<T>> obtenerVerticesOrdenados() {
		List<Vertice<T>> vertices = grafo.obtenerVertices();

		ordenarVertices(vertices);

		return vertices;
	}

	private void ordenarVertices(List<Vertice<T>> vertices) {
		Collections.sort(vertices, new Comparator<Vertice<T>>() {
			@Override
			public int compare(Vertice<T> verticeA, Vertice<T> verticeB) {
				int cantidadDeVecinosDeA = grafo.obtenerCantidadVecinos(verticeA);
				int cantidadDeVecinosDeB = grafo.obtenerCantidadVecinos(verticeB);

				return cantidadDeVecinosDeA - cantidadDeVecinosDeB;
			}
		});

		Collections.reverse(vertices);
	}

	public boolean esCDM() {
		List<Vertice<T>> verticesDelGrafo = grafo.obtenerVertices();

		Set<Vertice<T>> vecinosDeCDM = new HashSet<Vertice<T>>();

		for (Vertice<T> verticeActual : cdm) {
			vecinosDeCDM.add(verticeActual);

			List<Vertice<T>> vecinosDeVerticeActual = grafo.obtenerVecinos(verticeActual);

			vecinosDeCDM.addAll(vecinosDeVerticeActual);
		}

		boolean esCDM = vecinosDeCDM.containsAll(verticesDelGrafo);

		return esCDM;
	}

	public void setCDM(Set<Vertice<T>> cdm) {
		this.cdm = cdm;
	}
}