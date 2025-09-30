
import javax.swing.*;
import java.awt.event.*;

public class Controller2 {
    private CashRegister cash;
    private Display display;
    private Scanner scanner;
    private String productsFilePath;

    public Controller2(CashRegister cash, Display display, Scanner scanner, String productsFilePath) {
        this.cash = cash;
        this.display = display;
        this.scanner = scanner;
        this.productsFilePath = productsFilePath;
        init();
    }

    private void init() {
        // load products into both scanner and cash register
        scanner.loadUPCsFromFile(productsFilePath);
        cash.loadProducts(productsFilePath);

        // wire scan button
        scanner.getScanButton().addActionListener(e -> {
            String upc = scanner.scanOnce();
            if (upc == null) {
                JOptionPane.showMessageDialog(null, "No UPCs loaded.");
                return;
            }
            Product p = cash.addItemByUPC(upc);
            if (p != null) {
                display.addLine(p.toString());
                display.setSubtotal(cash.getSubtotal());
            } else {
                display.addLine(upc + " UNKNOWN");
            }
        });
    }
}
