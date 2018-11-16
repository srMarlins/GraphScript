package general;

import util.PriorityMatrix;
import util.RandomTaskNode;

public class LoginTask extends RandomTaskNode {


    private PriorityMatrix priorityMatrix = new PriorityMatrix(PriorityMatrix.Priority.LOW, PriorityMatrix.Priority.LOW, PriorityMatrix.Priority.LOW);

    @Override
    public int priority() {
        return priorityMatrix.getPriorityValue();
    }

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
}
