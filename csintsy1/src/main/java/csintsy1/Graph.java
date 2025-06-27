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
        this.addEdge("S", "M", 400);
        this.addEdge("S", "O", 50);
        this.addEdge("S", "Q", 50);
        this.addEdge("S", "R", 160);
        this.addEdge("S", "T", 500);

        this.addEdge("M", "N", 60);
        this.addEdge("M", "K", 190);

        this.addEdge("N", "O", 210);
        this.addEdge("N", "I", 90);

        this.addEdge("O", "P", 110);
        this.addEdge("O", "E", 280);

        this.addEdge("P", "Q", 225);
        this.addEdge("Q", "R", 210);
        this.addEdge("Q", "C", 280);

        this.addEdge("R", "T", 260);

        this.addEdge("T", "B", 190);
        this.addEdge("T", "A", 160);

        this.addEdge("K", "J", 20);
        this.addEdge("J", "I", 10);
        this.addEdge("I", "G", 50);
        this.addEdge("G", "F", 50);
        this.addEdge("F", "E", 30);
        this.addEdge("E", "C", 230);

        this.addEdge("C", "B", 60);
        this.addEdge("C", "D", 280);

        this.addEdge("U", "K", 60);
        this.addEdge("U", "V", 270); 

        this.addEdge("V", "L", 60);
        this.addEdge("L", "H", 80);
        this.addEdge("H", "D", 120);   

        this.addEdge("I", "L", 170);
        this.addEdge("K", "V", 50);
        this.addEdge("J", "V", 50);
        this.addEdge("G", "H", 120);
        this.addEdge("F", "H", 170);
        this.addEdge("E", "H", 180);
    }

    private void addEdge(String from, String to, int weight) {
        this.graph.get(from).add(new Edge(to, weight));
        this.graph.get(to).add(new Edge(from, weight)); // Bidirectional graph
    }
    
    public Map<String, List<Edge>> getGraph(){
        return this.graph;
    }
}
