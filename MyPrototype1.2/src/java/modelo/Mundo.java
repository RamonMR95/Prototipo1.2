package modelo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.sun.tools.javac.code.Attribute.Array;


public class Mundo {
	private String nombre;
	private static final int TAMAÑO_MUNDO = 25;
	private byte[][] espacio = new byte[TAMAÑO_MUNDO][TAMAÑO_MUNDO];
	private List<Posicion> distribucion;
	private HashMap<String, int[]> constantes;
	
	enum FormaEspacio { PLANO, ESFERICO }
	private static final FormaEspacio TIPO_MUNDO = FormaEspacio.PLANO;

	public Mundo(String nombre,byte [][] espacio,  List<Posicion> distribucion, HashMap<String, int[]> constantes) {
		this.espacio = espacio;
		this.nombre = nombre;
		this.distribucion = distribucion;
		this.constantes = constantes;
	}
	
	public Mundo() {
		this("Demo", crearMundoDemo(), new LinkedList<Posicion>(), new HashMap<String, int[]>());
		leyesEstandar();
	}
	
	// TODO Terminar constructor copia
	public Mundo(Mundo mundo) {
		this.espacio = System.arraycopy(mundo.espacio, 0, espacio, 0, espacio.length);
		this.nombre = mundo.nombre;
		this.distribucion = mundo.distribucion;
		this.constantes = mundo.constantes;
	}
	
	public int getTamañoMundo() {
		return TAMAÑO_MUNDO;
	}
	
	
	public String getNombre() {
		return nombre;
	}

	public byte[][] getEspacio() {
		return espacio;
	}

	public List<Posicion> getDistribucion() {
		return distribucion;
	}

	public HashMap<String, int[]> getConstantes() {
		return constantes;
	}

	public static FormaEspacio getTipoMundo() {
		return TIPO_MUNDO;
	}

	private void leyesEstandar() {
		constantes.put("ValoresSobrevivir", new int[] { 2, 3 });
		constantes.put("ValoresRenacer", new int[] { 3 });
	}
	
	/**
	 * Carga datos demo en la matriz que representa el mundo.
	 */
	public static byte [][] crearMundoDemo() {
		return new byte[][] { 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
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
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } 
		};
	}
	/**
	 * Actualiza el estado del Juego de la Vida. Actualiza según la configuración
	 * establecida para la forma del espacio.
	 */
	public void actualizarMundo() {
		if (TIPO_MUNDO == FormaEspacio.PLANO) {
			actualizarMundoPlano();
		}
		if (TIPO_MUNDO == FormaEspacio.ESFERICO) {
			actualizarMundoEsferico();
		}
	}

	/**
	 * Actualiza el estado almacenado del Juego de la Vida. Las celdas periféricas
	 * son adyacentes con las del lado contrario. El mundo representado sería
	 * esférico cerrado sin límites para células de dos dimensiones.
	 */
	private void actualizarMundoEsferico() {
		// Pendiente de implementar.
	}

	/**
	 * Actualiza el estado de cada celda almacenada del Juego de la Vida. Las celdas
	 * periféricas son los límites absolutos. El mundo representado sería plano,
	 * cerrado y con límites para células de dos dimensiones.
	 */
	private void actualizarMundoPlano() {
		byte[][] nuevoEstado = new byte[TAMAÑO_MUNDO][TAMAÑO_MUNDO];

		for (int i = 0; i < TAMAÑO_MUNDO; i++) {
			for (int j = 0; j < TAMAÑO_MUNDO; j++) {
				int vecinas = 0;
				vecinas += visitarCeldaNoroeste(i, j);
				vecinas += visitarCeldaNorte(i, j);
				vecinas += visitarCeldaNoreste(i, j);
				vecinas += visitarCeldaEste(i, j);
				vecinas += visitarCeldaSureste(i, j);
				vecinas += visitarCeldaSur(i, j);
				vecinas += visitarCeldaSuroeste(i, j);
				vecinas += visitarCeldaOeste(i, j);

				actualizarCelda(nuevoEstado, i, j, vecinas);
			}
		}
		espacio = nuevoEstado;
	}

	/**
	 * Aplica las leyes del mundo a la celda indicada dada la cantidad de células
	 * adyacentes vivas.
	 * @param nuevoEstado
	 * @param fila
	 * @param col
	 * @param vecinas
	 */
	private void actualizarCelda(byte[][] nuevoEstado, int fila, int col, int vecinas) {

		for (int valor : constantes.get("ValoresRenacer")) {
			if (vecinas == valor) { // Pasa a estar viva.
				nuevoEstado[fila][col] = 1;
				return;
			}
		}


			for (int valor : constantes.get("ValoresSobrevivir")) {
				if (vecinas == valor && espacio[fila][col] == 1) { // Permanece viva, si lo estaba.
					nuevoEstado[fila][col] = 1;
					return;
				}
			}
		}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Oeste de la indicada
	 * por la coordenada.
	 * @param fila de la celda evaluada.
	 * @param col  de la celda evaluada.
	 * @return el estado o valor de la celda Oeste.
	 */
	private byte visitarCeldaOeste(int fila, int col) {
		if (col - 1 >= 0) {
			return espacio[fila][col - 1];
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Suroeste de la
	 * indicada por la coordenada.
	 * @param fila de la celda evaluada.
	 * @param col  de la celda evaluada.
	 * @return el estado o valor de la celda Suroeste.
	 */
	private byte visitarCeldaSuroeste(int fila, int col) {
		if (fila + 1 < TAMAÑO_MUNDO && col - 1 >= 0) {
			return espacio[fila + 1][col - 1];
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Sur de la indicada
	 * por la coordenada.
	 * @param fila de la celda evaluada.
	 * @param col  de la celda evaluada.
	 * @return el estado o valor de la celda Sur.
	 */
	private byte visitarCeldaSur(int fila, int col) {
		if (fila + 1 < TAMAÑO_MUNDO) {
			return espacio[fila + 1][col];
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Sureste de la
	 * indicada por la coordenada.
	 * @param fila de la celda evaluada.
	 * @param col  de la celda evaluada.
	 * @return el estado o valor de la celda Sureste.
	 */
	private byte visitarCeldaSureste(int fila, int col) {
		if (fila + 1 < TAMAÑO_MUNDO && col + 1 < TAMAÑO_MUNDO) {
			return espacio[fila + 1][col + 1];
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Este de la indicada
	 * por la coordenada.
	 * @param fila de la celda evaluada.
	 * @param col  de la celda evaluada.
	 * @return el estado o valor de la celda Este.
	 */
	private byte visitarCeldaEste(int fila, int col) {
		if (col + 1 < TAMAÑO_MUNDO) {
			return espacio[fila][col + 1];
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Noreste de la
	 * indicada por la coordenada.
	 * @param fila de la celda evaluada.
	 * @param col  de la celda evaluada.
	 * @return el estado o valor de la celda Noreste.
	 */
	private byte visitarCeldaNoreste(int fila, int col) {
		if (fila - 1 >= 0 && col + 1 < TAMAÑO_MUNDO) {
			return espacio[fila - 1][col + 1];
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al NO de la indicada por
	 * la coordenada.
	 * @param fila de la celda evaluada.
	 * @param col  de la celda evaluada.
	 * @return el estado o valor de la celda NO.
	 */
	private byte visitarCeldaNorte(int fila, int col) {
		if (fila - 1 >= 0) {
			return espacio[fila - 1][col];
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Noroeste de la
	 * indicada por la coordenada.
	 * @param fila de la celda evaluada.
	 * @param col  de la celda evaluada.
	 * @return el estado o valor de la celda Noroeste.
	 */
	private byte visitarCeldaNoroeste(int fila, int col) {
		if (fila - 1 >= 0 && col - 1 >= 0) {
			return espacio[fila - 1][col - 1];
		}
		return 0;
	}
}
