package node;

import node.graph.GraphNode;
import util.Priority;

public class RootNode extends GraphNode {
    @Override
    protected int getMaxExecutionTime() {
        return 10000;
    }

    @Override
    protected int getMinExecutionTime() {
        return 500;
    }

    @Override
    protected int getTaskOrder() {
        return Priority.HIGH.value();
    }

    @Override
    protected int getSituationalImportance() {
        return Priority.HIGH.value();
    }
}
