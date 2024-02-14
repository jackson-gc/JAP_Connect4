package panels;

import javax.swing.*;
import java.awt.*;

public class BottomRightPanel extends JPanel {
    public BottomRightPanel() {
        setBackground(Color.YELLOW);
        setPreferredSize(new Dimension(400, 100)); // Set the preferred size

        // Add components to the left panel
        JLabel label = new JLabel("Bottom Right Panel");
        add(label);
    }
}