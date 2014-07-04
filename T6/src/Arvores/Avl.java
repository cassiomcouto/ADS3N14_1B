package Arvores;

import Arvores.Arvore;
import Arvores.Nodo;



public class Avl extends Arvore {
	public int comparacoes;
	public int rotacoes;
	public boolean removido;
	
	private void resetaAtributos()
	{
		comparacoes = 0;
		rotacoes = 0;
	}

	public void insereAvl(Nodo nodo) {
		resetaAtributos();
		insereAvl(raiz, nodo);
	}

	public void insereAvl(Nodo p, Nodo q) {
		if (p == null) {
			raiz = q;
		} else {

			if (q.codigo.toLowerCase().compareTo(p.codigo.toLowerCase()) < 0) {
				comparacoes++;
				if (p.filhoEsquerda == null) {
					p.filhoEsquerda = q;
					q.pai = p;

					balancoRec(p);
				} else {
					insereAvl(p.filhoEsquerda, q);
				}

			} else if (q.codigo.toLowerCase().compareTo(p.codigo.toLowerCase()) >= 0) {
				comparacoes++;
				if (p.filhoDireita == null) {
					p.filhoDireita = q;
					q.pai = p;

					balancoRec(p);
				} else {
					insereAvl(p.filhoDireita, q);
				}
			}
		}
	}

	public void balancoRec(Nodo atual) {

		setBalanco(atual);
		int balanco = atual.balanco;

		if (balanco == -2) {

			comparacoes++;
			if (altura(atual.filhoEsquerda.filhoEsquerda) >= altura(atual.filhoEsquerda.filhoDireita)) {
				atual = rotaDir(atual);
			} else {
				atual = rotaEsqDir(atual);
			}
		} else if (balanco == 2) {
			comparacoes++;
			if (altura(atual.filhoDireita.filhoDireita) >= altura(atual.filhoDireita.filhoEsquerda)) {
				atual = rotaEsq(atual);
			} else {
				atual = rotaDirEsq(atual);
			}
		}

		if (atual.pai != null) {
			balancoRec(atual.pai);
		} else {
			raiz = atual;
			System.out
					.println("Balanceado");
		}
	}

	public void removeAvl(String k) {
		removido = false;
		resetaAtributos();
		removeAvl(raiz, k);
	}
	
	public void removeAvl(Nodo p, String q) {
		if (p == null) {
			return;
		} else {
			comparacoes++;
			if (p.codigo.toLowerCase().compareTo(q.toLowerCase()) > 0) {
				removeAvl(p.filhoEsquerda, q);
			} else if (p.codigo.toLowerCase().compareTo(q.toLowerCase()) < 0) {
				removeAvl(p.filhoDireita, q);
			} else if (p.codigo.toLowerCase().trim().equals(q.toLowerCase().trim())) {
				removido = true;
				removerNodo(p);
			}
		}
	}

	public void removerNodo(Nodo q) {
		Nodo r;
		if (q.filhoEsquerda == null || q.filhoDireita == null) {
			if (q.pai == null) {
				raiz = null;
				q = null;
				return;
			}
			r = q;
		} else {
			r = successor(q);
			q.codigo = r.codigo;
		}

		Nodo p;
		if (r.filhoEsquerda != null) {
			p = r.filhoEsquerda;
		} else {
			p = r.filhoDireita;
		}

		if (p != null) {
			p.pai = r.pai;
		}

		if (r.pai == null) {
			raiz = p;
		} else {
			if (r == r.pai.filhoEsquerda) {
				r.pai.filhoEsquerda = p;
			} else {
				r.pai.filhoDireita = p;
			}
			balancoRec(r.pai);
		}
		r = null;
	}

	public Nodo rotaEsq(Nodo n) {
		rotacoes++;
		System.out.println("RotaÃ§Ã£o Esquerda");
		Nodo v = n.filhoDireita;
		v.pai = n.pai;

		n.filhoDireita = v.filhoEsquerda;

		if (n.filhoDireita != null) {
			n.filhoDireita.pai = n;
		}

		v.filhoEsquerda = n;
		n.pai = v;

		if (v.pai != null) {
			if (v.pai.filhoDireita == n) {
				v.pai.filhoDireita = v;
			} else if (v.pai.filhoEsquerda == n) {
				v.pai.filhoEsquerda = v;
			}
		}

		setBalanco(n);
		setBalanco(v);

		return v;
	}

	public Nodo rotaDir(Nodo n) {
		rotacoes++;
		System.out.println("RotaÃ§Ã£o Direita");
		Nodo v = n.filhoEsquerda;
		v.pai = n.pai;

		n.filhoEsquerda = v.filhoDireita;

		if (n.filhoEsquerda != null) {
			n.filhoEsquerda.pai = n;
		}

		v.filhoDireita = n;
		n.pai = v;

		if (v.pai != null) {
			if (v.pai.filhoDireita == n) {
				v.pai.filhoDireita = v;
			} else if (v.pai.filhoEsquerda == n) {
				v.pai.filhoEsquerda = v;
			}
		}

		setBalanco(n);
		setBalanco(v);

		return v;
	}

	public Nodo rotaEsqDir(Nodo u) {
		rotacoes++;
		rotacoes++;
		System.out.println("RotaÃ§Ã£o Esquerda / Direita");
		u.filhoEsquerda = rotaEsq(u.filhoEsquerda);
		return rotaDir(u);
	}

	public Nodo rotaDirEsq(Nodo u) {
		rotacoes++;
		rotacoes++;
		System.out.println("RotaÃ§Ã£o Direita / Esquerda");
		u.filhoDireita = rotaDir(u.filhoDireita);
		return rotaEsq(u);
	}

	public Nodo successor(Nodo q) {
		if (q.filhoDireita != null) {
			Nodo r = q.filhoDireita;
			while (r.filhoEsquerda != null) {
				r = r.filhoEsquerda;
			}
			return r;
		} else {
			Nodo p = q.pai;
			while (p != null && q == p.filhoDireita) {
				q = p;
				p = q.pai;
			}
			return p;
		}
	}

	private int altura(Nodo cur) {
		if (cur == null) {
			return -1;
		}
		if (cur.filhoEsquerda == null && cur.filhoDireita == null) {
			return 0;
		} else if (cur.filhoEsquerda == null) {
			return 1 + altura(cur.filhoDireita);
		} else if (cur.filhoDireita == null) {
			return 1 + altura(cur.filhoEsquerda);
		} else {
			return 1 + maximum(altura(cur.filhoEsquerda), altura(cur.filhoDireita));
		}
	}

	private int maximum(int a, int b) {
		if (a >= b) {
			return a;
		} else {
			return b;
		}
	}

	private void setBalanco(Nodo cur) {
		cur.balanco = altura(cur.filhoDireita) - altura(cur.filhoEsquerda);
	}
	
	public void mostraArvore()
	{
		System.out.println("================================================");
		System.out.println("Ã�rvore AVL");
		mostraArvore(raiz);
		System.out.println("");
		System.out.println("Fim Ã�rvore AVL");
		System.out.println("================================================");
	}
	
	public void mostraArvore(Nodo nodo)
	{
		boolean temEsq;
		boolean temDir;
		
		if (nodo.filhoEsquerda == null)
		{
			temEsq = false;
		}
		else
		{
			temEsq = true;
		}
		
		if (nodo.filhoDireita == null)
		{
			temDir = false;
		}
		else
		{
			temDir = true;
		}
		
		System.out.print(nodo.codigo);
		
		if (temDir || temEsq)
		{
			System.out.print("(");
			
			if (temEsq)
			{
				mostraArvore(nodo.filhoEsquerda);
			}
			else 
			{
				System.out.print("()");
			}
			
			System.out.print(" - ");
			
			if (temDir)
			{
				mostraArvore(nodo.filhoDireita);
			}
			else
			{
				System.out.print("()");
			}
			
			System.out.print(")");
		}
	}
}
