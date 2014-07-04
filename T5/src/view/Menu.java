package view;

public class Menu {

	String mensagem;

	public Menu() {
		this.mensagem = "";
	}

	public void menuInicial() {

		mensagem = "Lista de Contatos:\n"

		+ "Selecione uma opcao:\n" + "1 - Inserir \n" + "2 - Remover \n"
				+ "3 - Consultar \n" + "4 - travessia e buscas.\n"
				+ "5 - Sair.";

		System.out.println(mensagem);
	}

	public void menuInserir() {

		mensagem = "Inserir Contato:" + "Digite o nome do contato "
				+ "para inserir:\n";

		System.out.println(mensagem);
	}

	public void menuInserirTelefone() {

		mensagem = "Digite o telefone do contato " + "que deseja inserir:\n";

		System.out.println(mensagem);
	}

	public void tipoTravessia() {

		System.out
				.println("Escolha qual tipo de travessia ou  busca deseja efetuar:\n"
						+

						"1 - Infixa."
						+ "2 - Prefixa.\n"
						+ "3 - Posfixa.\n"
						+ "4 - Busca em largura+\n"
						+ "5 - Busca em profundidade.");

	}

}
