package controler;

public class GameBoardTile {
	
	/**
	 * Position relative to the GameBoard.
	 */
	private int[] boardPosition;
	
	/**
	 * State of current board tile, 
	 * 
	 *  0 - Empty
	 *  1 - Player0 (host)
	 *  2 - Player1 (client)
	 */
	protected byte tileState;
	
	GameBoardTile(byte givenState, int[] pos){
		this.tileState = givenState;
		this.boardPosition = pos;
	}
	
	
	
	/**
	 * Updates the image of a given tile sprite based on tileState
	 * 
	 * @return T if update success; F if fail
	 */
	public boolean updateImage() {
		
		
		
		
		
		return true;
	}

}