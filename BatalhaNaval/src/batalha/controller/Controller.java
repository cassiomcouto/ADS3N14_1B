package batalha.controller;

import batalha.model.Embarcacao;
import batalha.model.Tabuleiro;
import batalha.view.BatalhaView;

import java.util.Random;
import java.util.Scanner;

public class Controller {

	private Tabuleiro tabuleiro, rascunho;
	private int linha, coluna;
	private Embarcacao porta_aviao, destroyer, fragata, submarino,
			torpedeiro;
	private BatalhaView view;
	private Scanner input;
	private Random random;

	public Controller() {
		this.tabuleiro = new Tabuleiro();
		this.rascunho = new Tabuleiro();
		this.porta_aviao = new Embarcacao(5, 2);
		this.destroyer = new Embarcacao(8, 2);
		this.fragata = new Embarcacao(6, 2);
		this.submarino = new Embarcacao(5, 3);
		this.torpedeiro = new Embarcacao(6, 2);
                
		this.view = new BatalhaView();
		this.input = new Scanner(System.in);
		random = new Random();

	}


	public int[][] iniciaJogo() {
		int[][] array = new int[10][10];

		for (linha = 0; linha < tabuleiro.getTabuleiro().length; linha++) {

			for (coluna = 0; coluna < tabuleiro.getTabuleiro()[linha].length; coluna++) {
				array[linha][coluna] = -1;
			}

		}
		this.tabuleiro.setTabuleiro(array);
		return this.tabuleiro.getTabuleiro();
	}

	public void jogada() {
		System.out.println("Informe o número da linha:\n");
		tabuleiro.setLinha(input.nextInt());
		System.out.println("Informe a letra da coluna:\n");
		tabuleiro.setLetras(input.next());
		for (int teste = 0; teste < tabuleiro.getColun().length; teste++) {
			String letra = this.tabuleiro.getColun()[teste];
			if (letra.equalsIgnoreCase(this.tabuleiro.getLetra())) {
				this.tabuleiro.setColuna(teste);
			}
		}

	}


	public void geraEmbarcacao() {

		int[][] embarcacao = new int[this.submarino.getLinha()][this.submarino
				.getColuna()];

		for (int cont = 0; cont < this.submarino.getEmbarcacao().length; cont++) {
			embarcacao[cont][0] = random.nextInt(10);
			embarcacao[cont][1] = random.nextInt(10);
			embarcacao[cont][2] = 0;

			for (int anterior = 0; anterior < cont; anterior++) {
				if ((embarcacao[cont][0] == embarcacao[anterior][0])
						&& (embarcacao[cont][1] == embarcacao[anterior][1]))
					do {
						embarcacao[cont][0] = random.nextInt(10);
						embarcacao[cont][1] = random.nextInt(10);
					} while ((embarcacao[cont][0] == embarcacao[anterior][0])
							&& (embarcacao[cont][1] == embarcacao[anterior][1]));
			}

		}
		this.submarino.setEmbarcacao(embarcacao);

	}

	public boolean testaJogada() {
		int[][] array = this.submarino.getEmbarcacao();
		for (int embarcacao = 0; embarcacao < submarino.getEmbarcacao().length; embarcacao++) {
			if (this.tabuleiro.getLinha() == this.submarino.getEmbarcacao()[embarcacao][0]
					&& this.tabuleiro.getColuna() == this.submarino
							.getEmbarcacao()[embarcacao][1]) {
				System.out.printf("Você acertou o tiro.\n");
				array[embarcacao][0] = this.submarino.getEmbarcacao()[embarcacao][0];
				array[embarcacao][1] = this.submarino.getEmbarcacao()[embarcacao][1];
				array[embarcacao][2] = 1;
				System.out.printf("%d %d %d\n", array[embarcacao][0],
						array[embarcacao][1], array[embarcacao][2]);
				this.tabuleiro.setTiros(this.tabuleiro.getTiros() + 5);
				this.tabuleiro.setAcertos(this.tabuleiro.getAcertos() + 1);
				return true;
			}
			System.out.printf("%d %d %d\n", array[embarcacao][0],
					array[embarcacao][1], array[embarcacao][2]);
		}
		this.submarino.setEmbarcacao(array);
		this.tabuleiro.setTiros(this.tabuleiro.getTiros() - 1);
		this.tabuleiro.setErros(this.tabuleiro.getErros() + 1);
		return false;
	}


	public boolean testaFim() {
		boolean valida = false;
		int cont = 0;
		int[][] embarcacao = this.submarino.getEmbarcacao();
		for (int linha = 0; linha < this.submarino.getEmbarcacao().length; linha++) {
			if (embarcacao[linha][2] == 1) {
				cont = cont + 1;
			}
		}
		if (this.submarino.getLinha() == cont) {
			valida = true;
		}

		return valida;

	}


	public int acerto() {
		return this.tabuleiro.getAcertos();
	}

	public int erros() {
		return this.tabuleiro.getErros();
	}


	public int tiros() {
		return this.tabuleiro.getTiros();
	}


	public int[][] atualizaTabuleiro(boolean retorno) {
		int[][] tabuleiro = new int[10][10];

		if (retorno) {
			for (int linha = 0; linha < this.tabuleiro.getTabuleiro().length; linha++) {
				for (int coluna = 0; coluna < this.tabuleiro.getTabuleiro()[linha].length; coluna++) {
					if (linha == this.tabuleiro.getLinha()
							&& coluna == this.tabuleiro.getColuna()) {
						tabuleiro[this.tabuleiro.getLinha()][this.tabuleiro
								.getColuna()] = 1;
					} else {
						tabuleiro[linha][coluna] = this.tabuleiro
								.getTabuleiro()[linha][coluna];
					}
				}

			}
			this.tabuleiro.setTabuleiro(tabuleiro);
		} else {
			for (int linha = 0; linha < this.tabuleiro.getTabuleiro().length; linha++) {
				for (int coluna = 0; coluna < this.tabuleiro.getTabuleiro()[linha].length; coluna++) {
					if (linha == this.tabuleiro.getLinha()
							&& coluna == this.tabuleiro.getColuna()) {
						tabuleiro[this.tabuleiro.getLinha()][this.tabuleiro
								.getColuna()] = 0;
					} else {
						tabuleiro[linha][coluna] = this.tabuleiro
								.getTabuleiro()[linha][coluna];
					}

				}

			}
			this.tabuleiro.setTabuleiro(tabuleiro);
		}
		return this.tabuleiro.getTabuleiro();
	}
}
