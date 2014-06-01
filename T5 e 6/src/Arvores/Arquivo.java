package Arvores;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import Model.Pessoa;


public class Arquivo {
	public Nodo raiz;
	public int comparacoes;
	public int numNodos;
	
	public static Avl carregarAVL()
	{
		Avl avl = new Avl();
		Nodo raiz = null;
		Pessoa pessoa = new Pessoa();
		String linha;

		try {
			FileReader fReader = new FileReader("contatos.txt");
			BufferedReader textReader = new BufferedReader(fReader);

			while ((linha = textReader.readLine()) != null) {
				pessoa = new Pessoa();
				String[] linhaSplit = linha.split("##");
				
				Nodo nodo = new Nodo();
				
				pessoa.setNome(linhaSplit[0]);
				pessoa.setFone(linhaSplit[1]);
				nodo.valor = pessoa;
				nodo.codigo = linhaSplit[0];
				
				nodo.pai = raiz;
				
				avl.insereAvl(nodo);
			}
			
			textReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}

		return avl;
	}
	
	
	public static void salvar(Arvore arv)
	{
		ArrayList<Nodo> Nodos = recuperaNodos(arv.raiz);
		
		Writer writer = null;
		try
		{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("contatos.txt"), "utf-8"));
			
			for (Nodo item : Nodos)
			{
				writer.write(item.valor.getNome() + "##" + item.valor.getFone() + "\n");
			}
		}
		catch (IOException ex) {}
		finally 
		{
			try
			{
				writer.close();
			}
			catch (IOException e) {}
		}
	}
	
	public static ArrayList<Nodo> recuperaNodos(Nodo raiz)
	{
		ArrayList<Nodo> fila = new ArrayList<Nodo>();
		ArrayList<Nodo> lista = new ArrayList<Nodo>();
		
		
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
		
		return lista;
	}
}
