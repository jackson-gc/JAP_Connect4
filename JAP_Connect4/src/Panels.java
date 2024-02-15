import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import panelComponents.BottomPanel;
import panelComponents.LeftPanel;
import panelComponents.RightPanel;

public class Panels extends JPanel {
    public Panels() {
    	final Color BG_BLUE = new Color(187, 212, 227);
        setLayout(new BorderLayout());

        // Adding panels from the Panels package
        RightPanel botRPanel = new RightPanel();
        LeftPanel leftPanel = new LeftPanel();
        BottomPanel bottomPanel = new BottomPanel();

        add(leftPanel, BorderLayout.WEST);
        add(botRPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
