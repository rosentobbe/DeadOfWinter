import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.TextArea;
import javax.swing.JTextArea;

public class TestGui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGui window = new TestGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "name_338133506421145");
		panel.setLayout(new BorderLayout(0, 0));
		
		JTextArea txtrTextarea = new JTextArea();
		txtrTextarea.setWrapStyleWord(true);
		txtrTextarea.setLineWrap(true);
		txtrTextarea.setText("this is a long text which hopefully will be longer then the window which will ocntaion this text");
		Font font = new Font(txtrTextarea.getFont().getName(), Font.BOLD, txtrTextarea.getFont().getSize());
		txtrTextarea.setFont(font);
		panel.add(txtrTextarea, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("New button");
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel_1.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, "name_338173373571853");
	}

}
