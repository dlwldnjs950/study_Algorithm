import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static Set<Integer> numbers;
	static int map[][];

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		map = new int[5][5];
		for (int rIdx = 0; rIdx < 5; rIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int cIdx = 0; cIdx < 5; cIdx++) {
				map[rIdx][cIdx] = Integer.parseInt(st.nextToken());
			}
		}

		numbers = new HashSet<>();
		for (int rIdx = 0; rIdx < 5; rIdx++) {
			for (int cIdx = 0; cIdx < 5; cIdx++) {
				make6Numbers(rIdx, cIdx, map[rIdx][cIdx], 1);
			}
		}

		System.out.println(numbers.size());

	}

	static final int[] dr = { 0, -1, 0, 1 }, dc = { 1, 0, -1, 0 };

	private static void make6Numbers(int rIdx, int cIdx, int midNumber, int depth) {
		if (depth == 6) {
			numbers.add(midNumber);
			return;
		}
		for (int direction = 0; direction < 4; direction++) {
			int nr = rIdx + dr[direction];
			int nc = cIdx + dc[direction];

			if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5)
				continue;

			make6Numbers(nr, nc, midNumber * 10 + map[nr][nc], depth + 1);
		}

	}

}
