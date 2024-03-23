package panelComponents;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CST8221.Connect4;
import CST8221.ImagedPanel;
import CST8221.Panels;

/**
 * RightPanel Class panel container for the turn-display, turn timer, and chat/move box
 */
public class SystemPanel extends JPanel {
    /**
	 * auto-generated serial uid
	 */
	private static final long serialVersionUID = -1448673650776121690L;
	
	public static JLabel redTurn;
	public static JLabel yellowTurn;
	public static JLabel turnTimeLeft = new JLabel();
	public static int allotedTurnTime = 90;

	private Connect4 c4;
	
	/**
	 * RightPanel Constructor
	 */
	public SystemPanel(Connect4 connect4) {
		this.c4 = connect4;
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
        
        redTurn = new JLabel(new ImageIcon(Panels.imgPath + "redTurn.png"));
        redTurn.setPreferredSize(new Dimension(210,100));
        redTurn.setCursor(Cursor.getDefaultCursor());
        redTurn.setEnabled(c4.currentTurn == 01);
        
        yellowTurn = new JLabel(new ImageIcon(Panels.imgPath + "yellowTurn.png"));
        yellowTurn.setPreferredSize(new Dimension(210,100));
        yellowTurn.setCursor(Cursor.getDefaultCursor());
        yellowTurn.setEnabled(c4.currentTurn == 02);

        
        playerTurnDisplay.add(redTurnLabel);
        playerTurnDisplay.add(yellowTurnLabel);
        playerTurnDisplay.add(redTurn);
        playerTurnDisplay.add(yellowTurn);
        /* End Player Turn Display Sub-Panel */
        
        
        /* Start Player Timer Display Sub-Panel */
        JPanel playerTimerDisplay = new JPanel();
        playerTimerDisplay.setPreferredSize(new Dimension(510, 80));
        playerTimerDisplay.setBackground(componentBG);
        
        
        
        
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
	
	public void updateTimer(int timeElapsed) {
		
		if(timeElapsed == allotedTurnTime) {
			c4.playerMove();
			c4.timer.setStatus(2);
			
			JDialog dialog = new JDialog(c4, "Times Up!", Dialog.ModalityType.APPLICATION_MODAL);
			dialog.setUndecorated(true);
	        dialog.setSize(275, 120);
	        dialog.setLocationRelativeTo(null);

	        ImagedPanel panel = new ImagedPanel("");
	        panel.setPreferredSize(new Dimension(200,200));
	        
	        
	        
	        
	        
	        dialog.add(panel);

	        dialog.setVisible(true);
	        dialog.dispose(); 
	        
			c4.timer.setStatus(3);
			c4.timer.setStatus(1);
		}
		
		
		int totalSeconds = (allotedTurnTime - timeElapsed);
		int minLeft = totalSeconds/60;
		int secLeft = totalSeconds - (minLeft * 60);
		String secLeftCorrected = (secLeft < 10) ? "0" + secLeft : String.valueOf(secLeft);
		turnTimeLeft.setText("Time Left | " + minLeft + ":" + secLeftCorrected);

	}
	
	
}