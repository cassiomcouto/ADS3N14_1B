package controller;

import view.ProdutosView;
import view.View;
import Model.Produto;

public class ControllerProduto extends ControllerBase<Produto> {

	@Override
	protected View<Produto> getView() {		
		return new ProdutosView();
	}
	
}
