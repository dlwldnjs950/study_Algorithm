import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// N
		int N = Integer.parseInt(br.readLine().trim());

		ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
		
		for(int idx =0; idx <N; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int oper = Integer.parseInt(st.nextToken());
			int input = 0;
			switch (oper) {
			case 1:
				input = Integer.parseInt(st.nextToken());
				deque.addFirst(input);
				break;
			case 2:
				input = Integer.parseInt(st.nextToken());
				deque.addLast(input);
				break;
			case 3:
				if(deque.isEmpty()) {
					sb.append(-1);
				}else {
					sb.append(deque.pollFirst());
				}
				sb.append("\n");
				break;
			case 4:
				if(deque.isEmpty()) {
					sb.append(-1);
				}else {
					sb.append(deque.pollLast());
				}
				sb.append("\n");
				break;
			case 5:
				sb.append(deque.size()).append("\n");
				break;
			case 6:
				sb.append(deque.isEmpty()?1:0).append("\n");
				break;
			case 7:
				if(deque.isEmpty()) {
					sb.append(-1);
				}else {
					sb.append(deque.peekFirst());
				}
				sb.append("\n");
				break;
			case 8:
				if(deque.isEmpty()) {
					sb.append(-1);
				}else {
					sb.append(deque.peekLast());
				}
				sb.append("\n");
				break;
			}
		}
		System.out.println(sb);
	}

}
