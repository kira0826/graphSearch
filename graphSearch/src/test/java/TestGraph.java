import org.example.structure.graph.Graph;
import org.example.structure.narytree.NaryTree;
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

    public void setUpStage7Directed(){
        graph = new Graph<>(true);
        graph.insertVertex("V");
        graph.insertVertex("R");
        graph.insertVertex("S");
        graph.insertVertex("H");
        graph.insertVertex("L");
        //Insertions:
        graph.insertEdge("V","R");
        graph.insertEdge("S","R");
        graph.insertEdge("H","L");

    }

    @Test
    public void insertionDirectToRoot(){
        setUpStage6();
        assertEquals(0,graph.getVertexes().size());
    }

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

    @Test
    public void dfsForestConstructionPreOrder1(){
        setUpStage7Directed();
        String test1 = "";
        for (Node n:graph.dfs().get(0).preOrder()) {
            test1 += n.getElement() + " ";
        }

        assertEquals(3, graph.dfs().size());
        assertEquals(2, graph.dfs().get(0).weight());
        assertEquals("V R " , test1);
    }
    @Test
    public void dfsForestConstructionPreOrder2(){
        setUpStage7Directed();
        String test2 = "";
        for (Node n:graph.dfs().get(1).preOrder()) {
            test2 += n.getElement() + " ";
        }
        assertEquals(3, graph.dfs().size());
        assertEquals(1, graph.dfs().get(1).weight());
        assertEquals("S " , test2);
    }
    @Test
    public void dfsForestConstructionPreOrder3(){
        setUpStage7Directed();
        String test3 = "";
        for (Node n:graph.dfs().get(2).preOrder()) {
            test3 += n.getElement() + " ";
        }
        assertEquals(3, graph.dfs().size());
        assertEquals(2, graph.dfs().get(2).weight());
        assertEquals("H L " , test3);
    }

    @Test
    public void bfsForestConstructionPreOrder1(){
        setUpStage7Directed();
        String test1 = "";
        for (Node n: graph.bfs("V").preOrder()) {
            test1 += n.getElement() + " ";
        }
        assertEquals(2, graph.bfs("V").weight());
        assertEquals("V R " , test1);
    }
    @Test
    public void bfsForestConstructionPreOrder2(){
        setUpStage7Directed();
        String test2 = "";
        for (Node n:graph.bfs("R").preOrder()) {
            test2 += n.getElement() + " ";
        }
        assertEquals(1, graph.bfs("R").weight());
        assertEquals("R " , test2);
    }

    @Test
    public void bfsForestConstructionPreOrder3(){
        setUpStage7Directed();
        String test2 = "";
        for (Node n:graph.bfs("H").preOrder()) {
            test2 += n.getElement() + " ";
        }
        assertEquals(2, graph.bfs("H").weight());
        assertEquals("H L " , test2);
    }
}
