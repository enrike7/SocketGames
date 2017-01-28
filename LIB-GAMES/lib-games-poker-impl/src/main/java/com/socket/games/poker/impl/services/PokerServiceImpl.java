package com.socket.games.poker.impl.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.CollectionUtils;

import com.socket.games.poker.api.bo.JugadorBO;
import com.socket.games.poker.api.bo.PartidaBO;
import com.socket.games.poker.api.bo.TriunfoBO;
import com.socket.games.poker.api.bo.TriunfoJugadorBO;
import com.socket.games.poker.api.enums.CartaEnum;
import com.socket.games.poker.api.enums.PaloEnum;
import com.socket.games.poker.api.enums.TriunfoEnum;
import com.socket.games.poker.api.services.PokerService;

public class PokerServiceImpl implements PokerService {

	public TriunfoJugadorBO obtenerGanador(final PartidaBO partida) {
		final List<TriunfoJugadorBO> listaTriunfoJugador = new ArrayList<TriunfoJugadorBO>();
		for (JugadorBO jugador : partida.getListaJugadores()) {
			listaTriunfoJugador.add(this.determinarTriunfoJugador(jugador));
		}
		return this.determinarTriunfoGanador(listaTriunfoJugador);
	}

	private TriunfoJugadorBO determinarTriunfoGanador(final List<TriunfoJugadorBO> listaTriunfoJugador) {
		TriunfoJugadorBO triunfoJugadorGanador = null;
		for (final TriunfoJugadorBO triunfoJugadorActual : listaTriunfoJugador) {
			if (triunfoJugadorGanador == null || triunfoJugadorActual.getTriunfo().getTriunfo()
					.compareTo(triunfoJugadorGanador.getTriunfo().getTriunfo()) > 0) {
				triunfoJugadorGanador = triunfoJugadorActual;
			} else if (triunfoJugadorActual.getTriunfo().getTriunfo()
					.compareTo(triunfoJugadorGanador.getTriunfo().getTriunfo()) == 0) {
				// TODO logica, determinar que jugador tiene el triunfo mas alto
			}
		}
		return triunfoJugadorGanador;
	}

	public TriunfoJugadorBO determinarTriunfoJugador(final JugadorBO jugador) {
		final TriunfoJugadorBO triunfoJugador = new TriunfoJugadorBO();

		// Ordenamos las cartas
		final List<CartaEnum> cartasJugador = jugador.getCartas();
		Collections.sort(cartasJugador);

		final TriunfoBO triunfo;
		if (esTriunfoEscaleraDeColor(cartasJugador)) {
			triunfo = getTriunfoEscaleraDeColor(cartasJugador);
		} else if (esTriunfoPoker(cartasJugador)) {
			triunfo = getTriunfoPoker(cartasJugador);
		} else if (esTriunfoFull(cartasJugador)) {
			triunfo = getTriunfoFull(cartasJugador);
		} else if (esTriunfoColor(cartasJugador)) {
			triunfo = getTriunfoColor(cartasJugador);
		} else if (esTriunfoEscalera(cartasJugador)) {
			triunfo = getTriunfoEscalera(cartasJugador);
		} else if (esTriunfoTrio(cartasJugador)) {
			triunfo = getTriunfoTrio(cartasJugador);
		} else if (esTriunfoDoblePareja(cartasJugador)) {
			triunfo = getTriunfoDoblePareja(cartasJugador);
		} else if (esTriunfoPareja(cartasJugador)) {
			triunfo = getTriunfoPareja(cartasJugador);
		} else {
			triunfo = getTriunfoCartaAlta(cartasJugador);
		}

		triunfoJugador.setJugador(jugador);
		triunfoJugador.setTriunfo(triunfo);
		return triunfoJugador;
	}

	private Map<Integer, List<CartaEnum>> agruparCartasPorNumero(final List<CartaEnum> cartasJugador) {
		final Map<Integer, List<CartaEnum>> mapPareja = new HashMap<Integer, List<CartaEnum>>();
		for (final CartaEnum carta : cartasJugador) {
			final Integer numero = carta.getNumero();
			if (mapPareja.get(numero) == null) {
				mapPareja.put(numero, new ArrayList<CartaEnum>());
			}
			mapPareja.get(numero).add(carta);
		}
		return mapPareja;
	}

	private Map<PaloEnum, List<CartaEnum>> agruparCartasPorPalo(final List<CartaEnum> cartasJugador) {
		final Map<PaloEnum, List<CartaEnum>> mapPareja = new HashMap<PaloEnum, List<CartaEnum>>();
		for (final CartaEnum carta : cartasJugador) {
			final PaloEnum color = carta.getPalo();
			if (mapPareja.get(color) == null) {
				mapPareja.put(color, new ArrayList<CartaEnum>());
			}
			mapPareja.get(color).add(carta);
		}
		return mapPareja;
	}

	private boolean esTriunfoEscaleraDeColor(final List<CartaEnum> cartasJugador) {
		return !CollectionUtils.isEmpty(this.getTriunfoEscaleraDeColor(cartasJugador).getCartasTriunfo());
	}

	private TriunfoBO getTriunfoEscaleraDeColor(final List<CartaEnum> cartasJugador) {
		// Condiciones
		boolean color = this.esTriunfoColor(cartasJugador);
		boolean escalera = this.esTriunfoEscalera(cartasJugador);

		final TriunfoBO triunfo = new TriunfoBO();
		if (escalera && color) {
			triunfo.setTriunfo(TriunfoEnum.ESCALERA_COLOR);
			triunfo.setCartasTriunfo(this.getTriunfoEscalera(cartasJugador).getCartasTriunfo());
		}
		return triunfo;
	}

	private boolean esTriunfoPoker(final List<CartaEnum> cartasJugador) {
		return !CollectionUtils.isEmpty(this.getTriunfoPoker(cartasJugador).getCartasTriunfo());
	}

	private TriunfoBO getTriunfoPoker(final List<CartaEnum> cartasJugador) {
		// Condiciones
		boolean poker = false;

		final List<CartaEnum> cartasTriunfo = new ArrayList<CartaEnum>();
		final Map<Integer, List<CartaEnum>> mapAgupacionPorNumero = this.agruparCartasPorNumero(cartasJugador);
		for (final Entry<Integer, List<CartaEnum>> entry : mapAgupacionPorNumero.entrySet()) {
			final List<CartaEnum> cartasAgrupadasPorNumero = entry.getValue();
			if (cartasAgrupadasPorNumero.size() == 4) {
				cartasTriunfo.addAll(cartasAgrupadasPorNumero);
				poker = true;
			}
		}

		final TriunfoBO triunfo = new TriunfoBO();
		if (poker) {
			triunfo.setTriunfo(TriunfoEnum.POKER);
			triunfo.setCartasTriunfo(cartasTriunfo);
		}
		return triunfo;
	}

	private boolean esTriunfoFull(final List<CartaEnum> cartasJugador) {
		return !CollectionUtils.isEmpty(this.getTriunfoFull(cartasJugador).getCartasTriunfo());
	}

	private TriunfoBO getTriunfoFull(final List<CartaEnum> cartasJugador) {
		// Condiciones
		boolean pareja = false;
		boolean trio = false;

		final List<CartaEnum> cartasTriunfo = new ArrayList<CartaEnum>();
		final Map<Integer, List<CartaEnum>> mapAgupacionPorNumero = this.agruparCartasPorNumero(cartasJugador);
		for (final Entry<Integer, List<CartaEnum>> entry : mapAgupacionPorNumero.entrySet()) {
			final List<CartaEnum> cartasAgrupadasPorNumero = entry.getValue();
			if (cartasAgrupadasPorNumero.size() == 2) {
				cartasTriunfo.addAll(cartasAgrupadasPorNumero);
				pareja = true;
			}
			if (cartasAgrupadasPorNumero.size() == 3) {
				cartasTriunfo.addAll(cartasAgrupadasPorNumero);
				trio = true;
			}
		}

		final TriunfoBO triunfo = new TriunfoBO();
		if (pareja && trio) {
			triunfo.setTriunfo(TriunfoEnum.FULL);
			triunfo.setCartasTriunfo(cartasTriunfo);
		}
		return triunfo;
	}

	private boolean esTriunfoColor(final List<CartaEnum> cartasJugador) {
		return !CollectionUtils.isEmpty(this.getTriunfoColor(cartasJugador).getCartasTriunfo());
	}

	private TriunfoBO getTriunfoColor(final List<CartaEnum> cartasJugador) {
		// Condiciones
		boolean color = false;

		final List<CartaEnum> cartasTriunfo = new ArrayList<CartaEnum>();
		final Map<PaloEnum, List<CartaEnum>> mapAgupacionPorPalo = this.agruparCartasPorPalo(cartasJugador);
		for (final Entry<PaloEnum, List<CartaEnum>> entry : mapAgupacionPorPalo.entrySet()) {
			final List<CartaEnum> cartasAgrupadasPorPalo = entry.getValue();
			if (cartasAgrupadasPorPalo.size() == 5) {
				cartasTriunfo.addAll(cartasAgrupadasPorPalo);
				color = true;
			}
		}

		final TriunfoBO triunfo = new TriunfoBO();
		if (color) {
			triunfo.setTriunfo(TriunfoEnum.COLOR);
			triunfo.setCartasTriunfo(cartasTriunfo);
		}
		return triunfo;
	}

	private boolean esTriunfoEscalera(final List<CartaEnum> cartasJugador) {
		return !CollectionUtils.isEmpty(this.getTriunfoEscalera(cartasJugador).getCartasTriunfo());
	}

	private TriunfoBO getTriunfoEscalera(final List<CartaEnum> cartasJugador) {
		// Condicion
		boolean escalera = true;

		final List<CartaEnum> cartasTriunfo = new ArrayList<CartaEnum>();
		final Map<Integer, List<CartaEnum>> mapAgupacionPorNumero = this.agruparCartasPorNumero(cartasJugador);
		CartaEnum cartaAnterior = null;
		for (Entry<Integer, List<CartaEnum>> entry : mapAgupacionPorNumero.entrySet()) {
			final CartaEnum cartaActual = entry.getValue().get(0);
			if (cartaAnterior == null || cartaActual.getNumero().equals(cartaAnterior.getNumero() + 1)) {
				cartaAnterior = cartaActual;
				cartasTriunfo.add(cartaActual);
			} else if (cartaActual.isAs() && cartaAnterior.getNumero().equals(5)) {
				cartasTriunfo.add(0, cartaActual);
			} else {
				escalera = false;
				break;
			}
		}

		final TriunfoBO triunfo = new TriunfoBO();
		if (escalera) {
			triunfo.setTriunfo(TriunfoEnum.ESCALERA);
			triunfo.setCartasTriunfo(cartasTriunfo);
		}
		return triunfo;
	}

	private boolean esTriunfoTrio(final List<CartaEnum> cartasJugador) {
		return !CollectionUtils.isEmpty(this.getTriunfoTrio(cartasJugador).getCartasTriunfo());
	}

	private TriunfoBO getTriunfoTrio(final List<CartaEnum> cartasJugador) {
		// Condiciones
		boolean trio = false;

		final List<CartaEnum> cartasTriunfo = new ArrayList<CartaEnum>();
		final Map<Integer, List<CartaEnum>> mapAgupacionPorNumero = this.agruparCartasPorNumero(cartasJugador);
		for (final Entry<Integer, List<CartaEnum>> entry : mapAgupacionPorNumero.entrySet()) {
			final List<CartaEnum> cartasAgrupadasPorNumero = entry.getValue();
			if (cartasAgrupadasPorNumero.size() == 3) {
				cartasTriunfo.addAll(cartasAgrupadasPorNumero);
				trio = true;
			}
		}

		final TriunfoBO triunfo = new TriunfoBO();
		if (trio) {
			triunfo.setTriunfo(TriunfoEnum.TRIO);
			triunfo.setCartasTriunfo(cartasTriunfo);
		}
		return triunfo;
	}

	private boolean esTriunfoDoblePareja(final List<CartaEnum> cartasJugador) {
		return !CollectionUtils.isEmpty(this.getTriunfoDoblePareja(cartasJugador).getCartasTriunfo());
	}

	private TriunfoBO getTriunfoDoblePareja(final List<CartaEnum> cartasJugador) {
		// Condiciones
		boolean primeraPareja = false;
		boolean segundaPareja = false;

		final List<CartaEnum> cartasTriunfo = new ArrayList<CartaEnum>();
		final Map<Integer, List<CartaEnum>> mapAgupacionPorNumero = this.agruparCartasPorNumero(cartasJugador);
		for (final Entry<Integer, List<CartaEnum>> entry : mapAgupacionPorNumero.entrySet()) {
			final List<CartaEnum> cartasAgrupadasPorNumero = entry.getValue();
			if (cartasAgrupadasPorNumero.size() == 2) {
				cartasTriunfo.addAll(cartasAgrupadasPorNumero);
				if (primeraPareja) {
					segundaPareja = true;
				}
				primeraPareja = true;
			}
		}

		final TriunfoBO triunfo = new TriunfoBO();
		if (primeraPareja && segundaPareja) {
			triunfo.setTriunfo(TriunfoEnum.DOBLE_PAREJA);
			triunfo.setCartasTriunfo(cartasTriunfo);
		}
		return triunfo;
	}

	private boolean esTriunfoPareja(final List<CartaEnum> cartasJugador) {
		return !CollectionUtils.isEmpty(this.getTriunfoPareja(cartasJugador).getCartasTriunfo());
	}

	private TriunfoBO getTriunfoPareja(final List<CartaEnum> cartasJugador) {
		// Condiciones
		boolean pareja = false;

		final List<CartaEnum> cartasTriunfo = new ArrayList<CartaEnum>();
		final Map<Integer, List<CartaEnum>> mapAgupacionPorNumero = this.agruparCartasPorNumero(cartasJugador);
		for (final Entry<Integer, List<CartaEnum>> entry : mapAgupacionPorNumero.entrySet()) {
			final List<CartaEnum> cartasAgrupadasPorNumero = entry.getValue();
			if (cartasAgrupadasPorNumero.size() == 2) {
				cartasTriunfo.addAll(cartasAgrupadasPorNumero);
				pareja = true;
			}
		}

		final TriunfoBO triunfo = new TriunfoBO();
		if (pareja) {
			triunfo.setTriunfo(TriunfoEnum.PAREJA);
			triunfo.setCartasTriunfo(cartasTriunfo);
		}
		return triunfo;
	}

	private TriunfoBO getTriunfoCartaAlta(final List<CartaEnum> cartasJugador) {
		final TriunfoBO triunfo = new TriunfoBO();
		triunfo.setTriunfo(TriunfoEnum.CARTA_ALTA);
		triunfo.setCartasTriunfo(Arrays.asList(cartasJugador.get(cartasJugador.size() - 1)));
		return triunfo;
	}
}
