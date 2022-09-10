package view;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import buildings.EconomicBuilding;
import engine.City;
import engine.Game;
import exceptions.NotEnoughGoldException;
import units.Unit;


public class ControlledCityWindow extends JFrame implements ActionListener{
	private JButton buyMarket;
	private JButton buyFarm; 
	private JButton buyArcheryRange;
	private JButton buyStable;
	private JButton buyBarracks; 
	private JPanel buildings;
	private JButton farm;
	private JButton market;
	private JButton archeryRange;
	private JButton stable;
	private JButton barracks;
	private JButton showDefendingArmy;
	private String currCity;
	private Game game;
	private EssentialsPanel essentialsPanel;
	private WorldMapWindow worldMapWindow;
	private ArrayList<UpgradeEconomicalsWindow> upgradeWindows;
	private Graphics graphics;
	private JButton defendingArmy;
	private ArrayList<Unit> units = new ArrayList<Unit>();
	private DefendingArmyWindow defendingArmyWindow;
	private ArrayList<JButton> unitsbuttons;
	private JButton controlledArmiesButton;
	private ControlledArmiesWindow controlledArmiesWindow;
	public String getCurrCity() {
		return currCity;
	}
	
	public JButton getBuyMarket() {
		return buyMarket;
	}
	public JButton getBuyFarm() {
		return buyFarm;
	}
	public JButton getBuyArcheryRange() {
		return buyArcheryRange;
	}
	public JButton getBuyStable() {
		return buyStable;
	}
	public JButton getBuyBarracks() {
		return buyBarracks;
	}
	public ControlledCityWindow(Graphics graphics, String cityName, EssentialsPanel essentialsPanel, WorldMapWindow worldMapWindow, Game game) {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setLayout(new BorderLayout());
		setTitle(cityName + " City");
		currCity = cityName;
		this.essentialsPanel = essentialsPanel;
		controlledArmiesButton = new JButton("Controlled Idle Armies");
		controlledArmiesButton.addActionListener(this);
		showDefendingArmy = new JButton("Show Defending Army");
		showDefendingArmy.addActionListener(this);
		this.essentialsPanel.add(showDefendingArmy);
		this.essentialsPanel.add(controlledArmiesButton);
		worldMapWindow.getEssentialsPanel().add(controlledArmiesButton);
		this.worldMapWindow = worldMapWindow;
		this.graphics = graphics;
		JPanel buttons = new JPanel();
		add(essentialsPanel, BorderLayout.NORTH);
		add(buttons, BorderLayout.CENTER);
		buyFarm = new JButton("Build Farm");
		buyFarm.addActionListener(this);
		buttons.add(buyFarm);
		buyMarket = new JButton("Build Market");
		buyMarket.addActionListener(this);
		buttons.add(buyMarket);
		buyArcheryRange = new JButton("Build Archery Range");
		buyArcheryRange.addActionListener(this);
		buttons.add(buyArcheryRange);
		buyStable = new JButton("Build Stable");
		buyStable.addActionListener(this);
		buttons.add(buyStable);
		buyBarracks = new JButton("Build Barracks");
		buyBarracks.addActionListener(this);
		buttons.add(buyBarracks);
		buttons.setLayout(new FlowLayout());
		buildings = new JPanel();
		buildings.setPreferredSize(new Dimension(800, 400));
		buildings.setLayout(new FlowLayout());
		add(buildings, BorderLayout.SOUTH);
		this.game = game;
		this.validate();
	}

	public void build(String s) {
		if(s.equals("Build Farm")) {
			farm = new JButton("Farm");
			farm.setPreferredSize(new Dimension(130, 190));
			farm.addActionListener(this);
			buildings.add(farm);
		}
		if(s.equals("Build Market")) {
			market = new JButton("Market");
			market.setPreferredSize(new Dimension(130, 190));
			market.addActionListener(this);
			buildings.add(market);
			
		}
		if(s.equals("Build Archery Range")) {
			archeryRange = new JButton("Archery Range");
			archeryRange.setPreferredSize(new Dimension(130, 190));
			archeryRange.addActionListener(this);
			buildings.add(archeryRange);	
		}
		if(s.equals("Build Stable")) {
			stable = new JButton("Stable");
			stable.setPreferredSize(new Dimension(130, 190));
			stable.addActionListener(this);
			buildings.add(stable);
			
		}
		if(s.equals("Build Barracks")) {
			barracks = new JButton("Barracks");
			barracks.setPreferredSize(new Dimension(130, 190));
			barracks.addActionListener(this);
			buildings.add(barracks);
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Show Defending Army")) {
			if(defendingArmyWindow == null) {
				if(controlledArmiesWindow == null) {
				controlledArmiesWindow = new ControlledArmiesWindow();
				}
				defendingArmyWindow = new DefendingArmyWindow(graphics, game, currCity, controlledArmiesWindow);
			}
			else {
				defendingArmyWindow.update();
				defendingArmyWindow.setDefaultDefendingArmyWindowSize();
				
			}
		}
		if(e.getActionCommand().equals("Build Farm")) {
			try {
				game.getPlayer().build("farm", currCity);
				
				this.buyFarm.setVisible(false);
				this.build("Build Farm");
				essentialsPanel.refresh();
				worldMapWindow.getEssentialsPanel().refresh();
			} 
			catch (NotEnoughGoldException e1) {
				NotEnoughGoldWindow notEnoughGoldWindow = new NotEnoughGoldWindow();
			}
		}
		if(e.getActionCommand().equals("Build Market")) {
			try {
				game.getPlayer().build("Market", currCity);
				this.buyMarket.setVisible(false);
				this.build("Build Market");
				essentialsPanel.refresh();
				worldMapWindow.getEssentialsPanel().refresh();
			} 
			catch (NotEnoughGoldException e1) {
				NotEnoughGoldWindow notEnoughGoldWindow = new NotEnoughGoldWindow();
			}
		}
		if(e.getActionCommand().equals("Build Archery Range")) {
			try {
				game.getPlayer().build("ArcheryRange", currCity);
				essentialsPanel.refresh();
				this.buyArcheryRange.setVisible(false);
				this.build("Build Archery Range");
				worldMapWindow.getEssentialsPanel().refresh();
			} 
			catch (NotEnoughGoldException e1) {
				NotEnoughGoldWindow notEnoughGoldWindow = new NotEnoughGoldWindow();
			}
		}
		if(e.getActionCommand().equals("Build Stable")) {
			
			try {
				game.getPlayer().build("Stable", currCity);
				essentialsPanel.refresh();
				this.buyStable.setVisible(false);
				this.build("Build Stable");
				worldMapWindow.getEssentialsPanel().refresh();
			} 
			catch (NotEnoughGoldException e1) {
				NotEnoughGoldWindow notEnoughGoldWindow = new NotEnoughGoldWindow();
			}
		}
		if(e.getActionCommand().equals("Build Barracks")) {
			
			try {
				game.getPlayer().build("Barracks", currCity);
				essentialsPanel.refresh();
				this.buyBarracks.setVisible(false);
				this.build("Build Barracks");
				worldMapWindow.getEssentialsPanel().refresh();
			} 
			catch (NotEnoughGoldException e1) {
				NotEnoughGoldWindow notEnoughGoldWindow = new NotEnoughGoldWindow();
			}
		}
		if(e.getActionCommand().equals("Farm")) {
			boolean exists = false;
			boolean maximum = false;
			if(upgradeWindows == null) {
				
				try {
					game.getPlayer().build("Farm", currCity);
					UpgradeEconomicalsWindow farmWindow = new UpgradeEconomicalsWindow(graphics, game, "Farm", currCity, "");
					upgradeWindows = new ArrayList<UpgradeEconomicalsWindow>();
					upgradeWindows.add(farmWindow);
					exists = true;
					worldMapWindow.getEssentialsPanel().refresh();
					this.essentialsPanel.refresh();
				} catch (NotEnoughGoldException e1) {
					NotEnoughGoldWindow w = new NotEnoughGoldWindow();
				}
				
			}
			else{
				for(UpgradeEconomicalsWindow w : upgradeWindows) {
					if(w.getBuildingType().equalsIgnoreCase("Farm")) {
						exists = true;
						if(w.getBuildingLevel() < 3) {
							w.setState(NORMAL);
							w.setVisible(true);
						}
						else{
							w.setDefaultMaximumLevelSize();
							maximum = true;
						}
					}
				}
				
			}
			if(!exists) {
				try {
					game.getPlayer().build("Farm", currCity);
					UpgradeEconomicalsWindow farmWindow = new UpgradeEconomicalsWindow(graphics, game, "Farm", currCity, "");
					upgradeWindows.add(farmWindow);
					exists = true;
					worldMapWindow.getEssentialsPanel().refresh();
					this.essentialsPanel.refresh();
				} catch (NotEnoughGoldException e1) {
					NotEnoughGoldWindow w = new NotEnoughGoldWindow();
				}
			}
		}
		if(e.getActionCommand().equals("Market")) {
			boolean exists = false;
			boolean maximum = false;
			if(upgradeWindows == null) {
				try {
					game.getPlayer().build("Market", currCity);
					UpgradeEconomicalsWindow marketWindow = new UpgradeEconomicalsWindow(graphics, game, "Market", currCity, "");
					upgradeWindows = new ArrayList<UpgradeEconomicalsWindow>();
					upgradeWindows.add(marketWindow);
					worldMapWindow.getEssentialsPanel().refresh();
					this.essentialsPanel.refresh();
					exists = true;
				} catch (NotEnoughGoldException e1) {
					NotEnoughGoldWindow w = new NotEnoughGoldWindow();
			
			}
		}
			else{
				for(UpgradeEconomicalsWindow w : upgradeWindows) {
					if(w.getBuildingType().equalsIgnoreCase("Market")) {
						exists = true;
						if(w.getBuildingLevel() < 3) {
							w.setVisible(true);
						}
						else{
							w.setDefaultMaximumLevelSize();
							maximum = true;
						}
					}
				}
			}
			if(!exists) {
				try {
					game.getPlayer().build("Market", currCity);
					UpgradeEconomicalsWindow marketWindow = new UpgradeEconomicalsWindow(graphics, game, "Market", currCity, "");
					upgradeWindows.add(marketWindow);
					worldMapWindow.getEssentialsPanel().refresh();
					this.essentialsPanel.refresh();
				} catch (NotEnoughGoldException e1) {
					NotEnoughGoldWindow w = new NotEnoughGoldWindow();
				}
			}

		}
		if(e.getActionCommand().equals("Archery Range")) {
			boolean exists = false;
			boolean maximum = false;
			if(upgradeWindows == null) {
				UpgradeEconomicalsWindow archeryRangeWindow = new UpgradeEconomicalsWindow(graphics, game, "Archery Range", currCity, "Archers");
				upgradeWindows = new ArrayList<UpgradeEconomicalsWindow>();
				upgradeWindows.add(archeryRangeWindow);
				exists = true;
			}
			else{
				for(UpgradeEconomicalsWindow w : upgradeWindows) {
					if(w.getBuildingType().equalsIgnoreCase("Archery Range")) {
						exists = true;
						if(w.getBuildingLevel() < 3) {
							w.setVisible(true);
						}
						else{
							w.setDefaultMaximumLevelSize();
							maximum = true;
						}
					}
				}
			}
			if(!exists) {
				UpgradeEconomicalsWindow archeryRangeWindow = new UpgradeEconomicalsWindow(graphics, game, "Archery Range", currCity, "Archers");
				upgradeWindows.add(archeryRangeWindow);
			}

		}
		if(e.getActionCommand().equals("Stable")) {
			boolean exists = false;
			boolean maximum = false;
			if(upgradeWindows == null) {
				UpgradeEconomicalsWindow stableWindow = new UpgradeEconomicalsWindow(graphics, game, "Stable", currCity, "Cavalry");
				upgradeWindows = new ArrayList<UpgradeEconomicalsWindow>();
				upgradeWindows.add(stableWindow);
				exists = true;
			}
			else{
				for(UpgradeEconomicalsWindow w : upgradeWindows) {
					if(w.getBuildingType().equalsIgnoreCase("Stable")) {
						exists = true;
						if(w.getBuildingLevel() < 3) {
							w.setVisible(true);
						}
						else{
							w.setDefaultMaximumLevelSize();
							maximum = true;
						}
					}
				}
			}
			if(!exists) {
				UpgradeEconomicalsWindow stableWindow = new UpgradeEconomicalsWindow(graphics, game, "Stable", currCity, "Cavalry");
				upgradeWindows.add(stableWindow);
			}

		}
		if(e.getActionCommand().equals("Barracks")) {
			boolean exists = false;
			boolean maximum = false;
			if(upgradeWindows == null) {
				UpgradeEconomicalsWindow barracksWindow = new UpgradeEconomicalsWindow(graphics, game, "Barracks", currCity, "Infantry");
				upgradeWindows = new ArrayList<UpgradeEconomicalsWindow>();
				upgradeWindows.add(barracksWindow);
				exists = true;
			}
			else{
				for(UpgradeEconomicalsWindow w : upgradeWindows) {
					if(w.getBuildingType().equalsIgnoreCase("Barracks")) {
						exists = true;
						if(w.getBuildingLevel() < 3) {
							w.setVisible(true);
						}
						else{
							w.setDefaultMaximumLevelSize();
							maximum = true;
						}
					}
				}
			}
			if(!exists) {
				UpgradeEconomicalsWindow barracksWindow = new UpgradeEconomicalsWindow(graphics, game, "Barracks", currCity, "Infantry");
				upgradeWindows.add(barracksWindow);
			}
		}
		if(e.getActionCommand().equals("Controlled Idle Armies")) {
			if(controlledArmiesWindow == null) {
				return;			
				}
			else {
				controlledArmiesWindow.rel();
			}
		}
	}
	
}
