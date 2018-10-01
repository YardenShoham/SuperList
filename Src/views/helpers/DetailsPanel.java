// DetailsPanel.java
package views.helpers;

import javax.swing.*;
import java.awt.*;

import bl.*;

public class DetailsPanel extends JPanel {

	JTextField[] fields;

	int maxFields = 6;

	String[] productFieldNames = {"Name", "Brand", "Note", "Units"};
	String[] basicProductFieldNames = {"Required Quantity", "Quantity at Home"};
	String[] nonessentialProductFieldNames = {"Requested Quantity"};

	JLabel[] productFieldLabels;
	JLabel[] basicProductFieldLabels;
	JLabel[] nonessentialProductFieldLabels;

	public DetailsPanel() {
		setLayout(new GridLayout(maxFields, 2, 5, 5)); 

		fields = new JTextField[maxFields];
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new JTextField(10);
			fields[i].setEditable(false);
		}

		productFieldLabels = new JLabel[productFieldNames.length];
		for (int i = 0; i < productFieldLabels.length; i++)
			productFieldLabels[i] = new JLabel(productFieldNames[i], SwingConstants.RIGHT);

		basicProductFieldLabels = new JLabel[basicProductFieldNames.length];
		for (int i = 0; i < basicProductFieldLabels.length; i++)
			basicProductFieldLabels[i] = new JLabel(basicProductFieldNames[i], SwingConstants.RIGHT);

		nonessentialProductFieldLabels = new JLabel[nonessentialProductFieldNames.length];
		for (int i = 0; i < nonessentialProductFieldLabels.length; i++)
			nonessentialProductFieldLabels[i] = new JLabel(nonessentialProductFieldNames[i], SwingConstants.RIGHT);
		
		setFieldLayout(null);
	}

	public void setFieldLayout(Product product) {
		removeAll();

		// building and filling layout
		int gridRow;
		for (gridRow = 0; gridRow < productFieldLabels.length; gridRow++) {
			add(productFieldLabels[gridRow]);
			add(fields[gridRow]);
		}

		if (product != null) {
			fields[0].setText(product.getName());
			fields[1].setText(product.getBrand());
			fields[2].setText(product.getNote());
			fields[3].setText(product.getUnits());

			if (product.getClass() == BasicProduct.class) {
				for (; gridRow < productFieldLabels.length + basicProductFieldLabels.length; gridRow++) {
					add(basicProductFieldLabels[gridRow - productFieldLabels.length]);
					add(fields[gridRow]);
				}
				BasicProduct basicProduct = (BasicProduct) product;
				fields[4].setText((new Double(basicProduct.getRequiredQuantity())).toString());
				fields[5].setText((new Double(basicProduct.getQuantityAtHome())).toString());
			}
			else if (product.getClass() == NonessentialProduct.class) {
				for (; gridRow < productFieldLabels.length + nonessentialProductFieldLabels.length; gridRow++) {
					add(nonessentialProductFieldLabels[gridRow - productFieldLabels.length]);
					add(fields[gridRow]);
				}
				NonessentialProduct nonessentialProduct = (NonessentialProduct) product;
				fields[4].setText((new Double(nonessentialProduct.getRequestedQuantity())).toString());
			}
		}
		else {
			fields[0].setText(null);
			fields[1].setText(null);
			fields[2].setText(null);
			fields[3].setText(null);
		}
	}
}