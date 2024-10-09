import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Point {
		int row, col;

		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine().trim());

		for (int testCase = 0; testCase < tc; testCase++) {

			int mapSize = Integer.parseInt(br.readLine().trim());

			// 시작 지점
			st = new StringTokenizer(br.readLine().trim());
			Point startP = getThePoint();

			// 목표 지점
			st = new StringTokenizer(br.readLine().trim());
			Point targetP = getThePoint();

			int minMove = knightMinMove(startP, targetP, mapSize);

			sb.append(minMove).append("\n");
		}

		System.out.println(sb);

	}

	static Point getThePoint() {
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());

		return new Point(row, col);
	}

	static int knightMinMove(Point startP, Point targetP, int mapSize) {

		int move = -1;

		int[] dr = { -1, -2, -2, -1, 1, 2, 2, 1 };
		int[] dc = { 2, 1, -1, -2, -2, -1, 1, 2 };

		Queue<Point> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[mapSize][mapSize];

		visited[startP.row][startP.col] = true;
		queue.add(startP);

		outer : while (!queue.isEmpty()) {

			move++;
			int size = queue.size();

			for (int loop = 0; loop < size; loop++) {
				Point current = queue.poll();

				if (current.row == targetP.row && current.col == targetP.col) {
					break outer;
				}

				for (int direction = 0; direction < dr.length; direction++) {

					int nr = current.row + dr[direction];
					int nc = current.col + dc[direction];

					if (nr < 0 || nc < 0 || nr >= mapSize || nc >= mapSize)
						continue;
					if (visited[nr][nc])
						continue;

					visited[nr][nc] = true;
					queue.add(new Point(nr, nc));
				}
			}
		}

		return move;
	}

}
