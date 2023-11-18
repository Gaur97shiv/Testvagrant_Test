import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ShoppingCart {
    public Map<String, double[]> basket = new HashMap<>();

    public void startBilling() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the total types of items: ");
        int totalTypes = scanner.nextInt();

        while (totalTypes-- > 0) {
            addItem();
        }

        maxGST();
        totalBill();
    }

    private void addItem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Item name: ");
        String item = scanner.next();

        System.out.println("Enter the qty of " + item + ": ");
        double qty = scanner.nextDouble();

        System.out.println("Enter the price of " + item + ": ");
        double price = scanner.nextDouble();

        System.out.println("Enter the GST of " + item + ": ");
        double gst = scanner.nextDouble();

        double discount = 0;
        if (price >= 500) {
            discount = (price / 100) * 5;
            price -= discount;
        }

        double tax = (price / 100) * gst;
        basket.put(item, new double[]{qty, price, gst, tax});
    }

    private void totalBill() {
        double totalAmount = 0;

        for (double[] values : basket.values()) {
            totalAmount += values[1] + values[3];
        }

        System.out.println("The total amount to be paid to the shopkeeper is: " + totalAmount);
    }

    private void maxGST() {
        double maxGST = 0;

        for (double[] values : basket.values()) {
            maxGST = Math.max(maxGST, values[3]);
        }

        for (Map.Entry<String, double[]> entry : basket.entrySet()) {
            if (entry.getValue()[3] == maxGST) {
                System.out.println("The maximum GST paid for " + entry.getKey() + ", and the amount is " + entry.getValue()[3]);
            }
        }
    }
}

public class testvargent {
    public static void main(String[] args) {
        ShoppingCart sc = new ShoppingCart();
        sc.startBilling();
    }
}
