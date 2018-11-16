package eat.edible;

import com.sun.istack.internal.NotNull;

public class GenericEdible implements Edible {

    private String action;
    private int hitPointValue;
    private String name;
    private int id;

    GenericEdible(@NotNull int itemId, @NotNull String itemName, @NotNull String action, @NotNull int hitPointValue) {
        this.action = action;
        this.hitPointValue = hitPointValue;
        this.name = itemName;
        this.id = itemId;
    }

    @NotNull
    @Override
    public String getAction() {
        return this.action;
    }

    @NotNull
    @Override
    public int hitPointValue() {
        return this.hitPointValue;
    }

    @NotNull
    @Override
    public String name() {
        return this.name;
    }

    @NotNull
    @Override
    public int id() {
        return this.id;
    }
}
