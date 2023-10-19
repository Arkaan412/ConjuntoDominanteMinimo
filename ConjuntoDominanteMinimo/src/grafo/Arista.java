package grafo;

import java.util.Objects;

public class Arista<T> {
	private int carga;

	private Vertice<T> verticeA;
	private Vertice<T> verticeB;

	public Arista(Vertice<T> verticeA, Vertice<T> verticeB, int carga) {
		this.setVerticeA(verticeA);
		this.setVerticeB(verticeB);

		this.carga = carga;
	}

	public Arista(Vertice<T> verticeA, Vertice<T> verticeB) {
		this.setVerticeA(verticeA);
		this.setVerticeB(verticeB);

		this.carga = 0;
	}

	public int getCarga() {
		return carga;
	}

	public Vertice<T> getVerticeA() {
		return verticeA;
	}

	public Vertice<T> getVerticeB() {
		return verticeB;
	}

	public void setVerticeA(Vertice<T> verticeA) {
		this.verticeA = verticeA;
	}

	public void setVerticeB(Vertice<T> verticeB) {
		this.verticeB = verticeB;
	}

	public boolean perteneceAlVertice(Vertice<T> vertice) {
		return vertice == verticeA || vertice == verticeB;
	}

	@Override
	public int hashCode() {
		return Objects.hash(verticeA, verticeB);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Arista)) {
			return false;
		}
		Arista<?> other = (Arista<?>) obj;

		// La carga no influye en la igualdad. Nos interesa comparar por vértices.
		boolean sonIguales = Objects.equals(verticeA, other.verticeA) && Objects.equals(verticeB, other.verticeB);
		boolean sonInversas = Objects.equals(verticeA, other.verticeB) && Objects.equals(verticeB, other.verticeA);

		return sonIguales || sonInversas;
	}

	@Override
	public String toString() {
		return "Arista [vA=" + verticeA + ", vB=" + verticeB + ", c=" + carga + "]";
	}
}
