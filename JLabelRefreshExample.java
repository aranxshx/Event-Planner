import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JLabelRefreshExample {
    private static JLabel label;

    public static void main(String[] args) {
        JFrame frame = new JFrame("JLabel Refresh Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JLabel
        label = new JLabel("Initial Text");
        frame.getContentPane().add(label);

        // Schedule a task to update the text every 1 second
        Timer timer = new Timer(1000, new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the text
                label.setText("Updated Text " + count++);
            }
        });
        timer.start(); // Start the timer

        frame.pack();
        frame.setVisible(true);
    }
}
