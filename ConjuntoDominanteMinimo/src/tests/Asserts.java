package tests;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Asserts {
	public static <T> boolean compararColecciones(Collection<T> coleccionA, Collection<T> coleccionB) {
		if (coleccionA == null || coleccionB == null)
			return false;

		if (coleccionA.size() != coleccionB.size())
			return false;

		return coleccionA.containsAll(coleccionB);
	}

	public static boolean compararArreglos(int[] arregloA, int[] arregloB) {
		Arrays.sort(arregloA);
		Arrays.sort(arregloB);

		return Arrays.equals(arregloA, arregloB);
	}

	public static <T> boolean compararPorOrdenIdentico(List<T> listaA, List<T> listaB) {
		if (listaA == null || listaB == null)
			return false;
		
		if (listaA.size() != listaB.size())
			return false;
		
		for (int i = 0; i < listaA.size(); i++) {
			boolean sonElMismoElemento = listaA.get(i).equals(listaB.get(i));
			
			if (!sonElMismoElemento) {
				return false;
			}
		}
		return true;
	}
}
