import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

//import org.apache.commons.lang3.ArrayUtils;
import javax.swing.*;


public class MainFrame extends JFrame implements ActionListener{
/************************************************************
*			           Declares/initialization              *
*************************************************************/	
	
		// Main frame
		private int numPlayers=0;
		private int count = 0;
		private Player[] _pArray = new Player[5];
		private JPanel _startaPanel;
		private JPanel _addPlayerPanel;
		private JPanel _cardPanel;
		private CardLayout _deck;
		
		// StartaAvsluta
		private JButton _avsluta;
		private JButton _starta;
		private JPanel _empty1;
		private JPanel _empty2;
		private JPanel _test;
	
		// Add player
		private JLabel _label;
		private JPanel _playerPanel;
		private JButton _button1;
		private JButton _button2;
		private JButton _button3;
		private JButton _button4;
		private JButton _button5;
		
		//Create Player
		private JPanel _createPlayer;
		private JPanel _namepanel;
		private JPanel _buttonPanel;
		private JPanel _container;
		private JButton _addPbutton;
		private JLabel _playerName;
		private JTextField _NP;
		private int totalnumchars = 31;
		private String labels[] = { "Alexis Grey", "Andrew Evans", "Annaleigh Chan", "Arthur Thurston",
				"Ashley Ross", "Bev Russell", "Brandon Kane", "Brian Lee", "Buddy Davis",
				 "Carla Thompson", "Daniel Smith", "David Garcia", "Edward White", "Forest Plum",
				"Gray Beard", "Gabriel Diaz", "Harman Brooks", "Jenny Clark", "James Meyers",
				"John Price", "Janet Taylor", "Kodiak Colby", "Loretta Clay", "Mike Cho",
				"Maria Lopez", "Olivia Brown", "Rod Miller", "Sophie Robinson", "Sparky",
			     "Thomas Heart", "Talia Jones"
			     }; 
		private JCheckBox[] _listChars = new JCheckBox[labels.length];
		//private JCheckBox[] _listChars = new JCheckBox[totalnumchars];
		
		private String[] _charDeck;
		private String[] _active;
		
		// Playing Screen
		private GameFrame _gamescreen;
		
/***********************************************************
*			          Start of Class                       *
************************************************************/
		public MainFrame() {
			//Main Frame
			this.setSize(700, 500);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			
			_deck = new CardLayout();
			_cardPanel = new JPanel();
			_cardPanel.setLayout(_deck);
			this.add(_cardPanel);
			_charDeck = labels;
/************************************************************
 *			           Cards                                *
 ************************************************************/
			// StartaPanel
			_startaPanel = new JPanel();
			
			_empty1 = new JPanel();
			_test = new JPanel(new GridLayout(2,0, 0,240));
			_empty2 = new JPanel();
			
			_startaPanel.add(_empty1);
			_startaPanel.add(_test);
			_startaPanel.add(_empty2);
			
			_starta = new JButton("New Game");
			_starta.setPreferredSize(new Dimension(200,100));
			_starta.addActionListener(this);
			_test.add(_starta);
			
			_avsluta = new JButton("Exit");
			_avsluta.addActionListener(this);
			_test.add(_avsluta);
			
			// AddPLayerPanel
			_addPlayerPanel = new JPanel();
			_empty1 = new JPanel();
			_playerPanel = new JPanel(new GridLayout(6,0, 0,20));
			_empty2 = new JPanel();
			
			_addPlayerPanel.add(_empty1);
			_addPlayerPanel.add(_playerPanel);
			_addPlayerPanel.add(_empty2);
			
			_label = new JLabel("Number of players?");
			_button1 = new JButton("1");
			_button2 = new JButton("2");
			_button3 = new JButton("3");
			_button4 = new JButton("4");
			_button5 = new JButton("5");
			_button1.setPreferredSize(new Dimension(10,50));
			_button1.addActionListener(this);
			_button2.addActionListener(this);
			_button3.addActionListener(this);
			_button4.addActionListener(this);
			_button5.addActionListener(this);
			_playerPanel.add(_label);
			_playerPanel.add(_button1);
			_playerPanel.add(_button2);
			_playerPanel.add(_button3);
			_playerPanel.add(_button4);
			_playerPanel.add(_button5);
			
			// CreatePlayerPanel
			_createPlayer = new JPanel(new BorderLayout());
			_namepanel = new JPanel();
			_buttonPanel = new JPanel();
			
			_addPbutton = new JButton("Add Player");
			_playerName = new JLabel("Name of Player 1");////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			_NP = new JTextField(20);
			_container = new JPanel(new GridLayout(8,4));
			_container.setAlignmentY(JComponent.LEFT_ALIGNMENT);

			_createPlayer.add(_buttonPanel, BorderLayout.PAGE_END);
			_createPlayer.add(_namepanel, BorderLayout.PAGE_START);
			_createPlayer.add(_container, BorderLayout.CENTER);
			_namepanel.add(_playerName);
			_namepanel.add(_NP);
			_buttonPanel.add(_addPbutton);

			for(int i=0; i<totalnumchars; i++) {
				_listChars[i] = new JCheckBox(labels[i]);
				_container.add(_listChars[i]);
			}
			
			//Deck Container
			_cardPanel.add(_startaPanel, "start");
			_cardPanel.add(_addPlayerPanel, "addplayer");
			_cardPanel.add(_createPlayer, "createP");
			_addPbutton.addActionListener(this);
			this.setVisible(true);
			

		}
/************************************************************		 
*			           Functions                            *
*************************************************************/
		public void newgameFrame() {
			_gamescreen = new GameFrame(_pArray, numPlayers, _listChars, labels, _charDeck);
			this.setVisible(false);
		}
		
		
		public void clearCheckbox(){
			for(int i=0; i < _listChars.length; i++) {
				_listChars[i].setSelected(false);
			}
		}
		
		public void newPlayer() {
			for(int i=0; i < numPlayers; i++) {
				_pArray[i] = new Player();
			}
		}
			
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Object source = arg0.getSource();
			if(source.equals(_starta)) {
				_deck.show(_cardPanel, "addplayer");
			}
			else if(source.equals(_addPbutton)) {
				String name = _NP.getText();
				if(name.isEmpty())
					name = "Player" + (count+1);
				_pArray[count].setName(name);
				for(int i = 0; i < totalnumchars; i++) {
					if(_listChars[i].isSelected()) {
						String charName = _listChars[i].getText();
						ArrayList<String> list = new ArrayList<String>(Arrays.asList(_charDeck));
						list.remove(charName);
						_charDeck = list.toArray(new String[0]);
						_pArray[count].addChar(charName);
						_pArray[count].setCharPos(charName, "Colony");
						_listChars[i].setEnabled(false);
					}
				}
				//System.out.println(Arrays.toString(_charDeck));
				//System.out.println("Player " + (count+1) + " "+ _pArray[count].getName());
				count++;
				if (count == numPlayers) {
					newgameFrame(); 
				}
				else {
					clearCheckbox();
					_playerName.setText("Name of Player " + (count+1));
					_NP.setText("");
				}
			}
			else if(source.equals(_button1)) {
				numPlayers = 1;
				newPlayer();
				_deck.show(_cardPanel, "createP");
			}
			else if(source.equals(_button2)) {
				numPlayers = 2;
				newPlayer();
				_deck.show(_cardPanel, "createP");
			}
			else if(source.equals(_button3)) {
				numPlayers = 3;
				newPlayer();
				_deck.show(_cardPanel, "createP");
			}
			else if(source.equals(_button4)) {
				numPlayers = 4;
				newPlayer();
				_deck.show(_cardPanel, "createP");
			}
			else if(source.equals(_button5)) {
				numPlayers = 5;
				newPlayer();
				_deck.show(_cardPanel, "createP");
				
				}
			else if(source.equals(_avsluta))
				System.exit(0);
			}
	}
	
