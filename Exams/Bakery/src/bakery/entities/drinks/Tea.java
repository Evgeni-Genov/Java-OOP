package bakery.entities.drinks;

import bakery.entities.drinks.BaseDrink;

public class Tea extends BaseDrink {
    private static final double TEA_PRICE = 2.50;

    public Tea(String name, int portion, String brand) {
        super(name, portion, TEA_PRICE, brand);
    }
}
