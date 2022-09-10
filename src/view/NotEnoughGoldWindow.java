package view;

import java.awt.*;
import java.awt.event.*;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;

public class NotEnoughGoldWindow extends JFrame{
	public NotEnoughGoldWindow() {
		setFocusable(false);
		setSize(400, 200);
		getRootPane().setWindowDecorationStyle(JRootPane.ERROR_DIALOG);
		setUndecorated(true);
		setTitle("NOT ENOUGH GOLD");
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		JLabel notEnoughGold = new JLabel("                       There is No Enough Gold For This Action!");
		notEnoughGold.setBounds(150, 120, 200, 200);
		add(notEnoughGold);
		this.validate();
	}
}
