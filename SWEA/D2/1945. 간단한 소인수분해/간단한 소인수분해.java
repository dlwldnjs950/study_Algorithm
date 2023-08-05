import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	/**
	 * # 1945 간단한 소인수 분해
	 * 
	 * 숫자 N은 N=2^a x 3^b x 5^c x 7^d x 11^e a ~ e를 구하여라
	 * 
	 */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int[] division = { 2, 3, 5, 7, 11 };

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 개수 저장 배열
			int[] cnt = new int[division.length];
			int num = Integer.parseInt(br.readLine().trim());

			int dIdx = 0;
			while (num > 1) {
				if (num % division[dIdx] == 0) {
					cnt[dIdx]++;
					num /= division[dIdx];
				} else {
					dIdx++;
				}
			}
			for (int d : cnt) {
				sb.append(d).append(" ");
			}
			sb.append("\n");

		}
		System.out.println(sb);
	}

}
