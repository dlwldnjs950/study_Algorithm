import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		while(true) {
			String str = br.readLine();
			if(str.equals("."))	break;
			
			Stack<Character> stack = new Stack<>();
			for(int idx = 0; idx<str.length(); idx++) {
				char tmp = str.charAt(idx);
				if(tmp == '(' || tmp == '[')
					stack.push(tmp);
				// 우 괄호를 만나면
				if(tmp == ')') {
					// 스택 맨 위에 좌 괄호가 있으면 빼고
					if(!stack.isEmpty() && stack.peek() == '(')
						stack.pop();
					else
						stack.push(tmp);
				}
				if(tmp == ']') {
					// 스택 맨 위에 좌 괄호가 있으면 빼고
					if(!stack.isEmpty() && stack.peek() == '[')
						stack.pop();
					else
						stack.push(tmp);
				}
			}
			// 다 확인했을 때, 스택이 비어있어야 짝이 맞는 것
			sb.append(stack.isEmpty()?"yes":"no").append("\n");
		}
		System.out.println(sb);
	}

}
