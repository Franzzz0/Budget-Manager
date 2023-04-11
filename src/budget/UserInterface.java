package budget;

import java.io.*;
import java.util.Scanner;

public class UserInterface{
    private final Scanner scanner;
    private BudgetManager manager;
    private final String filePath;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.manager = new BudgetManager();
        this.filePath = "purchases.txt";
//        this.filePath = ".\\Budget Manager\\task\\src\\purchases.txt";
    }

    public void start() {
        while (true) {
            printMenu();
            Menu menu = new Menu(scanner, manager);
            Action action = null;
            switch (scanner.nextLine()) {
                case "1" -> action = new IncomeMenu();
                case "2" -> action = new PurchaseMenu();
                case "3" -> action = new ListMenu();
                case "4" -> action = new ShowBalanceAction();
                case "5" -> saveToFile();
                case "6" -> loadFromFile();
                case "7" -> action = new SortMenu();
                case "0" -> {
                    System.out.println("\nBye!");
                    return;
                }
                default -> System.out.println("\nUnknown command\n");
            }
            menu.setAction(action);
            menu.executeAction();
        }
    }

    private void loadFromFile() {
        System.out.println();
        File file = new File(this.filePath);
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis);
             ObjectInputStream input = new ObjectInputStream(bis)
        ) {
            this.manager = (BudgetManager) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Purchases were loaded!");
        System.out.println();
    }

    private void saveToFile() {
        System.out.println();
        File file = new File(this.filePath);
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ObjectOutputStream output = new ObjectOutputStream(bos)
        ){
            output.writeObject(manager);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Purchases were saved!");
        System.out.println();
    }

    private void printMenu() {
        System.out.println("""
                Choose your action:
                1) Add income
                2) Add purchase
                3) Show list of purchases
                4) Balance
                5) Save
                6) Load
                7) Analyze (Sort)
                0) Exit""");
    }
}
