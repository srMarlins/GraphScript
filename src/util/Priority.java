package util;

public enum Priority {
    LOW(1),
    MED(3),
    HIGH(5);

    private int value;

    Priority(int priority) {
        this.value = priority;
    }

    public int value() {
        return this.value;
    }
}