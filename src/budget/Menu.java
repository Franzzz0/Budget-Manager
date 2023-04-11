package budget;

import java.util.Scanner;

public class Menu {
    private Action action;
    private final Scanner scanner;
    private final BudgetManager manager;

    public Menu(Scanner scanner, BudgetManager manager) {
        this(scanner, manager, null);
    }

    public Menu(Scanner scanner, BudgetManager manager, Action action) {
        this.scanner = scanner;
        this.manager = manager;
        this.action = action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void executeAction() {
        if (this.action == null) {
            return;
        }
        this.action.execute(this.scanner, this.manager);
    }
}
