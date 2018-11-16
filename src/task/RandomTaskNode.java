package task;

import org.dreambot.api.script.TaskNode;

import java.util.Random;

public abstract class RandomTaskNode extends TaskNode {

    private Random random = new Random();

    @Override
    public boolean accept() {
        return false;
    }

    @Override
    public int execute() {
        return random.nextInt((getMaxExecutionTime() - getMinExecutionTime()) + 1) + getMaxExecutionTime();
    }

    abstract int getMaxExecutionTime();
    abstract int getMinExecutionTime();


}
