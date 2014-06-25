package controller;

import java.util.List;

import db.Repositorio;
import view.ClientesView;
import view.FuncionariosView;
import view.ProdutosView;
import view.VendasView;
import view.View;
import Model.Cliente;
import Model.Funcionario;
import Model.Produto;
import Model.Venda;

public class ControllerVenda extends ControllerBase<Venda> {

	private Repositorio<Cliente> repositorioClientes;
	private Repositorio<Funcionario> repositorioFuncionarios;
	private Repositorio<Produto> repositorioProdutos;
	
	public ControllerVenda(Repositorio<Cliente> clientes, Repositorio<Funcionario> funcionarios, Repositorio<Produto> produtos) {
		super();
		this.repositorioClientes = clientes;
		this.repositorioFuncionarios = funcionarios;
		this.repositorioProdutos = produtos;
	}

	public ControllerVenda() {		
	}
	
	@Override
	public void remover(){		
		getRepositorio().remover();
	}
	
	@Override
	protected Venda lerEntradaCadastro() {		
		Cliente clienteCadastrado = lerCliente();
		Funcionario atendenteCadastrado = lerFuncionario();
		Produto produtoCadastrado = lerProduto();
		Venda venda = new Venda(proximoId(), clienteCadastrado, atendenteCadastrado);
		int quantidade = new VendasView().lerQuantidadeProdutos();
		venda.adicionarPedido(produtoCadastrado, quantidade);
		return venda;
	}
	
	private Funcionario lerFuncionario() {
		FuncionariosView funcionariosView = new FuncionariosView();
		Funcionario funcionarioPesquisado = funcionariosView.lerEntradaPesquisa();
		return repositorioFuncionarios.pesquisar(funcionarioPesquisado);
	}

	private Cliente lerCliente() {
		ClientesView clientesView = new ClientesView();
		Cliente clientePesquisado = clientesView.lerEntradaPesquisa();
		return repositorioClientes.pesquisar(clientePesquisado);
	}

	private Produto lerProduto(){
		ProdutosView produtosView = new ProdutosView();
		Produto produtoPesquisado = produtosView.lerEntradaPesquisa();
		return repositorioProdutos.pesquisar(produtoPesquisado);
	}
	
	@Override
	protected Venda lerEntradaRemocao() {		
		Venda venda = super.lerEntradaRemocao();
		venda = pesquisarPorId(venda.getId());
		return venda;
	}
	
	@Override
	protected Venda lerEntradaPesquisa() {		
		Venda venda = super.lerEntradaPesquisa();
		venda = pesquisarPorId(venda.getId());
		return venda;
	}

	private Venda pesquisarPorId(Integer id) {
		List<Venda> vendas = getRepositorio().getLista();
		Venda vendaEncontrada = null;
		for (Venda venda : vendas) {
			if((venda!=null) && (venda.getId() == id) ){
				vendaEncontrada = venda;
			}
		}
		return vendaEncontrada;
	}

	@Override
	protected View<Venda> getView() {		
		return new VendasView();
	}
	

	

}
