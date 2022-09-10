package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import engine.Game;
import exceptions.MaxCapacityException;

public class ChooseArmyToRelocateTo extends JFrame implements ActionListener{
	private ControlledArmiesWindow controlledArmiesWindow;
	private UnitWindow unitWindow;
	private Game game;
	
	public ChooseArmyToRelocateTo(Game game, ControlledArmiesWindow controlledArmiesWindow, ArrayList<NewArmyButton> armyButtons, UnitWindow unitWindow) {
		setSize(400, 400);
		setTitle("Choose Army To Relocate To");
		setVisible(true);
		this.controlledArmiesWindow = controlledArmiesWindow;
		this.unitWindow = unitWindow;
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		this.game = game;
		for(JButton b : armyButtons) {
			JButton armyButton = new JButton("Army " + armyButtons.indexOf(b));
			armyButton.addActionListener(this);
			add(armyButton);
		}
		this.validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 1000; i++) {
			if(e.getActionCommand().equals("Army " + i)) {
				try {
					game.getPlayer().getControlledArmies().get(i).relocateUnit(unitWindow.getUnit());
					controlledArmiesWindow.getArmyButtons().get(unitWindow.getArmyNum()).getArmyWindow().getUnitButtons().remove(unitWindow.getUnitButton());
					controlledArmiesWindow.getArmyButtons().get(unitWindow.getArmyNum()).getArmyWindow().redraw();
					controlledArmiesWindow.getArmyButtons().get(unitWindow.getArmyNum()).getArmyWindow().setVisible(false);
					unitWindow.setArmyNum(i);
					controlledArmiesWindow.getArmyButtons().get(i).getArmyWindow().getUnitButtons().add(unitWindow.getUnitButton());
				} catch (MaxCapacityException e1) {
					MaxArmyCapacityWindow w = new MaxArmyCapacityWindow();
					setVisible(false);
					
				}
				controlledArmiesWindow.getArmyButtons().get(i).getArmyWindow().redraw();
			}
		}
		setVisible(false);
	}
}
