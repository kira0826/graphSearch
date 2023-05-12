package org.example.structure.interfaces;

import org.example.structure.graph.Vertex;
import org.example.structure.narytree.NaryTree;

import java.util.ArrayList;

public interface Igraph <V extends Comparable<V>>{

    boolean insertVertex(V valueVertex);
    boolean insertEdge(V from, V to);

    NaryTree<V> bfs(V from);
    ArrayList<NaryTree<V>> dfs(V from);
    NaryTree<V> dfsVisit(Vertex<V> from);
    Vertex<V> searchVertex(V values);

}
