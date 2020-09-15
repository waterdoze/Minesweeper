import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePlay {
	int[][] board;
	int r, c;
	JPanel panel;
	public GamePlay(int mines, int rows, int columns) {
		r = rows;
		c = columns;
		board = new int[rows][columns];
		
		
		generateMines(mines);
		countMinesNearSquare();
		
		
		JFrame frame = new  JFrame("Easy Mode");
		panel = new JPanel();
		GridLayout layout = new GridLayout(rows, columns);
		
		
		
		panel.setLayout(layout);
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				JButton button = new JButton("");
				switch (board[i][j]) {
					case 0:{
						
					}
					case 1:{
/*						ImageIcon image = null;
						try {
							image = new ImageIcon(new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Minesweeper_1.svg/480px-Minesweeper_1.svg.png"));
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Al a = new Al(button, image);*/
						Al a= new Al(button, "1");
						button.addActionListener(a);
					}
					case 2:{
	/*					ImageIcon image = null;
						try {
							image = new ImageIcon(new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Minesweeper_2.svg/480px-Minesweeper_2.svg.png"));
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Al a = new Al(button, image);*/
						Al a= new Al(button, "2");
						button.addActionListener(a);
					}
					case 3:{
				/*		ImageIcon image = null;
						try {
							image = new ImageIcon(new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Minesweeper_3.svg/480px-Minesweeper_3.svg.png"));
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Al a = new Al(button, image);*/
						Al a= new Al(button, "3");
						button.addActionListener(a);
					}
					case 10:{
			/*			ImageIcon image = null;
						try {
							image = new ImageIcon(new URL("https://giantbomb1.cbsistatic.com/uploads/scale_small/8/87790/3216800-icon_mine.png"));
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							System.out.println("image not found");
						}
						
						Al a = new Al(button, image);*/
						Al a= new Al(button, "bomb");
						button.addActionListener(a);
						
					}
					
				}
/*				JButton button = new JButton("");
				ImageIcon image = new ImageIcon("C:\\Users\\kevin\\Documents\\480px-Minesweeper_1.svg.png");
				Al a = new Al(button,image);
				button.addActionListener(a);	*/			
			panel.add(button);
			}
		}
		
		frame.add(panel);
		frame.setSize(1000, 1000);
		frame.setVisible(true);
	}
	//change the icon
	
	//squares that don't contain mines need a number to indicate how many mines are diagonally/directly adjacent to it
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
	
	//spawn an exact amount of mines as suggested in class constructor
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
	//test the grid
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

//ActionListener to change icon and disable further presses
class Al implements ActionListener{
	JButton button;
	ImageIcon i;
	String s ="";
	public Al(JButton b, ImageIcon i) {
		button = b;
		this.i=i;
	}
	public Al(JButton b, String s) {
		button = b; 
		this.s=s;
		i=null;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
			//need a pic of numbers
			if(i==null) {
				button.setText(s);
			}else {
				button.setIcon(i);
			}
			
			button.removeActionListener(this);
			System.out.println(s);
	
		
	}
	
}
