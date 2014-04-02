package batalha.controller;

import batalha.view.BatalhaView;

public class Principal {

	public static void main(String[] args) {
		BatalhaView view = new BatalhaView();
		Controller controller = new Controller();

		view.imprimeTabuleiro(controller.iniciaJogo());
		controller.geraEmbarcacao();
		do {
			controller.jogada();
			view.imprimeTabuleiro(controller.atualizaTabuleiro(controller
					.testaJogada()));

			System.out.println(controller.testaFim());

			view.imprimeDisparos(controller.tiros());
			view.imprimeResultado(controller.acerto(), controller.erros(),
					controller.acerto() + controller.erros());
		} while (controller.tiros() > 0 && controller.testaFim() == false);

		System.out.println("Fim do jogo!");

	}

}
