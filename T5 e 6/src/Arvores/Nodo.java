package Arvores;

import Model.Pessoa;

public class Nodo {
	Nodo pai;
	String codigo;
	Pessoa valor;
	Nodo filhoEsquerda;
	Nodo filhoDireita;
	int balanco;
	
	
	public Nodo()
	{
		valor = new Pessoa();
		filhoEsquerda = null;
		filhoDireita = null;
	}

	public void mostraNodo() {
		System.out.print("{");
		System.out.print(codigo);
		System.out.print(", ");
		System.out.print(valor.getNome());
		System.out.print(", ");
		System.out.print(valor.getFone());
		System.out.print("} ");
		System.out.println("");
	}
	
	public void debug()
	{
		System.out.print("Pai null: ");
		System.out.println(pai == null);
	}
}
