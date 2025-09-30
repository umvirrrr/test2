
import javax.swing.*;
import java.awt.*;

public class Display {
    private JFrame frame;
    private JTextArea listArea;
    private JLabel subtotalLabel;

    public Display(String title) {
        frame = new JFrame(title);
        frame.setSize(400, 300);
        frame.setLocation(100,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listArea = new JTextArea();
        listArea.setEditable(false);
        JScrollPane sp = new JScrollPane(listArea);
        subtotalLabel = new JLabel("Subtotal: $0.00");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(sp, BorderLayout.CENTER);
        frame.getContentPane().add(subtotalLabel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public void addLine(String line) {
        listArea.append(line + "\n");
    }

    public void setSubtotal(double amount) {
        subtotalLabel.setText("Subtotal: $" + String.format("%.2f", amount));
    }
}
