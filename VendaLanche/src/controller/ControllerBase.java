package controller;

import java.util.List;

import view.View;
import db.Repositorio;

public abstract class ControllerBase<T> {

	private Repositorio<T> repositorio = new Repositorio<T>();
				
	public void cadastrar(){
		T item = lerEntradaCadastro();
		cadastrar(item);
	}
	
	public void cadastrar(T item){		
		getRepositorio().adicionar(item);
	}
	
	public void remover(){
		T item = lerEntradaRemocao();
		getRepositorio().remover(item);
	}
	
	public Integer proximoId(){
		return getRepositorio().getQuantidade() + 1;
	}
	
	protected abstract View<T> getView();
	
	public void imprimirQuantidade(){
		System.out.println("Quantidade "+getRepositorio().getQuantidade());
	}
	
	public void pesquisar(){
		T item = lerEntradaPesquisa();
		T itemEncontrado = getRepositorio().pesquisar(item);
		if(itemEncontrado == null){
			System.out.println("Não encontrado");
		}
		else{
			System.out.println(itemEncontrado);
		}
		
	}
	
	public void imprimirLista(){
		List<T> lista = getRepositorio().getLista();
		for (T item : lista) {
			System.out.println(formatar(item));
		}
	}
	
	protected String formatar(T item){
		return item.toString();
	}

	public Repositorio<T> getRepositorio() {
		return repositorio;
	}
	
	protected T lerEntradaPesquisa() {		
		return getView().lerEntradaPesquisa();
	}
	
	protected T lerEntradaRemocao() {		
		return getView().lerEntradaRemocao();
	}
	
	protected T lerEntradaCadastro() {		
		return getView().lerEntradaCadastro();
	}

	
	
	
}
