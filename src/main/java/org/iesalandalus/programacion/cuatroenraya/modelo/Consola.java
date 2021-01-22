package org.iesalandalus.programacion.cuatroenraya.modelo;

import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	
	private static int numDejugador = 1;
	private static Ficha fichaJugadornum1;

	private Consola()
	{

	}

	public static String leerNombre()
	{
		String nombre;
		boolean trigger = false;
		System.out.print("Escribe tu nombre completo: ");
		do 
		{
			if (trigger)
			{
				System.out.print("ERROR: Obligatorio el nomble, vuelve a introducirlo: ");
			}
			else
			{
				trigger = true;
			}
			nombre = Entrada.cadena();
		} while (nombre.trim().isEmpty());
		return nombre;
	}

	public static Ficha elegirColorFichas()
	{
		Ficha ficha = Ficha.AZUL;
		if (numDejugador==1)
		{
			int color;
			boolean trigger = false;
			System.out.print("Introduce el color que desees para la ficha: (1->Azul | 2->Verde) ");
			do
			{
				if (trigger)
				{
					System.out.print("El color no es correcto, vuelve a introducir, (1->Azul) o (2->Verde): ");
				}
				else
				{
					trigger = true;
				}
				color = Entrada.entero();
			} while (color<1 || color>2);
			if (color==1)
			{
				ficha = Ficha.AZUL;
			}
			if (color==2)
			{
				ficha = Ficha.VERDE;
			}
			fichaJugadornum1 = ficha;
		}
		else
		{
			if (fichaJugadornum1==Ficha.AZUL)
			{
				ficha = Ficha.VERDE;
			}
		}
		return ficha;
	}

	public static Jugador leerJugador()
	{
		System.out.println("[Informacion del Jugador "+numDejugador+"]");
		String nombre = leerNombre();
		Ficha ficha = elegirColorFichas();
		numDejugador++;
		return new Jugador (nombre, ficha);
	}

	public static Jugador leerJugadorFicha(Ficha ficha)
	{
		System.out.println("[Informacion del Jugador "+numDejugador+"]");
		String nombre = leerNombre();
		numDejugador++;
		return new Jugador(nombre, ficha);
	}

	public static int leerColumna(Jugador jugador)
	{
		int columna;
		boolean trigger = false;
		System.out.print(jugador.getNombre()+" introduce el numero de la columna (0-6): ");
		do
		{
			if (trigger)
			{
				System.out.print(jugador.getNombre()+" columna erronea,vuelve a intentarlo, desde 0 hasta 6: ");
			}
			else
			{
				trigger = true;
			}
			columna = Entrada.entero();
		} while (columna<0 || columna>=Tablero.COLUMNAS);
		return columna;
	}

}

