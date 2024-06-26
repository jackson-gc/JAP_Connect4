package CST8221;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

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
public class MenuBar extends JMenuBar implements LocaleManager.LocaleChangeListener {
	/**
	 * The generated serial version UID for serialization/deserialization.
	 */
	private static final long serialVersionUID = 1019707613887489945L;

	/**
	 * The Connect4 instance associated with this class.
	 */
	private Connect4 c4;

	/**
	 * The JMenuItem for displaying scores.
	 */
	protected JMenuItem scoreDisplayItem;

	// private JMenu and JMenuItem variables
	/**
	 * The JMenu for file operations.
	 */
	private JMenu fileMenu;
	/**
	 * The JMenu for size settings.
	 */
	private JMenu Smenu;
	/**
	 * The JMenu for other options.
	 */
	private JMenu Omenu;
	/**
	 * The JMenu for load operations.
	 */
	private JMenu Lmenu;
	/**
	 * The JMenu for help options.
	 */
	private JMenu Hmenu;

	/**
	 * The JMenuItem for exiting the application.
	 */
	private JMenuItem exitItem;
	/**
	 * The JMenuItem for loading game data.
	 */
	private JMenuItem loadItem;
	/**
	 * The JMenuItem for saving game data.
	 */
	private JMenuItem saveItem;
	/**
	 * The JMenuItem for resetting scores.
	 */
	private JMenuItem resetScoreItem;
	/**
	 * The JMenuItem for setting the turn.
	 */
	private JMenuItem setTurnItem;
	/**
	 * The JMenuItem for resetting the board.
	 */
	private JMenuItem resetBoardItem;
	/**
	 * The JMenuItem for load option 1.
	 */
	private JMenuItem LmenuItem;
	/**
	 * The JMenuItem for load option 2.
	 */
	private JMenuItem LmenuItem1;
	/**
	 * The JMenuItem for help option 1.
	 */
	private JMenuItem HmenuItem;
	/**
	 * The JMenuItem for help option 2.
	 */
	private JMenuItem HmenuItem1;

	/**
	 * The dialog for displaying messages or user inputs.
	 */
	JDialog dialog;

	
    /**
     * Constructs a new MenuBar object for the Connect4 game.
     * 
     * @param connect4 the main Connect4 game instance
     */
	@SuppressWarnings("deprecation")
	public MenuBar(Connect4 connect4) {
		this.c4 = connect4;
		LocaleManager.addLocaleChangeListener(this);
		localeChanged(new Locale("en", "US"));
	}

		
    /**
     * Invoked when the locale has changed.
     * 
     * @param newLocale the new locale to be set
     */
	public void localeChanged(Locale newLocale) {
		LocaleManager.messages = ResourceBundle.getBundle("message", newLocale);
		initializeMenu();
	}
		
	
	private void initializeMenu() {
	
        // File
		fileMenu = new JMenu(LocaleManager.messages.getString("file"));
	    exitItem = new JMenuItem(LocaleManager.messages.getString("exit"));
	    loadItem = new JMenuItem(LocaleManager.messages.getString("load"));
	    saveItem = new JMenuItem(LocaleManager.messages.getString("save"));
	    fileMenu.add(saveItem);
	    fileMenu.add(loadItem);
	    fileMenu.add(exitItem);

	    saveItem.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {	        	
	        	c4.saveState = true;
	        	System.out.println(c4.gameModel.getWorkingFile() + " is " + c4.saveState);
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
        


        // Score
        Smenu = new JMenu(LocaleManager.messages.getString("scoreMenu"));
        scoreDisplayItem = new JMenuItem(LocaleManager.messages.getString("current") + c4.score[0] + "-" + c4.score[1]);
        resetScoreItem = new JMenuItem(LocaleManager.messages.getString("resetScore"));
        Smenu.add(scoreDisplayItem);
        Smenu.add(resetScoreItem);

        resetScoreItem.addActionListener(e -> {
            c4.score[0] = 0;
            c4.score[1] = 0;
            c4.updateScoreItem();
            c4.gameModel.updateWorkingFile();
        });
        
        
        Smenu.add(scoreDisplayItem);
        Smenu.add(resetScoreItem);
        add(Smenu);

        // Options
        Omenu = new JMenu(LocaleManager.messages.getString("optionsMenu"));
        resetBoardItem = new JMenuItem(LocaleManager.messages.getString("resetBoard"));
        setTurnItem = new JMenuItem(LocaleManager.messages.getString("gameLength"));
        
        setTurnItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c4.timer.setStatus(0);
                dialog = new JDialog(c4, "Set Turn Length", true);
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
                            	// parse the input collecting minutes and seconds
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
                        	c4.gameModel.refreshBoard(false, true, true);
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
	        	c4.gameModel.refreshBoard(false, true, true);
	        }
	    });    
        
        
        Omenu.add(resetBoardItem);
        Omenu.add(setTurnItem);
        add(Omenu);

        // Language
        Lmenu = new JMenu(LocaleManager.messages.getString("languageMenu"));
        LmenuItem = new JMenuItem(LocaleManager.messages.getString("english"));
        LmenuItem1 = new JMenuItem(LocaleManager.messages.getString("french"));
        Lmenu.add(LmenuItem);
        Lmenu.add(LmenuItem1);
        add(Lmenu);
        
        // action listener for chaning languages between french and english
        LmenuItem.addActionListener(new ActionListener() {
	        @SuppressWarnings("deprecation")
			@Override
	        public void actionPerformed(ActionEvent e) {
	        	changeLanguage(new Locale("en", "US"));
	        }
	    });
	    
        LmenuItem1.addActionListener(new ActionListener() {
	        @SuppressWarnings("deprecation")
			@Override
	        public void actionPerformed(ActionEvent e) {
	        	changeLanguage(new Locale("fr", "FR"));
	        }
	    });

        // Help
        Hmenu = new JMenu(LocaleManager.messages.getString("helpMenu"));
        HmenuItem = new JMenuItem(LocaleManager.messages.getString("helpPage"));
        HmenuItem1 = new JMenuItem(LocaleManager.messages.getString("aboutPage"));
        Hmenu.add(HmenuItem);
        Hmenu.add(HmenuItem1);
        add(Hmenu);
        
        revalidate();
        repaint();
    }
	
	
    private void openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("..\\saves"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getName();
            if (!c4.gameModel.isNewGame || c4.saveState) {
            	c4.gameModel = new GameBoard(c4, selectedFile);
            	
            } else if (fileName.equals(c4.gameModel.getWorkingFile().getName())) {
            	System.out.println("Not changing file.");
            
            } else {
            	File oldFile = c4.gameModel.getWorkingFile();
            	c4.gameModel = new GameBoard(c4, selectedFile);
            	System.out.println("deleting: " + oldFile.getAbsolutePath());
            	oldFile.delete();
            	
            }
            c4.gameModel.refreshBoard(false, false, false);
        }

    }
    
    /**
     * Changes the language of the menu items and labels based on the specified
     * locale.
     * 
     * @param locale the locale representing the language to be set
     */
    private void changeLanguage(Locale locale) {
    	Locale.setDefault(locale);
    	LocaleManager.messages = ResourceBundle.getBundle("message", locale);
    	
    	fileMenu.setText(LocaleManager.messages.getString("file"));
    	exitItem.setText(LocaleManager.messages.getString("exit"));
    	loadItem.setText(LocaleManager.messages.getString("open"));
    	saveItem.setText(LocaleManager.messages.getString("save"));

    	Smenu.setText(LocaleManager.messages.getString("scoreMenu"));
    	scoreDisplayItem.setText(LocaleManager.messages.getString("current"));
    	resetScoreItem.setText(LocaleManager.messages.getString("resetScore"));

    	Omenu.setText(LocaleManager.messages.getString("optionsMenu"));
    	resetBoardItem.setText(LocaleManager.messages.getString("resetBoard"));
    	setTurnItem.setText(LocaleManager.messages.getString("gameLength"));
    	
    	Lmenu.setText(LocaleManager.messages.getString("languageMenu"));
    	LmenuItem.setText(LocaleManager.messages.getString("english"));
    	LmenuItem1.setText(LocaleManager.messages.getString("french"));
    	
    	Hmenu.setText(LocaleManager.messages.getString("helpMenu"));
    	HmenuItem.setText(LocaleManager.messages.getString("helpPage"));
    	HmenuItem1.setText(LocaleManager.messages.getString("aboutPage"));
    }   	
}
