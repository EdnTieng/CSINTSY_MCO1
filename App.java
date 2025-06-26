import java.util.*;

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
        

        System.out.println();

       
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

        scanner.close();
    }
}
