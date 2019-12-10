import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GameView extends JPanel{
	public static final long serialVersionUID = 1L;
	//view members
	public JButton [][] squares;
	
	//Constructor
	public GameView() {
		//Task 1: Set the gridlayout and background color
		setLayout(new GridLayout(3,3,5,5));
		setBackground(Color.black);
		
		//Task 2: instantiate squares
		squares = new JButton[3][3];
		
		//Task 3: add buttons to the array. place an "" in each button.
		for(int i=0; i<3;i++) {
			for(int j =0; j <3; j++) {
				JButton button = new JButton("");
				button.setFont(new Font("Arial",Font.BOLD,100));
				button.setBorderPainted(true);
				squares[i][j]= button;
				this.add(squares[i][j]);
			}
		}
		
	}
	
}
