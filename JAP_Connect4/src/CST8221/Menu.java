package CST8221;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
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
	JButton joinButton, hostButton;
	
	/**
	 * JPanel initial declaration
	 */
	ImagedPanel panel;
	
	/**
	 * Menu constructor
	 */
	public Menu() {
		setTitle("Main Menu");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(500, 600));
		setUndecorated(true);
		setResizable(false);
		
//		hostButton = new JButton("Host Game");
//		joinButton = new JButton("Join Game");
//		
//		
//		joinButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Connect4 mainpage = new Connect4();
//				mainpage.setVisible(true);
//				dispose();
//			}
//		});
//		hostButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Connect4 mainpage = new Connect4();
//				mainpage.setVisible(true);
//				dispose();
//			}
//		});
		
		
		
		panel = new ImagedPanel(Panels.imgPath + "Menu.png");
		
//		panel.add(hostButton);
//		panel.add(joinButton);
		
		add(panel);
		
		
		
		
		
		pack();
		setLocationRelativeTo(null);
		
		 // Show splash screen for 5 seconds
        Timer timer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the game screen after 5 seconds
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        Connect4 mainpage = new Connect4();
                        mainpage.setVisible(true);
                        dispose();
                    }
                });
            }
        });
        timer.setRepeats(false); // Ensure the timer only runs once
        timer.start();
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


