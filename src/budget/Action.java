package budget;

import java.util.Scanner;

public interface Action {

    void execute(Scanner scanner, BudgetManager manager);
}
