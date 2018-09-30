import java.util.ArrayList;

public class Location {
	protected int zombies = 0;
	protected int baracades = 0;
	protected ArrayList<String> survivors;
	protected String locationName;
	
	public Location() {
		survivors = new ArrayList<String>();
	}
	public Location(String newName) {
		locationName = newName;
		survivors = new ArrayList<String>();
	}
	
	public String getName() {
		return locationName;
	}
	
	public int getNumZom() {
		return zombies;
	}
	
	public int getNumBar() {
		return baracades;
	}
	
	public int getNumSurvivors() {
		return survivors.size();
	}
	
	public ArrayList<String> getSurvivors() {
		return survivors;
	}
	
	public void setName(String locName) {
		locationName = locName;
	}
	
	public void addBaracade(int num) {
		baracades = baracades + num;
	}
	public void addZombies(int num) {
		zombies = zombies + num;
	}
	
	public void addSurvivor(String newChar) {
		survivors.add(newChar);
	}
	
	public void remSurvivor(String charname) {
		survivors.remove(charname);
	}
	
	public void remZombies(int num) {
		if(zombies>0)
			zombies = zombies - num;
		
	}
	
	public void remBaracade(int num) {
		if(baracades>0)
			baracades = baracades - num;
	}
	
	public boolean isThere(String charName) {
		return survivors.contains(charName);
	}
}
