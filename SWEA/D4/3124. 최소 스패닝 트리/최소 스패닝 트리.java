import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	// 트리를 구성하기 위한 변수와 함수
	static int parents[], rank[];
	
	private static void make() {
		// 최초의 서로소 집합 구성하기
		parents = new int[V+1];
		rank = new int[V+1];
		for(int idx = 0; idx<=V; idx++) {
			parents[idx] = idx;	// 부모가 자기 자신
		}
	}
	
	// 입력한 정점의 부모 찾기
	// 경로 압축도 함께
	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);
		
		// 부모가 같다면 같은 집합이므로 합칠 수 없다
		if(aParent == bParent) return false;
		
		// 부모가 서로 달랐다면
		
		// a부모의 rank가 더 크면 b를 a 밑으로
		if(rank[aParent] > rank[bParent]) {
			parents[bParent] = aParent;
			return true;
		}
		
		// b부모의 rank가 더 크면 a를 b 밑으로
		parents[aParent] = bParent;
		
		// rank가 서로 같았다면 b의 부모의 rank를 높여서 b밑으로 a를 넣는다
		// 위에서 이미 b밑에 a를 넣어줬으니까 b부모의 rank만 높여주면 된다
		if(rank[aParent] == rank[bParent])
			rank[bParent]++;
		
		return true;
	}
	
	// 간선 정보 클래스
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int V,E;
	
	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
		
			// 정점 개수 V 간선 개수 E
			st = new StringTokenizer(br.readLine().trim());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			// 간선 개수만큼 간선 정보 입력받기
			Edge[] edge = new Edge[E];
			for(int idx = 0; idx<E; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				// 번호가 1부터 시작하네...
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edge[idx] = new Edge(from, to, weight);
			}
			
			// 가중치를 기준으로 배열 오름차순 정렬
			Arrays.sort(edge);
			
			make();
			
			// 각 간선의 최소값부터 확인하며, 트리 구성이 가능하면 누적한다
			// 최소 신장 트리가 될 때 까지 (연결된 간선수가 V-1개가 될 때 까지)
			long sum = 0;
			int cnt = 0;
			for(Edge e : edge) {
				if(union(e.from, e.to)) {
					sum+=e.weight;
					if(++cnt==V-1)	break;
				}
			}
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}

}
