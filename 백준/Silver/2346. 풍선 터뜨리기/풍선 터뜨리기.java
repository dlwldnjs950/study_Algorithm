import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// N
		int N = Integer.parseInt(br.readLine().trim());

		ArrayDeque<int[]> deque = new ArrayDeque<>();

		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 1; idx <= N; idx++) {
			deque.offer(new int[] { idx, Integer.parseInt(st.nextToken()) });
		}

		int current = 0;
		while (!deque.isEmpty()) {
			int[] next = null;
			if (current >= 0) {
				for (int idx = 0; idx < current - 1; idx++) {
					deque.offerLast(deque.pollFirst());
				}
				next = deque.pollFirst();
			} else {
				for (int idx = 0; idx < current * -1 - 1; idx++) {
					deque.offerFirst(deque.pollLast());
				}
				next = deque.pollLast();
			}
			//System.out.println(Arrays.toString(next));
			sb.append(next[0]).append(" ");
			current = next[1];
		}
		System.out.println(sb);
	}

}
