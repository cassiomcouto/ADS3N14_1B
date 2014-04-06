package batalha.model.entidade;

public class Tabuleiro {
	private int linha, coluna, acertos, erros, tiros;
	private String[] colun = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
	private String letra;
	private int[][] tabuleiro;

	public Tabuleiro() {
		linha = 0;
		coluna = 0;
		acertos = 0;
		erros = 0;
		tiros = 15;
		letra = null;
		tabuleiro = new int[10][10];
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public String[] getColun() {
		return colun;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public int getAcertos() {
		return acertos;
	}

	public void setAcertos(int acertos) {
		this.acertos = acertos;
	}

	public int getErros() {
		return erros;
	}

	public void setErros(int erros) {
		this.erros = erros;
	}

	public int getTiros() {
		return tiros;
	}

	public void setTiros(int tiros) {
		this.tiros = tiros;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetras(String letra) {
		this.letra = letra;
	}

	public void setTabuleiro(int[][] tabuleiro) {
		this.tabuleiro = tabuleiro;

	}

	public int[][] getTabuleiro() {
		return tabuleiro;
	}

}
