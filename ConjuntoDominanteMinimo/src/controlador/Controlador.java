package controlador;

import java.util.List;
import java.util.Set;

import grafo.Grafo;
import grafo.Vertice;
import modelo.ConjuntoDominanteSolver;
import modelo.ConstructorDeGrafo;
import modelo.VerticeConNombre;

public class Controlador {
	ConstructorDeGrafo constructorDeGrafo;
//	ConjuntoDominanteSolver<String> CDMSolver;

	public Controlador(ConstructorDeGrafo constructorDeGrafo) {
		this.constructorDeGrafo = constructorDeGrafo;
	}

	public void agregarVertice(VerticeConNombre vertice) {
		constructorDeGrafo.agregarVertice(vertice);
	}

	public void agregarVecino(VerticeConNombre verticeA, VerticeConNombre verticeB) {
		constructorDeGrafo.agregarArista(verticeA, verticeB);
	}

	public void eliminarVecino(VerticeConNombre verticeA, VerticeConNombre verticeB) {
		constructorDeGrafo.eliminarArista(verticeA, verticeB);
	}

	public void eliminarVertice(VerticeConNombre vertice) {
		constructorDeGrafo.eliminarVertice(vertice);
	}

	public List<VerticeConNombre> obtenerVecinosActuales(VerticeConNombre vertice) {
		return constructorDeGrafo.obtenerVecinosActuales(vertice);
	}

	public List<VerticeConNombre> obtenerVerticesDisponibles(VerticeConNombre vertice) {
		return constructorDeGrafo.obtenerVerticesDisponibles(vertice);
	}

	public List<VerticeConNombre> obtenerVertices() {
		return constructorDeGrafo.obtenerVertices();
	}

	public Set<Vertice<String>> obtenerConjuntoDominanteMinimo() {
		Grafo<String> grafo = constructorDeGrafo.obtenerGrafo();

		ConjuntoDominanteSolver<String> CDMSolver = new ConjuntoDominanteSolver<>(grafo);

		return CDMSolver.obtenerConjuntoDominanteMinimo();
	}
}