package CST8221;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**
 * Connect4 class, boots the main game body
 */
public class Connect4 extends JFrame implements ActionListener {
    
    /**
	 * auto-generated serial uid
	 */
	private static final long serialVersionUID = 5369068481793381074L;

	/**
	 * Connect4 Constructor
	 */
    public Connect4() {
        setTitle("Connect 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1920, 1080));
        setResizable(false);
        setLocation(960, 540);

        // Panels
        Panels panel = new Panels();
        setContentPane(panel);

        // Menu bar
        MenuBar menuBar = new MenuBar();
        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * Connect4 main
     * 
     * @param args commandline arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Connect4 connect4 = new Connect4();
            connect4.setVisible(true);
        });
    }
}
