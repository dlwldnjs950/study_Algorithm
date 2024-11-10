import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		int size = Integer.parseInt(st.nextToken());
		int want = Integer.parseInt(st.nextToken());

		LinkedList<Integer> deque = new LinkedList<Integer>();
		for (int number = 1; number <= size; number++) {
			deque.add(number);
		}

		st = new StringTokenizer(br.readLine().trim());
		int answer = 0;

		for (int loop = 0; loop < want; loop++) {
			int target = Integer.parseInt(st.nextToken());
			int targetIdx = deque.indexOf(target);
			int halfIdx = deque.size() / 2;

			if (targetIdx <= halfIdx) {
				// 2번 연산
				for (int idx = 0; idx < targetIdx; idx++) {
					int temp = deque.pollFirst();
					deque.offerLast(temp);
					answer++;
				}
			} else {
				// 3번 연산
				for (int idx = 0; idx < deque.size() - targetIdx; idx++) {
					int temp = deque.pollLast();
					deque.offerFirst(temp);
					answer++;
				}
			}

			deque.pollFirst();
		}

		System.out.println(answer);

	}

}
