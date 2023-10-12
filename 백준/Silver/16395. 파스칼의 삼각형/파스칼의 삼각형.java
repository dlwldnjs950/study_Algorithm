import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		int range = Integer.parseInt(st.nextToken());
		int num = Integer.parseInt(st.nextToken());

		int[][] midResult = new int[2][range+1];

		midResult[1][1] = 1;
		// loop : 1부터 시작하는 n번째 줄을 의미
		for (int loop = 2; loop <= range; loop++) {
			int targetIdx = loop % 2;
			for (int idx = 1; idx <= loop; idx++) {
				// 결과 저장 행 : 홀수면 1, 짝수면 0 = loop%2
				// 값 가져올 행 : 홀수면 0, 짝수면 1 = (loop%2)^1
				midResult[targetIdx][idx] = midResult[targetIdx ^ 1][idx - 1] + midResult[targetIdx ^ 1][idx];
			}
		}
		sb.append(midResult[range%2][num]);
		System.out.println(sb);
	}
}
