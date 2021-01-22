package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Casilla {
	private Ficha ficha;

	public Casilla() {
		ficha = null;
	}

	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) throws OperationNotSupportedException {
		if (ficha==null)
		{
			throw new NullPointerException("ERROR: No se puede poner una ficha nula.");
		}
		else if (estaOcupada())
		{
			throw new OperationNotSupportedException("ERROR: Ya contengo una ficha.");
		}
		else
		{
			this.ficha = ficha;
		}
	}

	public boolean estaOcupada()
	{
		boolean ocupada = false;
		if (ficha!=null)
		{
			ocupada = true;
		}
		return ocupada;
	}

	public String toString() {
		String resultado = "";
		if (ficha==null)
		{
			resultado = " ";
		}
		else
		{
			resultado = String.format("%s", ficha).substring(0,1);
		}
		System.out.println(resultado);
		return resultado;
	}

}
