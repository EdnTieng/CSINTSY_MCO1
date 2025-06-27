/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csintsy1;

import java.util.*;

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
    private Map<String, Integer> heuristics = new HashMap<>();  // <-- A* uses this

    public Graph() {
        for (String node : letters) {
            this.graph.put(node, new ArrayList<>());
        }

        // --- Edges ---
        // Group 1
        this.addEdge("S", "M", 250);
        this.addEdge("S", "O", 230);
        this.addEdge("S", "Q", 10);
        this.addEdge("S", "R", 50);
        this.addEdge("S", "T", 100);

        // Group 2
        this.addEdge("M", "K", 50);
        this.addEdge("M", "N", 10);
        this.addEdge("M", "S", 250);

        this.addEdge("N", "I", 80);
        this.addEdge("N", "O", 100);
        this.addEdge("N", "M", 10);

        this.addEdge("O", "S", 230);
        this.addEdge("O", "N", 100);
        this.addEdge("O", "P", 100);
        this.addEdge("O", "E", 100);

        this.addEdge("P", "O", 100);
        this.addEdge("P", "Q", 10);

        this.addEdge("Q", "S", 10);
        this.addEdge("Q", "P", 10);
        this.addEdge("Q", "R", 50);
        this.addEdge("Q", "C", 80);

        this.addEdge("R", "S", 50);
        this.addEdge("R", "Q", 50);
        this.addEdge("R", "T", 20);

        this.addEdge("T", "S", 100);
        this.addEdge("T", "R", 20);
        this.addEdge("T", "B", 30);
        this.addEdge("T", "A", 20);

        // Group 3
        this.addEdge("K", "M", 50);
        this.addEdge("K", "J", 10);
        this.addEdge("K", "U", 30);

        this.addEdge("J", "K", 10);
        this.addEdge("J", "I", 10);

        this.addEdge("I", "N", 80);
        this.addEdge("I", "J", 10);
        this.addEdge("I", "G", 40);

        this.addEdge("G", "I", 40);
        this.addEdge("G", "F", 20);

        this.addEdge("F", "G", 20);
        this.addEdge("F", "E", 30);

        this.addEdge("E", "O", 100);
        this.addEdge("E", "F", 30);
        this.addEdge("E", "C", 50);

        this.addEdge("C", "Q", 80);
        this.addEdge("C", "E", 50);
        this.addEdge("C", "B", 10);
        this.addEdge("C", "D", 100);

        this.addEdge("B", "T", 30);
        this.addEdge("B", "C", 10);
        this.addEdge("B", "A", 10);

        this.addEdge("A", "B", 10);
        this.addEdge("A", "T", 20);

        // Group 4
        this.addEdge("U", "K", 30);
        this.addEdge("U", "J", 50);

        this.addEdge("J", "U", 50);
        this.addEdge("J", "L", 60);

        this.addEdge("L", "J", 60);
        this.addEdge("L", "H", 20);

        this.addEdge("H", "L", 20);
        this.addEdge("H", "D", 50);

        this.addEdge("D", "C", 100);
        this.addEdge("D", "H", 50);


        // --- Heuristics for A* (example values) ---
        initHeuristics();
    }

    private void addEdge(String from, String to, int weight) {
        this.graph.get(from).add(new Edge(to, weight));
        this.graph.get(to).add(new Edge(from, weight)); // Bidirectional graph
    }

    public Map<String, List<Edge>> getGraph() {
        return this.graph;
    }

    // A* Heuristic initialization
    private void initHeuristics() {
        // These should be estimated distances to the GOAL node.
        // Example goal: A (you can change accordingly)

        heuristics.put("A", 0);
        heuristics.put("B", 100);
        heuristics.put("C", 180);
        heuristics.put("D", 300);
        heuristics.put("E", 250);
        heuristics.put("F", 270);
        heuristics.put("G", 290);
        heuristics.put("H", 280);
        heuristics.put("I", 320);
        heuristics.put("J", 350);
        heuristics.put("K", 340);
        heuristics.put("L", 260);
        heuristics.put("M", 450);
        heuristics.put("N", 430);
        heuristics.put("O", 400);
        heuristics.put("P", 350);
        heuristics.put("Q", 300);
        heuristics.put("R", 280);
        heuristics.put("S", 500);  // if start is S
        heuristics.put("T", 200);
        heuristics.put("U", 400);
        heuristics.put("V", 360);
    }

    public int getHeuristic(String node) {
        return heuristics.getOrDefault(node, Integer.MAX_VALUE);
    }
    
}
