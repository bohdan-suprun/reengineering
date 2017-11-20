package ua.nure.suprun;

/**
 * @author Bohdan_Suprun
 */
public final class VmsOptimizationResult {

    private final int optimalNodeCount;
    private final int optimalNodeSubSetSize;
    private final double systemCost;

    public VmsOptimizationResult(int optimalNodeCount, int optimalNodeSubSetSize, double systemCost) {
        this.optimalNodeCount = optimalNodeCount;
        this.optimalNodeSubSetSize = optimalNodeSubSetSize;
        this.systemCost = systemCost;
    }

    public int getOptimalNodeCount() {
        return optimalNodeCount;
    }

    public int getOptimalNodeSubSetSize() {
        return optimalNodeSubSetSize;
    }

    public double getSystemCost() {
        return systemCost;
    }

    @Override
    public String toString() {
        return "{" +
                "optimalNodeCount=" + optimalNodeCount +
                ", optimalNodeSubSetSize=" + optimalNodeSubSetSize +
                ", systemCost=" + systemCost +
                '}';
    }
}
