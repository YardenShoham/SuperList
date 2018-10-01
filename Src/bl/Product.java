// Product.java
package bl;
import java.io.Serializable;

/**
* A product that you can find when you go to a store.
* @author Yarden Shoham & Avi Mishayev
*/
public class Product implements Serializable
{
	private String name; 
	private String brand;
	private String note; // additional information about the product
	private String units;

	/**
	* A constructor to initialize every property of the product.
	* @param name the name of the product
	* @param brand the brand of the product
	* @param note additional information about the product
	* @param units the units of the product - how the product is measured
	*/
	public Product(String name, String brand, String note, String units)
	{
		setName(name);
		setBrand(brand);
		setNote(note);
		setUnits(units);
	}

	/**
	* A constructor to initialize only the name and units of the product.
	* @param name the name of the product
	* @param units the units of the product - how the product is measured
	*/
	public Product(String name, String units)
	{
		this(name, null, null, units);
	}

	/**
	* Updates the name of the product
	* @param name the name of the product
	*/
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	* Updates the brand of the product
	* @param brand the brand of the product
	*/
	public void setBrand(String brand)
	{
		this.brand = brand;
	}

	/**
	* Updates the note of the product - additional information about the product
	* @param note the additional product information
	*/
	public void setNote(String note)
	{
		this.note = note;
	}

	/**
	* Updates the units of the product - how the product is measured
	* @param units the units of the product
	*/
	public void setUnits(String units)
	{
		this.units = units;
	}

	/**
	* returns the name of the product
	* @return the name of the product
	*/
	public String getName()
	{
		return name;
	}

	/**
	* returns the brand of the product
	* @return the brand of the product
	*/
	public String getBrand()
	{
		return brand;
	}

	/**
	* returns the note of the product - additional information about the product
	* @return additional information about the product
	*/
	public String getNote()
	{
		return note;
	}

	/**
	* returns the units of the product - how the product is measured
	* @return the units of the product
	*/
	public String getUnits()
	{
		return units;
	}

	/**
	* Compares two products.
	* @param obj a product to check
	* @return true if obj has the same name and brand as this, false otherwise.
	*/
	@Override
	public boolean equals(Object obj)
	{
		if (obj == this) return true; // if both references refer to the same product
		if (!(obj instanceof Product)) return false; // if the other object is not a product

		Product other = (Product) obj; // now we know that obj is a product

		boolean nameEquals = getName().equals(other.getName());

		boolean brandEquals;
		if (other.getBrand() == null && getBrand() == null) brandEquals = true;
		else if ((other.getBrand() == null && getBrand() != null) || (other.getBrand() != null && getBrand() == null)) brandEquals = false;
		else brandEquals = getBrand().equals(other.getBrand());

		return (nameEquals && brandEquals);
	}

	/**
	* @return a string with a form of "name brand"
	*/
	@Override
	public String toString()
	{
		return (brand == null) ? name : name + " " + brand;
	}

}