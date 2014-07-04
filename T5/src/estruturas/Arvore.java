package estruturas;

import java.util.ArrayList;

public class Arvore {


	private Nodo raiz;

	public Arvore() {
		raiz = null;
	}
	

	public boolean ehFolha(Nodo nodo) {
		if((nodo.getLeftNodo() == null) && (nodo.getRightNodo() == null))
			return true;
		return false;
	}
	
	public void insere(Nodo nodo) {
		if (raiz == null) {
			raiz = nodo;
		} else {
			insere(raiz, nodo);
		}
	}
	
	private void insere(Nodo arvore, Nodo nodo) {
		
		
		if (raiz == null) {
			arvore = nodo;
		
		} else if (nodo.getValor().compareToIgnoreCase(arvore.getValor()) < 0) {
	
			if (arvore.getLeftNodo() == null)
				arvore.setLeftNodo(nodo);
			else
				insere(arvore.getLeftNodo(),nodo);
		} else {

			if (arvore.getRightNodo() == null) 
				arvore.setRightNodo(nodo);
			else
				insere(arvore.getRightNodo(),nodo);
		}
	}
	
	public Nodo getRaiz() {
		return raiz;
	}
	
	public ArrayList<Nodo> buscaLargura() {
		ArrayList<Nodo> lista = new ArrayList<Nodo>();
		ArrayList<Nodo> saida = new ArrayList<Nodo>();
		Nodo nodo = raiz;
		
		if (raiz == null) {
			return saida;
		} else {
			do {
				if(nodo == null) {
				
					nodo = lista.remove(0);
				}
				
				saida.add(nodo);
				
				if (nodo.getLeftNodo() != null) 
					lista.add(nodo.getLeftNodo());
				if (nodo.getRightNodo() != null) 
					lista.add(nodo.getRightNodo());
				
				nodo = null;
			} while (!lista.isEmpty());
		}
		
		
		return saida;
	}
	
	public ArrayList<Nodo> travessiaInfixa() {
		ArrayList<Nodo> lista = new ArrayList<Nodo>();
		
		if (raiz == null)
			return lista;
		
		travessiaInfixa(raiz, lista);
		
		return lista;
	}
	
	private ArrayList<Nodo> travessiaInfixa(Nodo nodo, ArrayList<Nodo> lista) {
	
		if (nodo == null)
			return lista;
		
		lista.add(nodo);
		travessiaInfixa(nodo.getLeftNodo(), lista);
		travessiaInfixa(nodo.getRightNodo(), lista);
		return lista;
	}
	
	public ArrayList<Nodo> travessiaPrefixa() {
		
		ArrayList<Nodo> lista = new ArrayList<Nodo>();
		
		if (raiz == null)
			return lista;
		
		travessiaPrefixa(raiz, lista);
		
		return lista;
		
	}
	
	private ArrayList<Nodo> travessiaPrefixa(Nodo nodo, ArrayList<Nodo> lista) {
		
		if (nodo == null)
			return lista;
		
		travessiaPrefixa(nodo.getLeftNodo(), lista);
		lista.add(nodo);
		travessiaPrefixa(nodo.getRightNodo(), lista);
		
		return lista;
	}
	
	
	public ArrayList<Nodo> travessiaPosfixa() {
		
		ArrayList<Nodo> lista = new ArrayList<Nodo>();
		
		if (raiz == null)
			return lista;
		
		travessiaPosfixa(raiz, lista);
		
		return lista;
		
	}
	
	private ArrayList<Nodo> travessiaPosfixa(Nodo nodo, ArrayList<Nodo> lista) {
		
		if (nodo == null)
			return lista;
		
		travessiaPosfixa(nodo.getRightNodo(), lista);
		travessiaPosfixa(nodo.getLeftNodo(), lista);
		lista.add(nodo);
		
		return lista;
	}
	
	public ArrayList<Nodo> buscaProfundidade() {
		
		ArrayList<Nodo> pilha = new ArrayList<Nodo>();
		ArrayList<Nodo> saida = new ArrayList<Nodo>();
		Nodo nodo;
		pilha.add(raiz);
		while(!pilha.isEmpty()) {
			nodo = pilha.remove((pilha.size()-1));
			
			saida.add(nodo);
			if (nodo.getLeftNodo() != null)
				pilha.add(nodo.getLeftNodo());
			if (nodo.getRightNodo() != null)
				pilha.add(nodo.getRightNodo());
		}
		
		return saida;
	}
	
	public Nodo buscaNodo(String valor) {
		
		Nodo nodo = buscaNodo(valor, raiz);
		return nodo;
		
	}
	
	private Nodo buscaNodo(String valor, Nodo nodo) {
		
		if (nodo == null) {
			return nodo;
		} else if (nodo.getValor().equalsIgnoreCase(valor) || nodo == null) {
			return nodo;
		} else if (valor.compareToIgnoreCase(nodo.getValor()) < 0) {
			return nodo = buscaNodo(valor, nodo.getLeftNodo());
		} else {
			return nodo = buscaNodo(valor, nodo.getRightNodo());
		}
	}
	
	public Nodo buscaPai(String valor) {
		if (raiz == null)
			return null;
		else if (raiz.getValor().equalsIgnoreCase(valor))
			return null;
		else
			return buscaPai(valor, raiz);
	}
	
	private Nodo buscaPai(String valor, Nodo nodo) {
		
		if (nodo == null)
			return null;
		else if (valor.compareToIgnoreCase(nodo.getValor()) < 0) {
			if (nodo.getLeftNodo() == null)
				return null;
			else if (valor.equalsIgnoreCase(nodo.getLeftNodo().getValor()))
				return nodo;
			else
				return buscaPai(valor, nodo.getLeftNodo());
		} else if (valor.compareToIgnoreCase(nodo.getValor()) > 0) {
			if (nodo.getRightNodo() == null) 
				return null;
			else if (valor.equalsIgnoreCase(nodo.getRightNodo().getValor()))
				return nodo;
			else
				return nodo;
		}
		return nodo;
	}
	
	public boolean removeNodo(String valor) {
		Nodo nodo = buscaNodo(valor);
		Nodo pai = buscaPai(valor);
		
		if (nodo == null)
			return false;
		
		if (ehFolha(nodo)) {
			removeFolha(pai, nodo);
		} else if (nodo.getRightNodo() == null) {
			removeFilhoUnicoEsquerda(pai, nodo);
		} else if (nodo.getLeftNodo() == null) {
			removeFilhoUnicoDireita(pai, nodo);
		} else {
			//possui dois filhos
			Nodo auxiliar = LeftDeapthSearch(nodo.getRightNodo());
			auxiliar.setLeftNodo(nodo.getLeftNodo());
			//se pai for nulo elemento eh raiz
			if (pai == null)
				raiz = auxiliar;
			else
				pai.setRightNodo(auxiliar);
		}
		return true;
		
	}//fim remove nodo
	
	private void removeFolha(Nodo pai, Nodo nodo) {
		if (nodo == raiz) {
			raiz = null;
		} else if (pai.getLeftNodo() == nodo) {
			pai.setLeftNodo(null);
		} else if (pai.getRightNodo() == nodo) {
			pai.setRightNodo(null);
		} else {
			//nao localizou o pai
			System.out.println("Referencia ao pai não existe!");
		}
	}
	
	private void removeFilhoUnicoEsquerda(Nodo pai, Nodo nodo) {
		//so possui um filho a esquerda
		if (raiz == nodo)
			raiz.setLeftNodo(nodo.getLeftNodo());
		if (pai.getLeftNodo() == nodo) {
			pai.setLeftNodo(nodo.getLeftNodo());
		} else {
			pai.setRightNodo(nodo.getLeftNodo());
		}
	}
	
	private void removeFilhoUnicoDireita(Nodo pai, Nodo nodo) {
		//so possui um filho a direita
		if (raiz == nodo)
			raiz.setRightNodo(nodo.getRightNodo());
		if (pai.getLeftNodo() == nodo) {
			pai.setLeftNodo(nodo.getRightNodo());
		} else {
			pai.setRightNodo(nodo.getRightNodo());
		}
	}
	
	private Nodo LeftDeapthSearch(Nodo nodo) {
		while (nodo.getLeftNodo() != null) {
			nodo = nodo.getLeftNodo();
		}
		return nodo;
	}
	
}
