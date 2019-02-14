/** Proyecto: Juego de la vida.
 *  Implementa el concepto de DireccionPostal del modelo  
 *  @since: prototipo1.1
 *  @source: DireccionPostal.java 
 *  @version: 1.1 - 2019/01/22 
 *  @author: Ramon Moñino
 */
package modelo;

public class DireccionPostal {
	/**
	 * Atributos que van a formar nuestra direccion.
	 */
	private String calle;
	private String numero;
	private String CP;
	private String poblacion;

	/**
	 * Constructor convencional de la clase que usa los metodos set.
	 * @param calle
	 * @param numero
	 * @param CP
	 * @param poblacion
	 */
	public DireccionPostal(String calle, String numero, String CP, String poblacion) {
		setCalle(calle);
		setNumero(numero);
		setCP(CP);
		setPoblacion(poblacion);
	}

	/**
	 * Constructor por defecto de la clase.
	 */
	public DireccionPostal() {
		this("calle", "15", "30157", "poblacion");
	}

	/**
	 * Constructor copia de la clase.
	 * @param direccionpostal
	 */
	public DireccionPostal(DireccionPostal direccionpostal) {
		this.calle = new String(direccionpostal.calle);
		this.numero = new String(direccionpostal.numero);
		this.CP = new String(direccionpostal.CP);
		this.poblacion = new String(direccionpostal.poblacion);
	}

	/**
	 * Metodo get que obtiene la cadena de caracteres que forma la calle.
	 * @return calle
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * Metodo set que establece la calle de la direccion postal
	 * @param calle
	 */
	public void setCalle(String calle) {
		assert calle != null;
		if (calleValida(calle)) {
			this.calle = calle;
		}
		if (this.calle == null) {
			this.calle = new DireccionPostal().calle;
		}

	}

	/**
	 * Metodo que comprueba si nuestra calle es valida.
	 * @param calle
	 * @return true, si es una calle valida.
	 */
	private boolean calleValida(String calle) {
		return calle.matches("\\w+|\\/");
	}

	/**
	 * Metodo get que obtiene el numero que forma la direccion postal.
	 * @return numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Metodo set que establece el numero de la direccion postal
	 * @param numero
	 */
	public void setNumero(String numero) {
		assert numero != null;
		if (numeroValido(numero)) {
			this.numero = numero;
		}
		if (this.numero == null) {
			this.numero = new DireccionPostal().numero;
		}
	}

	/**
	 * Metodo que comprueba si un numero es valido o no.
	 * @param numero
	 * @return true, si el numero es valido
	 */
	private boolean numeroValido(String numero) {
		return numero.matches("\\d{1,3}");
	}

	/**
	 * Metodo get que obtiene la cadena de caracteres CP que forma el codigo postal la direccion postal.
	 * @return CP
	 */
	public String getCP() {
		return CP;
	}

	/**
	 * Metodo set que establece el codigo postal de la direccion postal.
	 * @param CP
	 */
	public void setCP(String CP) {
		assert CP != null;
		if (cpValido(CP)) {
			this.CP = CP;
		}
		if (this.CP == null) {
			this.CP = new DireccionPostal().CP;
		}
	}

	/**
	 * Metodo que comprueba si el CP es valido o no.
	 * @param CP
	 * @return true, si el CP es valido.
	 */
	private boolean cpValido(String CP) {
		return CP.matches("\\d{5}");
	}

	/**
	 * Metodo get que obtiene la cadena de caracteres poblacion que forma la direccion postal.
	 * @return poblacion
	 */ 
	public String getPoblacion() {
		return poblacion;
	}

	/**
	 * Metodo set que establece la poblacion de la direccion postal.
	 * @param poblacion
	 */
	public void setPoblacion(String poblacion) {
		assert poblacion != null;
		if (poblacionValido(poblacion)) {
			this.poblacion = poblacion;
		}
		if (this.poblacion == null) {
			this.poblacion = new DireccionPostal().poblacion;
		}

	}

	/**
	 * Metodo que comprueba si una poblacion es valida o no.
	 * @param poblacion
	 * @return true, si la poblacion es valida
	 */
	private boolean poblacionValido(String poblacion) {
		return !poblacion.matches("[ ]+");
	}

	/**
	 * Redefinicion del metodo hashCode de la clase Object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CP == null) ? 0 : CP.hashCode());
		result = prime * result + ((calle == null) ? 0 : calle.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((poblacion == null) ? 0 : poblacion.hashCode());
		return result;
	}

	/**
	 * Redefinicion del metodo equals de la clase Object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DireccionPostal other = (DireccionPostal) obj;
		if (CP == null) {
			if (other.CP != null)
				return false;
		} else if (!CP.equals(other.CP))
			return false;
		if (calle == null) {
			if (other.calle != null)
				return false;
		} else if (!calle.equals(other.calle))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (poblacion == null) {
			if (other.poblacion != null)
				return false;
		} else if (!poblacion.equals(other.poblacion))
			return false;
		return true;
	}

	/**
	 * Redefinicion del metodo toString de la clase Object que da formato a los atributos de la clase DireccionPostal.
	 */
	@Override
	public String toString() {
		return calle + ", " + numero + ", " + CP + ", " + poblacion;
	}

	/**
	 * Redefinicion del metodo clone de la clase Object que utiliza el constructor de copia.
	 */
	@Override
	public DireccionPostal clone() {
		return new DireccionPostal(this);
	}
}
