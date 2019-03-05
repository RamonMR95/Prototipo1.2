/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Datos del accesoDato  
 *  @since: prototipo1.1
 *  @source: Datos.java 
 *  @version: 1.2 - 2019/01/22 
 *  @author: Ramon Moñino
 */

package accesoDato;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.*;
import modelo.Usuario.RolUsuario;
import util.Fecha;

public class Datos {
	/**
	 * Atributos del almacen de datos del prototipo.
	 */
	private static ArrayList<Usuario> datosUsuarios = new ArrayList<Usuario>();
	private static HashMap<String, String> mapaEquivalencias = new HashMap<String, String>();
	private static ArrayList<SesionUsuario> datosSesiones = new ArrayList<SesionUsuario>();
	private static ArrayList<Simulacion> datosSimulaciones = new ArrayList<Simulacion>();
	private static ArrayList<Mundo> datosMundos = new ArrayList<Mundo>();

	// USUARIO
	
	/**
	 * @param clave Key del mapa de equivalencias entre NIF, correo e idUsr
	 * @return El valor que le corresponde a dicha clave
	 */
	public String getEquivalenciaId(String clave) {
		return mapaEquivalencias.get(clave);
	}

	/**
	 * Metodo get que obtiene el número de usuarios registrados.
	 * @return Numero de Usuarios
	 */
	public int getUsuariosRegistrados() {
		return datosUsuarios.size();
	}

	/**
	 * Metodo que muestra todos los Usuarios.
	 */
	public void mostrarTodosUsuarios() {
		for (Usuario usr : datosUsuarios) {
			System.out.println("\n" + usr);
		}
	}

	private void registrarEquivalenciasId(Usuario usr) {
		mapaEquivalencias.put(usr.getNif().getNifTexto(), usr.getIdUsr());
		mapaEquivalencias.put(usr.getCorreo().getCorreoTexto(), usr.getIdUsr());
		mapaEquivalencias.put(usr.getIdUsr(), usr.getIdUsr());
	}

	/**
	 * Busca usuario dado su nif.
	 * @param idUsr - el nif del Usuario a buscar.
	 * @return - el Usuario encontrado o null si no existe.
	 */
	public int indexSortUsuario(String idUsr) {
		int size = datosUsuarios.size();
		int puntoMedio;
		int limiteInferior = 0;
		int limiteSuperior = size - 1;

		while (limiteInferior <= limiteSuperior) {
			puntoMedio = (limiteSuperior + limiteInferior) / 2;
			int comparacion = idUsr.compareTo(datosUsuarios.get(puntoMedio).getIdUsr());

			if (comparacion == 0) {
				return puntoMedio + 1;
			}

			if (comparacion > 0) {
				limiteInferior = puntoMedio + 1;

			} else {
				limiteSuperior = puntoMedio - 1;
			}

		}
		return -(limiteInferior + 1);
	}

	/**
	 * 
	 * @param idUsr
	 * @return
	 */
	public Usuario buscarUsuario(String idUsr) {
		assert idUsr != null;
		idUsr = mapaEquivalencias.get(idUsr);

		if (idUsr != null) {
			int indice = indexSortUsuario(idUsr) - 1;

			if (indice >= 0) {
				return datosUsuarios.get(indexSortUsuario(idUsr) - 1);
			}
		}
		return null;

	}

	/**
	 * Metodo que registra el usuario en el almacen de usuarios
	 * @param usr
	 * @throws Exception 
	 */
	public void altaUsuario(Usuario usr) throws Exception {
		assert usr != null;
		int posicionInsercion = indexSortUsuario(usr.getIdUsr());

		if (posicionInsercion < 0) {
			datosUsuarios.add(-posicionInsercion - 1, usr);
			registrarEquivalenciasId(usr);

		} else {

			if (!datosUsuarios.get(posicionInsercion - 1).equals(usr)) {
				int intentos = "BCDEFGHIJKLMNOPQRSTUVWXYZA".length() - 1;

				do {
					/* Coincidencia de ig generar variante */
					posicionInsercion = indexSortUsuario(usr.getIdUsr());
					usr = new Usuario(usr, usr.getIdUsr());
					datosUsuarios.add(-posicionInsercion, usr);
					registrarEquivalenciasId(usr);
					intentos--;

				} while (intentos > 0 && posicionInsercion < 0);

				if (intentos == 0) {
					throw new Exception("Error imposible generar variante");
				}

			} else {
				throw new Exception("Error usr repetido");
			}

		}
	}

	/**
	 * Metodo que realiza una carga los Usuarios de prueba que se van a almacenar en
	 * nuestro programa.
	 */
	public void cargarUsuariosPrueba() {
		for (int i = 0; i < 10; i++) {
			try {
				try {
					altaUsuario(new Usuario(new Nif("0000000" + i + "K"), "Pepe", "López Pérez",
							new DireccionPostal("C/ Luna", "2" + i, "3013" + i, "Murcia"),
							new Correo("pepe" + i + "@gmail.com"), new Fecha(1999, 11, 12), new Fecha(2018, 01, 03),
							new ClaveAcceso("Miau#" + i), RolUsuario.NORMAL));

				} catch (ModeloException e) {
					
				}

			} catch (Exception e) {

			}

		}
	}

	// SESIONES
	
	/**
	 * Metodo get que obtiene el numero de sesiones registradas.
	 * @return Numero de sesionesRegistradas
	 */
	public int getSesionesRegistradas() {
		return datosSesiones.size();
	}

	/**
	 * Metodo que registra la sesion en el almacen de sesiones del programa.
	 * @param sesion
	 */
	public void altaSesion(SesionUsuario sesion) {
		datosSesiones.add(sesion);

	}

	
	// SIMULACIONES
	
	/**
	 * 
	 * @param idSimulacion
	 * @return
	 */
	public Simulacion buscarSimulacion(String idSimulacion) {
		for (Simulacion simulacion : datosSimulaciones) {
			if (simulacion != null && simulacion.getIdSimulacion().equalsIgnoreCase(idSimulacion)) {
				return simulacion;
			}
		}
		return null;
	}
	
	/**
	 * Metodo que registra la simulacion en el almacen de simulaciones
	 * @param simulacion
	 */
	public void altaSimulacion(Simulacion simulacion) {
		datosSimulaciones.add(simulacion);
	}

	/**
	 * Metodo get que obtiene el numero de simulaciones registradas.
	 * @return Numero de simulaciones
	 */
	public int getSimulacionesRegistradas() {
		return datosSimulaciones.size();
	}

	/**
	 * Metodo que realiza un volcado con los datos de los usuarios.
	 * @return String volcadoUsuarios
	 */
	public static String volcarDatosUsuariosTexto() {
		StringBuilder sb = new StringBuilder();
		String delimitadorUsrApertura = "<usr>";
		String delimitadorUsrCierre = "</usr>";
		String delimitadorAtribUsrApertura = "<attrib>";
		String delimitadorAtribUsrCierre = "</attrib>";

		for (Usuario usr : datosUsuarios) {
			sb.append(delimitadorUsrApertura);
			sb.append(delimitadorAtribUsrApertura).append(usr.getNif().getNifTexto()).append(delimitadorAtribUsrCierre);
			sb.append(delimitadorAtribUsrApertura).append(usr.getNombre()).append(delimitadorAtribUsrCierre);
			sb.append(delimitadorAtribUsrApertura).append(usr.getApellidos()).append(delimitadorAtribUsrCierre);
			sb.append(delimitadorAtribUsrApertura).append(usr.getDireccionPostal().toString()).append(delimitadorAtribUsrCierre);
			sb.append(delimitadorAtribUsrApertura).append(usr.getCorreo().getCorreoTexto()).append(delimitadorAtribUsrCierre);
			sb.append(delimitadorAtribUsrApertura).append(usr.getFechaNacimiento().toString()).append(delimitadorAtribUsrCierre);
			sb.append(delimitadorAtribUsrApertura).append(usr.getFechaAlta().toString()).append(delimitadorAtribUsrCierre);
			sb.append(delimitadorAtribUsrApertura).append(usr.getClaveAcceso().getTexto()).append(delimitadorAtribUsrCierre);
			sb.append(delimitadorAtribUsrApertura).append(usr.getRol()).append(delimitadorAtribUsrCierre);
			sb.append(delimitadorUsrCierre);

		}
		return sb.toString();
	}

	/**
	 * Metodo que realiza un volcado de los datos de las sesiones de texto.
	 * @return String volcadoSesiones
	 */
	public static String volcarDatosSesionesTexto() {
		StringBuilder sb = new StringBuilder();
		String delimitadorSesionApertura = "<sesion>";
		String delimitadorSesionCierre = "</sesion>";

		for (SesionUsuario sesionUsr : datosSesiones) {
			if (sesionUsr != null) {
				sb.append(delimitadorSesionApertura);
				sb.append(sesionUsr.getIdSesion());
				sb.append(delimitadorSesionCierre);
			}
			break;

		}
		return sb.toString();
	}
	
	// MUNDO
	
	/**
	 * Metodo get que obtiene el numero de mundos registrados
	 * que coincide con el tamaño del arraylist datosMundos
	 * @return Numero de mundos registrados
	 */
	public int getMundosRegistrados() {
		return datosMundos.size();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Mundo buscarMundo(String id) {
		for (Mundo mundo : datosMundos) {
			if (mundo != null && mundo.getId().equalsIgnoreCase(id)) {
				return mundo;
			}
		}
		return null;
	}
	/**
	 * Busca usuario dado su nif.
	 * @param idUsr - el nif del Usuario a buscar.
	 * @return - el Usuario encontrado o null si no existe.
	 */
	public int indexSortMundo(String idUsr) {
		int size = datosMundos.size();
		int puntoMedio;
		int limiteInferior = 0;
		int limiteSuperior = size - 1;

		while (limiteInferior <= limiteSuperior) {
			puntoMedio = (limiteSuperior + limiteInferior) / 2;
			int comparacion = idUsr.compareTo(datosMundos.get(puntoMedio).getId());

			if (comparacion == 0) {
				return puntoMedio + 1;
			}

			if (comparacion > 0) {
				limiteInferior = puntoMedio + 1;

			} else {
				limiteSuperior = puntoMedio - 1;
			}

		}
		return -(limiteInferior + 1);
	}

	/**
	 * Metodo que inserta en el arraylist de mundos de forma binaria un nuevo mundo
	 * @param mundo - mundo que va a ser dado de alta
	 * @throws Exception
	 */
	public void altaMundo(Mundo mundo) throws Exception {
		assert mundo != null;
		int posicionInsercion = indexSortMundo(mundo.getId());

		if (posicionInsercion < 0) {
			datosMundos.add(-posicionInsercion - 1, mundo);

		} else {
			throw new Exception("Error: nombre repetido");
			
		}

	}

	/**
	 * Carga datos demo en la matriz que representa el mundo.
	 * @throws Exception 
	 */
	public void cargarMundoDemo() throws Exception {
		Mundo mundo = new Mundo();
		mundo.setEspacio(new byte[][]
			  { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } });
		
		altaMundo(mundo);
	}

} // Class
