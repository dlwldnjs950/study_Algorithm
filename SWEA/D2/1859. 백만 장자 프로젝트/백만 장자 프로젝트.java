import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * # 1859 백만 장자 프로젝트
	 * 
	 * 	사재기하려는 원재
	 * 	
	 * # 사재기 조건
	 * 	1. 연속된 N일 동안의 물건 매매가를 알고 있다
	 * 	2. 하루 최대 1개 구매 가능 ( 돈 - )
	 * 	3. 판매는 얼마든지 가능 ( 돈 + )
	 * 
	 * 	최대한의 이익을 얻도록 해보자
	 * 
	 * 	사거나 안사거나 팔거나
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			// 기간 N
			int N = Integer.parseInt(br.readLine().trim());
			
			// 각 날짜의 매매가 한줄 입력 (공백 구분)
			st = new StringTokenizer(br.readLine().trim());
			int[] howMuch = new int[N];
			for(int idx=0; idx<N; idx++) {
				howMuch[idx] = Integer.parseInt(st.nextToken());
			}
			
			// 현재의 income의 절댓값이 최대 매매가 보다 작을 때 구매
			// 최대 매매가인날 판매
			// 판매하고 나면 그 다음으로 작은 최대 매매가...
			// 거꾸로 확인...이구나...
			long max = Long.MIN_VALUE;
			int num = 0;	// 현재 가진 물건 개수
			long cost = 0L;		//구매가
			long result = 0L; 	//최종
			for (int i = N-1 ;  i >= 0 ; i--) {
				if( howMuch[i] > max ) {
					result += (max*num - cost);
					max = howMuch[i];
					//초기화
					cost = 0;		
					num = 0;
				}else {	//max 보다 작으면 구매
					cost+=howMuch[i];
					num++;
				}
			}
			
			result += (max*num - cost);
			
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}

}
