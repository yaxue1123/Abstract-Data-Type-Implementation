package A6_Dijkstra;

public class EntryPair implements EntryPair_Interface {
    public Vertex v;
    public long dist;

    public EntryPair(Vertex aV, long distance) {
        v = aV;
        dist = distance;
    }

    public Vertex getVertex() {
        return v;
    }

    public long getDistance() {
        return dist;
    }
}
