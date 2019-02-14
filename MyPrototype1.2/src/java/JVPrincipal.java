
/** 
Proyecto: Juego de la vida.
 * Implementa el control de inicio de sesión y ejecución de la simulación por defecto. 
 * En esta versión sólo se ha aplicado un diseño OO básico.
 *  Se pueden detectar varios defectos y antipatrones de diseño:
 *  	- Obsesión por los tipos primitivos.
 *      - Exceso de métodos estáticos.
 *  	- Clase acaparadora, que delega poca responsabilidad. 
 *  	- Clase demasiado grande.
 * @since: prototipo1.1
 * @source: JVPrincipal.java 
 * @version: 1.1 - 2019-02-01
 * @author: Ramon Moñino
 */

import accesoDato.Datos;
import accesoUsr.Presentacion;
import modelo.SesionUsuario;
import modelo.Simulacion;
import util.Fecha;

public class JVPrincipal {

	/**
	 * Secuencia principal del programa.
	 */
	static Datos datos = new Datos();
	static Presentacion interfazUsr = new Presentacion();
	
	public static void main(String[] args) {
		
		datos.cargarUsuariosPrueba();
		datos.mostrarTodosUsuarios();

		if (interfazUsr.inicioSesionCorrecto()) {

			SesionUsuario sesion = new SesionUsuario();
			sesion.setUsr(interfazUsr .getUsrEnSesion());
			sesion.setFecha(new Fecha());
			
			datos.altaSesion(sesion);
			System.out.println("Sesión: " + datos.getSesionesRegistradas() + '\n' + "Iniciada por: " + interfazUsr.getUsrEnSesion().getNombre()
					+ " " + interfazUsr.getUsrEnSesion().getApellidos());
			
			new Simulacion().lanzarDemo();
		} else {
			System.out.println("\nDemasiados intentos fallidos...");
		}
		System.out.println("Fin del programa.");
	}


} // class
