package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CityIsAlreadyTargetWindow extends JFrame{
	public CityIsAlreadyTargetWindow(){
		setFocusable(false);
		setSize(400, 200);
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
		setUndecorated(true);
		setTitle("Successfull Upgrade");
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		JLabel successfullUpgrade = new JLabel("                        " + "         " + "City Is Already A Target!");
		successfullUpgrade.setBounds(150, 120, 200, 200);
		add(successfullUpgrade);
		this.validate();
	}
}
