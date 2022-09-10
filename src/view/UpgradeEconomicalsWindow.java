package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import engine.City;
import engine.Game;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import units.Unit;


public class UpgradeEconomicalsWindow extends JFrame implements ActionListener{
	private String buildingCity;
	private String buildingType;
	private JLabel level;
	private JButton upgrade;
	private Game game;
	private boolean maximumLevelReached = false;
	private int buildingLevel;
	private EssentialsPanel essentialsPanel;
	private Graphics graphics;
	private JButton recruitUnit;
	private String unitType;
	private int currRecruitmentCost;
	private  ArrayList<Unit> units;
	private ArrayList<UnitWindow> unitWindows = new ArrayList<UnitWindow>();
	public void setDefaultMaximumLevelSize() {
		setVisible(false);
		setState(NORMAL);
		setSize(400, 130);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public UpgradeEconomicalsWindow(Graphics graphics, Game game, String type, String cityName, String unitType) {
		setSize(400, 300);
		setTitle(type);
		this.buildingType = type;
		this.buildingCity = cityName;
		this.game = game;
		this.graphics = graphics;
		setLayout(new FlowLayout());
		this.unitType = unitType;
		setLocationRelativeTo(null);
		this.units = units;
		setVisible(true);
		for(City x : game.getPlayer().getControlledCities()) {
			if(x.getName().equalsIgnoreCase(cityName)) {
				for(EconomicBuilding b : x.getEconomicalBuildings()) {
					if(b instanceof Farm) {
						if(("Farm").equalsIgnoreCase(type)) {
							level = new JLabel("Level: " + b.getLevel() + "");
							upgrade = new JButton("Upgrade to Level " + (b.getLevel() + 1) + " for " + b.getUpgradeCost());
							upgrade.addActionListener(this);
							upgrade.setActionCommand("Upgrade " + buildingType);
							add(level);
							add(upgrade);
						}
				}
					if(b instanceof Market) {
						if(("Market").equalsIgnoreCase(type)) {
							level = new JLabel("Level: " + b.getLevel() + "");
							upgrade = new JButton("Upgrade to Level " + (b.getLevel()+1) + " for " + b.getUpgradeCost());
							upgrade.addActionListener(this);
							upgrade.setActionCommand("Upgrade " + buildingType);
							add(level);
							add(upgrade);
						}
				}	
			}
				for(MilitaryBuilding b : x.getMilitaryBuildings()) {
					if(b instanceof ArcheryRange) {
						if(("Archery Range").equalsIgnoreCase(type)) {
							System.out.print("hi");
							level = new JLabel("Level: " + b.getLevel() + "");
							upgrade = new JButton("Upgrade to Level " + (b.getLevel() + 1) + " for " + b.getUpgradeCost());
							recruitUnit = new JButton("Recruit " + "Archers " +  "for  " + b.getRecruitmentCost());
							upgrade.addActionListener(this);
							recruitUnit.addActionListener(this);
							recruitUnit.setActionCommand("Recruit Archers");
							upgrade.setActionCommand("Upgrade " + buildingType);
							add(level);
							add(upgrade);
							add(recruitUnit);
						}
				}
					if(b instanceof Stable) {
						if(("Stable").equalsIgnoreCase(type)) {
							System.out.print("hi");
							level = new JLabel("Level: " + b.getLevel() + "");
							upgrade = new JButton("Upgrade to Level " + (b.getLevel()+1) + " for " + b.getUpgradeCost());
							recruitUnit = new JButton("Recruit " + "Cavalry " +  "for  " + b.getRecruitmentCost());
							upgrade.addActionListener(this);
							recruitUnit.addActionListener(this);
							recruitUnit.setActionCommand("Recruit Cavalry");
							upgrade.setActionCommand("Upgrade " + buildingType);
							add(level);
							add(upgrade);
							add(recruitUnit);
						}
				}	
					if(b instanceof Barracks) {
						if(("Barracks").equalsIgnoreCase(type)) {
							System.out.print("hi");
							level = new JLabel("Level: " + b.getLevel() + "");
							upgrade = new JButton("Upgrade to Level " + (b.getLevel()+1) + " for " + b.getUpgradeCost());
							recruitUnit = new JButton("Recruit " + "Infantry " + " for " + b.getRecruitmentCost());
							recruitUnit = new JButton("Recruit " + "Infantary " +  "for  " + b.getRecruitmentCost());
							recruitUnit.addActionListener(this);
							recruitUnit.setActionCommand("Recruit Infantry");
							upgrade.addActionListener(this);
							upgrade.setActionCommand("Upgrade " + buildingType);
							add(level);
							add(upgrade);
							add(recruitUnit);
						}
				}	
			}
		}
	}
		this.validate();
}
	public String getBuildingCity() {
		return buildingCity;
	}
	public void setBuildingCity(String cityName) {
		this.buildingCity = cityName;
	}
	public String getBuildingType() {
		return buildingType;
	}
	public void setType(String type) {
		this.buildingType = buildingType;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Recruit " + unitType)) {
			System.out.print("hi");
			for(City x : game.getPlayer().getControlledCities()) {
				if(x.getName().equals(buildingCity)) {
					for(MilitaryBuilding b : x.getMilitaryBuildings()) {
						if(b instanceof ArcheryRange && ("Archers").equals(unitType)){
							try {
								game.getPlayer().recruitUnit("archer", buildingCity);
								currRecruitmentCost = b.getRecruitmentCost();
								graphics.getWorldMapEssentialsPanel().refresh();
								graphics.getControlledEssentialsPanel().refresh();
		
								if(b.getCurrentRecruit() < b.getMaxRecruit() ) {
									recruitUnit.setText("Recruit " + "Archers " + "for " + b.getRecruitmentCost());
									setVisible(false);
								}
								else if(b.getCurrentRecruit() == b.getMaxRecruit()) {
									recruitUnit.setText(" Maximum Units Recruited!");
								}
							} catch (BuildingInCoolDownException e1) {
								InCoolDownWindow c = new InCoolDownWindow("Building in Cool Down! Units Can't be Recruited!");
							} catch (MaxRecruitedException e1) {
								MaximumLevelWindow w = new MaximumLevelWindow("Maximum Units Recruited");
							} catch (NotEnoughGoldException e1) {
								NotEnoughGoldWindow w = new NotEnoughGoldWindow();
							}
					}
						if(b instanceof Stable && ("Cavalry").equals(unitType)){
							try {
								game.getPlayer().recruitUnit("cavalry", buildingCity);
								currRecruitmentCost = b.getRecruitmentCost();
								graphics.getWorldMapEssentialsPanel().refresh();
								graphics.getControlledEssentialsPanel().refresh();
								if(b.getCurrentRecruit() < b.getMaxRecruit() ) {
									recruitUnit.setText("Recruit " + "Cavalry " + "for " + b.getRecruitmentCost());
									setVisible(false);
								}
								else if(b.getCurrentRecruit() == b.getMaxRecruit()) {
									recruitUnit.setText(" Maximum Units Recruited!");
								}
							} catch (BuildingInCoolDownException e1) {
								InCoolDownWindow c = new InCoolDownWindow("Building in Cool Down! Units Can't be Recruited!");
							} catch (MaxRecruitedException e1) {
								MaximumLevelWindow w = new MaximumLevelWindow("Maximum Units Recruited");
							} catch (NotEnoughGoldException e1) {
								NotEnoughGoldWindow w = new NotEnoughGoldWindow();
							}
					}
						if(b instanceof Barracks && ("Infantry").equals(unitType)){
							try {
								game.getPlayer().recruitUnit("infantry", buildingCity);
								currRecruitmentCost = b.getRecruitmentCost();
								graphics.getWorldMapEssentialsPanel().refresh();
								graphics.getControlledEssentialsPanel().refresh();
								if(b.getCurrentRecruit() < b.getMaxRecruit() ) {
									recruitUnit.setText("Recruit " + "Infantry " + "for " + b.getRecruitmentCost());
									setVisible(false);
								}
								else if(b.getCurrentRecruit() == b.getMaxRecruit()) {
									recruitUnit.setText(" Maximum Units Recruited!");
								}
							} catch (BuildingInCoolDownException e1) {
								InCoolDownWindow c = new InCoolDownWindow("Building in Cool Down! Units Can't be Recruited!");
							} catch (MaxRecruitedException e1) {
								MaximumLevelWindow w = new MaximumLevelWindow("Maximum Units Recruited");
							} catch (NotEnoughGoldException e1) {
								NotEnoughGoldWindow w = new NotEnoughGoldWindow();
							}
					}
				}
			}
		}
	}
		if(e.getActionCommand().equals("Upgrade " + buildingType)) {
			for(City x : game.getPlayer().getControlledCities()) {
				if(x.getName().equals(buildingCity)) {
					for(EconomicBuilding b : x.getEconomicalBuildings()) {
						if(b instanceof Farm) {
							if(("Farm").equals(buildingType)) {
								if(!maximumLevelReached) {
									try {
										game.getPlayer().upgradeBuilding(b);
										graphics.getWorldMapEssentialsPanel().refresh();
										graphics.getControlledEssentialsPanel().refresh();
										this.buildingLevel = b.getLevel();
										System.out.println(this.buildingLevel);
										if(b.getLevel() < 3) {
											level.setText("Level: " + b.getLevel() + "");
											upgrade.setText("Upgrade to Level " + (b.getLevel()+1) + " for " + b.getUpgradeCost());
											setVisible(false);
										}
										else if(b.getLevel() == 3) {
											maximumLevelReached = true;
											level.setText("Level: " + 3 + "");
											upgrade.setVisible(false);
											remove(upgrade);
											setVisible(false);
											setSize(400,130);
											setTitle("Farm Has Reached Maximum Level!");
										}
										SuccessfullUpgradeWindow successfullUpgradeWindow = new SuccessfullUpgradeWindow(buildingType);
									} catch (BuildingInCoolDownException | MaxLevelException e1) {
										if(e1.getMessage().equalsIgnoreCase("Building is in cool down. Wait for the next turn ")) {
											InCoolDownWindow inCoolDownWindow = new InCoolDownWindow(buildingType);
											setVisible(false);
										}
										if(e1.getMessage().equalsIgnoreCase("Maximum level reached!!")) {
											MaximumLevelWindow maximumLevelWindow = new MaximumLevelWindow(buildingType);
											setVisible(false);
											
										}
									} catch (NotEnoughGoldException e1) {
										NotEnoughGoldWindow w = new NotEnoughGoldWindow();
										setVisible(false);
									}
									
							  }
								else {
									MaximumLevelWindow maximumLevelWindow = new MaximumLevelWindow(buildingType);
								}
						}
					}
						if(b instanceof Market) {
							if(("Market").equalsIgnoreCase(buildingType)) {
								if(!maximumLevelReached) {	
									try {
										game.getPlayer().upgradeBuilding(b);
										graphics.getWorldMapEssentialsPanel().refresh();
										graphics.getControlledEssentialsPanel().refresh();
										this.buildingLevel = b.getLevel();
										if(b.getLevel() < 3) {
											level.setText("Level: " + b.getLevel() + "");
											upgrade.setText("Upgrade to Level " + (b.getLevel()+1) + " for " + b.getUpgradeCost());
											setVisible(false);
										}
										else if(b.getLevel() == 3) {
											maximumLevelReached = true;
											level.setText("Level: " + 3 + "");
											upgrade.setVisible(false);
											remove(upgrade);
											setVisible(false);
											setSize(400,130);
											setTitle("Market Has Reached Maximum Level!");
										}
										SuccessfullUpgradeWindow successfullUpgradeWindow = new SuccessfullUpgradeWindow(buildingType);
									} 
									catch (BuildingInCoolDownException | MaxLevelException e1) {
										if(e1.getMessage().equalsIgnoreCase("Building is in cool down. Wait for the next turn ")) {
											InCoolDownWindow inCoolDownWindow = new InCoolDownWindow(buildingType);
											setVisible(false);
										}
										if(e1.getMessage().equalsIgnoreCase("Maximum level reached!!")) {
											MaximumLevelWindow maximumLevelWindow = new MaximumLevelWindow(buildingType);
											maximumLevelReached = true;
											setVisible(false);
										}
									} catch (NotEnoughGoldException e1) {
										NotEnoughGoldWindow w = new NotEnoughGoldWindow();
										setVisible(false);
									}
							  }
								else{
									MaximumLevelWindow maximumLevelWindow = new MaximumLevelWindow(buildingType);
								}
							}
						}
					}
					for(MilitaryBuilding b : x.getMilitaryBuildings()) {
						if(b instanceof ArcheryRange) {
							if(("Archery Range").equals(buildingType)) {
								if(!maximumLevelReached) {
									try {
										game.getPlayer().upgradeBuilding(b);
										currRecruitmentCost = b.getRecruitmentCost();
										graphics.getWorldMapEssentialsPanel().refresh();
										graphics.getControlledEssentialsPanel().refresh();
										this.buildingLevel = b.getLevel();
										System.out.println(this.buildingLevel);
										if(b.getLevel() < 3) {
											recruitUnit.setText("Recruit " + "Archers " + "for " + currRecruitmentCost);
											level.setText("Level: " + b.getLevel() + "");
											upgrade.setText("Upgrade to Level " + (b.getLevel()+1) + " for " + b.getUpgradeCost());
											setVisible(false);
										}
										else if(b.getLevel() == 3) {
											currRecruitmentCost = b.getRecruitmentCost();
											recruitUnit.setText("Recruit " + "Archers " + "for " + currRecruitmentCost);
											if(b.getCurrentRecruit() >= b.getMaxRecruit()) {
												recruitUnit.setText("Maximum units Recruited!");
											}
											maximumLevelReached = true;
											level.setText("Level: " + 3 + "");
											upgrade.setVisible(false);
											remove(upgrade);
											setVisible(false);
											setSize(400,130);
											setTitle("Archery Range Has Reached Maximum Level!");
										}
										SuccessfullUpgradeWindow successfullUpgradeWindow = new SuccessfullUpgradeWindow(buildingType);
									} catch (BuildingInCoolDownException | MaxLevelException e1) {
										if(e1.getMessage().equalsIgnoreCase("Building is in cool down. Wait for the next turn ")) {
											InCoolDownWindow inCoolDownWindow = new InCoolDownWindow(buildingType);
											setVisible(false);
										}
										if(e1.getMessage().equalsIgnoreCase("Maximum level reached!!")) {
											MaximumLevelWindow maximumLevelWindow = new MaximumLevelWindow(buildingType);
											setVisible(false);
											
										}
									} catch (NotEnoughGoldException e1) {
										NotEnoughGoldWindow w = new NotEnoughGoldWindow();
										setVisible(false);
									}
									
							  }
								else {
									MaximumLevelWindow maximumLevelWindow = new MaximumLevelWindow(buildingType);
								}
						}
					}
						if(b instanceof Stable) {
							if(("Stable").equalsIgnoreCase(buildingType)) {
								if(!maximumLevelReached) {	
									try {
										game.getPlayer().upgradeBuilding(b);
										currRecruitmentCost = b.getRecruitmentCost();
										graphics.getWorldMapEssentialsPanel().refresh();
										graphics.getControlledEssentialsPanel().refresh();
										this.buildingLevel = b.getLevel();
										if(b.getLevel() < 3) {
											level.setText("Level: " + b.getLevel() + "");
											upgrade.setText("Upgrade to Level " + (b.getLevel()+1) + " for " + b.getUpgradeCost());
											recruitUnit.setText("Recruit " + "Stable " + "for " + currRecruitmentCost);
											setVisible(false);
										}
										else if(b.getLevel() == 3) {
											currRecruitmentCost = b.getRecruitmentCost();
											recruitUnit.setText("Recruit " + "Cavalry " + "for " + currRecruitmentCost);
											if(b.getCurrentRecruit() >= b.getMaxRecruit()) {
												recruitUnit.setText("Maximum units Recruited!");
											}
											maximumLevelReached = true;
											level.setText("Level: " + 3 + "");
											upgrade.setVisible(false);
											remove(upgrade);
											setVisible(false);
											setSize(400,130);
											setTitle("Stable Has Reached Maximum Level!");
										}
										SuccessfullUpgradeWindow successfullUpgradeWindow = new SuccessfullUpgradeWindow(buildingType);
									} 
									catch (BuildingInCoolDownException | MaxLevelException e1) {
										if(e1.getMessage().equalsIgnoreCase("Building is in cool down. Wait for the next turn ")) {
											InCoolDownWindow inCoolDownWindow = new InCoolDownWindow(buildingType);
											setVisible(false);
										}
										if(e1.getMessage().equalsIgnoreCase("Maximum level reached!!")) {
											MaximumLevelWindow maximumLevelWindow = new MaximumLevelWindow(buildingType);
											maximumLevelReached = true;
											setVisible(false);
										}
									} catch (NotEnoughGoldException e1) {
										NotEnoughGoldWindow w = new NotEnoughGoldWindow();
										setVisible(false);
									}
							  }
								else{
									MaximumLevelWindow maximumLevelWindow = new MaximumLevelWindow(buildingType);
								}
							}
						}
						if(b instanceof Barracks) {
							if(("Barracks").equalsIgnoreCase(buildingType)) {
								if(!maximumLevelReached) {	
									try {
										game.getPlayer().upgradeBuilding(b);
										currRecruitmentCost = b.getRecruitmentCost();
										graphics.getWorldMapEssentialsPanel().refresh();
										graphics.getControlledEssentialsPanel().refresh();
										this.buildingLevel = b.getLevel();
										if(b.getLevel() < 3) {
											level.setText("Level: " + b.getLevel() + "");
											upgrade.setText("Upgrade to Level " + (b.getLevel()+1) + " for " + b.getUpgradeCost());
											recruitUnit.setText("Recruit " + "Infantry " + "for " + currRecruitmentCost);
											setVisible(false);
										}
										else if(b.getLevel() == 3) {
											currRecruitmentCost = b.getRecruitmentCost();
											recruitUnit.setText("Recruit " + "Infantry " + "for " + currRecruitmentCost);
											if(b.getCurrentRecruit() >= b.getMaxRecruit()) {
												recruitUnit.setText("Maximum units Recruited!");
											}
											maximumLevelReached = true;
											level.setText("Level: " + 3 + "");
											upgrade.setVisible(false);
											remove(upgrade);
											setVisible(false);
											setSize(400,130);
											setTitle("Barracks Has Reached Maximum Level!");
										}
										SuccessfullUpgradeWindow successfullUpgradeWindow = new SuccessfullUpgradeWindow(buildingType);
									} 
									catch (BuildingInCoolDownException | MaxLevelException e1) {
										if(e1.getMessage().equalsIgnoreCase("Building is in cool down. Wait for the next turn ")) {
											InCoolDownWindow inCoolDownWindow = new InCoolDownWindow(buildingType);
											setVisible(false);
										}
										if(e1.getMessage().equalsIgnoreCase("Maximum level reached!!")) {
											MaximumLevelWindow maximumLevelWindow = new MaximumLevelWindow(buildingType);
											maximumLevelReached = true;
											setVisible(false);
										}
									} catch (NotEnoughGoldException e1) {
										NotEnoughGoldWindow w = new NotEnoughGoldWindow();
										setVisible(false);
									}
							  }
								else{
									MaximumLevelWindow maximumLevelWindow = new MaximumLevelWindow(buildingType);
								}
							}
						}
					}
				}
			}
		}
	}
	public int getBuildingLevel() {
		return buildingLevel;
	}

}
