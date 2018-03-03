import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class theAlgorithm {


    public static void main(String args[]) {
        //building regular graph
        Graph<String, Edge> g = GraphGenrator.genreateGraph(10, 10, (float) 0.3);
        //creating weighted graph from the regualr graph ( the sconed arguent is a hash map from E -> Double
        double t = 0.1;
        short length = 10;
        short height = 50;
        //creating 50 graphs
        float dens=0;
        TableExpirementCreator expirenment1a = new TableExpirementCreator("expirement1a", length, height);
        TableExpirementCreator expirenment1b = new TableExpirementCreator("expirement1b", length, height);
        TableExpirementCreator expirenment2a = new TableExpirementCreator("expirement2a", length, height);
        TableExpirementCreator expirenment2b = new TableExpirementCreator("expirement2b", length, height);
        for (int i = 0; i < 50; i++) {
            Graph G = GraphGenrator.genreateGraph(100, 100, dens+= 1/50);
            //for each graph run the spanner algorithm for 10 different t's
            int j=0;
            while (t<2 && j<10) {
                KruskalMinimumSpanningTree MstOfG = new KruskalMinimumSpanningTree(G);
                SpanningTreeAlgorithm.SpanningTree<Edge> mst = MstOfG.getSpanningTree();
                Graph tSpannanOfG = runAlgorithm(G, t, new ArrayList<>(g.vertexSet()));

                utils.initVars(G,tSpannanOfG,mst);
                expirenment1a.writeToFile(i, j, String.valueOf(utils.calcRatioTspannerToTmst()));
                expirenment1b.writeToFile(i, j, String.valueOf(utils.getWeight(tSpannanOfG) / mst.getWeight())+";" + String.valueOf(utils.getSize(tSpannanOfG) / utils.getSize(mst)));
                expirenment2a.writeToFile(i, j, String.valueOf(utils.avp()) );
                expirenment2b.writeToFile(i, j, String.valueOf(utils.getWeight(G) / utils.getWeight(tSpannanOfG)) );
                j++;
                t=t+0.2;
            }
        }
    }


    public static Graph<String, Edge> runAlgorithm(Graph<String, Edge> g, double r, List<String> vertixes) {

        List<Edge> sortedEdges = getSortedListOfEdges(g.edgeSet());
        Graph<String, Edge> gtag = utils.getGraphWithNoEdges(vertixes);


        for (Edge sortedEdge : sortedEdges) {
            double shortetstPathWeightstPathWeight = getShortestPath(sortedEdge.getSource(), sortedEdge.getTarget(), gtag);
            if (r * sortedEdge.getWeight() < shortetstPathWeightstPathWeight) {
                gtag.addEdge(sortedEdge.getSource(), sortedEdge.getTarget());

            }

        }
        return gtag;
    }

    private static double getShortestPath(String source, String target, Graph<String, Edge> graph) {

        DijkstraShortestPath dij = new DijkstraShortestPath(graph);
        return dij.getPathWeight(source, target);
    }



    private static List<Edge> getSortedListOfEdges(Set<Edge> edges) {
        List<Edge> ans = new ArrayList<>();
        ans.addAll(edges);
        ans.sort((o1, o2) -> (((Edge) o1).getWeightInt() - ((Edge) o2).getWeightInt()));
        return ans;
    }

}

