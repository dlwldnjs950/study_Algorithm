import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;

	static int numCnt, numbers[];

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));

		int numCnt = Integer.parseInt(br.readLine().trim());
		String[] sequence = br.readLine().trim().split(" ");
		
		Stack<Integer> stack = new Stack<>();
		List<Integer> answer = new ArrayList<>();

		for (int idx = numCnt - 1; idx >= 0; idx--) {
			int number = Integer.parseInt(sequence[idx]);
			
			while(!stack.isEmpty()) {
				if(stack.peek() > number) {
					answer.add(stack.peek());
					break;
				}else
					stack.pop();
			}
			
			if(stack.isEmpty()) {
				answer.add(-1);
			}
			
			stack.add(number);
		}
		
		Collections.reverse(answer);
		
		StringBuilder sb = new StringBuilder();
		for(int num : answer) {
			sb.append(num).append(" ");
		}
		
		System.out.println(sb);

	}
}
