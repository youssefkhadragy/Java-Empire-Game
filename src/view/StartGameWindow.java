package view;
import javax.swing.*;

import engine.Game;
import engine.Player;

import java.awt.*;
import java.awt.event.*;

public class StartGameWindow extends JFrame{
	JButton quitGameButton;
	public StartGameWindow(Graphics graphics) {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		JButton startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(graphics);
		startGameButton.setBounds(700, 275, 150, 100);
		add(startGameButton);
		quitGameButton = new JButton("Quit");
		quitGameButton.addActionListener(graphics);
		quitGameButton.setBounds(700, 400, 150, 100);
		add(quitGameButton);
		this.validate();
	}
}
