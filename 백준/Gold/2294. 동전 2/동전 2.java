import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		int coinCnt = Integer.parseInt(st.nextToken());

		int targetAmount = Integer.parseInt(st.nextToken());
		int[] dp = new int[targetAmount + 1];
		Arrays.fill(dp, Integer.MAX_VALUE -1);
		dp[0] = 0;

		// 동전 정보
		int[] coins = new int[coinCnt];
		for (int idx = 0; idx < coinCnt; idx++) {
			coins[idx] = Integer.parseInt(br.readLine().trim());
		}

		for (int i = 0; i < coinCnt; i++) {
			for (int j = coins[i]; j <= targetAmount; j++) {
				dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
			}
		}

		if(dp[targetAmount]==Integer.MAX_VALUE -1)
            System.out.println(-1);
        else
            System.out.println(dp[targetAmount]);

	}

}
