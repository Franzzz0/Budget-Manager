package budget;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {
    private static final Locale locale = Locale.US;

    public static String getFormatted(double amount) {
        return NumberFormat.getCurrencyInstance(locale).format(amount);
    }
}
