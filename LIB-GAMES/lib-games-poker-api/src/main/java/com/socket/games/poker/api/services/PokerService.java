package com.socket.games.poker.api.services;

import com.socket.games.poker.api.bo.JugadorBO;
import com.socket.games.poker.api.bo.PartidaBO;
import com.socket.games.poker.api.bo.TriunfoJugadorBO;

public interface PokerService {
	TriunfoJugadorBO obtenerGanador(PartidaBO partida);

	TriunfoJugadorBO determinarTriunfoJugador(JugadorBO jugador);
}
