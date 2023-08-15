import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 1234_비밀번호
	 * 
	 * 	[설명]
	 * 같은 번호로 붙어있는 쌍들이 없을 때까지 소거
	 * 
	 * 	[입력]
	 * (테스트 케이스 10개)
	 * ===> 문자열길이 문자열 (공백 구분)
	 * 
	 * 	[출력]
	 * 소거하고 남은 비밀번호
	 * 
	 * 	[풀이방법]
	 * ...스택 문제구나...
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = 10;

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 문자열 길이와 문자열 입력받기
			st = new StringTokenizer(br.readLine().trim());
			int len = Integer.parseInt(st.nextToken());
			String str = st.nextToken();
			
			// 확인하는 문자열 저장할 변수
			Stack<Character> stack = new Stack<Character>();
			
			for(int i = 0; i < len; i++) {
                char cur = str.charAt(i);
                 
                // 스택이 비어있거나 스택 peek가 다른 문자라면 push
                if(stack.isEmpty() || cur != stack.peek()) {
                    stack.push(cur);
                }
                // 스택 peek이 현재 문자와 같다면 pop
                else {
                    stack.pop();
                }
            }
			
			StringBuilder tmp = new StringBuilder();
			while(!stack.isEmpty()) {
				tmp.append(stack.pop());
			}
			sb.append(tmp.reverse());
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
