import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Edge {
		int to;
		long cost;

		Edge(int to, long cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		// 시야에 보이는지 여부
		String[] visible = br.readLine().trim().split(" ");
		visible[V - 1] = "0";

		// 그래프 정보
		List<List<Edge>> graph = new ArrayList<>();
		for (int idx = 0; idx < V; idx++) {
			graph.add(new ArrayList<>());
		}

		for (int loop = 0; loop < E; loop++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long cost = Long.parseLong(st.nextToken());

			if (visible[from].equals("1") || visible[to].equals("1"))
				continue;

			graph.get(from).add(new Edge(to, cost));
			graph.get(to).add(new Edge(from, cost));
		}

		long costs[] = new long[V];
		Arrays.fill(costs, Long.MAX_VALUE);

		costs[0] = 0;
		PriorityQueue<Edge> queue = new PriorityQueue<>((a, b) -> (int) (a.cost - b.cost));
		queue.add(new Edge(0, 0));

		while (!queue.isEmpty()) {

			Edge current = queue.poll();

			if (costs[current.to] < current.cost)
				continue;

			for (Edge next : graph.get(current.to)) {
				if (current.cost + next.cost < costs[next.to]) {
					costs[next.to] = current.cost + next.cost;
					queue.add(new Edge(next.to, costs[next.to]));
				}
			}
		}

		if (costs[V - 1] == Long.MAX_VALUE)
			sb.append(-1);
		else
			sb.append(costs[V - 1]);
		System.out.println(sb);

	}

}
