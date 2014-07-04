package view;

import java.util.Scanner;

public class Erro {

	public int scannerInt(Scanner entrada) {
		int opcao;
		opcao = entrada.nextInt();

		return opcao;
	}

	public String scannerString(Scanner entrada) {
		String string;

		string = entrada.next();

		return string;
	}

}
