package CST8221;
import java.awt.BorderLayout;
import java.nio.file.Paths;

import javax.swing.JPanel;

import panelComponents.ChatPanel;
import panelComponents.SystemPanel;

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

	public SystemPanel sysPanel;
	protected ChatPanel chatPanel;

	private Connect4 c4;
	
	/**
	 * Panels Constructor
	 */
	public Panels(Connect4 connect4) {
		this.c4 = connect4;
        setLayout(new BorderLayout());

        // Adding panels from the Panels package
        sysPanel = new SystemPanel(c4);
      	chatPanel = new ChatPanel();

        add(c4.gbViewControl, BorderLayout.WEST);
        add(sysPanel, BorderLayout.EAST);
        add(chatPanel, BorderLayout.SOUTH);
    }
}
