package panelComponents;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CST8221.Panels;

/**
 * RightPanel Class panel container for the turn-display, turn timer, and chat/move box
 */
public class SystemPanel extends JPanel {
    /**
	 * auto-generated serial uid
	 */
	private static final long serialVersionUID = -1448673650776121690L;

	/**
	 * RightPanel Constructor
	 */
	public SystemPanel() {
    	final Color panelBG = new Color(187, 212, 227);
    	final Color componentBG = new Color(201, 221, 237);
    	
    	
        setBackground(panelBG);
        setPreferredSize(new Dimension(520, 100)); // Set the preferred size
        
        /* Start Player Turn Display Sub-Panel */
        JPanel playerTurnDisplay = new JPanel();
        playerTurnDisplay.setPreferredSize(new Dimension(510, 140));
        playerTurnDisplay.setBackground(componentBG);
     
        JLabel yellowTurnLabel = new JLabel("Player 1");
        yellowTurnLabel.setBorder(new EmptyBorder(4, 80, 0, 30));
            
        JLabel redTurnLabel = new JLabel("Player 0");
        redTurnLabel.setBorder(new EmptyBorder(4, 30, 0, 80));
        
        JLabel redTurn = new JLabel(new ImageIcon(Panels.imgPath + "redTurn.png"));
        redTurn.setPreferredSize(new Dimension(210,100));
        redTurn.setCursor(Cursor.getDefaultCursor());
        redTurn.setEnabled(false);
        
        JLabel yellowTurn = new JLabel(new ImageIcon(Panels.imgPath + "yellowTurn.png"));
        yellowTurn.setPreferredSize(new Dimension(210,100));
        yellowTurn.setCursor(Cursor.getDefaultCursor());
        yellowTurn.setEnabled(true);

        
        playerTurnDisplay.add(redTurnLabel);
        playerTurnDisplay.add(yellowTurnLabel);
        playerTurnDisplay.add(redTurn);
        playerTurnDisplay.add(yellowTurn);
        /* End Player Turn Display Sub-Panel */
        
        
        /* Start Player Timer Display Sub-Panel */
        JPanel playerTimerDisplay = new JPanel();
        playerTimerDisplay.setPreferredSize(new Dimension(510, 80));
        playerTimerDisplay.setBackground(componentBG);

        JLabel turnTimeLeft = new JLabel("<html>Time Left In Turn: <u>1:28<u/></html>");
        turnTimeLeft.setFont(turnTimeLeft.getFont().deriveFont(Font.BOLD, 24));	
        turnTimeLeft.setPreferredSize(new Dimension(400, 72));
        turnTimeLeft.setHorizontalAlignment(JLabel.CENTER);
        turnTimeLeft.setVerticalAlignment(JLabel.CENTER);
        turnTimeLeft.setBackground(Color.WHITE);
        turnTimeLeft.setOpaque(true);
      
        JLabel timerImg = new JLabel(new ImageIcon(Panels.imgPath + "timerIcon.png"));
        timerImg.setPreferredSize(new Dimension(72, 72));
        playerTimerDisplay.add(timerImg);
        playerTimerDisplay.add(turnTimeLeft);
        /* End Player Timer Display Sub-Panel */
        
        /* Start Chat & Move Box Sub-Panel */
        JLabel chatMoveBox = new JLabel("<html><i style=\"color:gray;\">Player 0's move...</i> <br /> Player 0: hello world!<br /> Player 1: Goodnight Moon.<br /><i style=\"color:gray;\">Player 0 moved slot 4</i><br /><i style=\"color:gray;\">Player 1's move...</i></html>");
        chatMoveBox.setPreferredSize(new Dimension(510, 680));
        chatMoveBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        chatMoveBox.setVerticalAlignment(JLabel.TOP);
        chatMoveBox.setOpaque(true);
       
        /* End Chat & Move Box Sub-Panel */
        
        add(playerTurnDisplay);
        add(playerTimerDisplay);
        add(chatMoveBox);

    }
}