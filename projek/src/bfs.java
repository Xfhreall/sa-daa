import java.util.*;

class Queue {
    private LinkedList<Integer> items;

    public Queue() {
        items = new LinkedList<>();
    }

    public void enqueue(int element) {
        items.addLast(element);
    }

    public int dequeue() {
        if (isEmpty()) {
            return -1;
        }
        return items.removeFirst();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}

public class bfs {
    private static Map<Integer, List<Integer>> graph = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Input edge graf (misal: \"1 2\"). Ketik \"done\" untuk selesai.");
        askGraphInput();
    }

    private static void askGraphInput() {
        while (true) {
            System.out.print("Masukkan edge (node1 node2, atau \"done\" untuk selesai): ");
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
                graph.get(node2).add(node1);
            }
        }
    }

    private static void askStartNode() {
        System.out.print("Masukkan node awal BFS: ");
        int start = scanner.nextInt();
        bfs(start);
    }

    private static void bfs(int start) {
        Queue queue = new Queue();
        Set<Integer> visited = new HashSet<>();
        List<Integer> order = new ArrayList<>();
        int count = 0;

        queue.enqueue(start);

        while (!queue.isEmpty()) {
            int vertex = queue.dequeue();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                order.add(vertex);
                count++;
                System.out.println("visited vertex : " + vertex + ", count: " + count);

                List<Integer> neighbors = graph.get(vertex);
                if (neighbors != null) {
                    for (int neighbor : neighbors) {
                        if (!visited.contains(neighbor)) {
                            queue.enqueue(neighbor);
                        }
                    }
                }
            }
        }

        System.out.println("BFS order: " + order);
    }
}
