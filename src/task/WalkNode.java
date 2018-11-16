package task;

import org.dreambot.api.methods.map.Area;
import util.PriorityMatrix;

public class WalkNode extends RandomTaskNode {

    private Area walkArea;
    private PriorityMatrix priorityMatrix = new PriorityMatrix(PriorityMatrix.Priority.LOW, PriorityMatrix.Priority.LOW, PriorityMatrix.Priority.LOW);

    public WalkNode(Area to) {
        this.walkArea = to;
    }

    @Override
    public int priority() {
        return priorityMatrix.getPriorityValue();
    }

    @Override
    public boolean accept() {
        return !this.walkArea.contains(getLocalPlayer()) && !getLocalPlayer().isAnimating() && getWalking().shouldWalk();
    }

    @Override
    public int execute() {
        log("Walk: execute");
        if(this.walkArea.contains(this.getLocalPlayer()) || !getWalking().shouldWalk()) return super.execute();

        log("Walk: walking");
        getWalking().walk(walkArea.getRandomTile());

        return super.execute();
    }

    @Override
    int getMaxExecutionTime() {
        return 2000;
    }

    @Override
    int getMinExecutionTime() {
        return 1000;
    }
}
