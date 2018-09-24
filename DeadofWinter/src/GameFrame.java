import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.ButtonPeer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

public class GameFrame extends JFrame implements ActionListener {
/************************************************************
*			           Declares/initialization              *
*************************************************************/	
	private JPanel _cardPanel;
	private CardLayout _deck;
	private int numbPlayers;
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
	private JSpinner _numberof;
	private JLabel _player;
	private Character[] _playerChars;
	private JTextArea _infoChar;
	private JTextArea _infoColony;
	private CardLayout _maindeck;
	private ArrayList<String> _currPlayerChar;

	private Location _gasStation;
	private Location _hospital;
	private Location _school;
	private Location _library;
	private Location _groceryStore;
	private Location _policeStation;
	private Colony _colony;
	private ArrayList<Location> _ListLoc;
	private ArrayList<String> _charDeck;
	
	private String _Poscharinfo;
	private String _currcharpos;
	private String _newcharpos;
	private Player[] _playerArray;
	private int numPchars;
	
	private JPanel _boxPanel;
	//Move Frame
	private JPanel _dropPanel;
	private JPanel _performPanel;
	private JButton _perform;
	private String[] _actionsString = {"Search","Move", "Use Weapon", "Use Food", "Use Medicine"};
	private String[] _locations = {"Colony", "Police Station", "School", "Library", "Hospital", "Gas Station", "Grocery Store"};
	private String[] _result = {"Nothing", "Fuel", "Frostbite", "Wound", "Zombiebite"};
	private JComboBox _locationDrop;
	private JComboBox _resultDrop;
	private JComboBox _actionsDrop;
	
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
	private String _actionsSel = "";
	private String _char = "";
	private String _loc = "";
	private String _consi = "";
	
	//Add/remove-panel
	private final static int ADD = 1;
	private final static int REMOVE = 0;
	private int _AddorRem = -1;
	private JButton _add_rem;
	private JLabel _add_rem_label;

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
		_charBox = new Checkbox[labels.length]; //Ta bort om övre blir insatt.
		_charString = labels;
		_charDeck = new ArrayList(Arrays.asList(deck));
		
		
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
	//	_playerInfo.setBorder(new EmptyBorder(10,10,10,10));
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
		_numberof = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
		_addremChar.add(_addChar);
		_addremChar.add(_remChar);
		_nextTurn = new JButton("Next Turn");
		_waste.add(_addWaste);
		_waste.add(_clearWastePanel);
		_clearWastePanel.add(_clearWaste);
		_clearWastePanel.add((Component) _numberof);
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
		
		getInfo();
		placeAllChars();

		_mainCard.add(_actions1, "action1");
		_mainFrame.add(_name, BorderLayout.PAGE_START);
		_mainFrame.add(_mainCard, BorderLayout.WEST);
		_mainFrame.add(_info, BorderLayout.EAST);
		_mainFrame.add(_colPanel, BorderLayout.CENTER);
		
		//Radio panel
		_boxPanel = new JPanel(new GridLayout(8,4));
		_boxPanel.setAlignmentY(JComponent.LEFT_ALIGNMENT);
		_locationDrop = new JComboBox(_locations);
		_resultDrop = new JComboBox(_result);
		_actionsDrop = new JComboBox(_actionsString);
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
		_add_rem = new JButton();
		_add_rem_label = new JLabel();
		_addremPanel = new JPanel(new BorderLayout());
		_addremPanel.add(_add_rem_label, BorderLayout.PAGE_START);
		_addremPanel.add(_add_rem, BorderLayout.PAGE_END);
		
		//Crossroaddeck and random draw.
		_crossroadDeck = new Crossroads(_playerArray, _ListLoc);
		_crossroadDeck.setAlwaysOnTop(true);
		rand = new Random();
		_cardNumber = rand.nextInt(82) + 1;
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
	}
	public int isCharExiled(String _charName) {
		int status = -1;
		System.out.println(_charName);
		for(int i=0; i < numbPlayers; i++) {
			status = _playerArray[i].isExiled(_charName);
		}
		return status;
	}
	public boolean isCharAtLocation(String _charName, String _locName) {
		if(_playerArray[_whosTurn].getCharPos(_charName).equals(_locName))
			return true;
		return false;
	}	
	public boolean isCharAtLocation(String _locName) {
		ArrayList charNames = _playerArray[_whosTurn].getCharList();
		for(int i = 0; i < charNames.size(); i++)
			if(_playerArray[_whosTurn].getCharPos(charNames.get(i).toString()).equals(_locName))
				return true;
		return false;
	}
	public void checkboxes() {
		boxGroup = new CheckboxGroup();
		for(int i=0; i<_charString.length; i++) {
			_charBox[i] = new Checkbox(_charString[i], boxGroup, true);
			_boxPanel.add(_charBox[i], boxGroup);
		}
	}
	
	public void getInfo() {
		_currPlayerChar = _playerArray[_whosTurn].getCharList();
		_infoChar.setText("");
		_Poscharinfo = "";
		numPchars = _playerArray[_whosTurn].getNumchars();
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
				"\n\nBaracades: " + _colony.getNumBar() + "\n\nRoundTracker: " + _round);
		
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
		for(int i=0; i < _ListLoc.size(); i++) {
			if(((Location) _ListLoc.get(i)).isThere(_chartoMove))
				((Location) _ListLoc.get(i)).remSurvivor(_chartoMove);
			else if(_ListLoc.get(i).getName().equals(_newLoc)) {
				_ListLoc.get(i).addSurvivor(_chartoMove);
			}
				
		}
		_playerArray[_whosTurn].setCharPos(_chartoMove, _newLoc);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source= arg0.getSource();
		if(source.equals(_actionsButton)) {
			_performPanel.add(_boxPanel, BorderLayout.CENTER);
			for(int i=0; i < _charBox.length; i++) {
				if(_currPlayerChar.contains(_charBox[i].getLabel())) {
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
			checkCard();
			
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
				else if(error_cancel == 1 && _Errorlable.getParent().equals(_dropPanel)) {
					error_cancel = 0;
					_dropPanel.remove(_Errorlable);
				}
			}
			else if(_actionsSel.equals("Use Weapon")) {
				useWeaponIfPossible();
			}
			else if(_actionsSel.equals("Move")) {
				moveIfPossible();
			}
			else if(_actionsSel.equals("Use Food")) {
				//Ska man ens göra något här? 
				
			}
			else if(_actionsSel.equals("Use Medicine")) {
				//Ska man ens göra något här? 
			}
			if(error_cancel == 0)
				_deck.show(_cardPanel, "main");
			error_cancel = 0; 
			_dropPanel.repaint();
		}
		else if(source.equals(_clearWaste)) {
			_colony.remWaste((int)_numberof.getValue());
		}
		else if(source.equals(_addWaste)) {
			_colony.addWaste();
		}
		else if(source.equals(_addChar)) {
			_AddorRem = ADD;
			_addremPanel.add(_boxPanel, BorderLayout.CENTER);
			_add_rem.setText("Add Character");
			_add_rem_label.setText("Check which character to add");
			for(int i=0; i < _charBox.length; i++) {
				if(_charDeck.contains(_charBox[i].getLabel()))
						_charBox[i].setEnabled(true);
				else
					_charBox[i].setEnabled(false);
			}
			_deck.show(_cardPanel, "addrem");
			
		}
		else if(source.equals(_remChar)) {
			_AddorRem = REMOVE;
			_addremPanel.add(_boxPanel, BorderLayout.CENTER);
			_add_rem.setText("Remove Character");
			_add_rem_label.setText("Check which character to remove");
			for(int i=0; i < _charBox.length; i++) {
				if(_currPlayerChar.contains(_charBox[i].getLabel()))
						_charBox[i].setEnabled(true);
				else
					_charBox[i].setEnabled(false);
			}
			_deck.show(_cardPanel, "addrem");
		}
		else if(source.equals(_add_rem)) {
			if(_AddorRem == REMOVE) {
				String _char = boxGroup.getSelectedCheckbox().getLabel();
				System.out.println(_char);
				for(int i=0; i < _ListLoc.size(); i++) {
					if(((Location) _ListLoc.get(i)).isThere(_char))
						((Location) _ListLoc.get(i)).remSurvivor(_char);
				}
			}
			else if(_AddorRem == ADD) {
				String _char = boxGroup.getSelectedCheckbox().getLabel();
				_playerArray[_whosTurn].addChar(_char);
				_playerArray[_whosTurn].setCharPos(_char, "Colony");
				_charDeck.remove(_char);
				_colony.addSurvivor(_char);
			}
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
			}
			_cardNumber = rand.nextInt(82) + 1;
			resetVariables();
			checkCard();
		}
		else if(source.equals(_crossroadDeck._Con)) {
			resolveCardOption(_cardNumber);
			getInfo();
		}
		
		getInfo();
		checkCard();
	}

	private void useWeaponIfPossible() {
		if(!_loc.equals(_currcharpos)) {
			error_cancel = 1;
			_Errorlable.setText(_actionsSel + _Errortext);
			_dropPanel.add(_Errorlable);
		}
		else {
			if(error_cancel == 1 && _Errorlable.getParent().equals(_dropPanel)) 
				_dropPanel.remove(_Errorlable);
			for(int i=0; i < _ListLoc.size(); i++) {
				if(_ListLoc.get(i).getName().equals(_loc)){
					_ListLoc.get(i).remZombies(1);
					}
				// Lägga till så att man kan ändra antalet Zombies man dödar?! 
			}
		}
	}

	private void moveIfPossible() {
		if(_loc.equals(_currcharpos)) {
			error_cancel = 1;
			_Errorlable.setText(_actionsSel + _ErrorText_moveSameLoc);
			_dropPanel.add(_Errorlable);
		}
		else if(error_cancel == 1 && _Errorlable.getParent().equals(_dropPanel)) 
			_dropPanel.remove(_Errorlable);
		else
			moveTo(_loc, _char);
	}
	
	private void resolveCardOption(int cardNumber) {
		int selectedOption = _crossroadDeck.getSelected();
		
	}
	
	public void checkCard() {
		/**************************/
		_cardNumber = 1; // KOM IHÅG ATT TA BORT DENNA!
		_cardNumber = rand.nextInt(82) + 1;
		/****************************/

		if(!_crossroadDeck.isTriggered(_cardNumber)) {
			_crossroadDeck.loadCardtoPanel(_cardNumber);
			switch(_cardNumber) {
//			case 1: 
//				if(_actionsSel.equals("Move") && _consi.equals("Fuel")) {
//					_crossroadDeck.card1(_loc, _char);
//				} break;
//				
//			case 71:
//				if(_playerArray[_whosTurn].controlsChar("Janet Taylor") && (isCharExiled("Janet Taylor")==0)) {
//					System.out.println("Triggered!");
//					//_crossroadDeck.card71();
//				} break;				
//			case 72:
//				if(isCharAtLocation("Colony")) {
//					_crossroadDeck.card72();
//				} break;
//			/*case 73:
//				if() {
//					_crossroadDeck.card73();
//				} break;*/ //Fix a player count on a location
//			case 74:
//				if(_actionsSel.equals("Search") && _loc.equals("Gas Station")) {
//					_crossroadDeck.card74();
//				} break;
//			case 75:
//				if(_playerArray[_whosTurn].controlsChar("Carla Thompson") && (isCharExiled("Carla Thompson")==0)) {
//					moveTo("Police Station", "Carla Thompson");
//					_crossroadDeck.card75();
//				} break;
//			case 76:
//				if(_actionsSel.equals("Search") && _char.equals("Jenny Clark")) {
//					_crossroadDeck.card76();
//				} break;
//			case 77:
//				if(_actionsSel.equals("Move") && !_consi.equals("Wound") && !_consi.equals("Zombiebite")) {
//					_crossroadDeck.card77();
//				} break;
//			case 78:
//				if(_playerArray[_whosTurn].controlsChar("Loretta Clay") && (isCharExiled("Loretta Clay")==0)) {
//					_crossroadDeck.card78();
//				} break;
//			case 79:
//				if(_actionsSel.equals("Move") && isCharExiled(_char)==0) {
//					_crossroadDeck.card79();
//				} break;
//			case 80:
//				if(!_charDeck.contains("Harman Brooks") && (isCharExiled("Harman Brooks")==0)) {
//					_crossroadDeck.card80();
//				} break;
			}
		}
		/*else { // Uncomment this as all crossroadcards are finished!
			_cardNumber = rand.nextInt(82) + 1;
			checkCard();
		}*/
	}
}
