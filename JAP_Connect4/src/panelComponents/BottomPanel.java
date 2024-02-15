package panelComponents;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BottomPanel extends JPanel {
    public BottomPanel() {
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(200, 100)); // Set the preferred size

        // Add components to the left panels
        JTextField chatField = new JTextField("Player chat box");
        
        chatField.setPreferredSize(new Dimension(1700, 90));
        
        JButton sendButton = new JButton("SEND");
        sendButton.setPreferredSize(new Dimension(190, 90));
        
        
        add(chatField);
        add(sendButton);
    }
}