// NonessentialProduct.java

import java.io.Serializable;

/**
* A non essential product is the opposite of a basic product, it's not required for the day-to-day life but may be requested nevertheless.
* For example: chocolate.
* @author Yarden Shoham
*/
public class NonessentialProduct extends Product implements Serializable
{
	private double requestedQuantity; // the requested amount to be purchased

	/**
	* A constructor to initialize every property of the nonessential product.
	* @param name the name of the product
	* @param brand the brand of the product
	* @param note additional information about the product
	* @param units the units of the product - how the product is measured
	* @param requestedQuantity the requested amount to be purchased
	*/
	public NonessentialProduct(String name, String brand, String note, String units, double requestedQuantity)
	{
		super(name, brand, note, units);
		setRequestedQuantity(requestedQuantity);
	}

	/**
	* A constructor to set only the name, units, and requested amount of the product.
	* @param name the name of the product
	* @param units the units of the product - how the product is measured
	* @param requestedQuantity the requested amount to be purchased
	*/
	public NonessentialProduct(String name, String units, double requestedQuantity)
	{
		super(name, units);
		setRequestedQuantity(requestedQuantity);
	}

	/**
	* Updates the requested amount to be purchased
	* @param requestedQuantity the requested amount to be purchased - equal to or higher than 0
	*/
	public void setRequestedQuantity(double requestedQuantity)
	{
		this.requestedQuantity = Math.min(0.0, requestedQuantity);
	}

	/**
	* Returns the requested amount to be purchased
	* @return the requested amount to be purchased
	*/
	public double getRequestedQuantity()
	{
		return requestedQuantity;
	}
}