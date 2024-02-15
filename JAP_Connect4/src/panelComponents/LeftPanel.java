package panelComponents;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LeftPanel extends JPanel {
	private static final int BOARD_ROWS = 6;
	private static final int BOARD_COLS = 7;
	private static final int GRID_WIDTH = 140;
	
    public LeftPanel() {
        setPreferredSize(new Dimension(1385, 100)); // Set the preferred size

        
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
            		icon = new ImageIcon("src\\imageLib\\boardTileRed.png"); 

            	if (i ==  5 && j == 4)
            		icon = new ImageIcon("src\\imageLib\\boardTileYellow.png");
            		
            	if (i ==  4 && j == 4)
            		icon = new ImageIcon("src\\imageLib\\boardTileRed.png"); 

            	
            	if(icon == null)
            		icon = new ImageIcon("src\\imageLib\\boardTileEmpty.png");   

                
            	JLabel gridling = new JLabel(icon);
            	gridling.setPreferredSize(new Dimension(GRID_WIDTH, 120));
            	boardGrid.add(gridling);
            }
        }

        add(boardSlots);
        add(boardGrid);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Paint the background image
        g.drawImage(new ImageIcon("src\\imageLib\\redPile.png").getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
