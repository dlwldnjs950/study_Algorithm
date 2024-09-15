import java.io.*;
import java.util.*;

public class Main {

	/* 안고르기/1행고르기/2행고르기 */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine().trim());
		for (int testCase = 0; testCase < tc; testCase++) {

			int width = Integer.parseInt(br.readLine().trim());

			int[][] stickers = new int[2][width];

			// 스티커 정보
			for (int rIdx = 0; rIdx < 2; rIdx++) {
				st = new StringTokenizer(br.readLine().trim());

				for (int cIdx = 0; cIdx < width; cIdx++) {
					stickers[rIdx][cIdx] = Integer.parseInt(st.nextToken());
				}
			}

			// 예상 결과 계산
			int[][] dp = new int[3][width]; // 0: 안고름, 1: 1행고름, 2: 2행고름
			dp[0][0] = 0;
			dp[1][0] = stickers[0][0];
			dp[2][0] = stickers[1][0];

			for (int cIdx = 1; cIdx < width; cIdx++) {
				dp[0][cIdx] = Math.max(dp[1][cIdx - 1], dp[2][cIdx - 1]);
				dp[1][cIdx] = Math.max(dp[0][cIdx - 1], dp[2][cIdx - 1]) + stickers[0][cIdx];
				dp[2][cIdx] = Math.max(dp[0][cIdx - 1], dp[1][cIdx - 1]) + stickers[1][cIdx];
			}

			sb.append(Math.max(Math.max(dp[0][width - 1], dp[1][width - 1]), dp[2][width - 1])).append("\n");
		}

		System.out.println(sb);

	}

}
