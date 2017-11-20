package ua.nure.suprun;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bohdan_Suprun
 */
public final class Node {

    private final double xCoordinate;
    private final double yCoordinate;

    public Node(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public double getXCoordinate() {
        return xCoordinate;
    }

    public double getYCoordinate() {
        return yCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Node node = (Node) o;

        return Double.compare(node.xCoordinate, xCoordinate) == 0 && Double.compare(node.yCoordinate, yCoordinate) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(xCoordinate);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yCoordinate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public static List<Node> fromTextFile(Path inputFile) throws IOException {
        return Files.lines(inputFile)
                .map(str -> str.split("\\s"))
                .map(arr -> new Node(Double.parseDouble(arr[0]), Double.parseDouble(arr[1])))
                .collect(Collectors.toList());
    }
}
