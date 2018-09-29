// SuperList.java

import java.util.*;

import java.io.*;

public class SuperList
{
	private ArrayList<Product> theList;
	private String location = "theList.bin";

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
	}

	public boolean removeFromList(Product product)
	{
		return theList.remove(product);
	}

}