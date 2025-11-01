import java.util.*;

class PracticeProblem {
    public static void main(String[] args) {
        String[][] maze = {
            {"", "", "", "F"},
            {"", "*", "", ""},
            {"S", "", "", ""}
        };

        System.out.println("Minimum Moves: " + searchMazeMoves(maze)); // Expected: shortest path count
        System.out.println("Number of Paths: " + noOfPaths(maze));     // Expected: total valid paths
    }

    // --------------------------------------------
    // 1️⃣ searchMazeMoves – find MIN number of moves (BFS to guarantee shortest path)
    // --------------------------------------------
    public static int searchMazeMoves(String[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;

        int startRow = rows - 1; // bottom left (S)
        int startCol = 0;

        // queue will hold {row, col, moves}
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        queue.add(new int[]{startRow, startCol, 0});
        visited[startRow][startCol] = true;

        // directions: up, down, left, right
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int moves = curr[2];

            // If finish found
            if (arr[r][c].equals("F")) {
                return moves;
            }

            // explore all directions
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols &&
                    !arr[nr][nc].equals("*") && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc, moves + 1});
                }
            }
        }

        // if finish not reachable
        return -1;
    }

    // --------------------------------------------
    // 2️⃣ noOfPaths – find TOTAL number of paths (DFS)
    // --------------------------------------------
    public static int noOfPaths(String[][] arr) {
        int curRow = arr.length - 1; // bottom row
        int curCol = 0;              // first column
        boolean[][] visited = new boolean[arr.length][arr[0].length];
        return noOfPathsHelper(arr, curRow, curCol, visited);
    }

    public static int noOfPathsHelper(String[][] arr, int curRow, int curCol, boolean[][] visited) {
        // base case 1: out of bounds
        if (curRow < 0 || curRow >= arr.length || curCol < 0 || curCol >= arr[0].length) {
            return 0;
        }

        // base case 2: hit a wall
        if (arr[curRow][curCol].equals("*")) {
            return 0;
        }

        // base case 3: already visited
        if (visited[curRow][curCol]) {
            return 0;
        }

        // base case 4: reached finish
        if (arr[curRow][curCol].equals("F")) {
            return 1;
        }

        // mark as visited
        visited[curRow][curCol] = true;

        // explore all directions
        int totalPaths = 0;
        totalPaths += noOfPathsHelper(arr, curRow, curCol + 1, visited); // right
        totalPaths += noOfPathsHelper(arr, curRow, curCol - 1, visited); // left
        totalPaths += noOfPathsHelper(arr, curRow - 1, curCol, visited); // up
        totalPaths += noOfPathsHelper(arr, curRow + 1, curCol, visited); // down

        // backtrack
        visited[curRow][curCol] = false;

        return totalPaths;
    }
}
