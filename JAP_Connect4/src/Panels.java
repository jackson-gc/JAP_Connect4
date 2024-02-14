import java.awt.BorderLayout;

import javax.swing.JPanel;

import panelComponents.BottomPanel;
import panelComponents.LeftPanel;
import panelComponents.RightPanel;

public class Panels extends JPanel {
    public Panels() {
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
