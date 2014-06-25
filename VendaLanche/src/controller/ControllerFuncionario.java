package controller;

import view.FuncionariosView;
import view.View;
import Model.Funcionario;

public class ControllerFuncionario extends ControllerBase<Funcionario> {

	@Override
	protected View<Funcionario> getView() {
		// TODO Auto-generated method stub
		return new FuncionariosView();
	}
	

}
