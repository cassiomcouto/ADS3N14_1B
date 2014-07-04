package Arvores;

import Arvores.Nodo;

import java.util.ArrayList;

public class Arvore {
	public Nodo raiz;
	public int comparacoes;
	public int numNodos;

	public Arvore() { 
		raiz = null;
		comparacoes = 1;
		numNodos = 0;
	}

	public Nodo pesquisa(String chave) {
		comparacoes = 1;
		Nodo atual = raiz;
		while (!atual.codigo.toLowerCase().trim()
				.equals(chave.toLowerCase().trim())) {
			comparacoes++;
			if (chave.toLowerCase().compareTo(atual.codigo.toLowerCase()) < 0)
				atual = atual.filhoEsquerda;
			else
				atual = atual.filhoDireita;
			if (atual == null) {
				System.out.println("Comparações: " + comparacoes);
				return null;
			}

		}
		System.out.println("Comparações: " + comparacoes);
		return atual;
	} 
	
	
	public void insere(Nodo Novo, boolean contar) {
		if (contar)
			numNodos++;
		Nodo NodovoNodo = Novo;

		if (raiz == null)
			raiz = NodovoNodo;
		else 
		{
			Nodo atual = raiz;
			Nodo parente;
			while (true) {
				parente = atual;
				if (Novo.codigo.compareTo(atual.codigo) < 0) {
					atual = atual.filhoEsquerda;
					if (atual == null) { 
						parente.filhoEsquerda = NodovoNodo;
						NodovoNodo.pai = parente;
						return;
					}
				} else 
				{
					atual = atual.filhoDireita;
					if (atual == null) {
						parente.filhoDireita = NodovoNodo;
						NodovoNodo.pai = parente;
						return;
					}
				} 
			} 
		} 
	}



	public boolean delete(String chave) {
		numNodos--;
		Nodo atual = raiz;
		Nodo parente = raiz;
		boolean eFilhoEsquerda = true;

		while (!atual.codigo.toLowerCase().trim()
				.equals(chave.toLowerCase().trim())) 
		{
			parente = atual;
			if (chave.toLowerCase().compareTo(atual.codigo.toLowerCase()) < 0) {
				eFilhoEsquerda = true;
				atual = atual.filhoEsquerda;
			} else {
				eFilhoEsquerda = false;
				atual = atual.filhoDireita;
			}
			if (atual == null)
				return false; 
		} 

		
		if (atual.filhoEsquerda == null && atual.filhoDireita == null) {
			if (atual == raiz) 
				raiz = null;
			else if (eFilhoEsquerda)
				atual.pai.filhoEsquerda = null;
			else
				atual.pai.filhoDireita = null;
		}
		
		else if (atual.filhoDireita == null) {
			if (atual == raiz)
				raiz = atual.filhoEsquerda;
			else if (eFilhoEsquerda)
				atual.pai.filhoEsquerda = atual.filhoEsquerda;
			else
				atual.pai.filhoDireita = atual.filhoEsquerda;
		}

		// 
		else if (atual.filhoEsquerda == null) {
			if (atual == raiz)
				raiz = atual.filhoDireita;
			else if (eFilhoEsquerda)
				atual.pai.filhoEsquerda = atual.filhoDireita;
			else
				atual.pai.filhoDireita = atual.filhoDireita;
		}

		else 
		{
			// connecta parente do atual ao successor
			if (atual == raiz) {
				raiz = null;
			} else if (eFilhoEsquerda)
				atual.pai.filhoEsquerda = null;
			else
				atual.pai.filhoDireita = null;

			insere(atual.filhoDireita, false);
			insere(atual.filhoEsquerda, false);
		} // fim do else dois filhos
		return true;
	} // fim do método delete()

	/*
	 * O método travesseia apaga um nó da árvore passado como parâmetro pela
	 * variável chave
	 */
	public void travessia(int tipoTravessia) {
		switch (tipoTravessia) {
		case 1:
			System.out.println("\nTravessia usando Preorder: ");
			preOrder(raiz);
			break;
		case 2:
			System.out.println("\nTravessia usando INodorder:  ");
			iNodorder(raiz);
			break;
		case 3:
			System.out.println("\nTravessia usando Postorder: ");
			posOrder(raiz);
			break;
		case 4:
			System.out.println("\nTravessia usando Busca por Profundidade: ");
			profundidade();
			break;
		case 5:
			System.out.println("\nTravessia usando Busca por Largura: ");
			largura();
			break;
		}
		System.out.println();
	}

	/*
	 * O método preOrder
	 */
	private void preOrder(Nodo localraiz) {
		if (localraiz != null) {
			localraiz.mostraNodo();
			preOrder(localraiz.filhoEsquerda);
			preOrder(localraiz.filhoDireita);
		}
	}

	/*
	 * O método iNodorder
	 */
	private void iNodorder(Nodo localraiz) {
		if (localraiz != null) {
			iNodorder(localraiz.filhoEsquerda);
			localraiz.mostraNodo();
			iNodorder(localraiz.filhoDireita);
		}
	}

	/*
	 * O método posOrder
	 */
	private void posOrder(Nodo localraiz) {
		if (localraiz != null) {
			posOrder(localraiz.filhoEsquerda);
			posOrder(localraiz.filhoDireita);
			localraiz.mostraNodo();
		}
	}

	private void profundidade() {
		ArrayList<Nodo> pilha = new ArrayList<Nodo>();

		pilha.add(raiz);

		while (!pilha.isEmpty()) {
			Nodo Nodo = pilha.get(pilha.size() - 1);
			Nodo.mostraNodo();
			pilha.remove(Nodo);

			if (Nodo.filhoDireita != null) {
				pilha.add(Nodo.filhoDireita);
			}

			if (Nodo.filhoEsquerda != null) {
				pilha.add(Nodo.filhoEsquerda);
			}
		}
	}

	private void largura() {
		ArrayList<Nodo> fila = new ArrayList<Nodo>();

		fila.add(raiz);

		while (!fila.isEmpty()) {
			Nodo nodo = fila.get(0);
			nodo.mostraNodo();
			fila.remove(nodo);

			if (nodo.filhoEsquerda != null) {
				fila.add(nodo.filhoEsquerda);
			}

			if (nodo.filhoDireita != null) {
				fila.add(nodo.filhoDireita);
			}
		}
	}
}
