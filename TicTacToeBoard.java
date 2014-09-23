package tictactoeboard;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

/*
 * A Tic Tac Toe Program
 * 
 * by Maurice O'Connor
 * 
 * This is a program in which you play tic tac toe against the computer in a 
 * GUI environment
 * 
 * Note: I converted this from a program in which the program reads in a 
 * (semi-completed) board then finishes the game with both sides using 
 * perfect play, computing who wins. I include this note so that if a 
 * reviewer is wondering about any part of the program "Why did he do it 
 * that way?", this may (hopefully) help explain why ;) 
 * 
 */


public class TicTacToeBoard {
	char player = 'X', waiter = 'O', winner = 'C'; // keeps track of who's 
	// turn it is
	char grid[][] = new char[3][3]; // the 2D array which holds the board
	int responseAgain; // this holds the answer to the 'Play again?' dialog
	boolean compFirst = false;
	JButton topLeft, topMiddle, topRight;
	JButton middleLeft, middleMiddle, middleRight;
	JButton bottomLeft, bottomMiddle, bottomRight;
	JFrame jfrm;
	JPanel jpnl;
	String[] symbolOpts = { "X", "O" }; // for the askSymbol method



	public TicTacToeBoard() {
		
		// set up the board
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				grid[i][j] = '_';
			}
		}
		
		// set up the GUI components 
		
		Font f = new Font(Font.SANS_SERIF,Font.PLAIN , 80);

		jfrm = new JFrame("Tic-Tac-Toe");

		jfrm.getContentPane().setLayout(new FlowLayout());

		jfrm.setSize(420, 360);

		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jpnl = new JPanel();

		jpnl.setPreferredSize(new Dimension(400, 400));

		jpnl.setOpaque(true);

		jpnl.setLayout(new FlowLayout());

		topLeft = new JButton("");

		topLeft.setPreferredSize(new Dimension(100, 100));

		topMiddle = new JButton("");

		topMiddle.setPreferredSize(new Dimension(100, 100));

		topRight = new JButton("");

		topRight.setPreferredSize(new Dimension(100, 100));

		middleLeft = new JButton("");

		middleLeft.setPreferredSize(new Dimension(100, 100));

		middleMiddle = new JButton("");

		middleMiddle.setPreferredSize(new Dimension(100, 100));

		middleRight = new JButton("");

		middleRight.setPreferredSize(new Dimension(100, 100));

		bottomLeft = new JButton("");

		bottomLeft.setPreferredSize(new Dimension(100, 100));

		bottomMiddle = new JButton("");

		bottomMiddle.setPreferredSize(new Dimension(100, 100));

		bottomRight = new JButton("");

		bottomRight.setPreferredSize(new Dimension(100, 100));

		topLeft.setFont(f);
		topMiddle.setFont(f);
		topRight.setFont(f);
		middleLeft.setFont(f);
		middleMiddle.setFont(f);
		middleRight.setFont(f);
		bottomLeft.setFont(f);
		bottomMiddle.setFont(f);
		bottomRight.setFont(f);


		topLeft.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JButton) e.getSource()).getText() == "") {
					((JButton) e.getSource())
					.setText(Character.toString(player));
					// mark the GUI's board with the user's selection

					grid[0][0] = player; // mark the underlying board with 
					// user's selection

					winner = checkWin(grid); 

					if(winner == player) {
						JOptionPane.showMessageDialog(jfrm, "You win!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

						return;
					}
					if(winner == 'D') {
						JOptionPane.showMessageDialog(jfrm, "Draw!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

						return;
					}

					player = (char) (player + waiter); // swap whose turn it is
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);

					winner = computerGo(grid); // this method plays the 
					// computer's turn

					if(compFirst) winner = checkWin(grid);
					// if the computer goes first it also goes last (there 
					// are 9 moves). This if clause ensures that, if the 
					// board is full when the computer makes its move, it is 
					// registered as a draw.

					if(winner == 'X' || winner == 'O') {
						JOptionPane.showMessageDialog(jfrm, "Computer wins!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

					}

					if(winner == 'D') {
						JOptionPane.showMessageDialog(jfrm, "Draw!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

					}
				}

			}
		});

		topMiddle.addActionListener(new ActionListener() {
			// the statements below are explained above in the 'topLeft' 
			// button's action listener

			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JButton) e.getSource()).getText() == "") {
					((JButton) e.getSource())
					.setText(Character.toString(player));

					grid[0][1] = player;

					winner = checkWin(grid);

					if(winner == player) {
						JOptionPane.showMessageDialog(jfrm, "You win!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) { 
							reset();
							askSymbol();
						} else buttonsEnabled(false);

						return;
					}
					if(winner == 'D') {
						JOptionPane.showMessageDialog(jfrm, "Draw!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

						return;
					}

					player = (char) (player + waiter); // swap whose turn it is
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);

					winner = computerGo(grid);

					if(compFirst) winner = checkWin(grid);

					if(winner == 'X' || winner == 'O') {
						JOptionPane.showMessageDialog(jfrm, "Computer wins!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

					}

					if(winner == 'D') {
						JOptionPane.showMessageDialog(jfrm, "Draw!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

					}
				}

			}
		});

		topRight.addActionListener(new ActionListener() {
			// the statements below are explained above in the 'topLeft' 
			// button's action listener

			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JButton) e.getSource()).getText() == "") {
					((JButton) e.getSource())
					.setText(Character.toString(player));

					grid[0][2] = player;

					winner = checkWin(grid);

					if(winner == player) {
						JOptionPane.showMessageDialog(jfrm, "You win!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

						return;
					}
					if(winner == 'D') {
						JOptionPane.showMessageDialog(jfrm, "Draw!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

						return;
					}

					player = (char) (player + waiter); // swap whose turn it is
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);

					winner = computerGo(grid);

					if(compFirst) winner = checkWin(grid);

					if(winner == 'X' || winner == 'O') {
						JOptionPane.showMessageDialog(jfrm, "Computer wins!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} buttonsEnabled(false);

					}

					if(winner == 'D') {
						JOptionPane.showMessageDialog(jfrm, "Draw!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

					}
				}
			}
		});

		middleLeft.addActionListener(new ActionListener() {
			// the statements below are explained above in the 'topLeft' 
			// button's action listener
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JButton) e.getSource()).getText() == "") { 
					((JButton) e.getSource())
					.setText(Character.toString(player));

					grid[1][0] = player;

					winner = checkWin(grid);

					if(winner == player) {
						JOptionPane.showMessageDialog(jfrm, "You win!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

						return;
					}
					if(winner == 'D') {
						JOptionPane.showMessageDialog(jfrm, "Draw!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

						return;
					}

					player = (char) (player + waiter); // swap whose turn it is
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);

					winner = computerGo(grid);

					if(compFirst) winner = checkWin(grid);

					if(winner == 'X' || winner == 'O') {
						JOptionPane.showMessageDialog(jfrm, "Computer wins!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);
					}

					if(winner == 'D') {
						JOptionPane.showMessageDialog(jfrm, "Draw!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);
					}
				}
			}
		});

		middleMiddle.addActionListener(new ActionListener() {
			// the statements below are explained above in the 'topLeft' 
			// button's action listener
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JButton) e.getSource()).getText() == "") {
					((JButton) e.getSource())
					.setText(Character.toString(player));

					grid[1][1] = player;

					winner = checkWin(grid);

					if(winner == player) {
						JOptionPane.showMessageDialog(jfrm, "You win!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

						return;
					}
					if(winner == 'D') {
						JOptionPane.showMessageDialog(jfrm, "Draw!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

						return;
					}

					player = (char) (player + waiter); // swap whose turn it is
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);

					winner = computerGo(grid);

					if(compFirst) winner = checkWin(grid);

					if(winner == 'X' || winner == 'O') {
						JOptionPane.showMessageDialog(jfrm, "Computer wins!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

					}

					if(winner == 'D') {
						JOptionPane.showMessageDialog(jfrm, "Draw!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

					}
				}
			}
		});

		middleRight.addActionListener(new ActionListener() {
			// the statements below are explained above in the 'topLeft' 
			// button's action listener

			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JButton) e.getSource()).getText() == "") {
					((JButton) e.getSource())
					.setText(Character.toString(player));

					grid[1][2] = player;

					winner = checkWin(grid);

					if(winner == player) {
						JOptionPane.showMessageDialog(jfrm, "You win!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

						return;
					}
					if(winner == 'D') {
						JOptionPane.showMessageDialog(jfrm, "Draw!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

						return;
					}

					player = (char) (player + waiter); // swap whose turn it is
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);

					winner = computerGo(grid);

					if(compFirst) winner = checkWin(grid);

					if(winner == 'X' || winner == 'O') {
						JOptionPane.showMessageDialog(jfrm, "Computer wins!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

					}

					if(winner == 'D') {
						JOptionPane.showMessageDialog(jfrm, "Draw!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

					}
				}
			}
		});

		bottomLeft.addActionListener(new ActionListener() {
			// the statements below are explained above in the 'topLeft' 
			// button's action listener

			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JButton) e.getSource()).getText() == "") {
					((JButton) e.getSource())
					.setText(Character.toString(player));

					grid[2][0] = player;

					winner = checkWin(grid);

					if(winner == player) {
						JOptionPane.showMessageDialog(jfrm, "You win!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

						return;
					}
					if(winner == 'D') {
						JOptionPane.showMessageDialog(jfrm, "Draw!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

						return;
					}

					player = (char) (player + waiter); // swap whose turn it is
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);

					winner = computerGo(grid);

					if(compFirst) winner = checkWin(grid);

					if(winner == 'X' || winner == 'O') {
						JOptionPane.showMessageDialog(jfrm, "Computer wins!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

					}

					if(winner == 'D') {
						JOptionPane.showMessageDialog(jfrm, "Draw!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

					}
				}
			}
		});

		bottomMiddle.addActionListener(new ActionListener() {
			// the statements below are explained above in the 'topLeft' 
			// button's action listener

			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JButton) e.getSource()).getText() == "") { 
					((JButton) e.getSource())
					.setText(Character.toString(player));

					grid[2][1] = player;

					winner = checkWin(grid);

					if(winner == player) {
						JOptionPane.showMessageDialog(jfrm, "You win!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

						return;
					}
					if(winner == 'D') {
						JOptionPane.showMessageDialog(jfrm, "Draw!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

						return;
					}

					player = (char) (player + waiter); // swap whose turn it is
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);

					winner = computerGo(grid);

					if(compFirst) winner = checkWin(grid);

					if(winner == 'X' || winner == 'O') {
						JOptionPane.showMessageDialog(jfrm, "Computer wins!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

					}

					if(winner == 'D') {
						JOptionPane.showMessageDialog(jfrm, "Draw!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

					}
				}
			}
		});

		bottomRight.addActionListener(new ActionListener() {
			// the statements below are explained above in the 'topLeft' 
			// button's action listener

			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JButton) e.getSource()).getText() == "") {
					((JButton) e.getSource())
					.setText(Character.toString(player));

					grid[2][2] = player;

					winner = checkWin(grid);

					if(winner == player) {
						JOptionPane.showMessageDialog(jfrm, "You win!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

						return;
					}
					if(winner == 'D') {
						JOptionPane.showMessageDialog(jfrm, "Draw!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

						return;
					}

					player = (char) (player + waiter); // swap whose turn it is
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);

					winner = computerGo(grid);

					if(compFirst) winner = checkWin(grid);

					if(winner == 'X' || winner == 'O') {
						JOptionPane.showMessageDialog(jfrm, "Computer wins!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

					}

					if(winner == 'D') {
						JOptionPane.showMessageDialog(jfrm, "Draw!",
								"End of Game", JOptionPane.PLAIN_MESSAGE);
						responseAgain = JOptionPane.showConfirmDialog(jfrm, 
								"Play again?", "", JOptionPane.YES_NO_OPTION);
						if(responseAgain == JOptionPane.YES_OPTION) {
							reset();
							askSymbol();
						} else buttonsEnabled(false);

					}
				}
			}
		});

		jpnl.add(topLeft);
		jpnl.add(topMiddle);
		jpnl.add(topRight);
		jpnl.add(middleLeft);
		jpnl.add(middleMiddle);
		jpnl.add(middleRight);
		jpnl.add(bottomLeft);
		jpnl.add(bottomMiddle);
		jpnl.add(bottomRight);

		jfrm.getContentPane().add(jpnl);

		JMenuBar jmb = new JMenuBar();

		JMenu jmFile = new JMenu("File");
		jmFile.setMnemonic(KeyEvent.VK_F);
		JMenuItem jmiNewGame = new JMenuItem("New game", KeyEvent.VK_N);
		jmiNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 
				0));
		JMenuItem jmiQuit = new JMenuItem("Quit", KeyEvent.VK_Q);
		jmiQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 
				InputEvent.CTRL_MASK));
		jmFile.add(jmiNewGame);
		jmFile.addSeparator();
		jmFile.add(jmiQuit);
		jmb.add(jmFile);

		JMenu jmHelp = new JMenu("Help");
		JMenuItem jmiAbout = new JMenuItem("About");
		jmHelp.add(jmiAbout);
		jmb.add(jmHelp);

		jmiNewGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
				askSymbol();
				buttonsEnabled(true);
			}
		});

		jmiQuit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		jmiAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(jfrm, 
						"Tic-Tac-Toe Game\n           by\nMaurice O'Connor", 
						"About", JOptionPane.INFORMATION_MESSAGE);

			}
		});

		jfrm.setJMenuBar(jmb);

		jfrm.setResizable(false);

		jfrm.setVisible(true);

		askSymbol(); // asks which symbol to play as when the program first
		// loads


	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new TicTacToeBoard();

			}
		});
	}

	/*
	 *  Ask what symbol they want to play as
	 */

	void askSymbol() {
		int responseSymbol = JOptionPane.showOptionDialog(jfrm, 
				"Do you want to be:", 
				"New Game", 
				JOptionPane.DEFAULT_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				symbolOpts, "X");

		switch(responseSymbol) {
		case 0:
			player = 'X';
			waiter = 'O';
			break;
		case 1:
			player = 'O';
			waiter = 'X';
			break;
		}

		if(player == 'O') {
			player = 'X';
			waiter = 'O';
			compFirst = true;
			winner = computerGo(grid);
		} else compFirst = false;
	}


	/*
	 * 
	 * reset game
	 * 
	 */

	void reset() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				grid[i][j] = '_';
			}
		}
		topLeft.setText("");
		topMiddle.setText("");
		topRight.setText("");
		middleLeft.setText("");
		middleMiddle.setText("");
		middleRight.setText("");
		bottomLeft.setText("");
		bottomMiddle.setText("");
		bottomRight.setText("");	
	}

	/*
	 * Enable or disable buttons
	 */

	void buttonsEnabled(boolean state) {
		topLeft.setEnabled(state);
		topMiddle.setEnabled(state);
		topRight.setEnabled(state);
		middleLeft.setEnabled(state);
		middleMiddle.setEnabled(state);
		middleRight.setEnabled(state);
		bottomLeft.setEnabled(state);
		bottomMiddle.setEnabled(state);
		bottomRight.setEnabled(state);
	}

	/*
	 *  check if win
	 *  
	 *  return winner or 'continue'
	 *  
	 */

	char checkWin(char[][] a) {
		int x, y;

		// check rows and columns

		for(int i = 0; i < 3; i++) {
			x = 0;
			y = 0;
			for (int j = 0; j < 3; j++) {
				if (a[i][j] == 'X') x += 1;
				if (a[i][j] == 'O') y += 1;
			}

			if(x == 3) return 'X';
			if(y == 3) return 'O';

			x = 0;
			y = 0;
			for (int j = 0; j < 3; j++) {
				if (a[j][i] == 'X') x += 1;
				if (a[j][i] == 'O') y += 1;
			}

			if(x == 3) return 'X';
			if(y == 3) return 'O';
		}

		// check diagonals

		x = 0;
		y = 0;
		if(a[0][0] == 'X') x += 1;
		if(a[0][0] == 'O') y += 1;

		if(a[1][1] == 'X') x += 1;
		if(a[1][1] == 'O') y += 1;

		if(a[2][2] == 'X') x += 1;
		if(a[2][2] == 'O') y += 1;

		if(x == 3) return 'X';
		if(y == 3) return 'O';

		x = 0;
		y = 0;
		if(a[0][2] == 'X') x += 1;
		if(a[0][2] == 'O') y += 1;

		if(a[1][1] == 'X') x += 1;
		if(a[1][1] == 'O') y += 1;

		if(a[2][0] == 'X') x += 1;
		if(a[2][0] == 'O') y += 1;

		if(x == 3) return 'X';
		if(y == 3) return 'O';

		x = 0;

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(a[i][j] != '_') x += 1;
			}
		}
		if(x == 9) return 'D';

		return 'C';


	}

	/*
    Checks for optimal move and either: 

    Makes the move. 
    Changes turn.
    Returns "continue",

    or
    
	Makes the move.
    Returns win for X, win for O or draw.
    
    This method follows the 'Perfect Play' algorithm for tic tac toe
    which you can find here:
    
    http://en.wikipedia.org/wiki/Tic-tac-toe
    
    under 'Strategy'

	 */    
	char computerGo(char[][] a) {
		int x, y, z = 0, i;
		boolean boolTopLeft, boolTopRight, boolBottomLeft, boolBottomRight;


		// check for ability to win in next move

		// check rows and columns

		for(i = 0; i < 3; i++) {

			x = 0;
			y = 0;
			z = 0;
			{    
				if(a[i][0] == player) x += 1;
				else z = 1;
				if(a[i][0] == waiter) y += 1;
			}
			{
				if(a[i][1] == player) x += 1;
				else z = 2;
				if(a[i][1] == waiter) y += 1;
			}
			{
				if(a[i][2] == player) x += 1;
				else z = 3;
				if(a[i][2] == waiter) y += 1;
			} 

			if(x == 2 && y != 1) {
				a[i][z-1] = player;
				if(i == 0) {
					if(z == 1) topLeft.setText(Character.toString(player));
					if(z == 2) topMiddle.setText(Character.toString(player));
					if(z == 3) topRight.setText(Character.toString(player));
				}
				if(i == 1) {
					if(z == 1) middleLeft.setText(Character.toString(player));
					if(z == 2) 
						middleMiddle.setText(Character.toString(player));
					if(z == 3) middleRight.setText(Character.toString(player));
				}
				if(i == 2) {
					if(z == 1) bottomLeft.setText(Character.toString(player));
					if(z == 2) 
						bottomMiddle.setText(Character.toString(player));
					if(z == 3) bottomRight.setText(Character.toString(player));

				}
				return player;
			}            

			x = 0;
			y = 0;
			z = 0;

			{    
				if(a[0][i] == player) x += 1;
				else z = 1;
				if(a[0][i] == waiter) y += 1;
			}
			{
				if(a[1][i] == player) x += 1;
				else z = 2;
				if(a[1][i] == waiter) y += 1;
			}
			{
				if(a[2][i] == player) x += 1;
				else z = 3;
				if(a[2][i] == waiter) y += 1; 
			}    

			if(x == 2 && y != 1) {
				a[z-1][i] = player;
				if(i == 0) {
					if(z == 1) topLeft.setText(Character.toString(player));
					if(z == 2) middleLeft.setText(Character.toString(player));
					if(z == 3) bottomLeft.setText(Character.toString(player));
				}
				if(i == 1) {
					if(z == 1) topMiddle.setText(Character.toString(player));
					if(z == 2) 
						middleMiddle.setText(Character.toString(player));
					if(z == 3) 
						bottomMiddle.setText(Character.toString(player));
				}
				if(i == 2) {
					if(z == 1) topRight.setText(Character.toString(player));
					if(z == 2) middleRight.setText(Character.toString(player));
					if(z == 3) bottomRight.setText(Character.toString(player));

				}
				return player;
			}            

		}

		// check diagonals

		x = 0;
		y = 0;
		z = 0;
		{
			if(a[0][0] == player) x += 1;
			else z = 1;
			if(a[0][0] == waiter) y += 1;
		}
		{
			if(a[1][1] == player) x += 1;
			else z = 2;
			if(a[1][1] == waiter) y += 1;
		}
		{
			if(a[2][2] == player) x += 1;
			else z = 3;
			if(a[2][2] == waiter) y += 1;
		}

		if(x == 2 && y != 1) {
			a[z-1][z-1] = player;
			if(z == 1) topLeft.setText(Character.toString(player));
			if(z == 2) middleMiddle.setText(Character.toString(player));
			if(z == 3) bottomRight.setText(Character.toString(player));
			return player;
		}

		x = 0;
		y = 0;
		z = 0;
		{
			if(a[0][2] == player) x += 1;
			else z = 1;
			if(a[0][2] == waiter) y += 1;
		}
		{
			if(a[1][1] == player) x += 1;
			else z = 2;
			if(a[1][1] == waiter) y += 1;
		}
		{
			if(a[2][0] == player) x += 1;
			else z = 3;
			if(a[2][0] == waiter) y += 1;
		}

		if(x == 2 && y != 1) {
			if(z == 1) { 
				a[0][2] = player;
				topRight.setText(Character.toString(player));
			}
			if(z == 2) { 
				a[1][1] = player;
				middleMiddle.setText(Character.toString(player));
			}
			if(z == 3) { 
				a[2][0] = player;
				bottomLeft.setText(Character.toString(player));
			}
			return player;
		}

		// check for opponent opportunities to win
		// which must be blocked

		// check rows and columns

		for(i = 0; i < 3; i++) {

			x = 0;
			y = 0;
			z = 0;
			{    
				if(a[i][0] == waiter) x += 1;
				else z = 1;
				if(a[i][0] == player) y += 1;
			}
			{
				if(a[i][1] == waiter) x += 1;
				else z = 2;
				if(a[i][1] == player) y += 1;
			}
			{
				if(a[i][2] == waiter) x += 1;
				else z = 3;
				if(a[i][2] == player) y += 1;
			} 

			if(x == 2 && y != 1) {
				a[i][z-1] = player;
				if(i == 0) {
					if(z == 1) topLeft.setText(Character.toString(player));
					if(z == 2) topMiddle.setText(Character.toString(player));
					if(z == 3) topRight.setText(Character.toString(player));
				}
				if(i == 1) {
					if(z == 1) middleLeft.setText(Character.toString(player));
					if(z == 2) middleMiddle.setText(Character.toString(player));
					if(z == 3) middleRight.setText(Character.toString(player));
				}
				if(i == 2) {
					if(z == 1) bottomLeft.setText(Character.toString(player));
					if(z == 2) 
						bottomMiddle.setText(Character.toString(player));
					if(z == 3) bottomRight.setText(Character.toString(player));
				}

				player = (char) (player + waiter); // swap whose turn it is
				waiter = (char) (player - waiter);
				player = (char) (player - waiter);
				return 'C';
			}

			x = 0;
			y = 0;
			z = 0;

			{    
				if(a[0][i] == waiter) x += 1;
				else z = 1;
				if(a[0][i] == player) y += 1;
			}
			{
				if(a[1][i] == waiter) x += 1;
				else z = 2;
				if(a[1][i] == player) y += 1;
			}
			{
				if(a[2][i] == waiter) x += 1;
				else z = 3;
				if(a[2][i] == player) y += 1; 
			}                

			if(x == 2 && y != 1) {
				a[z-1][i] = player;
				if(i == 0) {
					if(z == 1) topLeft.setText(Character.toString(player));
					if(z == 2) middleLeft.setText(Character.toString(player));
					if(z == 3) bottomLeft.setText(Character.toString(player));
				}
				if(i == 1) {
					if(z == 1) topMiddle.setText(Character.toString(player));
					if(z == 2) 
						middleMiddle.setText(Character.toString(player));
					if(z == 3) 
						bottomMiddle.setText(Character.toString(player));
				}
				if(i == 2) {
					if(z == 1) topRight.setText(Character.toString(player));
					if(z == 2) middleRight.setText(Character.toString(player));
					if(z == 3) bottomRight.setText(Character.toString(player));
				}
				player = (char) (player + waiter);  
				waiter = (char) (player - waiter);
				player = (char) (player - waiter);
				return 'C';
			}

		}

		// check diagonals

		x = 0;
		y = 0;
		z = 0;
		{
			if(a[0][0] == waiter) x += 1;
			else z = 1;
			if(a[0][0] == player) y += 1;
		}
		{
			if(a[1][1] == waiter) x += 1;
			else z = 2;
			if(a[1][1] == player) y += 1;
		}
		{
			if(a[2][2] == waiter) x += 1;
			else z = 3;
			if(a[2][2] == player) y += 1;
		}

		if(x == 2 && y != 1) {
			a[z-1][z-1] = player;
			if(z == 1) topLeft.setText(Character.toString(player));
			if(z == 2) middleMiddle.setText(Character.toString(player));
			if(z == 3) bottomRight.setText(Character.toString(player));
			player = (char) (player + waiter);  
			waiter = (char) (player - waiter);
			player = (char) (player - waiter);
			return 'C';
		}  

		x = 0;
		y = 0;
		z = 0;
		{
			if(a[0][2] == waiter) x += 1;
			else z = 1;
			if(a[0][2] == player) y += 1;
		}
		{
			if(a[1][1] == waiter) x += 1;
			else z = 2;
			if(a[1][1] == player) y += 1;
		}
		{
			if(a[2][0] == waiter) x += 1;
			else z = 3;
			if(a[2][0] == player) y += 1;
		}

		if(x == 2 && y != 1) {
			if(z == 1) {
				a[0][2] = player;
				topRight.setText(Character.toString(player));
			}
			if(z == 2) {
				a[1][1] = player;
				middleMiddle.setText(Character.toString(player));
			}
			if(z == 3) { 
				a[2][0] = player;
				bottomLeft.setText(Character.toString(player));
			}
			player = (char) (player + waiter);  
			waiter = (char) (player - waiter);
			player = (char) (player - waiter);
			return 'C';
		}

		// check for potential "fork" opportunities

		// check for forks from sides

		// so check if you have 2 corners

		boolTopLeft = false;
		boolTopRight = false;
		boolBottomLeft = false;
		boolBottomRight = false;

		x = 0; 
		if(a[0][0] == player) { x += 1; boolTopLeft = true; }
		if(a[0][2] == player) { x += 1; boolTopRight = true; }
		if(a[2][0] == player) { x += 1; boolBottomLeft = true; }
		if(a[2][2] == player) { x += 1; boolBottomRight = true;}

		if(x == 2) { 
			// now check if opposite (or side-by side)
			if(boolTopRight && boolBottomLeft) {
				//check if "side roads" free
				if(a[0][1] == '_' && a[0][0] == '_' && a[1][0] == '_') {
					a[0][0] = player;
					topLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[1][2] == '_' && a[2][2] == '_' && a[2][1] == '_') {
					a[2][2] = player;
					bottomRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
			} else if(boolTopLeft && boolBottomRight) {
				if(a[0][1] == '_' && a[0][2] == '_' && a[1][2] == '_') {
					a[0][2] = player;
					topRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[1][0] == '_' && a[2][0] == '_' && a[2][1] == '_') {
					a[2][0] = player;
					bottomLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				// side-by-side
			} else if(boolTopLeft && boolTopRight) { // top  
				if(a[1][0] == '_' && a[1][1] == '_' && a[2][0] == '_') {  
					a[2][0] = player;                  //a[0][1] already
					bottomLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';                         
				}
				if(a[1][1] == '_' && a[1][2] == '_' && a[2][2] == '_') {
					a[2][2] = player;
					bottomRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
			} else if(boolTopRight && boolBottomRight) { // right
				if(a[0][1] == '_' && a[1][1] == '_' && a[0][0] == '_') {
					a[0][0] = player;
					topLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[1][1] == '_' && a[2][1] == '_' && a[2][0] == '_') { 
					a[2][0] = player;
					bottomLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
			} else if(boolBottomLeft && boolBottomRight) { // bottom
				if(a[1][0] == '_' && a[1][1] == '_' && a[0][0] == '_') {
					a[0][0] = player;
					topLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[1][1] == '_' && a[1][2] == '_' && a[0][2] == '_') {
					a[0][2] = player;
					topRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
			} else if(boolTopLeft && boolBottomLeft) { // left
				if(a[0][1] == '_' && a[1][1] == '_' && a[0][2] == '_') {
					a[0][2] = player;
					topRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[1][1] == '_' && a[2][1] == '_' && a[2][2] == '_') {
					a[2][2] = player;
					bottomRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
			}
		}

		// check for other potential fork opportunities
		// (two in a row blocked)

		for(i = 0; i < 3; i++) {

			x = 0;
			y = 0;
			z = 0;

			{            
				if(a[i][0] == player) x += 1;
				else z = 1;
				if(a[i][0] == waiter) y += 1;
			}
			{
				if(a[i][1] == player) x += 1;
				else z = 2;
				if(a[i][1] == waiter) y += 1;
			} 
			{
				if(a[i][2] == player) x += 1;
				else z = 3;
				if(a[i][2] == waiter) y += 1;
			}  

			if(i == 0 && x == 2 && y == 1) {
				if(z == 1) { 
					// check if boxes needed for fork are free
					if(a[1][1] == '_' && a[2][0] == '_' && a[2][1] == '_') {
						a[1][1] = player;
						middleMiddle.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
				} else if(z == 3) {   // z = 2 already covered 
					if(a[1][1] == '_' && a[2][1] == '_' && a[2][2] == '_') {
						a[1][1] = player;
						middleMiddle.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
				}
			}        

			if(i == 1 && x == 2 && y == 1) {
				if(z == 1) { 
					if(a[0][2] == '_' && a[2][0] == '_' && a[2][2] == '_') {
						a[0][2] = player;
						topRight.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
					if(a[0][0] == '_' && a[0][2] == '_' && a[2][2] == '_') {
						a[2][2] = player;
						bottomRight.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
				} else if(z == 3) {   // z = 2 not fork situation 
					if(a[0][0] == '_' && a[0][2] == '_' && a[2][2] == '_') {
						a[0][0] = player;
						topLeft.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
					if(a[0][0] == '_' && a[0][2] == '_' && a[2][0] == '_') {
						a[2][0] = player;
						bottomLeft.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
				}
			}

			if(i == 2 && x == 2 && y == 1) {
				if(z == 1) { 
					if(a[0][0] == '_' && a[0][1] == '_' && a[1][1] == '_') {
						a[1][1] = player;
						middleMiddle.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}   
				} else if(z == 3) {   // z = 2 already covered 
					if(a[1][1] == '_' && a[0][1] == '_' && a[0][2] == '_') {
						a[1][1] = player;
						middleMiddle.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
				}
			}           


			x = 0;
			y = 0;
			z = 0;

			{            
				if(a[0][i] == player) x += 1;
				else z = 1;
				if(a[0][i] == waiter) y += 1;
			}
			{
				if(a[1][i] == player) x += 1;
				else z = 2;
				if(a[1][i] == waiter) y += 1;
			}
			{
				if(a[2][i] == player) x += 1;
				else z = 3;
				if(a[2][i] == waiter) y += 1;
			} 

			if(i == 0 && x == 2 && y == 1) {
				if(z == 1) { 
					if(a[1][1] == '_' && a[0][2] == '_' && a[1][2] == '_') {
						a[1][1] = player;
						middleMiddle.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
				} else if(z == 3) {   // z = 2 already covered 
					if(a[1][1] == '_' && a[1][2] == '_' && a[2][2] == '_') {
						a[1][1] = player;
						middleMiddle.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
				}
			}      

			if(i == 1 && x == 2 && y == 1) {
				if(z == 1) { 
					if(a[0][2] == '_' && a[2][0] == '_' && a[2][2] == '_') {
						a[2][0] = player;
						bottomLeft.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
					if(a[0][0] == '_' && a[2][0] == '_' && a[2][2] == '_') {
						a[2][2] = player;
						bottomRight.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
				} else if(z == 3) {   // z = 2 not fork situation 
					if(a[0][0] == '_' && a[0][2] == '_' && a[2][2] == '_') {
						a[0][0] = player;
						topLeft.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
					if(a[0][0] == '_' && a[0][2] == '_' && a[2][0] == '_') {
						a[0][2] = player;
						topRight.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
				}
			}

			if(i == 2 && x == 2 && y == 1) {
				if(z == 1) { 
					if(a[0][0] == '_' && a[1][0] == '_' && a[1][1] == '_') {
						a[1][1] = player;
						middleMiddle.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}   
				} else if(z == 3) {   // z = 2 already covered 
					if(a[1][1] == '_' && a[1][0] == '_' && a[2][0] == '_') {
						a[1][1] = player;
						middleMiddle.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
				}
			}
		} 

		x = 0;
		y = 0;
		z = 0;

		{
			if(a[0][0] == player) x += 1;
			else z = 1;
			if(a[0][0] == waiter) y += 1;
		}
		{
			if(a[1][1] == player) x += 1;
			else z = 2;
			if(a[1][1] == waiter) y += 1;
		}
		{
			if(a[2][2] == player) x += 1;
			else z = 3;
			if(a[2][2] == waiter) y += 1;
		}

		if(x == 2 && y == 1) {
			if(z == 1) {
				if(a[2][0] == '_' && a[2][1] == '_' && a[0][2] == '_') {
					a[2][0] = player;
					bottomLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[0][2] == '_' && a[1][2] == '_' && a[2][0] == '_') {
					a[0][2] = player;
					topRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[0][1] == '_' && a[2][0] == '_' && a[2][1] == '_') {
					a[2][1] = player;
					bottomMiddle.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[1][0] == '_' && a[0][2] == '_' && a[1][2] == '_') {
					a[1][2] = player;
					middleRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
			}else if(z == 3) { // z = 2 already covered
				if(a[0][1] == '_' && a[0][2] == '_' && a[2][0] == '_') {
					a[0][2] = player;
					topRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[1][0] == '_' && a[2][0] == '_' && a[0][2] == '_') {
					a[2][0] = player;
					bottomLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[0][1] == '_' && a[0][2] == '_' && a[2][1] == '_') {
					a[0][1] = player;
					topMiddle.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C'; 
				}
				if(a[1][0] == '_' && a[2][0] == '_' && a[1][2] == '_') {
					a[1][0] = player;
					middleLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
			}  
		}

		x = 0;
		y = 0;
		z = 0;

		{
			if(a[0][2] == player) x += 1;
			else z = 1;
			if(a[0][2] == waiter) y += 1;
		}
		{
			if(a[1][1] == player) x += 1;
			else z = 2;
			if(a[1][1] == waiter) y += 1;
		}
		{
			if(a[2][0] == player) x += 1;
			else z = 3;
			if(a[2][0] == waiter) y += 1;
		}

		if(x == 2 && y == 1) {
			if(z == 1) {
				if(a[0][0] == '_' && a[1][0] == '_' && a[2][2] == '_') {
					a[0][0] = player;
					topLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[0][0] == '_' && a[2][1] == '_' && a[2][2] == '_') {
					a[2][2] = player;
					bottomRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[0][0] == '_' && a[1][0] == '_' && a[1][2] == '_') {
					a[1][0] = player;
					middleLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[2][1] == '_' && a[2][2] == '_' && a[0][1] == '_') {
					a[2][1] = player;
					bottomMiddle.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
			} else if(z == 3) {
				if(a[0][0] == '_' && a[1][2] == '_' && a[2][2] == '_') {
					a[2][2] = player;
					bottomRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[0][0] == '_' && a[0][1] == '_' && a[2][2] == '_') {
					a[0][0] = player;
					topLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[1][0] == '_' && a[1][2] == '_' && a[2][2] == '_') {
					a[1][2] = player;
					middleRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[0][0] == '_' && a[0][1] == '_' && a[2][1] == '_') {
					a[0][1] = player;
					topMiddle.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
			}
		}

		// if opponent has fork opportunity, block

		// check for forks from sides

		// so check if they have 2 corners

		boolTopLeft = false;
		boolTopRight = false;
		boolBottomLeft = false;
		boolBottomRight = false;

		x = 0; 
		if(a[0][0] == waiter) { x += 1; boolTopLeft = true; }
		if(a[0][2] == waiter) { x += 1; boolTopRight = true; }
		if(a[2][0] == waiter) { x += 1; boolBottomLeft = true; }
		if(a[2][2] == waiter) { x += 1; boolBottomRight = true; }


		if(x == 2) { 
			// now check if opposite (or side-by side)

			// in this case blocking means taking side square

			if(boolTopRight && boolBottomLeft) {

				//check if "side roads" free
				if(a[0][1] == '_' && a[0][0] == '_' && a[1][0] == '_') {
					a[0][1] = player;
					topMiddle.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[1][2] == '_' && a[2][2] == '_' && a[2][1] == '_') {
					a[1][2] = player;
					middleRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
			} else if(boolTopLeft && boolBottomRight) {
				if(a[0][1] == '_' && a[0][2] == '_' && a[1][2] == '_') {
					a[0][1] = player;
					topMiddle.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[1][0] == '_' && a[2][0] == '_' && a[2][1] == '_') {
					a[1][0] = player;
					middleLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				// side-by-side

				// in this case block by marking middle

			} else if(boolTopLeft && boolTopRight) { // top  
				if(a[1][0] == '_' && a[1][1] == '_' && a[2][0] == '_') {  
					a[1][1] = player;
					middleMiddle.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';                         
				}
				if(a[1][1] == '_' && a[1][2] == '_' && a[2][2] == '_') {
					a[1][1] = player;
					middleMiddle.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
			} else if(boolTopRight && boolBottomRight) { // right
				if(a[0][1] == '_' && a[1][1] == '_' && a[0][0] == '_') {
					a[1][1] = player;
					middleMiddle.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				} 
				if(a[1][1] == '_' && a[2][1] == '_' && a[2][0] == '_') { 
					a[1][1] = player;
					middleMiddle.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
			} else if(boolBottomLeft && boolBottomRight) { // bottom
				if(a[1][0] == '_' && a[1][1] == '_' && a[0][0] == '_') {
					a[1][1] = player;
					middleMiddle.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[1][1] == '_' && a[1][2] == '_' && a[0][2] == '_') {
					a[1][1] = player;
					middleMiddle.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
			} else if(boolTopLeft && boolBottomLeft) { // left
				if(a[0][1] == '_' && a[1][1] == '_' && a[0][2] == '_') {
					a[1][1] = player;
					middleMiddle.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[1][1] == '_' && a[2][1] == '_' && a[2][2] == '_') {
					a[1][1] = player;
					middleMiddle.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
			}
		}  

		// check for other potential fork opportunities
		// (two in a row blocked)

		// in these cases blocking means taking the square 
		// your opponent wishes to take

		for(i = 0; i < 3; i++) {

			x = 0;
			y = 0;
			z = 0;

			{            
				if(a[i][0] == waiter) x += 1;
				else z = 1;
				if(a[i][0] == player) y += 1;
			}
			{
				if(a[i][1] == waiter) x += 1;
				else z = 2;
				if(a[i][1] == player) y += 1;
			}  
			{
				if(a[i][2] == waiter) x += 1;
				else z = 3;
				if(a[i][2] == player) y += 1;
			}  

			if(i == 0 && x == 2 && y == 1) {
				if(z == 1) { 
					// check if boxes needed for fork are free
					if(a[1][1] == '_' && a[2][0] == '_' && a[2][1] == '_') {
						a[1][1] = player;
						middleMiddle.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
				} else if(z == 3) {   // z = 2 already covered 
					if(a[1][1] == '_' && a[2][1] == '_' && a[2][2] == '_') {
						a[1][1] = player;
						middleMiddle.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
				}
			}        

			if(i == 1 && x == 2 && y == 1) {
				if(z == 1) { 
					if(a[0][2] == '_' && a[2][0] == '_' && a[2][2] == '_') {
						a[0][2] = player;
						topRight.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
					if(a[0][0] == '_' && a[0][2] == '_' && a[2][2] == '_') {
						a[2][2] = player;
						bottomRight.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
				} else if(z == 3) {   // z = 2 not fork situation 
					if(a[0][0] == '_' && a[0][2] == '_' && a[2][2] == '_') {
						a[0][0] = player;
						topLeft.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
					if(a[0][0] == '_' && a[0][2] == '_' && a[2][0] == '_') {
						a[2][0] = player;
						bottomLeft.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
				}
			}

			if(i == 2 && x == 2 && y == 1) {
				if(z == 1) { 
					if(a[0][0] == '_' && a[0][1] == '_' && a[1][1] == '_') {
						a[1][1] = player;
						middleMiddle.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}   
				} else if(z == 3) {   // z = 2 already covered 
					if(a[1][1] == '_' && a[0][1] == '_' && a[0][2] == '_') {
						a[1][1] = player;
						middleMiddle.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
				}
			}           


			x = 0;
			y = 0;
			z = 0;

			{            
				if(a[0][i] == waiter) x += 1;
				else z = 1;
				if(a[0][i] == player) y += 1;
			}
			{
				if(a[1][i] == waiter) x += 1;
				else z = 2;
				if(a[1][i] == player) y += 1;
			}
			{
				if(a[2][i] == waiter) x += 1;
				else z = 3;
				if(a[2][i] == player) y += 1;
			} 

			if(i == 0 && x == 2 && y == 1) {
				if(z == 1) { 
					if(a[1][1] == '_' && a[0][2] == '_' && a[1][2] == '_') {
						a[1][1] = player;
						middleMiddle.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
				} else if(z == 3) {   // z = 2 already covered 
					if(a[1][1] == '_' && a[1][2] == '_' && a[2][2] == '_') {
						a[1][1] = player;
						middleMiddle.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					} 
				}
			}      

			if(i == 1 && x == 2 && y == 1) {
				if(z == 1) { 
					if(a[0][2] == '_' && a[2][0] == '_' && a[2][2] == '_') {
						a[2][0] = player;
						bottomLeft.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
					if(a[0][0] == '_' && a[2][0] == '_' && a[2][2] == '_') {
						a[2][2] = player;
						bottomRight.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
				} else if(z == 3) {   // z = 2 not fork situation 
					if(a[0][0] == '_' && a[0][2] == '_' && a[2][2] == '_') {
						a[0][0] = player;
						topLeft.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
					if(a[0][0] == '_' && a[0][2] == '_' && a[2][0] == '_') {
						a[0][2] = player;
						topRight.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}  
				}
			}

			if(i == 2 && x == 2 && y == 1) {
				if(z == 1) { 
					if(a[0][0] == '_' && a[1][0] == '_' && a[1][1] == '_') {
						a[1][1] = player;
						middleMiddle.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}   
				} else if(z == 3) {   // z = 2 already covered 
					if(a[1][1] == '_' && a[1][0] == '_' && a[2][0] == '_') {
						a[1][1] = player;
						middleMiddle.setText(Character.toString(player));
						player = (char) (player + waiter);  
						waiter = (char) (player - waiter);
						player = (char) (player - waiter);
						return 'C';
					}
				}
			}
		} 

		x = 0;
		y = 0;
		z = 0;

		{
			if(a[0][0] == waiter) x += 1;
			else z = 1;
			if(a[0][0] == player) y += 1;
		} 
		{
			if(a[1][1] == waiter) x += 1;
			else z = 2;
			if(a[1][1] == player) y += 1;
		} 
		{
			if(a[2][2] == waiter) x += 1;
			else z = 3;
			if(a[2][2] == player) y += 1;
		} 

		if(x == 2 && y == 1) {
			if(z == 1) {
				if(a[2][0] == '_' && a[2][1] == '_' && a[0][2] == '_') {
					a[2][0] = player;
					bottomLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[0][2] == '_' && a[1][2] == '_' && a[2][0] == '_') {
					player = a[0][2];
					topRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[0][1] == '_' && a[2][0] == '_' && a[2][1] == '_') {
					a[2][1] = player;
					bottomMiddle.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[1][0] == '_' && a[0][2] == '_' && a[1][2] == '_') {
					a[1][2] = player;
					middleRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
			}else if(z == 3) { // z = 2 already covered
				if(a[0][1] == '_' && a[0][2] == '_' && a[2][0] == '_') {
					a[0][2] = player;
					topRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[1][0] == '_' && a[2][0] == '_' && a[0][2] == '_') {
					a[2][0] = player;
					bottomLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[0][1] == '_' && a[0][2] == '_' && a[2][1] == '_') {
					a[0][1] = player;
					topMiddle.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C'; 
				}
				if(a[1][0] == '_' && a[2][0] == '_' && a[1][2] == '_') {
					a[1][0] = player;
					middleLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
			}
		}

		x = 0;
		y = 0;
		z = 0;

		{
			if(a[0][2] == waiter) x += 1;
			else z = 1;
			if(a[0][2] == player) y += 1;
		}
		{
			if(a[1][1] == waiter) x += 1;
			else z = 2;
			if(a[1][1] == player) y += 1;
		}
		{
			if(a[2][0] == waiter) x += 1;
			else z = 3;
			if(a[2][0] == player) y += 1;
		}

		if(x == 2 && y == 1) {
			if(z == 1) {
				if(a[0][0] == '_' && a[1][0] == '_' && a[2][2] == '_') {
					a[0][0] = player;
					topLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[0][0] == '_' && a[2][1] == '_' && a[2][2] == '_') {
					a[2][2] = player;
					bottomRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[0][0] == '_' && a[1][0] == '_' && a[1][2] == '_') {
					a[1][0] = player;
					middleLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[2][1] == '_' && a[2][2] == '_' && a[0][1] == '_') {
					a[2][1] = player;
					bottomMiddle.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
			} else if(z == 3) {
				if(a[0][0] == '_' && a[1][2] == '_' && a[2][2] == '_') {
					a[2][2] = player;
					bottomRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[0][0] == '_' && a[0][1] == '_' && a[2][2] == '_') {
					a[0][0] = player;
					topLeft.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[1][0] == '_' && a[1][2] == '_' && a[2][2] == '_') {
					a[1][2] = player;
					middleRight.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
				if(a[0][0] == '_' && a[0][1] == '_' && a[2][1] == '_') {
					a[0][1] = player;
					topMiddle.setText(Character.toString(player));
					player = (char) (player + waiter);  
					waiter = (char) (player - waiter);
					player = (char) (player - waiter);
					return 'C';
				}
			}
		}

		// if centre free, take centre

		if(a[1][1] == '_') {
			a[1][1] = player;
			middleMiddle.setText(Character.toString(player));
			player = (char) (player + waiter);  
			waiter = (char) (player - waiter);
			player = (char) (player - waiter);
			return 'C';
		}

		// if opponent in corner, take opposite corner

		if(a[0][0] == waiter && a[2][2] == '_') {
			a[2][2] = player;
			bottomRight.setText(Character.toString(player));
			player = (char) (player + waiter);  
			waiter = (char) (player - waiter);
			player = (char) (player - waiter);
			return 'C';
		}
		if(a[0][2] == waiter && a[2][0] == '_') {
			a[2][0] = player;
			bottomLeft.setText(Character.toString(player));
			player = (char) (player + waiter);  
			waiter = (char) (player - waiter);
			player = (char) (player - waiter);
			return 'C';
		}
		if(a[2][2] == waiter && a[0][0] == '_') {
			a[0][0] = player;
			topLeft.setText(Character.toString(player));
			player = (char) (player + waiter);  
			waiter = (char) (player - waiter);
			player = (char) (player - waiter);
			return 'C'; 
		}
		if(a[2][0] == waiter && a[0][2] == '_') {
			a[0][2] = player;
			topRight.setText(Character.toString(player));
			player = (char) (player + waiter);  
			waiter = (char) (player - waiter);
			player = (char) (player - waiter);
			return 'C';
		}

		// play corner square, if free

		if(a[0][0] == '_') {
			a[0][0] = player;
			topLeft.setText(Character.toString(player));
			player = (char) (player + waiter);  
			waiter = (char) (player - waiter);
			player = (char) (player - waiter);
			return 'C';
		}
		if(a[0][2] == '_') {
			a[0][2] = player;
			topRight.setText(Character.toString(player));
			player = (char) (player + waiter);  
			waiter = (char) (player - waiter);
			player = (char) (player - waiter);
			return 'C';
		} 
		if(a[2][2] == '_') {
			a[2][2] = player;
			bottomRight.setText(Character.toString(player));
			player = (char) (player + waiter);  
			waiter = (char) (player - waiter);
			player = (char) (player - waiter);
			return 'C';
		}
		if(a[2][0] == '_') {
			a[2][0] = player;
			bottomLeft.setText(Character.toString(player));
			player = (char) (player + waiter);  
			waiter = (char) (player - waiter);
			player = (char) (player - waiter);
			return 'C';
		}

		// play side square, if free

		if(a[0][1] == '_') {
			a[0][1] = player;
			topMiddle.setText(Character.toString(player));
			player = (char) (player + waiter);  
			waiter = (char) (player - waiter);
			player = (char) (player - waiter);
			return 'C';
		}
		if(a[1][2] == '_') {
			a[1][2] = player;
			middleRight.setText(Character.toString(player));
			player = (char) (player + waiter);  
			waiter = (char) (player - waiter);
			player = (char) (player - waiter);
			return 'C';
		}
		if(a[2][1] == '_') {
			a[2][1] = player;
			bottomMiddle.setText(Character.toString(player));
			player = (char) (player + waiter);  
			waiter = (char) (player - waiter);
			player = (char) (player - waiter);
			return 'C';
		}
		if(a[1][0] == '_') {
			a[1][0] = player;
			middleLeft.setText(Character.toString(player));
			player = (char) (player + waiter);  
			waiter = (char) (player - waiter);
			player = (char) (player - waiter);
			return 'C';
		}

		// if here is reached, its a draw

		return 'D';

	}


}
