package controler;

import javax.swing.ImageIcon;

import CST8221.Panels;

public class GameBoardTile {
	
	/**
	 * Position relative to the GameBoard.
	 */
	public int[] boardPosition;
	
	/**
	 * State of current board tile, 
	 * 
	 *  0 - Empty
	 *  1 - Player0 (host)
	 *  2 - Player1 (client)
	 */
	protected byte tileState;
	
	public GameBoardTile(byte givenState, int[] pos){
		this.tileState = givenState;
		this.boardPosition = pos;
	}
	
	
	

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
	
	public byte getTileState() {
		return this.tileState;
	}
	
	/**
	 * Updates the image of a given tile sprite based on tileState
	 * 
	 * @return T if update success; F if fail
	 */
	public void setImage(){
	
	}

}