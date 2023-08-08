import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 3431 준환이의 운동관리
	 * 
	 * 	[설명]
	 * 1주일에 L분 이상 U분 이하 운동 필요
	 * 이번주 X분 운동
	 * 제한된 시간 이상 운동 했는지
	 * 아니라면 몇분 더 운동을 해야 하는지 출력
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 수 T
	 * ===> L, U, X 한 줄 입력 공백 구분
	 * 
	 * 	[출력]
	 * 필요한 양 이상 운동했으면 -1
	 * 아니라면 추가 운동량 출력
	 * 
	 * 	[풀이방법]
	 * X가
	 * L 보다 작으면 L-X
	 * L 이상 U 이하면 0
	 * U보다 크면 -1
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

			// L U X 입력받기
			st = new StringTokenizer(br.readLine().trim());
			int L = Integer.parseInt(st.nextToken());
			int U = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			
			if(X<L) {
				sb.append(L-X);
			}else if(X<=U) {
				sb.append(0);
			}else {
				sb.append(-1);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
