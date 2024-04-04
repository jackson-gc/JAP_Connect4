package panelComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import CST8221.Connect4;
import CST8221.ImagedPanel;
import CST8221.Panels;

/**
 * RightPanel Class panel container for the turn-display, turn timer, and chat/move box.
 */
public class SystemPanel extends JPanel {
    /**
	 * auto-generated serial uid
	 */
	private static final long serialVersionUID = -1448673650776121690L;
	
	/**
	 * displays if red player is currently moving 
	 */
	public static JLabel redTurn;
	
	/**
	 * displays if yellow player is currently moving 
	 */
	public static JLabel yellowTurn;
	
	/**
	 * displays time left in the current players turn
	 */
	public static JLabel turnTimeLeft = new JLabel();

	private static JTextArea chatMoveBox;
	
	/**
	 * the total alloted time to a given player turn
	 */
	public static int allotedTurnTime = 90;

	private Connect4 c4;
	
	/**
	 * Constructs a new SystemPanel with components for turn display, timer, and chat/move box.
	 *
	 * @param connect4 the Connect4 game instance
	 */
	public SystemPanel(Connect4 connect4) {
	    this.c4 = connect4; // Set the Connect4 game instance

	    // Define colors for the panel and its components
	    final Color panelBG = new Color(187, 212, 227);
	    final Color componentBG = new Color(201, 221, 237);

	    setBackground(panelBG); // Set background color of the panel
	    setPreferredSize(new Dimension(520, 100)); // Set the preferred size of the panel

	    /* Start Player Turn Display Sub-Panel */
	    JPanel playerTurnDisplay = new JPanel(); // Create a panel for displaying player turns
	    playerTurnDisplay.setPreferredSize(new Dimension(510, 140)); // Set the preferred size of the turn display panel
	    playerTurnDisplay.setBackground(componentBG); // Set background color of the turn display panel

	    JLabel yellowTurnLabel = new JLabel("Player 1"); // Label for yellow player's turn
	    yellowTurnLabel.setBorder(new EmptyBorder(4, 80, 0, 30)); // Set border for positioning

	    JLabel redTurnLabel = new JLabel("Player 0"); // Label for red player's turn
	    redTurnLabel.setBorder(new EmptyBorder(4, 30, 0, 80)); // Set border for positioning

	    redTurn = new JLabel(new ImageIcon(Panels.imgPath + "redTurn.png")); // Icon for red player's turn
	    redTurn.setPreferredSize(new Dimension(210, 100)); // Set preferred size for the icon
	    redTurn.setCursor(Cursor.getDefaultCursor()); // Set cursor for interaction
	    redTurn.setEnabled(c4.currentTurn == 01); // Enable/disable based on current turn

	    yellowTurn = new JLabel(new ImageIcon(Panels.imgPath + "yellowTurn.png")); // Icon for yellow player's turn
	    yellowTurn.setPreferredSize(new Dimension(210, 100)); // Set preferred size for the icon
	    yellowTurn.setCursor(Cursor.getDefaultCursor()); // Set cursor for interaction
	    yellowTurn.setEnabled(c4.currentTurn == 02); // Enable/disable based on current turn

	    // Add components to the turn display panel
	    playerTurnDisplay.add(redTurnLabel);
	    playerTurnDisplay.add(yellowTurnLabel);
	    playerTurnDisplay.add(redTurn);
	    playerTurnDisplay.add(yellowTurn);
	    /* End Player Turn Display Sub-Panel */

	    /* Start Player Timer Display Sub-Panel */
	    JPanel playerTimerDisplay = new JPanel(); // Create a panel for displaying the timer
	    playerTimerDisplay.setPreferredSize(new Dimension(510, 80)); // Set the preferred size of the timer display panel
	    playerTimerDisplay.setBackground(componentBG); // Set background color of the timer display panel

	    turnTimeLeft.setFont(turnTimeLeft.getFont().deriveFont(Font.BOLD, 24)); // Set font style for the timer text
	    turnTimeLeft.setPreferredSize(new Dimension(400, 72)); // Set preferred size for the timer text
	    turnTimeLeft.setHorizontalAlignment(JLabel.CENTER); // Set horizontal alignment of the timer text
	    turnTimeLeft.setVerticalAlignment(JLabel.CENTER); // Set vertical alignment of the timer text
	    turnTimeLeft.setBackground(Color.WHITE); // Set background color of the timer text
	    turnTimeLeft.setOpaque(true); // Enable opacity for the timer text

	    JLabel timerImg = new JLabel(new ImageIcon(Panels.imgPath + "timerIcon.png")); // Icon for the timer
	    timerImg.setPreferredSize(new Dimension(72, 72)); // Set preferred size for the timer icon

	    // Add components to the timer display panel
	    playerTimerDisplay.add(timerImg);
	    playerTimerDisplay.add(turnTimeLeft);
	    /* End Player Timer Display Sub-Panel */

	    /* Start Chat & Move Box Sub-Panel */
	    chatMoveBox = new JTextArea();
	    chatMoveBox.setPreferredSize(new Dimension(510, 680)); // Set the preferred size of the chat/move box panel
	    chatMoveBox.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Set border for the chat/move box panel
	    chatMoveBox.setOpaque(true); // Enable opacity for the chat/move box panel
		chatMoveBox.setEditable(false);
	    /* End Chat & Move Box Sub-Panel */

	    // Add sub-panels to the main SystemPanel
	    add(playerTurnDisplay);
	    add(playerTimerDisplay);
	    add(chatMoveBox);
	}


	public void sendChat(String msg){
		chatMoveBox.append(msg + "\n");
	}

	
	/**
	 * Updates the timer display with the time remaining.
	 * 
	 * @param timeElapsed the time elapsed since the turn started
	 */
	public void updateTimer(int timeElapsed) {
	    // Check if the time elapsed equals the allotted turn time
	    if (timeElapsed == allotedTurnTime) {
	        // Set the status of the timer to indicate time is up
	        c4.timer.setStatus(2);

	        // Create a dialog window to display a message
	        JDialog dialog = new JDialog(c4, "Times Up!", Dialog.ModalityType.APPLICATION_MODAL);
	        dialog.setLayout(new BorderLayout());
	        dialog.setUndecorated(true);
	        dialog.setSize(275, 120);
	        dialog.setLocationRelativeTo(null);

	        // Create an image panel based on the current player's turn
	        ImagedPanel panel = new ImagedPanel((c4.currentTurn == 01) ? (Panels.imgPath + "redTimeUp.png") : (Panels.imgPath + "yellowTimeUp.png"));
	        System.out.println(panel.toString());

	        panel.setPreferredSize(new Dimension(200, 200));

	        // Create a label to display the message based on the current player's turn
	        JLabel label = new JLabel((c4.currentTurn == 01) ? "Red player's time is up." : "Yellow player's time is up.");
	        label.setFont(new Font("Serif", Font.BOLD, 24));

	        // Create an OK button to close the dialog
	        JButton okButton = new JButton("OK");
	        okButton.addActionListener(e -> {
	            dialog.dispose();
	            c4.playerMove(); // Perform a player move
	            c4.timer.setStatus(1); // Reset the timer status
	            c4.timer.setStatus(3); // Restart the timer
	        });

	        // Add the label and OK button to the panel
	        panel.add(label);
	        panel.add(okButton);

	        // Add the panel to the dialog window
	        dialog.add(panel, BorderLayout.CENTER);

	        // Make the dialog window visible
	        dialog.setVisible(true);

	        // Reset the time elapsed to 0 after handling the timer event
	        timeElapsed = 0;
	    }

	    // Calculate the time left for the turn
	    int totalSeconds = (allotedTurnTime - timeElapsed);
	    int minLeft = totalSeconds / 60;
	    int secLeft = totalSeconds - (minLeft * 60);

	    // Format the seconds correctly (e.g., add leading zero if less than 10)
	    String secLeftCorrected = (secLeft < 10) ? "0" + secLeft : String.valueOf(secLeft);

	    // Update the text of a component to display the time left
	    turnTimeLeft.setText("Time Left | " + minLeft + ":" + secLeftCorrected);
	}
}
