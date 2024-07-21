import java.util.*;

class Stack {
    private LinkedList<Integer> items;

    public Stack() {
        items = new LinkedList<>();
    }

    public void push(int element) {
        items.addLast(element);
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        return items.removeLast();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}

public class dfs {
    private static Map<Integer, List<Integer>> graph = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Input edge graf (misal: \"1 2\"). Ketik \"done\" untuk selesai.");
        askGraphInput();
    }

    private static void askGraphInput() {
        while (true) {
            System.out.print("Masukkan edge (format: node1 node2, atau \"done\" untuk selesai): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                askStartNode();
                break;
            } else {
                String[] nodes = input.split(" ");
                int node1 = Integer.parseInt(nodes[0]);
                int node2 = Integer.parseInt(nodes[1]);

                graph.putIfAbsent(node1, new LinkedList<>());
                graph.putIfAbsent(node2, new LinkedList<>());

                graph.get(node1).add(node2);
            }
        }
    }

    private static void askStartNode() {
        System.out.print("Masukkan node awal DFS: ");
        int start = scanner.nextInt();
        dfs(start);
    }

    private static void dfs(int start) {
        Stack stack = new Stack();
        Set<Integer> visited = new HashSet<>();
        List<Integer> order = new ArrayList<>();
        int count = 0;

        stack.push(start);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                order.add(vertex);
                count++;
                System.out.println("visited vertex : " + vertex + ", count: " + count);

                List<Integer> neighbors = graph.get(vertex);
                if (neighbors != null) {
                    for (int i = neighbors.size() - 1; i >= 0; i--) {
                        int neighbor = neighbors.get(i);
                        if (!visited.contains(neighbor)) {
                            stack.push(neighbor);
                        }
                    }
                }
            }
        }

        System.out.println("DFS order: " + order);
    }
}
