package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.*;

import engine.Game;
import exceptions.FriendlyFireException;
import units.Army;
import units.Unit;

public class BattleView extends JFrame implements ActionListener{
	private ArrayList<Unit> defendingArmyUnits = new  ArrayList<Unit>();
	private ArrayList<Unit> attackingArmyUnits = new  ArrayList<Unit>();
	private ArrayList<JButton> defendingArmyButtons = new  ArrayList<JButton>();
	private ArrayList<JButton> attackingArmyButtons = new  ArrayList<JButton>();
	private int defendingArmyCounter = 0;
	private int attackingArmyCounter = 0;
	private JLabel logText;
	private ArrayList<Unit> units = new ArrayList<Unit>();
	private Army attackingArmy;
	private Army defendingArmy;
	private Game game;
	private Graphics graphics;
	public BattleView(Graphics graphics, Game game, Army attackingArmy, Army defendingArmy) {
		JPanel defendingArmyPanel = new JPanel();
		JPanel attackingArmyPanel = new JPanel();
		JPanel logPanel = new JPanel();
		logPanel.setLayout(new FlowLayout());
		this.graphics = graphics;
		this.game = game;
		add(logPanel, BorderLayout.NORTH);
		logText = new JLabel("Choose Units to Start Battle!");
		logPanel.add(logText);
		this.attackingArmy = attackingArmy;
		this.defendingArmy = defendingArmy;
		defendingArmyPanel.setLayout(new BoxLayout(defendingArmyPanel, BoxLayout.Y_AXIS));
		attackingArmyPanel.setLayout(new BoxLayout(attackingArmyPanel, BoxLayout.Y_AXIS));
		defendingArmyPanel.setPreferredSize(new Dimension(580, 700));
		attackingArmyPanel.setPreferredSize(new Dimension(580, 700));
		add(defendingArmyPanel, BorderLayout.EAST);
		add(attackingArmyPanel, BorderLayout.WEST);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		for(Unit u : defendingArmy.getUnits()) {
			JButton b = new JButton("Unit " + this.defendingArmyCounter + " Type: Archer" + ", Level: "+ u.getLevel() + ", Status: " + u.getParentArmy().getCurrentStatus() + ", Current Soldier Count: " + u.getCurrentSoldierCount() + ", Max Soldier Count: " + u.getMaxSoldierCount());                                           
			b.addActionListener(this);
			b.setActionCommand("d" + this.defendingArmyCounter);
			defendingArmyUnits.add(u);
			defendingArmyPanel.add(b);
			defendingArmyButtons.add(b);
			this.defendingArmyCounter++;
		}
		for(Unit u : attackingArmy.getUnits()) {
			JButton b = new JButton("Unit " + this.attackingArmyCounter + " Type: Archer" + ", Level: "+ u.getLevel() + ", Status: " + u.getParentArmy().getCurrentStatus() + ", Current Soldier Count: " + u.getCurrentSoldierCount() + ", Max Soldier Count: " + u.getMaxSoldierCount());                                           
			b.addActionListener(this);
			b.setActionCommand("a" + this.attackingArmyCounter);
			attackingArmyUnits.add(u);
			attackingArmyPanel.add(b);
			attackingArmyButtons.add(b);
			this.attackingArmyCounter++;
		}
		setVisible(true);
		this.validate();
	}
	public void updateUnits(ArrayList<Unit> units, ArrayList<JButton> buttons){
		for(int i = 0; i < buttons.size(); i++) {
			if(units.get(i).getCurrentSoldierCount() == 0) {
				buttons.get(i).setVisible(false);
			}
			buttons.get(i).setText("Unit " + i + " Type: Archer" + ", Level: "+ units.get(i).getLevel() + ", Status: " + units.get(i).getParentArmy().getCurrentStatus() + ", Current Soldier Count: " + units.get(i).getCurrentSoldierCount() + ", Max Soldier Count: " + units.get(i).getMaxSoldierCount());
		}
	}
	public void attack() {
		try {
			units.get(0).attack(units.get(1));
			defendingArmyUnits.get((int)(Math.random() * defendingArmyUnits.size())).attack(attackingArmyUnits.get((int)(Math.random() * attackingArmyUnits.size())));
			logText.setText("The attacked unit has lost " + (units.get(1).getMaxSoldierCount()-units.get(1).getCurrentSoldierCount()));
			this.units.clear();
			updateUnits(defendingArmyUnits, defendingArmyButtons);
			updateUnits(attackingArmyUnits, attackingArmyButtons);
			if(attackingArmy.getUnits().size() == 0) {
				Army c = null;
				for(Army a : game.getPlayer().getControlledArmies()) {
					if(a.equals(attackingArmy)) {
						c = a;
					}
				}
				if(c != null) {
					game.getPlayer().getControlledArmies().remove(c);	
				}
				EndOfBattleWindow w = new EndOfBattleWindow("You Lost the Battle!");
				graphics.getTargets().remove(defendingArmy.getCurrentLocation());
				dispose();
			}
			
			if(defendingArmy.getUnits().size() == 0) {
				HostileCityWindow c = null;
				game.occupy(attackingArmy, defendingArmy.getCurrentLocation());
				if(defendingArmy.getCurrentLocation().equals("Rome")) {
					EssentialsPanel romeEssentialsPanel = new EssentialsPanel(graphics, game.getPlayer().getName(), "Rome", game);
					romeEssentialsPanel.refresh();
					ControlledCityWindow w = new ControlledCityWindow(graphics, "Rome", romeEssentialsPanel, graphics.getWorldMapWindow(), game);
					graphics.getControlledCitiesWindows().add(w);
					for(HostileCityWindow a : graphics.getHostileCitiesWindows()) {
						if(a.getCityName().equals("Rome")) {
							c = a;
						}
					}
					if(c  != null) {
						graphics.getHostileCitiesWindows().remove(c);
					}
				}
				if(defendingArmy.getCurrentLocation().equals("Cairo")) {
					HostileCityWindow d= null;
					EssentialsPanel cairoEssentialsPanel = new EssentialsPanel(graphics, game.getPlayer().getName(), "Cairo", game);
					cairoEssentialsPanel.refresh();
					ControlledCityWindow w = new ControlledCityWindow(graphics, "Cairo", cairoEssentialsPanel, graphics.getWorldMapWindow(), game);
					graphics.getControlledCitiesWindows().add(w);
					for(HostileCityWindow a : graphics.getHostileCitiesWindows()) {
						if(a.getCityName().equals("Cairo")) {
							d = a;
						}
					}
					if(d  != null) {
						graphics.getHostileCitiesWindows().remove(d);
					}
				}
				if(defendingArmy.getCurrentLocation().equals("Sparta")) {
					HostileCityWindow e = null;
					EssentialsPanel spartaEssentialsPanel = new EssentialsPanel(graphics,game.getPlayer().getName(), "Sparta",  game);
					spartaEssentialsPanel.refresh();
					ControlledCityWindow w = new ControlledCityWindow(graphics, "Sparta", spartaEssentialsPanel, graphics.getWorldMapWindow(), game);
					graphics.getControlledCitiesWindows().add(w);
					for(HostileCityWindow a : graphics.getHostileCitiesWindows()) {
						if(a.getCityName().equals("Sparta")) {
							e = a;
						}
					}
					if(e  != null) {
						graphics.getHostileCitiesWindows().remove(e);
					}
				}
				EndOfBattleWindow w = new EndOfBattleWindow("You Won the Battle, City " +  defendingArmy.getCurrentLocation() + " is Occupied!");
				dispose();
			}
			return;
		} catch (FriendlyFireException e1) {
			FriendlyFireWindow f = new FriendlyFireWindow();
			this.units.clear();
			return;
			}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i<1000; i++) {
			if(e.getActionCommand().equals("a" + i)) {
				System.out.println("Att");
				this.units.add(((Unit)attackingArmyUnits.get(i)));
				}
			if(e.getActionCommand().equals("d" + i)) {
					this.units.add(((Unit)defendingArmyUnits.get(i)));
					if(this.units.size() == 2) {
						attack();
					}
			}
		}
	 }
}
