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

		// n이 11까지니까 미리 구해두고
		int dp[] = new int[12];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for (int number = 4; number < 12; number++) {
			dp[number] = dp[number - 3] + dp[number - 2] + dp[number - 1];
		}

		int tc = Integer.parseInt(br.readLine().trim());
		for (int testCase = 0; testCase < tc; testCase++) {
			int number = Integer.parseInt(br.readLine().trim());
			sb.append(dp[number]).append("\n");
		}

		System.out.println(sb);

	}

}
