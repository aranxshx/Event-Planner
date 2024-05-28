import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;


public class RoundedRectangle extends JPanel {
    Color blueColor = new Color(97, 113, 255);
    Border border = BorderFactory.createLineBorder(blueColor, 1);

    private final int borderRadius;
    private Color backgroundColor;
    private Color borderColor; 

    public RoundedRectangle(int borderRadius) {
        setOpaque(false);
        this.borderRadius = borderRadius;   
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
    }

    public void setColor(Color color) {
        this.backgroundColor = color;
    }
    
    public void setBorder(Color color) {
        this.borderColor = color;
        
    }
}