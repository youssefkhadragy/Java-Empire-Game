package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import engine.City;
import engine.Game;
import units.Unit;

public class UnitWindow extends JFrame implements ActionListener{
	private Unit unit;
	private int ArmyNum;
	private City city;
	private Game game;
	private JButton relocate;
	private JButton initiateArmy;
	private JButton unitButton;
	private DefendingArmyWindow defendingArmyWindow;
	private ControlledArmiesWindow controlledArmiesWindow;
	private Graphics graphics;
	public Unit getUnit() {
		return unit;
	}
	public JButton getUnitButton() {
		return unitButton;
	}
	public int getArmyNum() {
		return ArmyNum;
	}
	public void setArmyNum(int armyNum) {
		ArmyNum = armyNum;
	}
	public UnitWindow(Graphics graphics, Unit unit, City city, Game game, DefendingArmyWindow defendingArmyWindow, ControlledArmiesWindow controlledArmiesWindow, JButton unitButton) {
		setTitle("Choose Action");
		setSize(250, 100);
		this.unit = unit;
		this.city = city;
		this.game = game;
		this.graphics = graphics;
		this.controlledArmiesWindow = controlledArmiesWindow;
		this.unitButton = unitButton;
		this.unitButton = unitButton;
		this.defendingArmyWindow = defendingArmyWindow;
		setLocationRelativeTo(null);
	    setLayout(new FlowLayout());
		relocate = new JButton("Relocate?");
		relocate.addActionListener(this);
		initiateArmy = new JButton("Initiate Army?");
		initiateArmy.addActionListener(this);
		add(initiateArmy);
		add(relocate);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Relocate?")) {
			ChooseArmyToRelocateTo chooseArmyToRelocateTo = new ChooseArmyToRelocateTo(game, controlledArmiesWindow, controlledArmiesWindow.getArmyButtons(), this);
			setVisible(false);
		}
		if(e.getActionCommand().equals("Initiate Army?")) {
			game.getPlayer().initiateArmy(city, unit);
			defendingArmyWindow.removeButton(unitButton);
			defendingArmyWindow.getUnitsButtons().remove(unitButton);
			initiateArmy.setVisible(false);
			if(controlledArmiesWindow == null) {
				controlledArmiesWindow = new ControlledArmiesWindow();
			}
			controlledArmiesWindow.createNewArmy(graphics, this);
			setVisible(false);
		}		
	}
}
