import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Some {
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine().trim());
		
		for (int loop = 0; loop < tc; loop++) {
			st = new StringTokenizer(br.readLine().trim());
			int start = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());

			int dist = target - start;
			int ans = 0;
			int max = (int) Math.sqrt(dist);

			//sb.append(target);
			// 정수와 실수도 소수점 값까지 포함해서 비교 가능...
			if (Math.sqrt(dist) == max) {
				//sb.append(" case1 : ");
				ans = 2 * max - 1;
				
			// 1~max 까지 정수의 합...이 도착 직전 지점까지 여야함
			} else if (dist < max * (max + 1) + 1) {
				//sb.append(" case2 : ");
				ans = 2 * max;
			} else {
				//sb.append(" case3 : ");
				ans = 2 * max + 1;
			}

			sb.append(ans).append("\n");
		}

		System.out.println(sb);

	}

}
