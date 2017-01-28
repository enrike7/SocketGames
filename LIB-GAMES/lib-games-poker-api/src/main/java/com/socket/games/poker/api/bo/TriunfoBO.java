package com.socket.games.poker.api.bo;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.socket.games.poker.api.enums.CartaEnum;
import com.socket.games.poker.api.enums.TriunfoEnum;

public class TriunfoBO {

	private TriunfoEnum triunfo;
	private List<CartaEnum> cartasTriunfo;

	public TriunfoEnum getTriunfo() {
		return triunfo;
	}

	public void setTriunfo(TriunfoEnum triunfo) {
		this.triunfo = triunfo;
	}

	public List<CartaEnum> getCartasTriunfo() {
		return cartasTriunfo;
	}

	public void setCartasTriunfo(List<CartaEnum> cartasTriunfo) {
		this.cartasTriunfo = cartasTriunfo;
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
		TriunfoBO castOther = (TriunfoBO) other;
		return new EqualsBuilder().append(triunfo, castOther.triunfo).append(cartasTriunfo, castOther.cartasTriunfo)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(triunfo).append(cartasTriunfo).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("triunfo", triunfo).append("cartasTriunfo", cartasTriunfo).toString();
	}

}
