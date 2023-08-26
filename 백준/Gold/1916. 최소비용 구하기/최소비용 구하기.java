import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Edge {
		int to;
		int weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 정점개수 V 간선개수 E
		int V = Integer.parseInt(br.readLine().trim());
		int E = Integer.parseInt(br.readLine().trim());
		
		// 인접 리스트 (List 자료구조 사용)
		List<List<Edge>> graph = new ArrayList<>();
		// 정점 번호가 1부터 시작이기 때문에 V까지 진행
		for(int idx = 0; idx<=V; idx++) {
			graph.add(new ArrayList<>());
		}

		// 간선 정보 저장
		for(int idx =0; idx <E; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Edge(to, weight));
		}
		
		st = new StringTokenizer(br.readLine().trim());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		final int INF = Integer.MAX_VALUE;
		// 출발정점에서 해당 정점까지 최소거리 저장
		int[] distance = new int[V+1];
		Arrays.fill(distance, INF);
		// 방문 표시
		boolean[] visited = new boolean[V+1];
		
		// 출발 정점 표시
		distance[start] = 0;
		
		int min, stopOver;
		for(int loop = 0; loop<V; loop++) {
			min = INF;
			stopOver = -1;
			for(int idx = 1; idx<=V; idx++) {
				// 아직 방문한 적 없고, 비용이 더 작은 곳 경유하기
				if(!visited[idx] && min>distance[idx]) {
					min = distance[idx];
					stopOver = idx;
				}
			}

			// 경유지를 찾지 못했으면 끝내기
			if (stopOver == -1)
				break;

			// 방문 처리
			visited[stopOver] = true;

			// 새로운 경유지에 대해 다른 경로들도 비용을 줄일수 있는지 확인
			for (int idx = 1; idx <= V; idx++) {
				for (Edge e : graph.get(stopOver)) {
					if (!visited[e.to] && distance[e.to] > min + e.weight) {
						distance[e.to] = min + e.weight;
					}
				}
			}
		}
		sb.append(distance[end]);
		System.out.println(sb);
	}

}
