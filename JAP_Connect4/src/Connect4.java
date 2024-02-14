import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Connect4 extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    public Connect4() {
        setTitle("Connect 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 1000));
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
