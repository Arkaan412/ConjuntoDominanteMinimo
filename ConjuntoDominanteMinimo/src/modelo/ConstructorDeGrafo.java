package modelo;

import java.util.ArrayList;
import java.util.List;

import grafo.Grafo;
import grafo.Vertice;

public class ConstructorDeGrafo {
	Grafo<String> grafo;

	public ConstructorDeGrafo() {
		this.grafo = new Grafo<>();
	}

	public void agregarVertice(VerticeConNombre vertice) {
		grafo.agregarVertice(vertice);
	}

	public void eliminarVertice(VerticeConNombre vertice) {
		grafo.eliminarVertice(vertice);
	}

	public void agregarArista(VerticeConNombre verticeA, VerticeConNombre verticeB) {
		grafo.agregarArista(verticeA, verticeB);
	}

	public void eliminarArista(VerticeConNombre verticeA, VerticeConNombre verticeB) {
		grafo.eliminarArista(verticeA, verticeB);
	}

	public List<VerticeConNombre> obtenerVecinosActuales(VerticeConNombre vertice) {
		List<VerticeConNombre> vecinos = new ArrayList<>();

		for (Vertice<String> verticeActual : grafo.obtenerVecinos(vertice)) {
			vecinos.add((VerticeConNombre) verticeActual);
		}

		return vecinos;
	}

	public List<VerticeConNombre> obtenerVerticesDisponibles(VerticeConNombre vertice) {
		List<VerticeConNombre> vecinos = obtenerVecinosActuales(vertice);
		
		List<VerticeConNombre> verticesDisponibles = new ArrayList<>();

		for (Vertice<String> verticeActual : grafo.obtenerVertices()) {
			boolean esVecinoDeVerticeActual = vecinos.contains(verticeActual);
			boolean esElMismoVertice = verticeActual.equals(vertice);
			
			if (!esVecinoDeVerticeActual && !esElMismoVertice) {
				verticesDisponibles.add((VerticeConNombre) verticeActual);
			}
		}

		return verticesDisponibles;
	}

	public List<VerticeConNombre> obtenerVertices() {
		List<VerticeConNombre> vecinos = new ArrayList<>();

		for (Vertice<String> verticeActual : grafo.obtenerVertices()) {
			vecinos.add((VerticeConNombre) verticeActual);
		}

		return vecinos;
	}
	
	public Grafo<String> obtenerGrafo(){
		return grafo;
	}
}