import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 1238_Contact
	 * 
	 * 	[설명]
	 * 비상 연락망과 연락을 시작하는 당번 정보가 주어질 때, 
	 * 가장 나중에 연락을 받게되는 사람 중 번호가 가장 큰 사람의 번호?
	 * 
	 * 	[입력]
	 * (10개의 테스트 케이스)
	 * ===> 입력의 데이터 길이와 시작점 (공백 구분)
	 * ===> 연결 정보 (from to 쌍, 공백구분)
	 * 
	 * 번호가 1부터 시작
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * 너비 우선 탐색 + depth
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static List<Set<Integer>> graph;
	static boolean visited[];
	static final int LIMIT =100;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = 10;

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			// 입력의 데이터 길이와 시작점
			st = new StringTokenizer(br.readLine().trim());
			int inputSize = Integer.parseInt(st.nextToken());
			int startVertex = Integer.parseInt(st.nextToken());
			
			// 연결 정보 저장할 리스트
			graph = new ArrayList<>();
			for(int num = 0; num<=LIMIT; num++) {
				graph.add(new HashSet<Integer>());
			}

			// 연결 정보 저장. 같은 연결에 대한 정보가 여러번 들어올 수 있으므로 set을 이용한다
			st = new StringTokenizer(br.readLine().trim());
			while(st.hasMoreTokens()) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph.get(from).add(to);
			}
			
			visited = new boolean[LIMIT+1];
			bfs(startVertex, 0);
		}
		
		System.out.println(sb);
	}

	private static void bfs(int startVertex, int depth) {
		// 방문 순서 관리. 정점 번호 + 방문한 depth
		Queue<int[]> q = new ArrayDeque<>();
		PriorityQueue<int[]> realVisit = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// 깊이 같으면 정점 번호기준 내림차순
				if(o1[1] == o2[1])
					return o2[0] - o1[0];
				// 깊이 기준 내림 차순
				return o2[1] - o1[1];
			}
		});
		
		// 방문 시작 정점
		q.offer(new int[] {startVertex,0});
		visited[startVertex] = true;
		
		while(!q.isEmpty()) {
			int[] a = q.poll();
			// 실제 방문
			realVisit.add(a);
			// 연결된 정점에 대해서만 방문
			for(int b : graph.get(a[0])) {
				// 이전에 방문 기록이 없으면
				if(!visited[b]) {
					// 방문할 예정
					q.offer(new int[] {b, a[1]+1});
					visited[b] = true;
				}
			}
		}
		// 최상단 정점 번호 출력
		sb.append(realVisit.peek()[0]).append("\n");
	}

}
