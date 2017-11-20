package ua.nure.suprun;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Bohdan_Suprun
 */
public final class Vms implements Iterable<Node> {

    private final double centerCost;
    private final double nodeCost;
    private final double unitOfLengthCost;
    private final List<Node> nodes;
    private final Node centerNode;

    public Vms(double centerCost, Collection<Node> nodes, double nodeCost, Node centerNode, double unitOfLengthCost) {
        this.nodes = new ArrayList<>(nodes);
        this.centerCost = centerCost;
        this.nodeCost = nodeCost;
        this.unitOfLengthCost = unitOfLengthCost;
        this.centerNode = centerNode;
    }

    @Override
    public Iterator<Node> iterator() {
        return nodes.iterator();
    }

    public List<Node> getNodes() {
        return Collections.unmodifiableList(nodes);
    }

    public Node getCenterNode() {
        return centerNode;
    }

    public double getCenterCost() {
        return centerCost;
    }

    public double getNodeCost() {
        return nodeCost;
    }

    public double getUnitOfLengthCost() {
        return unitOfLengthCost;
    }

    public int getSystemSize() {
        return nodes.size() + 1;
    }
}
