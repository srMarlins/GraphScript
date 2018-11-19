package node.general;

import node.graph.GraphNode;
import util.Priority;

public class LoginNode extends GraphNode {


    @Override
    public boolean accept() {
        return getClient().isLoggedIn();
    }

    @Override
    public int execute() {
        return super.execute();
    }

    @Override
    protected int getMaxExecutionTime() {
        return 20000;
    }

    @Override
    protected int getMinExecutionTime() {
        return 5000;
    }

    @Override
    protected int getTaskOrder() {
        return Priority.LOW.value();
    }

    @Override
    protected int getSituationalImportance() {
        return getClient().isLoggedIn() ? Priority.LOW.value() : Priority.HIGH.value();
    }
}
