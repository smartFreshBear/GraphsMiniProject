import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphGenrator {

    private  static Random randNum = new Random();
    public  static Graph<String,Edge> genreateGraph(int amounthOfVertics, int maxWeight, float dens){
    Graph<String,Edge> graph  = new SimpleGraph<>((e1, e2) -> (new Edge(e1, e2)));

    for (int i = 0; i < amounthOfVertics; i++) {
        graph.addVertex(""+i);
    }

    List<String> verticxLst = new ArrayList<String>(graph.vertexSet());


    for (String v1 : verticxLst) {
        for (String v2 : verticxLst) {
            if(getBooleanPossabiltyOf(dens))
            {
                if(!v1.equals(v2)) {
                    graph.addEdge(v1, v2);
                    graph.getEdge(v1, v2).setWeight(randNum.nextInt(maxWeight));
                }
            }
//test
        }
    }
return graph;
}
//
public static boolean getBooleanPossabiltyOf(float dens){
        float rand= randNum.nextFloat();
        return(rand<=dens);

}
}

