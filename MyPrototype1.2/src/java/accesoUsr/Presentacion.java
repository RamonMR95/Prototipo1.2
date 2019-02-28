/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Presentacion del accesoUsr  
 *  @since: prototipo1.2
 *  @source: Presentacion.java 
 *  @version: 1.2 - 2019/01/22 
 *  @author: Ramon Moñino
 */
package accesoUsr;

import java.util.Scanner;

import accesoDato.Datos;
import modelo.ClaveAcceso;
import modelo.Simulacion;
import modelo.Usuario;

public class Presentacion {
	/**
	 * Atributos de la clase.
	 */
	private static final int MAX_INTENTOS_FALLIDOS = 3;
	private static Usuario usrEnSesion;
	private Datos datos;
	private Simulacion simulacion;

	public Presentacion() {
		usrEnSesion = null;
		datos = new Datos();
		simulacion = new Simulacion();
	}
	
	/**
	 * Metodo get que obtiene el usuario que se encuentra en sesion.
	 * @return usrEnSesion
	 */
	public Usuario getUsrEnSesion() {
		return usrEnSesion;
	}

	/**
	 * 
	 * @return
	 */
	public Simulacion getSimulacion() {
		return this.simulacion;
	}
	
	/**
	 * Controla el acceso de usuario.
	 * @return true si la sesión se inicia correctamente.
	 */
	public boolean inicioSesionCorrecto() {
		Scanner teclado = new Scanner(System.in); // Entrada por consola.
		int intentosPermitidos = MAX_INTENTOS_FALLIDOS;

		do {
			System.out.print("Introduce el ID de usuario: \n");
			String id = teclado.nextLine();
			System.out.print("Introduce clave acceso: ");
			ClaveAcceso clave = new ClaveAcceso(teclado.nextLine());

			usrEnSesion = datos.buscarUsuario(datos.getEquivalenciaId(id));

			Usuario aux = new Usuario();
			aux.setClaveAcceso(new ClaveAcceso(clave));
			clave = aux.getClaveAcceso();
			
			if (usrEnSesion != null && usrEnSesion.getClaveAcceso().equals(clave)) {
				simulacion.setUsr(usrEnSesion);
				return true;
				
			} else {
				intentosPermitidos--;
				System.out.print("Credenciales incorrectas: ");
				System.out.println("Quedan " + intentosPermitidos + " intentos... ");
			}
			
		} while (intentosPermitidos > 0);

		return false;
	}


	/**
	 * Ejecuta una simulación del juego de la vida en la consola.
	 */
	public void mostrarSimulacion() {
		
		int generacion = 0;
		
		do {
			System.out.println("\nGeneración: " + generacion);
			simulacion.getMundo().actualizarMundo();
			generacion++;
			System.out.println(simulacion.getMundo().toStringEstadoMundo());
			
		} while (generacion < simulacion.getCiclosSimulacion());
	}

}	// Class
