import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import bsh.Interpreter;

public class Crossroads extends JFrame implements ActionListener{
	private int[] _triggerStatus; // 1: Already triggered, 0: Not triggered.
	private CSVParser collectCardsFromFile;
	private int numberOfCards;
	//private ArrayList<String[]> DrawDeckofCrossroadCards;
	private JPanel window;
	private JPanel topPanel;
	private JPanel centerPanel;
	private JPanel buttonPanel;
	private JLabel _cardTitle;
	private JTextArea _infoField;
	private String _currTitle; //Title of the card
	private String _currCond; //Trigger condition
	private String _currSetup; //Prolog
	private String _Opt1S; //Short text of the option
	private String _Opt2S; //Short text of the option
	private String _Opt3S; // Short text of the option
	private String _Opt1F; // full text of the option
	private String _Opt2F; // full text of the option
	private String _Opt3F; // full text of the option
	private Player[] _playerArray;
//	private Colony colony;
//	private Location gas;
//	private Location school;
//	private Location police;
//	private Location groc;
//	private Location lib;
//	private Location hosp;
	private ArrayList<Location> _ListLoc;
//	private String _Loc;
//	private String _currChar;
	protected JButton _Con;
	private int _whosTurn;
	private int _selectedOption;

	private JButton _optB_1;
	private JButton _optB_2;
	private JButton _optB_3;
	
	public Crossroads(Player _pArray[], ArrayList<Location> _lLoc) {
		_ListLoc = _lLoc;
		collectCardsFromFile = new CSVParser();
		collectCardsFromFile.ParseCSVFileWithCards();
		numberOfCards = collectCardsFromFile.getNumberOfCards()-1;
		_triggerStatus = new int[numberOfCards];
		for(int i=0; i < _triggerStatus.length; i++)
			_triggerStatus[i] = 0;
		this.setLocationRelativeTo(null);
		this.setSize(700,500); 
		this.setTitle("Crossroadcard");
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		topPanel = new JPanel();
		_infoField = new JTextArea();
		_infoField.setLineWrap(true);
		_infoField.setWrapStyleWord(true);
		//_infoField.setContentType("text/html");
		_infoField.setFont(new Font("Arial", Font.PLAIN, 15));
		_infoField.setSize(new Dimension(650, 450));
		_infoField.setOpaque(false);
		_infoField.setEditable(false);
		centerPanel = new JPanel();
		buttonPanel = new JPanel();
		_cardTitle = new JLabel();
		centerPanel.add(_infoField);
		topPanel.add(_cardTitle);
		this.add(topPanel, BorderLayout.PAGE_START);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.PAGE_END);
		_optB_1 = new JButton("Option 1");
		_optB_2 = new JButton("Option 2");

		//Adding all listeners!
		_optB_1.addActionListener(this);
		_optB_2.addActionListener(this);

		_Con = new JButton("Continue");
		_Con.addActionListener(this);
	}
//	public void setLocation(Colony _col, Location po, Location gr, Location li, 
//			Location sc, Location ho, Location ga) {
//		colony = _col;
//		police = po;
//		groc = gr;
//		lib = li;
//		school = sc;
//		hosp = ho;
//		gas = ga;
//	}
	public int getNumberofCards() {
		return numberOfCards;
	}
	public boolean isTriggered(int index) {
		if(_triggerStatus[index] == 0)
			return false;
		else
			return true;
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
	
	private void repaintBPanel() {
		buttonPanel.removeAll();
		buttonPanel.add(_Con);
		buttonPanel.repaint();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source.equals(_optB_1)) {
			_infoField.setText("Option 1: \n"+ _Opt1F);
			_selectedOption = 1;
			repaintBPanel();
		}
		else if(source.equals(_optB_2)) {
			_infoField.setText("Option 2: \n"+ _Opt2F);
			_selectedOption = 2;
			repaintBPanel();
		}
		else if(source.equals(_optB_3)) {
			_infoField.setText("Option 3: \n"+ _Opt3F);
			_selectedOption = 3;
			repaintBPanel();
		}
		else if(source.equals(_Con)) {
			System.out.println("Crossroadcard is done.");
			this.setVisible(false);
		}
//		if(source.equals(_optB1_1)) {
//			_infoField.setText("Option 1: <br>"+ _Opt1F);
//			moveTo("Colony", _currChar);
//			repaintBPanel();
//		}
//		else if(source.equals(_optB1_2)) {
//			_infoField.setText("Option 2: <br>"+ _Opt2F);
//			moveTo(_Loc, _currChar);
//			repaintBPanel();
//		}

	}
	public int getSelected() {
		return _selectedOption;
	}

	public void loadCardtoPanel(int whichCard) {
		String completeCardText;
		_selectedOption = 0;
		_triggerStatus[whichCard] = 1;
		_cardTitle.setText(collectCardsFromFile.getTitle(whichCard)); //Title of the cards
		_currCond = collectCardsFromFile.getCondition(whichCard) + "\n"; //Trigger Condition for the card
		_currSetup = collectCardsFromFile.getSetup(whichCard) + "\n"; //Setup/intro text for the card
		_Opt1S = collectCardsFromFile.getOptionOneShort(whichCard); //Option 1 Short
		_Opt2S = collectCardsFromFile.getOptionTwoShort(whichCard); //Option 2 short
		//_Opt3S = ""; //OPtion 3 short
		_Opt1F = collectCardsFromFile.getOptionOne(whichCard); //Option 1 Full
		_Opt2F = collectCardsFromFile.getOptionTwo(whichCard); //Option 2 FUll
		_Opt3F = collectCardsFromFile.getOptionThree(whichCard); //Option 3 FUll
		completeCardText = _currCond + _currSetup;
		if(!_Opt1F.isEmpty()) {
			completeCardText = completeCardText + "\nOption 1:\n" + _Opt1S;
			buttonPanel.add(_optB_1);
		}
		if(!_Opt2F.isEmpty()) {
			completeCardText = completeCardText + "\n\nOption 2:\n" + _Opt2S;
			buttonPanel.add(_optB_2);
		}
		if(!_Opt3F.isEmpty()) {
			completeCardText = completeCardText + "\n\nOption 3:\n" + _Opt3S;
			buttonPanel.add(_optB_3);
		}
		_infoField.setText(completeCardText);
		this.setVisible(true);
	}																									// |
}
