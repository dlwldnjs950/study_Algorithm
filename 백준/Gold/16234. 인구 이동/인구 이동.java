import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int mapSize, minDiff, maxDiff, map[][];
	static boolean isVisited[][];
	static List<int[]> list;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		mapSize = Integer.parseInt(st.nextToken());
		minDiff = Integer.parseInt(st.nextToken());
		maxDiff = Integer.parseInt(st.nextToken());

		map = new int[mapSize][mapSize];
		for (int rIdx = 0; rIdx < mapSize; rIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int cIdx = 0; cIdx < mapSize; cIdx++) {
				map[rIdx][cIdx] = Integer.parseInt(st.nextToken());
			}
		}

		int result = 0;
		while (true) {
			boolean isMove = false;
			isVisited = new boolean[mapSize][mapSize];
			for (int rIdx = 0; rIdx < mapSize; rIdx++) {
				for (int cIdx = 0; cIdx < mapSize; cIdx++) {
					if (!isVisited[rIdx][cIdx]) {
						int sum = BFS(rIdx, cIdx);
						if (list.size() > 1) {
							populationMove(sum);
							isMove = true;
						}
					}
				}
			}
			if (!isMove)
				break;
			result++;
		}

		sb.append(result);
		System.out.println(sb);

	}

	static int[] dr = { 1, 0, -1, 0 }, dc = { 0, 1, 0, -1 };

	public static int BFS(int row, int col) {
		Queue<int[]> queue = new ArrayDeque<>();
		list = new ArrayList<int[]>();

		queue.offer(new int[] { row, col });
		list.add(new int[] { row, col });
		isVisited[row][col] = true;

		int sum = map[row][col];

		while (!queue.isEmpty()) {
			int[] current = queue.poll();

			for (int direction = 0; direction < dr.length; direction++) {
				int nr = current[0] + dr[direction];
				int nc = current[1] + dc[direction];
				if (nr < 0 || nc < 0 || nr >= mapSize || nc >= mapSize || isVisited[nr][nc])
					continue;
				int diff = Math.abs(map[current[0]][current[1]] - map[nr][nc]);
				if (minDiff <= diff && diff <= maxDiff) {
					queue.offer(new int[] { nr, nc });
					list.add(new int[] { nr, nc });
					sum += map[nr][nc];
					isVisited[nr][nc] = true;
				}
			}
		}

		return sum;
	}

	private static void populationMove(int sum) {
		int avg = sum / list.size();
		for(int[] city : list) {
			map[city[0]][city[1]] = avg;
		}

	}

}
