import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class utils {

    private static double [][] weightOfAllPathsG;
    private static double [][] weightOfAllPathsGtag;
    private static double [][] weightOfAllPathsMST;
    private  static  double heaviestPathWeightG;
    private  static  double heaviestPathWeightGtag;
    private  static  double heaviestPathWeightMst;


    private  static DijkstraShortestPath<String,Edge> dijG;
    private  static DijkstraShortestPath<String,Edge> dijGtag;
    private  static DijkstraShortestPath<String,Edge> dijMst;

    public static void initVars(Graph<String,Edge> g,Graph<String,Edge> gtag,SpanningTreeAlgorithm.SpanningTree<Edge> mst)
    {
        Graph<String,Edge> gMst = getGraphWithNoEdges(new ArrayList<>(g.vertexSet()));
        addEdgeSetToGtaph(gMst,mst.getEdges());
        dijMst = new DijkstraShortestPath<>(gMst);
         dijG = new DijkstraShortestPath<>(g);
        dijGtag = new DijkstraShortestPath<>(gtag);


        ArrayList<String> vertics = new ArrayList<>(g.vertexSet());
        int amountOfVertics = vertics.size();
        weightOfAllPathsG = new double[vertics.size()][vertics.size()];
        weightOfAllPathsGtag =new double[vertics.size()][vertics.size()];
        weightOfAllPathsMST =new double[vertics.size()][vertics.size()];
        double maxWeightGtag=0;
        double maxWeightG=0;
        double maxWeightGmst =0;

        for (int i = 0; i <amountOfVertics; i++) {
            for (int j = 0; j < amountOfVertics; j++) {

                weightOfAllPathsG[i][j] = dijG.getPathWeight(vertics.get(i),vertics.get(j));
                weightOfAllPathsGtag[i][j] = dijGtag.getPathWeight(vertics.get(i),vertics.get(j));
                weightOfAllPathsMST[i][j] = dijMst.getPathWeight(vertics.get(i),vertics.get(j));

                if(weightOfAllPathsG[i][j]>maxWeightG) maxWeightG= weightOfAllPathsG[i][j];
                if(weightOfAllPathsGtag[i][j]>maxWeightGtag) maxWeightGtag= weightOfAllPathsG[i][j];
                if(weightOfAllPathsMST[i][j]>maxWeightGmst) maxWeightGmst= weightOfAllPathsMST[i][j];
            }
        }
        heaviestPathWeightG = maxWeightG;
        heaviestPathWeightGtag = maxWeightGtag;
        heaviestPathWeightMst = maxWeightGmst;
    }

    private static void addEdgeSetToGtaph(Graph<String, Edge> gMst, Set<Edge> edges) {
        for (Edge edge : edges) {
            String source = edge.getSource();
            String target = edge.getTarget();
            gMst.addEdge(source,target);
            gMst.getEdge(source,target).setWeight(edge.getWeight());
        }
    }

    public static double calcRatioTspannerToTmst() {
        double t=  heaviestPathWeightGtag/ heaviestPathWeightG ;
        double tmst = heaviestPathWeightMst/heaviestPathWeightG;
        return  t/tmst;
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

    public static double getSize(SpanningTreeAlgorithm.SpanningTree<Edge> mst) {
        return mst.getEdges().size() ;
    }

    public static double getSize(Graph g) {
        return g.edgeSet().size();
    }


    //TODO reconstruct  graph genreator.
    public static double avp(){

        int amountOfVertics = weightOfAllPathsG.length;
        double accWeightGtag =0;
        double accWeightG=0;
        int amounthOfPaths = 0;
        for (int i = 0; i <amountOfVertics; i++) {
            for (int j = 0; j < amountOfVertics; j++) {
                /*continue and asume u are working with connected graps*/
                accWeightG+=weightOfAllPathsG[i][j];
                accWeightGtag+= weightOfAllPathsGtag[i][j];
               if(i!=j) {amounthOfPaths++;}

            }
        }
        amounthOfPaths= amounthOfPaths/2;
        accWeightG=accWeightG/2;
        accWeightGtag=accWeightGtag/2;
        double avgOfGTag = accWeightGtag/amounthOfPaths;
        double avgOfG = accWeightG/amounthOfPaths;
        return  avgOfG/avgOfGTag;

        //to divide each with the amounth of root;



    }
    public static Graph<String, Edge> getGraphWithNoEdges(List<String> vertixs) {
        Graph<String, Edge> gAns = new SimpleGraph<>((e1, e2) -> (new Edge(e1, e2)));
        for (String v : vertixs) {
            gAns.addVertex(v);
        }
        return gAns;
    }
}
