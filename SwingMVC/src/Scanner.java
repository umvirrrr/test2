
// Scanner that reads products file and returns a random UPC on scan
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Scanner {
    private ArrayList<String> upcs = new ArrayList<>();
    private Random rand = new Random();
    private JButton scanButton;
    private JFrame frame;
    private JPanel scannerPanel;

    public Scanner() {
        // UI for scanner: a simple window with a Scan button
        frame = new JFrame("Scanner");
        scannerPanel = new JPanel();
        scanButton = new JButton("Scan");
        scannerPanel.add(scanButton);
        frame.getContentPane().add(scannerPanel);
        frame.setSize(120, 100);
        frame.setLocation(650,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Load UPCs from a products file with lines: UPC Product Price
    public void loadUPCsFromFile(String path) {
        upcs.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                // expect first token is UPC
                String[] parts = line.split("\\s+", 2);
                if (parts.length >= 1) upcs.add(parts[0]);
            }
        } catch (Exception e) {
            System.out.println("Failed to load UPCs: " + e.getMessage());
        }
        System.out.println("Loaded " + upcs.size() + " UPCs.");
    }

    // Return a random UPC (or null if none)
    public String scanOnce() {
        if (upcs.isEmpty()) return null;
        int i = rand.nextInt(upcs.size());
        String upc = upcs.get(i);
        System.out.println("Scanned UPC: " + upc);
        return upc;
    }

    public JButton getScanButton() { return scanButton; }
    public JFrame getFrame() { return frame; }
    public JPanel getScannerPanel() { return scannerPanel; }
}
