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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import CST8221.Connect4;
import panelComponents.BoardPanel;

public class GameBoard {
	public static final int BOARD_ROWS = 6;
	public static final int BOARD_COLS = 7;
	public GameBoardTile[][] tileList = new GameBoardTile[BOARD_ROWS][BOARD_COLS];
	private int[] columnDepth = {BOARD_ROWS, BOARD_ROWS, BOARD_ROWS, BOARD_ROWS, BOARD_ROWS, BOARD_ROWS, BOARD_ROWS};
	private final String path = "saves\\";
	private File workingFile;
	public byte winState; 
	public boolean isNewGame;

	private Connect4 c4;

	/**
	 * Method handling file IO
	 * 
	 * @return true to show file has been successfully updated, false if not.
	 */
	public boolean updateWorkingFile() {
		int lnTrack = 0;
		try (FileWriter fw = new FileWriter(this.workingFile)){
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
					
		
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		return true;
	}

	public File getWorkingFile() {
		return workingFile;
	}
	
	public GameBoard(Connect4 connect4, File gameFile) {
		this.c4 = connect4;
		winState = (byte) 00;
		
		// Create a blank board...
		if (gameFile == null) {
			isNewGame = true;
			try {
				long unixTime = System.currentTimeMillis() / 1000L;
				workingFile = new File(path + unixTime + ".cfour");
				workingFile.createNewFile();
				
			} catch (IOException e) {
				e.printStackTrace();
			}

			for (int i = 0 ; i < BOARD_ROWS ; i++) {
				for (int j = 0 ; j < BOARD_COLS ; j++) {
					int[] position = {i, j};
					tileList[i][j] = new GameBoardTile((byte) 00, position);
				}
			}
		
			updateWorkingFile();

		} else {
			isNewGame = false;
			loadGame(gameFile);
		}		
	}
	
	
	public boolean checkWin(byte targetTile) {
		final int MAGIC_WIN_LENGTH = 4;
		int r, c, i;
	
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
	
	
	public void updateWinLoss(byte winTile) {
		this.winState = winTile;
		
		JDialog dialog = new JDialog(c4, "CONGRADULATIONS!", true);
    	dialog.setSize(275, 120);
    	
    	if (winState == 03) {
    		
    	}
    	
        JPanel dialogPanel = new JPanel();
        dialogPanel.setPreferredSize(new Dimension(200,200));
        
        JLabel label = null;
        
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
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	c4.gb.refreshBoard(false);
            	dialog.dispose();
            }
        });
        
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	c4.gb.refreshBoard(false);
            	dialog.dispose();
            }
        });
        
        dialogPanel.add(okButton);
    	
    	dialog.add(dialogPanel);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
	}

	public void killBoard(boolean exitGame) {
		for (int i = 0; i < BOARD_COLS ; i++) {
			columnDepth[i] = BOARD_ROWS;
		}
			
		
		for (GameBoardTile[] tileRow : tileList) {
			for (GameBoardTile tile : tileRow) {
				tile.boardPosition[0] = 0;
				tile.boardPosition[1] = 0;
				
				tile.tileState = 0;
				
			}
		}
		if (!exitGame)
			updateWorkingFile();
		
	}
	
	public boolean loadGame(File file) {
		this.workingFile = file;
	    try (Scanner trk = new Scanner(file)){
	    	int currentTiles = 0;
	    	int row = -1;
	    	int col = 0;
	    	
	    	
	        while (trk.hasNextInt()) {
	        	if (currentTiles > BOARD_ROWS * BOARD_COLS) {
	        		System.out.println("Error: Save state could not be loaded.");
	        		return false;
	        	}
	        	currentTiles++;

	        	if (col % BOARD_COLS == 0) {
	        		row++;
	        		col = 0;
	        	}

	        	
	        	int num = trk.nextInt();
	        	if (num > 2 || num < 0) {
	        		System.out.println("Warning: Save state may be corrupted.");
	        		num = 0;
	        	}
	        	
	     
            	int[] position = {row, col};
            	tileList[row][col] = new GameBoardTile((byte) num, position);
            	
            	
	        	col++;
	        }
	        trk.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    return true;
	}
	
	public int[] colCheck() {
		int position = 0;
		int tempCol[] = {BOARD_ROWS, BOARD_ROWS, BOARD_ROWS, BOARD_ROWS, BOARD_ROWS, BOARD_ROWS, BOARD_ROWS};
		for (GameBoardTile[] tileRow : tileList) {
			for (GameBoardTile tile : tileRow) {
				if (tile.tileState > 0) {
					int col = position % BOARD_COLS;
					tempCol[col]--;
				}
				position++;
			}
		}
		
		columnDepth = tempCol;
		System.out.println(Arrays.toString(columnDepth));
		return columnDepth;
	}
	
	
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
		colCheck();
	}
    
    public void refreshBoard(boolean closeWindow) {
    	if (c4.currentTurn == 02)
    		c4.playerMove();
    	
    	
    	c4.timer.setStatus(3);
    	c4.timer.setStatus(1);
    	
    	killBoard(closeWindow);
        // Recreate the BoardPanel and add it to the container
        BoardPanel newBoardPanel = new BoardPanel(c4);
        c4.remove(c4.gbViewControl); // Remove the old BoardPanel
        c4.add(newBoardPanel); // Add the new BoardPanel
        c4.revalidate(); // Inform the layout manager to recalculate the layout
        c4.repaint(); // Refresh the UI
        c4.gbViewControl = newBoardPanel; // Update the reference	
    }
}
























