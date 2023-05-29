package org.example;

import javax.xml.transform.Result;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args)  throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> edges = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                edges.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int start = Integer.parseInt(bufferedReader.readLine().trim());

        int result = prims(n, edges, start);

        bufferedReader.close();
        System.out.println(result);
    }

    public static int prims(int n, List<List<Integer>> edges, int start) {
            Set<Integer> visitedNodes = new HashSet<>();
            int minWeight = 0;
            // Ordeno por los pesos, posicion 2.
            PriorityQueue<List<Integer>> minPriorityQueue = new PriorityQueue<>((a,b) -> a.get(2) - b.get(2) );
        for (int i = 0; i < n; i++) {
            minPriorityQueue.add(edges.get(i));
        }
            while(!minPriorityQueue.isEmpty()){

                int node = minPriorityQueue.poll().get(0);
                int temporal = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    temporal =0;
                    if (visitedNodes.contains(edges.get(i).get(0)) || visitedNodes.contains(edges.get(i).get(1))) continue;
                    if (edges.get(i).get(0) == node){
                            temporal =edges.get(i).get(2) < temporal?edges.get(i).get(2): temporal;
                    }
                }
                visitedNodes.add(node);
                minWeight += temporal;
            }
            return minWeight;
        }
}