package budget;

import java.util.*;

public class SortMenu implements Action{
    private Scanner scanner;
    private BudgetManager manager;
    @Override
    public void execute(Scanner scanner, BudgetManager manager) {
        this.scanner = scanner;
        this.manager = manager;
        System.out.println();
        while (true) {
            printMenu();
            switch(scanner.nextLine()) {
                case "1" -> sort(null);
                case "2" -> sortByType();
                case "3" -> sortByCategory();
                case "4" -> {
                    System.out.println();
                    return;
                }
                default -> System.out.println("\nUnknown command\n");
            }
        }
    }

    private void sortByCategory() {
        System.out.println();
        System.out.println("""
                Choose the type of purchase
                1) Food
                2) Clothes
                3) Entertainment
                4) Other""");
        switch (scanner.nextLine()) {
            case "1" -> sort(PurchaseType.FOOD);
            case "2" -> sort(PurchaseType.CLOTHES);
            case "3" -> sort(PurchaseType.FUN);
            case "4" -> sort(PurchaseType.OTHER);
            default -> System.out.println("Unknown category.");
        }
    }

    private void sortByType() {
        System.out.println();
        System.out.println("Types:");
        List<Purchase> typesList = new ArrayList<>();
        for (PurchaseType type : PurchaseType.values()) {
            double amount = manager.getCategory(type).stream().mapToDouble(Purchase::getAmount).sum();
            Purchase category = new Purchase(type.getCategoryName() + " -", amount);
            typesList.add(category);
        }
        printSorted(manager.getTotal(), typesList);
        System.out.println();
    }

    private void sort(PurchaseType category) {
        System.out.println();
        if (manager.isPurchaseListEmpty()) {
            System.out.println("The purchase list is empty!\n");
            return;
        }
        double total;
        List<Purchase> list;
        if (category == null) {
            System.out.println("All:");
            list = manager.getAll();
            total = manager.getTotal();
        } else {
            list = manager.getCategory(category);
            if (list.isEmpty()) {
                System.out.println("The purchase list is empty!\n");
                return;
            }
            System.out.println(category.getCategoryName() + ":");
            total = list.stream().mapToDouble(Purchase::getAmount).sum();
        }
        printSorted(total, list);
        System.out.println();
    }

    private static void printSorted(double total, List<Purchase> list) {
        list.sort(Comparator.comparing(Purchase::getAmount).thenComparing(Purchase::getName).reversed());
        list.forEach(System.out::println);
        System.out.printf("Total sum: %s%n", CurrencyFormatter.getFormatted(total));
    }

    private void printMenu() {
        System.out.println("""
                How do you want to sort?
                1) Sort all purchases
                2) Sort by type
                3) Sort certain type
                4) Back""");
    }
}
