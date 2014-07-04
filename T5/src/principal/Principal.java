package principal;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.*;

import view.*;

import estruturas.*;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		
		Scanner entrada = new Scanner(System.in);
		int opcao = 0;
		String aux = "";
		Menu menu = new Menu();
 
		
		
		Erro trataErro = new Erro();
		ArrayList<Nodo> travessia = new ArrayList<Nodo>();
		
			
		

		File arquivo = new File( "contatos.txt" );
		
		Arvore arvore = learquivo(arquivo);

		while(true) {
			menu.menuInicial();
			opcao = trataErro.scannerInt(entrada);
			
			switch (opcao) {
			case 1:
				aux = inserircontato(entrada, menu, arvore);
				break;
			case 2:
				aux = removercontato(entrada, arvore);
				break;
			case 3:
				aux = procurarcontato(entrada, arvore);
				break;
			case 4:
				menu.tipoTravessia();

				opcao = trataErro.scannerInt(entrada);
				
				switch (opcao) {
				case 1:
					travessia = arvore.travessiaInfixa();
					aux = "Travessia Infixa.\n";
					break;
				case 2:
					travessia = arvore.travessiaPrefixa();
					aux = "Travessia Prefixa.\n";
					break;
				case 3:
					travessia = arvore.travessiaPosfixa();
					aux = "Travessia Posfixa.\n";
					break;
				case 4:
					travessia = arvore.buscaProfundidade();
					aux = "Busca em largura.\n";
					break;
				case 5:
					travessia = arvore.buscaLargura();
					aux = "Busca em profundidade.\n";
					break;
				default:
					System.out.println("Opcao invalida, o sistema será encerado.");
					System.exit(0);
					break;
				}
				
				aux += "Nome,\t Telefone\n";
				
				for (int i = 0; i < travessia.size(); i++) {
					aux += travessia.get(i).getValor()+", "+travessia.get(i).getDados().getTelefone()+"\n";
				}
				
				System.out.println(aux);
				break;
			case 5:
				travessia = sairprograma(arvore, arquivo);
				
				System.exit(0);
				
			default:
				System.exit(0);
				break;
		}
		
		}
		
	}

	private static Arvore learquivo(File arquivo) throws IOException,
			FileNotFoundException {
		Arvore arvore = new Arvore();	
		if (!arquivo.exists())
			arquivo.createNewFile();
		
		  FileReader fr = new FileReader(arquivo);
		  BufferedReader br = new BufferedReader(fr);
		
		while (br.ready()) {
		   
			Pessoa p = new Pessoa(br.readLine());		
		  
		    p.setTelefone(br.readLine());
		   
		    arvore.insere(new Nodo(p));
		  }
		br.close();
		fr.close();
		return arvore;
	}

	private static ArrayList<Nodo> sairprograma(Arvore arvore, File arquivo)
			throws IOException {
		ArrayList<Nodo> travessia;
		System.out.println("Salvando arquivo...");
		travessia = arvore.travessiaInfixa();
		
		FileWriter fw = new FileWriter(arquivo);
		BufferedWriter bw = new BufferedWriter(fw);
		
		for (int i = 0; i < travessia.size(); i++) {
			bw.write(travessia.get(i).getValor());
			bw.newLine();
			bw.write(travessia.get(i).getDados().getTelefone());

			if (!(i == travessia.size() - 1))
				bw.newLine();
		}
		
		bw.close();
		fw.close();
		
		System.out.println("Arquivo foi salvo com sucesso.");
		return travessia;
	}

	private static String inserircontato(Scanner entrada, Menu menu, Arvore arvore) {
		Pessoa p;
		String aux;
		menu.menuInserir();
		aux = entrada.next();
		
		p = new Pessoa(aux);
		menu.menuInserirTelefone();
		p.setTelefone(entrada.next());
		arvore.insere(new Nodo(p));
		
		System.out.println("Pessoa inserida com sucesso:\n" +
				"Nome: "+p.getNome()+"\n" +
						"Telefone: "+p.getTelefone());
		return aux;
	}

	private static String removercontato(Scanner entrada, Arvore arvore) {
		String aux;
		System.out.println("Digite o nome do contato que deseja remover:");
		aux = entrada.next();
		if (arvore.removeNodo(aux))
			System.out.println("Contato removido com sucesso:");
		else
			System.out.println("Contato não localizado.");
		return aux;
	}

	private static String procurarcontato(Scanner entrada, Arvore arvore) {
		String aux;
		Nodo nodoAux;
		System.out.println("Digite o nome do contato que deseja procurar:");
		aux = entrada.next();
		nodoAux = arvore.buscaNodo(aux); 
		if ( nodoAux == null) 
			System.out.println("Nodo não localizado.");
		else
			System.out.println("Nome: "+nodoAux.getDados().getNome()+"\n" +
					"Telefone: "+nodoAux.getDados().getTelefone());
		return aux;
	}


	
}
