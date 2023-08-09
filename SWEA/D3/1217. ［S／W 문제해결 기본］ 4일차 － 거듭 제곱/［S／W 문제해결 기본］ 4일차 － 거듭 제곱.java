import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	/**
	 * 	# 1217 거듭제곱
	 * 
	 * 	[설명]
	 * 거듭제곱을 재귀호출을 이용해 구현해라
	 * N의 M 거듭제곱
	 * 
	 * 	[입력]
	 * 총 10 개의 테스트 케이스
	 * ===> 테스트 케이스 번호
	 * ===> 숫자 N과 M 공백 구분
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = 10;

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 테스트 케이스 번호 입력
			br.readLine().trim();
			
			st = new StringTokenizer(br.readLine().trim());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			sb.append(exponentiation(M, N)).append("\n");
		}
		System.out.println(sb);
	}
	
	static int exponentiation(int depth, int number) {
		if (depth > 1)
			return number * exponentiation(depth - 1, number);
		else
			return number;
	}
}
