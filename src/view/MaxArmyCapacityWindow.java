package view;

import java.awt.*;
import java.awt.event.*;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;

public class MaxArmyCapacityWindow extends JFrame{
	public MaxArmyCapacityWindow() {
		setFocusable(false);
		setSize(400, 200);
		getRootPane().setWindowDecorationStyle(JRootPane.ERROR_DIALOG);
		setUndecorated(true);
		setTitle("Maximum Army Capacity!");
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		JLabel maxArmyCapacity = new JLabel("                       Maximum Army Capacity is Reached!");
		maxArmyCapacity.setBounds(150, 120, 200, 200);
		add(maxArmyCapacity);
		this.validate();
	}
}
