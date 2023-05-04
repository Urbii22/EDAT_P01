package es.ubu.gii.edat.pr01;

/**
 * Clase que alberga el código completo para la Práctica 01
 * 
 * @author Victor De Marco Velasco
 * @author Diego Urbaneja Portal
 *
 */
public class ElementoMayoritario<E> {

	/**
	 * Para poder devolver dos valores diferentes por parte de un mismo método, se
	 * necesita una clase sencilla con dos atributos. En este caso, la clase
	 * RespuestaMayoritaria permite encapsular en el mismo objeto: - elemento
	 * mayoritario encontrado - el numero de veces que se repite (o frecuencia) como
	 * un entero
	 * 
	 * Al devolver la respuesta de cada metodo, se deberán incluir los dos datos
	 * encontrados dentro de un objeto de este tipo y devolverlo como respuesta.
	 * 
	 * @author bbaruque
	 *
	 * @param <E> elemento generico
	 */
	public static class RespuestaMayoritaria<E> {

		private E elemento;
		private int frecuencia;

		/**
		 * constructor 
		 * @param elemento mayoritorio
		 * @param frecuencia en la que aparece
		 */
		public RespuestaMayoritaria(E elemento, int frecuencia) {
			this.elemento = elemento;
			this.frecuencia = frecuencia;
		}

		/**
		 * getter
		 * @return elemento
		 */
		public E getElemento() {
			return elemento;
		}
		
		/**
		 * getter
		 * @return frecuecnia
		 */
		public int getFrecuencia() {
			return frecuencia;
		}
	}

	/**
	 * Metodo Iterativo que te devuelve el elemento mayoritario y el numero de veces que aparece
	 * @param <E> generico
	 * @param array pasado como entrada para comprobación
	 * @return RespuestaMayoritaria
	 */
	public static <E> RespuestaMayoritaria<E> mayoritarioIterativo(E[] array) {

		// TODO Completar el metodo iterativo
		int frecMax = 0;
		E mayor = null;
		for (E elem: array) {
			int frecAux = 0;
			for (int i = 0; i < array.length; i++) {
				if (elem == array[i]) {
					frecAux++;
				}
			}
			if (frecAux > frecMax) {
				frecMax = frecAux;
				mayor = elem;
			}
		}
		if (frecMax > (array.length / 2)) {
			return new RespuestaMayoritaria<E>(mayor, frecMax);

		} else {
			return new RespuestaMayoritaria<E>(null, 0);
		}
	}

	/**
	 * Metodo Recursivo que llama a una subfuncion para que esta sea la que calcule el elemento mayoritario
	 * @param <E> generico
	 * @param array pasado como entrada para su comprobacion
	 * @return encontrarElementoMayoritarioRecursivo(array, 0, array.length - 1) que consta d ela llamda a la subfuncion que mas tarde esta devolvera lo que buscamos 
	 * el elemento mayoritario y su frecuencia 
	 */

    public static <E> RespuestaMayoritaria<E> mayoritarioRecursivo(E[] array) {
        return encontrarElementoMayoritarioRecursivo(array, 0, array.length - 1);
    }
    
    /**
     * Metodo que procede a llamarse asi mismo hasta que es capaz de determinar el elemento mayoritario del array y su frecuencia
     * @param <E> generico
     * @param array
     * @param inicio
     * @param fin
     * @return RespuestaMayoritaria<>(x,y) que consta del elemento mayoritario y su frecuencia
     */
    private static <E> RespuestaMayoritaria<E> encontrarElementoMayoritarioRecursivo(E[] array, int inicio, int fin) {
        if (inicio == fin) {
            // Caso base: el subarray tiene un solo elemento
            return new RespuestaMayoritaria<>(array[inicio], 1);
        } else {
            // Caso general: dividir el array en dos y encontrar los elementos mayoritarios de cada mitad
            int mitad = (inicio + fin) / 2;
            RespuestaMayoritaria<E> respuestaIzquierda = encontrarElementoMayoritarioRecursivo(array, inicio, mitad);
            RespuestaMayoritaria<E> respuestaDerecha = encontrarElementoMayoritarioRecursivo(array, mitad + 1, fin);

            // Combinar las respuestas de cada mitad
            if (respuestaIzquierda.getElemento() == (respuestaDerecha.getElemento())) {
                // Si los elementos mayoritarios de cada mitad son iguales, ese es el elemento mayoritario global
                return new RespuestaMayoritaria<E>(respuestaIzquierda.getElemento(), respuestaIzquierda.getFrecuencia() + respuestaDerecha.getFrecuencia());
            } else {
                // Si los elementos mayoritarios de cada mitad son distintos, contar cuántas veces aparece cada uno en el subarray completo
                int contadorIzquierda = 0;
                int contadorDerecha = 0;

                for (int i = inicio; i <= fin; i++) {
                    if (array[i].equals(respuestaIzquierda.getElemento())) {
                        contadorIzquierda++;
                    } else if (array[i].equals(respuestaDerecha.getElemento())) {
                        contadorDerecha++;
                    }
                }

                // Devolver la respuesta con el elemento mayoritario global, si existe
                if (contadorIzquierda > (fin - inicio + 1) / 2) {
                    return new RespuestaMayoritaria<E>(respuestaIzquierda.getElemento(), contadorIzquierda);
                } else if (contadorDerecha > (fin - inicio + 1) / 2) {
                    return new RespuestaMayoritaria<E>(respuestaDerecha.getElemento(), contadorDerecha);
                } else {
                    return new RespuestaMayoritaria<E>(null, 0);
                }
            }
        }
    }
}
