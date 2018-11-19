package node.graph;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import node.Registrar;
import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.script.TaskNode;

import java.util.*;

public abstract class GraphNode extends TaskNode implements Registrar<MethodContext> {

    private final Random random = new Random();
    private final List<GraphNode> childNodes = new ArrayList<>();
    private final Comparator<TaskNode> prioritySortOrder = Comparator.comparingInt(TaskNode::priority);

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

    public boolean addNode(@NotNull GraphNode node) {
        if (!register(node)) return false;
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

    protected abstract int getMaxExecutionTime();

    protected abstract int getMinExecutionTime();

    protected abstract int getTaskOrder();

    protected abstract int getSituationalImportance();

}
