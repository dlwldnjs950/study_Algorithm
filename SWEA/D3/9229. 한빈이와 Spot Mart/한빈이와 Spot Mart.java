import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 9229_한빈이와 Spot Mart
	 * 
	 * 	[설명]
	 * 과자 두봉지를 양손에 하나씩 들고 가려고 한다
	 * N개의 과자봉지
	 * 각 과자 봉지의 무게 ai그램
	 * 과자봉지 M그램 이하만 들 수 있음
	 * 
	 * 정 확 히 두봉지만 구매 가능
	 * 
	 * 	[입력]
	 * ===> 테스트케이스 개수
	 * ===> 과자봉지 개수와 무개 합 제한
	 * ===> 각 과자봉지 무게
	 * 
	 * 	[출력]
	 * 과자 봉지 무게 합 최대
	 * (들고갈 방법이 없으면 -1)
	 * 
	 * 	[풀이방법]
	 * 과자 봉지 무게 합이 M그램 이하면서 최대인 값!
	 * 1. 과자 2개를 고르는 조합을 구한다
	 * 2. 고른 과자의 무게합을 구한다
	 * 	2-1. 기준 값 M 이하인지 확인한다
	 * 	2-2. 기준값 이하면 최대값을 갱신한다
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int snackCnt, limitWeight, snackWeight[], maxWeight;
	static final int LIMIT_SNACK = 2;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 과자개수와 제한 무게 입력받기
			st = new StringTokenizer(br.readLine().trim());
			snackCnt = Integer.parseInt(st.nextToken());
			limitWeight = Integer.parseInt(st.nextToken());
			
			// 각 과자의 무게
			st = new StringTokenizer(br.readLine().trim());
			snackWeight = new int[snackCnt];
			for (int sIdx = 0; sIdx < snackCnt; sIdx++) {
				snackWeight[sIdx] = Integer.parseInt(st.nextToken());
			}

			// 과자 선택 조합 구하기
			//chosenSnack = new int[LIMIT_SNACK];
			maxWeight = Integer.MIN_VALUE;
			comb(0, 0, 0);
			sb.append(maxWeight==Integer.MIN_VALUE?-1:maxWeight).append("\n");
		}

		System.out.println(sb);
	}
	
	// 선택된 과자 리스트가 필요하지 않다
	// 선택된 과자 각각이 필요한 것이 아니라 무게 합만 필요하므로 
	// 매개변수로 무게 합을 넘겨 준다

	// 과자 선택 조합 구하는 함수
	private static void comb(int cnt, int start, int sum) {
		// 기저 조건
		if(cnt ==LIMIT_SNACK) {	// 제한 개수만큼 선택했으면
			// 제한 무게 이하인지 확인
			if(sum<=limitWeight) {
				// 제한 무게 이하면 최대 무게 갱신
				maxWeight = Math.max(maxWeight, sum);
			}
			return;
		}
		// 유도 부분
		for(int sIdx = start; sIdx<snackCnt; sIdx++) {
			comb(cnt+1, sIdx + 1, sum + snackWeight[sIdx]);
		}
	}
}
