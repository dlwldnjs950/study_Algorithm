import java.util.*;
import java.io.*;

public class Main {
    
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        
    	sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine().trim());
		
		for (int testCase = 1; testCase <= tc; testCase++) {

			int cNum = Integer.parseInt(br.readLine().trim());
			
			int[] coins = new int[cNum];
			st = new StringTokenizer(br.readLine().trim());
			for (int idx = 0; idx < cNum; idx++) {
				coins[idx] = Integer.parseInt(st.nextToken());
			}

			int target = Integer.parseInt(br.readLine().trim());

			int[] dp = new int[target + 1];
			dp[0] = 1;

			for (int coin : coins) {
				for (int amount = coin; amount <= target; amount++) {
					dp[amount] += dp[amount - coin];
				}
			}

			sb.append(dp[target]).append("\n");
		}

		System.out.println(sb.toString());
        
    }
}