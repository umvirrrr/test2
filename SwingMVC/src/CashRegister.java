
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CashRegister {
    private HashMap<String, Product> products = new HashMap<>();
    private List<Product> scannedItems = new ArrayList<>();

    // load products file lines: UPC Product Price
    public void loadProducts(String path) {
        products.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                // split into three parts: upc, name, price
                String[] parts = line.split("\\s+", 3);
                if (parts.length < 3) continue;
                String upc = parts[0];
                String name = parts[1];
                String priceStr = parts[2].replace("$","");
                double price = Double.parseDouble(priceStr);
                Product p = new Product(upc, name, price);
                products.put(upc, p);
            }
        } catch (Exception e) {
            System.out.println("Failed to load products: " + e.getMessage());
        }
        System.out.println("Products loaded: " + products.size());
    }

    public Product addItemByUPC(String upc) {
        Product p = products.get(upc);
        if (p != null) {
            scannedItems.add(p);
        }
        return p;
    }

    public List<Product> getScannedItems(){ return scannedItems; }

    public double getSubtotal() {
        double s=0;
        for (Product p: scannedItems) s += p.getPrice();
        return s;
    }
}
