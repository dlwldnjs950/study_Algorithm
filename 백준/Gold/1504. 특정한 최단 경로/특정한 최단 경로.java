import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;

	static class Node {
		int to, cost;

		Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	static int V, E;
	static List<List<Node>> graph;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));

		// 정점 간선 개수
		st = new StringTokenizer(br.readLine().trim());
		V = Integer.parseInt(st.nextToken()); // 정점
		E = Integer.parseInt(st.nextToken()); // 간선

		// 그래프
		graph = new ArrayList<>();
		for (int loop = 0; loop <= V; loop++) {
			graph.add(new ArrayList<>());
		}

		// 연결 정보
		for (int loop = 0; loop < E; loop++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, cost));
			graph.get(to).add(new Node(from, cost));
		}

		// 필수 방문 정점
		st = new StringTokenizer(br.readLine().trim());
		int mustV1 = Integer.parseInt(st.nextToken());
		int mustV2 = Integer.parseInt(st.nextToken());

		// 방문 경우
		// 1 -> mustV1 -> mustV2 -> V
		// 1 -> mustV2 -> mustV1 -> V
		// 각 구간의 최소 거리를 구하여 최단경로 길이를 찾는다

		int[] distance_1 = new int[V + 1];
		int[] distance_mV1 = new int[V + 1];
		int[] distance_mV2 = new int[V + 1];

		dijkstra(1, distance_1);
		dijkstra(mustV1, distance_mV1);
		dijkstra(mustV2, distance_mV2);

		// 경로가 없는 경우는 INF 처리
		int path1 = (distance_1[mustV1] == Integer.MAX_VALUE || distance_mV1[mustV2] == Integer.MAX_VALUE
				|| distance_mV2[V] == Integer.MAX_VALUE) ? Integer.MAX_VALUE
						: distance_1[mustV1] + distance_mV1[mustV2] + distance_mV2[V];
		int path2 = (distance_1[mustV2] == Integer.MAX_VALUE || distance_mV2[mustV1] == Integer.MAX_VALUE
				|| distance_mV1[V] == Integer.MAX_VALUE) ? Integer.MAX_VALUE
						: distance_1[mustV2] + distance_mV2[mustV1] + distance_mV1[V];

		int answer = Math.min(path1, path2);

		if (answer < Integer.MAX_VALUE)
			System.out.println(answer);
		else
			System.out.println(-1);

	}

	static void dijkstra(int start, int[] distance) {
		Arrays.fill(distance, Integer.MAX_VALUE);
		boolean[] visited = new boolean[V + 1];

		distance[start] = 0;
		int minCost = 0, stopOver = 0;

		// 모든 간선에 대해 최소비용 찾기
		for (int loop = 0; loop < V; loop++) {
			minCost = Integer.MAX_VALUE;
			stopOver = -1;

			// 경유지 찾기
			for (int idx = 1; idx <= V; idx++) {
				if (!visited[idx] && minCost > distance[idx]) {
					stopOver = idx;
					minCost = distance[idx];
				}
			}

			if (stopOver == -1)
				break;
			
			visited[stopOver] = true;

			// 경유지에 따라 최소비용 업데이트
			for (Node node : graph.get(stopOver)) {
				int nextVertex = node.to;
				int newCost = minCost + node.cost;

				if (!visited[nextVertex] && distance[nextVertex] > newCost) {
					distance[nextVertex] = newCost;
				}
			}

		}
	}
}
