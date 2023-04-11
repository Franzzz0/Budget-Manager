package budget;

import java.io.Serializable;

public class Purchase implements Serializable {
    private final String name;
    private final double amount;

    public Purchase(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + CurrencyFormatter.getFormatted(this.amount);
    }
}
