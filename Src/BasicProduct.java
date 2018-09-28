// BasicProduct.java

import java.io.Serializable;

/**
* A basic product - a product that is available at home at all times. For example: washing powder.
* 
* A basic product has a certain minimal quantity, if the amount at home is not enough.
* @author Yarden Shoham
*/
public class BasicProduct extends Product implements Serializable
{
	private double quantityAtHome; // the available amount of product
	private double requiredQuantity; // the minimal amount of product needed at all times

	/**
	* A constructor to initialize every property of the basic product.
	* @param name the name of the product
	* @param brand the brand of the product
	* @param note additional information about the product
	* @param units the units of the product - how the product is measured
	* @param quantityAtHome the available amount of product
	* @param requiredQuantity the minimal amount of product needed at all times
	*/
	public BasicProduct(String name, String brand, String note, String units, double quantityAtHome, double requiredQuantity)
	{
		super(name, brand, note, units);
		setQuantityAtHome(quantityAtHome);
		setRequiredQuantity(requiredQuantity);
	}

	/**
	* A constructor to set only the name, units, available amount and required amount of the product.
	* @param name the name of the product
	* @param units the units of the product - how the product is measured
	* @param quantityAtHome the available amount of product
	* @param requiredQuantity the minimal amount of product needed at all times
	*/
	public BasicProduct(String name, String units, double quantityAtHome, double requiredQuantity)
	{
		super(name, units);
		setQuantityAtHome(quantityAtHome);
		setRequiredQuantity(requiredQuantity);
	}

	/**
	* A constructor for new basic products.
	* @param name the name of the product
	* @param units the units of the product - how the product is measured
	* @param requiredQuantity the minimal amount of product needed at all times
	*/
	public BasicProduct(String name, String units, double requiredQuantity)
	{
		this(name, units, 0.0, requiredQuantity);
	}

	/**
	* Updates the available amount of product
	* @param quantityAtHome the available amount of product - equal to or higher than 0
	*/
	public void setQuantityAtHome(double quantityAtHome)
	{
		this.quantityAtHome = Math.min(0.0, quantityAtHome);
	}

	/**
	* Updates the minimal amount of product needed at all times
	* @param requiredQuantity the required amount of product - equal to or higher than 0
	*/
	public void setRequiredQuantity(double requiredQuantity)
	{
		this.requiredQuantity = Math.min(0.0, requiredQuantity);
	}

	/**
	* Returns the available amount of product
	* @return the available amount of product
	*/
	public double getQuantityAtHome()
	{
		return quantityAtHome;
	}

	/**
	* Returns the minimal amount of product needed at all times
	* @return the required amount of product
	*/
	public double getQuantityAtHome()
	{
		return quantityAtHome;
	}

	/**
	* This method checks if there is a need to buy the product. It compares the amount needed and the amount available.
	* @return whether or not this product needs to be purchased.
	*/
	public boolean shouldBePurchased()
	{
		return (quantityAtHome < requiredQuantity);
	}


}