package com.socket.games.poker.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.socket.games.poker.api.bo.JugadorBO;
import com.socket.games.poker.api.bo.PartidaBO;
import com.socket.games.poker.api.bo.TriunfoJugadorBO;
import com.socket.games.poker.api.enums.CartaEnum;
import com.socket.games.poker.api.services.PokerService;
import com.socket.games.poker.impl.services.PokerServiceImpl;

public class PokerGameTest {

	protected PartidaBO partidaTriunfoUnicoJugadorTest;
	protected PartidaBO partidaTestDos;
	protected PartidaBO partidaTestTres;

	protected List<CartaEnum> escaleraDeColor;
	protected List<CartaEnum> poker;
	protected List<CartaEnum> full;
	protected List<CartaEnum> color;
	protected List<CartaEnum> escalera;
	protected List<CartaEnum> trio;
	protected List<CartaEnum> doblePareja;
	protected List<CartaEnum> pareja;
	protected List<CartaEnum> cartaAlta;

	@Before
	public void init() {
		this.escaleraDeColor = this.crearEscaleraDeColor();
		this.poker = this.crearPoker();
		this.full = this.crearFull();
		this.color = this.crearColor();
		this.escalera = this.crearEscalera();
		this.trio = this.crearTrio();
		this.doblePareja = this.crearDoblePareja();
		this.pareja = this.crearPareja();
		this.cartaAlta = this.crearCartaAlta();

		this.partidaTriunfoUnicoJugadorTest = this.crearPartidaTriunfoUnicoJugadorTest();
	}

	private PartidaBO crearPartidaTriunfoUnicoJugadorTest() {
		final PartidaBO partida = new PartidaBO();
		final JugadorBO jugadorUno = new JugadorBO();
		jugadorUno.setJugador(1);
		jugadorUno.setCartas(escaleraDeColor);

		final JugadorBO jugadorDos = new JugadorBO();
		jugadorDos.setJugador(2);
		jugadorDos.setCartas(poker);

		// jugadorUno.setCartas(poker);
		// jugadorUno.setCartas(full);
		// jugadorUno.setCartas(color);
		// jugadorUno.setCartas(escalera);
		// jugadorUno.setCartas(trio);
		// jugadorUno.setCartas(doblePareja);
		// jugadorUno.setCartas(pareja);
		// jugadorUno.setCartas(cartaAlta);

		partida.setListaJugadores(Arrays.asList(jugadorUno, jugadorDos));
		return partida;
	}

	@Test
	public void triunfoUnicoJugadorTest() {
		final PokerService pokerService = new PokerServiceImpl();
		final TriunfoJugadorBO resultado = pokerService.obtenerGanador(this.partidaTriunfoUnicoJugadorTest);

		// Salida requerida
		final String resultadoEsperado = "Gana el jugador 1 con 'Escalera de color': {♥A, ♥2, ♥3, ♥4, ♥5}";
		Assert.assertEquals(resultadoEsperado, resultado.getTriunfoJugador());
	}

	private List<CartaEnum> crearEscaleraDeColor() {
		final List<CartaEnum> escaleraDeColor = new ArrayList<CartaEnum>();
		escaleraDeColor.add(CartaEnum.CORAZONES_AS);
		escaleraDeColor.add(CartaEnum.CORAZONES_DOS);
		escaleraDeColor.add(CartaEnum.CORAZONES_TRES);
		escaleraDeColor.add(CartaEnum.CORAZONES_CUATRO);
		escaleraDeColor.add(CartaEnum.CORAZONES_CINCO);
		return escaleraDeColor;
	}

	private List<CartaEnum> crearPoker() {
		final List<CartaEnum> poker = new ArrayList<CartaEnum>();
		poker.add(CartaEnum.CORAZONES_AS);
		poker.add(CartaEnum.PICAS_AS);
		poker.add(CartaEnum.TREBOLES_AS);
		poker.add(CartaEnum.CORAZONES_CUATRO);
		poker.add(CartaEnum.DIAMANTES_AS);
		return poker;
	}

	private List<CartaEnum> crearFull() {
		final List<CartaEnum> full = new ArrayList<CartaEnum>();
		full.add(CartaEnum.CORAZONES_AS);
		full.add(CartaEnum.PICAS_AS);
		full.add(CartaEnum.TREBOLES_AS);
		full.add(CartaEnum.CORAZONES_CUATRO);
		full.add(CartaEnum.DIAMANTES_CUATRO);
		return full;
	}

	private List<CartaEnum> crearColor() {
		final List<CartaEnum> color = new ArrayList<CartaEnum>();
		color.add(CartaEnum.TREBOLES_DOS);
		color.add(CartaEnum.TREBOLES_KING);
		color.add(CartaEnum.TREBOLES_JACK);
		color.add(CartaEnum.TREBOLES_AS);
		color.add(CartaEnum.TREBOLES_SIETE);
		return color;
	}

	private List<CartaEnum> crearEscalera() {
		final List<CartaEnum> escaleraDeColor = new ArrayList<CartaEnum>();
		escaleraDeColor.add(CartaEnum.TREBOLES_AS);
		escaleraDeColor.add(CartaEnum.CORAZONES_DIEZ);
		escaleraDeColor.add(CartaEnum.DIAMANTES_JACK);
		escaleraDeColor.add(CartaEnum.PICAS_KING);
		escaleraDeColor.add(CartaEnum.CORAZONES_QUEEN);
		return escaleraDeColor;
	}

	private List<CartaEnum> crearTrio() {
		final List<CartaEnum> trio = new ArrayList<CartaEnum>();
		trio.add(CartaEnum.CORAZONES_SIETE);
		trio.add(CartaEnum.PICAS_CUATRO);
		trio.add(CartaEnum.TREBOLES_AS);
		trio.add(CartaEnum.CORAZONES_CUATRO);
		trio.add(CartaEnum.DIAMANTES_CUATRO);
		return trio;
	}

	private List<CartaEnum> crearDoblePareja() {
		final List<CartaEnum> doblePareja = new ArrayList<CartaEnum>();
		doblePareja.add(CartaEnum.CORAZONES_AS);
		doblePareja.add(CartaEnum.PICAS_OCHO);
		doblePareja.add(CartaEnum.TREBOLES_AS);
		doblePareja.add(CartaEnum.CORAZONES_CUATRO);
		doblePareja.add(CartaEnum.DIAMANTES_CUATRO);
		return doblePareja;
	}

	private List<CartaEnum> crearPareja() {
		final List<CartaEnum> pareja = new ArrayList<CartaEnum>();
		pareja.add(CartaEnum.CORAZONES_SIETE);
		pareja.add(CartaEnum.PICAS_OCHO);
		pareja.add(CartaEnum.TREBOLES_AS);
		pareja.add(CartaEnum.CORAZONES_CUATRO);
		pareja.add(CartaEnum.DIAMANTES_CUATRO);
		return pareja;
	}

	private List<CartaEnum> crearCartaAlta() {
		final List<CartaEnum> cartaAlta = new ArrayList<CartaEnum>();
		cartaAlta.add(CartaEnum.CORAZONES_SIETE);
		cartaAlta.add(CartaEnum.PICAS_OCHO);
		cartaAlta.add(CartaEnum.TREBOLES_AS);
		cartaAlta.add(CartaEnum.CORAZONES_DIEZ);
		cartaAlta.add(CartaEnum.DIAMANTES_CUATRO);
		return cartaAlta;
	}
}
