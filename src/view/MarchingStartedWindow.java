package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MarchingStartedWindow extends JFrame{
	public MarchingStartedWindow() {
		setFocusable(false);
		setSize(400, 200);
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
		setUndecorated(true);
		setTitle("Marching Started!");
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		JLabel successfullUpgrade = new JLabel("                        " + "         " + "     Marching Started!");
		successfullUpgrade.setBounds(150, 120, 200, 200);
		add(successfullUpgrade);
		this.validate();
	}
}
