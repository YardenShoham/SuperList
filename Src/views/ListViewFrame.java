// ListViewFrame.java
package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import bl.Product;
import views.helpers.*;


public class ListViewFrame extends JFrame
{

	JButton addButton, removeButton;

	JList<Product> productList;
	DefaultListModel productListModel = new DefaultListModel(); // to add use productListModel.addElement(new Product("name", "brand", null, null));
	
	JPanel detailsPanel;

	public ListViewFrame()
	{
		super("Super List");

			// creating north buttons panel

		// creating add button
		addButton = new JButton("Add Product", new ImageIcon(getClass().getResource("resources/add1.png")));
		addButton.setRolloverIcon(new ImageIcon(getClass().getResource("resources/add2.png")));

		// creating remove button
		removeButton = new JButton("Remove Product", new ImageIcon(getClass().getResource("resources/del1.png")));
		removeButton.setRolloverIcon(new ImageIcon(getClass().getResource("resources/del2.png")));

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

}