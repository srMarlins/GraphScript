package eat.edible;

public class EdibleFactory {
    public static Edible newDrinkEdible(int itemId, String itemName) {
        return new GenericEdible(itemId, itemName, "Drink");
    }

    public static Edible newEatEdible(int itemId, String itemName) {
        return new GenericEdible(itemId, itemName, "Eat");
    }
}
