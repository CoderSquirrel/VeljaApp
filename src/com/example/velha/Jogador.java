package com.example.velha;

public enum Jogador {

	COMPUTADOR, PESSOA;

	public Jogador getInverso() {
		if (this == PESSOA) {
			return COMPUTADOR;
		} else {
			return PESSOA;
		}
	}
}
