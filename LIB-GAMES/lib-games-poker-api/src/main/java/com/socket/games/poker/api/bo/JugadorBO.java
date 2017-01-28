package com.socket.games.poker.api.bo;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.socket.games.poker.api.enums.CartaEnum;

public class JugadorBO {

	private Integer jugador;
	private List<CartaEnum> cartas;

	public Integer getJugador() {
		return jugador;
	}

	public void setJugador(Integer jugador) {
		this.jugador = jugador;
	}

	public List<CartaEnum> getCartas() {
		return cartas;
	}

	public void setCartas(List<CartaEnum> cartas) {
		this.cartas = cartas;
	}

	@Override
	public boolean equals(final Object other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (!getClass().equals(other.getClass())) {
			return false;
		}
		JugadorBO castOther = (JugadorBO) other;
		return new EqualsBuilder().append(jugador, castOther.jugador).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(jugador).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("jugador", jugador).append("cartas", cartas).toString();
	}
}
