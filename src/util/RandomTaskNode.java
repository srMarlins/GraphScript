package util;

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

    protected abstract int getMaxExecutionTime();
    protected abstract int getMinExecutionTime();
}
