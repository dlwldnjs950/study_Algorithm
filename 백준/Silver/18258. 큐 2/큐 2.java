import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
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

		LinkedList<Integer> q = new LinkedList<>();
		
		for(int idx =0; idx <N; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			switch(st.nextToken()) {
			case "push":
				int number = Integer.parseInt(st.nextToken());
				q.offer(number);
				break;
			case "pop":
				if(!q.isEmpty())
					sb.append(q.poll());
				else
					sb.append(-1);
				sb.append("\n");
				break;
			case "size":
				sb.append(q.size()).append("\n");
				break;
			case "empty":
				sb.append(q.isEmpty()?1:0).append("\n");
				break;
			case "front":
				if(!q.isEmpty())
					sb.append(q.peek());
				else
					sb.append(-1);
				sb.append("\n");
				break;
			case "back":
				if(!q.isEmpty())
					sb.append(q.peekLast());
				else
					sb.append(-1);
				sb.append("\n");
				break;
			}
		}
		System.out.println(sb);
	}

}
