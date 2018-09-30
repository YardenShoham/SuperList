package listeners;

import bl.Product;

public interface ListEventsListener {
	void addedProductToModelEvent(Product addedProduct);
	void removedProductFromModelEvent(Product deletedProduct);
}
