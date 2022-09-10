package view;

import javax.swing.*;
import engine.Player;
import java.awt.*;
import java.awt.event.*;

public class ChooseCityWindow extends JFrame{
	private String cityName;
	private boolean flag = false;
	public ChooseCityWindow(Graphics graphics) {
		setSize(400, 200);
		setTitle("Please choose a City");
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		JButton cairoButton = new JButton("Cairo");
		cairoButton.setActionCommand("C");
		cairoButton.addActionListener(graphics);
		add(cairoButton);
		JButton romeButton = new JButton("Rome");
		romeButton.addActionListener(graphics);
		romeButton.setActionCommand("R");
		add(romeButton);                                
		JButton spartaButton = new JButton("Sparta");
		spartaButton.addActionListener(graphics);
		spartaButton.setActionCommand("S");
		add(spartaButton);
		this.setVisible(true);
		this.validate();
	}
}