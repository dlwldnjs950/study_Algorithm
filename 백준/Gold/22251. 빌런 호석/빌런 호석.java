import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringTokenizer st;

	static int[][] ledChanges = {
	        {0, 4, 3, 3, 4, 3, 2, 3, 1, 2},
	        {4, 0, 5, 3, 2, 5, 6, 1, 5, 4},
	        {3, 5, 0, 2, 5, 4, 3, 4, 2, 3},
	        {3, 3, 2, 0, 3, 2, 3, 2, 2, 1},
	        {4, 2, 5, 3, 0, 3, 4, 3, 3, 2},
	        {3, 5, 4, 2, 3, 0, 1, 4, 2, 1},
	        {2, 6, 3, 3, 4, 1, 0, 5, 1, 2},
	        {3, 1, 4, 2, 3, 4, 5, 0, 4, 3},
	        {1, 5, 2, 2, 3, 2, 1, 4, 0, 1},
	        {2, 4, 3, 1, 2, 1, 2, 3, 1, 0}
	    };

		static int answer, limitFloor, limitDigits, limitChange;
		static String nowFloor;

		public static void main(String[] args) throws IOException {

			br = new BufferedReader(new InputStreamReader(System.in));

			st = new StringTokenizer(br.readLine().trim());
			limitFloor = Integer.parseInt(st.nextToken());
			limitDigits = Integer.parseInt(st.nextToken());
			limitChange = Integer.parseInt(st.nextToken());
			// 현재 층이 기준 자릿수 보다 짧을 수 있음 (0으로 채움)
			nowFloor = String.format("%0" + limitDigits + "d", Integer.parseInt(st.nextToken()));

			answer = 0;
			findCases("", 0, 0);

			System.out.println(answer);

		}

		private static void findCases(String currentNumber, int position, int changeCnt) {

			if (position == limitDigits) {
				int floorNum = Integer.parseInt(currentNumber);
				// 1층부터
				// 부분 값에 대해 판단 X 생성된 층이 최고층보다 높을 때
				// 변경된게 없을 때 (동일 층)
				// 기준 반전 횟수보다 클 때,
				if (floorNum >= 1 && floorNum <= limitFloor && changeCnt > 0 && changeCnt <= limitChange) {
					answer++;
				}
				return;
			}

			int originNumber = nowFloor.charAt(position) - '0';
			for (int number = 0; number < 10; number++) {
				findCases(currentNumber + number, position + 1, changeCnt + ledChanges[originNumber][number]);
			}

		}

	}
