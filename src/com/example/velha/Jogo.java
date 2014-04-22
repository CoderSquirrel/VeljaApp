package com.example.velha;

import java.util.Random;
import java.util.Scanner;

public class Jogo {

	public static void main(String[] args) {

	}

	public static boolean vencedor(Jogador[][] tab, Jogador jogador) {

		boolean ganhou = false;

		if (jogador == Jogador.PESSOA) {

			if (verificarTransversais(tab, Jogador.PESSOA)) {

				ganhou = true;

			} else if (verificarHorzontais(tab, Jogador.PESSOA)) {

				ganhou = true;

			} else if (verificarVerticais(tab, Jogador.PESSOA)) {

				ganhou = true;

			}

		}

		else if (jogador == Jogador.COMPUTADOR) {

			if (verificarTransversais(tab, Jogador.COMPUTADOR)) {

				ganhou = true;

			} else if (verificarHorzontais(tab, Jogador.COMPUTADOR)) {

				ganhou = true;

			} else if (verificarVerticais(tab, Jogador.COMPUTADOR)) {

				ganhou = true;

			}

		}

		return ganhou;

	}

	private static boolean verificarTransversais(Jogador[][] tab,

	Jogador jogador) {

		// direita

		if (tab[0][0] == tab[1][1] && tab[1][1] == tab[2][2]

		&& tab[2][2] == jogador) {

			return true;

		}

		if (tab[0][2] == tab[1][1] && tab[1][1] == tab[2][0]

		&& tab[2][0] == jogador) {

			return true;

		}

		// esquerda

		return false;

	}

	private static boolean verificarVerticais(Jogador[][] tab, Jogador jogador) {

		// 0

		if (tab[0][0] == tab[1][0] && tab[1][0] == tab[2][0]

		&& tab[2][0] == jogador) {

			return true;

		}

		// 1

		if (tab[0][1] == tab[1][1] && tab[1][1] == tab[2][1]

		&& tab[2][1] == jogador) {

			return true;

		}

		// 2

		if (tab[0][2] == tab[1][2] && tab[1][2] == tab[2][2]

		&& tab[2][2] == jogador) {

			return true;

		}

		return false;

	}

	private static boolean verificarHorzontais(Jogador[][] tab, Jogador jogador) {

		// 0

		if (tab[0][0] == tab[0][1] && tab[0][1] == tab[0][2]

		&& tab[0][2] == jogador) {

			return true;

		}

		// 1

		if (tab[1][0] == tab[1][1] && tab[1][1] == tab[1][2]

		&& tab[1][2] == jogador) {

			return true;

		}

		// 2

		if (tab[2][0] == tab[2][1] && tab[2][1] == tab[2][2]

		&& tab[2][2] == jogador) {

			return true;

		}

		return false;

	}

	/**
	 * 
	 * 
	 * 
	 * @param tab
	 * 
	 *            : recebe o tabuleiro
	 * 
	 * @return: verdadeiro se o tabuleiro estiver cheio, false se tiver alguma
	 * 
	 *          prosição sobrando
	 */

	private static boolean verificarTabuleiroCheio(Jogador[][] tab) {

		for (int i = 0; i < tab.length; i++) {

			for (int j = 0; j < tab.length; j++) {

				if (tab[i][j] == null) {

					return false;

				}

			}

		}

		return true;

	}

	private static int max(Jogador[][] tab) {

		if (vencedor(tab, Jogador.PESSOA)) {

			return -10;

		} else if (vencedor(tab, Jogador.COMPUTADOR)) {

			return 10;

		} else if (verificarTabuleiroCheio(tab)) {

			return 0;

		} else {

			int maior = Integer.MIN_VALUE;

			for (int l = 0, m = 0; l < tab.length; l++) {

				for (int c = 0; c < tab.length; c++) {

					if (tab[l][c] == null) {

						tab[l][c] = Jogador.COMPUTADOR;

						m = min(tab);

						maior = m > maior ? m : maior;

						tab[l][c] = null;

					}

				}

			}

			return maior;

		}

	}

	public static int min(Jogador[][] tab) {

		if (vencedor(tab, Jogador.PESSOA)) {

			return -10;

		} else if (vencedor(tab, Jogador.COMPUTADOR)) {

			return 10;

		} else if (verificarTabuleiroCheio(tab)) {

			return 0;

		} else {

			int menor = Integer.MAX_VALUE;

			for (int l = 0, m = 0; l < tab.length; l++) {

				for (int c = 0; c < tab.length; c++) {

					if (tab[l][c] == null) {

						tab[l][c] = Jogador.PESSOA;

						m = max(tab);

						menor = m < menor ? m : menor;

						tab[l][c] = null;

					}

				}

			}

			return menor;

		}

	}

	private static void Decisao(Jogador[][] tabuleiro) {

		Jogador[][] tab = tabuleiro.clone();

		int[][] custos = new int[3][3];

		for (int l = 0; l < 3; l++) {

			for (int c = 0; c < 3; c++) {

				if (tab[l][c] == null) {

					tab[l][c] = Jogador.COMPUTADOR;

					custos[l][c] = min(tab);

					tab[l][c] = null;

				} else {

					custos[l][c] = -99;

				}

			}

		}

		int l = 0, c = 0;

		int maior = Integer.MIN_VALUE;

		for (int i = 0; i < custos.length; i++) {

			for (int j = 0; j < custos.length; j++) {

				if (custos[i][j] != -99) {

					if (maior < custos[i][j]) {

						maior = custos[i][j];

						l = i;

						c = j;

					}

				}

			}

		}

		tabuleiro[l][c] = Jogador.COMPUTADOR;

	}

}