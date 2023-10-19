package grafo;

import java.util.Objects;

public class Vertice<T> {
	private static int siguienteID = 0;

	private int id;
	private T carga;

	public Vertice() {
		id = siguienteID;

		siguienteID++;
	}

	public Vertice(T carga) {
		this.carga = carga;

		id = siguienteID;

		siguienteID++;
	}

	public int getId() {
		return id;
	}

	public T getCarga() {
		return carga;
	}

	public void setCarga(T carga) {
		this.carga = carga;
	}

	@Override
	public int hashCode() {
		return Objects.hash(carga, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Vertice<?>)) {
			return false;
		}
		Vertice<?> other = (Vertice<?>) obj;

		return Objects.equals(carga, other.carga) && id == other.id;
	}

	@Override
	public String toString() {
		return "Vertice " + id;
	}
}
