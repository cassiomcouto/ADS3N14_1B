package controller;

import Model.Cliente;
import Model.Funcionario;
import Model.Produto;
import view.Entrada;
import view.Menu;
import view.Menu.OpcaoMenu;
import view.Menu.OpcaoSubMenu;

public class Principal {

	private ControllerCliente controllerClientes = new ControllerCliente();
	private ControllerFuncionario controllerFuncionarios = new ControllerFuncionario();
	private ControllerProduto controllerProduto = new ControllerProduto();
	private ControllerVenda controllerVenda = new ControllerVenda(
			controllerClientes.getRepositorio(),
			controllerFuncionarios.getRepositorio(),
			controllerProduto.getRepositorio());

	public static void main(String[] args) {
		Principal principal = new Principal();
		principal.efetuarCargaInicial();
		principal.executar();
	}

	// Menu Principal
	public void executar() {
		Menu.OpcaoMenu opcao = OpcaoMenu.PRINCIPAL;
		Menu menu = new Menu();
		menu.exibirMenu(opcao);
		opcao = lerEntradaMenu();
		while (opcao != OpcaoMenu.SAIR) {
			menu.exibirMenu(opcao);
			OpcaoSubMenu opcaoSubMenu = lerEntradaSubMenu();
			if (opcaoSubMenu != OpcaoSubMenu.SAIR) {
				executarOperacao(opcao, opcaoSubMenu);
			} else {
				menu.exibirMenu(OpcaoMenu.PRINCIPAL);
				opcao = lerEntradaMenu();
			}
		}
	}

	// SubMenu
	private void executarOperacao(OpcaoMenu menu, OpcaoSubMenu submenu) {
		ControllerBase controller = getController(menu);
		if (controller != null) {
			switch (submenu) {
			case CADASTRO:
				controller.cadastrar();
				break;
			case LISTAGEM:
				controller.imprimirLista();
				break;
			case PESQUISA:
				controller.pesquisar();
				break;
			case EXCLUSAO:
				controller.remover();
				break;
			default:
				break;
			}
		}
	}

	// Switch Menu
	private ControllerBase getController(OpcaoMenu menu) {
		ControllerBase controller;
		switch (menu) {
		case EXIBIR_MENU_CLIENTES:
			controller = controllerClientes;
			break;
		case EXIBIR_MENU_FUNCIONARIOS:
			controller = controllerFuncionarios;
			break;
		case EXIBIR_MENU_PRODUTOS:
			controller = controllerProduto;
			break;
		case EXIBIR_MENU_VENDAS:
			controller = controllerVenda;
			break;
		default:
			controller = null;
		}
		return controller;
	}

	// Entrada Menu
	private OpcaoMenu lerEntradaMenu() {
		Entrada entrada = new Entrada();

		int entradaInt = entrada.entradaInt();
		OpcaoMenu menu = OpcaoMenu.valueOf(entradaInt);
		return menu;

	}

	// Entrada SubMenu
	private OpcaoSubMenu lerEntradaSubMenu() {
		Entrada entrada = new Entrada();
		int entradaInt = entrada.entradaInt();
		OpcaoSubMenu menu = OpcaoSubMenu.valueOf(entradaInt);
		return menu;
	}

	// efetua a carga inicial de : Clientes, Funcionarios e Produto
	private void efetuarCargaInicial() {
		for (int i = 0; i < 10; i++) {
			Cliente cliente = new Cliente();
			cliente.setId(i);
			cliente.setTelefone(90951700 + i);
			cliente.setNome("Cliente " + i);
			controllerClientes.cadastrar(cliente);

			Funcionario funcionario = new Funcionario();
			funcionario.setNome("Funcionario " + i);
			funcionario.setTelefone(80951700 + i);
			funcionario.setSalario(i + 600);
			funcionario.setCargo("Vendedor");
			controllerFuncionarios.cadastrar(funcionario);

			Produto produto = new Produto();
			produto.setPreco(i + 12.54);
			produto.setNome("Produto " + i);
			controllerProduto.cadastrar(produto);
		}
	}

}
