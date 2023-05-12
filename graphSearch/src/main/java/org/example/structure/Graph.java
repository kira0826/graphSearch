package org.example.structure;

import java.util.ArrayList;

public class Graph <K>  {

    private boolean isDirected;
    private ArrayList<K> vertexes;



    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public Graph() {
    }

    public boolean isDirected() {
        return isDirected;
    }

    public void setDirected(boolean directed) {
        isDirected = directed;
    }

    public ArrayList<K> getVertexes() {
        return vertexes;
    }

    public void setVertexes(ArrayList<K> vertexes) {
        this.vertexes = vertexes;
    }
}
