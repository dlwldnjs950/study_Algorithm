import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static boolean[] visited;
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 정점 개수
		int M = sc.nextInt(); // 간선 개수
		int V = sc.nextInt(); // 탐색 시작 정점 번호

		visited = new boolean[N + 1]; // 정점 번호를 인덱스 값으로 쓰기 위해 +1
		for (int i = 0; i < N + 1; i++) { // 그래프 초기화
			graph.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		dfs(V);
		System.out.println();
		visited = new boolean[N + 1];
		bfs(V);
	}	

	public static void dfs(int v) {
		visited[v] = true;
		System.out.print(v + " ");
		for (int i = 0; i < graph.get(v).size(); i++) {
			graph.get(v).sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o1 - o2;
				}
			});
			int x = graph.get(v).get(i);
			if (!visited[x])
				dfs(x);
		}
	}

	public static void bfs(int v) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(v);
		visited[v] = true;
		while (!q.isEmpty()) {
			int x = q.poll();
			System.out.print(x + " ");
			for (int i = 0; i < graph.get(x).size(); i++) {
				graph.get(x).sort(new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						return o1 - o2;
					}
				});
				int y = graph.get(x).get(i);
				if (!visited[y]) {
					q.offer(y);
					visited[y] = true;
				}
			}
		}
	}

}
