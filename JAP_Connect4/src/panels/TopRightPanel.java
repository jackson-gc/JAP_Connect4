package panels;

import javax.swing.*;
import java.awt.*;

public class TopRightPanel extends JPanel {
    public TopRightPanel() {
        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(400, 100)); // Set the preferred size

        // Add components to the left panel
        JLabel label = new JLabel("Top Right Panel");
        add(label);
    }
}