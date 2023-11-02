package modelo;

import grafo.Vertice;

public class VerticeConNombre extends Vertice<String>{
	public VerticeConNombre(String nombreVertice) {
		super(nombreVertice);
	}

	@Override
	public String toString() {
		return getCarga().toString();
	}
}
