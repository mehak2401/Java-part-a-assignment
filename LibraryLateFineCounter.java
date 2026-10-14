import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class LibraryItem {
    protected String title;
    protected int daysLate;

    public LibraryItem(String title, int daysLate) {
        this.title = title;
        this.daysLate = daysLate;
    }

    public abstract double calculateFine();

    public String getTitle() {
        return title;
    }
}

class BookItem extends LibraryItem {
    public BookItem(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    public double calculateFine() {
        return daysLate * 2.0;
    }
}

class DvdItem extends LibraryItem {
    public DvdItem(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    public double calculateFine() {
        double fine = daysLate * 5.0;
        return Math.min(50.0, fine); // Max fine capped at 50
    }
}

class MagazineItem extends LibraryItem {
    public MagazineItem(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    public double calculateFine() {
        return daysLate * 1.0;
    }
}

public class LibraryLateFineCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;

        int n = scanner.nextInt();
        List<LibraryItem> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String title = scanner.next();
            int daysLate = scanner.nextInt();

            switch (type) {
                case "BOOK":
                    items.add(new BookItem(title, daysLate));
                    break;
                case "DVD":
                    items.add(new DvdItem(title, daysLate));
                    break;
                case "MAGAZINE":
                    items.add(new MagazineItem(title, daysLate));
                    break;
            }
        }

        double totalFines = 0.0;
        for (LibraryItem item : items) {
            double fine = item.calculateFine();
            totalFines += fine;
            System.out.printf("%s: %.2f%n", item.getTitle(), fine);
        }

        System.out.printf("Total Fines: %.2f%n", totalFines);
        scanner.close();
    }
}