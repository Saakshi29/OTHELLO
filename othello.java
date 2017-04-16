package lecture22.othell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;

public class othello extends JFrame implements ActionListener {

	private static final int boardsize = 8;
	private static final int north = 1;
	private static final int northeast = 2;
	private static final int northwest = 3;
	private static final int south = 4;
	private static final int southeast = 5;
	private static final int southwest = 6;
	private static final int east = 7;
	private static final int west = 8;
	private boolean crossturn;
	private JButton[][] board = new JButton[boardsize][boardsize];

	public othello() {
		super("othello");
		super.setSize(600, 600);
		super.setResizable(false);

		GridLayout layout = new GridLayout(boardsize, boardsize);
		super.setLayout(layout);
		for (int row = 0; row < boardsize; row++) {
			for (int col = 0; col < boardsize; col++) {
				JButton button = new JButton();
				button.setFont(new Font("Times New Roman", 1, 225));
				button.setBackground(Color.green);
				if (row == 3 && col == 3 || row == 4 && col == 4) {
					button.setBackground(Color.white);
				}
				if (row == 2 && col == 3 || row == 3 && col == 2 || row == 4 && col == 5 || row == 5 && col == 4) {
					button.setBackground(Color.blue);
				}
				if (row == 3 && col == 4 || row == 4 && col == 3) {
					button.setBackground(Color.black);
				}
				button.addActionListener(this);
				this.board[row][col] = button;
				super.add(button);
			}
		}

		super.setVisible(true);
	}

	public void startgame() {
		crossturn = true;

	}

	public void move1(JButton button) {

		if (button.getBackground().equals(Color.blue)) {
			button.setBackground(Color.black);
		} else if (button.getBackground().equals(Color.red)) {
			button.setBackground(Color.white);

		}

	}

	public void move2() {
		for (int i = 0; i < boardsize; i++) {
			for (int j = 0; j < boardsize; j++) {
				if (board[i][j].getBackground() == Color.blue) {
					board[i][j].setBackground(Color.green);
				} else if (board[i][j].getBackground() == Color.red) {
					board[i][j].setBackground(Color.green);
				}

			}

		}
	}

	public void markvalidcell(int i, int j, int flag) {

		checkvalidityindirection(south, i, j, flag);
		checkvalidityindirection(southeast, i, j, flag);
		checkvalidityindirection(southwest, i, j, flag);
		checkvalidityindirection(north, i, j, flag);
		checkvalidityindirection(northeast, i, j, flag);
		checkvalidityindirection(northwest, i, j, flag);
		checkvalidityindirection(east, i, j, flag);
		checkvalidityindirection(west, i, j, flag);

	}

	public void checkvalidityindirection(int direction, int i, int j, int flag) {
		int incri = 0;
		int incrj = 0;

		switch (direction) {
		case 1: {
			incri = -1;
			incrj = 0;
			break;
		}
		case 2: {
			incri = -1;
			incrj = 1;
			break;

		}
		case 3: {
			incri = -1;
			incrj = -1;
			break;
		}
		case 4: {
			incri = 1;
			incrj = 0;
			break;
		}
		case 5: {
			incri = 1;
			incrj = 1;
			break;
		}
		case 6: {
			incri = 1;
			incrj = -1;
			break;
		}
		case 7: {
			incri = 0;
			incrj = 1;
			break;
		}
		case 8: {
			incri = 0;
			incrj = -1;
			break;
		}

		}
		int k = i;
		int l = j;
		i = i + incri;
		j = j + incrj;
		if (flag == 1) {
			if (crossturn == true) {
				if (isValidPos(i, j) && board[i][j].getBackground() != Color.black
						&& board[i][j].getBackground() != Color.green) {
					while (isValidPos(i, j) && board[i][j].getBackground() == Color.white) {
						i = i + incri;
						j = j + incrj;
					}
					if (isValidPos(i, j) && board[i][j].getBackground() == Color.black) {

						i = i - incri;
						j = j - incrj;
						// dangerous
						if (isValidPos(i, j)) {
							board[i][j].setBackground(Color.black);
						}
						i = i - incri;
						j = j - incrj;
						while (isValidPos(i, j) && board[i][j].getBackground() != Color.black) {
							board[i][j].setBackground(Color.black);
							i = i - incri;
							j = j - incrj;
						}
					}
				}
			} else {
				if (isValidPos(i, j) && board[i][j].getBackground() != Color.white
						&& board[i][j].getBackground() != Color.green) {
					while (isValidPos(i, j) && board[i][j].getBackground() == Color.black) {
						i = i + incri;
						j = j + incrj;
					}
					if (isValidPos(i, j) && board[i][j].getBackground() == Color.white) {

						i = i - incri;
						j = j - incrj;
						// dangerous
						if (isValidPos(i, j)) {
							board[i][j].setBackground(Color.white);
						}
						i = i - incri;
						j = j - incrj;
						while (isValidPos(i, j) && board[i][j].getBackground() != Color.white) {
							board[i][j].setBackground(Color.white);
							i = i - incri;
							j = j - incrj;
						}
					}
				}

			}
		}
		if (flag == 2) {
			if (crossturn == false) {
				if (isValidPos(i, j) && board[i][j].getBackground() != Color.white
						&& board[i][j].getBackground() != Color.green) {
					while (isValidPos(i, j) && board[i][j].getBackground() == Color.black) {
						i = i + incri;
						j = j + incrj;
					}
					if (isValidPos(i, j) && board[i][j].getBackground() == Color.green) {

						board[i][j].setBackground(Color.red);

					}
				}
			} else {
				if (isValidPos(i, j) && board[i][j].getBackground() != Color.black
						&& board[i][j].getBackground() != Color.green) {
					while (isValidPos(i, j) && board[i][j].getBackground() == Color.white) {
						i = i + incri;
						j = j + incrj;
					}
					if (isValidPos(i, j) && board[i][j].getBackground() == Color.green) {

						board[i][j].setBackground(Color.blue);

					}
				}

			}

		}
	}

	public static boolean isValidPos(int i, int j) {
		return i >= 0 && i < boardsize && j >= 0 && j < boardsize;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickbutton = (JButton) e.getSource();
		int i = 0, j = 0, l = 0, k = 0;
		for (i = 0; i < boardsize; i++) {
			for (j = 0; j < boardsize; j++) {
				if (board[i][j] == clickbutton) {
					k = i;
					l = j;
					break;
				}

			}

		}
		if (clickbutton.getBackground() == (Color.black) || clickbutton.getBackground() == (Color.white)
				|| clickbutton.getBackground() == (Color.green)) {
			JOptionPane.showMessageDialog(null, "invalid move");
		} else {
			if (clickbutton.getBackground() == (Color.blue) || clickbutton.getBackground() == (Color.red)) {
				move1(clickbutton);
				move2();
				markvalidcell(k, l, 1);
				crossturn = !crossturn;
				if (crossturn == false) {
					for (i = 0; i < boardsize; i++) {
						{
							for (j = 0; j < boardsize; j++)
								if (board[i][j].getBackground() == Color.white) {
									markvalidcell(i, j, 2);
								}
						}
					}
				} else {
					for (i = 0; i < boardsize; i++) {
						{
							for (j = 0; j < boardsize; j++)
								if (board[i][j].getBackground() == Color.black) {
									markvalidcell(i, j, 2);
								}
						}
					}

				}
				super.setTitle(crossturn ? "black'sturn" : "white's turn");
			}
		}
	
	
	}
	
	
}
