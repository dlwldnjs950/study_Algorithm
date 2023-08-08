import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class  Solution {

	/**
	 * 	# 9229 한빈이와 Spot Mart
	 * 
	 * 과자 2봉지 구매 예정
	 * N개의 과자 봉지
	 * 각 과자봉지 무게 ai 그램
	 * M 그램 이하만 들 수 있음
	 * 과자들의 최대 무게 합?
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스 수 TC
		int TC = Integer.parseInt(br.readLine().trim());
		
		for(int testCase = 1; testCase<= TC; testCase++) {
			sb.append("#").append(testCase).append(" ");
			// 봉지 개수 N, 무게 제한 M (공백 구분)
			st = new StringTokenizer(br.readLine().trim());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			//과자 봉지 무게
			int[] snackWeight = new int[N];
			st = new StringTokenizer(br.readLine().trim());
			for(int idx=0; idx<N; idx++) {
				snackWeight[idx] = Integer.parseInt(st.nextToken());
			}
			
			// 한빈이가 고를 수 있는 봉지의 조합 구하기
			// 2가지 골라서 M보다 작거나 같을 때, max합과 비교 및 저장
			// 2가지 고르는 방법 : 반복문, 재귀
			int maxWeight = -1;
			for (int firstIdx = 0; firstIdx < N; firstIdx++) {
				for (int secondIdx = firstIdx + 1; secondIdx < N; secondIdx++) {
					int tmpWeight = snackWeight[firstIdx] + snackWeight[secondIdx];
					if (tmpWeight <= M) {
						if (tmpWeight > maxWeight)
							maxWeight = tmpWeight;
					}
				}
			}
			sb.append(maxWeight).append("\n");
		}
		System.out.println(sb);
	}

}
