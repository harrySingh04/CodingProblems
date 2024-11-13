package July2024_problems;

import java.util.*;

public class DailyQuestion_July3 {


    int m, n;

    int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int shortestBridge(int[][] grid) {

        m = grid.length;
        n = grid[0].length;

        Set<Pair> visited = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    dfsVisit(grid, i, j, visited);
                    return bfsVisit(grid, visited);
                }
            }
        }

        return 0;
    }

    public boolean isSafe(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    public void dfsVisit(int[][] grid, int i, int j, Set<Pair> visited) {

        Pair pair = new Pair(i, j);

        if (!isSafe(i, j) || grid[i][j] == 0 || visited.contains(pair))
            return;

        visited.add(pair);

        for (int[] dir : directions) {
            int i_ = i + dir[0];
            int j_ = j + dir[1];

            dfsVisit(grid, i_, j_, visited);
        }
    }

    public int bfsVisit(int[][] grid, Set<Pair> visited) {

        Queue<Pair> queue = new LinkedList<>();

        for (Pair p : visited) {
            queue.add(p);
        }

        int level = 0;

        while (!queue.isEmpty()) {

            int L = queue.size();

            while (L > 0) {

                Pair p = queue.poll();

                for (int[] dir : directions) {

                    int x_ = p.row + dir[0];
                    int y_ = p.col + dir[1];

                    if (isSafe(x_, y_) && !visited.contains(new Pair(x_, y_))) {
                        if (grid[x_][y_] == 1)
                            return level;
                        Pair newPair = new Pair(x_, y_);
                        visited.add(newPair);
                        queue.add(newPair);
                    }
                }
                L--;
            }

            level++;

        }

        return level;
    }
}

class Pair {

    int row;
    int col;

    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return row == pair.row && col == pair.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
