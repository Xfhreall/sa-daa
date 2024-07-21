function lcs(X, Y) {
  const m = X.length;
  const n = Y.length;

  const c = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
  const b = Array.from({ length: m + 1 }, () => Array(n + 1).fill(""));

  for (let i = 1; i <= m; i++) {
    for (let j = 1; j <= n; j++) {
      if (X[i - 1] === Y[j - 1]) {
        c[i][j] = c[i - 1][j - 1] + 1;
        b[i][j] = "/";
      } else {
        if (c[i - 1][j] >= c[i][j - 1]) {
          c[i][j] = c[i - 1][j];
          b[i][j] = "^";
        } else {
          c[i][j] = c[i][j - 1];
          b[i][j] = "<";
        }
      }
    }
  }

  function printLCS(b, X, i, j) {
    if (i === 0 || j === 0) {
      return "";
    }
    if (b[i][j] === "/") {
      return printLCS(b, X, i - 1, j - 1) + X[i - 1];
    } else if (b[i][j] === "^") {
      return printLCS(b, X, i - 1, j);
    } else {
      return printLCS(b, X, i, j - 1);
    }
  }

  console.log("Matrix c :");
  for (let i = 0; i < c.length; i++) {
    console.log(c[i].join(" "));
  }

  console.log("\nMatrix b :");
  for (let i = 0; i < b.length; i++) {
    console.log(b[i].join(" "));
  }

  const lcsResult = printLCS(b, X, m, n);
  return lcsResult;
}
const X = "ABDECAFG";
const Y = "BDEACGFCA";
console.log("LCS dari X dan Y adalah: ", lcs(X, Y));
