package com.socket.games.poker.api.enums;

public enum CartaEnum {

	// @formatter:off
	PICAS_DOS(2, PaloEnum.PICAS, "♠2"),
	TREBOLES_DOS(2, PaloEnum.TREBOLES, "♣2"),
	CORAZONES_DOS(2, PaloEnum.CORAZONES, "♥2"),
	DIAMANTES_DOS(2, PaloEnum.DIAMANTES, "♦2"),
	
	PICAS_TRES(3, PaloEnum.PICAS, "♠3"),
	TREBOLES_TRES(3, PaloEnum.TREBOLES, "♣3"),
	CORAZONES_TRES(3, PaloEnum.CORAZONES, "♥3"),
	DIAMANTES_TRES(3, PaloEnum.DIAMANTES, "♦3"),
	
	PICAS_CUATRO(4, PaloEnum.PICAS, "♠4"),
	TREBOLES_CUATRO(4, PaloEnum.TREBOLES, "♣4"),
	CORAZONES_CUATRO(4, PaloEnum.CORAZONES, "♥4"),
	DIAMANTES_CUATRO(4, PaloEnum.DIAMANTES, "♦4"),
	
	PICAS_CINCO(5, PaloEnum.PICAS, "♠5"),
	TREBOLES_CINCO(5, PaloEnum.TREBOLES, "♣5"),
	CORAZONES_CINCO(5, PaloEnum.CORAZONES, "♥5"),
	DIAMANTES_CINCO(5, PaloEnum.DIAMANTES, "♦5"),
	
	PICAS_SEIS(6, PaloEnum.PICAS, "♠6"),
	TREBOLES_SEIS(6, PaloEnum.TREBOLES, "♣6"),
	CORAZONES_SEIS(6, PaloEnum.CORAZONES, "♥6"),
	DIAMANTES_SEIS(6, PaloEnum.DIAMANTES, "♦6"),
	
	PICAS_SIETE(7, PaloEnum.PICAS, "♠7"),
	TREBOLES_SIETE(7, PaloEnum.TREBOLES, "♣7"),
	CORAZONES_SIETE(7, PaloEnum.CORAZONES, "♥7"),
	DIAMANTES_SIETE(7, PaloEnum.DIAMANTES, "♦7"),
	
	PICAS_OCHO(8, PaloEnum.PICAS, "♠8"),
	TREBOLES_OCHO(8, PaloEnum.TREBOLES, "♣8"),
	CORAZONES_OCHO(8, PaloEnum.CORAZONES, "♥8"),
	DIAMANTES_OCHO(8, PaloEnum.DIAMANTES, "♦8"),
	
	PICAS_NUEVE(9, PaloEnum.PICAS, "♠9"),
	TREBOLES_NUEVE(9, PaloEnum.TREBOLES, "♣9"),
	CORAZONES_NUEVE(9, PaloEnum.CORAZONES, "♥9"),
	DIAMANTES_NUEVE(9, PaloEnum.DIAMANTES, "♦9"),
	
	PICAS_DIEZ(10, PaloEnum.PICAS, "♠10"),
	TREBOLES_DIEZ(10, PaloEnum.TREBOLES, "♣10"),
	CORAZONES_DIEZ(10, PaloEnum.CORAZONES, "♥10"),
	DIAMANTES_DIEZ(10, PaloEnum.DIAMANTES, "♦10"),
	
	PICAS_JACK(11, PaloEnum.PICAS, "♠J"),
	TREBOLES_JACK(11, PaloEnum.TREBOLES, "♣J"),
	CORAZONES_JACK(11, PaloEnum.CORAZONES, "♥J"),
	DIAMANTES_JACK(11, PaloEnum.DIAMANTES, "♦J"),
	
	PICAS_QUEEN(12, PaloEnum.PICAS, "♠Q"),
	TREBOLES_QUEEN(12, PaloEnum.TREBOLES, "♣Q"),
	CORAZONES_QUEEN(12, PaloEnum.CORAZONES, "♥Q"),
	DIAMANTES_QUEEN(12, PaloEnum.DIAMANTES, "♦Q"),
	
	PICAS_KING(13, PaloEnum.PICAS, "♠K"),
	TREBOLES_KING(13, PaloEnum.TREBOLES, "♣K"),
	CORAZONES_KING(13, PaloEnum.CORAZONES, "♥K"),
	DIAMANTES_KING(13, PaloEnum.DIAMANTES, "♦K"),
	
	PICAS_AS(14, PaloEnum.PICAS, "♠A"),
	TREBOLES_AS(14, PaloEnum.TREBOLES, "♣A"),
	CORAZONES_AS(14, PaloEnum.CORAZONES, "♥A"),
	DIAMANTES_AS(14, PaloEnum.DIAMANTES, "♦A");
	// @formatter:on

	private final Integer numero;
	private final PaloEnum palo;
	private final String carta;

	CartaEnum(final Integer numero, final PaloEnum palo, final String carta) {
		this.numero = numero;
		this.palo = palo;
		this.carta = carta;
	}

	public Integer getNumero() {
		return numero;
	}

	public PaloEnum getPalo() {
		return palo;
	}

	public String getCarta() {
		return carta;
	}

	public boolean isAs() {
		return this.numero.equals(14);
	}

	@Override
	public String toString() {
		return this.getCarta();
	}
}
