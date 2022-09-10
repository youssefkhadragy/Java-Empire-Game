package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class ArmyWindow extends JFrame{
	private ArrayList<JButton> unitButtons = new ArrayList<JButton>();
	private int armyNum;
	public ArmyWindow(int armyNum) {
		setSize(580, 700);
		setTitle("Army " + armyNum);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setLocationRelativeTo(null);
		this.armyNum = armyNum;
		this.validate();
	}
	public void redraw() {
		getContentPane().removeAll();
		for(JButton b : unitButtons) {
			add(b);
		}
	}
	/*public void addUnit(JButton b) {
		add();
	}*/
	public ArrayList<JButton> getUnitButtons() {
		return unitButtons;
	}
	public void setUnitButtons(ArrayList<JButton> unitButtons) {
		this.unitButtons = unitButtons;
	}
	public void rel() {
		setVisible(false);
		setState(NORMAL);
		setSize(500, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
