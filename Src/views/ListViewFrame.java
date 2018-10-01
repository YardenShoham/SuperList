// ListViewFrame.java
package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import bl.Product;
import listeners.ListUIEventsListener;
import views.AbstractListView;
import views.helpers.*;


@SuppressWarnings("serial")
public class ListViewFrame extends JFrame implements AbstractListView
{
	private ListUIEventsListener controller;
	private JButton addButton, removeButton;

	private JList<Product> productList;
	private DefaultListModel<Product> productListModel = new DefaultListModel<>();
	
	private JPanel detailsPanel;

	public ListViewFrame()
	{
		super("Super List");

			// creating north buttons panel

		// creating add button
		addButton = new JButton("Add Product", new ImageIcon(getClass().getResource("resources/add1.png")));
		addButton.setRolloverIcon(new ImageIcon(getClass().getResource("resources/add2.png")));
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addProductToUI(new Product("ya","yaa")); 
				controller.addProductToUI(new Product("as","yaa")); 
			}
		});
		// creating remove button
		removeButton = new JButton("Remove Product", new ImageIcon(getClass().getResource("resources/del1.png")));
		removeButton.setRolloverIcon(new ImageIcon(getClass().getResource("resources/del2.png")));
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.removeProductFromUI(productList.getSelectedValue().getName(),productList.getSelectedValue().getBrand()); 
			}
		});
		// creating panel
		JPanel northPanel = new JPanel();
		northPanel.add(addButton);
		northPanel.add(removeButton);
        add(northPanel, BorderLayout.NORTH);

			// creating center data panel

        // creating list
        productList = new JList<>(productListModel);
        productList.setVisibleRowCount(10);

        // creating details
        detailsPanel = new DetailsPanel();

        // creating panel
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.add(productList);
        centerPanel.add(detailsPanel);
        add(centerPanel);
	}

	@Override
	public void registerListener(ListUIEventsListener listener) {
		controller = listener;
	}

	@Override
	public void addProductToUI(Product addedProduct) {
		productListModel.addElement(addedProduct);
	}

	@Override
	public void removeProductFromUI(Product removedProduct) {
		productListModel.removeElement(removedProduct);
		
	}

}