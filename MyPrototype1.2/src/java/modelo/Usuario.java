package modelo;
/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Usuario según el modelo1.
 *  En esta versión sólo se ha aplicado un diseño OO básico.
 *  Se pueden detectar varios defectos y antipatrones de diseño:
 *  	- Obsesión por los tipos primitivos.
 *  	- Clase demasiado grande.
 *  	- Clase acaparadora, que delega poca responsabilidad.  
 *  @since: prototipo1.1
 *  @source: Usuario.java 
 *  @version: 1.1 - 2018/11/21 
 *  @author: Ramon Moñino
 */

import util.Fecha;

public class Usuario {

	private Nif nif;
	private String nombre;
	private String apellidos;
	private String idUsr;
	private DireccionPostal direccionPostal;
	private Correo correo;
	private Fecha fechaNacimiento;
	private Fecha fechaAlta;
	private ClaveAcceso claveAcceso;
	private RolUsuario rol;
	public enum RolUsuario { INVITADO, NORMAL, ADMINSTRADOR };

	/**
	 * Constructor convencional. Utiliza métodos set...()
	 * @param nif
	 * @param nombre
	 * @param apellidos
	 * @param domicilio
	 * @param correo
	 * @param fechaNacimiento
	 * @param fechaAlta
	 * @param claveAcceso
	 * @param rol
	 */
	public Usuario(Nif nif, String nombre, String apellidos, DireccionPostal direccionPostal, Correo correo,
			Fecha fechaNacimiento, Fecha fechaAlta, ClaveAcceso claveAcceso, RolUsuario rol) {
		setNif(nif);
		setNombre(nombre);
		setApellidos(apellidos);
		setIdUsr();
		setDireccionPostal(direccionPostal);
		setCorreo(correo);
		setFechaNacimiento(fechaNacimiento);
		setFechaAlta(fechaAlta);
		setClaveAcceso(claveAcceso);
		setRol(rol);
	}

	/**
	 * Constructor por defecto. Reenvía al constructor convencional.
	 */
	public Usuario() {
		this(new Nif(), "Nombre", "Apellido Apellido", new DireccionPostal(), new Correo(), new Fecha(), new Fecha(),
				new ClaveAcceso(), RolUsuario.NORMAL );
	}

	/**
	 * Constructor copia de la clase.
	 * @param usr , usuario a copiar
	 */
	public Usuario(Usuario usr) {
		this.nif = new Nif(usr.nif);
		this.nombre = new String(usr.nombre);
		this.apellidos = new String(usr.apellidos);
		this.direccionPostal = new DireccionPostal(usr.direccionPostal);
		this.correo = new Correo(usr.correo);
		this.fechaNacimiento = new Fecha(usr.fechaNacimiento.getYear(), usr.fechaNacimiento.getMonth(),
				usr.fechaNacimiento.getDay());
		this.fechaAlta = new Fecha(usr.fechaAlta.getYear(), usr.fechaAlta.getMonth(), usr.fechaAlta.getDay());
		this.claveAcceso = new ClaveAcceso(usr.claveAcceso);
		this.rol = usr.rol;
	}

	/**
	 * Metodo de get que obtiene el objeto nif de la clase Nif
	 * @return nif
	 */
	public Nif getNif() {
		return nif;
	}

	/**
	 * Metodo set que establece un objeto Nif dado por parametro
	 * @param nif
	 */
	public void setNif(Nif nif) {
		assert nif != null;
		this.nif = nif;
	}

	/**
	 * Metodo get que obtiene el nombre del usuario en forma de cadena de texto
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo set que establece un nombre de usuario dado por parametro
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		assert nombre != null;
		if (nombreValido(nombre)) {
			this.nombre = nombre;
		}

		if (this.nombre == null) { 
			this.nombre = new Usuario().nombre; 
		}
	}

	/**
	 * Metodo que comprueba la validez de un nombre.
	 * @param nombre.
	 * @return true si cumple.
	 */
	private boolean nombreValido(String nombre) {
		return nombre.matches("^([A-ZÁÉÍÓÚ][a-záéíóú]+)");
	}

	/**
	 * Metodo get que obtiene los apellidos en forma de cadena de texto
	 * @return apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Metodo set que establece los apellidos de un usuario dado por parametro
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		assert apellidos != null;
		if (apellidosValidos(apellidos)) {
			this.apellidos = apellidos;
		}

		if (this.apellidos == null) {
			this.apellidos = new Usuario().apellidos;
		}
	}

	/**
	 * Comprueba validez de los apellidos.
	 * @param apellidos.
	 * @return true si cumple.
	 */
	private boolean apellidosValidos(String apellidos) {
		return apellidos.matches("(^[A-Z][a-záéíóú]+)(\\s)([A-Z][a-záéíóú]+)");
	}
	
	/**
	 * Devuelve el id del usuario
	 * @return isUsr
	 */
	public String getIdUsr() {
		return idUsr;
	}
	
	/**
	 * Metodo que establece el idUsr
	 * @param idUsr
	 */
	public void setIdUsr() {
		this.idUsr = generarIdUsr();
	}
	
	/**
	 * Metodo que genera un ID de usuario
	 * @param idUsr
	 * @return id
	 */
	public String generarIdUsr() {
		StringBuilder id = new StringBuilder();
		id.append(this.nombre.substring(0, 1).toUpperCase());
		String[]divApellidos = this.apellidos.split("\\s+");
		id.append(divApellidos[0].substring(0, 1).toUpperCase());
		id.append(divApellidos[1].substring(0, 1).toUpperCase());
		id.append(this.nif.getnif().substring(7, 9));
		return id.toString();
	}

	/**
	 * Metodo get que obtiene el objeto direccionpostal
	 * @return direccionPostal
	 */
	public DireccionPostal getDireccionPostal() {
		return direccionPostal;
	}

	/**
	 * Metodo set que establece la direccion postal de un usuario dada por parametro
	 * @param direccionPostal
	 */
	public void setDireccionPostal(DireccionPostal direccionPostal) {
		assert direccionPostal != null;
		this.direccionPostal = direccionPostal;

	}

	/**
	 * Metodo get que obtiene el Objeto correo
	 * @return correo
	 */
	public Correo getCorreo() {
		return correo;
	}

	/**
	 * Metodo set que establece el correo de un usuario que se pasado por parametro
	 * @param correo
	 */
	public void setCorreo(Correo correo) {
		assert correo != null;
		this.correo = correo;
	}

	/**
	 * Metodo set que obtiene el objeto fechadenacimiento de un usuario
	 * @return fechaNacimiento
	 */
	public Fecha getFechaNacimiento() {
		return fechaNacimiento;

	}

	/**
	 * Metodo set que establece la fecha de nacimiento de un usuario dada por parametro.
	 * @param fechaNacimiento
	 */
	public void setFechaNacimiento(Fecha fechaNacimiento) {
		assert fechaNacimiento != null;
		if (fechaNacimientoValida(fechaNacimiento)) {
			this.fechaNacimiento = fechaNacimiento;
		}
		if (this.fechaNacimiento == null) {
			this.fechaNacimiento = new Usuario().fechaNacimiento;
		}
	}

	/**
	 * Metodo que comprueba si una fecha de nacimiento es valida
	 * @param fechaNacimiento
	 * @return true si es valida
	 */
	private boolean fechaNacimientoValida(Fecha fechaNacimiento) {
		return !fechaNacimiento.after(new Fecha());
	}

	/**
	 * Metodo get que obtiene el objeto fecha de alta
	 * @return fechaAlta
	 */
	public Fecha getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Metodo set que establece un objeto fecha de alta dado por parametro
	 * @param fechaAlta
	 */
	public void setFechaAlta(Fecha fechaAlta) {
		assert fechaAlta != null;
		if (fechaAltaValida(fechaAlta)) {
			this.fechaAlta = fechaAlta;
		}
		if (this.fechaAlta == null) {
			this.fechaAlta = new Usuario().fechaAlta;
		}
	}

	/**
	 * Metodo que comprueba si una fecha de alta es valida o no
	 * @param fechaAlta
	 * @return true, si la fecha de alta es valida
	 */
	private boolean fechaAltaValida(Fecha fechaAlta) {
		return !fechaAlta.after(new Fecha());
	}

	/**
	 * Metodo get que obtiene el objeto clave de acceso que se le asigna al usuario
	 * @return claveAcceso
	 */
	public ClaveAcceso getClaveAcceso() {
		return claveAcceso;
	}

	/**
	 * Metodo set que establece la clave de acceso pasada por parametro
	 * @param claveAcceso
	 */
	public void setClaveAcceso(ClaveAcceso claveAcceso) {
		assert claveAcceso != null;
		this.claveAcceso = claveAcceso;
	}

	/**
	 * Metodo get que obtiene el rol del usuario
	 * @return rol del usuario dentro de nuestra aplicacion
	 */
	public RolUsuario getRol() {
		return rol;
	}

	/**
	 * Metodo set que establece el rol de nuestro usuario pasado por parametro
	 * @param rol
	 */
	public void setRol(RolUsuario rol) {
		assert rol != null;
			this.rol = rol;
	}

	/**
	 * Redefinicion del metodo toString.
	 * @return String con los valores de los atributos de la clase
	 */
	@Override
	public String toString() {
		return String.format(
				"%-16s %s\n" +
				"%-16s %s\n" + 
				"%-16s %s\n" + 
				"%-16s %s\n" + 
				"%-16s %s\n" + 
				"%-16s %s\n" + 
				"%-16s %s\n" + 
				"%-16s %s\n" +
				"%-16s %s\n" + 
				"%-16s %s\n",
				"nif:", nif, 
				"nombre:", this.nombre, 
				"apellidos:", this.apellidos,
				"idUsr:", this.idUsr, 
				"domicilio:", this.direccionPostal,
				"correo:", this.correo, 
				"fechaNacimiento:",
				this.fechaNacimiento.getYear() + "." + this.fechaNacimiento.getMonth() + "."
						+ this.fechaNacimiento.getDay(),
						
				"fechaAlta:",
				this.fechaAlta.getYear() + "." + this.fechaAlta.getMonth() + "." + this.fechaAlta.getDay(),
				"claveAcceso:", this.claveAcceso, "rol:", this.rol);
	}

} // class
