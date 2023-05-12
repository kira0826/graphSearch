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
        return false;
    }

    @Override
    public boolean insertEdge(V from, V to) {
        return false;
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
        for (Vertex<V> v: vertexes){

        }






        return null;
    }

    @Override
    public NaryTree<V> dfsVisit(Vertex<V> from) {
        return null;
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
