import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge{


    private String source;
    private  String target;
    private  double weight;

    public Edge(String source, String target) {
        this.source =source;
        this.target = target;
        weight =1;

    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public String getTarget() {
        return target;
    }

    public double getWeight()
    {
        return  weight;
    }
    public int getWeightInt()
    {
        Double d = new Double(getWeight());
        int i = d.intValue();
        return  i;
    }

    public void setWeight(double weight)
    {
       this.weight=weight;
    }
}
