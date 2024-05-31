import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 아래 - 위 - 아래 - 위 - ...
	 * 길이 N 높이 H
	 * 개똥친구는 장애물 피하지 않고 파괴
	 * 개똥친구가 파괴하는 장애물의 최솟값과 그 구간이 몇개인지 구하라
	 * */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Some {
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		int caveLen = Integer.parseInt(st.nextToken());
		int caveHei = Integer.parseInt(st.nextToken());

		int[] bottoms = new int[caveHei];
		int[] tops = new int[caveHei];
		
		for (int idx = 0; idx < caveLen; idx++) {
			int rockLen = Integer.parseInt(br.readLine().trim());

			// idx가 짝수면 아래 장애뭏
			if (idx % 2 == 0)
				bottoms[rockLen -1]++;
			// idx가 홀수면 위 장애물 
			if (idx % 2 == 1)
				tops[rockLen -1]++;
		}
		
//		System.out.println(Arrays.toString(bottoms));
//		System.out.println(Arrays.toString(tops));
		
		// 장애물 개수 누적
		for (int idx = caveHei-1; idx > 0; idx--) {
			bottoms[idx-1] += bottoms[idx];
			tops[idx-1] += tops[idx];
		}
		
//		System.out.println(Arrays.toString(bottoms));
//		System.out.println(Arrays.toString(tops));
		
		int cnt = 0;
		int min = Integer.MAX_VALUE;
		for (int idx = 0; idx < caveHei; idx++) {
			// 해당 구간의 장애물 개수
			int tmp = bottoms[idx] + tops[caveHei -1 - idx];
			if(tmp < min) {
				cnt=1;
				min = tmp;
			}else if(tmp == min) {
				cnt++;
			}
		}

		sb.append(min).append(" ").append(cnt);
		
		System.out.println(sb);

	}

}
