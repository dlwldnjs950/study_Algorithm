import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;

	static final int BLOCKheightUwidth = 4;
	static int height, width, maps[][], selectedIdx[], maxSum;
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

		// 각 위치에 대해 가능한 합을 모두 구하자
		isVisited = new boolean[height][width];
		for (int rIdx = 0; rIdx < height; rIdx++) {
			for (int cIdx = 0; cIdx < width; cIdx++) {
				isVisited[rIdx][cIdx] = true;
				dfs(rIdx, cIdx, maps[rIdx][cIdx], 1);
				isVisited[rIdx][cIdx] = false;
				checkExceptionShape(rIdx, cIdx);
			}
		}

		System.out.println(maxSum);

	}

	static int[] dr = { 0, -1, 0, 1 }, dc = { -1, 0, 1, 0 };

	// DFS를 사용하여 테트로미노 모양 탐색
	private static void dfs(int x, int y, int sum, int depth) {
		if (depth == 4) {
			maxSum = Math.max(maxSum, sum);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dr[i];
			int ny = y + dc[i];

			if (nx < 0 || nx >= height || ny < 0 || ny >= width || isVisited[nx][ny])
				continue;

			isVisited[nx][ny] = true;
			dfs(nx, ny, sum + maps[nx][ny], depth + 1);
			isVisited[nx][ny] = false;
		}
	}

	// 'ㅗ', 'ㅜ', 'ㅓ', 'ㅏ' 모양 체크
	private static void checkExceptionShape(int x, int y) {
		if (x >= 0 && x < height - 1 && y >= 0 && y < width - 2) {
			int sum = maps[x][y] + maps[x][y + 1] + maps[x][y + 2] + maps[x + 1][y + 1];
			maxSum = Math.max(maxSum, sum);
		}
		if (x >= 1 && x < height && y >= 0 && y < width - 2) {
			int sum = maps[x][y] + maps[x][y + 1] + maps[x][y + 2] + maps[x - 1][y + 1];
			maxSum = Math.max(maxSum, sum);
		}
		if (x >= 0 && x < height - 2 && y >= 0 && y < width - 1) {
			int sum = maps[x][y] + maps[x + 1][y] + maps[x + 2][y] + maps[x + 1][y + 1];
			maxSum = Math.max(maxSum, sum);
		}
		if (x >= 0 && x < height - 2 && y >= 1 && y < width) {
			int sum = maps[x][y] + maps[x + 1][y] + maps[x + 2][y] + maps[x + 1][y - 1];
			maxSum = Math.max(maxSum, sum);
		}
	}

}
