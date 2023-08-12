import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 11478_서로 다른 부분 문자열의 개수
	 * 
	 * 	[설명]
	 * 문자열 S가 주어졌을 때,
	 * 서로 다른 부분 문자열의 개수를 구하여라
	 * 문자열의 길이는 1보다 크다
	 * 
	 * 	[입력]
	 * ===>
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * Set API를 사용한다
	 * 	만들어진 문자열을 set에 넣어 중복을 제외한다
	 * 부분 집합이 아니라....부분 문자열!
	 * 부분 문자열은 붙어있는 연속된 걸 말한다고...
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static String inputStr;
	static Set<String> substr;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 문자열 입력받기
		inputStr = br.readLine().trim();
		
		// 부분 문자열 담을 Set
		substr = new HashSet<>();
		
		// 문자열의 길이
		for (int strLength = 1; strLength <= inputStr.length(); strLength++) {
			// 문자열 시작 인덱스
			for (int startIdx = 0; strLength + startIdx <= inputStr.length(); startIdx++) {
				// 부분 문자열 만드는 API 사용
				substr.add(inputStr.substring(startIdx, startIdx + strLength));
			}
		}

		sb.append(substr.size());
		System.out.println(sb);
	}
}
