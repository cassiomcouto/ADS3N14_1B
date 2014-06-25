package view;

import db.Repositorio;

//Implementa Classes: ClienteView, FuncionariosView,ProdutosView
public interface View<T> {	
	T lerEntradaCadastro() ;	
	T lerEntradaPesquisa();	
	T lerEntradaRemocao() ;
	void exibirCadastro(Repositorio<T> repositorio);
	
}
