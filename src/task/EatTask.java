package task;

import org.dreambot.api.wrappers.items.Item;
import util.PriorityMatrix;

import java.util.Random;

public class EatTask extends RandomTaskNode {

    private int healLimit = 20;
    private int eatItemId = 315;
    private PriorityMatrix priorityMatrix = new PriorityMatrix(PriorityMatrix.Priority.HIGH, PriorityMatrix.Priority.HIGH, PriorityMatrix.Priority.HIGH);

    public EatTask(int healLimitPercentage, int eatItemId) {
        this.eatItemId = eatItemId;
        this.healLimit = healLimitPercentage;
    }

    @Override
    public int priority() {
        return priorityMatrix.getPriorityValue();
    }

    @Override
    public boolean accept() {
        return getCombat().getHealthPercent() <= this.healLimit;
    }

    @Override
    public int execute() {
        if (getCombat().getHealthPercent() > this.healLimit) super.execute();


        return super.execute();
    }

    @Override
    int getMaxExecutionTime() {
        return 150;
    }

    @Override
    int getMinExecutionTime() {
        return 50;
    }
}
