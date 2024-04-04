package model;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import CST8221.Connect4;
import CST8221.LocaleManager;
import panelComponents.BoardPanel;
import panelComponents.SystemPanel;

/**
 * Class representing the game board for Connect4.
 */
public class GameBoard {
	/**
	 * Represents the number of rows on the game board.
	 */
	public static final int BOARD_ROWS = 6;

	/**
	 * Represents the number of columns on the game board.
	 */
	public static final int BOARD_COLS = 7;

	/**
	 * Represents the 2D array of GameBoardTile objects representing the game board.
	 */
	public GameBoardTile[][] tileList = new GameBoardTile[BOARD_ROWS][BOARD_COLS];

	/**
	 * Represents the depth of each column in the game board.
	 * The index of this array corresponds to the column index on the game board.
	 */
	private int[] columnDepth = {BOARD_ROWS, BOARD_ROWS, BOARD_ROWS, BOARD_ROWS, BOARD_ROWS, BOARD_ROWS, BOARD_ROWS};

	/**
	 * Represents the path where game save files are stored.
	 */
	private final String path = "saves\\";

	/**
	 * Represents the working file being used for game operations.
	 */
	private File workingFile;

	/**
	 * Represents the byte value indicating the win state of the game.
	 */
	public byte winState;

	/**
	 * Represents a boolean flag indicating whether the game is a new one.
	 */
	public boolean isNewGame;


	private Connect4 c4;

    /**
     * Updates the working file with current game state.
     * 
     * @return true if the file has been successfully updated, false otherwise
     */
	public boolean updateWorkingFile() {
		int lnTrack = 0;
		// try new file writer
		try (FileWriter fw = new FileWriter(this.workingFile)){
			// update score to file
			fw.append(c4.score[0] + "-" + c4.score[1] + "\n");
			// update allotedTime to file
			fw.append(SystemPanel.allotedTurnTime + "\n");
			// update currentTurn to file
			fw.append(c4.currentTurn + "\n");
			// update current game state to file
			for (GameBoardTile[] tileRow : tileList) {
				for (GameBoardTile tile : tileRow) {
					lnTrack++;
		        	if (lnTrack % 7 == 0) {
		        		fw.append(tile.tileState + "\n");
		        	} else  {
		        		fw.append(tile.tileState + " ");
		        	}
				}
			}
		// catch file not found exception
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, LocaleManager.messages.getString("errorFileNotFound"));
		// catch ioexception
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, LocaleManager.messages.getString("errorIO"));
		}
		return true;
	}

    /**
     * Gets the working file.
     * 
     * @return the working file
     */
	public File getWorkingFile() {
		return workingFile;
	}
	
    /**
     * Constructs a new GameBoard instance.
     * 
     * @param connect4 the main Connect4 game instance
     * @param gameFile the file representing the saved game state
     */
	public GameBoard(Connect4 connect4, File gameFile) {
		this.c4 = connect4;
		winState = (byte) 00;
		
		// Create a blank board...
		if (gameFile == null) {
			isNewGame = true;
			try {
				// name the new file the current system time
				long unixTime = System.currentTimeMillis() / 1000L;
				workingFile = new File(path + unixTime + ".cfour");
				System.out.println(workingFile.getAbsolutePath().toString());
				if (workingFile.createNewFile()) {
					System.out.println("File created successfully.");
				} else {
					System.out.println("File already exists.");
				}
			
			// catch ioexception
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Initialize an empty board
			for (int i = 0 ; i < BOARD_ROWS ; i++) {
				for (int j = 0 ; j < BOARD_COLS ; j++) {
					int[] position = {i, j};
					tileList[i][j] = new GameBoardTile((byte) 00, position);
				}
			}
			// update working file with newly made board
			updateWorkingFile();

		} else {
			// pull info from file
			isNewGame = false;
			loadGame(gameFile);
		}		
	}
	
    /**
     * Checks if a player has won the game.
     * 
     * @param targetTile the player's tile type (1 for RED, 2 for YELLOW)
     * @return true if the player has won, false otherwise
     */
	public boolean checkWin(byte targetTile) {
		// length of a connect 4 win (4 tiles in a row)
		final int MAGIC_WIN_LENGTH = 4;
		int r, c, i;
	
		// check horizontal win states
		for (r = 0; r < BOARD_ROWS; r++) {
			for (c = 0; c < BOARD_COLS - MAGIC_WIN_LENGTH; c++) {
				boolean found = true;
	          	for (i = 0; i < MAGIC_WIN_LENGTH; i++) {
	          		if (tileList[r][c + i].tileState != targetTile) {
	          			found = false;
	          			break;
	          		}
	          	}
	         	if (found) {
	         		updateWinLoss(targetTile);	         		
	         		return true;
	         	}
			}
		}
		
		// check vertical win states
		for (r = 0; r <= BOARD_ROWS - MAGIC_WIN_LENGTH; r++) {
            for (c = 0; c < BOARD_COLS; c++) {
            	boolean found = true;
            	for (i = 0; i < MAGIC_WIN_LENGTH; i++) {
            		if (tileList[r + i][c].tileState != targetTile) {
            			found = false;
            			break;           			
            		}
            	}
            	if (found) {
            		updateWinLoss(targetTile);            		
            		return true;
            	}
            }
		}
		
		// check diagonal win states
		for (r = 0; r <= BOARD_ROWS - MAGIC_WIN_LENGTH; r++) {
            for (c = 0; c <= BOARD_COLS - MAGIC_WIN_LENGTH; c++) {
                boolean foundForward = true;
                boolean foundBackward = true;
                for (i = 0; i < MAGIC_WIN_LENGTH; i++) {
                    if (tileList[r + i][c + i].tileState != targetTile)
                        foundForward = false;
                    if (tileList[r + i][c + MAGIC_WIN_LENGTH - 1 - i].tileState != targetTile)
                        foundBackward = false;
                }
                if (foundForward || foundBackward) {
                	updateWinLoss(targetTile);
                	return true;
                }
            }
        }
		return false;
	}
	
	 /**
     * Updates the game state upon winning or losing.
     * 
     * @param winTile the winning player's tile type (1 for RED, 2 for YELLOW, 3 for tie)
     */
	public void updateWinLoss(byte winTile) {
		this.winState = winTile;
		
		// win dialog
		JDialog dialog = new JDialog(c4, "CONGRADULATIONS!", true);
    	dialog.setSize(275, 120);
    	
        JPanel dialogPanel = new JPanel();
        dialogPanel.setPreferredSize(new Dimension(200,200));
        
        JLabel label = null;
        
        // win state switch checking which player has won (if any)
        switch(winState) {
        case 01:
        	label = new JLabel("The RED Player Has Won The Game");
        	c4.score[0]++;
        	c4.updateScoreItem();
        	break;
        	
        case 02:
        	label = new JLabel("The YELLOW Player Has Won The Game");
        	c4.score[1]++;
        	c4.updateScoreItem();
        	break;
        	
        case 03:
        	dialog.setTitle("Cats Game");
        	label = new JLabel("No one wins!");
        	break;
        	
        default:
        	break;
        }
        
    	c4.timer.setStatus(2);
    	
    	dialogPanel.add(label);
        JButton okButton = new JButton("OK");
        
        // refresh board
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	c4.gameModel.refreshBoard(false, true, true);
            	dialog.dispose();
            }
        });
        
        // refresh board
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	c4.gameModel.refreshBoard(false, true, true);
            	dialog.dispose();
            }
        });
        
        dialogPanel.add(okButton);
    	
    	dialog.add(dialogPanel);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
	}
	
    /**
     * Resets the board for a new game or clean shutdown.
     * 
     * @param exitGame true if exiting the game, false otherwise
     */
	public void killBoard(boolean exitGame) {
		for (int i = 0; i < BOARD_COLS ; i++) {
			columnDepth[i] = BOARD_ROWS;
		}
			
		
		// set board to defaults
		for (GameBoardTile[] tileRow : tileList) {
			for (GameBoardTile tile : tileRow) {
				tile.boardPosition[0] = 0;
				tile.boardPosition[1] = 0;
				
				tile.tileState = 0;
				
			}
		}
		// if player is exiting the application then do not update the working file
		if (!exitGame)
			updateWorkingFile();
	}
	
    /**
     * Loads a game state from a file.
     * 
     * @param file the file containing the saved game state
     * @return true if the game state was successfully loaded, false otherwise
     */
	public boolean loadGame(File file) {
		this.workingFile = file;
		
		// Open a new scanner to read loaded file
		int lineTrack = 0;
	    try (Scanner trk = new Scanner(file)){
	    	int currentTiles = 0;
	    	int row = -1;
	    	int col = 0;
	    	
	    	// Take each line as a string, then parse the string to desired result
	    	while (trk.hasNextLine()) {
	    		final int SCORE_LINE = 1;
	    		final int TURN_LENGTH_LINE = 2;
	    		final int CURRENT_TURN_LINE = 3;
	    		final int GAME_BOARD_LINE = 4;

	    		lineTrack++;

		    	// check line arg
		    	switch (lineTrack) {
		    	
		    	// reading score line
		    	case SCORE_LINE:
		    		String scoreLine = trk.nextLine();
		    		String[] parts = scoreLine.split("-");

		    		if (parts.length == 2) {
		    		    try {
		    		        int redScore = Integer.parseInt(parts[0].trim());
		    		        int yellowScore = Integer.parseInt(parts[1].trim());
		    		        
		    		        
		    		        c4.score[0] = redScore;
		    		        c4.score[1] = yellowScore;
		    		        
		    		    } catch (NumberFormatException e) {
		    		    	 JOptionPane.showMessageDialog(null, LocaleManager.messages.getString("warningSaveStateCorrupted"));
		    		    }
		    		} else {
		    			 JOptionPane.showMessageDialog(null, LocaleManager.messages.getString("warningSaveStateCorrupted"));
		    		}
		    		c4.updateScoreItem();
		    		break;
		    		
		    	// reading turn length line
		    	case TURN_LENGTH_LINE:
		    		String turnLengthLine = trk.nextLine();
		    		try {
		    			SystemPanel.allotedTurnTime = Integer.parseInt(turnLengthLine);
		    			c4.timer.setStatus(3);
		    			
		    			
	    		    } catch (NumberFormatException e) {
	    		    	 JOptionPane.showMessageDialog(null, LocaleManager.messages.getString("warningSaveStateCorrupted"));
	    		        
	    		    }
		    		break;
		    		
		    	// reading current tun length line
		    	case CURRENT_TURN_LINE:
		    		String currentTurnLine = trk.nextLine();
		    		if (c4.currentTurn != (byte) Integer.parseInt(currentTurnLine)) {
		    			c4.playerMove();
		    		}
		    		
		    		break;
		    	// start of reading the game board
		    	case GAME_BOARD_LINE:
		    		// take all ints and add them to the game board
			        while (trk.hasNextInt()) {
			        	if (currentTiles > BOARD_ROWS * BOARD_COLS) {
			        		 JOptionPane.showMessageDialog(null, LocaleManager.messages.getString("warningSaveStateCorrupted"));
			        		return false;
			        	}
			        	currentTiles++;

			        	if (col % BOARD_COLS == 0) {
			        		row++;
			        		col = 0;
			        	}
			        	
			        	int num = trk.nextInt();
			        	if (num > 2 || num < 0) {
			        		 JOptionPane.showMessageDialog(null, LocaleManager.messages.getString("warningSaveStateCorrupted"));
			        		num = 0;
			        	}
			     
		            	int[] position = {row, col};
		            	tileList[row][col] = new GameBoardTile((byte) num, position);
		            	
		            	
			        	col++;
			        }
		    		break;
		    	default:
		    		System.out.println(trk.nextLine());
		    	}
		    	// update board
		    	refreshBoard(false, false, false);
	    	}
	        trk.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    return true;
	}
	
	
    /**
     * Checks the current column depths on the board.
     * 
     * @return an array representing the current column depths
     */
	public int[] colCheck() {
		int position = 0;
		int tempCol[] = {BOARD_ROWS, BOARD_ROWS, BOARD_ROWS, BOARD_ROWS, BOARD_ROWS, BOARD_ROWS, BOARD_ROWS};
		// check number of chips in each column
		for (GameBoardTile[] tileRow : tileList) {
			for (GameBoardTile tile : tileRow) {
				if (tile.tileState > 0) {
					int col = position % BOARD_COLS;
					tempCol[col]--;
				}
				position++;
			}
		}
		
		// update and return column depth
		columnDepth = tempCol;
		System.out.println(Arrays.toString(columnDepth));
		return columnDepth;
	}
	
	
    /**
     * Prints the current state of the game board.
     */
    public void printBoard() {
    	int lnTrack = 0;
		for (GameBoardTile[] tileRow : tileList) {
			for (GameBoardTile tile : tileRow) {
				lnTrack++;
	        	if (lnTrack % 7 == 0) {
	        		System.out.print(tile.tileState + "\n");
	        	} else  {
	        		System.out.print(tile.tileState + " ");
	        	}
			}
		}
		// check columns
		colCheck();
	}
    
    /**
     * Refreshes the game board UI.
     * 
     * @param closeWindow true if closing the window, false otherwise
     * @param killBoard true if killing the board, false otherwise
     * @param updateTurn true if updating the turn, false otherwise
     */
    public void refreshBoard(boolean closeWindow, boolean killBoard, boolean updateTurn) {
    	// flip player turn
    	if (updateTurn) {
    		if (c4.currentTurn == 02)
    			c4.playerMove();    		
    	}
    	
    	// reset and start the timer
    	c4.timer.setStatus(3);
    	c4.timer.setStatus(1);
    	
    	// reset board
    	if (killBoard)
    		killBoard(closeWindow);
    	
        // Recreate the BoardPanel and add it to the container
        BoardPanel newBoardPanel = new BoardPanel(c4);
        c4.remove(c4.panel.boardPanel); // Remove the old BoardPanel
        c4.add(newBoardPanel); // Add the new BoardPanel
        c4.revalidate(); // Inform the layout manager to recalculate the layout
        c4.repaint(); // Refresh the UI
        c4.panel.boardPanel = newBoardPanel; // Update the reference	
    }
}








