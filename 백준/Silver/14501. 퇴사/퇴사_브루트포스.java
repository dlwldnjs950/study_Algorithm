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

	static int maxIncome;

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

		maxIncome = -1;

		makeResult(0, 0, counsels, num);

		System.out.println(maxIncome);

	}

	private static void makeResult(int day, int midIncome, Counsel[] counsels, int num) {

		if (day >= num) {
			maxIncome = Math.max(midIncome, maxIncome);
			return;
		}

		// 선택
		if (day + counsels[day].duration <= num)
			makeResult(day + counsels[day].duration, midIncome + counsels[day].income, counsels, num);
		// 선택 안함
		makeResult(day + 1, midIncome, counsels, num);

	}

}
