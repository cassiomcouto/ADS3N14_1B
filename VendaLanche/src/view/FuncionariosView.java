package view;

import java.util.List;

import Model.Funcionario;
import db.Repositorio;

public class FuncionariosView implements View<Funcionario>{
	
	@Override
	public Funcionario lerEntradaCadastro() {
		Funcionario funcionario = new Funcionario();		
		// Funcionario.setFichaAtendimento(listarFuncionario.quantidadeFuncionario()+1);
		Entrada entrada = new Entrada();
		System.out.println("CADASTRAR FUNCIONARIO");
		System.out.print("Nome:");
		funcionario.setNome(entrada.entradaString());
		System.out.print("Telefone:");
		funcionario.setTelefone(entrada.entradaInt());
		System.out.print("Salario:");
		funcionario.setSalario(entrada.entradaInt());
		System.out.print("Cargo:");
		funcionario.setCargo(entrada.entradaString());
		return funcionario;
	}

	@Override
	public Funcionario lerEntradaPesquisa() {
		Funcionario funcionario = new Funcionario();
		// Funcionario.setFichaAtendimento(listarFuncionario.quantidadeFuncionario()+1);
		Entrada entrada = new Entrada();
		System.out.println("Pesquisar FUNCIONARIO");
		System.out.print("Nome:");
		funcionario.setNome(entrada.entradaString());		
		return funcionario;
	}

	@Override
	public Funcionario lerEntradaRemocao() {
		Funcionario funcionario = new Funcionario();
		// Funcionario.setFichaAtendimento(listarFuncionario.quantidadeFuncionario()+1);
		Entrada entrada = new Entrada();
		System.out.println("Remover FUNCIONARIO");
		System.out.print("Nome:");
		funcionario.setNome(entrada.entradaString());		
		return funcionario;
	}

	@Override
	public void exibirCadastro(Repositorio<Funcionario> repositorio) {
		List<Funcionario> lista = repositorio.getLista();
		System.out.println("Funcionarios cadastrados");
		for (Funcionario funcionario: lista) {
			System.out.println(funcionario);
		}
	}

	
	
	
}
