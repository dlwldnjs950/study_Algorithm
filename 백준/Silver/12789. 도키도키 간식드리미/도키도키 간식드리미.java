import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 
	 * 
	 * 	[설명]
	 * 
	 * 	[입력]
	 * ===> 
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * 현재 줄서있는 순서는 큐에 넣고
	 * 옆에 한줄서기는 스택
	 * 1번부터 받아가야한다 (현재 받는 번호 변수)
	 * 	pop이나 poll이 발생했을 때, ++
	 * 큐의 peek을 확인해서 지금 받을 순서가 아니면 스택으로 넣기
	 * 큐에 지금 받을 순서가 아니면 스택 확인하기
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// N
		int N = Integer.parseInt(br.readLine().trim());

		Queue<Integer> origin = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine().trim());
		// 큐에 줄선사람들 넣기
		while (st.hasMoreTokens()) {
			origin.offer(Integer.parseInt(st.nextToken()));
		}

		Stack<Integer> waiting = new Stack<>();

		int num = 1;
		while(!origin.isEmpty()) {
			if(origin.peek()==num) {
				origin.poll();
				num++;
			}else if(!waiting.isEmpty() && waiting.peek() == num) {
				waiting.pop();
				num++;
			}else {
				waiting.push(origin.poll());
			}
		}
		while(!waiting.isEmpty()) {
			if(waiting.peek()==num) {
				waiting.pop();
				num++;
			}else {
				break;
			}
		}
		sb.append((origin.isEmpty() && waiting.isEmpty())?"Nice":"Sad");
		System.out.println(sb);
	}

}
