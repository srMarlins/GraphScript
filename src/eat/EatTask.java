package eat;

import com.sun.istack.internal.NotNull;
import eat.edible.Edible;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.items.Item;
import util.PriorityMatrix;
import util.RandomTaskNode;

public class EatTask extends RandomTaskNode {

    private final int healLimit;
    private final Edible edible;
    private final PriorityMatrix priorityMatrix = new PriorityMatrix(PriorityMatrix.Priority.LOW, PriorityMatrix.Priority.HIGH, PriorityMatrix.Priority.HIGH);

    public EatTask(Edible food, int healLimitPercentage) {
        this.edible = food;
        this.healLimit = healLimitPercentage;
    }

    @Override
    public int priority() {
        return priorityMatrix.getPriorityValue();
    }

    @Override
    public boolean accept() {
        log("Eat: accept");
        return getCombat().getHealthPercent() <= this.healLimit
                && getInventory().get(item -> item != null && item.getName().equals(edible.name())) != null;
    }

    @Override
    public int execute() {
        log("Eat: Execute");
        if (getCombat().getHealthPercent() > this.healLimit) super.execute();

        if (getTabs().isOpen(Tab.INVENTORY) || getTabs().open(Tab.INVENTORY)) {
            consumeItem(getFoodItem());
        }

        return super.execute();
    }

    @Override
    protected int getMaxExecutionTime() {
        return 150;
    }

    @Override
    protected int getMinExecutionTime() {
        return 50;
    }

    private Item getFoodItem() {
        return getInventory().get(item -> item != null && item.getName().equals(edible.name()));
    }

    private boolean consumeItem(@NotNull Item item) {
        return item != null && item.interact(edible.getAction());
    }
}
