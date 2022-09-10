package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import engine.City;
import engine.Game;
import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.TargetNotReachedException;
import units.Army;

public class LaySiegeOrAttackWindow extends JFrame implements ActionListener{
	private Game game;
	private City targetCity;
	private Army army;
	private JButton laySiege;
	private JButton autoResolve;
	private JButton attack;
	private String city;
	private Graphics graphics;
	public LaySiegeOrAttackWindow(Graphics graphics, Army army, Game game, String targetCity) {
		this.army = army;
		for(City x : game.getAvailableCities()) {
			if(x.getName().equalsIgnoreCase(targetCity)) {
				this.targetCity = x;
			}
		}
		this.city = targetCity;
		setSize(500, 200);
		this.game = game;
		this.graphics = graphics;
		setTitle("Lay Siege Or Attack " + targetCity + "?");
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		laySiege = new JButton("Lay Siege?");
		autoResolve = new JButton("Auto Resolve?");
		autoResolve.addActionListener(this);;
		attack = new JButton("Attack?");
		attack.addActionListener(this);
		laySiege.addActionListener(this);
		add(laySiege);
		add(autoResolve);
		add(attack);
		this.validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Lay Siege?")) {
			try {
				game.getPlayer().laySiege(army, this.targetCity);
				graphics.getWorldMapWindow().refreshArmiesPanel();
				JButton tempButton = null;
				Army tempArmy;
				for(Object b : graphics.getIdleArmies().keySet()) {
					if(((Army)graphics.getIdleArmies().get(b)).equals(army)) {
						tempButton = (JButton)b;
						tempArmy = army;
						graphics.getBesiegingArmies().put(tempButton, tempArmy);
					}
					graphics.getIdleArmies().remove(tempButton);
				}
				setVisible(false);
				laySiege.setText("Continue Siege?");
				laySiege.setActionCommand("Continue Siege?");
			} catch (TargetNotReachedException e1) {
				return;
			} catch (FriendlyCityException e1) {
				return;
			}
		}
		if(e.getActionCommand().equals("Auto Resolve?")) {
			Army defendingArmy = null;
			for(City x : game.getAvailableCities()) {
				if(x.getName().equals(city)) {
					defendingArmy = x.getDefendingArmy();
				}
			}
			try {
				game.autoResolve(army, defendingArmy);
				if(army.getUnits().size() == 0) {
					Army c = null;
					for(Army a : game.getPlayer().getControlledArmies()) {
						if(a.equals(army)) {
							c = a;
						}
					}
					if(c != null) {
						game.getPlayer().getControlledArmies().remove(c);	
					}
					EndOfBattleWindow w = new EndOfBattleWindow("You Lost!");
					setVisible(false);
					graphics.getTargets().remove(city);
				}
				else if(defendingArmy.getUnits().size() == 0) {
					EndOfBattleWindow w = new EndOfBattleWindow("You Won!");
					setVisible(false);
				}
			} catch (FriendlyFireException e1) {
				return;
			}
		}
		if(e.getActionCommand().equals("Continue Siege?")) {
			for(City x : game.getAvailableCities()) {
				if(x.getName().equals(city)) {
					if(x.getTurnsUnderSiege() < 3) {
						setVisible(false);
					}
					else if(x.getTurnsUnderSiege() == 3) {
						graphics.getLaySiegeWindows().remove(this);
						setVisible(false);
						AttackOrAutoResolveWindow w = new AttackOrAutoResolveWindow(this, army, game, graphics, city);
					}
				}
			}
		}
		if(e.getActionCommand().equals("Attack?")) {
			setVisible(false);
			Army defendingArmy = null;
			for(City x : game.getAvailableCities()) {
				if(x.getName().equals(city)) {
					defendingArmy = x.getDefendingArmy();
				}
			}
			BattleView battleView = new BattleView(graphics, game, army, defendingArmy);
		}		
	}	
}
