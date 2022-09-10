package view;

import java.awt.*;
import java.awt.event.*;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;

public class NoControlledArmiesWindow extends JFrame{
	public NoControlledArmiesWindow() {
		setFocusable(false);
		setSize(400, 200);
		getRootPane().setWindowDecorationStyle(JRootPane.ERROR_DIALOG);
		setUndecorated(true);
		setTitle("NO AVAILABLE ARMIES");
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		JLabel noControlledArmies = new JLabel("                                   NO AVAILABLE ARMIES");
		noControlledArmies.setBounds(150, 120, 200, 200);
		add(noControlledArmies);
		this.validate();
	}
}
