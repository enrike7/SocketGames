package com.socket.games.poker.api.bo;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TriunfoJugadorBO {

	private JugadorBO jugador;
	private TriunfoBO triunfo;

	public JugadorBO getJugador() {
		return jugador;
	}

	public void setJugador(JugadorBO jugador) {
		this.jugador = jugador;
	}

	public TriunfoBO getTriunfo() {
		return triunfo;
	}

	public void setTriunfo(TriunfoBO triunfo) {
		this.triunfo = triunfo;
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
		TriunfoJugadorBO castOther = (TriunfoJugadorBO) other;
		return new EqualsBuilder().append(jugador, castOther.jugador).append(triunfo, castOther.triunfo).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(jugador).append(triunfo).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("jugador", jugador).append("triunfo", triunfo).toString();
	}

	public String getTriunfoJugador() {
		return String.format("Gana el jugador %d con '%s': {%s}", this.jugador.getJugador(),
				this.triunfo.getTriunfo().getKeyDescripcion(), StringUtils.join(this.triunfo.getCartasTriunfo(), ", "));
	}

}
