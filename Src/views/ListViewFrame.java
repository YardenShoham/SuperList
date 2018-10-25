// ListViewFrame.java
package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bl.Product;
import listeners.ListUIEventsListener;
import views.helpers.DetailsPanel;
import views.helpers.TimeBar;

@SuppressWarnings("serial")
public class ListViewFrame extends JFrame implements AbstractListView {
	private static ListUIEventsListener controller;

	private JList<Product> productList;
	private DefaultListModel<Product> productListModel = new DefaultListModel<>();

	private DetailsPanel detailsPanel;

	public ListViewFrame() {
		super("Super List");
		setResizable(false);
		add(new ButtonsPanel(), BorderLayout.NORTH);
		add(new DataPanel(), BorderLayout.CENTER);
		add(new TimeBar(),BorderLayout.SOUTH);

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

	public void newProductFromUser(String[] details) {
		controller.addProductToUI(new Product(details[0], details[1], details[2], details[3]));
	}

	class ButtonsPanel extends JPanel {
		private JButton addButton, removeButton, modifyEvent;

		public ButtonsPanel() {
			setBackground(Color.WHITE);
			// creating add button
			addButton = new JButton("Add Product", imgIcon("add1.png"));
			addButton.setRolloverIcon(imgIcon("add2.png"));
			addButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame addProductFrame = new AddProductFrame(ListViewFrame.this);
					addProductFrame.setVisible(true);
					addProductFrame.setLocationRelativeTo(null);
				}
			});
			// creating remove button
			removeButton = new JButton("Remove Product", imgIcon("del1.png"));
			removeButton.setRolloverIcon(imgIcon("del2.png"));
			removeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Product selectedValue = productList.getSelectedValue();
					if (selectedValue != null)
						controller.removeProductFromUI(selectedValue.getName(), selectedValue.getBrand());
				}
			});

			/*
			 * // "Modify Product" Button modifyButton = new
			 * JButton("Modify Product",imgIcon("mod1.png"));
			 * modifyButton.setRolloverIcon(imgIcon("mod2.png"));
			 * modifyButton.addActionListener(new ActionListener() {
			 * 
			 * @Override public void actionPerformed(ActionEvent e) { Product selectedValue
			 * = productList.getSelectedValue(); if (selectedValue != null)
			 * controller.removeProductFromUI(selectedValue.getName(),
			 * selectedValue.getBrand());
			 * 
			 * } });
			 */

			add(addButton);
			add(removeButton);

		}

		private Icon imgIcon(String name) {
			return new ImageIcon(getClass().getResource("resources/" + name));
		}

	}

	class DataPanel extends JPanel {
		DataPanel() {
			super(new GridLayout(1, 2, 10, 10));
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
			add(productList);
			add(detailsPanel);
		}
	}

}
