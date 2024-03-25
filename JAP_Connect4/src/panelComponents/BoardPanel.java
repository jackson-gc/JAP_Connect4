package panelComponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import CST8221.Connect4;
import CST8221.LocaleManager;
import CST8221.Panels;
import model.GameBoard;
import model.GameBoardTile;

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
	
    private Connect4 c4;
    
	/**
	 * LeftPanel class, panel container for the game board.
	 */
    public BoardPanel(Connect4 connect4) {
    	this.c4 = connect4;
        setPreferredSize(new Dimension(1386, 100)); // Set the preferred size
       
        MatteBorder rightBoarder = new MatteBorder(0, 0, 0, 2, Color.GRAY);
		setBorder(rightBoarder);
        
        JPanel boardSlots = new JPanel(new GridLayout(1, GameBoard.BOARD_COLS));
        for (int i = 0 ; i < GameBoard.BOARD_COLS; i++) {
        	JButton slot = new JButton();
        	slot.setPreferredSize(new Dimension(GRID_WIDTH, 30));
        	boardSlots.add(slot);
        	
        	// Add ActionListener to the button
            slot.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	byte initalPlayer = c4.currentTurn;
                    // Get the source of the event (the clicked button)
                    JButton clickedButton = (JButton) e.getSource();
                    
                    // Get the position of the clicked button in the GridLayout
                    int index = boardSlots.getComponentZOrder(clickedButton);
                    int col = index % GameBoard.BOARD_COLS;
                    
                    // Pass the row and column to a method to handle the click event
                   
                    if(c4.gb.colCheck()[col] > 0)
                    	c4.gbViewControl.dropInCol(col, c4.playerMove());

                    
                    c4.gb.printBoard();
                    c4.gb.checkWin(initalPlayer);
                    
                    int totalDepth = 0;
                    int[] tempColDepth = c4.gb.colCheck();
                    for (int depth : tempColDepth) {
                    	totalDepth += depth;
                    }
                    
                    if (totalDepth == 0) {
                    	c4.gb.updateWinLoss((byte) 03);
                    }
                }
            });
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
    
    public boolean dropInCol(int column, byte tileType) {
    	int floor = c4.gb.colCheck()[column];
    	if (floor == 0)
    		return false;

    	for (int i = 0; i < floor; i++) {
//    		 try {
//                 Thread.sleep(50);
                 targetUpdate(i, column, tileType);
                 if (i > 0) {
                	 targetUpdate(i-1, column, (byte) 0);
                 }
                 
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
    	}
    	int position[] = {floor - 1, column};
    	c4.gb.tileList[floor - 1][column] = new GameBoardTile(tileType, position);
    	c4.timer.setStatus(3);
    	c4.gb.colCheck();
    	c4.gb.updateWorkingFile();
    	return true;
    }
    
    
    private void targetUpdate(int row, int col, byte tileType) {
        // Assuming row and col are zero-based indices
        // Calculate the index in the grid
        int index = row * GameBoard.BOARD_COLS + col;
        
        // Access the specific JLabel
        Component[] components = boardGrid.getComponents();
        if (index >= 0 && index < components.length) {
            JLabel label = (JLabel) components[index];
            
            // Update the icon based on the new state
            ImageIcon newIcon;
            switch (tileType) {
                case 0:
                    newIcon = new ImageIcon(Panels.imgPath + "boardTileEmpty.png");
                    break;
                case 1:
                    newIcon = new ImageIcon(Panels.imgPath + "boardTileRed.png");
                    break;
                case 2:
                    newIcon = new ImageIcon(Panels.imgPath + "boardTileYellow.png");
                    break;
                default:
                    newIcon = null;
                    break;
            }
            
            // Set the new icon to the JLabel
            label.setIcon(newIcon);
            
            // Repaint the JLabel to reflect the changes
            label.repaint();
        }
    }
    
    
    private void fullPaintBoard() {
        boardGrid = new JPanel();
        boardGrid.setLayout(gridLayout);
        for (int i = 0; i < GameBoard.BOARD_ROWS; i++) {
            for (int j = 0; j < GameBoard.BOARD_COLS; j++) {
                ImageIcon icon;
                
                
                switch(c4.gb.tileList[i][j].getTileState()) {
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
                
                JLabel jl = new JLabel(icon);
                boardGrid.add(jl);
            }
        }
    }
    
    public void updateLanguage(Locale locale) {
    	LocaleManager.messages = ResourceBundle.getBundle("message", locale);
    }
}


