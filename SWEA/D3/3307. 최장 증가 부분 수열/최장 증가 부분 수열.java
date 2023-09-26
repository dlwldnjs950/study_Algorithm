import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= tc; testCase++) {
			sb.append("#").append(testCase).append(" ");

			int length = Integer.parseInt(br.readLine().trim());

			int numbers[] = new int[length + 1];

			st = new StringTokenizer(br.readLine().trim());
			for (int idx = 1; idx <= length; idx++) {
				numbers[idx] = Integer.parseInt(st.nextToken());
			}

			int max = Integer.MIN_VALUE;
			int LIS[] = new int[length + 1];
			for (int num = 1; num <= length; num++) {
				LIS[num] = 1;
				for (int loop = 1; loop < num; loop++) {
					if (numbers[loop] < numbers[num] && LIS[num] < LIS[loop] + 1) {
						LIS[num] = LIS[loop] + 1;
					}
				}
				max = Math.max(max, LIS[num]);
			}

			sb.append(max).append("\n");
		}
		
		System.out.println(sb);
	}

}
