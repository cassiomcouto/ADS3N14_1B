package controller;

import view.ClientesView;
import view.View;
import Model.Cliente;

public class ControllerCliente extends ControllerBase<Cliente> {

	@Override
	protected Cliente lerEntradaCadastro() {		
		Cliente cliente = super.lerEntradaCadastro();
		cliente.setId(proximoId());
		return cliente;
	}


	@Override
	protected View<Cliente> getView() {
		ClientesView view = new ClientesView();
		return view;
	}

}
