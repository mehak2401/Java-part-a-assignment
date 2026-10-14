import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class ElectricityConnection {
    protected String type;
    protected double units;

    public ElectricityConnection(String type, double units) {
        this.type = type;
        this.units = units;
    }

    public abstract double calculateBill();

    public String getType() {
        return type;
    }
}

class HomeConnection extends ElectricityConnection {
    public HomeConnection(double units) {
        super("HOME", units);
    }

    @Override
    public double calculateBill() {
        if (units <= 100) {
            return units * 5.0;
        } else {
            return (100 * 5.0) + ((units - 100) * 7.0);
        }
    }
}

class ShopConnection extends ElectricityConnection {
    public ShopConnection(double units) {
        super("SHOP", units);
    }

    @Override
    public double calculateBill() {
        return (units * 8.0) + 100.0;
    }
}

class FactoryConnection extends ElectricityConnection {
    public FactoryConnection(double units) {
        super("FACTORY", units);
    }

    @Override
    public double calculateBill() {
        double bill = units * 6.0;
        return Math.max(1000.0, bill); // Minimum bill of 1000
    }
}

public class ElectricityConnectionBilling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;

        int n = scanner.nextInt();
        List<ElectricityConnection> connections = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double units = scanner.nextDouble();

            switch (type) {
                case "HOME":
                    connections.add(new HomeConnection(units));
                    break;
                case "SHOP":
                    connections.add(new ShopConnection(units));
                    break;
                case "FACTORY":
                    connections.add(new FactoryConnection(units));
                    break;
            }
        }

        double total = 0.0;
        for (ElectricityConnection conn : connections) {
            double bill = conn.calculateBill();
            total += bill;
            System.out.printf("%s: %.2f%n", conn.getType(), bill);
        }

        System.out.printf("Total: %.2f%n", total);
        scanner.close();
    }
}