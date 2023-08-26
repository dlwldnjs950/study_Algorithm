import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 1753 최단경로
	 * 
	 * 	[설명]
	 * 방향 그래프가 주어지면 
	 * 주어진 시작점에서 
	 * 다른 모든 정점으로의 최단 경로를 구하여라
	 * 
	 * 서로다른 두 정점 사이에 여러 개의 간선이 존재할 수 있다
	 * 
	 * 	[입력]
	 * ===> 정점개수 V 간선개수 E (정점 번호는 1부터 V까지)
	 * ===> 시작 정점 번호 K
	 * ===> u v w (출발 도착 가중치) 
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * 다익스트라 기본 코드
	 * 탐색하느라 시간 초과가 발생하는 것 같다...우선순위 큐를 사용해보자
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Edge implements Comparable<Edge>{
		int to;
		int weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 정점개수 V 간선개수 E
		st = new StringTokenizer(br.readLine().trim());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		// 출발 정점 번호
		int K = Integer.parseInt(br.readLine().trim());
		
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

		final int INF = Integer.MAX_VALUE;
		// 출발정점에서 해당 정점까지 최소거리 저장
		int[] distance = new int[V+1];
		Arrays.fill(distance, INF);
		// 방문 표시
		boolean[] visited = new boolean[V+1];

		// 탐색 시간을 줄이기 위해 우선 순위 큐를 활용한다
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		// 출발 정점 표시
		distance[K] = 0;
		pq.offer(new Edge(K, distance[K]));		
		
		int min=0, stopOver=0, cnt=0;
		//for(int loop = 0; loop<V; loop++) {
		while(!pq.isEmpty()) {

			// 아직 방문한 적 없고, 비용이 더 작은 곳 경유하기
			Edge current = pq.poll();
			min = current.weight;
			stopOver = current.to;
			
			if(visited[stopOver]) continue;

			// 방문 처리
			visited[stopOver] = true;
			// 새로운 경유지에 대해 다른 경로들도 비용을 줄일수 있는지 확인
			for (Edge e : graph.get(stopOver)) {
				if (!visited[e.to] && distance[e.to] > min + e.weight) {
					distance[e.to] = min + e.weight;
					pq.add(new Edge(e.to, distance[e.to]));
				}
			}
			// 모든 정점 확인했으면 끝내기
			if(++cnt == V) break;

			
		}
		for (int idx = 1; idx <= V; idx++) {
			sb.append(distance[idx] != INF ? distance[idx] : "INF").append("\n");
		}
		System.out.println(sb);
	}

}
