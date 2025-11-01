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
    // 1️⃣ searchMazeMoves – find MIN number of moves (DFS)
    // --------------------------------------------
    public static int searchMazeMoves(String[][] arr) {
        int curRow = arr.length - 1; // bottom row
        int curCol = 0;              // first column
        int noOfMoves = 0;

        boolean[][] visited = new boolean[arr.length][arr[0].length];
        int result = searchMazeHelper(arr, curRow, curCol, noOfMoves, visited);
        return (result == Integer.MAX_VALUE) ? -1 : result;
    }

    public static int searchMazeHelper(String[][] arr, int curRow, int curCol, int noOfMoves, boolean[][] visited) {
        // base case 1: out of bounds
        if (curRow < 0 || curRow >= arr.length || curCol < 0 || curCol >= arr[0].length) {
            return Integer.MAX_VALUE;
        }

        // base case 2: hit a wall
        if (arr[curRow][curCol].equals("*")) {
            return Integer.MAX_VALUE;
        }

        // base case 3: already visited
        if (visited[curRow][curCol]) {
            return Integer.MAX_VALUE;
        }

        // base case 4: reached the finish
        if (arr[curRow][curCol].equals("F")) {
            return noOfMoves;
        }

        // mark as visited
        visited[curRow][curCol] = true;

        // recursive exploration in 4 directions
        int right = searchMazeHelper(arr, curRow, curCol + 1, noOfMoves + 1, visited);
        int left  = searchMazeHelper(arr, curRow, curCol - 1, noOfMoves + 1, visited);
        int up    = searchMazeHelper(arr, curRow - 1, curCol, noOfMoves + 1, visited);
        int down  = searchMazeHelper(arr, curRow + 1, curCol, noOfMoves + 1, visited);

        // backtrack
        visited[curRow][curCol] = false;

        // choose the smallest valid number of moves
        return Math.min(Math.min(right, left), Math.min(up, down));
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
