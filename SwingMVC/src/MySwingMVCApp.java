
public class MySwingMVCApp {

    public static void main(String[] args) {
        // Assemble new Cash Register MVC pieces
        String productsFile = "products.txt";
        CashRegister cash = new CashRegister();
        Display display = new Display("Cash Register Display");
        Scanner scanner = new Scanner();
        Controller2 controller = new Controller2(cash, display, scanner, productsFile);
        // Now use the scanner window's Scan button to scan items
    }

}
