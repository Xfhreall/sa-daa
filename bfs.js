class Queue {
  constructor() {
    this.items = [];
  }

  enqueue(element) {
    this.items.push(element);
  }

  dequeue() {
    if (this.isEmpty()) {
      return;
    }
    return this.items.shift();
  }

  isEmpty() {
    return this.items.length === 0;
  }
}

let graph = new Map();
graph.set("1", ["2", "3", "4"]);
graph.set("2", []);
graph.set("3", ["5", "6"]);
graph.set("4", []);
graph.set("5", []);
graph.set("6", []);

const bfs = (graph, start) => {
  let count = 0;
  let queue = new Queue();
  let visited = new Set();
  let arr = [];

  const bfsRecursive = () => {
    if (queue.isEmpty()) {
      return;
    }

    let vertex = queue.dequeue();
    if (!visited.has(vertex)) {
      visited.add(vertex);
      arr[count++] = vertex;
      console.log(`visited vertex : ${vertex}, count: ${count}`);
      let neighbors = graph.get(vertex);
      for (let neighbor of neighbors) {
        if (!visited.has(neighbor)) {
          queue.enqueue(neighbor);
        }
      }
    }
    bfsRecursive();
  };

  queue.enqueue(start);
  bfsRecursive();
  console.log(`BFS order : ${arr.join(" ")}`);
};

bfs(graph, "1");

//interaktif
const readline = require("readline");

class Queue2 {
  constructor() {
    this.items = [];
  }

  enqueue(element) {
    this.items.push(element);
  }

  dequeue() {
    if (this.isEmpty()) {
      return;
    }
    return this.items.shift();
  }

  isEmpty() {
    return this.items.length === 0;
  }
}

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let graph2 = new Map();

const askGraphInput = () => {
  rl.question(
    'Masukkan edge (node1 node2, atau "done" untuk selesai): ',
    (input) => {
      if (input.toLowerCase() === "done") {
        askStartNode();
      } else {
        const [node1, node2] = input.split(" ");
        if (!graph2.has(node1)) {
          graph2.set(node1, []);
        }
        if (!graph2.has(node2)) {
          graph2.set(node2, []);
        }
        graph2.get(node1).push(node2);
        graph2.get(node2).push(node1);
        askGraphInput();
      }
    }
  );
};

const askStartNode = () => {
  rl.question("Masukkan node edge awal BFS: ", (start) => {
    bfs2(graph2, start);
    rl.close();
  });
};

const bfs2 = (graph2, start) => {
  let queue = new Queue2();
  let visited = new Set();
  let count = 0;
  let arr = [];

  const bfsRecursive = () => {
    if (queue.isEmpty()) {
      return;
    }

    let vertex = queue.dequeue();
    if (!visited.has(vertex)) {
      visited.add(vertex);
      arr[count++] = vertex;
      console.log(`visited vertex : ${vertex}, count: ${count}`);

      let neighbors = graph2.get(vertex);
      for (let neighbor of neighbors) {
        if (!visited.has(neighbor)) {
          queue.enqueue(neighbor);
        }
      }
    }
    bfsRecursive();
  };

  queue.enqueue(start);
  bfsRecursive();
  console.log(`BFS order : ${arr.join(" ")}`);
};

console.log('Input edge graf (misal: "A B"). Ketik "done" untuk keluar.');
askGraphInput();
