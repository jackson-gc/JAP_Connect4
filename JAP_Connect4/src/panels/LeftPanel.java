package panels;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    public LeftPanel() {
        setBackground(Color.CYAN);
        setPreferredSize(new Dimension(600, 200)); // Set the preferred size

        // Add components to the left panel
        JLabel label = new JLabel("Left Panel");
        add(label);
    }
}
