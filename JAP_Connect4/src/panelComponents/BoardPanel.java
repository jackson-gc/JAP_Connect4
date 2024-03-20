package panelComponents;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

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
	
	public JPanel boardGrid;
	
	/**
	 * LeftPanel class, panel container for the game board.
	 */
    public BoardPanel() {
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
        boardGrid = new JPanel(new GridLayout(GameBoard.BOARD_ROWS, GameBoard.BOARD_COLS));
        for (int i = 0; i < GameBoard.BOARD_ROWS; i++) {
            for (int j = 0; j < GameBoard.BOARD_COLS; j++) {
            	
            	ImageIcon img = Connect4.gb.tileList[i][j].getImage();
            	JLabel gridling = new JLabel(img);


            	
            	gridling.setPreferredSize(new Dimension(GRID_WIDTH, 120));
         
            	boardGrid.add(gridling);
            	
            	
            }
        }
    } 
}


