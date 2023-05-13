import org.example.structure.graph.Graph;
import org.example.structure.narytree.Node;
import org.junit.jupiter.api.Test;

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
    public void setUpStage3Directed(){
        graph = new Graph<>(true);
        graph.insertVertex("A");
        graph.insertVertex("B");
    }
    public void setUpStage4NoDirected(){
        graph = new Graph<>(false);
        graph.insertVertex("V");
        graph.insertVertex("R");
        graph.insertVertex("S");
        graph.insertVertex("W");
        graph.insertVertex("T");
        graph.insertVertex("X");
        graph.insertVertex("U");
        graph.insertVertex("Y");
        //Insertions:
        graph.insertEdge("V","R");
        graph.insertEdge("S","R");
        graph.insertEdge("W","S");
        graph.insertEdge("T","W");
        graph.insertEdge("W","X");
        graph.insertEdge("X","T");
        graph.insertEdge("U","T");
        graph.insertEdge("X","U");
        graph.insertEdge("X","Y");
        graph.insertEdge("U","Y");
    }

    public void setUpStage5(){
        graph = new Graph<>(false);
        graph.insertVertex("S");
        graph.insertVertex("R");
        graph.insertVertex("W");
        graph.insertEdge("S","W");
        graph.insertEdge("S","R");

    }

    public void  setUpStage6(){
        graph = new Graph<>(false);
        graph.insertVertex("S");
        graph.deleteVertex("S");
    }

    // INSERTIONS:



    @Test
    public void  insertEdge(){
        setUpStage2NoDirected();
        graph.insertEdge("A","B");
        assertEquals(2,graph.getVertexes().size());
        assertEquals("B",graph.getVertexes().get(0).getAdjacency().get(0).getValue());
        assertEquals("A",graph.getVertexes().get(1).getAdjacency().get(0).getValue());
    }

    @Test
    public void insertEdgeDirected(){
        setUpStage3Directed();
        graph.insertEdge("A","B");
        assertEquals(2,graph.getVertexes().size());
        assertEquals("B",graph.getVertexes().get(0).getAdjacency().get(0).getValue());
        assertEquals(0,graph.getVertexes().get(1).getAdjacency().size());
    }

    // DELETE:

    @Test
    public void deleteDirectToRoot(){
        setUpStage1();
        assertEquals(1,graph.getVertexes().size());
        assertEquals("S", graph.getVertexes().get(0).getValue());
    }

    @Test
    public void  deleteEdge(){
        setUpStage2NoDirected();
        graph.insertEdge("A","B");
        assertEquals(2,graph.getVertexes().size());
        graph.deleteEdge("A","B");
        assertEquals(0, graph.getVertexes().get(0).getAdjacency().size());
        assertEquals(0,graph.getVertexes().get(1).getAdjacency().size());
    }

    @Test
    public void deleteEdgeDirected(){
        setUpStage3Directed();
        graph.insertEdge("A","B");
        assertEquals(2,graph.getVertexes().size());
        assertEquals("B",graph.getVertexes().get(0).getAdjacency().get(0).getValue());
        assertEquals(0,graph.getVertexes().get(1).getAdjacency().size());
        graph.deleteEdge("A","B");
        assertEquals(0, graph.getVertexes().get(0).getAdjacency().size());

    }

    @Test
    public void adjacencyTest(){
        setUpStage5();
        assertEquals("W",graph.getVertexes().get(0).getAdjacency().get(0).getValue());
        assertEquals("R",graph.getVertexes().get(0).getAdjacency().get(1).getValue());
        assertEquals("S",graph.getVertexes().get(1).getAdjacency().get(0).getValue());
    }

    // BFS TEST

    //
    @Test
    public void bfsTreeConstruction(){
        setUpStage4NoDirected();
        String test = "";
        for (Node n:graph.bfs("S").preOrder()) {
            test += n.getElement() + " ";
        }
        assertEquals("S R V W T U X Y " , test);

    }

    @Test
    public void bfsTreeConstructionLevels(){
        setUpStage4NoDirected();
        String test = "";
        for (Node n:graph.bfs("S").postOrder()) {
            test += n.getElement() + " ";
        }
        assertEquals("V R U T Y X W S " , test);

    }

    // DFS  TEST
    @Test
    public void dfsTreeConstruction(){
        setUpStage5();
        String test = "";
        for (Node n:graph.dfs().get(0).postOrder()) {
            test += n.getElement() + " ";
        }
        assertEquals("W R S " , test);

    }

    @Test
    public void dfsTreeConstructionLevels(){
        setUpStage4NoDirected();
        String test = "";
        for (Node n:graph.dfs().get(0).postOrder()) {
            test += n.getElement() + " ";
        }
        assertEquals("Y U X T W S R V " , test);
    }

    @Test
    public void dfsBasicTreeConstructionPreOrder(){
        setUpStage5();
        String test = "";
        for (Node n:graph.dfs().get(0).preOrder()) {
            test += n.getElement() + " ";
        }
        assertEquals("S W R " , test);
    }

    @Test
    public void dfsBigTreeConstructionPreOrder(){
        setUpStage4NoDirected();
        String test = "";
        for (Node n:graph.dfs().get(0).preOrder()) {
            test += n.getElement() + " ";
        }
        assertEquals("V R S W T X U Y " , test);
    }
}
