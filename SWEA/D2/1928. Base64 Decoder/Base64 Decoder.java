import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * # 1928 Base64 Decoder
	 * 
	 * 	Base64 인코딩된 문자열이 주어졌을 때,
	 * 	디코딩하여 원문을 출력해라
	 * 
	 * 	문자열의 길이는 항상 4의 배수
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
			String encodeStr = br.readLine().trim();
			String decodeStr = new String(Base64.getDecoder().decode(encodeStr));
			
			sb.append(decodeStr).append("\n");
		}
		System.out.println(sb);
	}

}
