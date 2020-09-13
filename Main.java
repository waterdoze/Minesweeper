
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main {	
	public Main() {
		JFrame frame = new JFrame("Minesweeper");
		JPanel panel = new JPanel();
		JButton button = new JButton("Easy");
		JButton button2 = new JButton("Normal");
		JButton button3 = new JButton("Hard");
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GamePlay start = new GamePlay(10, 9, 9);
				frame.setVisible(false);
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GamePlay start = new GamePlay(40, 16, 16);
				frame.setVisible(false);
			}
		});
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GamePlay start = new GamePlay(99, 16, 30);
				frame.setVisible(false);
			}
		});
		
		panel.add(button);
		panel.add(button2);
		panel.add(button3);

		
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		Main run = new Main();
	}
}
