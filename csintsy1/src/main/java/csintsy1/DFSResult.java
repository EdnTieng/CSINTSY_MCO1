/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csintsy1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Lenovo
 */
public class DFSResult {
    private final Map<String, String> previous = new HashMap<>();
    private boolean found = false;
    private int totalCost = 0;
    
    public Map<String, String> getPrevious(){
        return this.previous;
    }
    
    public boolean getFound(){
        return this.found;
    }
    
    public int getTotalCost(){
        return this.totalCost;
    }
    
    public void setFound(boolean x){
        this.found = x;
    }
    
    public void setTotalCost(int x){
        this.totalCost = x;
    }
    
    public void addPrevious(String target, String current){
        this.previous.put(target, current);
    }
}
