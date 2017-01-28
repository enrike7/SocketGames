package com.socket.games.poker.api.bo;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class PartidaBO {

	private List<JugadorBO> listaJugadores;

	public List<JugadorBO> getListaJugadores() {
		return listaJugadores;
	}

	public void setListaJugadores(List<JugadorBO> listaJugadores) {
		this.listaJugadores = listaJugadores;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("listaJugadores", listaJugadores).toString();
	}
}
