import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuBar mbar;
	JMenu menu, submenu;
	JMenuItem mitem;
	JButton button;
	JPanel panel;
	
	public Menu() {
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(500, 600));
		setResizable(false);
		
		button = new JButton("Start Game");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connect4 mainpage = new Connect4();
				mainpage.setVisible(true);
				dispose();
			}
		});
		
		panel = new JPanel();
		panel.add(button);
		add(panel);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Menu mainMenu = new Menu();
			mainMenu.setVisible(true);
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
