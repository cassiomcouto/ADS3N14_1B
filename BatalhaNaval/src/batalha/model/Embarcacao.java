package batalha.model;

public class Embarcacao {

	int embarcacao[][];
	int linha, coluna;

	public Embarcacao(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
		embarcacao = new int[linha][coluna];
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public int[][] getEmbarcacao() {
		return embarcacao;
	}

	public void setEmbarcacao(int[][] embarcacao) {
		this.embarcacao = embarcacao;
	}

}
