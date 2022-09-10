package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import engine.Game;

public class EssentialsPanel extends JPanel{
	private JLabel playerName;
	private JLabel turnCount;
	private JLabel gold;
	private JLabel food;
	private JLabel controlledCities;
	private Game game;
	private JButton endTurn;
	public EssentialsPanel(Graphics graphics, String name, String city, Game game) {
		setLayout(new FlowLayout());
		setBounds(25, 10, 200, 200);
		setPreferredSize(new Dimension(200, 50));
		endTurn = new JButton("End Turn");
		endTurn.addActionListener(graphics);
		playerName = new JLabel("Player: "+ name + "   ");
		controlledCities = new JLabel("Controlled Cities: " + city + "   ");
		food = new JLabel("Food: "+ game.getPlayer().getFood() + "   ");
		gold = new JLabel("Gold: "+ game.getPlayer().getTreasury()+ "   ");
		turnCount = new JLabel("Turn Count: " + game.getCurrentTurnCount() + "/50  ");
		add(playerName);
		add(controlledCities);
		add(food);
		add(gold);
		add(turnCount);
		add(endTurn);
		this.game = game;
	}
	public JButton getEndTurn() {
		return endTurn;
	}
	public void refresh() {
		food.setText("Food: "+ game.getPlayer().getFood() + "   ");
		gold.setText("Gold: "+ game.getPlayer().getTreasury() + "   ");
		turnCount.setText("Turn Count: "+ game.getCurrentTurnCount() + "/50  ");
		repaint();
	}
	public JLabel getTurnCount() {
		return turnCount;
	}
	public void setTurnCount(JLabel turnCount) {
		this.turnCount = turnCount;
	}
	public JLabel getGold() {
		return gold;
	}
	public void setGold(JLabel gold) {
		this.gold = gold;
	}
	public JLabel getFood() {
		return food;
	}
	public void setFood(JLabel food) {
		this.food = food;
	}
	public void endTurn() {
		
	}
}
