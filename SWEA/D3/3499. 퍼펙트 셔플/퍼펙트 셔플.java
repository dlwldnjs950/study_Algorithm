import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 3499_퍼펙트셔플
	 * 
	 * 	[설명]
	 * 카드 덱을 정확히 절반 나누고 교대로 카드를 뽑아 새로운 덱을 만들자
	 * 홀수개일 경우 먼저 놓는 쪽에 한장 더 넣는다.
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 개수 T
	 * (T개의 케이스)
	 * ===> 카드 개수
	 * ===> 카드 입력 (공백 구분)
	 * 
	 * 	[출력]
	 * 퍼펙트 셔플한 결과
	 * 
	 * 	[풀이방법]
	 * 인덱스 값을 잘 이용하면 될 것 같다
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

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			int N = Integer.parseInt(br.readLine().trim());
			String[] cards = br.readLine().trim().split(" ");
			
			// 절반 이하부터 넣어야한다
			int startIdx = (int) Math.ceil((double) N / 2);
			for (int idx = 0; idx < N / 2; idx++) {
				sb.append(cards[idx]).append(" ");
				sb.append(cards[startIdx + idx]).append(" ");
			}
			// 홀수면 먼저 놓은 쪽에 카드 한장 더!
			if((N&1) == 1)
			sb.append(cards[startIdx-1]);
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
