package CST8221;
import java.awt.BorderLayout;
import java.nio.file.Paths;

import javax.swing.JPanel;

import panelComponents.BottomPanel;
import panelComponents.LeftPanel;
import panelComponents.RightPanel;

/**
 * Panel wrapper class that sets a layout for other panels.
 */
public class Panels extends JPanel {
    /**
	 * auto-generated serial uid
	 */
	private static final long serialVersionUID = -6764656409124794105L;
	/**
	 * Public Path to imageLib, compatible with /bin and /src executions
	 */
	public static final String imgPath = Paths.get(".").toAbsolutePath().toString() + "\\imageLib\\";

	/**
	 * Panels Constructor
	 */
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
