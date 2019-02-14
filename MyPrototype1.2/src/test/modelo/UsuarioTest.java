package modelo;
/** 
 * Proyecto: Juego de la vida.
 * Clase JUnit5 de prueba automatizada de las características de la clase Usuario según el modelo1.
 * @since: prototipo1.1
 * @source: TestUsuario.java 
 * @version: 1.1 - 2019.01.29
 * @author: Ramon Moñino 
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Usuario.RolUsuario;
import util.Fecha;

public class UsuarioTest {
	private static Usuario usuario1; 
	private Usuario usuario2; 

	/**
	 * Método que se ejecuta antes de cada @Test para preparar datos de prueba.
	 * @throws DatosException 
	 */
	@BeforeAll
	public static void iniciarlizarDatosFijos() {
		// Objetos no modicados en las pruebas.
		usuario1 = new Usuario(new Nif(), 
				"Luis", "Roca Mora",
				new DireccionPostal("Roncal", "10", "30130", "Murcia"), 
				new Correo("luis@gmail.com"), 
				new Fecha(2000, 03, 21),
				new Fecha(2018,10,17), 
				new ClaveAcceso(), 
				RolUsuario.NORMAL);
		}

	/**
	 * Método que se ejecuta una sola vez al final del conjunto pruebas.
	 * No es necesario en este caso.
	 */
	@AfterAll
	public static void limpiarDatosFijos() {
		usuario1 = null;
	}

	/**
	 * Método que se ejecuta antes de cada pruebas.
	 */
	@BeforeEach
	public void iniciarlizarDatosVariables() {	
		usuario2 = new Usuario();
	}

	/**
	 * Método que se ejecuta después de cada @Test para limpiar datos de prueba.
	 */
	@AfterEach
	public void borrarDatosPrueba() {
		usuario2 = null;
	}

	// Test's CON DATOS VALIDOS
	
	@Test
	public void testUsuarioConvencional() {	
		assertEquals(usuario1.getNif(), new Nif("00000001R"));
		assertEquals(usuario1.getNombre(), "Luis");
		assertEquals(usuario1.getApellidos(), "Roca Mora");
		assertEquals(usuario1.getDireccionPostal(), new DireccionPostal("Roncal", "10", "30130", "Murcia"));
		assertEquals(usuario1.getCorreo(), new Correo("luis@gmail.com"));
		assertEquals(usuario1.getFechaNacimiento(), new Fecha(2000, 03, 21));
		assertEquals(usuario1.getFechaAlta(), new Fecha(2018,10,17));
		assertEquals(usuario1.getClaveAcceso(), new ClaveAcceso("Miau#0"));
		assertEquals(usuario1.getRol(), RolUsuario.NORMAL);
	}

	@Test
	public void testUsuarioDefecto() {
		assertEquals(usuario2.getNif(), new Nif("00000001R"));
		assertEquals(usuario2.getNombre(), "Nombre");
		assertEquals(usuario2.getApellidos(), "Apellido Apellido");
		assertEquals(usuario2.getDireccionPostal(),new DireccionPostal("calle", "numero", "CP", "poblacion"));
		assertEquals(usuario2.getCorreo(), new Correo("correo@correo.es"));
		assertEquals(usuario2.getFechaNacimiento().getYear(),new Fecha().getYear());
		assertEquals(usuario2.getFechaNacimiento().getMonth(), new Fecha().getMonth());
		assertEquals(usuario2.getFechaNacimiento().getDay(), new Fecha().getDay());
		assertEquals(usuario2.getFechaAlta().getYear(), new Fecha().getYear());
		assertEquals(usuario2.getFechaAlta().getMonth(), new Fecha().getMonth());
		assertEquals(usuario2.getFechaAlta().getDay(), new Fecha().getDay());
		assertEquals(usuario2.getClaveAcceso(), new ClaveAcceso("Miau#0"));
		assertEquals(usuario2.getRol(), RolUsuario.NORMAL);
	}

	@Test
	public void testUsuarioCopia() {
		Usuario usuario = new Usuario(usuario1);
		assertNotSame(usuario, usuario1);
		assertNotSame(usuario.getNif(), usuario1.getNif());
		assertNotSame(usuario.getNombre(), usuario1.getNombre());
		assertNotSame(usuario.getApellidos(), usuario1.getApellidos());
		assertNotSame(usuario.getDireccionPostal(), usuario1.getDireccionPostal());
		assertNotSame(usuario.getCorreo(), usuario1.getCorreo());
		assertNotSame(usuario.getFechaNacimiento(), usuario1.getFechaNacimiento());
		assertNotSame(usuario.getFechaAlta(), usuario1.getFechaAlta());
		assertNotSame(usuario.getClaveAcceso(), usuario1.getClaveAcceso());
		assertSame(usuario.getRol(), RolUsuario.NORMAL);
	}
	
	@Test
	public void testSetNombre() {
		usuario2.setNombre("Luis");
		assertEquals(usuario2.getNombre(), "Luis");
	}
	
	@Test
	public void testSetApellidos() {
		usuario2.setApellidos("Roca Mora");
		assertEquals(usuario2.getApellidos(), "Roca Mora");
	}
	
	@Test
	public void testSetDomicilio() {
		DireccionPostal direccionLocal = new DireccionPostal("Roncal", "10", "30130", "Murcia");
		usuario2.setDireccionPostal(direccionLocal);
		assertEquals(usuario2.getDireccionPostal(),direccionLocal);
	}
	
	@Test
	public void testSetCorreo() {
		Correo correoLocal = new Correo("luis@gmail.com");
		usuario2.setCorreo(correoLocal);
		assertEquals(usuario2.getCorreo(),correoLocal);
	}
	@Test
	public void testSetFechaNacimiento() {
		usuario2.setFechaNacimiento(new Fecha(2000, 3, 21));
		assertEquals(usuario2.getFechaNacimiento(), new Fecha(2000, 3, 21));
	}
	
	@Test
	public void testSetFechaAlta() {
		usuario2.setFechaAlta(new Fecha(2017,9,17));
		assertEquals(usuario2.getFechaAlta(), new Fecha(2017,9,17));
	}

	@Test
	public void testSetClaveAcceso() {
		ClaveAcceso claveLocal = new ClaveAcceso("Miau#12");
		usuario2.setClaveAcceso(claveLocal);
		assertEquals(usuario2.getClaveAcceso(), claveLocal);
	}

	@Test
	public void testSetRol() {
		usuario2.setRol(Usuario.RolUsuario.NORMAL);
		assertEquals(usuario1.getRol(), Usuario.RolUsuario.NORMAL);
	}

	@Test
	public void testToString() {
		assertEquals(usuario1.toString(), 
				"nif:             0000001R\n" +
				"nombre:          Luis\n" +
				"apellidos:       Roca Mora\n" +
				"domicilio:       Roncal, 10, 30130, Murcia\n" +
				"correo:          luis@gmail.com\n" +
				"fechaNacimiento: 2000.3.21\n" +
				"fechaAlta:       2018.10.17\n" +
				"claveAcceso:     Miau#0\n" +
				"rol:             NORMAL\n"
			);
	}

	
	// Test's CON DATOS NO VALIDOS

	@Test
	public void testUsuarioConvencionalBlanco() {
		
		Usuario usuario = new Usuario(
				new Nif(" "), 
				" ", 
				" ",
				new DireccionPostal(" "," ", " ", " "), 
				new Correo(" "), 
				new Fecha(),
				new Fecha(), 
				new ClaveAcceso(" "), 
				RolUsuario.NORMAL
				); 
		assertNotNull(usuario.getNif());
		assertNotNull(usuario.getNombre());
		assertNotNull(usuario.getApellidos());
		assertNotNull(usuario.getDireccionPostal());
		assertNotNull(usuario.getCorreo());
		assertNotNull(usuario.getFechaNacimiento());
		assertNotNull(usuario.getFechaAlta());
		assertNotNull(usuario.getClaveAcceso());
		assertNotNull(usuario.getRol());
	}
	
	@Test
	public void testSetNifNull() {
		try {
			usuario2.setNif(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
			assertTrue(usuario2.getNif() != null);
		}
	}
	
	@Test
	public void testSetNifBlanco() {
			Nif dniBlanco = new Nif("00000000R");
			usuario2.setNif(new Nif(dniBlanco));	
			assertEquals(usuario2.getNif(), dniBlanco);
	}
	
	@Test
	public void testSetNombreNull() {
		try {
			usuario2.setNombre(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
			assertTrue(usuario2.getNombre() != null);
		}
	}
	
	@Test
	public void testSetNombreBlanco() {
			usuario2.setNombre("  ");	
			assertEquals(usuario2.getNombre(), "Nombre");
	}
	
	@Test
	public void testSetApellidosNull() {
		try {
			usuario2.setApellidos(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
			assertTrue(usuario2.getApellidos() != null);
		}
	}
	
	@Test
	public void testSetApellidosBlanco() {
			usuario2.setApellidos("  ");	
			assertEquals(usuario2.getApellidos(), "Apellido Apellido");
	}
	
	@Test
	public void testSetDomicilioNull() {
		try {
			usuario2.setDireccionPostal(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
			assertTrue(usuario2.getDireccionPostal() != null);
		}
	}
	
	@Test
	public void testSetDomicilioBlanco() {
			DireccionPostal domicilioBlanco = new DireccionPostal();
			usuario2.setDireccionPostal(domicilioBlanco);	
			assertEquals(usuario2.getDireccionPostal(), domicilioBlanco);
	}
	
	@Test
	public void testSetCorreoNull() {
		try {
			usuario2.setCorreo(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
			assertTrue(usuario2.getCorreo() != null);
		}
	}
	
	@Test
	public void testSetCorreoBlanco() {
			Correo correoBlanco = new Correo("correo@correo.es");
			usuario2.setCorreo(new Correo("  "));	
			assertEquals(usuario2.getCorreo(), correoBlanco);
	}
	
	@Test
	public void testSetFechaNacimientoNull() {
		try {
			usuario2.setFechaNacimiento(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
			assertTrue(usuario2.getFechaNacimiento() != null);
		}
	}
	
	@Test
	public void testSetFechaNacimientoFuturo() {	
			usuario1.setFechaNacimiento(new Fecha(3020, 9, 10));
			assertEquals(usuario1.getFechaNacimiento(), new Fecha(2000, 3, 21));
	}
	
	@Test
	public void testSetFechaAltaNull() {
		try {
			usuario2.setFechaAlta(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) {	
			assertTrue(usuario2.getFechaAlta() != null);
		}
	}

	@Test
	public void testSetFechaAltaFuturo() {	
			usuario1.setFechaAlta(new Fecha(3020, 9, 10));
			assertEquals(usuario1.getFechaAlta(), new Fecha(2018, 10, 17));
	}
	
	@Test
	public void testSetClaveAccesoNull() {
		try {
			usuario2.setClaveAcceso(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
			assertTrue(usuario2.getClaveAcceso() != null);
		}
	}

	@Test
	public void testSetClaveAccesoBlanco() {
			ClaveAcceso contraseñaBlanca = new ClaveAcceso("Miau#0");
			usuario2.setClaveAcceso(new ClaveAcceso("  "));	
			assertEquals(usuario2.getClaveAcceso(), contraseñaBlanca);
	}
	
	@Test
	public void testSetRolNull() {
		try {
			usuario2.setRol(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 		
			assertTrue(usuario2.getRol() != null);
		}
	}

} // class