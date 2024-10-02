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

	static int mapSize;
	static char maps[][];
	static boolean success;
	static List<Point> teachers;
	static final char TEACHER = 'T', STUDENT = 'S', OBSTACLE = 'O', EMPTY = 'X';

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		mapSize = Integer.parseInt(br.readLine().trim());
		maps = new char[mapSize][mapSize];

		teachers = new ArrayList<>();

		for (int rIdx = 0; rIdx < mapSize; rIdx++) {
			String[] states = br.readLine().trim().split(" ");

			for (int cIdx = 0; cIdx < mapSize; cIdx++) {
				maps[rIdx][cIdx] = states[cIdx].charAt(0);

				if (maps[rIdx][cIdx] == TEACHER) {
					teachers.add(new Point(rIdx, cIdx));
				}
			}
		}

		// 장애물 놓고 선생님들이 각 학생 감시 현황
		success = false;
		placeObstacles(0);

		System.out.println(success ? "YES" : "NO");

	}

	static void placeObstacles(int cnt) {

		if (cnt == 3) {
			// 감시 상황 확인
			if (check())
				success = true;

			return;
		}

		for (int position = 0; position < mapSize * mapSize; position++) {
			int row = position / mapSize;
			int col = position % mapSize;

			if (maps[row][col] == EMPTY) {
				maps[row][col] = OBSTACLE;
				placeObstacles(cnt + 1);
				maps[row][col] = EMPTY;
				if (success)
					return;
			}
		}
	}

	// 감시 현황 확인
	private static boolean check() {

		int[] dr = { 0, -1, 0, 1 }, dc = { 1, 0, -1, 0 };
		for (Point teacher : teachers) {
			for (int direction = 0; direction < 4; direction++) {
				if (detect(teacher, new Point(dr[direction], dc[direction])))
					return false;
			}
		}

		return true;
	}

	// 해당 방향 상황 - 학생을 봤는지 안봤는지
	private static boolean detect(Point teacher, Point direction) {
		int nr = teacher.row;
		int nc = teacher.col;

		while (true) {
			nr += direction.row;
			nc += direction.col;

			if (nr < 0 || nc < 0 || nr >= mapSize || nc >= mapSize)
				return false;
			if (maps[nr][nc] == STUDENT)
				return true;
			if (maps[nr][nc] == OBSTACLE)
				return false;

		}
	}

}
