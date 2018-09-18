import java.util.ArrayList;

public class Colony extends Location{
	private int helpless = 0;
	private int waste = 0;
	//private String locationName;
	
	public Colony(String newName) {
		super.locationName = newName;
	}

	public int getNumHelpless() {
		return helpless;
	}
	
	public void addHelpless(int num) {
		helpless = helpless + num;
	}
	public void remHelpless(int num) {
		if(helpless>0)
			helpless = helpless - num;
	}
	
	public void addWaste() {
		waste++;
	}
	public void remWaste(int num) {
		waste = waste - num;
		if(waste<0)
			waste = 0;
	}
	public int getWaste() {
		return waste;
	}

}
