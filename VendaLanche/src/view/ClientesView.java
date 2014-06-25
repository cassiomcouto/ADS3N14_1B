package view;

import java.util.List;

import db.Repositorio;
import Model.Cliente;

public class ClientesView implements View<Cliente>{
	
	@Override
	public Cliente lerEntradaCadastro() {
		Cliente cliente = new Cliente();		
		Entrada entrada = new Entrada();
		System.out.println("CADASTRAR CLIENTE");
		System.out.print("Nome:");
		cliente.setNome(entrada.entradaString());
		System.out.print("Telefone:");
		cliente.setTelefone(entrada.entradaInt());
		return cliente;
	}
	
	@Override
	public Cliente lerEntradaPesquisa() {
		Cliente cliente = new Cliente();		
		Entrada entrada = new Entrada();
		System.out.println("Pesquisar CLIENTE");
		System.out.print("Nome:");
		cliente.setNome(entrada.entradaString());
		return cliente;
	}
	
	@Override
	public Cliente lerEntradaRemocao() {
		Cliente cliente = new Cliente();		
		Entrada entrada = new Entrada();
		System.out.println("Remover CLIENTE");
		System.out.print("Nome:");
		cliente.setNome(entrada.entradaString());
		return cliente;
	}
	
	
	@Override
	public void exibirCadastro(Repositorio<Cliente> repositorio) {
		List<Cliente> lista = repositorio.getLista();
		System.out.println("Clientes cadastrados");
		for (Cliente cliente : lista) {
			System.out.println(cliente);
		}
	}

	
	
	
}
