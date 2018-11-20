package node.general;

import node.graph.GraphNode;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import util.Priority;

public class WalkNode extends GraphNode {

    private Area walkArea;

    public WalkNode(Area to) {
        this.walkArea = to;
    }

    @Override
    public boolean accept() {
        return !this.walkArea.contains(getLocalPlayer()) && !getLocalPlayer().isAnimating() && getWalking().shouldWalk();
    }

    @Override
    public int execute() {
        if (this.walkArea.contains(this.getLocalPlayer()) || !getWalking().shouldWalk()) return super.execute();

        getWalking().walk(walkArea.getRandomTile());

        MethodProvider.sleepUntil(() -> getLocalPlayer().isStandingStill(), getMaxExecutionTime());

        return super.execute();
    }

    @Override
    protected int getMaxExecutionTime() {
        return 2000;
    }

    @Override
    protected int getMinExecutionTime() {
        return 1000;
    }

    @Override
    protected int getTaskOrder() {
        return Priority.LOW.value();
    }

    @Override
    protected int getSituationalImportance() {
        return Priority.LOW.value();
    }
}
