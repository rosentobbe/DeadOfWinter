import java.util.ArrayList;
import java.util.Arrays;
//import org.apache.commons.lang3.ArrayUtils;

public class Player {
	private String name;
	private Character[] characters;
	private ArrayList<String> charnames;
	private int numChar;
	private int maxChar = 8;
	
	public Player() {
		charnames = new ArrayList<String>();
		characters = new Character[maxChar];
		for(int i=0; i<maxChar; i++) {
			characters[i] = new Character();
			characters[i].setName("\0");
		}
	}
	public Player(String playername) {
		setName(playername);
		charnames = new ArrayList<String>();
		characters = new Character[maxChar];
		for(int i=0; i<maxChar; i++) {
			characters[i] = new Character();
			characters[i].setName("\0");
		}
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
		characters[numChar].setName(newChar);
		charnames.add(newChar);
		numChar++;
	}
	
	public boolean controlsChar(String _SpecChar) {
		if(charnames.contains(_SpecChar))
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
	public ArrayList getCharList() {
		return charnames;
	}
	public int getNumchars() {
		return numChar;
	}
	
	public void removeChar(String charName) {
		//characters = ArrayUtils.removeElement(characters, charName);
		System.out.println("In remove char");
		int i, j;
		for(i=0; i<numChar; i++) {
			if(characters[i].getName().equals(charName)) {
				System.out.println("In if");
				for(j=i; j<numChar-1;j++) {
					characters[j] = characters[j+1];
				}
			}
		}// Något är fel här!! kan inte ersätta den sista i arrayen1!!!!!
		//characters[numChar-1].setName(String.valueOf(numChar));
		//System.out.println(Arrays.toString(characters));
		charnames.remove(charName);
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
