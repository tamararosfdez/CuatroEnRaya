package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class CuatroEnRaya {

	private static final int numeroJugadores = 2;
	private Jugador[] jugadores;
	public Tablero tablero;
	private int numJugador = 0;

	public CuatroEnRaya(Jugador jugador1, Jugador jugador2) {
		tablero = new Tablero();
		jugadores = new Jugador[numeroJugadores];
		if (jugador1==null || jugador2==null)
		{
			throw new NullPointerException("Error: jugador no puede ser nulo.");
		}
		jugadores[0] = jugador1;
		jugadores[1] = jugador2;
	}

	private boolean tirar(Jugador jugador)
	{
		boolean resultado = false;
		boolean trigger = false;
		do
		{
			int columna = Consola.leerColumna(jugador);
			try {
				if (tablero.introducirFicha(columna, jugador.getColorFichas()))
				{
					resultado = true;
				}
				trigger = true;
			} catch (OperationNotSupportedException e) {
				System.out.println(e.getMessage());
			}
		} while (!trigger);
		return resultado;
	}

	public void jugar()
	{
		if (tablero.estaLleno())
		{
			System.out.println("No quedan casillas libres.");
			System.exit(0);
		}
		else
		{
			System.out.println("Le toca a "+jugadores[numJugador].getNombre());
			if(tirar(jugadores[numJugador]))
			{
				System.out.println("¡"+jugadores[numJugador].getNombre()+" has ganado!");
				System.exit(0);
			}
			else
			{
				System.out.println(tablero);
			}
		}
		if (numJugador == 0)
		{
			numJugador = 1;
		}
		else
		{
			numJugador = 0;
		}
		jugar();
	}
}

