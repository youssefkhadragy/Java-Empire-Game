package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InCoolDownWindow extends JFrame{
	public InCoolDownWindow(String buildingType) {
		setFocusable(false);
		setSize(400, 200);
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
		setUndecorated(true);
		setTitle("Building In Cool Down!");
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		JLabel inCoolDown = new JLabel("       " + buildingType +  " is In CoolDown! Wait For Next Turn.");
		inCoolDown.setBounds(150, 120, 200, 200);
		add(inCoolDown);
		this.validate();
	}

}
