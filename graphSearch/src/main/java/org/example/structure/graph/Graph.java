package org.example.structure.graph;

import org.example.structure.interfaces.ColorType;
import org.example.structure.interfaces.Igraph;
import org.example.structure.narytree.NaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph <V> implements Igraph<V> {

    private boolean isDirected;
    private ArrayList<Vertex<V>> vertexes;
    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
    }
    public Graph() {
    }

    @Override
    public boolean insertVertex(V valueVertex) {
        getVertexes().add(new Vertex<>(valueVertex));
        return true;
    }

    @Override
    public boolean insertEdge(V from, V to) {
        if (getVertexes().isEmpty()) return false;

        Vertex fromVertex = searchVertex(from); // Luego verficar si los valores no son nullos.
        Vertex toVertex = searchVertex(to);

        fromVertex.getAdjacency().add(toVertex);
        if (!isDirected) toVertex.getAdjacency().add(fromVertex);
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
        naryTree.insertNode((V) fromVertex.getValue(), (V) fromVertex.getFather().getValue());

        while (!queue.isEmpty()){

            Vertex<V> temporalFather = queue.poll();

            for (Vertex vertex : temporalFather.getAdjacency()
                 ) {
                if (vertex.getColor().equals(ColorType.WHITE)){
                    vertex.setColor(ColorType.GRAY);
                    vertex.setDistance( temporalFather.getDistance() +1);
                    vertex.setFather(temporalFather);
                    naryTree.insertNode((V) vertex,(V) vertex.getFather() );
                    queue.add(vertex);
                }
            }
            temporalFather.setColor(ColorType.BLACK);
        }

        return naryTree;
    }

    @Override
    public ArrayList<NaryTree<V>> dfs(V from) {
        return null;
    }

    @Override
    public void dfsVisit(Vertex<V> from, NaryTree<V> tree) {

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

}
