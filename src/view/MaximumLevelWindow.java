package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MaximumLevelWindow extends JFrame{
	public MaximumLevelWindow(String buildingType) {
		setFocusable(false);
		setSize(400, 200);
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
		setUndecorated(true);
		setTitle("Maximum Level Reached!");
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		JLabel maximumLevel = new JLabel("                       " + buildingType +  " Has Reached Maximum Level!");
		maximumLevel.setBounds(150, 120, 200, 200);
		add(maximumLevel);
		this.validate();
	}

}
