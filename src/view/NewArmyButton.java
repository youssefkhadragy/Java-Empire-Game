package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class NewArmyButton extends JButton{
	private ArmyWindow armyWindow;
	private JButton unitButton;
	private int armyNum;
	private ArrayList<JButton> unitButtons;
	public ArrayList<JButton> getUnitButtons() {
		return unitButtons;
	}
	public void setUnitButtons(ArrayList<JButton> unitButtons) {
		this.unitButtons = unitButtons;
		this.armyWindow = armyWindow;
	}
	public NewArmyButton(String s, int armyNum, ArmyWindow armyWindow) {
		super(s);
		this.armyNum = armyNum;
	}
	public int getArmyNum() {
		return armyNum;
	}
	public JButton getUnitButton() {
		return unitButton;
	}
	public ArmyWindow getArmyWindow() {
		return armyWindow;
	}
	public void setArmyWindow(ArmyWindow armyWindow) {
		this.armyWindow = armyWindow;
	}
}
