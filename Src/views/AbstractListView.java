package views;

import bl.Product;
import listeners.ListUIEventsListener;

public interface AbstractListView {
	void registerListener(ListUIEventsListener listener);
	void addProductToUI(Product addedProduct);
	void removeProductFromUI(Product removedProduct);
}

