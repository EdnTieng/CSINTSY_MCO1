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
        this.graph.get("A").add(new Edge("B", 4));
        this.graph.get("A").add(new Edge("C", 2));

        this.graph.get("B").add(new Edge("A", 4));
        this.graph.get("B").add(new Edge("C", 5));
        this.graph.get("B").add(new Edge("D", 10));

        this.graph.get("C").add(new Edge("A", 2));
        this.graph.get("C").add(new Edge("B", 5));

        this.graph.get("D").add(new Edge("B", 10));
        this.graph.get("D").add(new Edge("E", 3));
    }
    
    public Map<String, List<Edge>> getGraph(){
        return this.graph;
    }
}
