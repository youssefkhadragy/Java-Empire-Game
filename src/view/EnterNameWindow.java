package view;

import javax.swing.*;
import engine.Player;
import java.awt.*;
import java.awt.event.*;

public class EnterNameWindow extends JFrame{
	private JTextField nameField;
	private String playerName;
	public JTextField getNameField() {
		return nameField;
	}
	public JTextField getTextField() {
		return nameField;
	}
	public EnterNameWindow(Graphics graphics) {
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Enter Your Name");
		setVisible(true);
		setLayout(new FlowLayout());
		nameField = new JTextField(16);
		nameField.setSize(new Dimension(30, 50));
		nameField.addActionListener(graphics);
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(graphics);
		confirmButton.setBounds(300, 150, 40, 25);
		add(nameField);
		add(confirmButton);
		this.validate();
	}
	public String getPlayerName() {
		return playerName;
		
	}
}
