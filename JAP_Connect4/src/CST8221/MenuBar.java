package CST8221;
import java.awt.Dimension;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.GameBoard;
import panelComponents.SystemPanel;
/**
 * MenuBar component class
 */
public class MenuBar extends JMenuBar {
    /**
	 * auto-generated serial uid
	 */
	private static final long serialVersionUID = 1019707613887489945L;

	private Connect4 c4;
	
	protected JMenuItem scoreDisplayItem;
	
	/**
	 * MenuBar Constructor
	 */
	public MenuBar(Connect4 connect4) {
		this.c4 = connect4;
        // File
		JMenu fileMenu = new JMenu("File");
	    JMenuItem exitItem = new JMenuItem("Exit");
	    JMenuItem loadItem = new JMenuItem("Load");
	    JMenuItem saveItem = new JMenuItem("Save");
	    fileMenu.add(saveItem);
	    fileMenu.add(loadItem);
	    fileMenu.add(exitItem);

	    saveItem.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {	        	
	        	c4.saveState = true;
	        	System.out.println(c4.gb.getWorkingFile() + " is " + c4.saveState);
	        }
	    });    
	    
	    loadItem.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            openFileChooser();
	        }
	    });
	    
	    exitItem.addActionListener(e -> System.exit(0)); 
        
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
        System.out.println("building score: " + c4.score[0] + "-" + c4.score[1]);
        scoreDisplayItem = new JMenuItem("Current score: " + c4.score[0] + "-" + c4.score[1]);
        JMenuItem resetScoreItem = new JMenuItem("Reset score");

        resetScoreItem.addActionListener(e -> {
            c4.score[0] = 0;
            c4.score[1] = 0;
            c4.updateScoreItem();
            c4.gb.updateWorkingFile();
        });
        
        
        Smenu.add(scoreDisplayItem);
        Smenu.add(resetScoreItem);
        add(Smenu);

        // Options
        JMenu Omenu = new JMenu("Game Options");
        JMenuItem resetBoardItem = new JMenuItem("Reset board");
        JMenuItem setTurnItem = new JMenuItem("Set Turn Length");
        
        setTurnItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c4.timer.setStatus(0);
                JDialog dialog = new JDialog(c4, "Set Turn Length", true);
                dialog.setSize(275, 120);
                
                JPanel dialogPanel = new JPanel();
                dialogPanel.setPreferredSize(new Dimension(200, 200));

                JLabel label = new JLabel("Set the game's turn length (mm:ss)");
                JTextField textField = new JTextField();
                textField.setPreferredSize(new Dimension(100, 25));
                dialogPanel.add(label);
                dialogPanel.add(textField);
                


                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	boolean realisticTime = false;
                        String inputTime = textField.getText().trim();
                        // Assuming the input format is "minutes:seconds"
                        String[] parts = inputTime.split(":");
                        if (parts.length == 2) {
                            try {

                            	int minutes = Math.abs(Integer.parseInt(parts[0]));
                                int seconds = Math.abs(Integer.parseInt(parts[1]));
                                int totalTimeInSeconds = minutes * 60 + seconds;

                                if (totalTimeInSeconds >= 5) {
                                	realisticTime = true;
                                	SystemPanel.allotedTurnTime = totalTimeInSeconds;
                                } else {
                                	label.setText("Please set the turn length to atleast 5s");
                                }

                            } catch (NumberFormatException ex) {
                                // Handle parsing errors if input is not a valid number
                                ex.printStackTrace();
                            }
                        }
                        
                        if (realisticTime) {
                        	c4.gb.refreshBoard(false);
                        	dialog.dispose();
                        	
                        }
                        
                    }
                });

                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        dialog.dispose();
                    }
                });
                
                dialogPanel.add(okButton);
                dialog.add(dialogPanel);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    
        
        resetBoardItem.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {	        	
	        	c4.gb.refreshBoard(false);
	        }
	    });    
        
        
        Omenu.add(resetBoardItem);
        Omenu.add(setTurnItem);
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
            String fileName = selectedFile.getName();
            if (!c4.gb.isNewGame || c4.saveState) {
            	c4.gb = new GameBoard(c4, selectedFile);
            	
            } else if (fileName.equals(c4.gb.getWorkingFile().getName())) {
            	System.out.println("Not changing file.");
            
            } else {
            	File oldFile = c4.gb.getWorkingFile();
            	c4.gb = new GameBoard(c4, selectedFile);
            	System.out.println("deleting: " + oldFile.getAbsolutePath());
            	oldFile.delete();
            	
            }
            c4.gb.refreshBoard(false);
        }

    }
    
     	
}
