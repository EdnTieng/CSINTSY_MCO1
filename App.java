import java.util.*;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {

    static class Edge {
        String target;
        int weight;

        Edge(String target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    static class DFSResult {
        Map<String, String> previous = new HashMap<>();
        boolean found = false;
        int totalCost = 0;
    }

    // DFS 
    static void dfs(String current, String goal, Map<String, List<Edge>> graph, 
                    Set<String> visited, DFSResult result, int costSoFar) {
        if (result.found) return; // if exit is found

        visited.add(current);

        if (current.equals(goal)) {
            result.found = true;
            result.totalCost = costSoFar;
            return;
        }

        for (Edge edge : graph.get(current)) {
            if (!visited.contains(edge.target)) {
                result.previous.put(edge.target, current);
                dfs(edge.target, goal, graph, visited, result, costSoFar + edge.weight);
                if (result.found) return;
            }
        }
    }

    static List<String> reconstructPath(String goal, Map<String, String> previous) {
        List<String> path = new ArrayList<>();
        for (String at = goal; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    // ----- Begin Greedy Best-First Search additions -----

    static class Node implements Comparable<Node> {
        String name;
        int heuristic;
        Node(String name, int heuristic) {
            this.name = name;
            this.heuristic = heuristic;
        }
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.heuristic, other.heuristic);
        }
    }

    static class BFSResult {
        Map<String, String> previous = new HashMap<>();
        boolean found = false;
        int totalCost = 0;
    }

    // Greedy Best-First Search (using edge weight as heuristic)
    static void bestFirst(String start, String goal, Map<String, List<Edge>> graph, BFSResult result) {
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();
        Map<String, Integer> costSoFar = new HashMap<>();

        frontier.add(new Node(start, 0));
        costSoFar.put(start, 0);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if (visited.contains(current.name)) continue;
            visited.add(current.name);

            if (current.name.equals(goal)) {
                result.found = true;
                result.totalCost = costSoFar.get(goal);
                return;
            }

            for (Edge e : graph.get(current.name)) {
                if (visited.contains(e.target)) continue;
                int newCost = costSoFar.get(current.name) + e.weight;
                boolean improved = !costSoFar.containsKey(e.target) || newCost < costSoFar.get(e.target);
                if (improved) {
                    costSoFar.put(e.target, newCost);
                    result.previous.put(e.target, current.name);
                    frontier.add(new Node(e.target, e.weight));
                }
            }
        }
    }
    // ----- End Greedy Best-First Search additions -----

    public static void main(String[] args) {
        List<String> letters = Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U"
        );
        
        
        Map<String, List<Edge>> graph = new HashMap<>();
        for (String node : letters) {
            graph.put(node, new ArrayList<>());
        }
        // Hardcoded weights and connections
        // Can add more nodes and change connections as needed
        graph.get("A").add(new Edge("B", 4));
        graph.get("A").add(new Edge("C", 2));

        graph.get("B").add(new Edge("A", 4));
        graph.get("B").add(new Edge("C", 5));
        graph.get("B").add(new Edge("D", 10));

        graph.get("C").add(new Edge("A", 2));
        graph.get("C").add(new Edge("B", 5));

        graph.get("D").add(new Edge("B", 10));
        graph.get("D").add(new Edge("E", 3));
        
        
        final int[] choiceWrapper = new int[1];
        final String[] startWrapper = new String[1];
        final String[] goalWrapper = new String[1];
        
        //GUI
        JFrame frame = new JFrame("Temporary GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(new GridLayout(4, 1));
        
        JPanel algorithms = new JPanel();
        algorithms = new JPanel();
        
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new FlowLayout());
        
        JPanel endPanel = new JPanel();
        endPanel.setLayout(new FlowLayout());
        
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new FlowLayout());
        JLabel textbox = new JLabel("No text");
        resultsPanel.add(textbox);
        
        JButton bfsButton = new JButton("BFS");
        bfsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choiceWrapper[0] = 1;
            }
        });
        JButton gbfsButton = new JButton("GBFS");
        gbfsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choiceWrapper[0] = 2;
            }
        });
        JButton findPath = new JButton("Find Path!");
        findPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(choiceWrapper[0] != 0 && startWrapper[0] != null && goalWrapper[0] != null){
                    switch (choiceWrapper[0]) {
                        case 1:
                            // DFS
                            Set<String> visited = new HashSet<>();
                            DFSResult dfsResult = new DFSResult();
                            dfs(startWrapper[0], goalWrapper[0], graph, visited, dfsResult, 0);

                            if (dfsResult.found) {
                                List<String> path = reconstructPath(goalWrapper[0], dfsResult.previous);
                                textbox.setText("DFS path from " + startWrapper[0] + " to " + goalWrapper[0] + ": " +
                                    String.join(" -> ", path) + " - Total cost: " + String.valueOf(dfsResult.totalCost));
                            } else {
                                textbox.setText("No path found from " + startWrapper[0] + " to " + goalWrapper[0] + " using DFS.");
                            }
                            break;

                        case 2:
                            // Greedy Best-First
                            BFSResult bfsResult = new BFSResult();
                            bestFirst(startWrapper[0], goalWrapper[0], graph, bfsResult);
                            if (bfsResult.found) {
                                List<String> path = reconstructPath(goalWrapper[0], bfsResult.previous);
                                textbox.setText("Greedy Best-First path from " + startWrapper[0] + " to " + goalWrapper[0] + ": " +
                                    String.join(" -> ", path) + " - Total cost: " + String.valueOf(bfsResult.totalCost));
                                //System.out.println("Total cost: " + bfsResult.totalCost);
                            } else {
                                textbox.setText("No path found from " + startWrapper[0] + " to " + goalWrapper[0] + " using Greedy Best-First.");
                            }
                            break;

                        default:
                            System.out.println("Invalid option.");
                    }
                }
                else
                {
                    textbox.setText("Input all variables.");
                }
            }
        });
        findPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(choiceWrapper[0]);
                System.out.println(startWrapper[0]);
                System.out.println(goalWrapper[0]);
            }
        });
        algorithms.add(bfsButton);
        algorithms.add(gbfsButton);
        algorithms.add(findPath);
        
        List<JButton> startButtons = new ArrayList<>();
        List<JButton> endButtons = new ArrayList<>();
        for(int i = 0; i < letters.size(); i++)
        {
            startButtons.add(new JButton(letters.get(i)));
            (startButtons.get(i)).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startWrapper[0] = e.getActionCommand();
                }
            });
            startPanel.add(startButtons.get(i));
            
            endButtons.add(new JButton(letters.get(i)));
            (endButtons.get(i)).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    goalWrapper[0] = e.getActionCommand();
                }
            });
            endPanel.add(endButtons.get(i));
        }
        frame.getContentPane().add(algorithms);
        frame.getContentPane().add(startPanel);
        frame.getContentPane().add(endPanel);
        frame.getContentPane().add(resultsPanel);
        frame.setVisible(true);
        //GUI

        /*
        Scanner scanner = new Scanner(System.in);
        
        // 1) Menu
        
        System.out.println("Choose search algorithm:");
        System.out.println("  1) DFS");
        System.out.println("  2) Greedy Best-First");
        System.out.print("Enter choice [1-2]: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // consume newline

        // 2) Read start and goal
        System.out.print("Enter the start state (A-U): ");
        String start = scanner.nextLine().trim().toUpperCase();
        System.out.print("Enter the goal state (A-U): ");
        String goal  = scanner.nextLine().trim().toUpperCase();

        if (!letters.contains(start) || !letters.contains(goal)) {
            System.out.println("Invalid input. Please enter letters from A to U.");
            scanner.close();
            return;
        }

        scanner.close();
        */
        
        /*
        switch (choice) {
            case 1:
                // DFS
                Set<String> visited = new HashSet<>();
                DFSResult dfsResult = new DFSResult();
                dfs(start, goal, graph, visited, dfsResult, 0);
                
                if (dfsResult.found) {
                    List<String> path = reconstructPath(goal, dfsResult.previous);
                    System.out.println("DFS path from " + start + " to " + goal + ": " +
                        String.join(" -> ", path));
                    System.out.println("Total cost: " + dfsResult.totalCost);
                } else {
                    System.out.println("No path found from " + start + " to " + goal + " using DFS.");
                }
                break;

            case 2:
                // Greedy Best-First
                BFSResult bfsResult = new BFSResult();
                bestFirst(start, goal, graph, bfsResult);
                if (bfsResult.found) {
                    List<String> path = reconstructPath(goal, bfsResult.previous);
                    System.out.println("Greedy Best-First path from " + start + " to " + goal + ": " +
                        String.join(" -> ", path));
                    System.out.println("Total cost: " + bfsResult.totalCost);
                } else {
                    System.out.println("No path found from " + start + " to " + goal + " using Greedy Best-First.");
                }
                break;

            default:
                System.out.println("Invalid option.");
        }   
        */
    }
}
