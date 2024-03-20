package controler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GameBoard {
	private final String path = "saves\\";
	private File workingFile;
	private final int ROWS = 6;
	private final int COLS = 7;
	private GameBoardTile[][] tileList = new GameBoardTile[ROWS][COLS];
	
	private byte winState; 

	/**
	 * Method handling file IO
	 * 
	 * @return true to show file has been successfully updated, false if not.
	 */
	private boolean updateWorkingFile() {
		try (FileWriter fw = new FileWriter(this.workingFile)){
			for (GameBoardTile[] tileRow : tileList) {
				for (GameBoardTile tile : tileRow) {
					fw.append(tile.tileState + "\n");
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

	
	
	GameBoard(File gameFile) {
		winState = (byte) 00;
		
		// Create a blank board...
		if (gameFile == null) {
			try {
				long unixTime = System.currentTimeMillis() / 1000L;
				workingFile = new File(path + unixTime + ".cfour");
				workingFile.createNewFile();
				
				
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			

			
			
			for (int i = 0 ; i < ROWS ; i++) {
				for (int j = 0 ; j < COLS ; j++) {
					int[] position = {i, j};
					this.tileList[i][j] = new GameBoardTile((byte) 00, position);
				}
			}
		
			updateWorkingFile();
			
			
			
			
		} else {
			loadGame(gameFile);
			
			
		}		
	}
	
	
	/**
	 * Default constructor chains up
	 */
	GameBoard(){ this(null); }
	
	
	public boolean checkWin() {
		return false;
	}
	
	public void updateWinLoss() {
		
	}

	public void killBoard() {
		
	}
	
	public void saveGame() {
		
		
	}
	
	public void loadGame(File file) {
	    try ( Scanner trk = new Scanner(file)){
	    	int i = 0, j = 0;
	        while (trk.hasNextLine()) {
	            int num = Integer.parseInt(trk.nextLine());
	            if(j > 6) {
	            	i++;
	            	j = 0;
	            	
	            	int[] position = {i, j};
	            	tileList[i][j] = new GameBoardTile((byte) num, position);
	            	System.out.println(num);
	            }
	            
	            j++;
	        }
	        trk.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	
	
	public void timesUp() {
		
	}
	
	
	
	
	public static void main(String[] args) {
		File na = new File("saves\\1710902522.cfour");
		GameBoard gb = new GameBoard(na);
		
	}
	
	
}
