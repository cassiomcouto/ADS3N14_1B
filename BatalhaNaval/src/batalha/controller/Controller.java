package batalha.controller;

import batalha.model.EmbarcacaoModel;
import batalha.view.BatalhaView;

public class Controller {

	public static void main(String[] args) {
		BatalhaView view = new BatalhaView();
		EmbarcacaoModel model = new EmbarcacaoModel();

		view.imprimeTabuleiro(model.iniciaJogo());
		model.geraEmbarcacao();
		do {
			model.jogada();
			
			view.imprimeTabuleiro(model.atualizaTabuleiro(model.testaJogada()));

			System.out.println(model.testaFim());

			view.imprimeDisparos(model.tiros());
			view.imprimeResultado(model.acerto(), model.erros(), model.acerto() + model.erros());
		} while (model.tiros() > 0 && model.testaFim() == false);

		System.out.println("Fim do jogo!");

	}

}
