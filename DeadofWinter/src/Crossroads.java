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
import javax.swing.JTextPane;
import bsh.Interpreter;

public class Crossroads extends JFrame implements ActionListener{
	int _numberOfCards;
	int[] _triggerStatus; // 1: Is already triggered, 0: Not triggered.
	private JPanel window;
	private JPanel topPanel;
	private JPanel centerPanel;
	private JPanel buttonPanel;
	private JLabel _cardTitle;
	private JTextPane _infoField;
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
	private Colony colony;
	private Location gas;
	private Location school;
	private Location police;
	private Location groc;
	private Location lib;
	private Location hosp;
	private ArrayList<Location> _ListLoc;
	private String _Loc;
	private String _currChar;
	protected JButton _Con;
	private int _whosTurn;
	//Card1
	private JButton _optB_1;
	private JButton _optB_2;
//	private JButton _optB1_1;
//	private JButton _optB1_2;
//
//	private JButton _optB71_1;
//	private JButton _optB71_2;
//	private JButton _optB72_1;
//	private JButton _optB72_2;
//	private JButton _optB73_1;
//	private JButton _optB73_2;
//	private JButton _optB74_1;
//	private JButton _optB74_2;
//	private JButton _optB75_1;
//	private JButton _optB75_2;
//	private JButton _optB76_1;
//	private JButton _optB76_2;
//	private JButton _optB77_1;
//	private JButton _optB77_2;
//	private JButton _optB78_1;
//	private JButton _optB78_2;
//	private JButton _optB79_1;
//	private JButton _optB79_2;
//	private JButton _optB80_1;
//	private JButton _optB80_2;
	//Card2 
	
	
	private String code1;
	private String code2;
	private String code3;
	private Interpreter inter;
	
	public Crossroads(Player _pArray[], ArrayList<Location> _lLoc, int playTurn) {
		_ListLoc = _lLoc;
		_whosTurn = playTurn;
		_playerArray = _pArray;
		_numberOfCards = 83; //Number of cards + 1; 
		_triggerStatus = new int[_numberOfCards];
		for(int i=0; i < _triggerStatus.length; i++)
			_triggerStatus[i] = 0;
		this.setLocationRelativeTo(null);
		this.setSize(700,500); 
		this.setTitle("Crossroadcard");
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		inter = new Interpreter();
		topPanel = new JPanel();
		_infoField = new JTextPane();
		_infoField.setContentType("text/html");
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
//		_optB71_1 = new JButton("Option 1");
//		_optB71_2 = new JButton("Option 2");
//		_optB72_1 = new JButton("Option 1");
//		_optB72_2 = new JButton("Option 2");
//		_optB73_1 = new JButton("Option 1");
//		_optB73_2 = new JButton("Option 2");
//		_optB74_1 = new JButton("Option 1");
//		_optB74_2 = new JButton("Option 2");
//		_optB75_1 = new JButton("Option 1");
//		_optB75_2 = new JButton("Option 2");
//		_optB76_1 = new JButton("Option 1");
//		_optB76_2 = new JButton("Option 2");
//		_optB77_1 = new JButton("Option 1");
//		_optB77_2 = new JButton("Option 2");
//		_optB78_1 = new JButton("Option 1");
//		_optB78_2 = new JButton("Option 2");
//		_optB79_1 = new JButton("Option 1");
//		_optB79_2 = new JButton("Option 2");
//		_optB80_1 = new JButton("Option 1");
//		_optB80_2 = new JButton("Option 2");
		//Adding all listeners!
		_optB_1.addActionListener(this);
		_optB_2.addActionListener(this);
//		_optB1_1.addActionListener(this);
//		_optB1_2.addActionListener(this);
//
//		_optB71_1.addActionListener(this);
//		_optB71_2.addActionListener(this);
//		_optB72_1.addActionListener(this);
//		_optB72_2.addActionListener(this);
//		_optB73_1.addActionListener(this);
//		_optB73_2.addActionListener(this);
//		_optB74_1.addActionListener(this);
//		_optB74_2.addActionListener(this);
//		_optB75_1.addActionListener(this);
//		_optB75_2.addActionListener(this);
//		_optB76_1.addActionListener(this);
//		_optB76_2.addActionListener(this);
//		_optB77_1.addActionListener(this);
//		_optB77_2.addActionListener(this);
//		_optB78_1.addActionListener(this);
//		_optB78_2.addActionListener(this);
//		_optB79_1.addActionListener(this);
//		_optB79_2.addActionListener(this);
//		_optB80_1.addActionListener(this);
//		_optB80_2.addActionListener(this);
		_Con = new JButton("Continue");
		_Con.addActionListener(this);
	}
	public void setLocation(Colony _col, Location po, Location gr, Location li, 
			Location sc, Location ho, Location ga) {
		colony = _col;
		police = po;
		groc = gr;
		lib = li;
		school = sc;
		hosp = ho;
		gas = ga;
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
	
	public void repaintBPanel() {
		buttonPanel.removeAll();
		buttonPanel.add(_Con);
		buttonPanel.repaint();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source.equals(_optB_1)) {
			_infoField.setText("Option 1: <br>"+ _Opt1F);
			repaintBPanel();
		}
		else if(source.equals(_optB_2)) {
			_infoField.setText("Option 2: <br>"+ _Opt2F);
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
	
	
	public void card80() {
		this.setVisible(true);
		//_triggerStatus[Enter right number] = 1;
		_currTitle = ""; //Title of the cards
		_currCond = "<br>"; //Trigger Condition for the card
		_currSetup = ""; //Setup/intro text for the card
		_Opt1S = ""; //Option 1 Short
		_Opt2S = ""; //Option 2 short
		_Opt3S = ""; //OPtion 3 short
		_Opt1F = ""; //Option 1 Full
		_Opt2F = ""; //Option 2 FUll
		_Opt3F = ""; //Option 3 FUll
		_cardTitle.setText(_currTitle);
		_infoField.setText(_currCond + _currSetup + "<br><br>Option 1: <br>" + _Opt1S + 
		"<br><br>Option 2:<br>" + _Opt2S);
		//buttonPanel.add(arg0);
		//buttonPanel.add(comp);
	}																									// |
		
}
