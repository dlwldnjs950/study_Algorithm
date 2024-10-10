import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int num, numbers[], operLimits[], answer[];

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 숫자 개수
		num = Integer.parseInt(br.readLine().trim());

		// 숫자 목록
		numbers = new int[num];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < num; idx++) {
			numbers[idx] = Integer.parseInt(st.nextToken());
		}

		// 연산자 개수 제한(+, -, x, /)
		operLimits = new int[4];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < 4; idx++) {
			operLimits[idx] = Integer.parseInt(st.nextToken());
		}

		answer = new int[2];
		answer[0] = Integer.MAX_VALUE;// 최소값
		answer[1] = Integer.MIN_VALUE;// 최대값
		makeResult(1, numbers[0]);

		sb.append(answer[1]).append("\n").append(answer[0]);
		
		System.out.println(sb);

	}

	static void makeResult(int cnt, int midResult) {

		if (cnt == num) {
			answer[0] = Math.min(answer[0], midResult);
			answer[1] = Math.max(answer[1], midResult);
		}

		for (int oper = 0; oper < 4; oper++) {
			if (operLimits[oper] < 1)
				continue;
			operLimits[oper] -= 1;

			switch (oper) {
			case 0:
				makeResult(cnt + 1, midResult + numbers[cnt]);
				break;
			case 1:
				makeResult(cnt + 1, midResult - numbers[cnt]);
				break;
			case 2:
				makeResult(cnt + 1, midResult * numbers[cnt]);
				break;
			case 3:
				makeResult(cnt + 1, midResult / numbers[cnt]);
				break;
			}

			operLimits[oper] += 1;
		}
	}

}
