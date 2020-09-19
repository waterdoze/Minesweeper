
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
	int r, c;
	JPanel mainpanel;
	
	int count;
	JLabel time;
	JLabel bomb;
	int bombCount;
	Timer timer;
	public GamePlay(int mines, int rows, int columns) {
		r = rows;
		c = columns;
		board = new int[rows][columns];
		
		
		generateMines(mines);
		countMinesNearSquare();
		
		
		JFrame frame = new  JFrame("Easy Mode");
		mainpanel= new JPanel();
		mainpanel.setLayout(new BoxLayout(mainpanel,BoxLayout.Y_AXIS));
		
		JPanel sub1 = new JPanel();
		
		//Top Bomb counter and Timer
		count =0; 
	    bombCount=mines;
		JLabel bombs = new JLabel("Bombs left:");
		bomb=new JLabel(Integer.toString(bombCount));
		sub1.add(bombs);
		sub1.add(bomb);
		sub1.add(new JLabel(" ===== "));
		JLabel t = new JLabel("Time:");
		time= new JLabel("0");		
		sub1.add(t);
		sub1.add(time);	
		
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
		
		mainpanel.add(sub1);
		
		
		JPanel sub2 = new JPanel();
		//adding all the buttons for minesweeper
		GridLayout layout = new GridLayout(rows, columns);
		sub2.setLayout(layout);
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				JButton button = new JButton("");
				switch (board[i][j]) {
					case 0:{
						Al a= new Al(button, "0");
						button.addActionListener(a);
						button.addMouseListener(a);
						break;
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
						button.addMouseListener(a);
						break;
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
						button.addMouseListener(a);
						break;
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
						button.addMouseListener(a);
						break;
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
						button.addMouseListener(a);
						break;
						
					}
					
				}
/*				JButton button = new JButton("");
				ImageIcon image = new ImageIcon("C:\\Users\\kevin\\Documents\\480px-Minesweeper_1.svg.png");
				Al a = new Al(button,image);
				button.addActionListener(a);	*/			
			sub2.add(button);
			}
		}
		sub2.setPreferredSize(new Dimension(800,1000));
		mainpanel.add(sub2);
		
		
		frame.add(mainpanel);
		frame.setSize(800, 800);
		frame.setVisible(true);
	
	}
	
	
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
	
	//ActionListener to change icon and disable further presses
class Al implements ActionListener, MouseListener{
	JButton button;
	ImageIcon i;
	String s ="";
	boolean flag= false;
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
			}
			else {
				button.setIcon(i);
			}

			button.removeActionListener(this);
			button.removeMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getButton()==MouseEvent.BUTTON3) {
			System.out.println("eft");
		if(flag) {
			button.setText("");
			bomb.setText(Integer.toString(++bombCount));
			button.setEnabled(true);
			flag=false;
		}
		else {
			button.setText("flagged");
			bomb.setText(Integer.toString(--bombCount));
			button.setEnabled(false);
			flag=true;
		}}
		
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
	
}
}


