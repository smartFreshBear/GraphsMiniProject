import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm;

import java.util.ArrayList;

public class utils {

    public static double calcT(SpanningTreeAlgorithm.SpanningTree<String> mst) {
        return 0;
    }

    public static double getWeight(SpanningTreeAlgorithm.SpanningTree<String> mst) {
        return 0;
    }

    public static double getWeight(Graph g) {
        double weight = 0;
        ArrayList<Edge> arr = new ArrayList<Edge>(g.edgeSet());
        for (Edge e: arr)
        {
            weight += e.getWeight();
        }
        return weight;
    }

    public static double getSize(SpanningTreeAlgorithm.SpanningTree<String> mst) {
        return 0;
    }

    public static double getSize(Graph g) {
        return 0;
    }

    public static double avp(Graph g){
        return 0;
    }

}
