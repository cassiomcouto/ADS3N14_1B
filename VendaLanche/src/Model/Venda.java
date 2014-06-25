package Model;

import java.util.ArrayList;
import java.util.List;

public class Venda {

	private Integer id; 
	private Funcionario atendente;
	private Cliente cliente;
	private List<Pedido> pedidos = new ArrayList<Pedido>();

	public Venda() {	
	}
	
	public Venda(Integer id, Cliente cliente, Funcionario atendente) {
		super();
		this.id=id;
		this.cliente = cliente;
		this.atendente = atendente;
	}

	public void adicionarPedido(Produto produto, Integer quantidade) {		
		Pedido pedido = new Pedido(this, produto, quantidade);
		pedidos.add(pedido);
	}
	
	public double calcularPrecoTotal(){
		double soma = 0;
		for(Pedido pedido: pedidos){
			soma+= pedido.calcularPreco();
		}
		return soma;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((atendente == null) ? 0 : atendente.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		if (atendente == null) {
			if (other.atendente != null)
				return false;
		} else if (!atendente.equals(other.atendente))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", atendente=" + atendente.getNome() + ", cliente="
				+ cliente.getNome() + ", pedidos=" + pedidos + ", total="
				+ calcularPrecoTotal() + "]";
	}

	
	
	
	

}
