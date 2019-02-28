
/** 
Proyecto: Juego de la vida.
 * Implementa el control de inicio de sesión y ejecución de la simulación por defecto. 
 * En esta versión sólo se ha aplicado un diseño OO básico.
 *  Se pueden detectar varios defectos y antipatrones de diseño:
 *  	- Obsesión por los tipos primitivos.
 *      - Exceso de métodos estáticos.
 *  	- Clase acaparadora, que delega poca responsabilidad. 
 *  	- Clase demasiado grande.
 * @since: prototipo1.2
 * @source: JVPrincipal.java 
 * @version: 1.2 - 2019-02-01
 * @author: Ramon Moñino
 */

import accesoDato.Datos;
import accesoUsr.Presentacion;
import modelo.SesionUsuario;
import util.Fecha;

public class JVPrincipal {

	/**
	 * Secuencia principal del programa.
	 */
	static Datos datos;
	static Presentacion interfazUsr;

	public static void main(String[] args) {
		datos = new Datos();
		interfazUsr = new Presentacion();

		datos.cargarUsuariosPrueba();
		datos.mostrarTodosUsuarios();

		try {
			datos.cargarMundoDemo();

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (interfazUsr.inicioSesionCorrecto()) {

			SesionUsuario sesion = new SesionUsuario();
			sesion.setUsr(interfazUsr.getUsrEnSesion());
			sesion.setFecha(new Fecha());
			datos.altaSesion(sesion);

			interfazUsr.getSimulacion().setMundo(datos.buscarMundo("Demo1"));
			datos.altaSimulacion(interfazUsr.getSimulacion());

			System.out.println("Sesión: " + datos.getSesionesRegistradas() + '\n' + "Iniciada por: "
					+ interfazUsr.getUsrEnSesion().getNombre() + " " + interfazUsr.getUsrEnSesion().getApellidos());

			interfazUsr.mostrarSimulacion();

		} else {
			System.out.println("\nDemasiados intentos fallidos...");

		}
		System.out.println("Fin del programa.");
	}

} // class
