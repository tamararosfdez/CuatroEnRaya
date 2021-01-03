package org.iesalandalus.programacion.cuatroenraya.modelo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import javax.naming.OperationNotSupportedException;

import org.junit.Test;

public class CasillaTest {
	
	private static final String CASILLA_DEBERIA_ESTAR_VACIA = "La casilla recién creada debería estar vacía.";
	private static final String FICHA_NO_ESPERADA = "La ficha no es la esperada.";
	private static final String CADENA_NO_ESPERADA = "La cadena que representa la casilla no es la esperada.";
	private static final String EXCEPCION_FICHA_NULA = "Debería haber saltado una excepción indicando que no se puede poner una ficha nula.";
	private static final String EXCEPCION_CASILLA_OCUPADA = "Debería haber saltado una excepción indicando que no se puede poner una ficha en una casilla ocupada.";
	private static final String MENSAJE_EXCEPCION_NO_ESPERADO = "El mensaje de la excepción no es el esperado.";
	private static final String NO_EXCEPCION = "No debería haber saltado este tipo de excepción.";
	private static final String ERROR_FICHA_NULA = "ERROR: No se puede poner una ficha nula.";
	private static final String ERROR_CASILLA_OCUPADA = "ERROR: Ya contengo una ficha.";

	@Test
	public void constructorCreaCasillaVacia() {
		Casilla casilla = new Casilla();
		assertThat(CASILLA_DEBERIA_ESTAR_VACIA, casilla.getFicha(), is(nullValue()));
		assertThat(CASILLA_DEBERIA_ESTAR_VACIA, casilla.estaOcupada(), is(false));
	}
	
	@Test
	public void ponerFichaCasillaVaciaPoneFichaCorrectamente() {
		Casilla casilla = new Casilla();
		try {
			casilla.setFicha(Ficha.AZUL);
			assertThat(FICHA_NO_ESPERADA, casilla.getFicha(), is(Ficha.AZUL));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
		casilla = new Casilla();
		try {
			casilla.setFicha(Ficha.VERDE);
			assertThat(FICHA_NO_ESPERADA, casilla.getFicha(), is(Ficha.VERDE));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
	}
	
	@Test
	public void ponerFichaNulaLanzaExcepcion() {
		Casilla casilla = new Casilla();
		try {
			casilla.setFicha(null);
			fail(EXCEPCION_FICHA_NULA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_EXCEPCION_NO_ESPERADO, e.getMessage(), is(ERROR_FICHA_NULA));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
	}
	
	@Test
	public void ponerFichaCasillaOcupadaLanzaExcepcion() {
		Casilla casilla = new Casilla();
		try {
			casilla.setFicha(Ficha.AZUL);
			casilla.setFicha(Ficha.AZUL);
			fail(EXCEPCION_CASILLA_OCUPADA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_EXCEPCION_NO_ESPERADO, e.getMessage(), is(ERROR_CASILLA_OCUPADA));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
	}
	
	@Test
	public void toStringMuestraCadenaCorrectamente() {
		Casilla casilla = new Casilla();
		assertThat(CADENA_NO_ESPERADA, casilla.toString(), is(" "));
		try {
			casilla.setFicha(Ficha.AZUL);
			assertThat(CADENA_NO_ESPERADA, casilla.toString(), is("A"));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
		casilla = new Casilla();
		try {
			casilla.setFicha(Ficha.VERDE);
			assertThat(CADENA_NO_ESPERADA, casilla.toString(), is("V"));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
	}

}
