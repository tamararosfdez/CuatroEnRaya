package org.iesalandalus.programacion.cuatroenraya.modelo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FichaTest {
	
	private static final String ERROR_FICHA_NO_VALIDA = "ERROR: El color de la ficha no es válido.";

	@Test
	public void colorValidoCreaFichaValida() {
		Ficha ficha;
		ficha = Ficha.AZUL;
		assertThat(ERROR_FICHA_NO_VALIDA, ficha, is(Ficha.AZUL));
		ficha = Ficha.VERDE;
		assertThat(ERROR_FICHA_NO_VALIDA, ficha, is(Ficha.VERDE));
	}

}
