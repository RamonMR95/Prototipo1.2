/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Nif del modelo  
 *  @since: prototipo1.1
 *  @source: Nif.java 
 *  @version: 1.1 - 2019/01/22 
 *  @author: Ramon Moñino
 */
package modelo;

public class Nif {
	/**
	 * Atributo de la clase que va a contener el DNI de los usuarios
	 */
	private String texto;

	/**
	 * Constructor convencional de la clase Nif
	 * @param texto
	 */
	public Nif(String texto) {
		setNif(texto);
	}

	/**
	 * Constructor por defecto de la clase
	 */
	public Nif() {
		this.texto = "00000001R";
	}

	/**
	 * Constructor de copia de la clase Nif
	 * @param nif
	 */
	public Nif(Nif nif) {
		this.texto = new String(nif.texto);
	}

	/**
	 * Metodo get que obtiene la cadena de texto que forma el DNI
	 * @return texto
	 */
	public String getnif() {
		return texto;
	}

	/**
	 * Metodo set que establece el NIF pasada la cadena de texto que lo contiene por parametro
	 * @param texto
	 */
	public void setNif(String texto) {
		assert texto != null;
		if (nifValido(texto)) {
			this.texto = texto;
		}
		
		if (this.texto == null) {
			this.texto = nifGenerado(texto);
		}
	}

	/**
	 * Metodo que valida si un NIF es valido o no
	 * @param texto
	 * @return true, si el nif es valido
	 */
	private boolean nifValido(String texto) {
		String numeros = texto.substring(0 ,8);
		String letrasDNI = "TRWAGMYFPDXBNJZSQVHLCKE";
		int suma = 0;
		int total = 0;
				
		for (int i = 0; i < numeros.length(); i ++) {
			suma += Character.getNumericValue(numeros.charAt(i));
		}
		
		total = suma % 23;
		
		return texto.substring(8,9).equals(String.valueOf(letrasDNI.charAt(total)));
	}
	
	/**
	 * Metodo que ante un nif que no tiene la letra que le corresponde genera otro con la letra correspondiente original
	 * @param texto
	 * @return nifGenerado
	 */
	private String nifGenerado(String texto) {
		String numeros = texto.substring(0 ,8);
		StringBuilder sb = new StringBuilder(numeros);
		String letrasDNI = "TRWAGMYFPDXBNJZSQVHLCKE";
		int suma = 0;
		int total = 0;
				
		for (int i = 0; i < numeros.length(); i ++) {
			suma += Character.getNumericValue(numeros.charAt(i));
		}
		
		total = suma % 23;
		sb.append(letrasDNI.charAt(total));
		return sb.toString();
	}

	/**
	 * Redefinicion del metodo hashCode de la clase object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		return result;
	}

	/**
	 * Redefinicion del metodo equals de la clase Object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nif other = (Nif) obj;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}

	/**
	 * Redefinicion del metodo toString de la clase Object
	 */
	@Override
	public String toString() {
		return texto;
	}

	/**
	 * Redefinicion del metodo clone de la clase object usando nuestro constructor copia
	 */
	@Override
	public Nif clone() {
		return new Nif(this);
	}

}
