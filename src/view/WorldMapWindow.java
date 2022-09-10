package view;
import javax.swing.*;

import engine.City;
import engine.Game;
import engine.Player;
import units.Army;
import units.Status;

import java.awt.*;
import java.awt.event.*;

public class WorldMapWindow extends JFrame{
	private JLabel playerName;
	private JLabel turnCount;
	private JLabel gold;
	private JLabel food;
	private JLabel chosenCity;
	private Game game;
	private EssentialsPanel essentialsPanel;
	private JPanel besiegingArmiesPanel;
	private JPanel marchingArmiesPanel;
	private int bmc = 0;
	
	public EssentialsPanel getEssentialsPanel() {
		return essentialsPanel;
		
	}
	public WorldMapWindow(Graphics graphics, JPanel bes, JPanel marchingArmies, String name, String city, EssentialsPanel essentialsPanel,  Game game) {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		this.game = game;
		this.essentialsPanel = essentialsPanel;
		/*JPanel nameAndCity = new JPanel();
		nameAndCity.setLayout(new FlowLayout());
		nameAndCity.setBounds(25, 10, 200, 200);
		playerName = new JLabel("Player: "+ name + "   ");
		chosenCity = new JLabel("Chosen City: " + city + "   ");
		food = new JLabel("Food: "+ game.getPlayer().getFood() + "   ");
		gold = new JLabel("Gold: "+ game.getPlayer().getTreasury()+ "   ");
		nameAndCity.add(playerName);
		nameAndCity.add(chosenCity);
		nameAndCity.add(food);
		nameAndCity.add(gold);
		add(nameAndCity, BorderLayout.NORTH);
		nameAndCity.setPreferredSize(new Dimension(300, 300));*/
		add(essentialsPanel, BorderLayout.NORTH);
		JPanel availableCities = new JPanel();
		besiegingArmiesPanel = new JPanel();
		besiegingArmiesPanel.setPreferredSize(new Dimension(500, 700));
		besiegingArmiesPanel.setLayout(new BoxLayout(besiegingArmiesPanel, BoxLayout.Y_AXIS));
		marchingArmiesPanel = new JPanel();
		marchingArmiesPanel.setLayout(new BoxLayout(marchingArmiesPanel, BoxLayout.Y_AXIS));
		marchingArmiesPanel.setPreferredSize(new Dimension(500, 700));
		availableCities.setLayout(new FlowLayout());
		availableCities.setPreferredSize(new Dimension(300, 600));
		for(City c : game.getAvailableCities()) {
			JButton b = new JButton(c.getName());
			b.setPreferredSize(new Dimension(120, 550));
			b.addActionListener(graphics);
			availableCities.add(b);
		}
		/*for(int i = 0; i < game.getPlayer().getControlledArmies().size(); i++) {
			if(game.getPlayer().getControlledArmies().get(i).equals(Status.IDLE)) {
				JButton b = new JButton("Army: " + i);
				b.setPreferredSize(new Dimension(20, 40));
				besiegingArmiesPanel.add(b);
				
			}
		}*/
		add(availableCities, BorderLayout.SOUTH);
		add(marchingArmiesPanel, BorderLayout.WEST);
		add(besiegingArmiesPanel, BorderLayout.EAST);
		this.validate();
	}
	
	public void refreshArmiesPanel() {
		marchingArmiesPanel.removeAll();
		besiegingArmiesPanel.removeAll();
		for(Army a : game.getPlayer().getControlledArmies()) {
			if(a.getCurrentStatus().equals(Status.MARCHING)) {
				JButton b = new JButton("Army " + bmc + "Status: Marching " + "Target: " + a.getTarget() + "Turns Till Target is Reached: " + a.getDistancetoTarget());
				marchingArmiesPanel.add(b);
				System.out.print(a);
			}
			if(a.getCurrentStatus().equals(Status.BESIEGING)) {
				JButton b = new JButton("Army " + bmc + "Status: Besieging " + "Target: " + a.getTarget() + "Turns Till Target is Reached: " + a.getDistancetoTarget());
				besiegingArmiesPanel.add(b);
			}
		}
	}

}
