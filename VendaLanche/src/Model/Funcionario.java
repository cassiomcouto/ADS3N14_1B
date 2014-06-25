package Model;

public class Funcionario extends Pessoa {

	private int salario;
	private String cargo;

	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		return "Funcionario [salario=" + salario + ", cargo=" + cargo
				+ ", nome=" + getNome() + ", telefone="
				+ getTelefone() + "]";
	}
	
	

}
