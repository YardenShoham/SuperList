// SuperList.java
package bl;

import java.util.*;
import java.io.*;
import listeners.*;

/**
* This class is the represents the model part in the MVC. It has a list of products that is saved unto a file.
* @author Yarden Shoham & Avi Mishayev
*/
public class SuperList {
	private ArrayList<Product> theList; // a list of products
	private String location = "theList.bin"; // the name of the file that contains the list
	private ListEventsListener controller; // a reference to controller part of the MVC

	/**
	* A constructor for the list. It loads the list from the file.
	*/
	@SuppressWarnings("unchecked") // there is a cast inside, it is safe
	public SuperList() {
		try {
			File theListFile = new File(location);
			if (theListFile.exists() && !theListFile.isDirectory()) { // if the file exists, load the list from it
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(location));
				theList = (ArrayList<Product>) ois.readObject();
				ois.close();
			} else {
				theList = new ArrayList<>(); // if the file does not exist, create a new list
			}
		} catch (Exception e) {
			System.out.println(e);
			theList = new ArrayList<>();
		}
		//System.out.println(theList.size());
	}

	/**
	* Adds a given product to the list, saves the list and fires an added product event.
	* @param newProduct the product to add to the list
	*/
	public void addToList(Product newProduct) {
		theList.add(newProduct);

		saveList();

		fireAddProductEvent(newProduct);

	}

	/**
	* Removes a given product from the list, saves the list and fires a removed product event.
	* @param product the product to remove from the list. It's not a reference to the actual product to remove
	* but merely a product with the same name and brand to satisfy the {@link Product#equals(Object obj)} method
	*/
	public void removeFromList(Product product) {
		Product removedProduct = theList.remove(theList.indexOf(product));
		saveList();
		fireRemoveProductEvent(removedProduct);
	}

	/**
	* Registers the controller of the model part in order to fire events to the controller part of the MVC.
	* @param listener the listener to fire events at
	*/
	public void registerListener(ListEventsListener listener) {
		controller = listener;
	}

	/**
	* Fires an event to the controller part of the MVC that specifies that a new product was added to the list.
	* @param newProduct the product that was added to the list
	*/
	private void fireAddProductEvent(Product newProduct) {
		controller.addedProductToModelEvent(newProduct);
	}

	/**
	* Fires an event to the controller part of the MVC that specifies that a product was removed from the list.
	* @param removedProduct the product that was removed from the list
	*/
	private void fireRemoveProductEvent(Product removedProduct) {
		controller.removedProductFromModelEvent(removedProduct);
	}

	/**
	* Saves the list to file.
	*/
	private void saveList() {
		// write updated list to file
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(location));
			oos.writeObject(theList);
			oos.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}