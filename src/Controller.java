import javax.swing.*;

//import com.sun.tools.javac.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	static boolean press; // static variable used in playerTurn method to check if jbutton is pressed.

	static int[][] playerMoves = new int[3][3]; // int 2D array that will hold the positions for the player selections
												// to determine if they win
	static int[][] computerMoves = new int[3][3]; // int 2D array that will hold the positions for the computer
													// selections to determine if they win

	public static void main(String[] args) {
		buildGameView();
		// buildGameModel();
		// playGame();

	}

	private static void buildGameView() {
		// TASK1: CREATE A FRAME, SET THE TITLE AND DIMENSIONS, MAKE VISIBLE
		JFrame frame = new JFrame(); // create a new JFrame
		frame.setTitle("Tic-Tac-Toe Application"); // set the title
		frame.setSize(500, 550); // specify the dimensions of the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make sure the frame can be closes

		// TASK 2: INSTANTIATE A GameView OBJECT AND ADD IT TO THE FRAME

		GameView view = new GameView();
		frame.add(view); // add the J panel to the J frame
		frame.setVisible(true); // show the j frame
		JOptionPane.showMessageDialog(frame, "Welcome to Tic-Tac-Toe!\n"
				+ "The player always goes first in this application." + "\nClick a button to start the game!");

		buildGameModel(view, frame); // build game model
	}

	private static void buildGameModel(GameView view, JFrame frame) {
		Game game = new Game(); // create game
		Player p1 = new Player(); // create object player
		Computer c = new Computer(); // add a computer to the game
		game.attach(p1); // attach the object(player) to be added to the game
		game.attach(c); // attach the object(computer) to be added to the game
		StateGameTicTac start; // notifys the observers(player and computer) with a boolean
		game.notifyTurn(start = new StateGameTicTac(true, false));

		playGame(view, game, p1, c, start, frame); // play the game, calls method.

	}

	private static void playGame(GameView view, Game game, Player p, Computer c, StateGameTicTac play, JFrame frame) {
		boolean playerGameWin = false; // if player wins game, turns true
		boolean computerGameWin = false; // if computer wins game, turns true

		// Loop to control the game
		while (!playerGameWin || !computerGameWin) {
			if (play.getPTurn()) {
				playerTurn(view, game, p, c); // calls method to make a player move
				playerGameWin = checkWin(playerMoves); // calls method to check if player won after turn
			}
			if (play.getCTurn()) {
				computerTurn(view, game, p, c); // calls method to make a computer move
				computerGameWin = checkWin(playerMoves);// calls method to check if computer won after turn
			}
			break;
		}
		if (playerGameWin) {
			JOptionPane.showMessageDialog(frame, "Congratulations Player, You beat the computer and WON the game!");
		} else if (computerGameWin) {
			JOptionPane.showMessageDialog(frame, "Sorry Player, the computer has won the game. You LOST :(");
		} else {
			JOptionPane.showMessageDialog(frame, "Sorry Player, you have tied the computer. Nobody has won the game.");
		}

	}

	// method performs the actions of a player and their selection button selection
	private static void playerTurn(GameView view, Game game, Player p, Computer c) {
		// entered = true;
		press = true;
		
		//Loop to add actionListener to buttons to allow an action after clicking
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				view.squares[i][j].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						for (int i = 0; i < 3; i++) {
							for (int j = 0; j < 3; j++) {
								JButton source = (JButton) e.getSource();
								if (source == view.squares[i][j] && press) {
									if (view.squares[i][j].getText().equals("")) {
										view.squares[i][j].setText("X");
										view.squares[i][j].disable();// disable the button that was used to prevent use again.
										press = false;
										return;
									}
								}

							}
						}
					}
				});
			}
		}

		game.notifyTurn(new StateGameTicTac(false, true)); //gives new to observers to end player turn and start computer turn
	}

	// peforms the action of a computer and their button selection
	private static void computerTurn(GameView view, Game game, Player p, Computer c) {
		int num1 = (int) (3 * Math.random()); // create random number from 0,2 to pick random position for computer
		int num2 = (int) (3 * Math.random()); // create random number from 0,2 to pick random position for computer
		boolean notTaken = true;// this will determine if a spot is taken already in the jbutton array, if it
								// becomes false a spot was open.

		while (notTaken) {
			if (view.squares[num1][num2].getText().equals("")) {
				view.squares[num1][num2].setText("O");
				notTaken = false;
			}
		}
		game.notifyTurn(new StateGameTicTac(true, false)); //gives new message to observers to end computer turn and start player turn
	}

	// Method that has algorithms to check if the player or the computer wins, one
	// array is passed in.
	private static boolean checkWin(int checkwinArr[][]) {
		// ROW WINS
		// top row
		if ((checkwinArr[0][0] == 1) && (checkwinArr[0][1] == 1) && (checkwinArr[0][2] == 1)) {
			return true;

			// middle row
		} else if ((checkwinArr[1][0] == 1) && (checkwinArr[1][1] == 1) && (checkwinArr[1][2] == 1)) {
			return true;

			// bottom row
		} else if ((checkwinArr[2][0] == 1) && (checkwinArr[2][1] == 1) && (checkwinArr[2][2] == 1)) {
			return true;

			// COLUMN WINS
			// left column
		} else if ((checkwinArr[0][0] == 1) && (checkwinArr[1][0] == 1) && (checkwinArr[2][0] == 1)) {
			return true;

			// middle column
		} else if ((checkwinArr[0][1] == 1) && (checkwinArr[1][1] == 1) && (checkwinArr[2][1] == 1)) {
			return true;

			// right column
		} else if ((checkwinArr[0][2] == 1) && (checkwinArr[1][2] == 1) && (checkwinArr[2][2] == 1)) {
			return true;

			// DIAGNOL WINS
			// Diagnol top left
		} else if ((checkwinArr[0][0] == 1) && (checkwinArr[1][1] == 1) && (checkwinArr[2][2] == 1)) {
			return true;
			// Diagnol top right
		} else if ((checkwinArr[0][0] == 1) && (checkwinArr[0][1] == 1) && (checkwinArr[0][2] == 1)) {
			return true;
		} else {
			return false;
		}
	}

}