package org.iesalandalus.programacion.cuatroenraya.modelo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class JugadorTest {
	
	private static final String NOMBRE_NO_ESPERADO = "El nombre del jugador recién creado no es el esperado.";
	private static final String FICHA_NO_ESPERADA = "La ficha no es la esperada.";
	private static final String CADENA_NO_ESPERADA = "La cadena que representa la casilla no es la esperada.";
	private static final String OBJETO_DEBERIA_SER_NULL = "No se debería haber creado el objeto posición.";
	private static final String EXCEPCION_NOMBRE_NULO = "Debería haber saltado una excepción indicando que no se puede poner un nombre nulo.";
	private static final String EXCEPCION_NOMBRE_VACIO = "Debería haber saltado una excepción indicando que no se puede poner un nombre vacío.";
	private static final String EXCEPCION_FICHA_NULA = "Debería haber saltado una excepción indicando que no se puede poner una ficha nula.";
	private static final String NO_EXCEPCION = "No debería haber saltado este tipo de excepción.";
	private static final String MENSAJE_EXCEPCION_NO_ESPERADO = "El mensaje de la excepción no es el esperado.";
	private static final String ERROR_NOMBRE_NULO = "ERROR: El nombre no puede ser nulo.";
	private static final String ERROR_NOMBRE_VACIO = "ERROR: El nombre no puede estar vacío.";
	private static final String ERROR_FICHA_NULA = "ERROR: El color de las fichas no puede ser nulo.";

	@Test
	public void constructorNombreValidoFichaValidaCreaJugadorCorrectamente() {
		Jugador jugador = new Jugador("José Ramón", Ficha.AZUL);
		assertThat(NOMBRE_NO_ESPERADO, jugador.getNombre(), is("José Ramón"));
		assertThat(FICHA_NO_ESPERADA, jugador.getColorFichas(), is(Ficha.AZUL));
	}
	
	@Test
	public void constructorNombreNoValidoFichaValidaLanzaExcepcion() {
		Jugador jugador = null;
		try {
			jugador = new Jugador(null, Ficha.AZUL);
			fail(EXCEPCION_NOMBRE_NULO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_EXCEPCION_NO_ESPERADO, e.getMessage(), is(ERROR_NOMBRE_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULL, jugador, is(nullValue()));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
		try {
			jugador = new Jugador("", Ficha.AZUL);
			fail(EXCEPCION_NOMBRE_VACIO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_EXCEPCION_NO_ESPERADO, e.getMessage(), is(ERROR_NOMBRE_VACIO));
			assertThat(OBJETO_DEBERIA_SER_NULL, jugador, is(nullValue()));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
		try {
			jugador = new Jugador("   ", Ficha.AZUL);
			fail(EXCEPCION_NOMBRE_VACIO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_EXCEPCION_NO_ESPERADO, e.getMessage(), is(ERROR_NOMBRE_VACIO));
			assertThat(OBJETO_DEBERIA_SER_NULL, jugador, is(nullValue()));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
	}
	
	@Test
	public void constructorNombreValidoFichaNoValidaLanzaExcepcion() {
		Jugador jugador = null;
		try {
			jugador = new Jugador("José Ramón", null);
			fail(EXCEPCION_FICHA_NULA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_EXCEPCION_NO_ESPERADO, e.getMessage(), is(ERROR_FICHA_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULL, jugador, is(nullValue()));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
	}
	
	@Test
	public void toStringMuestraCadenaCorrectamente() {
		Jugador jugador = new Jugador("José Ramón", Ficha.AZUL);
		try {
			assertThat(CADENA_NO_ESPERADA, jugador.toString(), is("José Ramón (AZUL)"));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
	}

}
