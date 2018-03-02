import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.AsWeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class theAlgorithm {


    public  static  void main (String args[])
    {
        //building regular graph,
        Graph<String,Edge> g  = GraphGenrator.genreateGraph(10,10);
        //creating weighted graph from the regualr graph ( the sconed arguent is a hash map from E -> Double
        double r = 3;


        runAlgorithm(g,r,new ArrayList<> (g.vertexSet()));
    }


    public static Graph<String,Edge>  runAlgorithm (Graph<String, Edge> g, double r,List<String> vertixes)
    {

        List<Edge> sortedEdges = getSortedListOfEdges(g.edgeSet());
        Graph<String,Edge> gtag = getGraphWithNoEdges(vertixes);



        for (Edge sortedEdge : sortedEdges) {
            double shortetstPathWeightstPathWeight = getShortestPath (sortedEdge.getSource(),sortedEdge.getTarget(),gtag);
            if (r*sortedEdge.getWeight() < shortetstPathWeightstPathWeight )
            {
                gtag.addEdge(sortedEdge.getSource(),sortedEdge.getTarget());

            }

        }
        return gtag;
    }

    private static double getShortestPath(String source, String target,Graph<String,Edge> graph) {

        DijkstraShortestPath dij =  new DijkstraShortestPath(graph);
        return dij.getPathWeight(source,target);
    }

    private static Graph<String,Edge> getGraphWithNoEdges(List<String> vertixs) {
        Graph<String ,Edge> gAns = new SimpleGraph<>((e1,e2)-> (new Edge(e1,e2)));
        for (String v : vertixs) {
            gAns.addVertex(v);
        }
    return  gAns;
    }

    private static List<Edge> getSortedListOfEdges(Set<Edge> edges) {
        List<Edge> ans = new ArrayList<>();
        ans.addAll(edges);
        ans.sort((o1, o2)->(((Edge)o1).getWeightInt() -((Edge)o2).getWeightInt()));
        return  ans;
    }


}
