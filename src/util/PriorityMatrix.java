package util;

public class PriorityMatrix {

    private int priorityValue;

    public PriorityMatrix(Priority executionPriority, Priority failPriority, Priority repeatPriority) {
        this.priorityValue = executionPriority.getPriority() * failPriority.getPriority() * repeatPriority.getPriority();
    }

    public int getPriorityValue(){
        return this.priorityValue;
    }

    public enum Priority {
        LOW(1),
        MED(2),
        HIGH(3);

        private int priority;

        private Priority(int priority){
            this.priority = priority;
        }

        public int getPriority() {
            return this.priority;
        }
    }
}
