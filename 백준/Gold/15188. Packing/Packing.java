import java.util.*;
import java.io.*;

public class Main {
	
	static class Present{
		int weight = 0;
		int value = 0;
		
		void setWeight(int weight){
			this.weight = weight;
		}
		
		void setValue(int value){
			this.value = value;
		}
	}
    
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        
    	sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine().trim());
		
		for(int testCase = 1; testCase <= tc; testCase++) {
			
			st = new StringTokenizer(br.readLine().trim());
			int pNum = Integer.parseInt(st.nextToken());	// 선물 개수
			int wLimit1 = Integer.parseInt(st.nextToken());	// 드론1 무게 제한
			int wLimit2 = Integer.parseInt(st.nextToken());	// 드론2 무게 제한
			
			Present[] presents = new Present[pNum];
			// 무게
			st = new StringTokenizer(br.readLine().trim());
			for(int idx=0; idx<pNum; idx++) {
				presents[idx] = new Present();
				presents[idx].setWeight(Integer.parseInt(st.nextToken()));
			}
			// 가치
			st = new StringTokenizer(br.readLine().trim());
			for(int idx=0; idx<pNum; idx++) {
				presents[idx].setValue(Integer.parseInt(st.nextToken()));
			}
			
			int[][][] dp = new int[pNum +1][wLimit1 +1][wLimit2 +1];
			for(int idx = 1; idx<= pNum; idx++) {
				for(int weight1 = 0; weight1<= wLimit1; weight1++) {
					for(int weight2 = 0; weight2<= wLimit2; weight2++) {

						int pWeight = presents[idx - 1].weight;
						int pValue = presents[idx - 1].value;

						// 두 드론 모두에 넣을 수 없음
						if (pWeight > weight1 && pWeight > weight2) {
							dp[idx][weight1][weight2] = dp[idx - 1][weight1][weight2];

						// 두 드론 모두에 넣을 수 있음
						} else if (pWeight <= weight1 && pWeight <= weight2) {
							int tmp = Math.max(dp[idx - 1][weight1 - pWeight][weight2],
									dp[idx - 1][weight1][weight2 - pWeight]);
							dp[idx][weight1][weight2] = Math.max(tmp + pValue, dp[idx - 1][weight1][weight2]);

						// 1번 드론에만 넣을 수 있음
						} else if (pWeight <= weight1) {
							dp[idx][weight1][weight2] = Math.max(dp[idx - 1][weight1 - pWeight][weight2] + pValue,
									dp[idx - 1][weight1][weight2]);

						// 2번 드론에만 넣을 수 있음
						} else {
							dp[idx][weight1][weight2] = Math.max(dp[idx - 1][weight1][weight2 - pWeight] + pValue,
									dp[idx - 1][weight1][weight2]);
						}
					}
				}
			}
			
			sb.append("Problem ").append(testCase).append(": ").append(dp[pNum][wLimit1][wLimit2]).append("\n");
		}
        
		System.out.println(sb.toString());
        
    }
}