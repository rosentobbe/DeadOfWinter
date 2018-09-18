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
	private JButton _optB1_1;
	private JButton _optB1_2;

	private JButton _optB71_1;
	private JButton _optB71_2;
	private JButton _optB72_1;
	private JButton _optB72_2;
	private JButton _optB73_1;
	private JButton _optB73_2;
	private JButton _optB74_1;
	private JButton _optB74_2;
	private JButton _optB75_1;
	private JButton _optB75_2;
	private JButton _optB76_1;
	private JButton _optB76_2;
	private JButton _optB77_1;
	private JButton _optB77_2;
	private JButton _optB78_1;
	private JButton _optB78_2;
	private JButton _optB79_1;
	private JButton _optB79_2;
	private JButton _optB80_1;
	private JButton _optB80_2;
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
		_optB_1 = new JButton();
		_optB_2 = new JButton();
		_optB1_1 = new JButton("Option 1");
		_optB1_2 = new JButton("Option 2");

		_optB71_1 = new JButton("Option 1");
		_optB71_2 = new JButton("Option 2");
		_optB72_1 = new JButton("Option 1");
		_optB72_2 = new JButton("Option 2");
		_optB73_1 = new JButton("Option 1");
		_optB73_2 = new JButton("Option 2");
		_optB74_1 = new JButton("Option 1");
		_optB74_2 = new JButton("Option 2");
		_optB75_1 = new JButton("Option 1");
		_optB75_2 = new JButton("Option 2");
		_optB76_1 = new JButton("Option 1");
		_optB76_2 = new JButton("Option 2");
		_optB77_1 = new JButton("Option 1");
		_optB77_2 = new JButton("Option 2");
		_optB78_1 = new JButton("Option 1");
		_optB78_2 = new JButton("Option 2");
		_optB79_1 = new JButton("Option 1");
		_optB79_2 = new JButton("Option 2");
		_optB80_1 = new JButton("Option 1");
		_optB80_2 = new JButton("Option 2");
		//Adding all listeners!
		_optB_1.addActionListener(this);
		_optB_2.addActionListener(this);
		_optB1_1.addActionListener(this);
		_optB1_2.addActionListener(this);

		_optB71_1.addActionListener(this);
		_optB71_2.addActionListener(this);
		_optB72_1.addActionListener(this);
		_optB72_2.addActionListener(this);
		_optB73_1.addActionListener(this);
		_optB73_2.addActionListener(this);
		_optB74_1.addActionListener(this);
		_optB74_2.addActionListener(this);
		_optB75_1.addActionListener(this);
		_optB75_2.addActionListener(this);
		_optB76_1.addActionListener(this);
		_optB76_2.addActionListener(this);
		_optB77_1.addActionListener(this);
		_optB77_2.addActionListener(this);
		_optB78_1.addActionListener(this);
		_optB78_2.addActionListener(this);
		_optB79_1.addActionListener(this);
		_optB79_2.addActionListener(this);
		_optB80_1.addActionListener(this);
		_optB80_2.addActionListener(this);
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
		if(source.equals(_optB1_1)) {
			_infoField.setText("Option 1: <br>"+ _Opt1F);
			moveTo("Colony", _currChar);
			repaintBPanel();
		}
		else if(source.equals(_optB1_2)) {
			_infoField.setText("Option 2: <br>"+ _Opt2F);
			moveTo(_Loc, _currChar);
			repaintBPanel();
		}

		else if(source.equals(_optB72_1)) {
			_infoField.setText("Option 1: <br>"+ _Opt1F);
			repaintBPanel();
		}
		else if(source.equals(_optB72_2)) {
			_infoField.setText("Option 2: <br>"+ _Opt2F);
			repaintBPanel();
		}		
		else if(source.equals(_optB73_1)) {
			_infoField.setText("Option 1: <br>"+ _Opt1F);
			repaintBPanel();
		}
		else if(source.equals(_optB73_2)) {
			_infoField.setText("Option 2: <br>"+ _Opt2F);
			repaintBPanel();
		}		
		else if(source.equals(_optB74_1)) {
			_infoField.setText("Option 1: <br>"+ _Opt1F);
			repaintBPanel();
		}
		else if(source.equals(_optB74_2)) {
			_infoField.setText("Option 2: <br>"+ _Opt2F);
			repaintBPanel();
		}
		else if(source.equals(_optB75_1)) {
			_infoField.setText("Option 1: <br>"+ _Opt1F);
			repaintBPanel();
		}
		else if(source.equals(_optB75_2)) {
			_infoField.setText("Option 2: <br>"+ _Opt2F);
			repaintBPanel();
		}
		else if(source.equals(_optB76_1)) {
			_infoField.setText("Option 1: <br>"+ _Opt1F);
			repaintBPanel();
		}
		else if(source.equals(_optB76_2)) {
			_infoField.setText("Option 2: <br>"+ _Opt2F);
			repaintBPanel();
		}
		else if(source.equals(_optB77_1)) {
			_infoField.setText("Option 1: <br>"+ _Opt1F);
			repaintBPanel();
		}
		else if(source.equals(_optB77_2)) {
			_infoField.setText("Option 2: <br>"+ _Opt2F);
			repaintBPanel();
		}
		else if(source.equals(_optB78_1)) {
			_infoField.setText("Option 1: <br>"+ _Opt1F);
			repaintBPanel();
		}
		else if(source.equals(_optB78_2)) {
			_infoField.setText("Option 2: <br>"+ _Opt2F);
			repaintBPanel();
		}
		else if(source.equals(_optB79_1)) {
			_infoField.setText("Option 1: <br>"+ _Opt1F);
			repaintBPanel();
		}
		else if(source.equals(_optB79_2)) {
			_infoField.setText("Option 2: <br>"+ _Opt2F);
			repaintBPanel();
		}
		
		else if(source.equals(_optB80_1)) {
			_infoField.setText("Option 1: <br>"+ _Opt1F);
			repaintBPanel();
		}
		else if(source.equals(_optB80_2)) {
			_infoField.setText("Option 2: <br>"+ _Opt2F);
			repaintBPanel();
		}
		
		
		else if(source.equals(_Con)) {
			System.out.println("Crossroadcard is done.");
			this.setVisible(false);
		}
	}
	
	public void card1(String location, String _char) {
		this.setVisible(true);
		_triggerStatus[1] = 1;
		_Loc = location;
		_currChar = _char;
		_currTitle = "Car Crash";
		_currCond = "If player uses a fuel when moving a non-exiled survivor:";
		_currSetup = "<br>Your headlights cut trough the darkness, random snowflakes scuttling by on a bitter wind."
				+ "<br>The car is nowhere near as warm as you want, but every drop of fuel is precious."
				+ "<br>When your windshield begins to fog, you reach down and fumble at the dash for the heat. "
				+ "<br>Suddenly a child walks into your oncoming lights. "
				+ "<br>Instinct takes over before your brain registers that the child is dead, and you jerk hard on the wheel. "
				+ "<br>Wheels screech as your car tumbles off the side of the road. "
				+ "<br>Time slows as the world swirls sickeningly around you.";
		_Opt1S = "Wait in the wrecked car untill the colony rescues you";
		_Opt2S = "Continue walking towards destination.";
		_Opt1F = "Wait in the wrecked car for the colony to rescue you. "
				+ "<br>Add a frostbite wound to the moved survivor. "
				+ "<br><br><b>Place the moved survivor in the colony (this does not count as a move)</b>";
		_Opt2F = "Make your way to your destination, dazed from the crash."
				+ "<br>Place the moved survivor in the location it was headed to. "
				+ "<br><br><b>Roll for exposure on that survivor 3 times.</b>";
		_cardTitle.setText(_currTitle);
		_infoField.setText(_currCond + _currSetup + "<br><br>Option 1:<br>" + _Opt1S + "<br><br> Option 2:<br>"
				+ _Opt2S);
		buttonPanel.add(_optB1_1);
		buttonPanel.add(_optB1_2);
	}
	/*
	public void card80() {
		this.setVisible(true);
		_triggerStatus[Enter right number] = 1;
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
		buttonPanel.add(arg0);
		buttonPanel.add(comp);
	}
	*/																														// |
	public void card72() {
		this.setVisible(true);
		_triggerStatus[72] = 1;
		_currTitle = "Old Divisions"; //Title of the cards
		_currCond = "If a survivor the player controls is at the colony:<br>"; //Trigger Condition for the card
		_currSetup = "Fists and curses are flying as you force your way through the crowd to break up an ugly fight.<br>"
				+ "\"What is going on here? you demand. \"It's him.\" snarls one of the combatants, pointing at his opponent.<br>"
				+ "\"I don't know why we let his type in here to begin with. This place is for good people!\" <br>"
				+ "Your heart sinks as several other voices rise up in agreement.<br>"
				+ "\"In case you haven't noticed, the world as we know it just ended!\" you yell.<br>"
				+ "\"The old divisions we knew - they don't mean jack now.<br>"
				+ "There are just human beings and the things that eat human beings.\"<br>"
				+ "<b>Every player with 1 or more survivors at the colony must vole with a thumbs up or down.<br>"
				+ "The option with the most votes takes effect. </b>"; //Setup/intro text for the card
		_Opt1S = "Exile the one who started the fight!"; //Option 1 Short
		_Opt2S = "Give the one who started the fight a warning!"; //Option 2 short
		_Opt1F = "The bigot who started the fight is expelled from the colony. <b>Remove 1 helpless survivor.</b><br>"
				+ "For a while, people work together more efficiently. <b>Place the current crisis on the bottom of the deck<br>"
				+ "and skip the resolve crisis step this round but lower morale by 1 as people balk at such a severe reaction.</b>"; //Option 1 Full
		_Opt2F = "A notice is hung stating that bigotry will be met with exile. <b>Nothing happens.</b>"; //Option 2 FUll
		_cardTitle.setText(_currTitle);
		_infoField.setText(_currCond + _currSetup + "<br><br>Option 1: <br>" + _Opt1S + 
		"<br><br>Option 2:<br>" + _Opt2S);
		buttonPanel.add(_optB72_1);
		buttonPanel.add(_optB72_2);
	}
	/*public void card73() {
		this.setVisible(true);
		_triggerStatus[73] = 1;
		_currTitle = "Voyeur"; //Title of the cards
		_currCond = "If a survivor the player controls share a location with a survivor another player controls<br>"
				+ "and those are the only 2 survivors at that location:<br>"; //Trigger Condition for the card
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
		buttonPanel.add(arg0);
		buttonPanel.add(comp);
	}*/
	public void card74() {
		this.setVisible(true);
		_triggerStatus[74] = 1;
		_currTitle = "The Artic King"; //Title of the cards
		_currCond = "If the player performs a search at the gas station:<br>"; //Trigger Condition for the card
		_currSetup = "Snowflakes waft by, and your fingers begin to numb in the frigid cold.<br>"
				+ "The eighth key you try works, and you lift the complaining garage door up.<br>"
				+ "You start to curse the noise but the words never make it past your lips.<br>"
				+ "It sits there like some ancient idol. The words POLAR BEAR are emblazoned along the snowmobile's flank,<br>"
				+ "and you joyously recall the TV jingle with no hint of irony, <br>"
				+ "\"When winter tries to trap you there, mount up on a Polar Bear.\""; //Setup/intro text for the card
		_Opt1S = "You use the snowmobile as your transportaion option from now on."; //Option 1 short
		_Opt2S = "You see a chance to get some extra fuel for the Colony."; //Option 2 short
		_Opt1F = "<b>Attach this card to searching survivor.<br>"
				+ "When moving with that survivor you do not roll for exposure but must place a noise token at the origin<br>"
				+ "and destination. If the origin or destination is the colony, add 1 zombie to the colony instead.<br>"
				+ "Each time you move, place a wound token on this snowmobile. When it has 4 wounds remove it from the game.</b>"; //Option 2 Full
		_Opt2F = "You siphon the gas.<br><br>"
				+ "<b>Search the gas station item deck for 2 fuel cards, add them to you hand.</b>"; //Option 2 FUll
		_cardTitle.setText(_currTitle);
		_infoField.setText(_currCond + _currSetup + "<br><br>Option 1: <br>" + _Opt1S + 
		"<br><br>Option 2:<br>" + _Opt2S);
		buttonPanel.add(_optB74_1);
		buttonPanel.add(_optB74_2);
	}
	public void card75() {
		this.setVisible(true);
		_triggerStatus[75] = 1;
		_currTitle = "Carla Thompson"; //Title of the cards
		_currCond = "If the player controls Carla and she is not exiled<br>"; //Trigger Condition for the card
		_currSetup = "Place Carla at the police station (this does not count as a move). \"This guy was a real creep, right?<br>"
				+ "He was basically stalking the entire town.<br>"
				+ "He had used just about every form of social media he could, <br>"
				+ "to get hold of photos which he taped up in every room of his house.<br>"
				+ "He's probably an undead cannibal now, which probably isn't too far off from who he was when he was alive.<br>"
				+ "The thing is, his house had been closed for the investigation, and I bet all those photos are still in there.<br>"
				+ " If I got my hands on them, I could probably give people in the colony photos of lost loved ones,<br>"
				+ "and right memories like those are important to hold on to.\"<br><br>"
				+ "A reanimated detective roams the house of the creep."; //Setup/intro text for the card
		_Opt1S = "\"I've always hated you anyways Derek... Would be nice to finally smash your face in... AND for a good cause.<br>"; //Option 1 Short
		_Opt2S = "\"Hell no! I won't risk my life for some pictures\""; //Option 2 short
		_Opt1F = "Roll for exposure twice on Carla. <br>"
				+ "<b>If she is not killed by the exposure, gain 1 morale."; //Option 1 Full
		_Opt2F = "The dead detective is too much and Carla decides not to risk it.<br>"
				+ "<br>b>Nothing happens.</b>"; //Option 2 FUll
		_cardTitle.setText(_currTitle);
		_infoField.setText(_currCond + _currSetup + "<br><br>Option 1: <br>" + _Opt1S + 
		"<br><br>Option 2:<br>" + _Opt2S);
		buttonPanel.add(_optB75_1);
		buttonPanel.add(_optB75_2);
	}
	public void card76() {
		this.setVisible(true);
		_triggerStatus[76] = 1;
		_currTitle = "Jenny Clark"; //Title of the cards
		_currCond = "If player performs a search with Jenny Clark and she is not exiled:<br>"; //Trigger Condition for the card
		_currSetup = "The jar of pennies crashes to the floor, glass shards and tarnished coins<br>"
				+ "spilling everywhere.<br>"
				+ "\"Stupid pennies.\" Jenny mutters.<br>"
				+ "\"Don't you know I'm trying lo be quiet? But when she turns away to search elsewhere,<br>"
				+ "she finds herself face to face with a zombie; it groans,<br>"
				+ "cold fetid air pushed from the thing's mouth.<br>"
				+ "Yet to Jenny's surprise the zombie does not attack,<br>"
				+ "and in its one good eye he thinks she sees something a kin to kindness.<br>"
				+ "Behind the zombie, more corpses shuffle into the room.<br>"
				+ "\"Aw, you guys aren't all bad are you?\" she says.<br>"
				+ "\"Come on. I know some people who are going to want to meet you.\""; //Setup/intro text for the card
		_Opt1S = "*Knock, Knock* \"Who's there?\" says the gatekeeper of the Colony as he looks through the keyhole.<br>"
				+ "As he sees Jenny Clark outside he starts opening the door."; //Option 1 Short
		_Opt2S = "One of the scouts scoopes Jenny as she is walking back towards the colony"; //Option 2 short
		_Opt1F = "<b>Place Jenny at the colony. Add 8 zombies to the colony.</b>"; //Option 1 Full
		_Opt2F = "Peering through the rifle's scope he sees Jenny walking slowly down the road, a group of dead in tow.<br>"
				+ "\"Oh God.\" he mutters, slipping a finger over the trigger.<br>"
				+ "\"That ditz must have gotten herself bit.\" <br><br>"
				+ "<b>Kill Jenny Clark.</b>"; //Option 2 FUll
		_cardTitle.setText(_currTitle);
		_infoField.setText(_currCond + _currSetup + "<br><br>Option 1:<br>" + _Opt1S + 
		"<br><br>Option 2:<br>" + _Opt2S);
		buttonPanel.add(_optB76_1);
		buttonPanel.add(_optB76_2);
	}
	public void card77() {
		this.setVisible(true);
		_triggerStatus[77] = 1;
		_currTitle = "Hunted"; //Title of the cards
		_currCond = "If the player receives a non-frostbite wound as the result of rolling for exposure after moving:<br>"; //Trigger Condition for the card
		_currSetup = "\"They shot me! It was no accident either. "
				+ "<br>I saw them! They were all decked out in camouflage and they had no interest in the zombies in the area."
				+ "<br>They were hunting me, and I tell you what - they don't look as rough as us."
				+ "<br>They look well fed. We've got armed cannibals in our backyard.\""; //Setup/intro text for the card
		_Opt1S = "\"These hunters are a big of a threat! Lets join up and take'em out!\""; //Option 1 Short
		_Opt2S = "\"We won't stand a chance against them, we will just have to be more carefull<br>"
				+ "from now on!\""; //Option 2 short
		_Opt1F = "You hunt down the hunters. "
				+ "<br><br><b>Roll for exposure twice on each survivor you control.</b>"; //Option 1 Full
		_Opt2F = "For the remainder or the game, whenever any player moves a survivor,<br>"
				+ "in addition to rolling the exposure die they must roll a dice.<br>"
				+ "On a result of 2 or lower that survivor receives 1 wound."; //Option 2 FUll
		_cardTitle.setText(_currTitle);
		_infoField.setText(_currCond + _currSetup + "<br><br>Option 1:<br>" + _Opt1S + 
				"<br><br>Option 2:<br>" + _Opt2S );
		buttonPanel.add(_optB77_1);
		buttonPanel.add(_optB77_2);
	}
	
	public void card78() {
		this.setVisible(true);
		_triggerStatus[78] = 1;
		_currTitle = "Loretta Clay"; //Title of the cards
		_currCond = "If the player controls Loretta and she is not exiled:<br>"; //Trigger Condition for the card
		_currSetup = "\"Where did we find these cans?\" Loretta says angrily."
				+ "<br>\"I have almost thirty cans well past their expiration date."
				+ "<br>If you hunters don't do your job and look, then it messes everything up."
				+ "<br>We were counting on this food!\""; //Setup/intro text for the card
		_Opt1S = "Loretta cooks the food anyway."; //Option 1 Short
		_Opt2S = "Loretta swears at the hunter for not bringing this to her earlier."; //Option 2 short
		_Opt1F = "Loretta cooks the food anyway. "
				+ "Roll a die. On a result of 5 or higher the food is not only good but Loretta does an"
				+ "<br>exceptional job of preparing it. Gain 1 morale. "
				+ "<br>On a result of 3-4 the food isn't any worse than usual. Nothing happens."
				+ "<br>On a result of 1-2, food poisoning runs rampant through the colony."
				+ "<br>Every non-helpless survivor at the colony must roll another die."
				+ "On a result of 1-2 they receive 1 wound."; //Option 1 Full
		_Opt2F = "She swears angrily and throws the cans out."
				+ "<br><b>Remove 1 food from the supply.</b>"; //Option 2 FUll
		_cardTitle.setText(_currTitle);
		_infoField.setText(_currCond + _currSetup + "<br><br>Option 1:<br>" + _Opt1S + 
				"<br><br>Option 2:<br>" + _Opt2S );
		buttonPanel.add(_optB78_1);
		buttonPanel.add(_optB78_2);
	}
	
	public void card79() {
		this.setVisible(true);
		_triggerStatus[79] = 1;
		_currTitle = "Fuel Tanker"; //Title of the cards
		_currCond = "If the player moves a non-exiled survivor:<br>"; //Trigger Condition for the card
		_currSetup = "\"Holy crap.\" The fuel tanker sits just off the road, and it appears in excellent condition. "
				+ "<br>You exit your car, and checking the perimeter you approach the cab, "
				+ "<br>it's driver side door hanging open. "
				+ "<br>You slice the seat belt apart, and bludgeon the head of the zombie that falls out. "
				+ "<br>The trucker's bloody hat says Beaver Inspector, "
				+ "<br>and you marvel al how stupid and frivolous your species used to be."
				+ "<br>Climbing into the cab you give the keys a twist and the loud diesel engine roars to life"
				+ "<br>like some ancient monster reborn into the world."
				+ "<br>You cringe. Noise like that is going to draw every dead thing for a mile."; //Setup/intro text for the card
		_Opt1S = "Drive the tanker back to Colony"; //Option 1 Short
		_Opt2S = "Decides it's to risky to drive it back."; //Option 2 short
		_Opt1F = "You drive the tanker back to the colony, drawing every zombie in the area back with you. "
				+ "<br><b>Place 6 zombies at the colony and add up to 4 fuel cards that have been</b>"
				+ "<br><b>removed from the game to your hand.</b>"; //Option 1 Full
		_Opt2F = "You sigh regretfully and turn the motor back off."
				+ "<br>It's just too loud to risk driving into the colony."
				+ "<br><br><b>Nothing happens.</b>"; //Option 2 Full
		_cardTitle.setText(_currTitle);
		_infoField.setText(_currCond + _currSetup + "<br><br>Option 1: <br>" +_Opt1S + "<br><br>Option 2: <br>"
				+ _Opt2S);
		buttonPanel.add(_optB79_1);
		buttonPanel.add(_optB79_2);
	}
	public void card80() {
		this.setVisible(true);
		_triggerStatus[80] = 1;
		_currTitle = "Harman Brooks"; //Title of the cards
		_currCond = "If Harman Brooks is in play and is not exiled:"; //Trigger Condition for the card
		_currSetup = "<br>\"No more shoveling trash or building barricades.<br>"
				+ "I need to get out and do what I do best.\""; //Setup/intro text for the card
		_Opt1S = "Harman disappears, he said something about food."; //Option 1 Short
		_Opt2S = "Harman disappears, he said something about medicine"; //Option 2 short
		_Opt1F = "Harman lays flat on his platform, and feels it sway in the winter wind.<br>"
				+ "He gazes down from his treetop vantage point at the specially laid snare below,"
				+ "<br>and steels himself for a long wait.<br>"
				+ "<b>Add 3 food to the supply.</b>"; //Option 1 Full
		_Opt2F = "Thorns snag at Harman's sleeves as he pushes through a thick tangle of greenery.<br>"
				+ "After long hours he is beginning to give up hope, "
				+ "<br>but then he spots a promising sprout at the base of an old oak tree.<br>"
				+ "Kneeling, he plucks a leaf and chews it. Perfect! <br>"
				+ "Now if he can just collect several dozen more plants, he might be able to make a decent salve.<br>"
				+ "<br><b>Remove 1 wound from up to 3 survivors.</b>"; //Option 2 FUll	
		_cardTitle.setText(_currTitle);
		_infoField.setText(_currCond + _currSetup + "<br><br>Option 1:<br>" + _Opt1S + "<br><br> Option 2:<br>"
				+ _Opt2S);
		
		buttonPanel.add(_optB80_1);
		buttonPanel.add(_optB80_2);
	}
		
}
