package view;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class HostileCityWindow extends  JFrame{
	private String city;
	
	public HostileCityWindow(Graphics graphics, String cityName){
	city = cityName;
	setSize(400, 100);
	setTitle("Target City " + cityName + " ?");
	setLayout(new FlowLayout());
	setLocationRelativeTo(null);
	setVisible(true);
	JPanel emptyPanel = new JPanel(new FlowLayout());
	JPanel buttons = new JPanel();
	buttons.setPreferredSize(new Dimension(200, 200));
	emptyPanel.setPreferredSize(new Dimension(200, 75));
	add(emptyPanel, BorderLayout.NORTH);
	add(buttons, BorderLayout.SOUTH);
	add(emptyPanel, BorderLayout.NORTH);
	JButton targetCity = new JButton("Target City " + cityName);
	targetCity.addActionListener(graphics);
	buttons.add(targetCity);
	this.validate();
	}
	public String getCityName() {
		return city;
	}
}
