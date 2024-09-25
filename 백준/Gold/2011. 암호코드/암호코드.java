import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));

		String code = br.readLine().trim();
		int length = code.length();

		int[] dp = new int[length + 1]; // 인덱스값 = 문자열 길이

		dp[0] = 1;
		dp[1] = code.charAt(0) == '0' ? 0 : 1;

		for (int idx = 2; idx <= length; idx++) {
			int length_1 = Integer.parseInt(code.substring(idx - 1, idx));
			int length_2 = Integer.parseInt(code.substring(idx - 2, idx));

			if (length_1 >= 1 && length_1 <= 9) {
				dp[idx] += dp[idx - 1];
			}
			if (length_2 >= 10 && length_2 <= 26) {
				dp[idx] += dp[idx - 2];
			}
			
			// 숫자 보정
			dp[idx] %=1000000;
		}

		System.out.println(dp[length]);

	}

}
