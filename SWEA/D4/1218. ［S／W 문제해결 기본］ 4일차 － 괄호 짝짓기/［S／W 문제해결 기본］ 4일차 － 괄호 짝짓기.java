import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	#1218 괄호 짝짓기
	 * 
	 * 	[ 설명 ]
	 * 4종쥬의 괄호문자 '()', '[]', '{}', '<>'
	 * 문자열에 사용된 괄호들이 짝이 모두 맞는지 판별하자
	 * 
	 * 10개의 테스트 케이스
	 * 
	 * 	[ 입력 ]
	 * ===> 해당 테케의 문자열 길이
	 * ===> 문자열
	 * 
	 * 	[ 출력 ]
	 * 유효하면 1, 유효하지 않으면 0 출력
	 * 
	 * 	[ 풀이 방법 ]
	 * 1. 최근에 들어간 괄호와 짝이 맞아야하므로 stack 자료구조를 사용한다
	 * 2. 왼쪽 괄호면 스택에 넣고
	 * 	2-1. 왼쪽 괄호인지 확인하는 방법 : 왼쪽 괄호인지 확인하는 함수
	 * 3. 오른쪽 괄호면 스택의 peek에 짝인 왼쪽 괄호가 있으면 pop
	 * 									    없으면 push
	 * 	3-1. 서로 짝인 지 확인하는 함수
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final char[][] brackets = {{'(',')'}, {'[',']'}, {'{','}'}, {'<','>'}};
	
	static Stack<Character> meetBracket;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//테스트 케이스는 10개
		int TC = 10;
		
		for(int testCase =1; testCase<=TC; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			// 문자열 길이
			int strLength = Integer.parseInt(br.readLine().trim());
			String str = br.readLine().trim();
			
			// 만난 괄호를 관리하는 스택
			meetBracket = new Stack<>();

			for(int sIdx = 0; sIdx<strLength; sIdx++) {
				char tmp = str.charAt(sIdx);
				// 왼쪽 괄호면 넣고
				if(isLeft(tmp))
					meetBracket.push(tmp);
				// 오른쪽 괄호라면
				else {
					// 비어있거나 최상단 괄호와 짝이 안맞으면
					if(meetBracket.isEmpty() || !isPair(tmp)) {
						// 스택에 넣기
						meetBracket.push(tmp);
					// 비어있지 않고 짝도 맞으면 빼기
					}else {
						meetBracket.pop();
					}
				}
			}
			sb.append(meetBracket.isEmpty()?1:0).append("\n");
		}
		System.out.println(sb);
	}
	
	// 왼쪽 괄호인지 확인하는 함수
	static boolean isLeft(char bracket){
		for(int bIdx = 0; bIdx<brackets.length; bIdx++) {
			// 왼쪽 괄호 중 하나라면 true 리턴
			if(brackets[bIdx][0] == bracket)
				return true;
		}
		// 없었으면 false
		return false;
	}
	
	// 짝이 맞는지 확인하는 함수
	static boolean isPair(char bracket) {
		for(int bIdx = 0; bIdx<brackets.length; bIdx++) {
			// 오른쪽 괄호에 해당하는 인덱스를 찾아
			// 그 인덱스에 맞는 왼쪽 괄호가 스택의 peek에 있어야 한다.
			if(brackets[bIdx][1] == bracket) {
				if(meetBracket.peek() == brackets[bIdx][0])
					return true;
			}
		}
		return false;
	}
}
