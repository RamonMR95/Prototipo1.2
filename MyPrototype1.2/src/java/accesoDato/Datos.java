/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Datos del accesoDato  
 *  @since: prototipo1.0
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
	private static ArrayList<SesionUsuario> datosSesiones = new ArrayList<SesionUsuario>();
	private static ArrayList<Simulacion> datosSimulaciones = new ArrayList<Simulacion>();
	private static HashMap<String, String> mapaEquivalencias = new HashMap<String, String>(); 
	// private static ArrayList<Mundo> datosMundo = new ArrayList<Mundo>();
	
	
	/**
	 * 
	 * @param clave
	 * @return
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
	 * Metodo get que obtiene el numero de sesiones registradas.
	 * @return Numero de sesionesRegistradas
	 */
	public int getSesionesRegistradas() {
		return datosSesiones.size();
	}

	/**
	 * Metodo get que obtiene el numero de simulaciones registradas.
	 * @return Numero de simulaciones
	 */
	public int getSimulacionesRegistradas() {
		return datosSimulaciones.size();
	}

	/**
	 * Metodo que muestra todos los Usuarios.
	 */
	public void mostrarTodosUsuarios() {
		for (Usuario usr : datosUsuarios) {
			System.out.println("\n" + usr);
		}
	}

	/**
	 * Busca usuario dado su nif.
	 * @param idUsr - el nif del Usuario a buscar.
	 * @return - el Usuario encontrado o null si no existe.
	 */
	public int indexSort(String idUsr) {
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
		int indice = indexSort(idUsr) - 1;
		
		if (indice < 0) {
			mapaEquivalencias.get(idUsr);
		}
		return datosUsuarios.get(indexSort(idUsr) - 1); // indexSort() es base 1
	}
	
	/**
	 * Metodo que registra la sesion en el almacen de sesiones del programa.
	 * @param sesion
	 */
	public void altaSesion(SesionUsuario sesion) {
		datosSesiones.add(sesion);
		
	}
	
	
	/**
	 * Metodo que registra el usuario en el almacen de usuarios
	 * @param usr
	 * @throws Exception 
	 */
	public void altaUsuario(Usuario usr) throws Exception {
		assert usr != null;
		int posicionInsercion = indexSort(usr.getIdUsr());

		if (posicionInsercion < 0) {
			datosUsuarios.add(-posicionInsercion - 1, usr);
			registrarEquivalenciasId(usr);

		} else {

			if (!datosUsuarios.get(posicionInsercion - 1).equals(usr)) {
				int intentos = "BCDEFGHIJKLMNOPQRSTUVWXYZA".length() - 1;

				do {
					/* Coincidencia de ig generar variante */
					posicionInsercion = indexSort(usr.getIdUsr());
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
	 * Metodo que registra la simulacion en el almacen de simulaciones
	 * @param simulacion
	 */
	public void altaSimulacion(Simulacion simulacion) {
		datosSimulaciones.add(simulacion);
	}

	/**
	 * Metodo que realiza una carga los Usuarios de prueba que se van a almacenar en
	 * nuestro programa.
	 */
	public void cargarUsuariosPrueba() {
		for (int i = 0; i < 10; i++) {
			try {
				altaUsuario(new Usuario(new Nif("0000000" + i + "K"), "Pepe", "López Pérez",
						new DireccionPostal("C/ Luna", "2" + i, "3013" + i, "Murcia"),
						new Correo("pepe" + i + "@gmail.com"), new Fecha(1999, 11, 12), new Fecha(2018, 01, 03),
						new ClaveAcceso("Miau#" + i), RolUsuario.NORMAL));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
	
	private void registrarEquivalenciasId(Usuario usr) {
		mapaEquivalencias.put(usr.getNif().getNifTexto(), usr.getIdUsr());
		mapaEquivalencias.put(usr.getCorreo().getCorreoTexto(), usr.getIdUsr());
		mapaEquivalencias.put(usr.getIdUsr(), usr.getIdUsr());
	}
}	// Class
