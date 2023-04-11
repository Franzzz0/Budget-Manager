package budget;

import java.util.List;
import java.util.Scanner;

public class ListMenu implements Action {
    private BudgetManager manager;

    @Override
    public void execute(Scanner scanner, BudgetManager manager) {
        this.manager = manager;
        System.out.println();
        if (manager.isPurchaseListEmpty()) {
            System.out.println("The purchase list is empty!\n");
            return;
        }
        while (true) {
            printMenu();
            switch(scanner.nextLine()) {
                case "1" -> printReport(PurchaseType.FOOD);
                case "2" -> printReport(PurchaseType.CLOTHES);
                case "3" -> printReport(PurchaseType.FUN);
                case "4" -> printReport(PurchaseType.OTHER);
                case "5" -> printReport(null);
                case "6" -> {
                    System.out.println();
                    return;
                }
                default -> System.out.println("\nUnknown command\n");
            }
        }
    }

    private void printReport(PurchaseType category) {
        System.out.println();
        double total;
        List<Purchase> list;
        if (category == null) {
            System.out.println("All:");
            list = manager.getAll();
            total = manager.getTotal();
        } else {
            System.out.println(category.getCategoryName() + ":");
            list = manager.getCategory(category);
            total = list.stream().mapToDouble(Purchase::getAmount).sum();
        }
        printList(list, total);
        System.out.println();
    }

    private void printList(List<Purchase> list, double total) {
        if (list.isEmpty()) {
            System.out.println("The purchase list is empty");
        } else {
            list.forEach(System.out::println);
            System.out.printf("Total sum: %s%n", CurrencyFormatter.getFormatted(total));
        }
    }

    private static void printMenu() {
        System.out.println("""
                Choose the type of purchases
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) All
                6) Back""");
    }
}
