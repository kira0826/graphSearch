package org.example.structure.graph;

import org.example.structure.interfaces.ColorType;

import java.util.ArrayList;

public class Vertex <V> {

    private Vertex<V> father;
    private ArrayList<V> adjacency;
    private V value;
    private Integer distance;
    private ColorType color;

    public Vertex() {
        adjacency = new ArrayList<>();
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public ColorType getColor() {
        return color;
    }

    public void setColor(ColorType color) {
        this.color = color;
    }

    public Vertex<V> getFather() {
        return father;
    }

    public void setFather(Vertex<V> father) {
        this.father = father;
    }

    public ArrayList<V> getAdjacency() {
        return adjacency;
    }

    public void setAdjacency(ArrayList<V> adjacency) {
        this.adjacency = adjacency;
    }
}
