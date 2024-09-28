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

		int glassesNum = Integer.parseInt(br.readLine().trim());
		int[] glasses = new int[glassesNum];

		for (int idx = 0; idx < glassesNum; idx++) {
			glasses[idx] = Integer.parseInt(br.readLine().trim());
		}

		int[][] dp = new int[glassesNum][2];

		dp[0][1] = glasses[0];
		if (glassesNum > 1) {
			dp[1][0] = dp[0][1];
			dp[1][1] = glasses[0] + glasses[1];
		}

		for (int idx = 2; idx < glassesNum; idx++) {
			// 안마심
			// 그전꺼중에 최대
			dp[idx][0] = Math.max(dp[idx - 1][0], dp[idx - 1][1]);

			// 마심
			// -2인거 마심/안마심 + 지금꺼마심
			int prevMax = Math.max(dp[idx - 2][0], dp[idx - 2][1]);
			// -2인거 안마심 + 그전꺼마심 + 지금꺼마심
			prevMax = Math.max(prevMax, dp[idx - 2][0] + glasses[idx - 1]);
			dp[idx][1] = prevMax + glasses[idx];

		}

		System.out.println(Math.max(dp[glassesNum - 1][0], dp[glassesNum - 1][1]));

	}

}
