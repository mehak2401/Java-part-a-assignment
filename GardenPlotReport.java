import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Plot {
    protected String owner;
    protected String shapeName;

    public Plot(String owner, String shapeName) {
        this.owner = owner;
        this.shapeName = shapeName;
    }

    public abstract double calculateArea();

    public String getOwner() {
        return owner;
    }

    public String getShapeName() {
        return shapeName;
    }
}

class CirclePlot extends Plot {
    private double radius;

    public CirclePlot(String owner, double radius) {
        super(owner, "CIRCLE");
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class RectanglePlot extends Plot {
    private double length;
    private double width;

    public RectanglePlot(String owner, double length, double width) {
        super(owner, "RECTANGLE");
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }
}

class TrianglePlot extends Plot {
    private double base;
    private double height;

    public TrianglePlot(String owner, double base, double height) {
        super(owner, "TRIANGLE");
        this.base = base;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}

public class GardenPlotReport {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;

        int n = scanner.nextInt();
        List<Plot> plots = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String shape = scanner.next();
            String owner = scanner.next();

            switch (shape) {
                case "CIRCLE":
                    double radius = scanner.nextDouble();
                    plots.add(new CirclePlot(owner, radius));
                    break;
                case "RECTANGLE":
                    double length = scanner.nextDouble();
                    double width = scanner.nextDouble();
                    plots.add(new RectanglePlot(owner, length, width));
                    break;
                case "TRIANGLE":
                    double base = scanner.nextDouble();
                    double height = scanner.nextDouble();
                    plots.add(new TrianglePlot(owner, base, height));
                    break;
            }
        }

        double totalArea = 0.0;
        for (Plot plot : plots) {
            double area = plot.calculateArea();
            totalArea += area;
            System.out.printf("%s (%s): %.2f%n", plot.getOwner(), plot.getShapeName(), area);
        }

        System.out.printf("Total Area: %.2f%n", totalArea);
        scanner.close();
    }
}