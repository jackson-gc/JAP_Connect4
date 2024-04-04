package CST8221;

import java.awt.BorderLayout;
import java.nio.file.Paths;

import javax.swing.JPanel;

import panelComponents.BoardPanel;
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

	/**
	 * System panel public declaration
	 */
	public SystemPanel sysPanel;
	
	/**
	 * Chat panel (currently largely unused)
	 */
	protected ChatPanel chatPanel;

	/** 
	 * BoardPanel instance for controlling game board view 
	 */
    public BoardPanel boardPanel;
	
	/**
	 * Connect 4 object instance
	 */
	private Connect4 c4;
	
	/**
	 * Constructs a new Panels object for managing the layout of other panels.
	 * 
	 * @param connect4 the main Connect4 game instance
	 */
	public Panels(Connect4 connect4) {
		this.c4 = connect4;
        setLayout(new BorderLayout());

        // Adding panels from the Panels package
		boardPanel = new BoardPanel(c4);
        sysPanel = new SystemPanel(c4);
      	chatPanel = new ChatPanel(c4);

        add(boardPanel, BorderLayout.WEST);
        add(sysPanel, BorderLayout.EAST);
        add(chatPanel, BorderLayout.SOUTH);
    }
}
