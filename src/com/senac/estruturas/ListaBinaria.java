package com.senac.estruturas;

import java.math.BigDecimal;


public class ListaBinaria<T extends Comparable<T>> extends ListaOrdenada<T> {

	private int size = 0;
	
	public Nodo<T> get(final int index) {
		if (this.size > 0 && index < this.size) {
			Nodo<T> nodo = getHead();
			
			for (int i = 0; i <= index; i++) {
				if (i == index) {
					return nodo;
				}
				
				nodo = nodo.getNext();
			}
		}
		return null;
	}
	
	@Override
	public Nodo<T> procuraNodo(Nodo<T> needle) {
		Nodo<T> result = null;
		
		if (this.getSize() > 0) {
			int begin = 0;
			int end = this.getSize() - 1;
			int middle = dividirPelaMetade(end + begin);
			
			T keyNeedle = needle.getData();
			
			Nodo<T> nodo = this.get(middle);
			Nodo<T> previous = null;
			
			while (result == null && end >= begin) {
				if (nodo == null) {
					break;
				}
				
				int cmp = keyNeedle.compareTo(nodo.getData());
				
				if (cmp == 0) {
					result = nodo;
				} else if (cmp < 0) {
					end = middle -1;
				} else {
					begin = middle + 1;
				}
				
				middle = dividirPelaMetade(begin + end);
				previous = nodo;
				nodo = this.get(middle);
			}
			
			if (result == null) {
				result = previous;
			}
		}
		
		return result;
	}
	
	public Nodo<T> procuraNodoExato(Nodo<T> needle) {
		Nodo<T> nodo = procuraNodo(needle);
		
		return (nodo != null && nodo.getData().compareTo(needle.getData()) != 0) ? null : nodo;
	}
	
	public int getSize() {
		return this.size;
	}
	
	@Override
	public void remove(Nodo<T> nodo) {
		Nodo<T> removeNodo = procuraNodoExato(nodo);
		if (removeNodo != null) {
			super.remove(removeNodo);
			this.size--;
		}
	}

	@Override
	public void insert(Nodo<T> novo) {
		super.insert(novo);
		this.size++;
	}
	
	private int dividirPelaMetade(int valor) {
		BigDecimal bd = new BigDecimal(valor);
		bd = bd.divide(new BigDecimal(2));
		bd = bd.setScale(0, BigDecimal.ROUND_HALF_UP);
		return bd.intValue();
	}
}
