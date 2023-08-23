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
		// 명령의 수 N
		int N = Integer.parseInt(br.readLine().trim());

		Stack<Integer> stack = new Stack<>();
		
		for(int loop =0; loop <N; loop++) {
			st = new StringTokenizer(br.readLine().trim());
			int oper = Integer.parseInt(st.nextToken());
			switch (oper) {
			case 1: 
				stack.push(Integer.parseInt(st.nextToken()));
				break;
			case 2: 
				if(!stack.isEmpty())
					sb.append(stack.pop());
				else
					sb.append(-1);
				break;
			case 3: 
				sb.append(stack.size());
				break;
			case 4: 
				sb.append(stack.isEmpty()?1:0);
				break;
			case 5: 
				if(!stack.isEmpty())
					sb.append(stack.peek());
				else
					sb.append(-1);
				break;
			}
			if(oper!=1)
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
