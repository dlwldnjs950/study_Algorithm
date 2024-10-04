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
		int numbers[] = new int[num]; // 정답 순서
		String biggerCnt[] = br.readLine().trim().split(" ");

		int number = num;
		for (int idx = num - 1; idx >= 0; idx--) {
			int cnt = biggerCnt[number - 1].charAt(0) - '0';
			int moveIdx = idx;

			if (cnt != 0) {
				// 더 큰 수가 있는 만큼 이동시켜 대입한다

				while (cnt-- > 0) {
					numbers[moveIdx] = numbers[moveIdx + 1];
					moveIdx++;
				}
			}
			numbers[moveIdx] = number--;
		}

		for (int n : numbers) {
			sb.append(n).append(" ");
		}
		System.out.println(sb);

	}

}
