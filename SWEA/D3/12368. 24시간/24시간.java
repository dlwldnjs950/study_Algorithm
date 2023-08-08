import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 12368 24시간
	 * 
	 * 	[설명]
	 * 24시간제 시계
	 * 자정은 0시
	 * 자정으로부터 A시간 지난 시점에
	 * B시간이 지나면 몇시인가?
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 수 T
	 * ===> 정수 A, B(공백 구분)
	 * 
	 * 	[풀이방법]
	 * (A + B)%24
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
			st = new StringTokenizer(br.readLine().trim());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			sb.append((A+B)%24).append("\n");
		}
		System.out.println(sb);
	}

}
