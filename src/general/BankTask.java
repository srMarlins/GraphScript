package general;

import util.RandomTaskNode;

public class BankTask extends RandomTaskNode {
    @Override
    protected int getMaxExecutionTime() {
        return 1000;
    }

    @Override
    protected int getMinExecutionTime() {
        return 5000;
    }
}
