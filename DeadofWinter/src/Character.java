
public class Character {
	private String _name;
	private String _position;
	private String _gender;
	private int _status; // 0=Non-Exiled, 1=Exiled
	private String _male[] = {"Kodiak Colby", "Arthur Thurston", "Andrew Evans", "David Garcia", "Thomas Heart", "Daniel Smith", "Brandon Kane",
							"Gabriel Diaz", "John Price", "James Meyers", "Brian Lee", "Rod Miller", "Gray Beard", "Harman Brooks", "Mike Cho", 
							"Buddy Davis", "Edward White", "Forest Plum"} ;
	
	public Character(String charName) {
		_name = charName;
		_gender = "f";
	}
	public Character() {
		_gender = "f";
	}
	public String getPosition() {
		//System.out.println(_position);
		return _position;
	}
	
	public String getName() {
		return _name;
	}
	
	public String getGender() {
		return _gender;
	}
	
	public void setPosition(String newPos) {
		_position = newPos;
	}
	public void setName(String newName) {
		_name = newName;
		setGender();
	}
	
	public void setGender() {
		if(_name.equals("Sparky"))
			_gender = "d";
		for(int i=0; i < _male.length; i++ ) {
			if(_name.equals(_male[i]))
				_gender = "m";
		}
	}
	public void setStatus(int newstatus) {
		_status = newstatus;
	}
	public boolean isExiled() {
		if(_status==0)  return false;
		else 			return true;
	}
}
