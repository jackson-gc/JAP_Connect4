package CST8221;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controler.GameBoard;
import panelComponents.BoardPanel;
/**
 * MenuBar component class
 */
public class MenuBar extends JMenuBar {
    /**
	 * auto-generated serial uid
	 */
	private static final long serialVersionUID = 1019707613887489945L;

	/**
	 * MenuBar Constructor
	 */

	public MenuBar() {
        // File
		JMenu fileMenu = new JMenu("File");
	    JMenuItem exitMenuItem = new JMenuItem("Exit");
	    JMenuItem loadMenuItem = new JMenuItem("Load");
	    JMenuItem saveMenuItem = new JMenuItem("Save");
	    fileMenu.add(saveMenuItem);
	    fileMenu.add(loadMenuItem);
	    fileMenu.add(exitMenuItem);

	    saveMenuItem.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {	        	
	        	Connect4.saveState = true;
	        }
	    });    
	    
	    loadMenuItem.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            openFileChooser();
	        }
	    });
	    
	    
	    exitMenuItem.addActionListener(e -> System.exit(0));    
        
        add(fileMenu);
        
        

        // Connection
        JMenu Cmenu = new JMenu("Connection");
        JMenuItem CmenuItem = new JMenuItem("Host");
        JMenuItem CmenuItem1 = new JMenuItem("Join");
        JMenuItem CmenuItem2 = new JMenuItem("Close");
        Cmenu.add(CmenuItem);
        Cmenu.add(CmenuItem1);
        Cmenu.add(CmenuItem2);
        add(Cmenu);

        // Score
        JMenu Smenu = new JMenu("Score");
        JMenuItem SmenuItem = new JMenuItem("<html>Current score: <u>2-3</u></html>");
        JMenuItem SmenuItem1 = new JMenuItem("Reset score");
        JMenuItem SmenuItem2 = new JMenuItem("Score history");
        Smenu.add(SmenuItem);
        Smenu.add(SmenuItem1);
        Smenu.add(SmenuItem2);
        add(Smenu);

        // Options
        JMenu Omenu = new JMenu("Game Options");
        JMenuItem OmenuItem = new JMenuItem("Reset board");
        JMenuItem OmenuItem1 = new JMenuItem("Set game length");
        Omenu.add(OmenuItem);
        Omenu.add(OmenuItem1);
        add(Omenu);

        // Language
        JMenu Lmenu = new JMenu("Language");
        JMenuItem LmenuItem = new JMenuItem("English");
        JMenuItem LmenuItem1 = new JMenuItem("French");
        Lmenu.add(LmenuItem);
        Lmenu.add(LmenuItem1);
        add(Lmenu);

        // Help
        JMenu Hmenu = new JMenu("Help");
        JMenuItem HmenuItem = new JMenuItem("See help page");
        JMenuItem HmenuItem1 = new JMenuItem("See about page");
        Hmenu.add(HmenuItem);
        Hmenu.add(HmenuItem1);
        add(Hmenu);
    }
	
	
    private void openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(".\\saves"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            if (!Connect4.gb.isNewGame) {
            	Connect4.gb = new GameBoard(selectedFile);
            	
            } else {
            	File oldFile = Connect4.gb.getWorkingFile();
            	Connect4.gb = new GameBoard(selectedFile);
            	System.out.println("deleting: " + oldFile.getAbsolutePath());
            	oldFile.delete();
            }
            Connect4.gbViewControl = new BoardPanel();
            
            
            
            Connect4.gbViewControl.repaint();
            Connect4.gb.printBoard();
        }

    }
     	
}
