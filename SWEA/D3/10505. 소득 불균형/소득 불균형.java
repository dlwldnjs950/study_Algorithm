import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 10505 소득 불균형
	 * 
	 * 	[설명]
	 * n명의 사람의 소득이 주어졌을 때,
	 * 이 중 평균 이하의 소득을 가진 사람 수를 출력
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 개수 T
	 * ===> 사람 수 N
	 * ===> 각 사람의 소득(한 줄 입력, 공백 구분)
	 * 
	 * 	[출력]
	 * 평균 이하의 소득을 가진 사람 수
	 * 
	 * 	[풀이방법]
	 * 소득을 전부 더하고 평균을 구해서
	 * 각 소득이 평균 이하인지 봐야하므로 값을 저장해야한다.
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
			
			int N = Integer.parseInt(br.readLine().trim());
			int[] income = new int[N];
			
			// 소득 입력
			st = new StringTokenizer(br.readLine().trim());
			int sum = 0;	// 합계
			for(int idx = 0; idx<N; idx++) {
				income[idx] = Integer.parseInt(st.nextToken());
				sum+=income[idx];
			}
			double avg = (double) sum / N;
			
			int lowerCnt = 0;
			for(int i : income) {
				if(i<=avg)
					lowerCnt++;
			}
			sb.append(lowerCnt).append("\n");
		}
		System.out.println(sb);
	}

}
