package ua.nure.suprun;

import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

public class Main {

    private static final int CENTER_COST = 3;
    private static final int NODE_COST = 1;
    private static final int UNIT_OF_LENGTH_COST = 1;
    private static final OptimizeService OPTIMIZE_SERVICE = new OptimizeService();

    public static void main(String[] args) throws Exception{
        List<Node> nodes = Node.fromTextFile(Paths.get(Main.class.getResource("/nodes.in").toURI()));
        Node center = nodes.stream().findFirst().orElseGet(() -> new Node(0, 0));
        nodes.remove(center);

        Vms vms = new Vms(CENTER_COST, nodes, NODE_COST, center, UNIT_OF_LENGTH_COST);
        VmsOptimizationResult result = OPTIMIZE_SERVICE.optimizeSystem(vms);

        System.out.println(result);
        System.out.println(100 * (result.getSystemCost() - 1172.84) / result.getSystemCost());


        Pattern p = Pattern.compile("\\w{4}", Pattern.UNICODE_CHARACTER_CLASS);
        System.out.println(p.matcher("шапка").find());
    }
}
