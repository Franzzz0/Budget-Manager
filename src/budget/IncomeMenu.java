package budget;

import java.util.Scanner;

public class IncomeMenu implements Action {

    @Override
    public void execute(Scanner scanner, BudgetManager manager) {
        System.out.println("\nEnter income:");
        try {
            double income = Double.parseDouble(scanner.nextLine());
            manager.addIncome(income);
        } catch (NumberFormatException e) {
            System.out.println("Wrong format. Income not added.");
            return;
        }
        System.out.println("Income was added!\n");
    }
}
