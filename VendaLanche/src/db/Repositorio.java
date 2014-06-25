package db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Repositorio<T> {

	private List<T> lista = new ArrayList<T>();
	
	public void adicionar(T item){
		lista.add(item);
	}
	
	//Metodo para remover Cliente,Produto e Pedido.
	public void remover(T item){
		lista.remove(item);
	}
	
	public Integer getQuantidade(){
		return lista.size();
	} 
	
	
	public List<T> getLista(){
		return Collections.unmodifiableList(lista);
	} 
	
	public T pesquisar(T item){
		T itemEncontrado = null;
		if( lista.contains(item) ){
			int index = lista.indexOf(item);
			if(index >= 0){
				itemEncontrado = lista.get(index);
			} 
		}
		return itemEncontrado;
	}
	
	//Remove as vendas da Fila
	public void remover() {
		lista.remove(0);
		
	}
	
	
}
