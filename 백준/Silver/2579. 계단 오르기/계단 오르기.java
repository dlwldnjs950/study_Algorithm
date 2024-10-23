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

		int num = Integer.parseInt(br.readLine().trim());
		int stairs[] = new int[num + 1];
		for (int idx = 1; idx <= num; idx++) {
			stairs[idx] = Integer.parseInt(br.readLine().trim());
		}

		int[] dp = new int[num + 1];
		// Base cases
		dp[1] = stairs[1];
		if (num >= 2) {
			dp[2] = stairs[1] + stairs[2];
		}

		// Fill the dp array using the recurrence relation
		for (int i = 3; i <= num; i++) {
			dp[i] = Math.max(dp[i - 2], dp[i - 3] + stairs[i - 1]) + stairs[i];
		}

		System.out.println(dp[num]);

	}

}
