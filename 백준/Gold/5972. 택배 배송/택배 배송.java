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
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		// 그래프 초기화
		List<Map<Integer, Integer>> graph = new ArrayList<>();
		for (int idx = 0; idx <= V; idx++) {
			graph.add(new HashMap<>());
		}

		for (int loop = 0; loop < E; loop++) {
			String[] info = br.readLine().trim().split(" ");
			int from = Integer.parseInt(info[0]);
			int to = Integer.parseInt(info[1]);
			int cost = Integer.parseInt(info[2]);

			// 길이 여러개일 수 있으니 최소값으로 넣기
			int minCost = graph.get(from).getOrDefault(to, Integer.MAX_VALUE);
			minCost = Math.min(minCost, cost);

			graph.get(from).put(to, minCost);
			graph.get(to).put(from, minCost);
		}

		int[] cost = new int[V + 1];
		boolean[] visited = new boolean[V + 1];
		Arrays.fill(cost, Integer.MAX_VALUE);

		// 우선순위 큐 사용
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		pq.offer(new int[] { 1, 0 });
		cost[1] = 0;

		while (!pq.isEmpty()) {
			int[] current = pq.poll();
			int node = current[0];
			int currentCost = current[1];

			if (visited[node])
				continue;
			visited[node] = true;

			for (Map.Entry<Integer, Integer> entry : graph.get(node).entrySet()) {
				int nextNode = entry.getKey();
				int nextCost = entry.getValue();

				if (!visited[nextNode] && cost[nextNode] > currentCost + nextCost) {
					cost[nextNode] = currentCost + nextCost;
					pq.offer(new int[] { nextNode, cost[nextNode] });
				}
			}
		}

		System.out.println(cost[V]);

	}

}
