package CST8221;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Connect4 extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    public Connect4() {
        setTitle("Connect 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1920, 1080));
        setResizable(false);

        // Panels
        Panels panel = new Panels();
        setContentPane(panel);

        // Menu bar
        MenuBar menuBar = new MenuBar();
        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Connect4 connect4 = new Connect4();
            connect4.setVisible(true);
        });
    }
}
