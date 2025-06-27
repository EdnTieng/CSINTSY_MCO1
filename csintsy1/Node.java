/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csintsy1;

/**
 *
 * @author Lenovo
 */
public class Node implements Comparable<Node> {

    private String name;
    private int heuristic;
    
    public Node(String name, int heuristic) {
        this.name = name;
        this.heuristic = heuristic;
    }
    
    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.heuristic, other.heuristic);
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getHeuristic(){
        return this.heuristic;
    }
}
