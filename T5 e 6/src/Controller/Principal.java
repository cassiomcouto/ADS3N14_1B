package Controller;

import Arvores.Arquivo;
import Arvores.Avl;
import Arvores.Nodo;
import Model.Pessoa;

import java.util.Scanner;

public class Principal {
	Avl avl;
	Nodo noAtual;

	public Principal() {
		avl = Arquivo.carregarAVL();
		noAtual = avl.raiz;
	}

	public void Principal() {
		System.out.println("Menu");
		System.out.println("1 - Adicionar contato");
		System.out.println("2 - Remover contato");
		System.out.println("3 - Mostrar árvore");
		System.out.println("4 - Sair");

		int num = (new Scanner(System.in)).nextInt();

		switch (num) {
		case 1:
			adicionar();
			break;
		case 2:
			remover();
			break;
		case 3:
			mostraArvore();
			break;
		case 4:
			sair();
			break;
		default:
			System.out.println("Opcao Invalida");
			Principal();
			break;
		}
	}

	private void mostraArvore() {
		avl.mostraArvore();
		Principal();
	}

	private void adicionar() {
		Nodo novoAVL = new Nodo();

		System.out.print("Digite o Nodome: ");
		String Nodome = (new Scanner(System.in).nextLine());
		novoAVL.valor.setNome(nome);

		System.out.print("Digite o telefone: ");
		String fone = (new Scanner(System.in).nextLine());
		novoAVL.valor.setFone(fone);

		novoAVL.codigo = Nodome;

		System.out.print("===============");
		System.out.print("Inserindo na AVL...");
		avl.insereAVL(novoAVL);
		System.out.print("AVL inserido!");
		System.out.print("Comparações realizadas: " + avl.comparacoes);
		System.out.print("Rotações feitas: " + avl.rotacoes);
		System.out.print("===============");

		Principal();
	}

	private void remover()
	{
		System.out.print("Digite o Nome: ");
		String Nome = (new Scanner(System.in)).nextLine();
		
		System.out.print("===============");
		System.out.print("Removendo AVL...");
		avl.removeAVL(Nome);
		if (avl.removido){
			System.out.print("AVL Removido.");
		}
		else
		{
			System.out.print("Nenhum Nó encontrado.");
		}
		
		System.out.print("Comparações: " + avl.comparacoes);
		System.out.print("Rotações: " + avl.rotacoes);
		System.out.print("===============");
	
	
		
		Principal();
	}


	private void msg(String msg) {
		System.out.print(msg);
	}

	private void sair() {
		System.exit(0);
	}

	public static void main(String[] args) {
		new Principal();

	}

}
