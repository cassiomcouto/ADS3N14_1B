package view;

import java.util.List;

import Model.Venda;
import db.Repositorio;

public class VendasView implements View<Venda>{
	
	@Override
	public void exibirCadastro(Repositorio<Venda> repositorio) {
		List<Venda> lista = repositorio.getLista();
		System.out.println("Vendas cadastradas");
		for (Venda venda: lista) {
			System.out.println(venda);
		}
	}

	@Override
	public Venda lerEntradaCadastro() {
		return null;
	}
	
	public Integer lerQuantidadeProdutos(){
		Entrada entrada = new Entrada();				
		System.out.println("Quantidade");
		Integer quantidade= entrada.entradaInt();
		return quantidade;
	}

	@Override
	public Venda lerEntradaPesquisa() {
		Entrada entrada = new Entrada();
		System.out.println("Pesquisar venda");		
		System.out.println("Id da venda");
		Integer id =entrada.entradaInt();
		Venda venda = new Venda();
		venda.setId(id);
		return venda;
	}

	@Override
	public Venda lerEntradaRemocao() {
		Entrada entrada = new Entrada();
		System.out.println("Remover venda");		
		System.out.println("Id da venda");
		Integer id =entrada.entradaInt();
		Venda venda = new Venda();
		venda.setId(id);
		return venda;
	}



	
	
	
}
