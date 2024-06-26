package panelComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CST8221.Connect4;
import CST8221.Panels;

/**
 * BottomPanel Class, panel container for the chat field and send button.
 */
public class ChatPanel extends JPanel {
    /**
     * auto-generated serial uid
     */
    private static final long serialVersionUID = -5426800239199025595L;

    private Connect4 c4;
    /**
     * Constructs a new ChatPanel with a chat field and send button.
     */
    public ChatPanel(Connect4 connect4) {
        this.c4 = connect4;
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(200, 100)); // Set the preferred size


        


        // Add components to the panel
        JTextField chatField = new JTextField("");
        chatField.setPreferredSize(new Dimension(170, 90));
        
        JButton sendButton = new JButton(new ImageIcon(Panels.imgPath+"sendButtonOverlay.png"));
        sendButton.setPreferredSize(new Dimension(190, 90));

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = chatField.getText(); // Get the text from the chat field
                if (!message.isEmpty()) {
                    c4.panel.sysPanel.sendChat(message);
                    c4.networkManager.sendPacket('@', message); // Send the message using NetworkManager
                    chatField.setText(""); // Clear the chat field after sending the message
                }
            }
        });

        add(chatField);
        add(sendButton);
    }
}
