package batalha.model;

import java.util.Random;
import java.util.Scanner;

import batalha.model.entidade.Embarcacao;
import batalha.model.entidade.Tabuleiro;
import batalha.view.BatalhaView;

public class EmbarcacaoModel {

	private Tabuleiro tabuleiro, rascunho;
	private int linha, coluna;
	private Embarcacao portaAviao, destroyer, fragata, submarino, torpedeiro;
	private BatalhaView view;
	private Scanner input;
	private Random random;

	public EmbarcacaoModel() {
		this.tabuleiro = new Tabuleiro();
		this.rascunho = new Tabuleiro();
		this.portaAviao = new Embarcacao(5, 2);
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
		
		geraEmbarcacao(this.submarino);

	}
	
	private void geraEmbarcacao(Embarcacao ship) {
		int[][] embarcacao = new int[ship.getLinha()][ship.getColuna()];

		for (int cont = 0; cont < ship.getEmbarcacao().length; cont++) {
			mountEmbarcacao(embarcacao[cont]);
			
			while (alreadyExistsCoordinate(embarcacao, cont)){
				mountEmbarcacao(embarcacao[cont]);
			}
		}
		ship.setEmbarcacao(embarcacao);
	}


	private boolean alreadyExistsCoordinate(int[][] embarcacao, int shipId) {
		for (int anterior = 0; anterior < shipId; anterior++) {
			boolean alreadyExists = true;
			
			for (int index = 0; index < embarcacao[shipId].length - 1; index++) {
				if (embarcacao[shipId][index] != embarcacao[anterior][index]) {
					alreadyExists = false;
					break;
				}
			}
			
			if (alreadyExists) {
				return true;
			}
		}
		return false;
	}


	private void mountEmbarcacao(int[] embarcacao) {
		for (int cont = 0; cont < embarcacao.length - 1; cont++) {
			embarcacao[cont] = random.nextInt(10);
		}
		embarcacao[embarcacao.length - 1] = 0;
	}

	public boolean testaJogada() {
		int[][] array = this.submarino.getEmbarcacao();
		
		for (int embarcacao = 0; embarcacao < submarino.getEmbarcacao().length; embarcacao++) {
			if (hit(this.tabuleiro.getLinha(), this.tabuleiro.getColuna(), this.submarino.getEmbarcacao()[embarcacao])) {
				
				System.out.printf("Você acertou o tiro.\n");
				
				array[embarcacao][0] = this.submarino.getEmbarcacao()[embarcacao][0];
				array[embarcacao][1] = this.submarino.getEmbarcacao()[embarcacao][1];
				array[embarcacao][2] = 1;
				
				System.out.printf("%d %d %d\n", array[embarcacao][0], array[embarcacao][1], array[embarcacao][2]);
				
				this.tabuleiro.setTiros(this.tabuleiro.getTiros() + 5);
				this.tabuleiro.setAcertos(this.tabuleiro.getAcertos() + 1);
				return true;
			}
			
			System.out.printf("%d %d %d\n", array[embarcacao][0], array[embarcacao][1], array[embarcacao][2]);
		}
		
		this.submarino.setEmbarcacao(array);
		this.tabuleiro.setTiros(this.tabuleiro.getTiros() - 1);
		this.tabuleiro.setErros(this.tabuleiro.getErros() + 1);
		return false;
	}
	
	private boolean hit(int x, int y, int[] ship) {
		return ship[0] == x && ship[1] == y;
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
