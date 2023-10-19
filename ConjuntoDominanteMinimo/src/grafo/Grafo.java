package grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grafo<T> {
	private HashMap<Vertice<T>, HashSet<Vertice<T>>> vertices;
	private HashSet<Arista<T>> aristas;

	public Grafo() {
		vertices = new HashMap<Vertice<T>, HashSet<Vertice<T>>>();
		aristas = new HashSet<Arista<T>>();
	}

	public boolean estaVacio() {
		return this.tamanio() == 0;
	}

	public int tamanio() {
		return vertices.size();
	}

	public void agregarVertice(Vertice<T> vertice) {
		HashSet<Vertice<T>> listaVecinos = new HashSet<Vertice<T>>();

		vertices.putIfAbsent(vertice, listaVecinos);
	}

	public void agregarArista(Vertice<T> verticeA, Vertice<T> verticeB) {
		crearArista(verticeA, verticeB, 0);
	}

	public void agregarArista(Vertice<T> verticeA, Vertice<T> verticeB, int cargaArista) {
		crearArista(verticeA, verticeB, cargaArista);
	}

	public void agregarArista(Arista<T> arista) {
		Vertice<T> verticeA = arista.getVerticeA();
		Vertice<T> verticeB = arista.getVerticeB();

		int carga = arista.getCarga();

		crearArista(verticeA, verticeB, carga);
	}

	private void crearArista(Vertice<T> verticeA, Vertice<T> verticeB, int cargaArista) {
		if (!existeVertice(verticeA))
			throw new IllegalArgumentException("El vértice A indicado no pertenece al grafo.");
		if (!existeVertice(verticeB))
			throw new IllegalArgumentException("El vértice B indicado no pertenece al grafo.");

		agregarVecinos(verticeA, verticeB);

		Arista<T> nuevaArista = new Arista<>(verticeA, verticeB, cargaArista);

		if (!existeArista(nuevaArista))
			aristas.add(nuevaArista);
	}

	private void agregarVecinos(Vertice<T> verticeA, Vertice<T> verticeB) {
		if (sonElMismoVertice(verticeA, verticeB))
			throw new IllegalArgumentException("Los vértices indicados son el mismo vértice. No se admiten ciclos.");

		HashSet<Vertice<T>> listaVecinosA = vertices.get(verticeA);
		HashSet<Vertice<T>> listaVecinosB = vertices.get(verticeB);

		listaVecinosA.add(verticeB);
		listaVecinosB.add(verticeA);
	}

	public boolean sonElMismoVertice(Vertice<T> verticeA, Vertice<T> verticeB) {
		return verticeA.equals(verticeB);
	}

	public boolean existeVertice(Vertice<T> vertice) {
		return vertices.containsKey(vertice);
	}

	/*
	/* El contains() no devuelve true cuando se pregunta por una arista AB y existe
	 * una arista BA. No respeta la igualdad descripta en la clase Arista.
	 */
//	public boolean existeArista(Arista<T> arista) {
//		return aristas.contains(arista);	
//	}

	public boolean existeArista(Arista<T> arista) {
		for (Arista<T> aristaActual : aristas) {
			if (aristaActual.equals(arista)) {
				return true;
			}
		}
		return false;
	}

	public List<Vertice<T>> obtenerVertices() {
		return new ArrayList<Vertice<T>>(vertices.keySet());
	}

	public List<Arista<T>> obtenerAristas() {
		return new ArrayList<Arista<T>>(aristas);
	}

	public List<Vertice<T>> obtenerVecinos(Vertice<T> vertice) {
		if (!existeVertice(vertice))
			throw new IllegalArgumentException("El vértice indicado no pertenece al grafo.");

		ArrayList<Vertice<T>> listaVecinos = new ArrayList<>(vertices.get(vertice));

		return listaVecinos;
	}

	public int obtenerCantidadVecinos(Vertice<T> vertice) {
		if (!existeVertice(vertice))
			throw new IllegalArgumentException("El vértice indicado no pertenece al grafo.");

		return obtenerVecinos(vertice).size();
	}

	public boolean sonVecinos(Vertice<T> verticeA, Vertice<T> verticeB) {
		if (!existeVertice(verticeA) || !existeVertice(verticeB))
			return false;

		boolean BEsVecinoDeA = obtenerVecinos(verticeA).contains(verticeB);
		boolean AEsVecinoDeB = obtenerVecinos(verticeB).contains(verticeA);

		Arista<T> aristaAB = new Arista<>(verticeA, verticeB);

		boolean existeArista = existeArista(aristaAB);

		return BEsVecinoDeA && AEsVecinoDeB && existeArista;
	}

	public boolean noTieneVecinos(Vertice<T> vertice) {
		return obtenerCantidadVecinos(vertice) == 0;
	}

	public void eliminarVertice(Vertice<T> vertice) {
		if (existeVertice(vertice)) {
			eliminarAristasConectadas(vertice);

			vertices.remove(vertice);
		}
	}

	private void eliminarAristasConectadas(Vertice<T> verticeAEliminar) {
		Set<Vertice<T>> conjuntoVertices = vertices.keySet();

		for (Vertice<T> verticeActual : conjuntoVertices) {
			if (!sonElMismoVertice(verticeActual, verticeAEliminar))
				eliminarArista(verticeActual, verticeAEliminar);
		}
	}

	public void eliminarArista(Arista<T> arista) {
		if (existeArista(arista)) {
			Vertice<T> verticeA = arista.getVerticeA();
			Vertice<T> verticeB = arista.getVerticeB();

			eliminarVecinos(verticeA, verticeB);

			aristas.remove(arista);
		}
	}

	public void eliminarArista(Vertice<T> verticeA, Vertice<T> verticeB) {
		Arista<T> aristaAEliminar = obtenerArista(verticeA, verticeB);

		eliminarVecinos(verticeA, verticeB);

		aristas.remove(aristaAEliminar);
	}

	private Arista<T> obtenerArista(Vertice<T> verticeA, Vertice<T> verticeB) {
		Arista<T> arista = new Arista<>(verticeA, verticeB);
		List<Arista<T>> aristas = obtenerAristas();

		for (Arista<T> aristaActual : aristas) {
			if (aristaActual.equals(arista)) {
				return aristaActual;
			}
		}

		return null;
	}

	private void eliminarVecinos(Vertice<T> verticeA, Vertice<T> verticeB) {
		vertices.get(verticeA).remove(verticeB);
		vertices.get(verticeB).remove(verticeA);
	}

	public List<Arista<T>> obtenerAristasDeVertice(Vertice<T> verticeA) {
		List<Vertice<T>> vecinosDeA = obtenerVecinos(verticeA);

		List<Arista<T>> aristasDeA = new ArrayList<>();

		for (Vertice<T> vecinoActual : vecinosDeA) {
			aristasDeA.add(obtenerArista(verticeA, vecinoActual));
		}

		return aristasDeA;
	}
}