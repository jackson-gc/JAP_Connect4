package model;

import javax.swing.ImageIcon;

import CST8221.Panels;

/**
 * Represents a tile on the game board.
 */
public class GameBoardTile {
	
    /**
     * Position relative to the GameBoard.
     */
	public int[] boardPosition;
	
    /**
     * State of the current board tile:
     * - 0: Empty
     * - 1: Red Player
     * - 2: Yellow Player
     */
	protected byte tileState;
	
    /**
     * Constructs a GameBoardTile object with the given state and position.
     * 
     * @param givenState the state of the tile
     * @param pos the position of the tile
     */
	public GameBoardTile(byte givenState, int[] pos){
		this.tileState = givenState;
		this.boardPosition = pos;
	}
	
	/**
     * Gets the ImageIcon representing the image of the tile based on its state.
     * 
     * @return the ImageIcon representing the tile's image
     */
	public ImageIcon getImage() {
		ImageIcon img;
		switch (tileState){
			case 0:
				img = new ImageIcon(Panels.imgPath + "boardTileEmpty.png");
				break;
			case 1:
				img = new ImageIcon(Panels.imgPath + "boardTileRed.png"); 
				break;
			case 2:
				img = new ImageIcon(Panels.imgPath + "boardTileYellow.png"); 
				break;
			default:
				img = null;
				System.out.println("Error: current save file may be corrupt");
				break;
		}
		return img;
	}
	
	/**
     * Gets the state of the tile.
     * 
     * @return the state of the tile
     */
	public byte getTileState() {
		return this.tileState;
	}

}