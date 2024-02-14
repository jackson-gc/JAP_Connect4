import javax.swing.*;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        // File
        JMenu Emenu = new JMenu("File");
        JMenuItem EmenuItem = new JMenuItem("Exit");
        EmenuItem.addActionListener(e -> System.exit(0));
        Emenu.add(EmenuItem);
        add(Emenu);

        // Connection
        JMenu Cmenu = new JMenu("Connection");
        JMenuItem CmenuItem = new JMenuItem("Exit");
        CmenuItem.addActionListener(e -> System.exit(0));
        Cmenu.add(CmenuItem);
        add(Cmenu);

        // Score
        JMenu Smenu = new JMenu("Score");
        JMenuItem SmenuItem = new JMenuItem("Exit");
        SmenuItem.addActionListener(e -> System.exit(0));
        Smenu.add(SmenuItem);
        add(Smenu);

        // Options
        JMenu Omenu = new JMenu("Options");
        JMenuItem OmenuItem = new JMenuItem("Exit");
        OmenuItem.addActionListener(e -> System.exit(0));
        Omenu.add(OmenuItem);
        add(Omenu);

        // Language
        JMenu Lmenu = new JMenu("Language");
        JMenuItem LmenuItem = new JMenuItem("Exit");
        LmenuItem.addActionListener(e -> System.exit(0));
        Lmenu.add(LmenuItem);
        add(Lmenu);

        // Help
        JMenu Hmenu = new JMenu("Help");
        JMenuItem HmenuItem = new JMenuItem("Exit");
        HmenuItem.addActionListener(e -> System.exit(0));
        Hmenu.add(HmenuItem);
        add(Hmenu);
    }
}
