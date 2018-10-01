package controller;

import bl.Product;
import bl.SuperList;
import views.AbstractListView;
import listeners.*;

/**
* The controller part of the MVC. This part acts as a middle-man between the model and view.
* @author Yarden Shoham & Avi Mishayev
*/
public class ProductController implements ListEventsListener, ListUIEventsListener {

	private SuperList listBL; // model
	private AbstractListView listUI; // view
	
	public ProductController(SuperList listBL, AbstractListView listUI) {
		this.listBL = listBL;
		this.listUI = listUI;
		listBL.registerListener(this);
		listUI.registerListener(this);

		listBL.loadDataFromFileIfExists();
	}

	/**
	* This method is called whenever a product is added to the list via the UI.
	* @param newProduct the product that is being added to the list
	*/
	@Override
	public void addProductToUI(Product newProduct) {
		listBL.addToList(newProduct);
	}

	/**
	* This method is called whenever a product is removed from the list via the UI.
	* @param name the name of the product to remove
	* @param brand the brand of the product to remove
	*/
	@Override
	public void removeProductFromUI(String name, String brand) {
		listBL.removeFromList(new Product(name, brand, null, null));	// this product is created in order to find a product with
																		// the same name and brand (the product to remove)
	}
	
	/**
	* This method is called when a product was added to the list. The actual product has now been added to the model part
	* of the MVC, now the only thing left to do is to relay the information to the view part - the UI.
	* @param addedProduct the product that was added to the list
	*/
	@Override
	public void addedProductToModelEvent(Product addedProduct) {
		listUI.addProductToUI(addedProduct);	
	}

	/**
	* This method is called when a product was removed from the list. The actual product has now been removed from the model part
	* of the MVC, now the only thing left to do is to relay the information to the view part - the UI.
	* @param removedProduct the product that was removed from the list
	*/
	@Override
	public void removedProductFromModelEvent(Product removedProduct) {
		listUI.removeProductFromUI(removedProduct);
	}

}
