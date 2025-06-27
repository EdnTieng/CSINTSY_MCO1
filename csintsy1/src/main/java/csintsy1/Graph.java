/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csintsy1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class Graph {
    
    private List<String> letters = Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V"
        );
    private Map<String, List<Edge>> graph = new HashMap<>();

    public Graph(){
        for (String node : letters) {
            this.graph.put(node, new ArrayList<>());
        }
        // Hardcoded weights and connections
        // Can add more nodes and change connections as needed
        this.addEdge("S", "M", 9);
        this.addEdge("S", "O", 6);
        this.addEdge("S", "Q", 3);
        this.addEdge("S", "R", 6);
        this.addEdge("S", "T", 8);

        this.addEdge("M", "N", 1);
        this.addEdge("M", "K", 1);

        this.addEdge("N", "O", 1);
        this.addEdge("N", "I", 1);

        this.addEdge("O", "P", 1);
        this.addEdge("O", "E", 1);

        this.addEdge("P", "Q", 1);
        this.addEdge("Q", "R", 1);
        this.addEdge("Q", "C", 1);

        this.addEdge("R", "T", 1);

        this.addEdge("T", "B", 1);
        this.addEdge("T", "A", 1);

        this.addEdge("K", "J", 1);
        this.addEdge("J", "I", 1);
        this.addEdge("I", "G", 1);
        this.addEdge("G", "F", 1);
        this.addEdge("F", "E", 1);
        this.addEdge("E", "C", 1);

        this.addEdge("C", "B", 1);
        this.addEdge("C", "D", 1);

        this.addEdge("U", "K", 1);
        this.addEdge("U", "J", 1); 

        this.addEdge("J", "L", 1);
        this.addEdge("L", "H", 1);
        this.addEdge("H", "D", 1);   
    }

    private void addEdge(String from, String to, int weight) {
        this.graph.get(from).add(new Edge(to, weight));
        this.graph.get(to).add(new Edge(from, weight)); // Bidirectional graph
    }
    
    public Map<String, List<Edge>> getGraph(){
        return this.graph;
    }
}
