package panels;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    public BottomPanel() {
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(200, 100)); // Set the preferred size

        // Add components to the left panel
        JLabel label = new JLabel("Bottom Panel");
        add(label);
    }
}