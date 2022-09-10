package view;

import engine.City;
import engine.Game;
import units.Archer;
import units.Cavalry;
import units.Infantry;
import units.Unit;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import buildings.Barracks;

public class DefendingArmyWindow extends JFrame implements ActionListener{
	private String cityName;
	private int nextUnitNum = 0;
	private Game game;
	private ControlledArmiesWindow controlledArmiesWindow;
	private ArrayList<Unit> units = new ArrayList<Unit>();
	private ArrayList<UnitWindow> unitWindows = new ArrayList<UnitWindow>();
	private ArrayList<JButton> unitsButtons = new ArrayList<JButton>();
	private Graphics graphics;
	public void setDefaultDefendingArmyWindowSize() {
		setVisible(false);
		setState(NORMAL);
		setSize(580, 700);
		setVisible(true);
	}
	public  ArrayList<JButton> getUnitsButtons(){
		return unitsButtons;
	}
	public void removeButton(JButton b) {
		unitsButtons.remove(b);
		b.setVisible(false);
	}
	public DefendingArmyWindow(Graphics graphics, Game game, String cityName, ControlledArmiesWindow controlledArmiesWindow) {
		setLocationRelativeTo(null);
		setSize(580, 700);
		setTitle(cityName + " Defending Army Units");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setVisible(true);
		this.game = game;
		this.graphics = graphics;
		this.cityName = cityName;
		this.controlledArmiesWindow = controlledArmiesWindow;
		setLocationRelativeTo(null);
		for(City x : game.getPlayer().getControlledCities()) {
			if(x.getName().equals(cityName)) {
				for(Unit u : x.getDefendingArmy().getUnits()) {
					units.add(u);
					if(u instanceof Infantry) {
					JButton unitsButton = new JButton("Unit "+ this.nextUnitNum + "Type: " + "Infantry " +  ", Level: "+ u.getLevel() + ", Status: " + u.getParentArmy().getCurrentStatus() + ", Current Soldier Count: " + u.getCurrentSoldierCount() + ", Max Soldier Count: " + u.getMaxSoldierCount());                                           
					unitsButton.setPreferredSize(new Dimension(500, 100));
					unitsButton.setActionCommand(u + "");
					unitsButton.addActionListener(this);
					add(unitsButton);
					UnitWindow unitWindow = new UnitWindow(graphics, u, x, game, this, controlledArmiesWindow, unitsButton);
					unitWindows.add(unitWindow);
					unitsButtons.add(unitsButton);
					this.nextUnitNum++;
					}
					if(u instanceof Cavalry) {
						JButton unitsButton = new JButton("Unit "+ this.nextUnitNum + "Type: " + "Cavalry " +  ", Level: "+ u.getLevel() + ", Status: " + u.getParentArmy().getCurrentStatus() + ", Current Soldier Count: " + u.getCurrentSoldierCount() + ", Max Soldier Count: " + u.getMaxSoldierCount());                                           
						unitsButton.setPreferredSize(new Dimension(500, 100));
						unitsButton.setActionCommand(u + "");
						unitsButton.addActionListener(this);
						add(unitsButton);
						UnitWindow unitWindow = new UnitWindow(graphics, u, x, game, this, controlledArmiesWindow, unitsButton);
						unitWindows.add(unitWindow);
						unitsButtons.add(unitsButton);
						this.nextUnitNum++;
						}
					if(u instanceof Archer) {
						JButton unitsButton = new JButton("Unit "+ this.nextUnitNum + "Type: Archer" + ", Level: "+ u.getLevel() + ", Status: " + u.getParentArmy().getCurrentStatus() + ", Current Soldier Count: " + u.getCurrentSoldierCount() + ", Max Soldier Count: " + u.getMaxSoldierCount());                                           
						unitsButton.setPreferredSize(new Dimension(500, 100));
						unitsButton.setActionCommand(u + "");
						unitsButton.addActionListener(this);
						add(unitsButton);
						UnitWindow unitWindow = new UnitWindow(graphics, u, x, game, this, controlledArmiesWindow, unitsButton);
						unitWindows.add(unitWindow);
						unitsButtons.add(unitsButton);
						this.nextUnitNum++;
						}
					
				}
			}
		}
		this.validate();
	}
	public void update() {
		for(City x : game.getPlayer().getControlledCities()) {
			if(x.getName().equals(cityName)) {
				for(Unit u : x.getDefendingArmy().getUnits()) {
					if(!units.contains(u)) {
						if(u instanceof Infantry) {
							JButton unitsButton = new JButton("Unit "+ this.nextUnitNum + "Type: " + "Infantry " +  ", Level: "+ u.getLevel() + ", Status: " + u.getParentArmy().getCurrentStatus() + ", Current Soldier Count: " + u.getCurrentSoldierCount() + ", Max Soldier Count: " + u.getMaxSoldierCount());                                           
							unitsButton.setPreferredSize(new Dimension(500, 100));
							unitsButton.setActionCommand(u + "");
							unitsButton.addActionListener(this);
							add(unitsButton);
							UnitWindow unitWindow = new UnitWindow(graphics, u, x, game, this, controlledArmiesWindow, unitsButton);
							unitWindows.add(unitWindow);
							unitsButtons.add(unitsButton);
							this.nextUnitNum++;
							}
							if(u instanceof Cavalry) {
								JButton unitsButton = new JButton("Unit "+ this.nextUnitNum + "Type: " + "Cavalry " +  ", Level: "+ u.getLevel() + ", Status: " + u.getParentArmy().getCurrentStatus() + ", Current Soldier Count: " + u.getCurrentSoldierCount() + ", Max Soldier Count: " + u.getMaxSoldierCount());                                           
								unitsButton.setPreferredSize(new Dimension(500, 100));
								unitsButton.setActionCommand(u + "");
								unitsButton.addActionListener(this);
								add(unitsButton);
								UnitWindow unitWindow = new UnitWindow(graphics, u, x, game, this, controlledArmiesWindow, unitsButton);
								unitWindows.add(unitWindow);
								unitsButtons.add(unitsButton);
								this.nextUnitNum++;
								}
							if(u instanceof Archer) {
								JButton unitsButton = new JButton("Unit "+ this.nextUnitNum + "Type: Archer" + ", Level: "+ u.getLevel() + ", Status: " + u.getParentArmy().getCurrentStatus() + ", Current Soldier Count: " + u.getCurrentSoldierCount() + ", Max Soldier Count: " + u.getMaxSoldierCount());                                           
								unitsButton.setPreferredSize(new Dimension(500, 100));
								unitsButton.setActionCommand(u + "");
								unitsButton.addActionListener(this);
								add(unitsButton);
								UnitWindow unitWindow = new UnitWindow(graphics, u, x, game, this, controlledArmiesWindow, unitsButton);
								unitWindows.add(unitWindow);
								unitsButtons.add(unitsButton);
								this.nextUnitNum++;
								}
					}
				}
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for(UnitWindow w : unitWindows) {
			if(e.getActionCommand().equals(w.getUnit() + "")) {
				w.setVisible(true);
			}
		}
		
	}

}
