package panelComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import CST8221.Panels;

public class LeftPanel extends JPanel {
	/**
	 * auto-generated serial uid
	 */
	private static final long serialVersionUID = -1527031301225314114L;
	/**
	 * Game board rows
	 */
	private static final int BOARD_ROWS = 6;
	/**
	 * Game board columns
	 */
	private static final int BOARD_COLS = 7;
	/**
	 * Grid unit width (px)
	 */
	private static final int GRID_WIDTH = 140;
	
	/**
	 * LeftPanel class, panel container for the game board.
	 */
    public LeftPanel() {
        setPreferredSize(new Dimension(1386, 100)); // Set the preferred size
       
        MatteBorder rightBoarder = new MatteBorder(0, 0, 0, 2, Color.GRAY);
		setBorder(rightBoarder);
        
        JPanel boardSlots = new JPanel(new GridLayout(1, BOARD_COLS));
        for (int i = 0 ; i < BOARD_COLS; i++) {
        	JButton gridling = new JButton();
        	gridling.setPreferredSize(new Dimension(GRID_WIDTH, 30));

        	boardSlots.add(gridling);
        }
        
        
        
        JPanel boardGrid = new JPanel(new GridLayout(BOARD_ROWS, BOARD_COLS));
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLS; j++) {
            	
            	ImageIcon icon = null;

            	
            	if (i ==  5 && j == 3)
            		icon = new ImageIcon(Panels.imgPath + "boardTileRed.png"); 

            	if (i ==  5 && j == 4)
            		icon = new ImageIcon(Panels.imgPath + "boardTileYellow.png");
            		
            	if (i ==  4 && j == 4)
            		icon = new ImageIcon(Panels.imgPath + "boardTileRed.png"); 

            	
            	if(icon == null)
            		icon = new ImageIcon(Panels.imgPath + "boardTileEmpty.png");   

                
            	JLabel gridling = new JLabel(icon);
            	gridling.setPreferredSize(new Dimension(GRID_WIDTH, 120));
            	boardGrid.add(gridling);
            }
        }

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
}
