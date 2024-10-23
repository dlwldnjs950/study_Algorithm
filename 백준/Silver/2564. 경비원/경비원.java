import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Position {
		int direction, row, col;

		Position(int direction, int row, int col) {
			this.direction = direction;
			this.row = row;
			this.col = col;
		}
	}

	static int width, height;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());

		int num = Integer.parseInt(br.readLine().trim());
		Position[] stores = new Position[num + 1];
		for (int idx = 0; idx <= num; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int direction = Integer.parseInt(st.nextToken());
			int position = Integer.parseInt(st.nextToken());

			stores[idx] = translateRowCol(direction, position);
		}

		int answer = 0;

		for (int idx = 0; idx < num; idx++) {

			int distance = calculate(stores[idx], stores[num]);
			// System.out.println(distance);
			answer += distance;
		}

		System.out.println(answer);

	}

	static int calculate(Position store, Position donggeun) {

		int distance = Math.abs(store.row - donggeun.row) + Math.abs(store.col - donggeun.col);

		int extraDistance = 0;

		if (donggeun.direction + store.direction == 3) {
			int min = Math.min(donggeun.row, store.row);
			int max = Math.max(donggeun.row, store.row);

			extraDistance = Math.min(min, width - max);

		} else if (donggeun.direction + store.direction == 7) {
			int min = Math.min(donggeun.col, store.col);
			int max = Math.max(donggeun.col, store.col);

			extraDistance = Math.min(min, height - max);
		}

		// System.out.println(">> " + extraDistance);
		distance += extraDistance * 2;

		return distance;
	}

	static Position translateRowCol(int direction, int position) {

		int row = -1;
		int col = -1;

		switch (direction) {
		case 1: // 북
			row = position;
			col = 0;
			break;
		case 2: // 남
			row = position;
			col = height;
			break;
		case 3: // 서
			row = 0;
			col = position;
			break;
		case 4:// 동
			row = width;
			col = position;
			break;
		}

		return new Position(direction, row, col);
	}

}
