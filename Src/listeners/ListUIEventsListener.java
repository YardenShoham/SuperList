package listeners;
import bl.Product;

public interface ListUIEventsListener {
	void addProductToUI(Product newProduct);
	void removeProductFromUI(String name,String brand);
}

