import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static boolean[][] erased;
	static final int SIZE = 5, BINGO = 3;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 빙고판
		// 해당 숫자가 빙고판 어디에 있는지 저장
		int[] numberRC = new int[SIZE * SIZE + 1];
		for (int rowIdx = 0; rowIdx < SIZE; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());

			for (int colIdx = 0; colIdx < SIZE; colIdx++) {
				int number = Integer.parseInt(st.nextToken());

				numberRC[number] = SIZE * rowIdx + colIdx;
			}
		}

		// 사회자 숫자 부르는 순서
		int[] numOrder = new int[SIZE * SIZE];
		for (int rowIdx = 0; rowIdx < SIZE; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());

			for (int colIdx = 0; colIdx < SIZE; colIdx++) {
				numOrder[SIZE * rowIdx + colIdx] = Integer.parseInt(st.nextToken());
			}
		}

		// 숫자 확인
		erased = new boolean[SIZE][SIZE];
		for (int idx = 0; idx < SIZE * SIZE; idx++) {
			int number = numOrder[idx];
			int row = numberRC[number] / SIZE;
			int col = numberRC[number] % SIZE;

			erased[row][col] = true;

			if (completeBingo()) {
				sb.append(idx + 1);
				break;
			}
		}

		System.out.println(sb);

	}

	private static boolean completeBingo() {
		int cnt = 0;

		// 가로 빙고 개수
		cnt += horizon();

		// 세로 빙고 개수
		cnt += vertical();

		// 대각선 /
		cnt += bottomToTop();

		// 대각선 \
		cnt += topToBottom();

		return cnt >= BINGO;
	}

	private static int topToBottom() {
		if (erased[0][0] && erased[1][1] && erased[2][2] && erased[3][3] && erased[4][4])
			return 1;
		else
			return 0;
	}

	private static int bottomToTop() {
		if (erased[0][4] && erased[1][3] && erased[2][2] && erased[3][1] && erased[4][0])
			return 1;
		else
			return 0;
	}

	private static int vertical() {
		int cnt = 0;

		for (int col = 0; col < SIZE; col++) {
			if (erased[0][col] && erased[1][col] && erased[2][col] && erased[3][col] && erased[4][col])
				cnt++;
		}
		return cnt;
	}

	private static int horizon() {
		int cnt = 0;

		for (int row = 0; row < SIZE; row++) {
			if (erased[row][0] && erased[row][1] && erased[row][2] && erased[row][3] && erased[row][4])
				cnt++;
		}
		return cnt;
	}

}
