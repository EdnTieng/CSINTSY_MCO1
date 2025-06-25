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

    public static void main(String[] args) {
        List<String> letters = Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U"
        );

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the start state (A-U): ");
        String start = scanner.nextLine().toUpperCase();

        System.out.print("Enter the goal state (A-U): ");
        String goal = scanner.nextLine().toUpperCase();

        if (!letters.contains(start) || !letters.contains(goal)) {
            System.out.println("Invalid input. Please enter letters from A to U.");
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

        // DFS 
        Set<String> visited = new HashSet<>();
        DFSResult result = new DFSResult();
        dfs(start, goal, graph, visited, result, 0);

        if (result.found) {
            List<String> path = reconstructPath(goal, result.previous);
            System.out.println("DFS path from " + start + " to " + goal + ": " + String.join(" -> ", path));
            System.out.println("Total cost: " + result.totalCost);
        } else {
            System.out.println("No path found from " + start + " to " + goal);
        }
    }
}
