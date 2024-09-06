import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int height, width, maps[][], maxSum;
	static boolean isVisited[][];

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine().trim());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());

		maps = new int[height][width];
		for (int rIdx = 0; rIdx < height; rIdx++) {

			st = new StringTokenizer(br.readLine().trim());
			for (int cIdx = 0; cIdx < width; cIdx++) {
				maps[rIdx][cIdx] = Integer.parseInt(st.nextToken());
			}
		}

		maxSum = 0;
		isVisited = new boolean[height][width];
		for (int rIdx = 0; rIdx < height; rIdx++) {
			for (int cIdx = 0; cIdx < width; cIdx++) {
				isVisited[rIdx][cIdx] = true;
				dfs(rIdx, cIdx, maps[rIdx][cIdx], 1);
				isVisited[rIdx][cIdx] = false;
				checkOtherShape(rIdx, cIdx);
			}
		}

		System.out.println(maxSum);

	}

	static int[] dr = { 0, -1, 0, 1 }, dc = { 1, 0, -1, 0 };

	private static void dfs(int rIdx, int cIdx, int tmpSum, int depth) {

		if (depth == 4) {
			maxSum = Math.max(maxSum, tmpSum);
			return;
		}

		for (int direction = 0; direction < 4; direction++) {
			int nr = rIdx + dr[direction];
			int nc = cIdx + dc[direction];

			if (nr < 0 || nc < 0 || nr >= height || nc >= width)
				continue;
			if (isVisited[nr][nc])
				continue;

			isVisited[nr][nc] = true;
			dfs(nr, nc, tmpSum + maps[nr][nc], depth + 1);
			isVisited[nr][nc] = false;
		}
	}

	// dfs로 탐색할 수 없는 모양(ㅗ) 확인

	private static void checkOtherShape(int row, int col) {
		// ㅗ
		if (col + 1 < width && col + 2 < width && row + 1 < height) {
			int sum = maps[row][col] + maps[row][col + 1] + maps[row][col + 2] + maps[row + 1][col + 1];
			maxSum = Math.max(maxSum, sum);
		}

		// ㅏ
		if (row + 1 < height && row + 2 < height && col + 1 < width) {
			int sum = maps[row][col] + maps[row + 1][col] + maps[row + 2][col] + maps[row + 1][col + 1];
			maxSum = Math.max(maxSum, sum);
		}

		// ㅜ
		if (col + 1 < width && col + 2 < width && row - 1 >= 0) {
			int sum = maps[row][col] + maps[row][col + 1] + maps[row][col + 2] + maps[row - 1][col + 1];
			maxSum = Math.max(maxSum, sum);
		}

		// ㅓ
		if (row + 1 < height && row + 2 < height && col - 1 >= 0) {
			int sum = maps[row][col] + maps[row + 1][col] + maps[row + 2][col] + maps[row + 1][col - 1];
			maxSum = Math.max(maxSum, sum);
		}
	}

}
