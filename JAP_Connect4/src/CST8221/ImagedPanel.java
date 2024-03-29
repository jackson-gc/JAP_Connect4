package CST8221;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagedPanel extends JPanel {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 5030933783775250766L;
	String path;
	
	
	public ImagedPanel(String path) {
	this.path = path;
	}
	
	@Override
	public String toString() {
		return path;
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Paint the background image	
        g.drawImage(new ImageIcon(path).getImage(), 0, 0, getWidth(), getHeight(), this);
        }
	}