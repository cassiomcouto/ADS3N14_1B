package com.senac.apps.ListaTelefonica.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

import com.senac.apps.ListaTelefonica.model.Pessoa;
import com.senac.apps.ListaTelefonica.view.ConsoleView;
import com.senac.estruturas.ListaBinaria;
import com.senac.estruturas.ListaEncadeada;
import com.senac.estruturas.Nodo;

public class ListaController {
	private ListaEncadeada<Pessoa> arquivo;
	private ListaBinaria<Pessoa> contatos;
	private ConsoleView view;
	private Nodo<Pessoa> current;

	public ListaController(ConsoleView view) {
		this.view = view;
		this.contatos = new ListaBinaria<Pessoa>();
		this.arquivo = new ListaEncadeada<Pessoa>();
		this.current = null;
	}

	public void loadFile(String filename) {
		Scanner arq = null;
		try {
			arq = new Scanner(new FileReader(filename));
			while (arq.hasNext()) {
				String name = arq.nextLine();
				String phone = arq.nextLine();
				Pessoa pessoa = new Pessoa(name);
				pessoa.setTelefone(phone);
				arquivo.insert(new Nodo<Pessoa>(pessoa));
				if (!name.startsWith("#"))
					contatos.insert(new Nodo<Pessoa>(pessoa));
			}
			current = contatos.getHead();
		} catch (FileNotFoundException e) {
			view.logError(e.getMessage());
		} finally {
			if (arq != null) {
				arq.close();
			}
		}
	}

	public void showContato() {
		if (current == null) {
			view.message("Nenhum contato existente.");
		} else {
			Pessoa contato = current.getData();
			view.printContato(contato.getNome(), contato.getTelefone());
		}
	}

	public void nextContato() {
		if (current != null) {
			current = current.getNext();
			if (current == null)
				current = contatos.getHead();
		}
	}

	public void previousContato() {
		if (current != null) {
			current = current.getPrevious();
			if (current == null)
				current = contatos.getTail();
		}
	}

	public void insertContato() {
		Pessoa contato = new Pessoa();
		contato.setNome(view.read("Nome"));
		contato.setTelefone(view.read("Telefone"));
		Nodo<Pessoa> novo = new Nodo<Pessoa>(contato);
		contatos.insert(novo);
		arquivo.append(new Nodo<Pessoa>(contato));
		current = novo;
	}

	public void removeContato() {
		if (current != null) {
			contatos.remove(current);
			nextContato();
		}
	}

	private Nodo<Pessoa> procuraContato(ListaBinaria<Pessoa> lista, final String key) {
		Nodo<Pessoa> result = null;

		if (lista.getSize() > 0) {
			int begin = 0;
			int end = lista.getSize() - 1;
			int middle = dividirPelaMetade(begin + end);
			
			Nodo<Pessoa> nodo = lista.get(middle);

			while (result == null && end >= begin) {
				if (nodo == null) {
					break;
				} else {
					final String nome = nodo.getData().getNome().toLowerCase();
					if (nome.startsWith(key)) {
						result = nodo;
					} else if (nome.compareToIgnoreCase(key) < 0) {
						end = middle - 1;
					} else {
						begin = middle + 1;
					}
				}

				middle = dividirPelaMetade(begin + end);
				nodo = lista.get(middle);
			}
		}
		return result;
	}

	private int dividirPelaMetade(int valor) {
		BigDecimal bd = new BigDecimal(valor);
		bd = bd.divide(new BigDecimal(2));
		bd = bd.setScale(0, BigDecimal.ROUND_HALF_UP);
		return bd.intValue();
	}

	public void searchContato() {
		String chave = view.read("Inicio do Nome").toLowerCase();
		Nodo<Pessoa> contato = procuraContato(contatos, chave);
		if (contato != null) {
			current = contato;
		}
	}

	public void saveFile(String filename) {
		FileWriter arq = null;
		try {
			arq = new FileWriter(filename, false);
			Nodo<Pessoa> iter = arquivo.getHead();
			while (iter != null) {
				Pessoa contato = iter.getData();
				if (procuraContato(contatos, contato.getNome()) == null)
					arq.append("#" + contato.getNome() + "\n");
				else
					arq.append(contato.getNome() + "\n");
				arq.append(contato.getTelefone() + "\n");
				iter = iter.getNext();
			}
		} catch (IOException e) {
			view.message(e.getMessage());
		} finally {
			if (arq != null)
				try {
					arq.close();
				} catch (IOException e) {
					view.message(e.getMessage());
				}
		}
	}

}
