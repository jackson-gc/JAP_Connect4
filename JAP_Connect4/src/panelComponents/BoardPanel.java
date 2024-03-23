package panelComponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import CST8221.Connect4;
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
        	
        	// Add ActionListener to the button
            slot.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	byte initalPlayer = Connect4.currentTurn;
                    // Get the source of the event (the clicked button)
                    JButton clickedButton = (JButton) e.getSource();
                    
                    // Get the position of the clicked button in the GridLayout
                    int index = boardSlots.getComponentZOrder(clickedButton);
                    int col = index % GameBoard.BOARD_COLS;
                    
                    // Pass the row and column to a method to handle the click event
                    
                    if(Connect4.gb.columnDepth[col] > 0) {
                    	if (!Connect4.gbViewControl.dropInCol(col, Connect4.playerMove()))
                    		System.out.println("drop failed");
                    }
                    		
                    else
                    	System.out.println("Cannot add to this colum.");
                    
                    Connect4.gb.printBoard();
                    
                    
                    if(Connect4.gb.checkWin(initalPlayer)) {
                    	JDialog dialog = new JDialog(Connect4.connect4, "CONGRADULATIONS!", true);
                    	dialog.setSize(275, 120);
       
                        
                        JPanel jp = new JPanel();
                        jp.setPreferredSize(new Dimension(200,200));
                        
                        JLabel label = null;
                    	if (initalPlayer == 1) {
                    		label = new JLabel("THE RED PLAYER HAS WON THE GAME");
     
                    	} else if (initalPlayer == 2) {
                    		label = new JLabel("THE YELLOW PLAYER HAS WON THE GAME");
                    		
                    	}
                    	
                    	jp.add(label);
                        JButton okButton = new JButton("OK");
                        okButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                            	Connect4.gb.killBoard(false);
                            	Connect4.gb.refreshBoard();
                                dialog.dispose();
                            }
                        });
                        
                        dialog.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                            	Connect4.gb.killBoard(false);
                            	Connect4.gb.refreshBoard();
                            	dialog.dispose();
                            }
                        });
                        
                        jp.add(okButton);
                    	
                    	dialog.add(jp);
                        
                        dialog.setVisible(true);
                        
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
    	int floor = Connect4.gb.columnDepth[column];
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
        Connect4.gb.tileList[floor - 1][column] = new GameBoardTile(tileType, position);
        Connect4.gb.colCheck();
    	Connect4.gb.updateWorkingFile();
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
                
                
                switch(Connect4.gb.tileList[i][j].getTileState()) {
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
}


