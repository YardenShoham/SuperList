// SuperList.java
package bl;

import java.util.*;
import java.io.*;
import listeners.*;

public class SuperList
{
	private ArrayList<Product> theList;
	private String location = "theList.bin";
	private ListEventsListener controller;

	@SuppressWarnings("unchecked")
	public SuperList()
	{
		try
		{
			File theListFile = new File(location);
			if (theListFile.exists() && !theListFile.isDirectory())
			{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(location));
				theList = (ArrayList<Product>) ois.readObject();
				ois.close();
			}
			else
			{
				theList = new ArrayList<>();
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
			theList = new ArrayList<>();
		}
	}

	public void addToList(Product newProduct) 
	{
		theList.add(newProduct);

		saveList();

		fireAddProductEvent(newProduct);

	}
	
	public void addToList(String name, String brand, String note, String units) {
		//Product newProduct = new BasicProduct(name,brand,note,units, 0, 0);
		theList.add(new BasicProduct(name,brand,note,units, 0, 0)); 
		
		
	}

	public void removeFromList(Product product)
	{
		theList.remove(product);

		saveList();

		fireRemoveProductEvent(product);
	}
	
	public void registerListener(ListEventsListener listener) {
		controller = listener;
	}

	private void fireAddProductEvent(Product newProduct) {
		controller.addedProductToModelEvent(newProduct);
	}
	
	private void fireRemoveProductEvent(Product removedProduct) {
		controller.removedProductFromModelEvent(removedProduct);
	}

	private void saveList()
	{
		// write updated list to file
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(location));
		oos.writeObject(theList);
		oos.close();
	}

}