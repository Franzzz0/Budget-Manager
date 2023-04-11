package budget;

import java.util.Scanner;

public class ShowBalanceAction implements Action{
    @Override
    public void execute(Scanner scanner, BudgetManager manager) {
        System.out.println();
        System.out.println("Balance: " + CurrencyFormatter.getFormatted(manager.getBalance()));
        System.out.println();
    }
}
