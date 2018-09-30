package controller;

import bl.Product;
import bl.SuperList;
import listeners.*;

public class ProductController implements ListEventsListener, ListUIEventsListener {
	private SuperList listBL;
//	private AbstractSuperListView listUI;
	
	@Override
	public void addProductToUI(Product newProduct) {
		listBL.addToList(newProduct);
	}

	@Override
	public void removeProductFromUI(String name, String brand) {
		listBL.removeFromList(new Product(name,brand,null,null));
	}

	@Override
	public void addedProductToModelEvent(Product addedProduct) {
		listUI.addProductToUI(addedProduct);	
	}

	@Override
	public void removedProductFromModelEvent(Product removedProduct) {
		listUI.removeProductFromUI(removedProduct);
	}

}
