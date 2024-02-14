package panelComponents;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LeftPanel extends JPanel {
    public LeftPanel() {
        setBackground(Color.CYAN);
        setPreferredSize(new Dimension(1385, 100)); // Set the preferred size

        // Add components to the left panel
        JLabel label = new JLabel("Game Board");
        add(label);
    }
}
