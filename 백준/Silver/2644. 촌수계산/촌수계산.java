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

		// 사람수
		int num = Integer.parseInt(br.readLine().trim());

		List<List<Integer>> graph = new ArrayList<>();
		for (int loop = 0; loop <= num; loop++) {
			graph.add(new ArrayList<>());
		}

		// 구할 관계
		st = new StringTokenizer(br.readLine().trim());
		int target1 = Integer.parseInt(st.nextToken());
		int target2 = Integer.parseInt(st.nextToken());

		// 주어질 정보 수
		int relationNum = Integer.parseInt(br.readLine().trim());

		for (int loop = 0; loop < relationNum; loop++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			graph.get(from).add(to);
			graph.get(to).add(from);
		}

		int answer = countChon(num, target1, target2, graph);

		System.out.println(answer);

	}
	
	private static int countChon(int num, int target1, int target2, List<List<Integer>> graph) {
		boolean[] visited = new boolean[num + 1];

		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(target1);
		visited[target1] = true;

		int chon = 0;
		while (!queue.isEmpty()) {

			int size = queue.size();

			for (int loop = 0; loop < size; loop++) {
				int current = queue.poll();
				if(current == target2) {
					return chon;
				}

				for (int next : graph.get(current)) {
					if(visited[next])
						continue;
					queue.add(next);
					visited[next] = true;
				}
			}
			chon++;
		}
		
		return -1;
	}

}
