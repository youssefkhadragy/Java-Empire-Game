package view;

import engine.City;
import engine.Game;
import engine.Player;
import exceptions.NotEnoughGoldException;
import units.Army;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

public class Graphics extends JFrame implements ActionListener{
	private Game game;
	private Player player;
	private String playerName;
	private String cityName;
	private ArrayList<ControlledCityWindow> controlledCitiesWindows;
	private ArrayList<HostileCityWindow> hostileCitiesWindows;
	private EnterNameWindow enterNameWindow;
	private StartGameWindow startGameWindow;
	private WorldMapWindow  worldMapWindow;
	private ChooseCityWindow chooseCityWindow;
	private boolean isStartWindowDone = false;
	private boolean isEnterNameWindowDone = false;
	private EssentialsPanel controlledEssentialsPanel;
	private EssentialsPanel worldMapEssentialsPanel;
	private HashMap<JButton, Army> controlledArmies = new HashMap<JButton, Army>();
	private HashMap<JButton, Army> marchingArmies = new HashMap<JButton, Army>();
	private HashMap<JButton, Army> idleArmies = new HashMap<JButton, Army>();
	private HashMap<JButton, Army> chooseArmyToAttackButtons = new HashMap<JButton, Army>();
	private HashMap<JButton, Army> besiegingArmies = new HashMap<JButton, Army>();
	private ArrayList<String> targets = new ArrayList<String>();
	private ChooseArmyToAttackWindow chooseArmyToAttackWindowSparta;
	private ChooseArmyToAttackWindow chooseArmyToAttackWindowRome;
	private ChooseArmyToAttackWindow chooseArmyToAttackWindowCairo;
	private LaySiegeOrAttackWindow laySiegeOrAttackWindowCairo = null;
	private LaySiegeOrAttackWindow laySiegeOrAttackWindowRome = null;
	private LaySiegeOrAttackWindow laySiegeOrAttackWindowSparta = null;
	private ArrayList<LaySiegeOrAttackWindow> laySiegeWindows = new  ArrayList<LaySiegeOrAttackWindow>();
	
	
	private JPanel idleArmiesPanel;
	private JPanel marchingArmiesPanel;
	
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public LaySiegeOrAttackWindow getLaySiegeOrAttackWindowCairo() {
		return laySiegeOrAttackWindowCairo;
	}
	public void setLaySiegeOrAttackWindowCairo(LaySiegeOrAttackWindow laySiegeOrAttackWindowCairo) {
		this.laySiegeOrAttackWindowCairo = laySiegeOrAttackWindowCairo;
	}
	public LaySiegeOrAttackWindow getLaySiegeOrAttackWindowRome() {
		return laySiegeOrAttackWindowRome;
	}
	public void setLaySiegeOrAttackWindowRome(LaySiegeOrAttackWindow laySiegeOrAttackWindowRome) {
		this.laySiegeOrAttackWindowRome = laySiegeOrAttackWindowRome;
	}
	public LaySiegeOrAttackWindow getLaySiegeOrAttackWindowSparta() {
		return laySiegeOrAttackWindowSparta;
	}
	public void setLaySiegeOrAttackWindowSparta(LaySiegeOrAttackWindow laySiegeOrAttackWindowSparta) {
		this.laySiegeOrAttackWindowSparta = laySiegeOrAttackWindowSparta;
	}
	
	public ArrayList<LaySiegeOrAttackWindow> getLaySiegeWindows() {
		return laySiegeWindows;
	}
	public void setLaySiegeWindows(ArrayList<LaySiegeOrAttackWindow> laySiegeWindows) {
		this.laySiegeWindows = laySiegeWindows;
	}
	public HashMap<JButton, Army> getBesiegingArmies() {
		return besiegingArmies;
	}
	public void setBesiegingArmies(HashMap<JButton, Army> besiegingArmies) {
		this.besiegingArmies = besiegingArmies;
	}
	
	public HashMap<JButton, Army> getChooseArmyToAttackButtons() {
		return chooseArmyToAttackButtons;
	}
	public void setChooseArmyToAttackButtons(HashMap<JButton, Army> chooseArmyToAttackButtons) {
		this.chooseArmyToAttackButtons = chooseArmyToAttackButtons;
	}

	public HashMap<JButton, Army> getControlledArmies() {
		return controlledArmies;
	}
	public void setControlledArmies(HashMap<JButton, Army> controlledArmies) {
		this.controlledArmies = controlledArmies;
	}
	public HashMap getMarchingArmies() {
		return marchingArmies;
	}
	public void setMarchingArmies(HashMap marchingArmies) {
		this.marchingArmies = marchingArmies;
	}
	public HashMap getIdleArmies() {
		return idleArmies;
	}
	public void setIdleArmies(HashMap idleArmies) {
		this.idleArmies = idleArmies;
	}
	public void startGame() {
		startGameWindow = new StartGameWindow(this);
	}
	public EssentialsPanel getWorldMapEssentialsPanel() {
		return worldMapEssentialsPanel;
	}
	public EssentialsPanel getControlledEssentialsPanel() {
		return controlledEssentialsPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start Game")) {
			enterNameWindow = new EnterNameWindow(this);
			enterNameWindow.setVisible(true);
		}
		if (e.getActionCommand().equals("Quit")) {
			System.exit(0);
		}
		if (e.getActionCommand().equals("Confirm")) {
			if (enterNameWindow.getNameField().getText() == " ") {
				playerName = "Stranger";
				chooseCityWindow = new ChooseCityWindow(this);
				enterNameWindow.setVisible(false);
			} else {
				playerName = enterNameWindow.getNameField().getText();
				chooseCityWindow = new ChooseCityWindow(this);
				enterNameWindow.setVisible(false);
			}
		}
		if (e.getActionCommand().equals("C")) {
			cityName = "Cairo";
			chooseCityWindow.setVisible(false);
			startGameWindow.dispose();
			try {
				game = new Game(playerName, cityName);
				worldMapEssentialsPanel = new EssentialsPanel(this, playerName, cityName, game);
				controlledEssentialsPanel =  new EssentialsPanel(this, playerName, cityName, game);
				worldMapWindow = new WorldMapWindow(this, idleArmiesPanel, marchingArmiesPanel, playerName, cityName,this.worldMapEssentialsPanel, game);
			} catch (IOException e1) {
				System.out.println("Failed to start game!");
			}
		}
		if (e.getActionCommand().equals("R")) {
			cityName = "Rome";
			chooseCityWindow.setVisible(false);
			startGameWindow.dispose();
			try {
				game = new Game(playerName, cityName);
				worldMapEssentialsPanel = new EssentialsPanel(this, playerName, cityName, game);
				controlledEssentialsPanel =  new EssentialsPanel(this, playerName, cityName, game);
				worldMapWindow = new WorldMapWindow(this, idleArmiesPanel, marchingArmiesPanel, playerName, cityName, this.worldMapEssentialsPanel, game);
			} catch (IOException e1) {
				System.out.println("Failed to start game!");
			}
		}
		if (e.getActionCommand().equals("S")) {
			cityName = "Sparta";
			chooseCityWindow.setVisible(false);
			startGameWindow.dispose();
			try {
				
				game = new Game(playerName, cityName);
				worldMapEssentialsPanel = new EssentialsPanel(this, playerName, cityName, game);
				controlledEssentialsPanel =  new EssentialsPanel(this, playerName, cityName, game);
				worldMapWindow = new WorldMapWindow(this, idleArmiesPanel, marchingArmiesPanel, playerName, cityName, this.worldMapEssentialsPanel, game);
			} catch (IOException e1) {
				System.out.println("Failed to start game!");
			}
		}
		if(e.getActionCommand().equals("Cairo")) {
			boolean isControlled = false;
			boolean isHostileExists = false;
			for(City x : game.getPlayer().getControlledCities()) {
				if(x.getName().equals("Cairo")) {
					if(controlledCitiesWindows == null) {
					 ControlledCityWindow controlledCityWindow = new ControlledCityWindow(this, "Cairo", controlledEssentialsPanel,worldMapWindow, game);
					 controlledCitiesWindows = new ArrayList<ControlledCityWindow>();
					 controlledCitiesWindows.add(controlledCityWindow);
					 isControlled = true;
					}
					else {
						for(ControlledCityWindow w : controlledCitiesWindows) {
							if(w.getCurrCity().equals("Cairo")) {
								isControlled = true;
								worldMapEssentialsPanel.refresh();
							}
							if(isControlled) {
								w.setState(JFrame.NORMAL);
								w.setVisible(true);
								}
						}
				}
					if(!isControlled){
						ControlledCityWindow controlledCityWindow = new ControlledCityWindow(this, "Cairo", controlledEssentialsPanel,worldMapWindow, game);
						controlledCitiesWindows = new ArrayList<ControlledCityWindow>();
						controlledCitiesWindows.add(controlledCityWindow);
					}
				}
				else {
					if(hostileCitiesWindows == null) {
						HostileCityWindow hostileCityWindow= new HostileCityWindow(this, "Cairo");
						hostileCitiesWindows = new ArrayList<HostileCityWindow>();
						hostileCitiesWindows.add(hostileCityWindow);
					}
					else {
						for(HostileCityWindow w : hostileCitiesWindows) {
							if(w.getCityName().equals("Cairo")) {
								isHostileExists = true;
								w.setState(JFrame.NORMAL);
								w.setVisible(true);
							}
						}
						if(!isHostileExists) {
							HostileCityWindow hostileCityWindow= new HostileCityWindow(this, "Cairo");
							hostileCitiesWindows = new ArrayList<HostileCityWindow>();
							hostileCitiesWindows.add(hostileCityWindow);
						}
					}
				}
			}
		}
		if(e.getActionCommand().equals("Sparta")) {
			boolean isControlled = false;
			boolean isHostileExists = false;
			for(City x : game.getPlayer().getControlledCities()) {
				if(x.getName().equals("Sparta")) {
					if(controlledCitiesWindows == null) {
					 ControlledCityWindow controlledCityWindow = new ControlledCityWindow(this, "Sparta", controlledEssentialsPanel, worldMapWindow, game);
					 controlledCitiesWindows = new ArrayList<ControlledCityWindow>();
					 controlledCitiesWindows.add(controlledCityWindow);
					 isControlled = true;
					}
					else {
						for(ControlledCityWindow w : controlledCitiesWindows) {
							if(w.getCurrCity().equals("Sparta")) {
								isControlled = true;
							}
							if(isControlled) {
								w.setState(JFrame.NORMAL);
								w.setVisible(true);
								}
						}
				}
					if(!isControlled){
						ControlledCityWindow controlledCityWindow = new ControlledCityWindow(this, "Sparta", controlledEssentialsPanel, worldMapWindow, game);
						controlledCitiesWindows = new ArrayList<ControlledCityWindow>();
						controlledCitiesWindows.add(controlledCityWindow);
					}
				}
				else {
					if(hostileCitiesWindows == null) {
						HostileCityWindow hostileCityWindow= new HostileCityWindow(this, "Sparta");
						hostileCitiesWindows = new ArrayList<HostileCityWindow>();
						hostileCitiesWindows.add(hostileCityWindow);
						
					}
					else {
						for(HostileCityWindow w : hostileCitiesWindows) {
							if(w.getCityName().equals("Sparta")) {
								isHostileExists = true;
								w.setState(JFrame.NORMAL);
								w.setVisible(true);
							}
						}
						if(!isHostileExists) {
							HostileCityWindow hostileCityWindow= new HostileCityWindow(this, "Sparta");
							hostileCitiesWindows = new ArrayList<HostileCityWindow>();
							hostileCitiesWindows.add(hostileCityWindow);
						}
					}
				}
			}
		}
		if(e.getActionCommand().equals("Rome")) {
			boolean isControlled = false;
			boolean isHostileExists = false;
			for(City x : game.getPlayer().getControlledCities()) {
				if(x.getName().equals("Rome")) {
					if(controlledCitiesWindows == null) {
					 ControlledCityWindow controlledCityWindow = new ControlledCityWindow(this, "Rome",controlledEssentialsPanel,worldMapWindow, game);
					 controlledCitiesWindows = new ArrayList<ControlledCityWindow>();
					 controlledCitiesWindows.add(controlledCityWindow);
					 isControlled = true;
					}
					else {
						for(ControlledCityWindow w : controlledCitiesWindows) {
							if(w.getCurrCity().equals("Rome")) {
								isControlled = true;
							}
							if(isControlled) {
								w.setState(JFrame.NORMAL);
								w.setVisible(true);
								}
						}
				}
					if(!isControlled){
						ControlledCityWindow controlledCityWindow = new ControlledCityWindow(this, "Rome", controlledEssentialsPanel,worldMapWindow, game);
						controlledCitiesWindows = new ArrayList<ControlledCityWindow>();
						controlledCitiesWindows.add(controlledCityWindow);
					}
				}
				else {
					if(hostileCitiesWindows == null) {
						HostileCityWindow hostileCityWindow= new HostileCityWindow(this, "Rome");
						hostileCitiesWindows = new ArrayList<HostileCityWindow>();
						hostileCitiesWindows.add(hostileCityWindow);
						
					}
					else {
						for(HostileCityWindow w : hostileCitiesWindows) {
							if(w.getCityName().equals("Rome")) {
								isHostileExists = true;
								w.setState(JFrame.NORMAL);
								w.setVisible(true);
							}
						}
						if(!isHostileExists) {
							HostileCityWindow hostileCityWindow= new HostileCityWindow(this, "Rome");
							hostileCitiesWindows = new ArrayList<HostileCityWindow>();
							hostileCitiesWindows.add(hostileCityWindow);
						}
					}
				}
			}
		}
		if(e.getActionCommand().equals("End Turn")) {
			game.endTurn();
			worldMapWindow.refreshArmiesPanel();
			if(game.isGameOver()) {
				worldMapWindow.getEssentialsPanel().getEndTurn().setVisible(false);
				if(game.getPlayer().getControlledCities().size() == game.getAvailableCities().size()) {
					GameOverWindow g = new GameOverWindow("You Won!");
				}
				else {
					GameOverWindow g = new GameOverWindow("You Lost!");
				}
			}
			JButton tempButton = null;
			Army tempArmy;
			for(LaySiegeOrAttackWindow w : laySiegeWindows) {
				w.setVisible(true);
			}
			for(Army k : game.getPlayer().getControlledArmies()) {
				if(k.getDistancetoTarget() == 0) {
					//tempArmy = ((Army)marchingArmies.get(k));
					//tempButton = (JButton)k;
					//idleArmies.put(tempButton, tempArmy);
					if(k.getCurrentLocation().equalsIgnoreCase("Cairo")) {
						if(laySiegeOrAttackWindowCairo == null) {
								laySiegeOrAttackWindowCairo  = new LaySiegeOrAttackWindow(this, k, game, "Cairo");
								laySiegeWindows.add(laySiegeOrAttackWindowCairo);
								targets.remove("Cairo");
						}
						else {
							laySiegeOrAttackWindowCairo.setVisible(true);
						}
				   }
					if(k.getCurrentLocation() == "Sparta") {
						if(laySiegeOrAttackWindowSparta == null) {
								laySiegeOrAttackWindowSparta  = new LaySiegeOrAttackWindow(this, k, game, "Sparta");
								laySiegeWindows.add(laySiegeOrAttackWindowSparta);
								targets.remove("Sparta");
								
					}
						else {
							laySiegeOrAttackWindowSparta.setVisible(true);
						}
				   }
					if(k.getCurrentLocation().equalsIgnoreCase("Rome")) {
						if(laySiegeOrAttackWindowRome == null) {
								laySiegeOrAttackWindowRome  = new LaySiegeOrAttackWindow(this, k, game, "Rome");
								laySiegeWindows.add(laySiegeOrAttackWindowRome);
								targets.remove("Rome");
					}
						else {
							laySiegeOrAttackWindowRome.setVisible(true);
						}
				   }
				}	
			}
			/*if(tempButton != null) {
				marchingArmies.remove(tempButton);
			}*/
			worldMapWindow.refreshArmiesPanel();
			worldMapEssentialsPanel.refresh();
			controlledEssentialsPanel.refresh();
		}
		/*if(e.getActionCommand().equals("Sparta")) {
			for(City x : game.getPlayer().getControlledCities()) {
				if(x.getName().equals("Sparta")) {
					if(spartaControlledCityWindow == null) {
					spartaControlledCityWindow = new ControlledCityWindow("Sparta", game);
					}
					else {
						return;
					}
				}
				else {
					if(hostileCityWindow == null) {
					hostileCityWindow = new HostileCityWindow(this);
					}
					else {
						hostileCityWindow.setVisible(true);
					}
				}
			}
		}
		if(e.getActionCommand().equals("Rome")) {
			for(City x : game.getPlayer().getControlledCities()) {
				if(x.getName().equals("Rome")) {
					if(romeControlledCityWindow == null) {
					romeControlledCityWindow = new ControlledCityWindow("Rome", game);
					}
					else {
						return;
					}
				}
				else {
					if(hostileCityWindow == null) {
					hostileCityWindow = new HostileCityWindow(this);
					}
					else {
						hostileCityWindow.setVisible(true);
						
					}
				}
			}
		}*/
		/*if(e.getActionCommand().equals("Buy Farm")) {
			cairoControlledCityWindow.build(e.getActionCommand());
			cairoControlledCityWindow.getBuyFarm().setVisible(false);
		}
		if(e.getActionCommand().equals("Buy Market in Cairo")) {
			cairoControlledCityWindow.getBuyMarket().setVisible(false);
			cairoControlledCityWindow.build(e.getActionCommand());
		}
		if(e.getActionCommand().equals("Buy Archery Range in Cairo")) {
			cairoControlledCityWindow.getBuyArcheryRange().setVisible(false);
			cairoControlledCityWindow.build(e.getActionCommand());
		}
		if(e.getActionCommand().equals("Buy Stable in Cairo")) {
			cairoControlledCityWindow.getBuyStable().setVisible(false);
			cairoControlledCityWindow.build(e.getActionCommand());
		}
		if(e.getActionCommand().equals("Buy Barracks in Cairo")) {
			cairoControlledCityWindow.getBuyBarracks().setVisible(false);
			cairoControlledCityWindow.build(e.getActionCommand());
		}
		if(e.getActionCommand().equals("Buy Farm in Sparta")) {
			spartaControlledCityWindow.getBuyFarm().setVisible(false);
			spartaControlledCityWindow.build(e.getActionCommand());
		}
		if(e.getActionCommand().equals("Buy Market in Sparta")) {
			spartaControlledCityWindow.getBuyMarket().setVisible(false);
			spartaControlledCityWindow.build(e.getActionCommand());
		}
		if(e.getActionCommand().equals("Buy Archery Range in Sparta")) {
			spartaControlledCityWindow.getBuyArcheryRange().setVisible(false);
			spartaControlledCityWindow.build(e.getActionCommand());
		}
		if(e.getActionCommand().equals("Buy Stable in Sparta")) {
			spartaControlledCityWindow.getBuyStable().setVisible(false);
			spartaControlledCityWindow.build(e.getActionCommand());
		}
		if(e.getActionCommand().equals("Buy Barracks in Sparta")) {
			spartaControlledCityWindow.getBuyBarracks().setVisible(false);
			spartaControlledCityWindow.build(e.getActionCommand());
		}
		if(e.getActionCommand().equals("Buy Farm in Rome")) {
			romeControlledCityWindow.getBuyFarm().setVisible(false);
			romeControlledCityWindow.build(e.getActionCommand());
		}
		if(e.getActionCommand().equals("Buy Market in Rome")) {
			romeControlledCityWindow.getBuyMarket().setVisible(false);
			romeControlledCityWindow.build(e.getActionCommand());
		}
		if(e.getActionCommand().equals("Buy Archery Range in Rome")) {
			romeControlledCityWindow.getBuyArcheryRange().setVisible(false);
			romeControlledCityWindow.build(e.getActionCommand());
		}
		if(e.getActionCommand().equals("Buy Stable in Rome")) {
			romeControlledCityWindow.getBuyStable().setVisible(false);
			romeControlledCityWindow.build(e.getActionCommand());
		}
		if(e.getActionCommand().equals("Buy Barracks in Rome")) {
			romeControlledCityWindow.getBuyBarracks().setVisible(false);
			romeControlledCityWindow.build(e.getActionCommand());
		}*/
		if(e.getActionCommand().equals("Target City Sparta")) {
			if(game.getPlayer().getControlledArmies().size() != 0) {
				if(!targets.contains("Sparta")) {
					for(HostileCityWindow h : hostileCitiesWindows) {
						if(h.getCityName().equals("Sparta")) {
							h.setVisible(false);
							worldMapWindow.refreshArmiesPanel();
						}
					}
					targets.add("Sparta");
					if(chooseArmyToAttackWindowSparta == null) {
						chooseArmyToAttackWindowSparta = new ChooseArmyToAttackWindow(this, game, "Sparta");
						}
						else{
							chooseArmyToAttackWindowSparta.getContentPane().removeAll();
							for(Object b : chooseArmyToAttackButtons.keySet()) {
								chooseArmyToAttackWindowSparta.add((JButton)b);
							}
						}
				}
				else {
					for(HostileCityWindow h : hostileCitiesWindows) {
						if(h.getCityName().equals("Sparta")) {
							h.setVisible(false);
						}
					}
					CityIsAlreadyTargetWindow a = new CityIsAlreadyTargetWindow();
				}
		    }
			else {
				NoControlledArmiesWindow w = new NoControlledArmiesWindow();
			}
		}
		if(e.getActionCommand().equals("Target City Rome")) {
			if(game.getPlayer().getControlledArmies().size() != 0) {
				if(!targets.contains("Rome")) {
					for(HostileCityWindow h : hostileCitiesWindows) {
						if(h.getCityName().equals("Rome")) {
							h.setVisible(false);
							worldMapWindow.refreshArmiesPanel();
						}
					}
					targets.add("Rome");
					if(chooseArmyToAttackWindowRome == null) {
						chooseArmyToAttackWindowRome = new ChooseArmyToAttackWindow(this, game, "Rome");
					}
					else{
						chooseArmyToAttackWindowRome.getContentPane().removeAll();
						for(Object b : chooseArmyToAttackButtons.keySet()) {
							chooseArmyToAttackWindowRome.add((JButton)b);
						}
					}
				}
				else {
					for(HostileCityWindow h : hostileCitiesWindows) {
						if(h.getCityName().equals("Rome")) {
							h.setVisible(false);
							worldMapWindow.refreshArmiesPanel();
						}
					}
					CityIsAlreadyTargetWindow a = new CityIsAlreadyTargetWindow();
				}
			}
			else {
				NoControlledArmiesWindow w = new NoControlledArmiesWindow();
			}
		}
		if(e.getActionCommand().equals("Target City Cairo")) {
			if(game.getPlayer().getControlledArmies().size() != 0) {
				if(!targets.contains("Cairo")) {
					for(HostileCityWindow h : hostileCitiesWindows) {
						if(h.getCityName().equals("Cairo")) {
							h.setVisible(false);
						}
					}
					targets.add("Cairo");
					if(chooseArmyToAttackWindowCairo == null) {
						chooseArmyToAttackWindowCairo = new ChooseArmyToAttackWindow(this, game, "Cairo");
					}
					else{
						chooseArmyToAttackWindowCairo.getContentPane().removeAll();
						for(Object b : chooseArmyToAttackButtons.keySet()) {
							chooseArmyToAttackWindowCairo.add((JButton)b);
						}
					}
				}
				else {
					for(HostileCityWindow h : hostileCitiesWindows) {
						if(h.getCityName().equals("Cairo")) {
							h.setVisible(false);
						}
					}
					CityIsAlreadyTargetWindow a = new CityIsAlreadyTargetWindow();
				}
			}
			else {
				NoControlledArmiesWindow w = new NoControlledArmiesWindow();
			}
		}
	}

	public WorldMapWindow getWorldMapWindow() {
		return worldMapWindow;
	}
	public void setWorldMapWindow(WorldMapWindow worldMapWindow) {
		this.worldMapWindow = worldMapWindow;
	}
	public ArrayList<ControlledCityWindow> getControlledCitiesWindows() {
		return controlledCitiesWindows;
	}
	public void setControlledCitiesWindows(ArrayList<ControlledCityWindow> controlledCitiesWindows) {
		this.controlledCitiesWindows = controlledCitiesWindows;
	}
	public ArrayList<HostileCityWindow> getHostileCitiesWindows() {
		return hostileCitiesWindows;
	}
	public void setHostileCitiesWindows(ArrayList<HostileCityWindow> hostileCitiesWindows) {
		this.hostileCitiesWindows = hostileCitiesWindows;
	}
	public ArrayList<String> getTargets() {
		return targets;
	}
	public void setTargets(ArrayList<String> targets) {
		this.targets = targets;
	}
	public static void main(String[] args) {
		Graphics game = new Graphics();
		game.startGame();
	}
}
