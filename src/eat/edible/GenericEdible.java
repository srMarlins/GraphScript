package eat.edible;

import com.sun.istack.internal.NotNull;

public class GenericEdible implements Edible {

    private String action;
    private String name;
    private int id;

    GenericEdible(@NotNull int itemId, @NotNull String itemName, @NotNull String action) {
        this.action = action;
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
    public String name() {
        return this.name;
    }

    @NotNull
    @Override
    public int id() {
        return this.id;
    }
}
