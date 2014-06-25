package view;

public class Menu {
	
	public enum OpcaoSubMenu{
		SAIR(0), CADASTRO(1), LISTAGEM(2), 
		PESQUISA(3), EXCLUSAO(4);		
		OpcaoSubMenu(int codigo){
			this.codigo=codigo;
		}		
		public static OpcaoSubMenu valueOf(int codigo){
			OpcaoSubMenu opcaoRetorno = null;
			OpcaoSubMenu[] values = OpcaoSubMenu.values();
			for (OpcaoSubMenu opcao : values) {
				if( opcao.codigo == codigo ){
					opcaoRetorno = OpcaoSubMenu.valueOf(opcao.name());
				}
			}
			return opcaoRetorno;			
		}		
		private int codigo;	
	}
	
	public enum OpcaoMenu{
		SAIR(0), EXIBIR_MENU_CLIENTES(1), 
		EXIBIR_MENU_FUNCIONARIOS(2), EXIBIR_MENU_PRODUTOS(3),
		EXIBIR_MENU_VENDAS(4), PRINCIPAL(-1);		
		OpcaoMenu(int codigo){
			this.codigo=codigo;
		}
		
		public static OpcaoMenu valueOf(int codigo){
			OpcaoMenu opcaoRetorno = null;
			OpcaoMenu[] values = OpcaoMenu.values();
			for (OpcaoMenu opcao : values) {
				if( opcao.codigo == codigo ){
					opcaoRetorno = OpcaoMenu.valueOf(opcao.name());
				}
			}
			return opcaoRetorno;			
		}		
		private int codigo;		
		
	}
	
	public void exibirMenu(OpcaoMenu menu){
		if(menu == OpcaoMenu.EXIBIR_MENU_CLIENTES){
			exibirMenuClientes();
		}
		else if(menu == OpcaoMenu.EXIBIR_MENU_FUNCIONARIOS){
			exibirMenuFuncionarios();
		}
		else if(menu == OpcaoMenu.EXIBIR_MENU_PRODUTOS){
			exibirMenuProdutos();
		}
		else if(menu == OpcaoMenu.EXIBIR_MENU_VENDAS){
			exibirMenuVenda();
		}	
		else{
			exibirMenuPrincipal();
		}
	}
	
	private void clearScreen(){
		//Limpa tela
		System.out.flush();
	}
	
	public void exibirMenuPrincipal(){
		clearScreen();
		System.out.println("\n########################### ");
		System.out.println("Menu: ");
		System.out.println("1. Cadastro de Clientes");
		System.out.println("2. Cadastro de Funcionarios");
		System.out.println("3. Cadastro de Produtos");
		System.out.println("4. Vendas");
		System.out.println("0. Sair");
	}
	
	public void exibirMenuClientes(){
		clearScreen();
		System.out.println("########################### ");
		System.out.println("Cadastro de Clientes: ");
		System.out.println("1. Cadastrar novo Cliente");
		System.out.println("2. Listar Clientes");
		System.out.println("3. Pesquisar Cliente");
		System.out.println("4. Excluir Cliente");
		System.out.println("0. Sair");
	}
	
	public void exibirMenuFuncionarios(){
		clearScreen();
		System.out.println("########################### ");
		System.out.println("Cadastro de Funcionarios: ");
		System.out.println("1. Cadastrar novo Funcionario");
		System.out.println("2. Listar Funcionarios");
		System.out.println("3. Pesquisar Funcionario");
		System.out.println("4. Excluir Funcionario");
		System.out.println("0. Sair");
	}
	
	public void exibirMenuProdutos(){
		clearScreen();
		System.out.println("########################### ");
		System.out.println("Cadastro de Produtos: ");
		System.out.println("1. Cadastrar novo Produto");
		System.out.println("2. Listar Produtos");
		System.out.println("3. Pesquisar Produto");
		System.out.println("4. Excluir Produto");
		System.out.println("0. Sair");
	}
	
	public void exibirMenuVenda(){
		clearScreen();
		System.out.println("########################### ");
		System.out.println("Vendas: ");
		System.out.println("1. Efetuar nova Venda");
		System.out.println("2. Listar Vendas");
		System.out.println("3. Pesquisar Vendas");
		System.out.println("4. Excluir Venda");
		System.out.println("0. Sair");
	}		

}
