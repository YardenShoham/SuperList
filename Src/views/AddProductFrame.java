package views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import bl.Product;

public class AddProductFrame extends JFrame {
	private String[] strProductType = { "Basic Product", "Non-Essential Product" };
	private JRadioButton[] productTypeChoose;
	
	private JPanel mainPanel;
	private JPanel detailsPanel;
	
	private static int indexDetail = 0;
	private String[] productTemplate;

	public String[] getProductTemplate() {
		return productTemplate;
	}

	public ArrayList<String> generateLabels(){
		ArrayList<String> temp = new ArrayList<>();
		temp.add("Name");temp.add("Brand");temp.add("Note");temp.add("Units");
		return temp;
	}
	
	public AddProductFrame() {
		indexDetail = 0;		//a static member. need to an initial value
		setSize(300, 150);
		getContentPane().setBackground(Color.WHITE);
		
		mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createTitledBorder("Insert your product details: "));
		
		// Radio buttons
		ButtonGroup bg = new ButtonGroup();
		productTypeChoose = new JRadioButton[strProductType.length];
		for (int i = 0; i < strProductType.length; i++) {
			productTypeChoose[i] = new JRadioButton(strProductType[i]);
			productTypeChoose[i].setActionCommand(strProductType[i]);
			bg.add(productTypeChoose[i]);
			mainPanel.add(productTypeChoose[i]);
		}
		productTypeChoose[0].setSelected(true);		//initial value
		
		//details panel
//		String[] strDetail = { "Name", "Brand", "Note", "Units" };
		ArrayList<String> strDetail = generateLabels();
		JLabel lblDetail = new JLabel(strDetail.get(0) + ": ");
		JTextField txtDetail = new JTextField(10);
		JButton btnConfirmDetail = new JButton("OK");
		productTemplate = new String[4];
		detailsPanel = new JPanel(new FlowLayout());
		detailsPanel.add(lblDetail);
		detailsPanel.add(txtDetail);
		detailsPanel.add(btnConfirmDetail);
		
		btnConfirmDetail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(indexDetail==0) FirstOK();
				productTemplate[indexDetail] = txtDetail.getText();
				txtDetail.setText("");
				if (indexDetail == strDetail.size()-1) {
					setVisible(false);
					ListViewFrame.newProductFromUser(productTemplate);
				} else
					lblDetail.setText(strDetail.get(++indexDetail) + ": ");
			}
			
			public void FirstOK() {
				for(int i=0;i<productTypeChoose.length;i++)
					productTypeChoose[i].setEnabled(false);
	//			if(productTypeChoose[0].isSelected()) strDetail.add("Requird Quanity");
			}
		});
		getRootPane().setDefaultButton(btnConfirmDetail);	//get text by pressing enter
		
		mainPanel.add(detailsPanel);
		add(mainPanel);

	}
}
