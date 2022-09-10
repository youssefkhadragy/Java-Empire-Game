package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameOverWindow extends JFrame implements ActionListener{
	private JButton ok = new JButton("OK");
	private JLabel winOrLose;
	public GameOverWindow(String s) {
		setTitle("Game Over");
		setSize(400, 400);
		setVisible(true);
		setLayout(new FlowLayout());
		winOrLose = new JLabel("        " + s);
		add(winOrLose);
		add(ok);
		ok.addActionListener(this);
		setLocationRelativeTo(null);
		this.validate();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("OK")) {
			System.exit(0);
		}
		
	}

}
