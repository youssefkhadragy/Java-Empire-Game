package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class ControlledArmiesWindow extends JFrame implements ActionListener{
	private int counter = 0;
	private ArrayList<NewArmyButton> armyButtons = new ArrayList<NewArmyButton>();
	public ControlledArmiesWindow() {
		setSize(300, 400);
		setTitle("Controlled Armies");
		setLayout(new FlowLayout());
		this.validate();
	}
	public  ArrayList<NewArmyButton> getArmyButtons(){
		return armyButtons;
	}
	public void relocate() {
		
	}
	public void createNewArmy(Graphics graphics, UnitWindow unitWindow) {
		ArmyWindow armyWindow = new ArmyWindow(counter);
		unitWindow.getUnitButton().setVisible(true);
		armyWindow.add(unitWindow.getUnitButton());
		armyWindow.getUnitButtons().add(unitWindow.getUnitButton());
		unitWindow.setArmyNum(counter);
		NewArmyButton newArmy = new NewArmyButton("Army " + counter, counter, armyWindow);
		newArmy.addActionListener(this);
		newArmy.setActionCommand(counter + "");
		newArmy.setArmyWindow(armyWindow);
		armyButtons.add(newArmy);
		newArmy.getArmyWindow().getUnitButtons().add(unitWindow.getUnitButton());
		add(newArmy);
		graphics.getIdleArmies().put(newArmy, unitWindow.getUnit().getParentArmy());
		graphics.getChooseArmyToAttackButtons().put(newArmy, unitWindow.getUnit().getParentArmy());
		graphics.getControlledArmies().put(newArmy, unitWindow.getUnit().getParentArmy());
		counter++;
	}
	public void rel() {
		setVisible(false);
		setState(NORMAL);
		setSize(300, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(NewArmyButton b : armyButtons) {
			if(e.getActionCommand().equals(b.getArmyNum() + "")) {
				if(b.getArmyWindow() != null) {
					b.getArmyWindow().setVisible(true);
				}
			}
		}
	}
}
