package Model;

import Model.Pessoa;

public class Cliente extends Pessoa {

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome()=" + getNome()
				+ ", telefone=" + getTelefone() + "]";
	}
	
	
	

}
