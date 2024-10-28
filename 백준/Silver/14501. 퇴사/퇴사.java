import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Counsel {
		int duration, income;

		Counsel(int duration, int income) {
			this.duration = duration;
			this.income = income;
		}
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int num = Integer.parseInt(br.readLine().trim());
		Counsel[] counsels = new Counsel[num];
		for (int idx = 0; idx < num; idx++) {
			st = new StringTokenizer(br.readLine().trim());

			int duration = Integer.parseInt(st.nextToken());
			int income = Integer.parseInt(st.nextToken());

			counsels[idx] = new Counsel(duration, income);
		}

		int dp[] = new int[num + 1];

		for (int day = num - 1; day >= 0; day--) {
			// 지금 상담 선택 가능 여부
			int next = day + counsels[day].duration;

			// 상담이 불가능 = 선택 X
			if (next > num) {
				dp[day] = dp[day + 1];
				
			// 상담 선택 가능
			} else {
				// 현재 상담 선택 안함 VS 현재 상담 선택
				dp[day] = Math.max(dp[day + 1], counsels[day].income + dp[next]);
			}
		}

		System.out.println(dp[0]);

	}

}
