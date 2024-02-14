package panelComponents;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BottomPanel extends JPanel {
    public BottomPanel() {
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(200, 100)); // Set the preferred size

        // Add components to the left panels
        JLabel label = new JLabel("Chat Field");
        add(label);
    }
}