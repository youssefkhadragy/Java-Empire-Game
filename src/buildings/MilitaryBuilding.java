package buildings;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxRecruitedException;
import units.Unit;

public abstract class MilitaryBuilding extends Building {
	private int recruitmentCost;
	private  int maxRecruit;
	private int currentRecruit;
	private boolean overDrive;

	public MilitaryBuilding(int cost, int upgradeCost,int recruitmentCost) {
		super(cost, upgradeCost);
		this.recruitmentCost=recruitmentCost;
		 maxRecruit=3;
		
	}
	public boolean isOverDrive() {
		return overDrive;
	}
	public void setOverDrive(boolean overDrive) {
		this.overDrive = overDrive;
	}
	public abstract Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException;
	public int getRecruitmentCost() {
		return recruitmentCost;
	}
	public void setRecruitmentCost(int recruitmentCost) {
		this.recruitmentCost = recruitmentCost;
	}
	public int getMaxRecruit() {
		return maxRecruit;
	}
	public void setMaxRecruit(int maxRecruit) {
		this.maxRecruit = maxRecruit;
	}
	public int getCurrentRecruit() {
		return currentRecruit;
	}
	public void setCurrentRecruit(int currentRecruit) {
		this.currentRecruit = currentRecruit;
	}

}
