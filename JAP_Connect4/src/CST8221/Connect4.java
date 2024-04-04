package CST8221;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.ControllableTimer;
import model.GameBoard;
import panelComponents.SystemPanel;

/**
 * Connect4 class represents the main frame of the Connect 4 game.
 * This class extends JFrame and implements the ActionListener interface.
 */
public class Connect4 extends JFrame implements ActionListener {

    /**
     * auto-generated serial version UID
     */
    private static final long serialVersionUID = 5369068481793381074L;

    /** Flag to indicate whether the game state is saved */
    boolean saveState = false;

    /** Current player's turn (1 for red, 0 for yellow) */
    public byte currentTurn = 1;

    /** ControllableTimer instance for managing game timing */
    public ControllableTimer timer;

    /** Array to store game scores (index 0 for red, index 1 for yellow) */
    public int[] score = {0, 0};

    /** Panels instance for managing game panels */
    public Panels panel;

    /** GameBoard instance for representing the game board */
    public GameBoard gameModel;

    /** MenuBar instance for managing the game menu bar */
    private MenuBar menuBar;

    public NetworkManager networkManager;



    /**
     * Constructor for the Connect4 class.
     * Initializes the main frame properties and sets up the game components.
     */
    public Connect4(NetworkManager nm) {
        this.networkManager = nm;
        setTitle("Connect 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1920, 1080));
        setResizable(false);
        setLocation(960, 540);

        // Initializing game board object with no file
        gameModel = new GameBoard(this, null);

        // Initializing main view panels
        panel = new Panels(this);
        setContentPane(panel);
        networkManager.updateViewControl(panel);
        
        // Initializing parallel timer object
        timer = new ControllableTimer(this.panel.sysPanel);
        timer.start();

        // Initializing menu bar
        menuBar = new MenuBar(this);
        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);


        if (networkManager.isHost){
            //networkManager.sendPacket('@', "hello from host!");

        } else {
            //networkManager.sendPacket('@', "hello from client!");
            playerMove();
        }





        // Delete working file if it is not saved on window close
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!saveState) {
                    gameModel.getWorkingFile().delete();
                }
                dispose();
            }
        });
    }

    /**
     * Method to handle player moves and toggle turns between red and yellow.
     * @return The previous turn's value (1 for red, 0 for yellow)
     */
    public byte playerMove() {
        byte temp = currentTurn;

        // If red player is currently in play switch to yellow
        if (currentTurn == 1) {
            SystemPanel.redTurn.setEnabled(false);
            SystemPanel.redTurn.repaint();
            SystemPanel.yellowTurn.setEnabled(true);
            SystemPanel.yellowTurn.repaint();
            currentTurn++;
        // If yellow player is currently in play switch to red
        } else {
            SystemPanel.redTurn.setEnabled(true);
            SystemPanel.redTurn.repaint();
            SystemPanel.yellowTurn.setEnabled(false);
            SystemPanel.yellowTurn.repaint();
            currentTurn--;
        }
        // Return initial move;
        return temp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /**
     * Updates the score display item in the menu bar with the current scores.
     */
    public void updateScoreItem() {
        menuBar.scoreDisplayItem.setText("Current score: " + score[0] + "-" + score[1]);
    }

    /**
     * Main method to start the Connect4 game.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Connect4 connect4 = new Connect4(null);
            connect4.setVisible(true);
        });
    }
}
