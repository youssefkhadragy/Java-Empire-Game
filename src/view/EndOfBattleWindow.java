package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EndOfBattleWindow extends JFrame{
	public EndOfBattleWindow(String s) {
		setFocusable(false);
		setSize(400, 200);
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
		setUndecorated(true);
		setTitle(s);
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		JLabel inCoolDown = new JLabel("              " + s);
		inCoolDown.setBounds(150, 120, 200, 200);
		add(inCoolDown);
		this.validate();
	}

}

