package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.junit.Test;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import engine.City;
import engine.Distance;
import engine.Game;
import engine.Player;
import exceptions.FriendlyFireException;
import exceptions.MaxCapacityException;
import exceptions.BuildingInCoolDownException;
import exceptions.EmpireException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.MaxLevelException;

import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;

import java.lang.reflect.Method;

import org.junit.Test;

import buildings.MilitaryBuilding;
import units.Unit;

public class Q2V3 {
	String militaryBuildingPath = "buildings.MilitaryBuilding";

	@Test(timeout = 100)
	public void testSetMaxRecruitMethodExists() {
		Method[] methods = MilitaryBuilding.class.getDeclaredMethods();
		
		assertTrue("Class MilitaryBuilding should contain a method named setMaxRecruit.", containsMethodName(methods, "setMaxRecruit"));
	}
	
	@Test(timeout = 100)
	public void testOverDriveSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(militaryBuildingPath), "setOverDrive", boolean.class, true);
		

		Constructor<?> constructor = Class.forName("buildings.Barracks").getConstructor();
		
		Object b = constructor.newInstance();
		testSetterLogic(b, "overDrive", true, true, boolean.class);
		testSetterLogic(b, "overDrive", false, false, boolean.class);
	}
	
	@Test(timeout = 100)
	public void testOverDriveGetter() throws Exception {
		class TestBuilding extends MilitaryBuilding{

			public TestBuilding(int cost, int upgradeCost) {
				super(cost, upgradeCost, 200);
				// TODO Auto-generated constructor stub
			}

			@Override
			public Unit recruit() throws BuildingInCoolDownException,
					MaxRecruitedException {
				// TODO Auto-generated method stub
				return null;
			}
			
		}
		testGetterMethodExistsInClass(Class.forName(militaryBuildingPath), "isOverDrive", boolean.class, true);
		
		TestBuilding t = new TestBuilding(200, 100);
		 //t.getClass();
//		Constructor<?> constructor = Class.forName("tests.TestBuilding").getConstructor(int.class, int.class);
//		int randomCost = (int) (Math.random() * 1500) + 1000;
//		int randomUpgradeCost = (int) (Math.random() * 1000) + 500;
		//Object b = constructor.newInstance(randomCost, randomUpgradeCost,200);
		
		testGetterLogic(t, "overDrive", false);
		testGetterLogic(t, "overDrive", true);
	}
	@Test(timeout = 3000)
	public void testRecruitUnitCavalryPlayerOriginalCityMaxRecruitedException() throws Exception {
		try {
			Game game = createGameHelper("Cairo");
			game.getPlayer().setTreasury(5000);
			playerRecruitUnitExceptionHelper(game, "Cavalry", "Cairo", false,false, 3);
			fail("MaxRecruitedException should be thrown.");
		} catch (MaxRecruitedException e) {

		}
	}
	@Test(timeout = 3000)
	public void testRecruitUnitCavalryPlayerOriginalCityMaxRecruitedExceptionWithOverDrive() throws Exception {
		try {
			Game game = createGameHelper("Cairo");
			game.getPlayer().setTreasury(5000);
			playerRecruitUnitExceptionHelper(game, "Cavalry", "Cairo", false,true, 6);
			fail("MaxRecruitedException should be thrown.");
		} catch (MaxRecruitedException e) {

		}
	}
	
	@Test(timeout = 3000)
	public void testRecruitUnitArcherPlayerOriginalCityWithEnoughGold() throws Exception {
		Game game = createGameHelper("Cairo");
		game.getPlayer().setTreasury(5000);
		playerRecruitUnitWithEnoughGoldHelper(game, "Archer", "Cairo");
		game.getPlayer().setTreasury(5000);
		playerRecruitUnitWithEnoughGoldHelper(game, "Infantry", "Cairo");
		game.getPlayer().setTreasury(5000);
		playerRecruitUnitWithEnoughGoldHelper(game, "Cavalry", "Cairo");
	}
	
	@Test(timeout = 3000)
	public void testRecruitUnitWithOverDriveLogic1() throws Exception {
		Game game = createGameHelper("Cairo");
		game.getPlayer().setTreasury(5000);
		playerRecruitUnitWithWithOverDriveHelper1(game, "Archer", "Cairo");
		game.getPlayer().setTreasury(5000);
		playerRecruitUnitWithWithOverDriveHelper1(game, "Infantry", "Cairo");
		game.getPlayer().setTreasury(5000);
		playerRecruitUnitWithWithOverDriveHelper1(game, "Cavalry", "Cairo");
	}
	
	@Test(timeout = 3000)
	public void testRecruitUnitWithOverDriveLogic2() throws Exception {
		Game game = createGameHelper("Cairo");
		game.getPlayer().setTreasury(5000);
		playerRecruitUnitWithWithOverDriveHelper2(game, "Archer", "Cairo");
		game.getPlayer().setTreasury(5000);
		playerRecruitUnitWithWithOverDriveHelper2(game, "Infantry", "Cairo");
		game.getPlayer().setTreasury(5000);
		playerRecruitUnitWithWithOverDriveHelper2(game, "Cavalry", "Cairo");
	}
	
	@Test(timeout = 3000)
	public void testRecruitUnitWithOverDriveLogic3() throws Exception {
		Game game = createGameHelper("Cairo");
		game.getPlayer().setTreasury(5000);
		playerRecruitUnitWithWithOverDriveHelper3(game, "Archer", "Cairo");
		game.getPlayer().setTreasury(5000);
		playerRecruitUnitWithWithOverDriveHelper3(game, "Infantry", "Cairo");
		game.getPlayer().setTreasury(5000);
		playerRecruitUnitWithWithOverDriveHelper3(game, "Cavalry", "Cairo");
	}
	@Test(timeout = 3000)
	public void testRecruitUnitWithOverDriveLogic4() throws Exception {
		Game game = createGameHelper("Cairo");
		game.getPlayer().setTreasury(5000);
		MilitaryBuilding m = null;
		City c = null;
		int initialArmySize = 0;
		m = new ArcheryRange();
		int old = m.getMaxRecruit();
		c = insertMilitaryBuildingHelper(game, "Cairo", m);
		initialArmySize = c.getDefendingArmy().getUnits().size();
		m.setCoolDown(false);
		m.setCurrentRecruit(0);
		m.setOverDrive(true);
		game.getPlayer().recruitUnit("Archer", "Cairo");
		assertEquals("The current recruit should be returned to its original value",old, m.getMaxRecruit());
		
	}
//	@Test(timeout = 100)
//	public void testUpgradeCoolDownGetter() throws Exception {
//
//		testGetterMethodExistsInClass(Class.forName(militaryBuildingPath), "isOverDrive", boolean.class, true);
//		String[] subClasses = { economicBuildingPath, farmPath, marketPath, militaryBuildingPath, archeryRangePath,
//				barracksPath, stablePath };
//		testGetterAbsentInSubclasses("coolDown", subClasses, boolean.class);
//		Constructor<?> constructor = Class.forName(buildingPath).getConstructor(int.class, int.class);
//		int randomCost = (int) (Math.random() * 1500) + 1000;
//		int randomUpgradeCost = (int) (Math.random() * 1000) + 500;
//		Object b = constructor.newInstance(randomCost, randomUpgradeCost);
//		testGetterLogic(b, "coolDown", true);
//		testGetterLogic(b, "coolDown", false);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////
	
	private void playerRecruitUnitWithEnoughGoldHelper(Game game, String type, String cityName) throws Exception {
		
		MilitaryBuilding m = null;
		City c = null;
		int initialArmySize = 0;
		switch (type) {
		case "Archer":
			m = new ArcheryRange();
			c = insertMilitaryBuildingHelper(game, cityName, m);
			initialArmySize = c.getDefendingArmy().getUnits().size();
			m.setCoolDown(false);
			m.setCurrentRecruit(0);
			game.getPlayer().recruitUnit("Archer", cityName);
			unitAddedAndTreasuryDecrementedHelper(game, c, initialArmySize + 1, 5000, m);
			assertTrue("A unit of type Archer should be added to the defending army.",
					c.getDefendingArmy().getUnits().get(initialArmySize) instanceof Archer);

			break;
		case "Cavalry":
			m = new Stable();
			c = insertMilitaryBuildingHelper(game, cityName, m);
			initialArmySize = c.getDefendingArmy().getUnits().size();
			m.setCoolDown(false);
			m.setCurrentRecruit(0);
			game.getPlayer().recruitUnit("Cavalry", cityName);
			unitAddedAndTreasuryDecrementedHelper(game, c, initialArmySize + 1, 5000, m);
			assertTrue("A unit of type Cavalry should be added to the defending army.",
					c.getDefendingArmy().getUnits().get(initialArmySize) instanceof Cavalry);
			break;
		case "Infantry":
			m = new Barracks();
			c = insertMilitaryBuildingHelper(game, cityName, m);
			initialArmySize = c.getDefendingArmy().getUnits().size();
			m.setCoolDown(false);
			m.setCurrentRecruit(0);
			game.getPlayer().recruitUnit("Infantry", cityName);
			unitAddedAndTreasuryDecrementedHelper(game, c, initialArmySize + 1, 5000, m);
			assertTrue("A unit of type Cavalry should be added to the defending army.",
					c.getDefendingArmy().getUnits().get(initialArmySize) instanceof Infantry);
			break;
		default:
			fail("recruitUnit can only be used on types: Archer, Cavalry or Infantry");
		}

	}
	private void unitAddedAndTreasuryDecrementedHelper(Game game, City c, int count, int treasury, MilitaryBuilding m) {
		assertEquals("A unit should be added to the defending army.", count, c.getDefendingArmy().getUnits().size());
		assertEquals("The recruited unit should have the defending army of the city as its parent army.", c.getDefendingArmy(),c.getDefendingArmy().getUnits().get(count-1).getParentArmy());
		assertEquals("Recruiting Cost was not deceremented correctly.", (int) (treasury - m.getRecruitmentCost()),
				(int) game.getPlayer().getTreasury());
	}
	
private void playerRecruitUnitWithWithOverDriveHelper1(Game game, String type, String cityName) throws Exception {
		
		MilitaryBuilding m = null;
		City c = null;
		int initialArmySize = 0;
		switch (type) {
		case "Archer":
			m = new ArcheryRange();
			c = insertMilitaryBuildingHelper(game, cityName, m);
			initialArmySize = c.getDefendingArmy().getUnits().size();
			m.setCoolDown(false);
			m.setCurrentRecruit(0);
			m.setOverDrive(true);
			game.getPlayer().recruitUnit("Archer", cityName);
			unitAddedAndTreasuryUpdatedWithOverDriveHelper(game, c, initialArmySize + 2, 5000, m);
			assertTrue("2 units of type Archer should be added to the defending army.",
					c.getDefendingArmy().getUnits().get(initialArmySize) instanceof Archer);

			break;
		case "Cavalry":
			m = new Stable();
			c = insertMilitaryBuildingHelper(game, cityName, m);
			initialArmySize = c.getDefendingArmy().getUnits().size();
			m.setCoolDown(false);
			m.setCurrentRecruit(0);
			m.setOverDrive(true);

			game.getPlayer().recruitUnit("Cavalry", cityName);
			unitAddedAndTreasuryUpdatedWithOverDriveHelper(game, c, initialArmySize + 2, 5000, m);
			assertTrue("2 units of type Cavalry should be added to the defending army.",
					c.getDefendingArmy().getUnits().get(initialArmySize) instanceof Cavalry);
			break;
		case "Infantry":
			m = new Barracks();
			c = insertMilitaryBuildingHelper(game, cityName, m);
			initialArmySize = c.getDefendingArmy().getUnits().size();
			m.setCoolDown(false);
			m.setCurrentRecruit(0);
			m.setOverDrive(true);

			game.getPlayer().recruitUnit("Infantry", cityName);
			unitAddedAndTreasuryUpdatedWithOverDriveHelper(game, c, initialArmySize + 2, 5000, m);
			assertTrue("2 units of type Cavalry should be added to the defending army.",
					c.getDefendingArmy().getUnits().get(initialArmySize) instanceof Infantry);
			break;
		default:
			fail("recruitUnit can only be used on types: Archer, Cavalry or Infantry");
		}

	}

private void playerRecruitUnitWithWithOverDriveHelper2(Game game, String type, String cityName) throws Exception {
	
	MilitaryBuilding m = null;
	City c = null;
	int initialArmySize = 0;
	switch (type) {
	case "Archer":
		m = new ArcheryRange();
		c = insertMilitaryBuildingHelper(game, cityName, m);
		initialArmySize = c.getDefendingArmy().getUnits().size();
		m.setCoolDown(false);
		m.setCurrentRecruit(0);
		m.setOverDrive(true);
		game.getPlayer().recruitUnit("Archer", cityName);
		unitAddedAndParentArmyUpdatedWithOverDriveHelper(game, c, initialArmySize + 2, 5000, m);
		assertTrue("2 units of type Archer should be added to the defending army.",
				c.getDefendingArmy().getUnits().get(initialArmySize) instanceof Archer);

		break;
	case "Cavalry":
		m = new Stable();
		c = insertMilitaryBuildingHelper(game, cityName, m);
		initialArmySize = c.getDefendingArmy().getUnits().size();
		m.setCoolDown(false);
		m.setCurrentRecruit(0);
		m.setOverDrive(true);

		game.getPlayer().recruitUnit("Cavalry", cityName);
		unitAddedAndParentArmyUpdatedWithOverDriveHelper(game, c, initialArmySize + 2, 5000, m);
		assertTrue("2 units of type Cavalry should be added to the defending army.",
				c.getDefendingArmy().getUnits().get(initialArmySize) instanceof Cavalry);
		break;
	case "Infantry":
		m = new Barracks();
		c = insertMilitaryBuildingHelper(game, cityName, m);
		initialArmySize = c.getDefendingArmy().getUnits().size();
		m.setCoolDown(false);
		m.setCurrentRecruit(0);
		m.setOverDrive(true);

		game.getPlayer().recruitUnit("Infantry", cityName);
		unitAddedAndParentArmyUpdatedWithOverDriveHelper(game, c, initialArmySize + 2, 5000, m);
		assertTrue("2 units of type Cavalry should be added to the defending army.",
				c.getDefendingArmy().getUnits().get(initialArmySize) instanceof Infantry);
		break;
	default:
		fail("recruitUnit can only be used on types: Archer, Cavalry or Infantry");
	}

	}
private void playerRecruitUnitWithWithOverDriveHelper3(Game game, String type, String cityName) throws Exception {
	
	MilitaryBuilding m = null;
	City c = null;
	int initialArmySize = 0;
	switch (type) {
	case "Archer":
		m = new ArcheryRange();
		c = insertMilitaryBuildingHelper(game, cityName, m);
		initialArmySize = c.getDefendingArmy().getUnits().size();
		m.setCoolDown(false);
		m.setCurrentRecruit(0);
		m.setOverDrive(true);
		game.getPlayer().recruitUnit("Archer", cityName);
		unitAddedAndDefendingArmyUpdatedWithOverDriveHelper(game, c, initialArmySize + 2, 5000, m);
		assertTrue("2 units of type Archer should be added to the defending army.",
				c.getDefendingArmy().getUnits().get(initialArmySize) instanceof Archer);

		break;
	case "Cavalry":
		m = new Stable();
		c = insertMilitaryBuildingHelper(game, cityName, m);
		initialArmySize = c.getDefendingArmy().getUnits().size();
		m.setCoolDown(false);
		m.setCurrentRecruit(0);
		m.setOverDrive(true);

		game.getPlayer().recruitUnit("Cavalry", cityName);
		unitAddedAndDefendingArmyUpdatedWithOverDriveHelper(game, c, initialArmySize + 2, 5000, m);
		assertTrue("2 units of type Cavalry should be added to the defending army.",
				c.getDefendingArmy().getUnits().get(initialArmySize) instanceof Cavalry);
		break;
	case "Infantry":
		m = new Barracks();
		c = insertMilitaryBuildingHelper(game, cityName, m);
		initialArmySize = c.getDefendingArmy().getUnits().size();
		m.setCoolDown(false);
		m.setCurrentRecruit(0);
		m.setOverDrive(true);

		game.getPlayer().recruitUnit("Infantry", cityName);
		unitAddedAndDefendingArmyUpdatedWithOverDriveHelper(game, c, initialArmySize + 2, 5000, m);
		assertTrue("2 units of type Cavalry should be added to the defending army.",
				c.getDefendingArmy().getUnits().get(initialArmySize) instanceof Infantry);
		break;
	default:
		fail("recruitUnit can only be used on types: Archer, Cavalry or Infantry");
	}

	}
	private void unitAddedAndTreasuryUpdatedWithOverDriveHelper(Game game, City c, int count, int treasury, MilitaryBuilding m) {
				assertEquals("Recruiting Cost was not deceremented correctly.", (int) (treasury - (m.getRecruitmentCost()*4)),
				(int) game.getPlayer().getTreasury());
	}
	
	private void unitAddedAndParentArmyUpdatedWithOverDriveHelper(Game game, City c, int count, int treasury, MilitaryBuilding m) {
		assertEquals("The recruited units should have the defending army of the city as its parent army.", c.getDefendingArmy(),c.getDefendingArmy().getUnits().get(count-1).getParentArmy());
	
	}
	private void unitAddedAndDefendingArmyUpdatedWithOverDriveHelper(Game game, City c, int count, int treasury, MilitaryBuilding m) {
		assertEquals("2 units should be added to the defending army.", count, c.getDefendingArmy().getUnits().size());
		
	}
	
	private void playerRecruitUnitExceptionHelper(Game game, String type, String cityName, boolean coolDown,boolean overDrive,
			int currentRecruit) throws Exception {
		MilitaryBuilding m = null;
		switch (type) {
		case "Archer":
			m = new ArcheryRange();
			insertMilitaryBuildingHelper(game, cityName, m);
			m.setCoolDown(coolDown);
			m.setCurrentRecruit(currentRecruit);
			m.setOverDrive(overDrive);
			game.getPlayer().recruitUnit("Archer", cityName);
			break;
		case "Cavalry":
			m = new Stable();
			insertMilitaryBuildingHelper(game, cityName, m);
			m.setCoolDown(coolDown);
			m.setCurrentRecruit(currentRecruit);
			m.setOverDrive(overDrive);

			game.getPlayer().recruitUnit("Cavalry", cityName);
			break;
		case "Infantry":
			m = new Barracks();
			insertMilitaryBuildingHelper(game, cityName, m);
			m.setCoolDown(coolDown);
			m.setCurrentRecruit(currentRecruit);
			m.setOverDrive(overDrive);

			game.getPlayer().recruitUnit("Infantry", cityName);

			break;
		default:
			fail("recruitUnit can only be used on types: Archer, Cavalry or Infantry");
		}

	}
	private City insertMilitaryBuildingHelper(Game game, String cityName, MilitaryBuilding m) throws Exception {
		for (City c : game.getAvailableCities()) {
			if (c.getName().equals(cityName)) {
				c.getMilitaryBuildings().add(m);
				return c;
			}
		}
		return null;

	}
	private Game createGameHelper(String cityName) throws Exception {
		Game g = new Game("Player", cityName);
		return g;
	}
	
	private static boolean containsMethodName(Method[] methods, String name) {
		for (Method method : methods) {
			if (method.getName().equals(name))
				return true;
		}
		return false;
	}
	
	private void testSetterMethodExistsInClass(Class aClass, String methodName, Class inputType,
			boolean writeVariable) {

		Method[] methods = aClass.getDeclaredMethods();
		String varName = methodName.substring(3).toLowerCase();
		if (writeVariable) {
			assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is a WRITE variable.", containsMethodName(methods, methodName));
		} else {
			assertFalse("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is not a WRITE variable.", containsMethodName(methods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputType);
		} catch (NoSuchMethodException e) {
			found = false;
		}

		assertTrue(aClass.getSimpleName() + " class should have " + methodName + " method that takes one "
				+ inputType.getSimpleName() + " parameter.", found);

		assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + ".",
				m.getReturnType().equals(Void.TYPE));

	}
	private void testGetterMethodExistsInClass(Class aClass, String methodName, Class returnedType,
			boolean readvariable) {
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName);
		} catch (NoSuchMethodException e) {
			found = false;
		}

		String varName = "";
		if (returnedType == boolean.class)
			varName = methodName.substring(2).toLowerCase();
		else
			varName = methodName.substring(3).toLowerCase();
		if (readvariable) {
			assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is a READ variable.", found);
			assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + " class.",
					m.getReturnType().isAssignableFrom(returnedType));
		} else {
			assertFalse("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is not a READ variable.", found);
		}

	}

	private void testGetterLogic(Object createdObject, String name, Object value) throws Exception {

		Field f = null;
		Class curr = createdObject.getClass();

		while (f == null) {

			if (curr == Object.class)
				fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \""
						+ name + "\".");
			try {
				f = curr.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				curr = curr.getSuperclass();
			}

		}

		f.setAccessible(true);
		f.set(createdObject, value);

		Character c = name.charAt(0);

		String methodName = "get" + Character.toUpperCase(c) + name.substring(1, name.length());

		if (value.getClass().equals(Boolean.class))
			methodName = "is" + Character.toUpperCase(c) + name.substring(1, name.length());

		Method m = createdObject.getClass().getMethod(methodName);
		assertEquals(
				"The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName()
						+ " should return the correct value of variable \"" + name + "\".",
				value, m.invoke(createdObject));

	}
	private void testSetterLogic(Object createdObject, String name, Object setValue, Object expectedValue, Class type)
			throws Exception {

		Field f = null;
		Class curr = createdObject.getClass();

		while (f == null) {

			if (curr == Object.class)
				fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \""
						+ name + "\".");
			try {
				f = curr.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				curr = curr.getSuperclass();
			}

		}

		f.setAccessible(true);

		Character c = name.charAt(0);
		String methodName = "set" + Character.toUpperCase(c) + name.substring(1, name.length());
		Method m = createdObject.getClass().getMethod(methodName, type);
		m.invoke(createdObject, setValue);

		assertEquals(
				"The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName()
						+ " should set the correct value of variable \"" + name + "\".",
				expectedValue, f.get(createdObject));

	}

}
