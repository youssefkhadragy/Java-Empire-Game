package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import engine.City;
import engine.Game;
import exceptions.FriendlyFireException;
import units.Army;

public class AttackOrAutoResolveWindow extends JFrame implements ActionListener{
	private Game game;
	private String targetCity;
	private Army army;
	public AttackOrAutoResolveWindow(LaySiegeOrAttackWindow laySiegeOrAttackWindow, Army army, Game game, Graphics graphics, String targetCity){
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(300, 200);
		setTitle("Attack Or AutoResolve?");
		JButton attack = new JButton("Attack?");
		JButton autoResolve = new JButton("Auto Resolve?");
		setLayout(new FlowLayout());
		attack.addActionListener(laySiegeOrAttackWindow);
		autoResolve.addActionListener(laySiegeOrAttackWindow);
		autoResolve.addActionListener(this);
		attack.addActionListener(this);
		this.targetCity = targetCity;
		setLocationRelativeTo(null);
		add(attack);
		add(autoResolve);
		this.army = army;
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Attack?")) {
			setVisible(false);
		}
		if(e.getActionCommand().equals("AutoResolve?")) {
			Army defendingArmy = null;
			for(City x : game.getAvailableCities()) {
				if(x.getName().equals(targetCity)) {
					defendingArmy = x.getDefendingArmy();
				}
			}
			try {
				game.autoResolve(army, defendingArmy);
			} catch (FriendlyFireException e1) {
				return;
			}
			if(army.getUnits().size() == 0) {
				
			}
			this.setVisible(false);
		}
	}
	
}
