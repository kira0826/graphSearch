package org.example.structure.graph;

import org.example.structure.interfaces.ColorType;
import org.example.structure.interfaces.Igraph;
import org.example.structure.narytree.NaryTree;

import java.util.ArrayList;

public class Graph <V extends Comparable<V>> implements Igraph<V> {

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

        Vertex fromVertex = searchVertex(from);
        Vertex toVertex = searchVertex(to);

        fromVertex.getAdjacency().add(toVertex);
        if (!isDirected) toVertex.getAdjacency().add(fromVertex);
        return true;
    }

    @Override
    public NaryTree<V> bfs(V from) {
        return null;
    }

    @Override
    public ArrayList<NaryTree<V>> dfs(V from) {
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
        tree.insertNode(from.getValue(), from.getFather().getValue());
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
                if (vertex.getValue().compareTo(values) == 0) return vertex;
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
