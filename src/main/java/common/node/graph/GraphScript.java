package common.node.graph;

import org.jetbrains.annotations.NotNull;
import common.node.Registrar;
import org.dreambot.api.script.AbstractScript;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public abstract class GraphScript extends AbstractScript implements Registrar<GraphNode> {

    private boolean scriptRunning = false;
    private Queue<GraphNode> executionQueue = new PriorityQueue<>();


    @Override
    public void onStart() {
        log("start");
        super.onStart();
        register(buildGraph());
        /*if (!registerNodes(buildGraph())) {
            throw new ScriptInitException();
        }*/
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
    public boolean register(GraphNode candidate) {
        candidate.passInstance(getClient().getInstance());
        candidate.registerContext(getClient());
        return true;
    }

    @NotNull
    protected abstract GraphNode getRoot();

    /**
     * Build a graph of executable nodes
     *
     * @return the common.node of the graph to use as the entry point
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
        final Queue<GraphNode> unRegisteredQueue = new ArrayDeque<>();
        boolean allRegistered = true;
        unRegisteredQueue.add(root);
        while (!unRegisteredQueue.isEmpty() && allRegistered) {
            GraphNode node = unRegisteredQueue.poll();
            if (!node.isRegistered()) allRegistered = register(node);
            unRegisteredQueue.addAll(unRegisteredQueue.stream().filter(childNode -> !childNode.isRegistered()).collect(Collectors.toCollection(ArrayList::new)));

        }
        return allRegistered;
    }


}
