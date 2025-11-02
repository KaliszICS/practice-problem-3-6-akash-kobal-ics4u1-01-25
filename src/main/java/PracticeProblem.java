public class PracticeProblem {

	public static void main(String args[]) {

	}

	public static int searchMazeMoves(String[][] maze) {
		int rows = maze.length, cols = maze[0].length;
		int sr = rows - 1, sc = 0;
		java.util.Queue<int[]> q = new java.util.LinkedList<>();
		boolean[][] vis = new boolean[rows][cols];
		if ("*".equals(maze[sr][sc])) return -1;
		q.add(new int[]{sr, sc, 0});
		vis[sr][sc] = true;
		int[][] dirs = {{-1,0},{0,1}};
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0], c = cur[1], d = cur[2];
			if ("F".equals(maze[r][c])) return d;
			for (int[] dir : dirs) {
				int nr = r + dir[0], nc = c + dir[1];
				if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && !"*".equals(maze[nr][nc]) && !vis[nr][nc]) {
					vis[nr][nc] = true;
					q.add(new int[]{nr, nc, d + 1});
				}
			}
		}
		return -1;
	}

	public static int noOfPaths(String[][] maze) {
		int rows = maze.length, cols = maze[0].length;
		int fr = -1, fc = -1;
		for (int i = 0; i < rows; i++) for (int j = 0; j < cols; j++) if ("F".equals(maze[i][j])) { fr = i; fc = j; }
		if (fr == -1) return 0;
		long[][] ways = new long[rows][cols];
		int sr = rows - 1, sc = 0;
		if (!"*".equals(maze[sr][sc])) ways[sr][sc] = 1;
		for (int r = rows - 1; r >= 0; r--) {
			for (int c = 0; c < cols; c++) {
				if ("*".equals(maze[r][c])) { ways[r][c] = 0; continue; }
				if (r - 1 >= 0 && !"*".equals(maze[r - 1][c])) ways[r - 1][c] += ways[r][c];
				if (c + 1 < cols && !"*".equals(maze[r][c + 1])) ways[r][c + 1] += ways[r][c];
			}
		}
		long ans = ways[fr][fc];
		return ans > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)ans;
	}
}
