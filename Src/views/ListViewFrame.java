// ListViewFrame.java
package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;

import bl.Product;
import listeners.ListUIEventsListener;
import views.AbstractListView;
import views.helpers.*;

@SuppressWarnings("serial")
public class ListViewFrame extends JFrame implements AbstractListView {
	private static ListUIEventsListener controller;
	private JButton addButton, removeButton,modifyButton;

	private JList<Product> productList;
	private DefaultListModel<Product> productListModel = new DefaultListModel<>();

	private DetailsPanel detailsPanel;

	public ListViewFrame() {
		super("Super List");
		setResizable(false);
		// creating north buttons panel

		// creating add button
		addButton = new JButton("Add Product", new ImageIcon(getClass().getResource("resources/add1.png")));
		addButton.setRolloverIcon(new ImageIcon(getClass().getResource("resources/add2.png")));
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame addProductFrame = new AddProductFrame();
				addProductFrame.setVisible(true);
				addProductFrame.setLocationRelativeTo(null);
			}
		});
		// creating remove button
		removeButton = new JButton("Remove Product", new ImageIcon(getClass().getResource("resources/del1.png")));
		removeButton.setRolloverIcon(new ImageIcon(getClass().getResource("resources/del2.png")));
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product selectedValue = productList.getSelectedValue();
				if (selectedValue != null)
					controller.removeProductFromUI(selectedValue.getName(), selectedValue.getBrand());
			}
		});
		
/*		// "Modify Product" Button
		modifyButton = new JButton("Modify Product", new ImageIcon(getClass().getResource("resources/mod1.png")));
		modifyButton.setRolloverIcon(new ImageIcon(getClass().getResource("resources/mod2.png")));
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product selectedValue = productList.getSelectedValue();
				if (selectedValue != null)
					controller.removeProductFromUI(selectedValue.getName(), selectedValue.getBrand());
					
			}
		});
*/		
		// creating panel
		JPanel northPanel = new JPanel();
		northPanel.add(addButton);
		northPanel.add(removeButton);
		add(northPanel, BorderLayout.NORTH);

		// creating center data panel

		// creating list
		productList = new JList<>(productListModel);
		productList.setVisibleRowCount(10);
		productList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				detailsPanel.setFieldLayout(productList.getSelectedValue());
			}
		});

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
	
	public static void newProductFromUser(String[] details) {
		controller.addProductToUI(new Product(details[0], details[1],
				details[2], details[3]));
	}

}