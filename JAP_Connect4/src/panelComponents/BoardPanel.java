package panelComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import CST8221.Connect4;
import CST8221.Panels;
import controler.GameBoard;

public class BoardPanel extends JPanel {
	/**
	 * auto-generated serial uid
	 */
	private static final long serialVersionUID = -1527031301225314114L;
	/**
	 * Grid unit width (px)
	 */
	private static final int GRID_WIDTH = 140;
	
	public JPanel boardGrid = null;
    private GridLayout gridLayout = new GridLayout(GameBoard.BOARD_ROWS, GameBoard.BOARD_COLS);
	
	/**
	 * LeftPanel class, panel container for the game board.
	 */
    public BoardPanel(String heello) {
    	System.out.println(heello);
        setPreferredSize(new Dimension(1386, 100)); // Set the preferred size
       
        MatteBorder rightBoarder = new MatteBorder(0, 0, 0, 2, Color.GRAY);
		setBorder(rightBoarder);
        
        JPanel boardSlots = new JPanel(new GridLayout(1, GameBoard.BOARD_COLS));
        for (int i = 0 ; i < GameBoard.BOARD_COLS; i++) {
        	JButton slot = new JButton();
        	slot.setPreferredSize(new Dimension(GRID_WIDTH, 30));
        	boardSlots.add(slot);
        }
        
        fullPaintBoard();
        
        add(boardSlots);
        add(boardGrid);
        
    }
    
    /**
     * LeftPanel graphic override, sets the background to an image of our choosing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Paint the background image
        g.drawImage(new ImageIcon(Panels.imgPath + "redPile.png").getImage(), 0, 0, getWidth(), getHeight(), this);
    }
    
    
    
    private void fullPaintBoard() {
        System.out.println("painting...");
        boardGrid = new JPanel();
        boardGrid.setLayout(gridLayout);
        for (int i = 0; i < GameBoard.BOARD_ROWS; i++) {
            for (int j = 0; j < GameBoard.BOARD_COLS; j++) {
                ImageIcon icon;
                
                
                switch(Connect4.gb.tileList[i][j].tileState) {
                case 0:
                	icon = new ImageIcon(Panels.imgPath + "boardTileEmpty.png");
                	break;
                case 1:
                	icon = new ImageIcon(Panels.imgPath + "boardTileRed.png");
                	break;
                case 2:
                	icon = new ImageIcon(Panels.imgPath + "boardTileYellow.png");
                	break;
                default:
                	icon = null;
                	break;
                	
                }
                System.out.println("adding: " + icon);
                
                JLabel jl = new JLabel(icon);
                boardGrid.add(jl);
                

            }
        }

        
    }//int tile = Connect4.gb.tileList[i][j].getTileState();
}


