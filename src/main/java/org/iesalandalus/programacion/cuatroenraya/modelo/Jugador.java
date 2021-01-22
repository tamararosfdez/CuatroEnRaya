package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Jugador {
	private Ficha colorFichas;
	private String nombre;

	public Jugador(String nombre, Ficha colorFichas) {
		setColorFichas(colorFichas);
		setNombre(nombre);
	}

	public Ficha getColorFichas() {
		return colorFichas;
	}

	public void setColorFichas(Ficha colorFichas) {
		if (colorFichas==null)
		{
			throw new NullPointerException("ERROR: El color de las fichas no puede ser nulo.");
		}
		else
		{
			this.colorFichas = colorFichas;
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre==null)
		{
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}
		if (nombre.trim().isEmpty())
		{
			throw new IllegalArgumentException("ERROR: El nombre no puede estar vacío.");
		}
		this.nombre = nombre;
	}

	public String toString() {
		return String.format("%s (%s)", nombre, colorFichas);
	}

}

