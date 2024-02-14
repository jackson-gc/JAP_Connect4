package panelComponents;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class RightPanel extends JPanel {
    public RightPanel() {
        setBackground(Color.YELLOW);
        setPreferredSize(new Dimension(520, 100)); // Set the preferred size
        
        /* Start Player Turn Display Sub-Panel */
        JPanel playerTurnDisplay = new JPanel();
        playerTurnDisplay.setPreferredSize(new Dimension(510, 140));
        playerTurnDisplay.setBackground(Color.ORANGE);
     
        JLabel yellowTurnLabel = new JLabel("Player 1");
        yellowTurnLabel.setBorder(new EmptyBorder(4, 80, 0, 30));
            
        JLabel redTurnLabel = new JLabel("Player 0");
        redTurnLabel.setBorder(new EmptyBorder(4, 30, 0, 80));
        
        JButton redTurn = new JButton("");
        redTurn.setPreferredSize(new Dimension(210,100));
        redTurn.setCursor(Cursor.getDefaultCursor());
        redTurn.setEnabled(true);
        
        JButton yellowTurn = new JButton("");
        yellowTurn.setPreferredSize(new Dimension(210,100));
        yellowTurn.setCursor(Cursor.getDefaultCursor());
        yellowTurn.setEnabled(false);

        
        playerTurnDisplay.add(redTurnLabel);
        playerTurnDisplay.add(yellowTurnLabel);
        playerTurnDisplay.add(redTurn);
        playerTurnDisplay.add(yellowTurn);
        /* End Player Turn Display Sub-Panel */
        
        
        /* Start Player Timer Display Sub-Panel */
        JPanel playerTimerDisplay = new JPanel();
        playerTimerDisplay.setPreferredSize(new Dimension(510, 80));
        playerTimerDisplay.setBackground(Color.RED);

        JLabel turnTimeLeft = new JLabel("Time Left In Turn: 1:28");
        turnTimeLeft.setFont(turnTimeLeft.getFont().deriveFont(Font.BOLD, 24));	
        turnTimeLeft.setPreferredSize(new Dimension(400, 72));
        turnTimeLeft.setHorizontalAlignment(JLabel.CENTER);
        turnTimeLeft.setVerticalAlignment(JLabel.CENTER);
        turnTimeLeft.setBackground(Color.LIGHT_GRAY);
        turnTimeLeft.setOpaque(true);
      
        JButton timerImg = new JButton(new ImageIcon("timerIcon.png"));
        timerImg.setPreferredSize(new Dimension(72, 72));
        timerImg.setEnabled(false);
        
 
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