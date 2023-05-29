package org.example.structure.graph;

import org.example.structure.interfaces.ColorType;
import org.example.structure.interfaces.IPriorityQueue;
import org.example.structure.interfaces.Igraph;
import org.example.structure.narytree.NaryTree;

import java.util.*;

public class Graph <V extends Comparable<V> > implements Igraph<V> {

    private boolean isDirected;
    private ArrayList<Vertex<V>> vertexes;

    private Hashtable<V, Hashtable<V,Integer>> weightedMatrix ;

    private boolean isWeighted;
    public Graph(boolean isDirected, boolean isWeighted) {
        this.vertexes = new ArrayList<>();
        this.isDirected = isDirected;
        if (isWeighted){
            this.isWeighted = true;
            this.weightedMatrix = new Hashtable<>();
        }
    }
    @Override
    public boolean insertVertex(V valueVertex) {
        getVertexes().add(new Vertex<>(valueVertex));
        if (isWeighted){
            getWeightedMatrix().put(valueVertex, new Hashtable<>()); // Add new vertex as a new row of weighted matrix.
            for (Vertex vertex: getVertexes()
            ) {
                getWeightedMatrix().get(valueVertex).put((V)vertex.getValue(),Integer.MAX_VALUE);
            }
            for (Hashtable<V,Integer> hashTable: getWeightedMatrix().values()
                 ) {
                hashTable.put(valueVertex, Integer.MAX_VALUE);
            }

            getWeightedMatrix().get(valueVertex).put(valueVertex,0);
        }
        return true;
    }

    public Map<?,?>[] dijkstra(V source){
        Vertex<V> vertexSource = searchVertex(source);

        Hashtable<V, Integer> distance=  new Hashtable<>();
        HashMap<V, V> prev =  new HashMap<>();
        IPriorityQueue priorityQueue = new Heap();

        for (Vertex<V> vertex: getVertexes()
             ) {
            distance.put(vertex.getValue(), Integer.MAX_VALUE);
            prev.put(vertex.getValue(), null);

            if (vertexSource.getValue().equals(vertex.getValue())) distance.put(vertexSource.getValue(), 0);

            priorityQueue.insert(distance.get(vertex.getValue()), vertex.getValue());
        }

            while(!priorityQueue.isEmpty()){
                Vertex<V> vertexToCheck =   searchVertex((V) priorityQueue.heapExtractMin());
                for (Vertex<V> adjacency: vertexToCheck.getAdjacency()
                     ) {
                    int temporal = distance.get(vertexToCheck.getValue()) +
                            getWeightedMatrix().get(vertexToCheck.getValue()).get(adjacency.getValue());

                    if (temporal <  distance.get(adjacency.getValue())){

                        distance.put(adjacency.getValue(), temporal);
                        prev.put(adjacency.getValue(),vertexToCheck.getValue());
                        priorityQueue.decreasePriority(adjacency.getValue(), temporal);
                }
            }
        }

            Map [] maps = {distance,prev};

            return maps;
    }
    public Integer searchWeightOfVertex(V from,  V to){
        Integer weight = -1;
        if (getWeightedMatrix().containsKey(from) && getWeightedMatrix().containsKey(to)) weight = getWeightedMatrix().get(from).get(to);

        return weight;
    }

    @Override
    public boolean insertEdge(V from, V to) {
        if (getVertexes().isEmpty()) return false;

        Vertex fromVertex = searchVertex(from); // Luego verficar si los valores no son nullos.
        Vertex toVertex = searchVertex(to);

        if (fromVertex == null || toVertex  == null) return false;
        fromVertex.getAdjacency().add(toVertex);
        if (!isDirected) toVertex.getAdjacency().add(fromVertex);

        return true;
    }

    public boolean insertWeightedEdge(V from, V to, Integer weight) {
        if (getVertexes().isEmpty()) return false;

        Vertex fromVertex = searchVertex(from); // Luego verficar si los valores no son nullos.
        Vertex toVertex = searchVertex(to);

        if (fromVertex == null || toVertex  == null) return false;

        fromVertex.getAdjacency().add(toVertex);
        getWeightedMatrix().get(from).put(to,weight);
        if (!isDirected) {
            toVertex.getAdjacency().add(fromVertex);
            getWeightedMatrix().get(to).put(from,weight);
        }

        return true;
    }

    @Override
    public boolean deleteVertex(V valueVertex) {
        for (int i = 0; i < getVertexes().size(); i++) {
            if (getVertexes().get(i).getValue().equals(valueVertex)){
                getVertexes().remove(i);
            }
        }

        for (Vertex<V> vertex : getVertexes()
        ) {
            for (int i = 0; i < vertex.getAdjacency().size(); i++) {
                if (vertex.getAdjacency().get(i).getValue().equals(valueVertex)){
                    vertex.getAdjacency().remove(i);
                }
            }
        }
        if (isWeighted){
            getWeightedMatrix().remove(valueVertex);
            for (Hashtable<V,Integer> hash: getWeightedMatrix().values()
            ) {
                hash.remove(valueVertex);
            }
        }
        return true;
    }

    @Override
    public boolean deleteEdge(V from, V to) {
        if (getVertexes().isEmpty()) return false;

        Vertex<V> fromVertex = searchVertex(from); // Luego verficar si los valores no son nullos.
        Vertex<V> toVertex = searchVertex(to);

        if (fromVertex == null || toVertex  == null) return false;

        fromVertex.getAdjacency().remove(toVertex);

        if (!isDirected) toVertex.getAdjacency().remove(fromVertex);

        if (isWeighted){
            getWeightedMatrix().get(from).put(to, Integer.MAX_VALUE);
            if (!isDirected) getWeightedMatrix().get(to).put(from,Integer.MAX_VALUE);
        }
        return true;
    }

    @Override
    public NaryTree<V> bfs(V from) {

        Vertex fromVertex  = searchVertex(from);
        if (getVertexes().isEmpty()) return null;
        
        // Asignar valores por defecto
        for (Vertex vertex : getVertexes()
             ) {
            vertex.setColor(ColorType.WHITE);
            vertex.setFather(null);
            vertex.setDistance(Integer.MAX_VALUE);
        }
        fromVertex.setColor(ColorType.GRAY);
        fromVertex.setDistance(0);

        Queue<Vertex<V>> queue = new LinkedList();
        queue.add(fromVertex);

        NaryTree<V> naryTree = new NaryTree<>();
        naryTree.insertNode((V) fromVertex.getValue(), null);

        while (!queue.isEmpty()){

            Vertex<V> temporalFather = queue.poll();

            for (Vertex vertex : temporalFather.getAdjacency()
                 ) {
                if (vertex.getColor().equals(ColorType.WHITE)){

                    vertex.setColor(ColorType.GRAY);
                    vertex.setDistance( temporalFather.getDistance() +1);
                    vertex.setFather(temporalFather);
                    naryTree.insertNode((V) vertex.getValue(),(V) vertex.getFather().getValue() );
                    queue.add(vertex);
                }
            }
            temporalFather.setColor(ColorType.BLACK);
        }

        return naryTree;
    }

    @Override
    public ArrayList<NaryTree<V>> dfs() {
        for (Vertex<V> v: vertexes){
            v.setColor(ColorType.WHITE);
            v.setFather(null);
        }

        ArrayList<NaryTree<V>> forest = new ArrayList<>();

        for (Vertex<V> v: vertexes){
            if (v.getColor().equals(ColorType.WHITE)){
                NaryTree<V> tree = new NaryTree<>();
                dfsVisit(v, tree);
                forest.add(tree);
            }
        }

        return forest;
    }

    @Override
    public void dfsVisit(Vertex<V> from, NaryTree<V> tree) {
        from.setColor(ColorType.GRAY);
        V temp = null;
        if(from.getFather() != null) { temp = from.getFather().getValue(); }
        tree.insertNode(from.getValue(), temp);
        for (Vertex<V> v: from.getAdjacency()) {
            if (v.getColor().equals(ColorType.WHITE)){
                v.setFather(from);
                dfsVisit(v, tree);
            }
        }
        from.setColor(ColorType.BLACK);
    }


    @Override
    public Vertex<V> searchVertex(V values) {
        if (getVertexes().isEmpty()) return null;
        else {
            for (Vertex<V> vertex: getVertexes()
                 ) {
                if (vertex.getValue().equals(values)) return vertex;
            }
        }
        return null;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public void setDirected(boolean directed) {
        isDirected = directed;
    }

    public ArrayList<Vertex<V>> getVertexes() {
        return vertexes;
    }

    public void setVertexes(ArrayList<Vertex<V>> vertexes) {
        this.vertexes = vertexes;
    }

    public boolean isWeighted() {
        return isWeighted;
    }

    public Hashtable<V, Hashtable<V, Integer>> getWeightedMatrix() {
        return weightedMatrix;
    }

    public void setWeightedMatrix(Hashtable<V, Hashtable<V, Integer>> weightedMatrix) {
        this.weightedMatrix = weightedMatrix;
    }

    public void setWeighted(boolean weighted) {
        isWeighted = weighted;
    }
}
