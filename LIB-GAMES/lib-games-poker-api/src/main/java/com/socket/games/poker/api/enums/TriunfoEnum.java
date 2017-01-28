package com.socket.games.poker.api.enums;

public enum TriunfoEnum {

	// @formatter:off
	CARTA_ALTA(1, "Carta alta"),
	PAREJA(2, "Pareja"),
	DOBLE_PAREJA(3, "Doble pareja"),
	TRIO(4, "Trio"),
	ESCALERA(5, "Escalera"),
	COLOR(6, "Color"),
	FULL(7, "Full"),
	POKER(8, "Poker"),
	ESCALERA_COLOR(9, "Escalera de color");
	// @formatter:on

	private final Integer clasificacion;
	private final String keyDescripcion;

	TriunfoEnum(final Integer clasificacion, final String keyDescripcion) {
		this.clasificacion = clasificacion;
		this.keyDescripcion = keyDescripcion;
	}

	public Integer getClasificacion() {
		return clasificacion;
	}

	public String getKeyDescripcion() {
		return keyDescripcion;
	}

	public TriunfoEnum determinarManoMasAlta(final TriunfoEnum manoA, final TriunfoEnum manoB) {
		if (manoA.clasificacion.compareTo(manoB.clasificacion) <= 0) {
			return manoB;
		}
		return manoA;
	}
}
