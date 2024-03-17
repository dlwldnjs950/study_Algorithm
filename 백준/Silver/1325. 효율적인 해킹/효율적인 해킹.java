import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static List<Integer> resultList = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		int computerCnt = Integer.parseInt(st.nextToken());
		int relationCnt = Integer.parseInt(st.nextToken());

		List<List<Integer>> relationship = new ArrayList<>();

		for (int idx = 0; idx <= computerCnt; idx++) {
			relationship.add(new ArrayList<>());
		}

		for (int idx = 0; idx < relationCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			// relationship.get(from).add(to);
			relationship.get(to).add(from);
		}

		int max = -1;
		int[] range = new int[computerCnt+1];
		for (int idx = 1; idx <= computerCnt; idx++) {
			boolean[] isVisited = new boolean[computerCnt + 1];

			int cnt = 0;
			Queue<Integer> q = new ArrayDeque<>();
			isVisited[idx] = true;
			q.add(idx);
			while(!q.isEmpty()) {
				int currnet = q.poll();
				
				for(int next : relationship.get(currnet)) {
					if(isVisited[next])
						continue;
					cnt ++;
					isVisited[next] = true;
					q.add(next);
				}
			}

			range[idx] = cnt;

			max = Math.max(max,range[idx]);
		}
		
		for (int idx = 1; idx <= computerCnt; idx++) {
			if(range[idx] == max)
				sb.append(idx).append(" ");
		}

		System.out.println(sb);
	}
}
