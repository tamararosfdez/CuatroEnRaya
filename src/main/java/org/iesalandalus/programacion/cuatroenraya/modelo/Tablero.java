package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Tablero {
	private Casilla[][] casillas;
	public static final int FILAS = 6;
	public static final int COLUMNAS = 7;
	public static final int fichasConsecutivas = 4; 

	public Tablero() {
		casillas = new Casilla[FILAS][COLUMNAS];
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				casillas[i][j] = new Casilla();
			}
		}
	}

	private boolean columnaVacia(int columna)
	{
		boolean resultado = true;
		for (int i=0; i<FILAS; i++)
		{
			if (casillas[i][columna].estaOcupada())
			{
				resultado = false;
			}
		}
		return resultado;
	}

	public boolean estaVacio()
	{
		boolean resultado = true;
		for (int i=0; i<COLUMNAS; i++)
		{
			if (!columnaVacia(i))
			{
				i = COLUMNAS;
				resultado = false;
			}
		}
		return resultado;
	}
	
	private boolean columnaLlena(int columna)
	{
		boolean resultado = true;
		for (int i=0; i<FILAS; i++)
		{
			if (!casillas[i][columna].estaOcupada())
			{
				resultado = false;
			}
		}
		return resultado;
	}


	public boolean estaLleno()
	{
		boolean resultado = true;
		for (int i=0; i<COLUMNAS; i++)
		{
			if (!columnaLlena(i))
			{
				i = COLUMNAS;
				resultado = false;
			} 
		}
		return resultado;
	}
	
	private void comprobarFicha(Ficha ficha)
	{
		if (ficha==null)
		{
			throw new NullPointerException("ERROR: La ficha no puede ser nula.");
		}
	}

	private void comprobarColumna(int columna)
	{
		if (columna<0 || columna>=COLUMNAS)
		{
			throw new IllegalArgumentException("ERROR: Columna incorrecta.");
		}
	}
	
	private int getPrimeraFilaVacia(int columna)
	{
		boolean trigger = false;
		int fila = 0;
		for (int i=0; i<FILAS; i++)
		{
			if (!casillas[i][columna].estaOcupada() && !trigger)
			{
				trigger = true;
				fila = i;
			}
		}
		return fila;
	}
	
	private boolean objetivoAlcanzado(int fichasConsecutiva)
	{
		boolean resultado = false;
		if (fichasConsecutiva>=fichasConsecutivas)
		{
			resultado = true;
		}
		return resultado;
	}
	
	private boolean comprobarHorizontal(int fila, Ficha ficha)
	{
		boolean resultado = false;
		int fichasConsecutivas = 0;
		for (int i=0; i<COLUMNAS; i++)
		{
			if(casillas[fila][i].estaOcupada())
			{
				if (ficha == casillas[fila][i].getFicha())
				{
					fichasConsecutivas++;
				}
				else
				{
					fichasConsecutivas = 0;
				}
			}
			else
			{
				fichasConsecutivas = 0;
			}
			if (objetivoAlcanzado(fichasConsecutivas))
			{
				resultado = true;
			}
		}
		return resultado;
	}
	
	private boolean comprobarVertical(int columna, Ficha ficha)
	{ 
		boolean resultado = false;
		int fichasConsecutivas = 0;
		for (int i=0; i<FILAS; i++)
		{
			if(casillas[i][columna].estaOcupada())
			{
				if (ficha == casillas[i][columna].getFicha())
				{
					fichasConsecutivas++;
				}
				else
				{
					fichasConsecutivas = 0;
				}
			}
			else
			{
				fichasConsecutivas = 0;
			}
			if (objetivoAlcanzado(fichasConsecutivas))
			{
				resultado = true;
			}
		}
		return resultado;
	}

	private int menor(int entero1, int entero2)
	{
		int numeroMenor = 0;
		if (entero1<entero2)
		{
			numeroMenor = entero1;
		}
		else if (entero2<entero1)
		{
			numeroMenor = entero2;
		}
		else if (entero1==entero2) {
			numeroMenor = entero1;
		}
		return numeroMenor;
	}

	private boolean comprobarDiagonaINE(int fila, int columna, Ficha ficha)
	{
		boolean resultado = false;
		int fichasConsecutivas = 0;
		int menor = menor(fila, columna);
		fila -= menor;
		columna -= menor;
		for (int i=0; i<COLUMNAS; ++i)
		{
			if (fila<FILAS && columna<COLUMNAS)
			{
				if(casillas[fila][columna].estaOcupada())
				{
					if (ficha == casillas[fila][columna].getFicha())
					{
						fichasConsecutivas++;
					}
					else
					{
						fichasConsecutivas = 0;
					}
				}
				else
				{
					fichasConsecutivas = 0;
				}
				if (objetivoAlcanzado(fichasConsecutivas))
				{
					resultado = true;
				}
				fila++;
				columna++;
			}
		}
		return resultado;
	}

	private boolean comprobarDiagonaINO(int fila, int columna, Ficha ficha)
	{
		boolean resultado = false;
		int fichasConsecutivas = 0;
		int menor = menor(fila, COLUMNAS-1-columna);
		fila -= menor;
		columna += menor;
		for (int i=0; i<COLUMNAS; ++i)
		{
			if (fila<FILAS && columna>=0)
			{
				if(casillas[fila][columna].estaOcupada())
				{
					if (ficha == casillas[fila][columna].getFicha())
					{
						fichasConsecutivas++;
					}
					else
					{
						fichasConsecutivas = 0;
					}
				}
				else
				{
					fichasConsecutivas = 0;
				}
				if (objetivoAlcanzado(fichasConsecutivas))
				{
					resultado = true;
				}
				fila++;
				columna--;
			}
		}
		return resultado;
	}
	
	private boolean comprobarTirada(int fila, int columna)
	{
		boolean resultado = false;
		if ( comprobarHorizontal(fila, casillas[fila][columna].getFicha()) ||
				comprobarVertical(columna, casillas[fila][columna].getFicha()) ||
				comprobarDiagonaINE(fila, columna, casillas[fila][columna].getFicha()) ||
				comprobarDiagonaINO(fila, columna, casillas[fila][columna].getFicha()) )
		{
			resultado = true;
		}
		return resultado;
	}

	
	public boolean introducirFicha(int columna, Ficha ficha) throws OperationNotSupportedException
	{
		boolean resultado = false;
		comprobarFicha(ficha);
		comprobarColumna(columna);
		if (columnaLlena(columna))
		{
			throw new OperationNotSupportedException("ERROR: Columna llena.");
		}
		else
		{
			int primeraFilaVacia = getPrimeraFilaVacia(columna);
			casillas[primeraFilaVacia][columna].setFicha(ficha);
			resultado = comprobarTirada(primeraFilaVacia, columna);
		}
		return resultado;
	}
	
	public String toString() {
		String solucion = "";
		for (int i=FILAS-1; i>=0; --i)
		{
			for (int j=0; j<COLUMNAS; ++j)
			{
				if (casillas[i][j].estaOcupada())
				{
					if (casillas[i][j].getFicha()==Ficha.AZUL)
					{
						solucion = solucion+"A";
					}
					else if (casillas[i][j].getFicha()==Ficha.VERDE)
					{
						solucion = solucion+"V";
					}
				}
				else
				{
					solucion = solucion+" ";
				}
			}
			solucion = solucion+"|\n|";
		}
		return "|"+solucion.substring(0,solucion.length()-1)+" -------\n";
	}

}


