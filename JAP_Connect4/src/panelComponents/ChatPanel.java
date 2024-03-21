package panelComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CST8221.Panels;

/**
 * BottomPanel Class, panel container for the chat feild and send button.
 */
public class ChatPanel extends JPanel {
    /**
	 * auto-generated serial uid
	 */
	private static final long serialVersionUID = -5426800239199025595L;

	/**
	 * BottomPanel Constructor
	 */
	public ChatPanel() {
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(200, 100)); // Set the preferred size

        // Add components to the left panels
        JTextField chatField = new JTextField("Player chat box");
        
        chatField.setPreferredSize(new Dimension(1700, 90));
        
        JButton sendButton = new JButton(new ImageIcon(Panels.imgPath+"sendButtonOverlay.png"));
        sendButton.setPreferredSize(new Dimension(190, 90));
       
        
        
        add(chatField);
        add(sendButton);
    }
}