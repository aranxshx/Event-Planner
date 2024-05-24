import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class RoundedButtons extends AbstractButton {

    private final int borderRadius;
    private Color color;

    public RoundedButtons(int borderRadius) {
        this.borderRadius = borderRadius;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
    }

    @Override
    public void updateUI() {
        super.updateUI();
        setUI(new BasicButtonUI()); // Use the default UI delegate
    }

    public void setColor(Color color) {
        this.color = color;
    }


    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (!enabled) {
            setBackground(getBackground().darker());
        } else {
            setBackground(color);
        }
    }
}
