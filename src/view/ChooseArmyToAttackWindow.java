package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.*;

import engine.Game;
import units.Army;
import units.Status;

public class ChooseArmyToAttackWindow extends JFrame implements ActionListener{
	private Game game;
	private String targetCity;
	private Graphics graphics;
	private ArrayList<JButton> armyButtons = new ArrayList<JButton>();
	public ChooseArmyToAttackWindow(Graphics graphics, Game game, String targetCity) {
		setSize(400, 400);
		this.game = game;
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.targetCity = targetCity;
		this.graphics = graphics;
		setLocationRelativeTo(null);
		setTitle("Please choose an army!");
		setLayout(new FlowLayout());
		for(Object b : graphics.getChooseArmyToAttackButtons().keySet()) {
				((JButton)b).addActionListener(this);
				((JButton)b).setActionCommand(((Army)graphics.getChooseArmyToAttackButtons().get(b)) + "");
				if(((Army)graphics.getChooseArmyToAttackButtons().get(b)).getCurrentStatus().equals(Status.IDLE)) {
					add((JButton)b);
				}
				else {
					graphics.getChooseArmyToAttackButtons().remove(b);
				}
			}
		setVisible(true);
		this.validate();
	}
		/*for(Army a : game.getPlayer().getControlledArmies()) {
			if(a.getCurrentStatus().equals(Status.IDLE)){
				JButton army = new JButton("Army " + game.getPlayer().getControlledArmies().indexOf(a));
				army.addActionListener(this);
				army.setActionCommand(game.getPlayer().getControlledArmies().indexOf(a) + "");
				add(army);
				armyButtons.add(army);
			}
		}*/


	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		JButton temp = null;
		JButton toBeRemoved = null;
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		Army tempArmy = null;
		for(Object b : graphics.getChooseArmyToAttackButtons().keySet()) {
			if(e.getActionCommand().equals(((Army)graphics.getChooseArmyToAttackButtons().get(b)) + "")) {
			    temp = (JButton)b;
			    toBeRemoved = (JButton)b;
				tempArmy = ((Army)graphics.getChooseArmyToAttackButtons().get(b));
				 game.targetCity(tempArmy, targetCity);
				 System.out.print(tempArmy.getDistancetoTarget());
				 graphics.getMarchingArmies().put(temp, tempArmy);
		        break;
			}
		}
		 graphics.getChooseArmyToAttackButtons().remove(toBeRemoved);
		/*for(int i = 0; i < game.getPlayer().getControlledArmies().size(); i++) {
			if(e.getActionCommand().equals(i + "")) {
				if(graphics.getIdleArmies().size() != 0) {
					 JButton temp = graphics.getIdleArmies().get(i);
					 armyButtons.get(i).setVisible(false);
					 armyButtons.remove(i);
					 graphics.getIdleArmies().remove();
					 graphics.getMarchingArmies()
					 MarchingStartedWindow marchingStartedWindow = new MarchingStartedWindow();
					setVisible(false);
				 }
			}
		}*/
		
	}

}
