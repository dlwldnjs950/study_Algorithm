import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 11856_반반
	 * 
	 * 	[설명]
	 * 길이 4의 알파벳 대문자로 이루어진 문자열 S
	 * 정확히 두개의 서로 다른 문자가 등장하고
	 * 각 문자가 정확히 등장하는 지 확인
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 수 T
	 * (T 줄의 입력)
	 * ===> 길이 4의 문자열
	 * 
	 * 	[출력]
	 * Yes나 No 출력
	 * 
	 * 	[풀이방법]
	 * 문자열을 받아서 char 배열로 만들고
	 * 	정렬해서 반반 확인
	 * 	또는 Set에 넣어서 Set 사이즈가 2인지, 각 원소가 2개인지 확인
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

			char[] str = br.readLine().trim().toCharArray();
			Arrays.sort(str);
			
			if(str[0] != str[1]) {
				sb.append("No");
			}else if(str[2] != str[3]) {
				sb.append("No");
			}else if(str[0] == str[2]) {
				sb.append("No");
			}else {
				sb.append("Yes");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
