package budget;

import java.util.Scanner;

public class PurchaseMenu implements Action {
    private Scanner scanner;
    private BudgetManager manager;

    @Override
    public void execute(Scanner scanner, BudgetManager manager) {
        this.scanner = scanner;
        this.manager = manager;
        startSubMenu();
    }

    private void startSubMenu() {
        System.out.println();
        while (true) {
            printMenu();
            switch(scanner.nextLine()) {
                case "1" -> addPurchase(PurchaseType.FOOD);
                case "2" -> addPurchase(PurchaseType.CLOTHES);
                case "3" -> addPurchase(PurchaseType.FUN);
                case "4" -> addPurchase(PurchaseType.OTHER);
                case "5" -> {
                    System.out.println();
                    return;
                }
                default -> System.out.println("\nUnknown command\n");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                Choose the type of purchase
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) Back""");
    }

    private void addPurchase(PurchaseType category) {
        System.out.println("\nEnter purchase name:");
        String name = scanner.nextLine();
        System.out.println("Enter its price:");
        double price;
        while (true) {
            try {
                price = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong price format! try again.");
            }
        }
        manager.addPurchase(new Purchase(name, price), category);
        System.out.println("Purchase was added!\n");
    }
}
