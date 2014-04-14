package batalha.controller;

<<<<<<< HEAD
import java.util.Scanner;

=======
>>>>>>> 40d74f39c3341d55fbd5c300a60bdb1982eb31c9
import batalha.model.EmbarcacaoModel;
import batalha.view.BatalhaView;

public class Controller {
<<<<<<< HEAD
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		final Scanner scanner = new Scanner(System.in);
		
		BatalhaView view = new BatalhaView();
		EmbarcacaoModel model;
		try {
			model = new EmbarcacaoModel();
			
			view.imprimeTabuleiro(model.getBoard());
			do {
				System.out.println("Informe o número da linha:\n");
				final int line = scanner.nextInt();
				System.out.println("Informe a letra da coluna:\n");
				final String column = scanner.next();
				
				if (model.testaJogada(line, column)) {
					System.out.printf("Você acertou o tiro. \n");
					System.out.printf("%s \n", model.getLastMove().toString());
				}
				
				view.imprimeTabuleiro(model.atualizaTabuleiro());

				view.imprimeDisparos(model.shots());
				view.imprimeResultado(model.hits(), model.errors(), model.hits() + model.errors());
			} while (model.shots() > 0 && model.testaFim() == false);

			System.out.println("Fim do jogo!");
		} catch (Exception e) {
			e.printStackTrace();
		}
=======

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

>>>>>>> 40d74f39c3341d55fbd5c300a60bdb1982eb31c9
	}

}
