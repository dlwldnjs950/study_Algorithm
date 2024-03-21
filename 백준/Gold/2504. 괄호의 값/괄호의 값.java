import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// () : 2점, [] : 3점, 안에있으면 곱하기, 붙어있으면 더하기
		// 괄호 확인은 일단 스택이었는데
		// 속해있는거 / 붙어있는거 판단 하우투!
		// 속해있는걸 뭉텅이로 점수 매기기 = 곱하기 => 스택에 넣기
		// 문자열 마지막까지 봤으면 스택에서 빼면서 더하기
		// 이때, 문자열 마지막까지 봤는데 스택 안비어 있으면 걍 0점!
		
		String bracketStr = br.readLine().trim();

		Stack<Character> bracket = new Stack<>();

		// (()()())
		
		int result = 0;	// 결과
		int cnt = 1;	// 중간 곱
		boolean flag = false;
		for (int idx = 0; idx < bracketStr.length(); idx++) {
			char current = bracketStr.charAt(idx);

			if (current == '(') {
				bracket.add(current);
				cnt *=2;
			} else if (current == '[') {
				bracket.add(current);
				cnt *=3;
			} else if (current == ')') {
				// 스택 짝이 안맞는 경우
				if(bracket.isEmpty() || bracket.peek() != '(') {
					flag = true;
					break;
				}
				// 괄호가 닫힌 경우
				if(bracketStr.charAt(idx-1) =='(') {
					result += cnt;
				}
				bracket.pop();
				cnt /= 2;

			} else if (current == ']') {
				if(bracket.isEmpty() || bracket.peek() != '[') {
					flag = true;
					break;
				}
				// 괄호가 닫힌 경우
				if(bracketStr.charAt(idx-1) =='[') {
					result += cnt;
				}
				bracket.pop();
				cnt /= 3;
			}
		}
		
		// 점수합 구하기
		int sum=0;
		// 스택이 비어있지 않다면 쌍이 맞지 않는 것 : 0을 출력하면 된다.
		// 끝까지 확인하지 못한 경우도 존재한다.
		if(!bracket.isEmpty() || flag) {
			sb.append(0);
		}else {
			sb.append(result);
		}
		
		System.out.println(sb);
	}
}
