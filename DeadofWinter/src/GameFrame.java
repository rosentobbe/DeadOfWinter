import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.ButtonPeer;
import java.lang.reflect.Method;
import java.nio.charset.spi.CharsetProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.PrimitiveIterator.OfDouble;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.ArrayUtils;

import sun.tools.jar.resources.jar;

public class GameFrame extends JFrame implements ActionListener {
/************************************************************
*			           Declares/initialization              *
*************************************************************/	
	private JPanel _cardPanel;
	private CardLayout _deck;
	private int numbPlayers;
	private int wasteStatus = 0; //0: Not touched waste, 1:added Waste, -1:Clear Waste
	private int _started = 0;
	private int _whosTurn = 0;
	private int _round = 0;
	
	private Checkbox[] _charBox;
	private CheckboxGroup boxGroup;
	private String[] _charString;
	//Main frame
	private JPanel _mainFrame;
	private JPanel _waste;
	private JPanel _clearWastePanel;
	private JPanel _info;
	private JPanel _playerInfo;
	private JPanel _addremChar;
	private JPanel _name;
	private JPanel _mainCard;
	private JPanel _actions1;
	private JPanel _colPanel;
	private JPanel _addremPanel;
	private JButton _clearWaste;
	private JButton _addWaste;
	private JButton _addChar;
	private JButton _remChar;
	private JButton _nextTurn;
	private JButton _actionsButton;
	private JSpinner _numberofWaste;
	private JLabel _player;
	private Character[] _playerChars;
	private JTextArea _infoChar;
	private JTextArea _infoColony;
	private CardLayout _maindeck;
	private ArrayList<String> _currPlayerChars;

	private Location _gasStation;
	private Location _hospital;
	private Location _school;
	private Location _library;
	private Location _groceryStore;
	private Location _policeStation;
	private Colony _colony;
	private ArrayList<Location> _ListLoc;
	private ArrayList<String> _charDeck;
	private ArrayList<String> _deathDeck;
	
	private String _Poscharinfo;
	private String _currcharpos;
	private String _newcharpos;
	private Player[] _playerArray;
	//private int numPchars;
	
	private JPanel _boxPanel;
	//Move Frame
	private JPanel _dropPanel;
	private JPanel _performPanel;
	private JButton _perform;
	private String[] _actionsString = {"Search","Move", "Use Weapon", "Use Food", "Use Medicine", "Use Attached Card"};
	private String[] _locations = {"Colony", "Police Station", "School", "Library", "Hospital", "Gas Station", "Grocery Store"};
	private String[] _result = {"Nothing", "Fuel", "Frostbite", "Wound", "Zombiebite"};
	private JComboBox<String> _locationDrop;
	private JComboBox<String> _resultDrop;
	private JComboBox<String> _actionsDrop;
	
	//Crossroad
	private Crossroads _crossroadDeck;
	private int _cardNumber;
	private Random rand;
	//Perform Panel Items
	private int error_cancel = 0;
	private String _Errortext = "-Error: Character is not at that Location";
	private String _ErrorText_SearchColony = "-Error: Can't search in Colony";
	private String _ErrorText_moveSameLoc = "-Error: Can't MOVE to same location";
	private JLabel _Errorlable;
	private String _LastAddedChar = "";
	private String _actionsSel = "";
	private String _char = "";
	private String _loc = "";
	private String _consi = "";
	private String _male[] = {"Kodiak Colby", "Arthur Thurston", "Andrew Evans", "David Garcia", "Thomas Heart", "Daniel Smith", "Brandon Kane",
			"Gabriel Diaz", "John Price", "James Meyers", "Brian Lee", "Rod Miller", "Grey Beard", "Harman Brooks", "Mike Cho", 
			"Buddy Davis", "Edward White", "Forest Plum"};
	private ArrayList<String> _maleName;
	//Add/remove-panel
	private final static int ADD = 1;
	private final static int REMOVE = 0;
	private int _AddorRem = -1;
	private JButton _add_rem;
	private JLabel _add_rem_label;
	private JSpinner _numberofHelpless;
	private JLabel _helplessText = new JLabel("How many Helpless would you like to add?");

/***********************************************************
*			          Start of Class                       *
************************************************************/
	public GameFrame(Player[] _playerArrayay, int numPlayers, JCheckBox[] _listChars, String[] labels, String[] deck) {
		this.setSize(700, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		numbPlayers = numPlayers;
		_playerArray = _playerArrayay;
		//_charBox = _listChars; //Kommentera bort undre rad om denna blir insatt.
		_charBox = new Checkbox[labels.length]; //Ta bort om �vre blir insatt.
		_charString = labels;
		_maleName = new ArrayList<String>(Arrays.asList(_male));
		_charDeck = new ArrayList<String>(Arrays.asList(deck));
		_deathDeck = new ArrayList<String>();
		
		
		_deck = new CardLayout();
		_cardPanel = new JPanel();
		_cardPanel.setLayout(_deck);
		this.add(_cardPanel);
		
/************************************************************
*			           Cards                                *
************************************************************/
		_Poscharinfo = new String("");
		_currcharpos = new String("");
		_newcharpos = new String();
		
		//Main play frame
		_maindeck = new CardLayout();
		_mainCard = new JPanel();
		_mainCard.setLayout(_maindeck);
		_mainFrame = new JPanel();
		_mainFrame.setLayout(new BorderLayout());
		_actions1 = new JPanel();
		_name = new JPanel();
		_info = new JPanel();
		_playerInfo = new JPanel();
		_info.add(_playerInfo);
		_actionsButton = new JButton("Actions");
		_player = new JLabel();
		_name.add(_player);
		_clearWaste = new JButton("Clear Waste");
		_addWaste = new JButton("Add Waste");
		_addChar = new JButton("Add Char");
		_remChar = new JButton("Remove Char");
		_addremChar = new JPanel(new GridLayout(0, 2));
		_waste = new JPanel(new GridLayout(0, 2));
		_clearWastePanel = new JPanel(new GridLayout(2,0));
		_numberofWaste = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
		_addremChar.add(_addChar);
		_addremChar.add(_remChar);
		_nextTurn = new JButton("Next Turn");
		_waste.add(_addWaste);
		_waste.add(_clearWastePanel);
		_clearWastePanel.add(_clearWaste);
		_clearWastePanel.add((Component) _numberofWaste);
		_actions1.setLayout(new GridLayout(4,1));
		_actions1.add(_actionsButton);
		_actions1.add(_waste);
		_actions1.add(_addremChar);
		_actions1.add(_nextTurn);

		_colPanel = new JPanel();
		_infoChar = new JTextArea();
		_infoChar.setEditable(false);
		_infoChar.setOpaque(false);
		_infoChar.setPreferredSize(new Dimension(190, 500));
		_infoColony = new JTextArea();
		_infoColony.setOpaque(false);
		_infoColony.setPreferredSize(new Dimension(210, 500));
		_infoColony.setEditable(false);
		_colPanel.add(_infoColony);
		
		_ListLoc = new ArrayList<Location>();
		_hospital = new Location("Hospital");
		_policeStation = new Location("Police Station");
		_library = new Location("Library");
		_colony = new Colony("Colony");
		_groceryStore = new Location("Grocery Store");
		_gasStation = new Location("Gas Station");
		_school = new Location("School");
		
		_ListLoc.add( _hospital);
		_ListLoc.add( _policeStation);
		_ListLoc.add( _library);
		_ListLoc.add( _colony);
		_ListLoc.add( _groceryStore);
		_ListLoc.add( _gasStation);
		_ListLoc.add( _school);
		placeAllChars();
		//getInfo();
		//placeAllChars();
		_playerArray[_whosTurn].setCharStartPos();

		_mainCard.add(_actions1, "action1");
		_mainFrame.add(_name, BorderLayout.PAGE_START);
		_mainFrame.add(_mainCard, BorderLayout.WEST);
		_mainFrame.add(_info, BorderLayout.EAST);
		_mainFrame.add(_colPanel, BorderLayout.CENTER);
		
		//Radio panel
		_boxPanel = new JPanel(new GridLayout(8,4));
		_boxPanel.setAlignmentY(JComponent.LEFT_ALIGNMENT);
		_locationDrop = new JComboBox<String>(_locations);
		_resultDrop = new JComboBox<String>(_result);
		_actionsDrop = new JComboBox<String>(_actionsString);
		boxGroup = new CheckboxGroup();
		checkboxes();
		
		//Perform panel Move Frame 
		_performPanel = new JPanel();
		_Errorlable = new JLabel();
		_performPanel.setLayout(new BorderLayout());
		_perform = new JButton("Perform");
		_dropPanel = new JPanel();
		_dropPanel.add(_actionsDrop);
		_dropPanel.add(_locationDrop);
		_dropPanel.add(_resultDrop);
		_dropPanel.add(_perform);
		_performPanel.add(_dropPanel, BorderLayout.PAGE_END);
		
		//Add Remove char panel
		JPanel helplessPanel = new JPanel();
		_add_rem = new JButton();
		_add_rem_label = new JLabel();
		_addremPanel = new JPanel(new BorderLayout());
		_numberofHelpless = new JSpinner(new SpinnerNumberModel(0, 0, 3, 1));
		_addremPanel.add(_add_rem_label, BorderLayout.PAGE_START);
		helplessPanel.add(_helplessText);
		helplessPanel.add(_numberofHelpless);
		helplessPanel.add(_add_rem);
		_addremPanel.add(helplessPanel, BorderLayout.PAGE_END);
		//Crossroaddeck and random draw.
		_crossroadDeck = new Crossroads(_playerArray, _ListLoc);
		_crossroadDeck.setAlwaysOnTop(true);
		rand = new Random();
		_cardNumber = rand.nextInt(_crossroadDeck.getNumberofCards()-1)+1;
		getInfo();
		checkCard();
		
		//Add listeners
		_crossroadDeck._Con.addActionListener(this);
		_clearWaste.addActionListener(this);
		_add_rem.addActionListener(this);
		_addWaste.addActionListener(this);
		_addChar.addActionListener(this);
		_remChar.addActionListener(this);
		_nextTurn.addActionListener(this);
		_actionsButton.addActionListener(this);
		_perform.addActionListener(this);
	
		_cardPanel.add(_mainFrame, "main");
		_cardPanel.add(_performPanel, "perform");
		_cardPanel.add(_addremPanel, "addrem");
		this.setVisible(true);
	}

	public void resetVariables() {
		_consi = "";
		_actionsSel = "";
		_loc = "";
		_char = "";
		wasteStatus = 0;
	}
	public int isCharExiled(String _charName) {
		int status = -1;
		for(int i=0; i < numbPlayers; i++) {
			if(_playerArray[i].getCharList().contains(_charName))
				status = _playerArray[i].isExiled(_charName);
		}
		return status;
	}
	public boolean isCharAtLocation(String _charName, String _locName) {
		if(_playerArray[_whosTurn].getCharPos(_charName).equals(_locName))
			return true;
		return false;
	}	
	
	public boolean allCharAtSpecificLocation(String _locName) {
		ArrayList<String> charNames = _playerArray[_whosTurn].getCharList();
		int charsAtLocation = 0;
		for(int i = 0; i < charNames.size(); i++) {
			if(_playerArray[_whosTurn].getCharPos(charNames.get(i).toString()).equals(_locName))
				charsAtLocation++;
		}
		if(charsAtLocation == _playerArray[_whosTurn].getNumchars())
			return true;
		else
			return false;
	}
	public boolean anyCharAtSpecificLocation(String _locName) {
		ArrayList<String> charNames = _playerArray[_whosTurn].getCharList();
		for(int i = 0; i < charNames.size(); i++) {
			if(_playerArray[_whosTurn].getCharPos(charNames.get(i).toString()).equals(_locName))
				return true;
		}
		return false;
	}
	public int whoControllsSurvivor(String survivorName) {
		int playerControllingSurvivor = -1;
		for(int i=0; i < numbPlayers; i++) {
			if(_playerArray[i].controlsChar(survivorName)) {
				playerControllingSurvivor = i;
				break;
			}
		}
		return playerControllingSurvivor;
	}
	public int[] whichPlayersContollAtThisLocation(ArrayList<String> survivors){
		int[] whoControllsSearch = new int[survivors.size()];
		for(int i = 0; i < survivors.size(); i++) {
			whoControllsSearch[i] = whoControllsSurvivor(survivors.get(i));
		}
		return whoControllsSearch;
	}
	public int numberOfCharsPlayerControlls(int[] ArrayWithPlayernumbers, int whichPlayer) {
		int numberofChars = 0;
		for(int i = 0; i< ArrayWithPlayernumbers.length; i++) {
			if(ArrayWithPlayernumbers[i] == whichPlayer)
				numberofChars++;
		}
		return numberofChars;
	}
	public Location getLocationWithName(String _LocationName) {
		for(int i = 0; i < _ListLoc.size(); i++) {
			if(_ListLoc.get(i).getName().equals(_LocationName))
				return _ListLoc.get(i);
		}
		return null;
	}
	
	public void checkboxes() {
		boxGroup = new CheckboxGroup();
		for(int i=0; i<_charString.length; i++) {
			_charBox[i] = new Checkbox(_charString[i], boxGroup, true);
			_boxPanel.add(_charBox[i], boxGroup);
		}
	}
	
	public void getInfo() {
//		for(int i = 0; i < _ListLoc.size(); i++) {
//			System.out.println(_ListLoc.get(i).getName() + ": " + _ListLoc.get(i).getSurvivors().toString());
//		}
//		System.out.println("\n");
		_currPlayerChars = _playerArray[_whosTurn].getCharList();
		_infoChar.setText("");
		_Poscharinfo = "";
		//numPchars = _playerArray[_whosTurn].getNumchars();
		_player.setText(_playerArray[_whosTurn].getName() + "'s turn");
		for(int i=0; i<numbPlayers; i++) {
			_playerChars = _playerArray[i].getChars();
			_Poscharinfo = _Poscharinfo + _playerArray[i].getName() + ": \n";
			for(int j=0; j<_playerArray[i].getNumchars(); j++) {
				_newcharpos =_playerArray[i].getCharsName(j)+": "+_playerArray[i].getCharPos(_playerChars[j].getName()) +"\n";
				_Poscharinfo = _Poscharinfo + _newcharpos;
			}
			_Poscharinfo = _Poscharinfo + "\n";
		}
		_infoChar.setText(_Poscharinfo);
		_playerInfo.add(_infoChar);
		_infoColony.setText("Colony: " + "\n\nZombies: " + _colony.getNumZom() + "\n\nHelpless: "+ _colony.getNumHelpless() + 
				"\n\nBaracades: " + _colony.getNumBar() + "\n\nRoundTracker: " + _round + "\n\n\n\nSelected Crossroad: " + _cardNumber);
		
	}
	
	public void placeAllChars() {
		Character[] chars;
		int j, i;
		for(i=0; i < numbPlayers; i++) {
			chars = _playerArray[i].getChars();
			for(j=0; j<_playerArray[i].getNumchars(); j++) {
				_colony.addSurvivor(chars[j].getName());
			}
		}
	}	
	public void moveTo(String _newLoc, String _chartoMove) {
		if(!isCharAtLocation(_chartoMove, _newLoc)) {
			for(int i=0; i < _ListLoc.size(); i++) {
				if(_ListLoc.get(i).isThere(_chartoMove))
					_ListLoc.get(i).remSurvivor(_chartoMove);
				else if(_ListLoc.get(i).getName().equals(_newLoc))
					_ListLoc.get(i).addSurvivor(_chartoMove);
			}
			_playerArray[_whosTurn].setCharPos(_chartoMove, _newLoc);
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source= arg0.getSource();
		if(source.equals(_actionsButton)) {
			_performPanel.add(_boxPanel, BorderLayout.CENTER);
			for(int i=0; i < _charBox.length; i++) {
				if(_currPlayerChars.contains(_charBox[i].getLabel())) {
						_charBox[i].setEnabled(true);
						_charBox[i].setState(true);
				}
				else
					_charBox[i].setEnabled(false);
			}
			_deck.show(_cardPanel, "perform");
		}
		else if(source.equals(_perform)) {
			_actionsSel = _actionsDrop.getSelectedItem().toString();
			_char = boxGroup.getSelectedCheckbox().getLabel();
			_currcharpos = _playerArray[_whosTurn].getCharPos(_char);
			_loc = _locationDrop.getSelectedItem().toString();
			_consi = _resultDrop.getSelectedItem().toString();
			//checkCard();  M�ste man ha checkCard h�r? eller r�cker det att kolla efter? 
			
			if(_actionsSel.equals("Search")) {
				if(!_loc.equals(_currcharpos)) {
					error_cancel = 1;
					_Errorlable.setText(_actionsSel + _Errortext);
					_dropPanel.add(_Errorlable);
				}
				else if(_loc.equals("Colony")) {
					error_cancel = 1;
					_Errorlable.setText(_actionsSel + _ErrorText_SearchColony);
					_dropPanel.add(_Errorlable);
				}
				else
					_dropPanel.remove(_Errorlable);
			}
			else if(_actionsSel.equals("Use Weapon")) {
				useWeaponIfPossible();
			}
			else if(_actionsSel.equals("Move")) {
				moveIfPossible();
			}
			else if(_actionsSel.equals("Use Food")) {
				//Ska man ens g�ra n�got h�r? 
				
			}
			else if(_actionsSel.equals("Use Medicine")) {
				//Ska man ens g�ra n�got h�r? 
			}
			if(error_cancel == 0)
				_deck.show(_cardPanel, "main"); 
			_dropPanel.repaint();
		}
		else if(source.equals(_clearWaste)) {
			wasteStatus = -1;
			_colony.remWaste((int)_numberofWaste.getValue());
			_numberofWaste.setValue(0);
		}
		else if(source.equals(_addWaste)) {
			wasteStatus = 1;
			_colony.addWaste((int)_numberofWaste.getValue());
			_numberofWaste.setValue(0);
		}
		else if(source.equals(_addChar)) {
			prepareAddWindow();
		}
		else if(source.equals(_remChar)) {
			prepareRemoveWindow();
		}
		else if(source.equals(_add_rem)) {
			if(_AddorRem == REMOVE) {
				String _char = boxGroup.getSelectedCheckbox().getLabel();
				_deathDeck.add(_char);
				_playerArray[_whosTurn].removeChar(_char);
				for(int i=0; i < _ListLoc.size(); i++) {
					if(((Location)_ListLoc.get(i)).isThere(_char))
						((Location)_ListLoc.get(i)).remSurvivor(_char);
				}
			}
			else if(_AddorRem == ADD) {
				String _char = boxGroup.getSelectedCheckbox().getLabel();
				_LastAddedChar = _char;
				_playerArray[_whosTurn].addChar(_char);
				_playerArray[_whosTurn].setCharPos(_char, "Colony");
				_charDeck.remove(_char);
				_colony.addSurvivor(_char);
				_colony.addHelpless((int) _numberofHelpless.getValue());
				checkCard();
				_numberofHelpless.setValue(0);
			}
			//** Test that can be removed when done!
			Character[] test = _playerArray[_whosTurn].getChars();
			for(int i = 0; i < test.length; i++)
				System.out.print(test[i].getName()+"--");
			System.out.println("\n");
			//***********************************
			_deck.show(_cardPanel, "main");
		}
		else if(source.equals(_nextTurn)) {
			_whosTurn++;
			if(_whosTurn == numbPlayers)
				_whosTurn = 0;
			if(_whosTurn == _started) {
				_started++; 
				if(_started == numbPlayers)
					_started = 0;
				_whosTurn = _started;
				_round--;
				//getInfo();
			}
			getInfo();
			_crossroadDeck.resetDrawnThisRound();
			_playerArray[_whosTurn].setCharStartPos();
			_cardNumber = rand.nextInt(_crossroadDeck.getNumberofCards()-1)+1;
			resetVariables();
			if(error_cancel == 0) // Nytt f�r att inte kunnan trigga om man g�r "fel" perform.
				checkCard();
		}
		else if(source.equals(_crossroadDeck._Con)) {
			resolveCardOption(_cardNumber);
			getInfo();
		}
		
		getInfo();
		if(error_cancel == 0) // Nytt f�r att inte kunnan trigga om man g�r "fel" perform.
			checkCard();
		_actionsSel = "";
		_LastAddedChar = "";
		//_char = "";
		_currcharpos = "";
		_loc = "";
		_consi = "";
		error_cancel = 0;
	}

	private void prepareAddWindow() {
		_AddorRem = ADD;
		_addremPanel.add(_boxPanel, BorderLayout.CENTER);
		_add_rem.setText("Add Character");
		_add_rem_label.setText("Check which character to add");
		for(int i=0; i < _charBox.length; i++) {
			if(_charDeck.contains(_charBox[i].getLabel())) {
					_charBox[i].setEnabled(true);
					_charBox[i].setState(true);
			}
			else
				_charBox[i].setEnabled(false);
		}
		_deck.show(_cardPanel, "addrem");
	}

	private void prepareRemoveWindow() {
		_AddorRem = REMOVE;
		_addremPanel.add(_boxPanel, BorderLayout.CENTER);
		_add_rem.setText("Remove Character");
		_add_rem_label.setText("Check which character to remove");
		for(int i=0; i < _charBox.length; i++) {
			if(_currPlayerChars.contains(_charBox[i].getLabel())) {
					_charBox[i].setEnabled(true);
					_charBox[i].setState(true);
			}
			else
				_charBox[i].setEnabled(false);
		}
		_deck.show(_cardPanel, "addrem");
	}
	private void useWeaponIfPossible() {
		if(!_loc.equals(_currcharpos)) {
			error_cancel = 1;
			_Errorlable.setText(_actionsSel + _Errortext);
			_dropPanel.add(_Errorlable);
		}
		else {
			_dropPanel.remove(_Errorlable);
			for(int i=0; i < _ListLoc.size(); i++) {
				if(((Location)_ListLoc.get(i)).getName().equals(_loc)){
					((Location)_ListLoc.get(i)).remZombies(1);
					}
				// L�gga till s� att man kan �ndra antalet Zombies man d�dar?! 
			}
		}
	}

	private void moveIfPossible() {
		if(_loc.equals(_currcharpos)) {
			error_cancel = 1;
			_Errorlable.setText(_actionsSel + _ErrorText_moveSameLoc);
			_dropPanel.add(_Errorlable);
		}
		else{
			_dropPanel.remove(_Errorlable);
			moveTo(_loc, _char);
		}
	}
	
	private void resolveCardOption(int cardNumber) {
		int selectedOption = _crossroadDeck.getSelected();
		solveCardEffect(selectedOption, cardNumber);
	}

	private void solveCardEffect(int selectedOption, int onCard) {
		switch(onCard) {
		case 1:
			if(selectedOption == 1)
				moveTo("Colony", _char);	
			break;
		case 3:
			if(selectedOption == 2)
				moveTo(_playerArray[_whosTurn].getCharStartPos(_char), _char);	
			break;
		case 7:
			if(selectedOption == 1)
				_colony.remZombies(8);
			break;
		case 9:
			if(selectedOption == 1) {
				_hospital.remZombies(_hospital.getNumZom());
				_hospital.addBaracade(4);
			}
			else if(selectedOption == 1) {
				_groceryStore.remZombies(_groceryStore.getNumZom());
				_groceryStore.addBaracade(4);
			}
			break;
		case 18:
			if(selectedOption == 2) {
				_playerArray[_whosTurn].addChar("Grey Beard");
				_playerArray[_whosTurn].setCharPos("Grey Beard", "Colony");
				_charDeck.remove("Grey Beard");
			}
			break;
		case 19:
			if(selectedOption == 1) {
				_charDeck.remove("Sophie Robinson");
				_playerArray[_whosTurn].addChar("Sophie Robinson");
				_playerArray[_whosTurn].setCharPos("Sophie Robinson", "Colony");
			}
			else if(selectedOption == 2) {
				_charDeck.remove("Sophie Robinson");
				_deathDeck.add("Sophie Robinson");
			}
			break;
		case 20:
			if(selectedOption == 1)
				prepareAddWindow();
			break;
		case 22:
			if(selectedOption == 1)
				_colony.remHelpless(1);
			break;
		case 24:
			if(selectedOption == 1)
				moveTo("Colony", "Brandon Kane");
			break;
		case 26:
			if(selectedOption == 1)
				moveTo("Grocery Store", "Forest Plum");
			break;
		case 28:
			if(selectedOption == 1)
				_colony.addHelpless(3);
			break;
		case 31:
			if(selectedOption == 2)
				_colony.remBaracade(_colony.getNumBar());
			break;
		case 36:
			if(selectedOption == 1) {
				_deathDeck.add("Bev Russell");
				_playerArray[_whosTurn].removeChar("Bev Russell");
			}
			break;
		case 45:
			if(selectedOption == 2)
				_playerArray[_whosTurn].removeChar(_char);
			break;
		case 47:
			if(selectedOption == 1) {
				for(int i=0; i < _ListLoc.size(); i++)
					_ListLoc.get(i).remZombies(1);
			}
			break;
		case 48:
			if(selectedOption == 1)
				_colony.addHelpless(numbPlayers);
			break;
		case 49:
			if(selectedOption == 3)
				_colony.remHelpless(1);
			break;
		case 50:
			if(selectedOption == 1)
				_colony.remHelpless(2);
			break;
		case 52:
			if(selectedOption == 1) {
				_deathDeck.add("Sophie Robinson");
				for(int i=0; i < numbPlayers; i++) {
					if(_playerArray[i].getCharList().contains("Sophie Robinson"))
						_playerArray[i].removeChar("Sophie Robinson");
				}
				for(int i=0; i < _ListLoc.size(); i++) {
					if(((Location)_ListLoc.get(i)).isThere(_char))
						((Location)_ListLoc.get(i)).remSurvivor(_char);
				}
			}
			break;
		case 53:
			if(selectedOption == 2)
				_colony.remHelpless(1);
			break;
		case 54:
			_colony.addZombies(5);
			if(selectedOption == 1)
				_colony.addHelpless(1);
			break;
		case 56:
			if(selectedOption == 1) {
				_playerArray[_whosTurn].addChar("John Price");
				_playerArray[_whosTurn].setCharPos("John Price", "Colony");
				_charDeck.remove("John Price");
				for(int i = 0; i < _ListLoc.size(); i++) {
					if(((Location)_ListLoc.get(i)).getName() != "School")
						((Location)_ListLoc.get(i)).addZombies(2);
				}
			}
			else if(selectedOption == 2) {
				_charDeck.remove("John Price");
				_deathDeck.add("John Price");
			}
			break;
		case 63:
			if(selectedOption == 1) {
				_colony.addHelpless(3);
				_playerArray[_whosTurn].addChar("Gabriel Diaz");
				_playerArray[_whosTurn].setCharPos("Gabriel Diaz","Colony");
				_charDeck.remove("Gabriel Diaz");
			}			
			break;
		case 65:
			if(selectedOption == 1) {
				for(int i = 0; i < _ListLoc.size(); i++) {
					if(((Location)_ListLoc.get(i)).getName() != "Colony")
						((Location)_ListLoc.get(i)).addZombies(1);
				}
			}
			else if(selectedOption == 2) {
				_playerArray[_whosTurn].removeChar("Annaleigh Chan");
				_deathDeck.add("Annaleigh Chan");
			}
			break;
		case 69:
			if(selectedOption == 2)
				_library.remZombies(1);
			break;
		case 70:
			if(selectedOption == 1)
				_colony.addZombies(5);
			break;
		}
		/*
		 if(onCard == 1 && selectedOption == 1 ) 
				moveTo("Colony", _char);
			else if(onCard == 3 && selectedOption == 2) 
				moveTo(_playerArray[_whosTurn].getCharStartPos(_char), _char);
			else if(onCard == 7 && selectedOption == 1)
				_colony.remZombies(8);
			else if(onCard == 9) {
				if(selectedOption == 1) {
					_hospital.remZombies(_hospital.getNumZom());
					_hospital.addBaracade(4);
				}
				else if(selectedOption == 1) {
					_groceryStore.remZombies(_groceryStore.getNumZom());
					_groceryStore.addBaracade(4);
				}
			}
			else if(onCard == 18 && selectedOption == 2) {
				_playerArray[_whosTurn].addChar("Grey Beard");
				_playerArray[_whosTurn].setCharPos("Grey Beard", "Colony");
				_charDeck.remove("Grey Beard");
				
			}
			else if(onCard == 19) {
				if(selectedOption == 1) {
					_charDeck.remove("Sophie Robinson");
					_playerArray[_whosTurn].addChar("Sophie Robinson");
					_playerArray[_whosTurn].setCharPos("Sophie Robinson", "Colony");
				}
				else if(selectedOption == 2) {
					_charDeck.remove("Sophie Robinson");
					_deathDeck.add("Sophie Robinson");
				}
			}
			else if(onCard == 20 && selectedOption == 1)
				prepareAddWindow();
			else if(onCard == 22 && selectedOption == 1)
				_colony.remHelpless(1);
			else if(onCard == 24) {
				if(selectedOption == 1)
					moveTo("Colony", "Brandon Kane");
			}
			else if(onCard == 26 && selectedOption == 1)
				moveTo("Grocery Store", "Forest Plum");
			else if(onCard == 28 && selectedOption == 1)
				_colony.addHelpless(3);
			else if(onCard == 31 && selectedOption == 2)
				_colony.remBaracade(_colony.getNumBar());
			else if(onCard == 36) {
				_deathDeck.add("Bev Russell");
				_playerArray[_whosTurn].removeChar("Bev Russell");
			}
			else if(onCard == 45 && selectedOption == 2)
				_playerArray[_whosTurn].removeChar(_char);
			else if(onCard == 47 && selectedOption == 1) {
				for(int i=0; i < _ListLoc.size(); i++)
					_ListLoc.get(i).remZombies(1);
			}
			else if(onCard == 48 && selectedOption == 1)
				_colony.addHelpless(numbPlayers);
			else if(onCard == 49 && selectedOption == 3) 
				_colony.remHelpless(1);
			else if(onCard == 50 && selectedOption == 1) 
				_colony.remHelpless(2);
			else if(onCard == 52) {
				if(selectedOption == 1) {
					_deathDeck.add("Sophie Robinson");
					for(int i=0; i < numbPlayers; i++) {
						if(_playerArray[i].getCharList().contains("Sophie Robinson"))
							_playerArray[i].removeChar("Sophie Robinson");
					}
					for(int i=0; i < _ListLoc.size(); i++) {
						if(((Location)_ListLoc.get(i)).isThere(_char))
							((Location)_ListLoc.get(i)).remSurvivor(_char);
					}
				}
			}
			else if(onCard == 53)
				_colony.remHelpless(1);
			else if(onCard == 54) {
				_colony.addZombies(5);
				if(selectedOption == 1)
					_colony.addHelpless(1);
			}
			else if(onCard == 56) {
				if(selectedOption == 1) {
					_playerArray[_whosTurn].addChar("John Price");
					_playerArray[_whosTurn].setCharPos("John Price", "Colony");
					_charDeck.remove("John Price");
					for(int i = 0; i < _ListLoc.size(); i++) {
						if(((Location)_ListLoc.get(i)).getName() != "School")
							((Location)_ListLoc.get(i)).addZombies(2);
					}
				}
				else if(selectedOption == 2) {
					_charDeck.remove("John Price");
					_deathDeck.add("John Price");
				}
			}
			else if(onCard == 63) {
				if(selectedOption == 1) {
					_colony.addHelpless(3);
					_playerArray[_whosTurn].addChar("Gabriel Diaz");
					_playerArray[_whosTurn].setCharPos("Gabriel Diaz","Colony");
					_charDeck.remove("Gabriel Diaz");

				}
			}
			else if(onCard == 65) {
				if(selectedOption == 1) {
					for(int i = 0; i < _ListLoc.size(); i++) {
						if(((Location)_ListLoc.get(i)).getName() != "Colony")
							((Location)_ListLoc.get(i)).addZombies(1);
					}
				}
				else if(selectedOption == 2) {
					_playerArray[_whosTurn].removeChar("Annaleigh Chan");
					_deathDeck.add("Annaleigh Chan");
				}
			}
			else if(onCard == 69 && selectedOption == 2)
				_library.remZombies(1);
			else if(onCard == 70 && selectedOption == 1)
				_colony.addZombies(5);

		  */
	}
	
	public void checkCard() {
		/******Check a specific Card*********/
		//_cardNumber = ;
		/************************************/
		if(!_crossroadDeck.alreadyDrawnThisRound()) {
			if(!_crossroadDeck.isTriggered(_cardNumber)) {
				switch(_cardNumber) {
				case 1: 
					if(_actionsSel.equals("Move") && _consi.equals("Fuel")) {
						moveTo("School", "Maria Lopez");
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					} break;
				case 2: // Resolve option for this card! Needs to check which player that should get it. 
					for(int i = 0 ; i < numbPlayers; i++) {
						if((i != _whosTurn) && (_playerArray[_whosTurn].getNumchars() > _playerArray[i].getNumchars()))
							_crossroadDeck.loadCardtoPanel(_cardNumber);
					}
					break;
				case 3:
					if(_actionsSel.equals("Move")  && !_consi.equals("Fuel"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break; 
				case 4:
					if(_actionsSel.equals("Use Medicine"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 5:
					if(_actionsSel.equals("Use Food"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 6:
					if(anyCharAtSpecificLocation("Grocery Store") && !_playerArray[_whosTurn].isPlayerExiled())
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 7:
					if(_playerArray[_whosTurn].controlsChar("Sparky") && isCharExiled("Sparky") == 0)
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 8:
					if(_playerArray[_whosTurn].controlsChar("Maria Lopez") && isCharExiled("Maria Lopez") == 0)
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 9:
					if(_playerArray[_whosTurn].controlsChar("Brian Lee") && isCharAtLocation("Brian Lee", "Colony"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 10:
					for(int i = 0; i < _ListLoc.size(); i++) {
						if(_ListLoc.get(i).getName() != "Colony") { 
							if(numberOfCharsPlayerControlls(whichPlayersContollAtThisLocation(_ListLoc.get(i).getSurvivors()), _whosTurn) > 1) {
								_crossroadDeck.loadCardtoPanel(_cardNumber);
								break;
							}
						}
					}
					break;
				case 11: 
					if(!_playerArray[_whosTurn].isPlayerExiled()) {
						for(int i=0; i < _currPlayerChars.size(); i++) {
							for(int j=0; j < _ListLoc.size(); j++) {
								if(isCharAtLocation(_currPlayerChars.get(i), _ListLoc.get(j).getName())){
									for(int k = 0; k < _ListLoc.get(j).getNumSurvivors(); k++){
										if(isCharExiled(_ListLoc.get(j).getSurvivors().get(k)) == 1) {
											_crossroadDeck.loadCardtoPanel(_cardNumber);
											break;
					}	}	}	}	}	}
					break;
				case 12:
					if(_maleName.contains(_LastAddedChar) && ((int)_numberofHelpless.getValue()) > 0)
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 13:
					if(_actionsSel.equals("Move"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 14: case 15:
					if(anyCharAtSpecificLocation("Colony"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 16:
					if(_currPlayerChars.contains("Talia Jones"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 17:
					if(_currPlayerChars.contains("Arthur Thurston") && isCharExiled("Arthur Thurston") == 0) {
						moveTo("School", "Arthur Thurston");
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					}
					break;
				case 18:
					if(_actionsSel.equals("Move") && _charDeck.contains("Grey Beard"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 19:
					if(_actionsSel.equals("Move") && _charDeck.contains("Sophie Robinson"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
				case 20:
					if(_actionsSel.equals("Search") && isCharExiled(_char)==0) {
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					}break;
				case 21:
					if(anyCharAtSpecificLocation("Colony"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 22:
					if(anyCharAtSpecificLocation("Colony") && _colony.getNumHelpless()>0)
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 23:
					if(!_charDeck.contains("Olivia Brown") && !_deathDeck.contains("Olivia Brown")) {
						moveTo("Colony", "Olivia Brown");
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					}
					break;
				case 24: // make a selection of how many wastes you add to the pile!
					if(!_charDeck.contains("Brandon Kane") && !_deathDeck.contains("Brandon Kane") && isCharExiled("Brandon Kane") == 0) {
						moveTo("Grocery Store", "Brandon Kane");
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					}
					break;
				case 25: 
					if(_actionsSel.equals("Use Attached Card"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 26:
					if(!_charDeck.contains("Forest Plum") && !_deathDeck.contains("Forest Plum"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 27: // Resolve card option 1!
					if(_currPlayerChars.contains("Daniel Smith") && _colony.getNumHelpless()>0) {
						_colony.remHelpless(1);
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					}
					break;
				case 28:
					if(_actionsSel.equals("Move") && isCharExiled(_char) == 0 && getLocationWithName(_loc).getNumSurvivors() <= 1)
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 29:
					if(_playerArray[_whosTurn].isPlayerExiled()) {
						System.out.println("The player is exiled!");
						for(int i = 0; i < _currPlayerChars.size(); i++){
							for(int j = 0; j < _ListLoc.size(); j++) {
								if(_ListLoc.get(j).getName().equals(_playerArray[_whosTurn].getCharPos(_currPlayerChars.get(i)))) {
									for(int k = 0; k < _ListLoc.get(i).getNumSurvivors(); k++) {
										if(isCharExiled(_ListLoc.get(j).getSurvivors().get(k)) == 0) {
											_crossroadDeck.loadCardtoPanel(_cardNumber);
											break;
					}	}	}	}	}	}
					break;
				case 30:
					if(_actionsSel.equals("Search") && isCharExiled(_char) == 0)
						_crossroadDeck.loadCardtoPanel(_cardNumber);
				case 31:
					if(anyCharAtSpecificLocation("Colony") && _colony.getNumZom()>0 && _colony.getNumBar()>0)
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 32:
				case 33:// Count number of added card to the waste pile ?
					if(anyCharAtSpecificLocation("Colony")) 
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 34: //Resolve card, that she cannot move to or from library?
					if(_currPlayerChars.contains("Alexis Grey") && isCharExiled("Alexis Grey") == 0) {
						moveTo("Library", "Alexis Grey");
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					}
					break;
				case 35:
					if(_currPlayerChars.contains("James Meyers") && isCharExiled("James Meyers") == 0)
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 36:
					if(_currPlayerChars.contains("Bev Russell")) {
						moveTo("School", "Bev Russell");
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					}
					break;
				case 37:
					for(int j = 0; j < _locations.length; j++) {
						for(int i = 0; i < _currPlayerChars.size(); i++) {
							if(!_locations[j].equals("Colony") && isCharAtLocation(_currPlayerChars.get(i), _locations[j])) {
								if(isCharExiled(_currPlayerChars.get(i)) == 0)
									_crossroadDeck.loadCardtoPanel(_cardNumber);
							}
						}
					}
					break;
				case 38: //Put card on char.
					if(anyCharAtSpecificLocation("Library"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 39: 
					if(_actionsSel.equals("Move") && isCharExiled(_char) == 0)
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 40:// Dont know how to do this! Maybe put another parameter on chars?
					break;
				case 41: // Fix the solvecard for this one! Dont really know how to do with the pick survivors!
					if(!_charDeck.contains("Rod Miller") && !_deathDeck.contains("Rod Miller"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 44: case 43: case 42: 
					if(anyCharAtSpecificLocation("Colony"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 45:
					if(_actionsSel.equals("Move") && _consi.equals("Frostbite")) {
						for(int i = 0; i < _ListLoc.size(); i++) {
							if(_ListLoc.get(i).getNumSurvivors()>0)
								_crossroadDeck.loadCardtoPanel(_cardNumber);
						}
					}
					break;
				case 46:
					if(anyCharAtSpecificLocation("Colony"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 47:
					if(_actionsSel.equals("Move") && isCharExiled(_char) == 0)
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 48:
					if(anyCharAtSpecificLocation("Colony"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 49:
					if(anyCharAtSpecificLocation("Colony") && (_colony.getNumSurvivors()<_colony.getNumHelpless()))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 50:
					if(anyCharAtSpecificLocation("Colony") && _colony.getNumHelpless()>=2) {
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					} break;
				case 51:
					if(anyCharAtSpecificLocation("Colony"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 52:
					if(_actionsSel.equals("Move") && !_charDeck.contains("Sophie Robinson") && !_deathDeck.contains("Sophie Robinson")) {
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					}
					break;
				case 53://Solve card option 1!
					if(_colony.getNumHelpless()>0) {
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					}
					break;
				case 54:
					if(wasteStatus==-1 && _colony.getNumHelpless()>0) 
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 55: // FIX RESOLVE CARD 55 FOR THIS ONE!!!!!
					if(!allCharAtSpecificLocation("Colony") && _charDeck.contains("Mike Cho")){
						for(int i = 0; i < _ListLoc.size(); i++){
							if(anyCharAtSpecificLocation(_ListLoc.get(i).getName()) && (_ListLoc.get(i).getNumZom() > 0) && (_ListLoc.get(i).getName()!="Colony"))
									_crossroadDeck.loadCardtoPanel(_cardNumber);		
						}
					}
					break;
				case 56:
					if(_actionsSel.equals("Search") && _currcharpos.equals("School") && _charDeck.contains("John Price"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 57:
					if(_playerArray[_whosTurn].controlsChar("Ashley Ross") && (_playerArray[_whosTurn].isExiled("Ashley Ross") == 0))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 58:
					if(_playerArray[_whosTurn].controlsChar("Edward White") && (_playerArray[_whosTurn].isExiled("Edward White") == 0))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 59:
					if(_actionsSel.equals("Search") && _currcharpos.equals("Police Station"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 60: // Yawn Card Replace with a new one, for now it will randomize a new number.
					_cardNumber = rand.nextInt(_crossroadDeck.getNumberofCards()-1)+1;
					checkCard();
					break;
				case 61:
					if(_playerArray[_whosTurn].controlsChar("Buddy Davis") && (_playerArray[_whosTurn].getCharStartPos("Buddy Davis") == "Colony"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 62: 
					if(!_charDeck.contains("Edward White") && !_charDeck.contains("John Price") && !_deathDeck.contains("Edward White") && !_deathDeck.contains("John Price"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 63: 
					if(!_playerArray[_whosTurn].isPlayerExiled() && !allCharAtSpecificLocation("Colony") && _charDeck.contains("Gabriel Diaz"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break; 
				case 64:
					if(_playerArray[_whosTurn].controlsChar("Andrew Evans") && isCharExiled("Andrew Evans") == 0) {
						moveTo("Grocery Store", "Andrew Evans");
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					} break;
				case 65:
					if(_playerArray[_whosTurn].controlsChar("Annaleigh Chan") && isCharExiled("Annaleigh Chan") == 0)
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 66:
					if(_playerArray[_whosTurn].controlsChar("David Garcia") && isCharExiled("David Garcia") == 0) {
						moveTo("Colony", "David Garcia");
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					} break;
				case 67:
					if(!allCharAtSpecificLocation("Colony"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 68:
					if(_actionsSel.equals("Move"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 69:
					if(_actionsSel.equals("Search") && _currcharpos.equals("Library") && isCharExiled(_char) == 0)
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 70:
					if(_actionsSel.equals("Search") && _currcharpos.equals("Hospital"))
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					break;
				case 71:
					if(_playerArray[_whosTurn].controlsChar("Janet Taylor") && (isCharExiled("Janet Taylor")==0)) {
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					} break;				
				case 72:
					if(anyCharAtSpecificLocation("Colony")) {
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					} break;
				case 73:
					for(int i = 0; i < _ListLoc.size(); i++) {
						if(_ListLoc.get(i).getNumSurvivors() == 2) {
							if(numberOfCharsPlayerControlls(
													whichPlayersContollAtThisLocation(_ListLoc.get(i).getSurvivors())
													, _whosTurn) 
													== 1) {
								_crossroadDeck.loadCardtoPanel(_cardNumber);
								break;
							}
						}
					}
					break;
				case 74:
					if(_actionsSel.equals("Search") && _loc.equals("Gas Station")) {
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					} break;
				case 75:
					if(_playerArray[_whosTurn].controlsChar("Carla Thompson") && (isCharExiled("Carla Thompson")==0)) {
						moveTo("Police Station", "Carla Thompson");
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					} break;
				case 76:
					if(_actionsSel.equals("Search") && _char.equals("Jenny Clark")) {
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					} break;
				case 77:
					if(_actionsSel.equals("Move") && !_consi.equals("Wound") && !_consi.equals("Zombiebite")) {
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					} break;
				case 78:
					if(_playerArray[_whosTurn].controlsChar("Loretta Clay") && (isCharExiled("Loretta Clay")==0)) {
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					} break;
				case 79:
					if(_actionsSel.equals("Move") && isCharExiled(_char)==0) {
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					} break;
				case 80:
					if(!_charDeck.contains("Harman Brooks") && (isCharExiled("Harman Brooks")==0)) {
						_crossroadDeck.loadCardtoPanel(_cardNumber);
					} break;
				default: 
					System.out.println("Card " + _cardNumber + " does not exist!");
					break;
				}
			}
			else {
				_cardNumber = rand.nextInt(_crossroadDeck.getNumberofCards()-1)+1;
				checkCard();
			}
		}
	}
}
