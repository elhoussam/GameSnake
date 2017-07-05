import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainSnake {

	public static void main(String[] args) {
		
		JFrame obj = new JFrame();
		Gameplay gameplay = new Gameplay();
		
		
		
		obj.setBounds(10,10,905,700);
		obj.setBackground(Color.DARK_GRAY); // why this work when i add the gameplaypanel #sam 
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		obj.add(gameplay);
		JOptionPane.showMessageDialog(obj,
			    "Welcome to Snake Game \n You Can increase or Decrese the speed of the snake by pressing (+) or (-) and to move the snake around press (UP) or (DOWN) or (RIGHT) or (LEFT) ");
		
		 
	
	
	}

}
