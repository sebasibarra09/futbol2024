package ar.edu.unlam.pb2.ea3;


import java.util.Comparator;
import java.util.TreeSet;


public class EquipoDeFutbol {

	private String nombre;
	private TreeSet<Jugador> jugadores;
	private static Integer CANTIDAD_MAXIMA_DE_JUGADORES;
	private Integer CONTADOR_JUGADORES;

	public EquipoDeFutbol(String nombre) {
		this.nombre = nombre;
		this.jugadores= new TreeSet<>();
		this.CANTIDAD_MAXIMA_DE_JUGADORES = 23;
		this.CONTADOR_JUGADORES = 0;
	}

	/*
	 * La capacidad m�xima de un equipo es 23. Cualquier intento de agregar m�s
	 * jugadores generar� una excepci�n (CapacidadMaximaException). Adem�s, no
	 * deber� permitir duplicar Jugadores (JugadorDuplicadoException).
	 */
	public Boolean agregarJugador(Jugador jugador) throws CapacidadMaximaException, JugadorDuplicadoException {
		if (jugadores.size() >= CANTIDAD_MAXIMA_DE_JUGADORES) {
			throw new CapacidadMaximaException();
		}
		if (jugadores.contains(jugador)) {
			throw new JugadorDuplicadoException();
		}
			jugadores.add(jugador);
			CONTADOR_JUGADORES++;
			return true;
		}
	

	/*
	 * Permite cambiar cualquier jugador. Un intento de cambiar un jugador no
	 * presente en el equipo generar� una excepci�n
	 * (JugadoreInexistenteException).
	 */
	public Boolean cambiarJugador(Jugador saliente, Jugador entrante) throws JugadoreInexistenteException, CapacidadMaximaException, JugadorDuplicadoException{
		if (!jugadores.contains(saliente)) {
			throw new JugadoreInexistenteException();
		} 
		jugadores.remove(saliente);
		
		return this.agregarJugador(entrante);
	}

	public TreeSet<Jugador> devolverPlanteOrdenadoPorNombreDeJugador() {
		return jugadores;
	}
	
	public TreeSet<Jugador> ordenarElPlantelParaDevolver(Comparator<Jugador> criterioDeOrdenacion) {
		TreeSet<Jugador> equipoOrdenado = new TreeSet<Jugador>(criterioDeOrdenacion);
		equipoOrdenado.addAll(jugadores);
		return equipoOrdenado;
	}

	public TreeSet<Jugador> devolverPlanteOrdenadoPorPrecioDeCompraDeJugador() {
		return ordenarElPlantelParaDevolver(new OrdenadoPorPrecio());
	}
	
	

	public TreeSet<Jugador> devolverPlanteOrdenadoPorNumeroDeCamisetaDeJugador() {
		return ordenarElPlantelParaDevolver(new OrdenadoPorNumero());
	}

	public TreeSet<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(TreeSet<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

}
