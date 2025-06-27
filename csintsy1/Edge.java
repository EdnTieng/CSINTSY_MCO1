/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csintsy1;

/**
 *
 * @author Lenovo
 */
public class Edge {
    private String target;
    private int weight;

    public Edge(String target, int weight) {
        this.target = target;
        this.weight = weight;
    }
    
    public String getTarget(){
        return this.target;
    }
    
    public int getWeight(){
        return this.weight;
    }
}
