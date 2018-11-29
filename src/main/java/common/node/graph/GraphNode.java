package common.node.graph;

import org.jetbrains.annotations.NotNull;
import common.node.Registrar;
import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.script.TaskNode;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public abstract class GraphNode extends TaskNode implements Registrar<MethodContext> {

    private final Random random = new Random();
    private final List<GraphNode> childNodes = new ArrayList<>();
    private final Comparator<TaskNode> prioritySortOrder = Comparator.comparingInt(TaskNode::priority);
    private boolean isRegistered = false;

    @Override
    public int priority() {
        return getTaskOrder() * getSituationalImportance();
    }

    @Override
    public boolean accept() {
        return false;
    }

    @Override
    public int execute() {
        return random.nextInt((getMaxExecutionTime() - getMinExecutionTime()) + 1) + getMaxExecutionTime();
    }

    @Override
    public boolean register(MethodContext candidate) {
        candidate.passInstance(getClient().getInstance());
        candidate.registerContext(getClient());
        return true;
    }

    @Nullable
    public GraphNode next() {
        Optional<GraphNode> optional = getChildNodes().isEmpty() ? Optional.empty() : getChildNodes().stream().filter(GraphNode::accept).findFirst();
        return optional == null || !optional.isPresent() ? null : optional.get();
    }

    public boolean addNode(@NotNull GraphNode node, @NotNull Registrar<GraphNode> registrar) {
        if (!registrar.register(node)) {
            node.setRegistered(false);
            return false;
        }

        node.setRegistered(true);
        childNodes.add(node);
        childNodes.sort(prioritySortOrder);
        return true;
    }

    public boolean removeNode(@NotNull GraphNode node) {
        if (!childNodes.contains(node)) {
            return false;
        }

        childNodes.remove(node);
        return true;
    }

    public List<GraphNode> getChildNodes() {
        return this.childNodes;
    }

    public boolean isRegistered() {
        return this.isRegistered;
    }

    public void setRegistered(boolean registered) {
        this.isRegistered = registered;
    }

    protected abstract int getMaxExecutionTime();

    protected abstract int getMinExecutionTime();

    protected abstract int getTaskOrder();

    protected abstract int getSituationalImportance();

}
