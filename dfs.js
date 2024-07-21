class Stack {
  constructor() {
    this.items = [];
  }

  push(element) {
    this.items.push(element);
  }

  pop() {
    if (this.isEmpty()) {
      return "Stack is empty";
    }
    return this.items.pop();
  }

  isEmpty() {
    return this.items.length === 0;
  }
}

let graph = new Map();
graph.set("1", ["2", "3", "4"]);
graph.set("2", ["5", "6"]);
graph.set("3", ["7"]);
graph.set("4", ["8"]);
graph.set("5", []);
graph.set("6", []);
graph.set("7", []);
graph.set("8", []);

const dfs = (graph, start) => {
  let count = 0;
  let stack = new Stack();
  let visited = new Set();
  let arr = [];

  const dfsRecursive = () => {
    if (stack.isEmpty()) {
      return;
    }

    let vertex = stack.pop();
    if (!visited.has(vertex)) {
      visited.add(vertex);
      arr[count++] = vertex;
      console.log(`visited vertex : ${vertex}, count: ${count}`);
      let neighbors = graph.get(vertex);
      for (let i = neighbors.length - 1; i >= 0; i--) {
        let neighbor = neighbors[i];
        if (!visited.has(neighbor)) {
          stack.push(neighbor);
        }
      }
    }
    dfsRecursive();
  };

  stack.push(start);
  dfsRecursive();
  console.log(`DFS order : ${arr.join(" ")}`);
};

dfs(graph, "1");

//interaktif
const readline = require("readline");

class Stack2 {
  constructor() {
    this.items = [];
  }

  push(element) {
    this.items.push(element);
  }

  pop() {
    if (this.isEmpty()) {
      return "Stack is empty";
    }
    return this.items.pop();
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
    'Masukkan edge (format: node1 node2, atau "done" untuk selesai): ',
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
        askGraphInput();
      }
    }
  );
};

const askStartNode = () => {
  rl.question("Masukkan node awal DFS: ", (start) => {
    dfs2(graph2, start);
    rl.close();
  });
};

const dfs2 = (graph2, start) => {
  let stack = new Stack2();
  let visited = new Set();
  let count = 0;
  let arr = [];
  const dfsRecursive = () => {
    if (stack.isEmpty()) {
      return;
    }

    let vertex = stack.pop();
    if (!visited.has(vertex)) {
      visited.add(vertex);
      arr[count++] = vertex;
      console.log(`visited vertex : ${vertex}, count: ${count}`);
      let neighbors = graph2.get(vertex);
      for (let i = neighbors.length - 1; i >= 0; i--) {
        let neighbor = neighbors[i];
        if (!visited.has(neighbor)) {
          stack.push(neighbor);
        }
      }
    }
    dfsRecursive();
  };

  stack.push(start);
  dfsRecursive();
  console.log(`DFS order : ${arr.join(" ")}`);
};

console.log('Input edge graf (misal: "A B"). Ketik "done" untuk selesai.');
askGraphInput();
