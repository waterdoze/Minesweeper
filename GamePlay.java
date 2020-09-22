
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay {
	
	int[][] board;
	int r, c, bombCount, count;
	JFrame frame;
	JPanel mainpanel;
	JLabel time, bomb;
	Timer timer;
	Al[][] test;

	public GamePlay(int mines, int rows, int columns) {
		r = rows;
		c = columns;
		board = new int[rows][columns];
		test = new Al[rows][columns];
		time = new JLabel("0");
		bombCount = mines;
		bomb = new JLabel(Integer.toString(bombCount));
		count = 0;
		mainpanel = new JPanel();
		mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));

		frame = new JFrame("Minesweeper");
		
		generateMines(mines);
		countMinesNearSquare();
		
		//switching between difficulty settings
		JPanel modeDisplay = new JPanel();
		JButton easy = new JButton("Easy");
		JButton normal = new JButton("Normal");
		JButton hard = new JButton("Hard");
		
		easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GamePlay start = new GamePlay(10, 9, 9);
				start.display();
				frame.dispose();
			}
		});
		normal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GamePlay start = new GamePlay(40, 16, 16);
				start.display();
				frame.dispose();
			}
		});
		hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GamePlay start = new GamePlay(99, 16, 30);
				start.display();
				frame.dispose();
			}
		});
		
		modeDisplay.add(easy);
		modeDisplay.add(normal);
		modeDisplay.add(hard);
		
		
		//Top Bomb counter and Timer
		JPanel timeDisplay = new JPanel();
		JLabel bombs = new JLabel("Bombs left:");
		JLabel t = new JLabel("Time:");
		timeDisplay.add(bombs);
		timeDisplay.add(bomb);
		timeDisplay.add(new JLabel(" ===== "));
		timeDisplay.add(t);
		timeDisplay.add(time);

		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				count++;
				if (count < 100000) {
					time.setText(Integer.toString(count));
				} else {
					((Timer) (e.getSource())).stop();
				}
			}
		});
		
		timer.setInitialDelay(1000);
		timer.start();

		JPanel cellDisplay = new JPanel();
		GridLayout layout = new GridLayout(rows, columns);
		cellDisplay.setLayout(layout);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				JButton button = new JButton("");
				switch (board[i][j]) {
						case 0: {
							Al a = new Al(button, "", i, j);
							button.addActionListener(a);
							button.addMouseListener(a);
							break;
						}
						case 1: {
							/*
							 * ImageIcon image = null; try { image = new ImageIcon(new URL(
							 * "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Minesweeper_1.svg/480px-Minesweeper_1.svg.png"
							 * )); } catch (MalformedURLException e) { // TODO Auto-generated catch block
							 * e.printStackTrace(); } Al a = new Al(button, image);
							 */
							Al a = new Al(button, "1", i, j);
							button.addActionListener(a);
							button.addMouseListener(a);
							break;
						}
						case 2: {
							Al a = new Al(button, "2", i, j);
							button.addActionListener(a);
							button.addMouseListener(a);
							break;
						}
						case 3: {
							Al a = new Al(button, "3", i, j);
							button.addActionListener(a);
							button.addMouseListener(a);
							break;
						}
						case 4: {
							Al a = new Al(button, "4", i, j);
							button.addActionListener(a);
							button.addMouseListener(a);
							break;
						}
						case 5: {
							Al a = new Al(button, "5", i, j);
							button.addActionListener(a);
							button.addMouseListener(a);
							break;
						}
						case 6: {
							Al a = new Al(button, "6", i, j);
							button.addActionListener(a);
							button.addMouseListener(a);
							break;
						}
						case 7: {
							Al a = new Al(button, "7", i, j);
							button.addActionListener(a);
							button.addMouseListener(a);
							break;
						}
						case 8: {
							Al a = new Al(button, "8", i, j);
							button.addActionListener(a);
							button.addMouseListener(a);
							break;
						}
						case 10: {
							Al a = new Al(button, "bomb", i, j);
							button.addActionListener(a);
							button.addMouseListener(a);
							break;
						}

				}
				/*
				 * JButton button = new JButton(""); ImageIcon image = new
				 * ImageIcon("C:\\Users\\kevin\\Documents\\480px-Minesweeper_1.svg.png"); Al a =
				 * new Al(button,image); button.addActionListener(a);
				 */
				cellDisplay.add(button);
				Al temp = (Al) button.getActionListeners()[0];
				test[i][j] = temp;
			}
		}
		cellDisplay.setPreferredSize(new Dimension(800, 1000));
		
		//Display Everything
		mainpanel.add(modeDisplay);
		mainpanel.add(timeDisplay);
		mainpanel.add(cellDisplay);

	}
	
	void display() {
		frame.add(mainpanel);
		frame.setSize(800, 800);
		frame.setVisible(true);
	}

	// squares that don't contain mines need a number to indicate how many mines are
	// diagonally/directly adjacent to it
	void countMinesNearSquare() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (board[i][j] == 10) {
					continue;
				} else {
					if (i != 0 && board[i - 1][j] == 10) {
						board[i][j]++;
					}
					if (j != 0 && board[i][j - 1] == 10) {
						board[i][j]++;
					}
					if (i != r - 1 && board[i + 1][j] == 10) {
						board[i][j]++;
					}
					if (j != c - 1 && board[i][j + 1] == 10) {
						board[i][j]++;
					}
					if (i != r - 1 && j != c - 1 && board[i + 1][j + 1] == 10) {
						board[i][j]++;
					}
					if (i != r - 1 && j != 0 && board[i + 1][j - 1] == 10) {
						board[i][j]++;
					}
					if (i != 0 && j != 0 && board[i - 1][j - 1] == 10) {
						board[i][j]++;
					}
					if (i != 0 && j != c - 1 && board[i - 1][j + 1] == 10) {
						board[i][j]++;
					}
				}
			}
		}
	}

	// spawn an exact amount of mines as suggested in class constructor
	void generateMines(int m) {
		for (int i = m; i > 0; i--) {
			int rowr = (int) (Math.random() * r);
			int columnr = (int) (Math.random() * c);
			if (board[rowr][columnr] == 10) {
				i++;
				continue;
			} else {
				board[rowr][columnr] = 10;
			}
		}
	}

	// test the grid
	void print() {
		for (int[] i : board) {
			for (int in : i) {
				System.out.printf("%4d", in);
			}
			System.out.println();
		}
	}
	
	

	// ActionListener to change icon and disable further presses
	class Al implements ActionListener, MouseListener {
		
		JButton button;
		String s = "";
		int r, c;
		boolean flag = false;
		

		public Al(JButton b, String s) {
			button = b;
			this.s = s;
		}

		public Al(JButton b, String s, int r, int c) {
			button = b;
			this.s = s;
			this.r = r;
			this.c = c;
		}
		
		public String getType() {
			return s;
		}
		
		public JButton getButton() {
			return button;
		}
		
		public int getRow() {
			return r;
		}
		
		public int getColumn() {
			return c;
		}
		//Induce end screen
		void createEndScreen() {
			for (Al[] row: test) {
				for (Al cells: row) {
					if (cells.getType().equals("bomb")) {
						cells.getButton().setText("bomb");
						cells.getButton().removeActionListener(cells);
					}
					else {
						cells.getButton().removeActionListener(cells);
					}
				}
			}
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// need a picture of numbers
			button.setText(s);
			if (s.equals("bomb")) {
				createEndScreen();					
			}
			if (s.equals("")) {
				testThis(r, c);
			}
			button.setEnabled(false);
			board[r][c] = -1;
			
			boolean win = true;
			for (int[] row: board) {
				for (int number: row) {
					if (number != 10 && number != -1) {
						win = false;
					}
				}
			}
			if (win) {
				createEndScreen();
			}
			
			
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if (arg0.getButton() == MouseEvent.BUTTON3 && button.isEnabled()) {

				if (flag) {
					button.setText("");
					button.addActionListener(this);
					bomb.setText(Integer.toString(++bombCount));
					flag = false;
				} 
				else {
					button.setText("flagged");
					button.removeActionListener(this);
					bomb.setText(Integer.toString(--bombCount));
					flag = true;
				}
			}

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		public void testThis(int r, int c) {

			if (r >= 0 && c >= 0 && r < test.length && c < test.length) {
				
				if (board[r][c] == 0) {
					button.setEnabled(false);
					board[r][c] = -1;
				} 
				else if (board[r][c] != -1) {
					button.setText(s);
					button.setEnabled(false);
					board[r][c] = -1;
					return;
				} 
				else {
					return;
				}

				if (r < test.length - 1) {
					test[r + 1][c].testThis(r + 1, c);
					if (c > 0) {
						test[r + 1][c - 1].testThis(r + 1, c - 1);
					}
					if (c < test.length - 1) {
						test[r + 1][c + 1].testThis(r + 1, c + 1);
					}
				}

				if (r > 0) {
					test[r - 1][c].testThis(r - 1, c);
					if (c > 0) {
						test[r - 1][c - 1].testThis(r - 1, c - 1);
					}
					if (c < test.length - 1) {
						test[r - 1][c + 1].testThis(r - 1, c + 1);
					}
				}
				if (c > 0) {
					test[r][c - 1].testThis(r, c - 1);
					if (r > 0) {
						test[r - 1][c - 1].testThis(r - 1, c - 1);
					}
					if (r < test.length - 1) {
						test[r + 1][c - 1].testThis(r + 1, c - 1);
					}
				}
				if (c < test.length - 1) {
					test[r][c + 1].testThis(r, c + 1);
					if (r > 0) {
						test[r - 1][c + 1].testThis(r - 1, c - 1);
					}
					if (r < test.length - 1) {
						test[r + 1][c + 1].testThis(r + 1, c - 1);
					}
				}

			}

		}
	}
}
