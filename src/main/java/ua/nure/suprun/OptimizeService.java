package ua.nure.suprun;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Collectors;

/**
 * @author Bohdan_Suprun
 */
public class OptimizeService {

    /**
     * Calculates cost of the optimized system.
     *
     * @param theSystem - The system to optimize.
     * @return VmsOptimizationResult instance.
     */
    public VmsOptimizationResult optimizeSystem(Vms theSystem) {
        Cluster optimalSubSet = getOptimalSubSetSize(theSystem);
        int optimalSubSetSize = optimalSubSet.getSubNodesCount();
        int optimalNodeCount = BigDecimal
                .valueOf(theSystem.getSystemSize() / optimalSubSetSize)
                .setScale(0, RoundingMode.UP).intValue();

        double systemCost = theSystem.getCenterCost() + optimalNodeCount * theSystem.getNodeCost()
                // Exclude center
                + getClusterCost(optimalSubSet, theSystem) * (theSystem.getSystemSize() - 1);

        return new VmsOptimizationResult(optimalNodeCount, optimalSubSetSize, systemCost);
    }

    private Cluster getOptimalSubSetSize(Vms vms) {
        Cluster minCluster = null;

        for (Node centralNode : vms) {
            Cluster currentCluster = new Cluster(centralNode);
            for (Node subNode : vms.getNodes().stream().filter(node -> !node.equals(centralNode))
                    .collect(Collectors.toList())) {

                currentCluster.addSubNode(subNode);
                if (minCluster == null || getClusterCost(minCluster, vms) > getClusterCost(currentCluster, vms)) {
                    minCluster = new Cluster(currentCluster);
                }
            }
        }

        return minCluster;
    }

    /**
     * Euclid distance.
     *
     * @param from - start node.
     * @param to   - end node.
     * @return distance between nodes.
     */
    private double getDistance(Node from, Node to) {
        double poweredXCoordinate = Math.pow(from.getXCoordinate() - to.getXCoordinate(), 2);
        double poweredYCoordinate = Math.pow(from.getYCoordinate() - to.getYCoordinate(), 2);

        return Math.sqrt(poweredXCoordinate + poweredYCoordinate);
    }

    private double getClusterCost(Cluster cluster, Vms vms) {
        double subNodesTotalCost = 0;

        for (Node clusterSubNode : cluster.getSubNodes()) {
            subNodesTotalCost += getDistance(clusterSubNode, cluster.getCenter()) * vms.getUnitOfLengthCost();
        }

        return (vms.getNodeCost()
                + getDistance(cluster.getCenter(), vms.getCenterNode()) * vms.getUnitOfLengthCost()
                // exclude central one, 'case we already taken it into account
                + subNodesTotalCost) / (cluster.getSubNodesCount() - 1);
    }
}
