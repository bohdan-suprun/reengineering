package ua.nure.suprun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Bohdan_Suprun
 */
public final class Cluster {

    private final Node center;
    private final List<Node> subNodes;

    public Cluster(Node center, List<Node> subNodes) {
        this.center = center;
        this.subNodes = new ArrayList<>(subNodes);
    }

    public Cluster(Node center) {
        this.center = center;
        this.subNodes = new ArrayList<>();
    }

    public Cluster(Cluster other) {
        this(other.getCenter(), other.getSubNodes());
    }

    public Node getCenter() {
        return center;
    }

    public List<Node> getSubNodes() {
        return Collections.unmodifiableList(subNodes);
    }

    public int getSubNodesCount() {
        // plus center
        return subNodes.size() + 1;
    }

    public void addSubNode(Node node) {
        Objects.requireNonNull(node);
        subNodes.add(node);
    }
}
