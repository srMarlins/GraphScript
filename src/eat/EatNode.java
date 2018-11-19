package eat;

import com.sun.istack.internal.NotNull;
import eat.edible.Edible;
import node.graph.GraphNode;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.items.Item;
import util.Priority;

public class EatNode extends GraphNode {

    private final int healLimit;
    private final Edible edible;

    public EatNode(Edible food, int healLimitPercentage) {
        this.edible = food;
        this.healLimit = healLimitPercentage;
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
        if (getCombat().getHealthPercent() > this.healLimit) return super.execute();

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

    @Override
    protected int getTaskOrder() {
        return Priority.HIGH.value();
    }

    @Override
    protected int getSituationalImportance() {
        return Priority.HIGH.value() / getCombat().getHealthPercent();
    }

    private Item getFoodItem() {
        return getInventory().get(item -> item != null && item.getName().equals(edible.name()));
    }

    private boolean consumeItem(@NotNull Item item) {
        return item != null && item.interact(edible.getAction());
    }
}
