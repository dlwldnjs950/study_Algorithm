import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 자료구조 개수 N
		int N = Integer.parseInt(br.readLine().trim());

		// 각 자료구조 종류 (0: 큐, 1: 스택)
		String[] queueORstack = br.readLine().trim().split(" ");

		// 각 자료구조에 있는 원소
		int[] dsNumber = new int[N];
		// 이때, 자료구조에 자료구조 큐에 있는 원소들만 담아둬야하는 거 같음
		ArrayDeque<Integer> change = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < N; idx++) {
			dsNumber[idx] = Integer.parseInt(st.nextToken());
			if(queueORstack[idx].equals("0")) {
				change.offer(dsNumber[idx]);
			}
		}

		// 삽입할 수열 길이
		int insertNumLength = Integer.parseInt(br.readLine().trim());

		st = new StringTokenizer(br.readLine().trim());
		// 각 수열에 대해 큐스택 자료구조 연산 수행
		for (int loop = 0; loop < insertNumLength; loop++) {
			int insertNum = Integer.parseInt(st.nextToken());
            
            // 큐의 동작이지만 앞에서 넣고
			change.offerFirst(insertNum);
			
            // 뒤에서 뺸다
			sb.append(change.pollLast()).append(" ");
		}
		System.out.println(sb);
	}

}
