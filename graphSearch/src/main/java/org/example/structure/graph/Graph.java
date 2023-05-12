package org.example.structure.graph;

import org.example.structure.interfaces.Igraph;
import org.example.structure.narytree.NaryTree;

import java.util.ArrayList;

public class Graph <V> implements Igraph<V> {

    private boolean isDirected;
    private ArrayList<V> vertexes;
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
        return null;
    }

    @Override
    public NaryTree<V> dfsVisit(Vertex<V> from) {
        return null;
    }

    @Override
    public Vertex<V> searchVertex(V values) {
        return null;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public void setDirected(boolean directed) {
        isDirected = directed;
    }

    public ArrayList<V> getVertexes() {
        return vertexes;
    }

    public void setVertexes(ArrayList<V> vertexes) {
        this.vertexes = vertexes;
    }
}
