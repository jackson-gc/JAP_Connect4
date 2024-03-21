package CST8221;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import controler.GameBoard;
import panelComponents.BoardPanel;
import panelComponents.SystemPanel;
/**
 * Connect4 class, boots the main game body
 */
public class Connect4 extends JFrame implements ActionListener {
    
    /**
	 * auto-generated serial uid
	 */
	private static final long serialVersionUID = 5369068481793381074L;
	public static GameBoard gb = new GameBoard();
	public static BoardPanel gbViewControl = new BoardPanel();
	public static byte currentTurn = 01;
	public static boolean saveState = false;
	public static Connect4 connect4;
	Panels panel;

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
        panel = new Panels();
        setContentPane(panel);

        // Menu bar
        MenuBar menuBar = new MenuBar();
        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
        
        addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		if(!saveState) {			
        			gb.getWorkingFile().delete();
        		}
        			
        		dispose();
            }
        });
    }
    
    public static byte playerMove() {
    	byte temp = currentTurn;
    	
    	if (currentTurn == 01) {
    		SystemPanel.redTurn.setEnabled(false);
    		SystemPanel.redTurn.repaint();
    		SystemPanel.yellowTurn.setEnabled(true);
    		SystemPanel.yellowTurn.repaint();    		
    		currentTurn++;
    	}
    	else {
    		SystemPanel.redTurn.setEnabled(true);
    		SystemPanel.redTurn.repaint();		
    		SystemPanel.yellowTurn.setEnabled(false);
    		SystemPanel.yellowTurn.repaint();    		
    		currentTurn--;
    	}
		return temp;
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
            connect4 = new Connect4();
            connect4.setVisible(true);
        });
    }
}
