package org.example.structure.interfaces;

public interface IPriorityQueue<K extends Comparable,V> {

    K heapExtractMin ();
    K getMin ();
    String increaseKey(int position, K key);
    void insert (K key,V value);

}
