package org.example.structure;

import java.util.ArrayList;

public class Vertex <K> {
    private ArrayList<K> adjacency;

    public Vertex() {
        adjacency = new ArrayList<>();
    }

    public ArrayList<K> getAdjacency() {
        return adjacency;
    }

    public void setAdjacency(ArrayList<K> adjacency) {
        this.adjacency = adjacency;
    }
}
