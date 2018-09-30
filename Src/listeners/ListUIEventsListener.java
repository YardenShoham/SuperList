package listeners;
import bl.Product;

public interface ListUIEventsListener {
	void addProductToUI(String name,String brand,String notes,String units);
	void removeProductFromUI(String name,String brand);
}

