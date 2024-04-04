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

/**
 * Menu class represents the main menu window for the game.
 * This class extends JFrame and implements the ActionListener interface.
 */
public class Menu extends JFrame implements ActionListener {
    
    /** Auto-generated serial version UID */
    private static final long serialVersionUID = 7282865349944705405L;

    /** JMenuBar declaration */
    JMenuBar mbar;

    /** JMenu and submenu declarations */
    JMenu menu, submenu;

    /** JMenuItem declaration */
    JMenuItem mitem;

    /** JButton declarations */
    JButton joinButton, hostButton;

    /** ImagedPanel instance for displaying background image */
    ImagedPanel panel;

    /**
     * Menu constructor to initialize the main menu window.
     */
    public Menu() {
        setTitle("Main Menu");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 600));
        setResizable(false);

        // Create and add the background image panel
        panel = new ImagedPanel(Panels.imgPath + "Menu.png");


        JButton hostButton = new JButton("Host");
        JButton joinButton = new JButton("Join");

        panel.add(hostButton);
        panel.add(joinButton);

        add(panel);

        // Pack and center the window
        pack();
        setLocationRelativeTo(null);

        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("This is client");
                NetworkManager nm = new NetworkManager(12345, false);
                if (nm.getClient().isConnected()){
                    Connect4 mainpage = new Connect4(nm);
                    mainpage.setVisible(true);
                    dispose();
                } else {
                    System.out.println("Connection to host refused or not found");
                }
            }
        });
        
        hostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("This is host");
                NetworkManager nm = new NetworkManager(12345, true);
                Connect4 mainpage = new Connect4(nm);
                mainpage.setVisible(true);
                dispose();
            }
        });
    }

    /**
     * Main method to start the Menu class.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu mainMenu = new Menu();
            mainMenu.setVisible(true);
        });
    }

    /**
     * Empty actionPerformed override.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
