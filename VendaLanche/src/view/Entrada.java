package view;

import java.util.Locale;
import java.util.Scanner;

public class Entrada {
	private Scanner leitor = new Scanner(System.in);

	public int entradaInt() {
		int entradaInt = 0;

		try {
			System.out.println(">> ");
			entradaInt = leitor.nextInt();
		
		} catch (Exception ex) {
			System.out.println("Aprende a digitar certo...Topera");
		}

		return entradaInt;
	}

	public double entradaDouble() {
		// garante que o formato utilizado sera o norte americano utilizando .
		// para decimais ao inves de virgula
		leitor.useLocale(Locale.US);
		System.out.println(">> ");
		double nextDouble = leitor.nextDouble();
		return nextDouble;
	}

	public String entradaString() {
		String entradaString;
		System.out.println(">> ");
		entradaString = leitor.nextLine();
		return entradaString;
	}

}
