package eat.edible;

public class EdibleFactory {
    public static Edible newDrinkEdible(int itemId, String itemName, int hitPointValue) {
        return new GenericEdible(itemId, itemName, "Drink", hitPointValue);
    }

    public static Edible newEatEdible(int itemId, String itemName, int hitPointValue) {
        return new GenericEdible(itemId, itemName, "Drink", hitPointValue);
    }
}
