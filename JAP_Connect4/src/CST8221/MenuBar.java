package CST8221;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/**
 * MenuBar component class
 */
public class MenuBar extends JMenuBar {
    /**
	 * auto-generated serial uid
	 */
	private static final long serialVersionUID = 1019707613887489945L;

	/**
	 * MenuBar Constructor
	 */
	public MenuBar() {
        // File
        JMenu Emenu = new JMenu("File");
        JMenuItem EmenuItem = new JMenuItem("Exit");
        JMenuItem EmenuItem1 = new JMenuItem("Open");
        JMenuItem EmenuItem2 = new JMenuItem("Save");
        EmenuItem.addActionListener(e -> System.exit(0));
        Emenu.add(EmenuItem);
        Emenu.add(EmenuItem1);
        Emenu.add(EmenuItem2);
        add(Emenu);

        // Connection
        JMenu Cmenu = new JMenu("Connection");
        JMenuItem CmenuItem = new JMenuItem("Host");
        JMenuItem CmenuItem1 = new JMenuItem("Join");
        JMenuItem CmenuItem2 = new JMenuItem("Close");
        Cmenu.add(CmenuItem);
        Cmenu.add(CmenuItem1);
        Cmenu.add(CmenuItem2);
        add(Cmenu);

        // Score
        JMenu Smenu = new JMenu("Score");
        JMenuItem SmenuItem = new JMenuItem("<html>Current score: <u>2-3</u></html>");
        JMenuItem SmenuItem1 = new JMenuItem("Reset score");
        JMenuItem SmenuItem2 = new JMenuItem("Score history");
        Smenu.add(SmenuItem);
        Smenu.add(SmenuItem1);
        Smenu.add(SmenuItem2);
        add(Smenu);

        // Options
        JMenu Omenu = new JMenu("Game Options");
        JMenuItem OmenuItem = new JMenuItem("Reset board");
        JMenuItem OmenuItem1 = new JMenuItem("Set game length");
        Omenu.add(OmenuItem);
        Omenu.add(OmenuItem1);
        add(Omenu);

        // Language
        JMenu Lmenu = new JMenu("Language");
        JMenuItem LmenuItem = new JMenuItem("English");
        JMenuItem LmenuItem1 = new JMenuItem("French");
        Lmenu.add(LmenuItem);
        Lmenu.add(LmenuItem1);
        add(Lmenu);

        // Help
        JMenu Hmenu = new JMenu("Help");
        JMenuItem HmenuItem = new JMenuItem("See help page");
        JMenuItem HmenuItem1 = new JMenuItem("See about page");
        Hmenu.add(HmenuItem);
        Hmenu.add(HmenuItem1);
        add(Hmenu);
    }
}
