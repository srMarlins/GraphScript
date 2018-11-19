package node.graph;

import com.sun.istack.internal.NotNull;
import exception.ScriptInitException;
import node.Registrar;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.TaskNode;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public abstract class GraphScript extends AbstractScript implements Registrar<TaskNode> {

    private boolean scriptRunning = false;
    private Queue<GraphNode> executionQueue = new PriorityQueue<>();

    @Override
    public void onStart() {
        super.onStart();
        buildGraph();
        if (!registerNodes(buildGraph())) {
            throw new ScriptInitException();
        }
        executionQueue.add(getRoot());
    }

    @Override
    public int onLoop() {
        if (executionQueue.isEmpty()) {
            executionQueue.add(getRoot());
            return 1000;
        }

        GraphNode node = executionQueue.poll();
        int executionTime = node.execute();

        GraphNode nextNode = node.next();
        if (nextNode != null) executionQueue.add(nextNode);

        return executionTime;
    }

    @Override
    public boolean register(TaskNode candidate) {
        candidate.passInstance(getClient().getInstance());
        candidate.registerContext(getClient());
        return true;
    }

    @NotNull
    protected abstract GraphNode getRoot();

    /**
     * Build a graph of executable nodes
     *
     * @return the node of the graph to use as the entry point
     */
    @NotNull
    protected abstract GraphNode buildGraph();

    public boolean isScriptRunning() {
        return this.scriptRunning;
    }

    protected void startScript() {
        this.scriptRunning = true;
    }

    protected void stopScript() {
        this.scriptRunning = false;
    }

    private boolean registerNodes(@NotNull final GraphNode root) {
        final Queue<GraphNode> nodeQueue = new ArrayDeque<>();
        boolean allRegistered = true;
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty() && allRegistered) {
            GraphNode node = nodeQueue.poll();
            allRegistered = register(node);
            nodeQueue.addAll(node.getChildNodes());
        }
        return allRegistered;
    }


}
