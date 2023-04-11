package budget;

import java.io.Serializable;

public enum PurchaseType implements Serializable {
    FOOD ("Food"),
    CLOTHES ("Clothes"),
    FUN ("Entertainment"),
    OTHER ("Other");

    private final String CategoryName;

    PurchaseType(String name) {
        this.CategoryName = name;
    }

    public String getCategoryName() {
        return CategoryName;
    }
}
