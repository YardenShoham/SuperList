import javax.swing.JFrame;
import javax.swing.UIManager;

import bl.Product;
import bl.SuperList;
import controller.ProductController;
import views.*;

public class Testing {

	public static void main(String[] args) {
		try {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch(Exception e) {}
		
		SuperList listModel = new SuperList();
		
		ListViewFrame top = new ListViewFrame();
		top.setVisible(true);
		top.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		top.setSize(500, 300);
		top.setLocationRelativeTo(null);
		
		ProductController c = new ProductController(listModel,top);
	}

}

