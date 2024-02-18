package CST8221;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 * Menu class for window head
 */
public class Menu extends JFrame implements ActionListener {
	
	/**
	 * auto-generated serial uid
	 */
	private static final long serialVersionUID = 7282865349944705405L;
	
	/**
	 * JMenuBar initial declaration
	 */
	JMenuBar mbar;
	/**
	 * JMenu, menu and submenu initial declaration
	 */
	JMenu menu, submenu;
	/**
	 * JMenuItem initial declaration
	 */
	JMenuItem mitem;
	/**
	 * JButton initial declaration
	 */
	JButton button;
	/**
	 * JPanel initial declaration
	 */
	JPanel panel;
	
	/**
	 * Menu constructor
	 */
	public Menu() {
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(500, 600));
		setResizable(false);
		
		button = new JButton("Start Game");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connect4 mainpage = new Connect4();
				mainpage.setVisible(true);
				dispose();
			}
		});
		
		panel = new JPanel();
		panel.add(button);
		add(panel);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	/**
	 * menu main
	 * 
	 * @param args commandline arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Menu mainMenu = new Menu();
			mainMenu.setVisible(true);
		});
	}

	/**
	 * Empty actionPerformed override
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
