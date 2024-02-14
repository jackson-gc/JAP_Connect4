import javax.swing.*;
import java.awt.*;
import panels.*;

public class Panels extends JPanel {
    public Panels() {
        setLayout(new BorderLayout());

        // Adding panels from the Panels package
        TopRightPanel topRPanel = new TopRightPanel();
        BottomRightPanel botRPanel = new BottomRightPanel();
        LeftPanel leftPanel = new LeftPanel();
        BottomPanel bottomPanel = new BottomPanel();

        add(leftPanel, BorderLayout.WEST);
        add(topRPanel, BorderLayout.EAST);
        add(botRPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
