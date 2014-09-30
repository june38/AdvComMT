package Restaurant;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

public class RestaurantPOS extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String foodList[] = {"","YOLO", "Steak", "Hamburger" ,"Sushi", "Tacos", "Spaghetti", "Noodle", "Doughnut", "Hot Dog"};
	String drinksList[] = {"","Mineral Water", "Cola", "Orange Juice", "Apple Juice", "Guava Juice", "Coconut Juice", "Heineken", "Coffee", "Tea"};
	JPanel foodPanel, drinksPanel, customerPanel, centerPanel;
	JLabel foodLabel, drinksLabel, sliderLabel;
	//JTextComponent welcomeTextComponent;
	JComboBox foodBox, drinksBox;
	JButton leftButton, rightButton, orderButton, billsButton, tAndBButton;
	JSlider customerSlider;
	JTextArea textArea;
	String welcomeMessage = "   WELCOME TO KUNNAPAT CAFE\n   since 1994 ,  World's Best Cafe.\n\n            Have a great meal!";
	
	
	
	public RestaurantPOS(String title) { //Constructor
		super(title);
		createDrinksPanel();
		this.add(drinksPanel, BorderLayout.EAST);
		createFoodPanel();
		this.add(foodPanel, BorderLayout.WEST);
       	createCustomerPanel();
		this.add(customerPanel,BorderLayout.SOUTH);
		createCenterPanel();
		this.add(centerPanel,BorderLayout.CENTER);
		
	}
	

	private void createCenterPanel() {
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(4,0));
		orderButton = new JButton("ORDER :)");
		orderButton.setActionCommand("order");
		orderButton.addActionListener(this);
		billsButton = new JButton("BILLS :)");
		billsButton.setActionCommand("bills");
		billsButton.addActionListener(this);
		tAndBButton = new JButton("Take Away Order");
		tAndBButton.setActionCommand("tandb");
		tAndBButton.addActionListener(this);
		textArea = new JTextArea();
		textArea.setBackground(Color.white);
		textArea.setForeground(Color.black);
		textArea.setFont(new Font("SansSerif",Font.PLAIN,15));
        textArea.setText(welcomeMessage);
		centerPanel.add(orderButton);
		centerPanel.add(billsButton);
		centerPanel.add(tAndBButton);
		centerPanel.add(textArea);
		
	}


	private void createCustomerPanel() {
		customerPanel = new JPanel();
		leftButton = new JButton("HIDE");
		leftButton.setActionCommand("hide");
		leftButton.addActionListener(this);
		rightButton = new JButton("SHOW");
		rightButton.setActionCommand("show");
		rightButton.addActionListener(this);
		customerSlider = new JSlider();
		sliderLabel = new JLabel("Customer:");
		
		customerPanel.add(leftButton, BorderLayout.WEST);
		customerPanel.add(rightButton, BorderLayout.EAST);
		customerPanel.add(sliderLabel,BorderLayout.CENTER);
		customerPanel.add(customerSlider,BorderLayout.CENTER);
	}


	private void createFoodPanel() {
		foodPanel = new JPanel();
		foodLabel = new JLabel("Menu List: ");
		foodBox = new JComboBox(foodList);
		
		foodPanel.add(foodLabel);
		foodPanel.add(foodBox);
	}
	private void createDrinksPanel() {
		drinksPanel = new JPanel();
		drinksLabel = new JLabel("Drinks List: ");
		drinksBox = new JComboBox(drinksList);
		
		drinksPanel.add(drinksLabel);
		drinksPanel.add(drinksBox, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton)e.getSource();
		if(src.getActionCommand().equals("hide")){
			sliderLabel.setVisible(false);
        	customerSlider.setVisible(false);
        } else if(src.getActionCommand().equals("show")){
        	sliderLabel.setVisible(true);
        	customerSlider.setVisible(true);
        } else if(src.getActionCommand().equals("order")){
        	String foodTemp = foodBox.getSelectedItem().toString();
        	String drinksTemp = drinksBox.getSelectedItem().toString();
        	if(foodTemp.equals("") && drinksTemp.equals("")){
        		System.out.println(">>Please select a food or drink. ");
        	}
        	else if (foodTemp.equals("") && !drinksTemp.equals("")){
         		System.out.println("**Customer order for " + drinksTemp );
         	} else if (!foodTemp.equals("") && drinksTemp.equals("")){
         		System.out.println("**Customer order for " + foodTemp );
         	} else {
         		System.out.println("**Customer order for " + foodTemp + " and " + drinksTemp );
         	}
        	
        	
        } else if(src.getActionCommand().equals("bills")){
        	System.out.println("\n\n\n******************************\nCustomer calls for bills\n******************************");
        } else if(src.getActionCommand().equals("tandb")){
        	String foodTemp = foodBox.getSelectedItem().toString();
        	String drinksTemp = drinksBox.getSelectedItem().toString();
        	if(foodTemp.equals("") && drinksTemp.equals("")){
        		System.out.println(">>Please select a food or drink. ");
        	}
        	else if (foodTemp.equals("") && !drinksTemp.equals("")){
         		System.out.println("TAKE AWAY: Customer order for " + drinksTemp );
         	} else if (!foodTemp.equals("") && drinksTemp.equals("")){
         		System.out.println("TAKE AWAY: Customer order for " + foodTemp );
         	} else {
         		System.out.println("TAKE AWAY: Customer order for " + foodTemp + " and " + drinksTemp );
         	}
        }
        
    }
	
	private static void createAndShowGUI() {
		RestaurantPOS frame = new RestaurantPOS("KUNNAPAT CAFE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,400);
        frame.setBackground(Color.pink);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
     
    }
	
 
    public static void main(String[] args) {
       SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	System.out.println("==============================\nWelcome to Kunnapat Dining.\n==============================");
            	createAndShowGUI();
            }
        });
    }

}