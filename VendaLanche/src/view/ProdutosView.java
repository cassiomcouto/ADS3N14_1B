package view;

import java.util.List;

import Model.Produto;
import db.Repositorio;

public class ProdutosView implements View<Produto>{
	
	@Override
	public void exibirCadastro(Repositorio<Produto> repositorio) {
		List<Produto> lista = repositorio.getLista();
		System.out.println("Produtos cadastrados");
		for (Produto produto: lista) {
			System.out.println(produto);
		}
	}

	@Override
	public Produto lerEntradaCadastro() {
		Produto produto = new Produto();
		Entrada entrada = new Entrada();
		System.out.println("CADASTRAR Produto");
		System.out.print("Nome:");
		produto.setNome(entrada.entradaString());
		System.out.print("Preco:");
		produto.setPreco(entrada.entradaDouble());
		return produto;
	}

	@Override
	public Produto lerEntradaPesquisa() {
		Produto produto = new Produto();
		Entrada entrada = new Entrada();
		System.out.println("Pesquisar Produto");
		System.out.print("Nome:");
		produto.setNome(entrada.entradaString());
		return produto;
	}

	@Override
	public Produto lerEntradaRemocao() {
		Produto produto = new Produto();
		Entrada entrada = new Entrada();
		System.out.println("Remover Produto");
		System.out.print("Nome:");
		produto.setNome(entrada.entradaString());
		return produto;
	}

	
	
	
}
