import org.example.structure.graph.Graph;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestGraph {

    private static Graph<String> graph;


    public void  setUpStage1(){
        graph = new Graph<>(false);
        graph.insertVertex("S");
    }

    public void setUpStage2NoDirected(){
        graph = new Graph<>(false);
        graph.insertVertex("A");
        graph.insertVertex("B");
    }

    @Test
    public void insertionDirectToRoot(){
        setUpStage1();
        assertEquals(1,graph.getVertexes().size());
        assertEquals("S", graph.getVertexes().get(0).getValue());
    }

    @Test
    public void  insertEdge(){
        setUpStage2NoDirected();
        graph.insertEdge("A","B");
        assertEquals(2,graph.getVertexes().size());
        assertEquals("B",graph.getVertexes().get(0).getAdjacency().get(0).getValue());
        assertEquals("A",graph.getVertexes().get(1).getAdjacency().get(0).getValue());
    }

}
