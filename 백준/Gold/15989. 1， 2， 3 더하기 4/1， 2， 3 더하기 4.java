import java.io.*;
import java.util.*;

public class Main {

	/*
	 * DP이자 조합!
	 * */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 10000까지 DP 값 미리 계산
		int[][] dp = new int[100000 + 1][3 + 1];
		
		// 초기값
		dp[1][1] = 1;	// 1
		dp[2][2] = 1;	// 2		
		dp[2][1] = 1;	// 1+1
		dp[3][3] = 1;	// 3
		dp[3][2] = 1;	// 1+2
		dp[3][1] = 1;	// 1+1+1
		
		for(int number = 4; number <= 10000; number++) {
			// 1로 끝나는 경우 - 조합의 중복 제거를 위한 1까지만 사용
			dp[number][1] = dp[number -1][1];
			
			// 2로 끝나는 경우 - 조합의 중복 제거를 위한 1,2까지만 사용. 1로 끝나는 경우의 2로 끝나는 경우 포함
			dp[number][2] = dp[number -2][1] + dp[number -2][2];
			
			// 3으로 끝나는 경우 - 1과 2로 끝나는 경우의 3으로 끝나는 경우를 포함
			dp[number][3] = dp[number -3][1] + dp[number -3][2] + dp[number -3][3];
		}
		
		int tc = Integer.parseInt(br.readLine().trim());
		
		for (int testCase = 0; testCase < tc; testCase++) {
			
			int targetNum = Integer.parseInt(br.readLine().trim());
			
			int answer = dp[targetNum][1] + dp[targetNum][2] + dp[targetNum][3];
			sb.append(answer).append("\n");
		}

		System.out.println(sb);

	}

}
