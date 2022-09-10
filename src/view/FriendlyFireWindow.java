package view;

import java.awt.*;
import java.awt.event.*;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;

public class FriendlyFireWindow extends JFrame{
	public FriendlyFireWindow() {
		setFocusable(false);
		setSize(400, 200);
		getRootPane().setWindowDecorationStyle(JRootPane.ERROR_DIALOG);
		setUndecorated(true);
		setTitle("FRIENDLY FIRE!");
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		JLabel friendlyFire = new JLabel("                       You Are Trying To Attack Unit Of Your Army!");
		friendlyFire.setBounds(150, 120, 200, 200);
		add(friendlyFire);
		this.validate();
	}
}
