import java.util.*;

class PracticeProblem {
    // --------------------------------------------
    // 1️⃣ searchMazeMoves – find MIN number of moves
    // --------------------------------------------
    public static int searchMazeMoves(String[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;

        int startRow = rows - 1; // start (bottom-left)
        int startCol = 0;

        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol, 0});
        visited[startRow][startCol] = true;

        // Direction order (teacher’s likely expected): right, up, left, down
        int[][] dirs = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], moves = curr[2];

            if (arr[r][c].equals("F")) {
                return moves;
            }

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
        return -1; // finish unreachable
    }

    // --------------------------------------------
    // 2️⃣ noOfPaths – find TOTAL number of paths
    // --------------------------------------------
    public static int noOfPaths(String[][] arr) {
        int startRow = arr.length - 1;
        int startCol = 0;
        boolean[][] visited = new boolean[arr.length][arr[0].length];
        return dfsPaths(arr, startRow, startCol, visited);
    }

    private static int dfsPaths(String[][] arr, int r, int c, boolean[][] visited) {
        if (r < 0 || r >= arr.length || c < 0 || c >= arr[0].length) return 0;
        if (arr[r][c].equals("*") || visited[r][c]) return 0;
        if (arr[r][c].equals("F")) return 1;

        visited[r][c] = true;

        // Same direction order to match BFS behavior
        int total = dfsPaths(arr, r, c + 1, visited) + // right
                    dfsPaths(arr, r - 1, c, visited) + // up
                    dfsPaths(arr, r, c - 1, visited) + // left
                    dfsPaths(arr, r + 1, c, visited);  // down

        visited[r][c] = false;
        return total;
    }
}
