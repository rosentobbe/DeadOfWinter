import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
//import org.apache.commons.lang3.ArrayUtils;

import bsh.Console;

public class Player {
	private String name;
	private Character[] characters;
	private ArrayList<String> allCharNames;
	private int numChar;
	private boolean exiled = false;
	//private int maxChar = 8;
	
	public Player() {
		allCharNames = new ArrayList<String>();
	}
	public Player(String playername) {
		setName(playername);
		allCharNames = new ArrayList<String>();
	}
	public boolean isPlayerExiled() {
		return exiled;
	}
	public void setToExiled() {
		exiled = true;
		for(int i = 0; i < characters.length; i++)
			characters[i].setStatus(1);
	}
	public int isExiled(String _charName) { // Status: -1: Player don't have that Character. 0: Player have that Character and not exiled. 1: Player have Character and Exiled. 
		int status = -1;
		for(int i=0; i<characters.length; i++) {
			if(characters[i].getName().equals(_charName)) {
				if(characters[i].isExiled())
					status = 1;
				else
					status = 0;
			}
		}
		return status;
	}
	
	public void addChar(String newChar) {
		characters = ArrayUtils.add(characters, new Character(newChar));
		//characters[numChar].setName(newChar);
		allCharNames.add(newChar);
		numChar++;
	}
	
	public boolean controlsChar(String _SpecChar) {
		if(allCharNames.contains(_SpecChar))
			return true;
		else
			return false;
	}
	public String getCharsName(int index) {
		return characters[index].getName();
	}
	
	public Character[] getChars() {
		return characters;
	}
	public ArrayList<String> getCharList() {
		return allCharNames;
	}
	public int getNumchars() {
		return numChar;
	}
	
	public void removeChar(String charName) {
		//characters = ArrayUtils.removeElement(characters, charName);
		System.out.println("In remove char");
		for(int i = 0; i < numChar; i++) {
			if(characters[i].getName().equals(charName)) {
				characters = ArrayUtils.remove(characters, i);
				break;
			}
				
		}
		allCharNames.remove(charName);
		numChar--;
	}
	
	public String getCharPos(String _charName) {
		for(int i = 0; i < numChar; i++) {
			if(_charName.equals(characters[i].getName())) {
				return characters[i].getPosition();
			}
		}
		
		System.out.println("Error: Not in Play");
		return "Error: Not in Play";
	}
	
	public void setCharPos(String _charName, String _newPos) {
		for(int i = 0; i < numChar; i++) {
			if(_charName.equals(characters[i].getName())) {
				characters[i].setPosition(_newPos);
				
			}
		}
	}
	public void setCharStartPos() {
		for(int i = 0; i < characters.length; i++)
			characters[i].setStartPosition(characters[i].getPosition());
	}
	public String getCharStartPos(String _charName) {
		for(int i = 0; i < numChar; i++) {
			if(_charName.equals(characters[i].getName())) {
				return characters[i].getStartPosition();
			}
		}
			 System.out.println("Error: Not in Play");
			 return "Error: Not in Play";
	}
	
	public void printArray() {
		System.out.println(Arrays.toString(characters));
	}

	public void setName(String newName) {
		name = newName;
	}
	public String getName() {
		return name;
	}
}
