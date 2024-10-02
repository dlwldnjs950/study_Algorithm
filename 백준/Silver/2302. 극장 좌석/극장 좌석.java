import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int seatNum = Integer.parseInt(br.readLine().trim());
		int fixedNum = Integer.parseInt(br.readLine().trim());

		int dp[] = new int[seatNum + 1];
		dp[0] = 1;
		dp[1] = 1;
		// 알고보니 피보나치...
		// 맨 끝자리에 새로운 숫자 추가하는거 (-1)
		// 맨 끝자리 자리 바꿀 수 있는 경우 = 전전자리에 새로운 숫자 추가하는 경우 (-2)
		for (int idx = 2; idx <= seatNum; idx++) {
			dp[idx] = dp[idx - 2] + dp[idx - 1];
		}

		if (fixedNum == 0) {
			System.out.println(dp[seatNum]);
			return;
		}

		// 좌석 구간 길이 정보

		int answer = 1;
		int prevSeat = 0;
		for (int idx = 0; idx < fixedNum; idx++) {
			int seat = Integer.parseInt(br.readLine().trim());

			int length = seat - prevSeat - 1;

			answer *= dp[length];
			prevSeat = seat;
		}

		if (prevSeat < seatNum) {
			int length = seatNum - prevSeat;

			answer *= dp[length];
		}

		System.out.println(answer);

	}

}
