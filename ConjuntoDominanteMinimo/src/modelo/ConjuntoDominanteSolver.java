package modelo;

import java.util.Set;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import grafo.Grafo;
import grafo.Vertice;

public class ConjuntoDominanteSolver<T> {
	private Grafo<T> grafo;
	private Set<Vertice<T>> conjuntoDominante;

	public ConjuntoDominanteSolver(Grafo<T> grafo) {
		this.grafo = grafo;

		conjuntoDominante = new HashSet<Vertice<T>>();
	}

	public Set<Vertice<T>> obtenerConjuntoDominanteMinimo() {
		List<Vertice<T>> vertices = obtenerVerticesOrdenados();

		while (!esConjuntoDominante(grafo, conjuntoDominante)) {
			conjuntoDominante.add(vertices.get(0));
			vertices.remove(0);
		}

		return conjuntoDominante;
	}

	private List<Vertice<T>> obtenerVerticesOrdenados() {
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

	public static <T> boolean esConjuntoDominante(Grafo<T> grafo, Set<Vertice<T>> conjuntoDominante) {
		if (conjuntoDominante.isEmpty())
			return false;

		List<Vertice<T>> verticesDelGrafo = grafo.obtenerVertices();

		Set<Vertice<T>> vecinosDeConjuntoDominante = new HashSet<Vertice<T>>();

		for (Vertice<T> verticeActual : conjuntoDominante) {
			vecinosDeConjuntoDominante.add(verticeActual);

			List<Vertice<T>> vecinosDeVerticeActual = grafo.obtenerVecinos(verticeActual);

			vecinosDeConjuntoDominante.addAll(vecinosDeVerticeActual);
		}

		boolean esConjuntoDominante = vecinosDeConjuntoDominante.containsAll(verticesDelGrafo);

		return esConjuntoDominante;
	}

	public void setConjuntoDominante(Set<Vertice<T>> conjuntoDominante) {
		this.conjuntoDominante = conjuntoDominante;
	}
}