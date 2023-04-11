package budget;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class BudgetManager implements Serializable {
    private final Map<PurchaseType, List<Purchase>> purchases;
    private double total;
    private double balance;

    public BudgetManager() {
        this.purchases = new HashMap<>();
        Arrays.stream(PurchaseType.values()).forEach(category -> this.purchases.put(category, new ArrayList<>()));
        this.total = 0;
        this.balance = 0;
    }

    public void addPurchase (Purchase purchase, PurchaseType category) {
        this.purchases.get(category).add(purchase);
        this.total += purchase.getAmount();
        this.balance -= purchase.getAmount();
        if (balance < 0) balance = 0;
    }

    public double getTotal() {
        return total;
    }

    public double getBalance() {
        return balance;
    }

    public List<Purchase> getAll() {
        return this.purchases.keySet().stream()
                .flatMap(key -> this.purchases.get(key).stream())
                .collect(Collectors.toList());
    }

    public List<Purchase> getCategory(PurchaseType category) {
        return this.purchases.get(category);
    }

    public boolean isPurchaseListEmpty() {
        for (PurchaseType key : this.purchases.keySet()) {
            if (!this.purchases.get(key).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void addIncome(double income) {
        this.balance += income;
    }
}
