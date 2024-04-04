package CST8221;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * ImagedPanel class represents a JPanel with an image background.
 * This class extends JPanel and is used to display an image as the panel background.
 */
public class ImagedPanel extends JPanel {

    /** Generated serial version UID */
    private static final long serialVersionUID = 5030933783775250766L;

    /** Path to the image file */
    String path;

    /**
     * Constructs an ImagedPanel with the specified image path.
     * @param path The path to the image file
     */
    public ImagedPanel(String path) {
        this.path = path;
    }

    /**
     * toString override returns the path
     * 
     * @return path returns the imaged panel's image path
     */
    @Override
    public String toString() {
        return path;
    }

    /**
     * Overrides the paintComponent method to paint the background image.
     * @param g The Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Paint the background image	
        g.drawImage(new ImageIcon(path).getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
