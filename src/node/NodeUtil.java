package node;

import node.graph.GraphNode;

public class NodeUtil {
    public static int getNodePriority(GraphNode startingNode, GraphNode targetNode, int startingDistance) {
        if (startingNode == null || targetNode == null) {
            return 0;
        } else if (startingNode == targetNode) {
            return startingNode.priority() * startingDistance;
        } else {
            return startingNode.getChildNodes().stream().mapToInt(node -> getNodePriority(node, targetNode, startingDistance + 1)).sum();
        }
    }
}
