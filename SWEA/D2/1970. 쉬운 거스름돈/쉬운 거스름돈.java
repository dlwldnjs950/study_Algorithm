import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * # 1970 쉬운 거스름돈
	 * 	금액이 높은 돈을 우선적으로 계산해 거스름돈 개수를 계산하자
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	static final int[] MONEY = {50000, 10000, 5000, 1000, 500, 100, 50, 10}; 

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		int[] mCount = new int[MONEY.length];
		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append("\n");
			int payMoney = Integer.parseInt(br.readLine().trim());
			
			for (int mIdx = 0; mIdx < MONEY.length; mIdx++) {
				mCount[mIdx] = payMoney / MONEY[mIdx];
				payMoney %= MONEY[mIdx];
			}
			
			for(int m: mCount) {
				sb.append(m).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
