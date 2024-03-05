import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/*
	 * N개의 물건
	 * 각 물건은 무게 W 가치 V
	 * 최대 K만큼 들 수 있다.
	 * 
	 * 물건 가치의 최대값?
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		int thingsCnt = Integer.parseInt(st.nextToken());
		int backLimit = Integer.parseInt(st.nextToken());

		int[] weights = new int[thingsCnt + 1];
		int[] values = new int[thingsCnt + 1];

		for (int idx = 1; idx <= thingsCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			weights[idx] = Integer.parseInt(st.nextToken());
			values[idx] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[thingsCnt + 1][backLimit + 1];
		
		//dp 채우기
		for(int thingIdx=1; thingIdx<=thingsCnt; thingIdx++) {
			for(int weight=1; weight<=backLimit; weight++) {
				if(weights[thingIdx] > weight) {
					dp[thingIdx][weight] = dp[thingIdx - 1][weight];
				} else {
					dp[thingIdx][weight] = Math.max(dp[thingIdx - 1][weight],
							dp[thingIdx-1][weight - weights[thingIdx]] + values[thingIdx]);
				}
			}
		}
		
		sb.append(dp[thingsCnt][backLimit]);
		
		
		System.out.println(sb);
	}

}
