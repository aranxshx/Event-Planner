import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JButtonIcon extends JButton {

    private boolean highlightOnHover;

    public JButtonIcon(ImageIcon icon, boolean highlightOnHover) {
        super(icon);
        this.highlightOnHover = highlightOnHover;
        init();
    }

    private void init() {
        // Set the initial cursor to hand to indicate it's clickable
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add MouseAdapter to handle mouse events
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!highlightOnHover) {
                    // Prevent highlighting
                    Toolkit.getDefaultToolkit().setDynamicLayout(false);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!highlightOnHover) {
                    // Restore dynamic layout after mouse exit
                    Toolkit.getDefaultToolkit().setDynamicLayout(true);
                }
            }
        });
    }
}
