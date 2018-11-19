package node.general;

import node.graph.GraphNode;
import util.Priority;

public class BankNode extends GraphNode {


    public BankNode() {

    }

    @Override
    protected int getMaxExecutionTime() {
        return 1000;
    }

    @Override
    protected int getMinExecutionTime() {
        return 5000;
    }

    @Override
    protected int getTaskOrder() {
        return Priority.HIGH.value();
    }

    @Override
    protected int getSituationalImportance() {
        return Priority.LOW.value() * (getInventory().getEmptySlots() / 27 * 10);
    }
}
