import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# DFS와 BFS
	 * 
	 * 	[설명]
	 * 주어진 그래프를 DFS로 탐색한 결과와
	 * BFS로 탐색한 결과를 출력해라
	 * 
	 * 방문할 수 있는 정점이 여러 개인 경우에는
	 * 		정점 번호가 작은 것부터 방문한다
	 * 정점 번호는 1부터 N까지
	 * 
	 * 	[입력]
	 * ===> 정점개수N 간선개수M 탐색시작할정점번호V (공백 구분)
	 * 
	 * 	[출력]
	 * DFS 수행 결과 출력 후 
	 * 다음줄에 BFS 수행 결과
	 * 
	 * 	[풀이방법]
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static List<List<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 정점개수N 간선개수M 탐색시작할정점번호V (공백 구분)
		st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());

		// 그래프 초기화
		for (int i = 0; i < N + 1; i++) {	// 정점 번호가 1부터 시작됨
			graph.add(new ArrayList<Integer>());
		}
		
		// 간선 개수 M만큼 두 정점 번호를 입력 받는다
		for(int idx =0; idx <M; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		// 번호가 작은 정점부터 방문해야 하므로 정렬한다
		for(int idx=1; idx<=N; idx++) {
			graph.get(idx).sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return o1 - o2;
				}
			});
		}
		
		visited = new boolean[N+1];	// 정점번호 1부터 시작
		DFS(V);
		sb.append("\n");
		visited = new boolean[N+1];	// 정점번호 1부터 시작
		BFS(V);
		
		System.out.println(sb);
	}
	
	private static void DFS(int v) {
		visited[v] = true;
		sb.append(v).append(" ");
		for(int idx = 0; idx<graph.get(v).size(); idx++) {
			int a = graph.get(v).get(idx);
			if(!visited[a])	// 방문한 적 없으면 DFS 돌리기
				DFS(a);
		}
	}
	
	private static void BFS(int v) {
		Queue<Integer> q = new ArrayDeque<>();
		// 큐에 넣으면서 방문 표시
		q.offer(v);
		visited[v] = true;
		while(!q.isEmpty()) {
			int a = q.poll();
			sb.append(a).append(" ");
			for(int idx = 0; idx<graph.get(a).size(); idx++) {
				int b = graph.get(a).get(idx);
				if(!visited[b]) {	// 방문한 적 없으면 큐에 넣기
					q.offer(b);
					visited[b] = true;
				}
			}
		}
	}

}
