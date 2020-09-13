import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePlay {
	int[][] board;
	int r, c;
	public GamePlay(int mines, int rows, int columns) {
		r = rows;
		c = columns;
		board = new int[rows][columns];
		
		
		generateMines(mines);
		countMinesNearSquare();
		
		
		JFrame frame = new  JFrame("Easy Mode");
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(rows, columns);
		
		
		
		panel.setLayout(layout);
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				switch (board[i][j]) {
					case 1:
					//....
				}
				JButton button = new JButton(new ImageIcon("C:\\Users\\ddinh\\Pictures\\Saved Pictures\\gray_button.PNG"));
				panel.add(button);
				
			}
		}
		
		frame.add(panel);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
	
	void countMinesNearSquare() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (board[i][j] == 10) {
					continue;
				}
				else {
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
	
	void generateMines(int m) {
		for (int i = m; i > 0; i--) {
			int rowr = (int)(Math.random() * r);
			int columnr = (int)(Math.random() * c);
			if (board[rowr][columnr] == 10) {
				i++;
				continue;
			}
			else {
				board[rowr][columnr] = 10;
			}
		}
	}
	
	void print() {
		for (int[] i: board) {
			for (int in: i) {
				System.out.printf("%4d", in);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		GamePlay g = new GamePlay(10, 9, 9);
		g.print();
	}
}
