import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 4406 모음이 보이지 않는 사람
	 * 
	 * 	[설명]
	 * 주어진 문자열에서 모음을 제외해라
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 수 T
	 * ===> 문자열
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
		int T = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			String str = br.readLine().trim().replace("a", "").replace("e", "").replace("i", "").replace("o", "")
					.replace("u", "");
			sb.append(str).append("\n");
		}
		System.out.println(sb);
	}

}
