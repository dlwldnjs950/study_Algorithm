import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 1251_하나로
	 * 
	 * 	[설명]
	 * 인도네시아 내의 모든 섬을 해저 터널로 연결하는 것이 목표
	 * 해저터널 : 두 섬을 선분으로 연결,
	 * 			두 터널 교차되어도 물리적으로는 연결 X
	 * 간접 연결도 연결
	 * 
	 * 	# 환경 부담금 정책
	 * 환경 부담 세율 E, 해저터널 길이 L
	 * 	 = E * L^2 만큼 지불
	 * 
	 * 환경 부담금을 최소로, N개의 모든 섬을 연결하는 교통 시스템 설계
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 수
	 * ===> 섬의 개수 N
	 * ===> 각 섬들의 x 좌표
	 * ===> 각 섬들의 y 좌표
	 * ===> 해저터널 건설 환경 부담 세율 실수 E
	 * 
	 * 	[출력]
	 * 최소 환경 부담금을 소수 첫째자리에서 반올림하여 정수로 출력
	 * 
	 * 	[풀이방법]
	 * 해저 터널 길이를 최소로 해야한다
	 * 
	 * 모든 정점을 연결하는 간선들의 가중치 합이 최소가 되는 트리를 찾는
	 * 크루스칼 알고리즘, 프림 알고리즘
	 * => 간적쿠 간만프
	 * 
	 * 크루스칼로 풀어보자
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int parents[];
	static int rank[];
	
	private static void make() {
		// 초기 집합 상태 만들기
		parents = new int[N];
		rank = new int[N];
		
		for(int idx = 0; idx<N; idx++) {
			parents[idx] = idx;
		}
	}
	
	private static int find(int a) {
		// 자신의 부모와 자신이 같으면 대표 원소
		if(parents[a] == a) return a;
		// 경로 압축
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {	// 같은 집합이므로 합칠 수 없다
			return false;
		}
		
		// rank가 더 높은 쪽으로 합치기
		if(rank[aRoot] > rank[bRoot]) {
			parents[bRoot] = aRoot;
			return true;
		}
		
		parents[aRoot] = bRoot;
		
		// rank가 같았다면 임의로 b 아래로 a 넣기
		if(rank[aRoot] == rank[bRoot]) {
			rank[bRoot]++;
		}
		return true;
	}
	
	static class Edge implements Comparable<Edge>{
		int from, to;
		double weight;

		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	
	static int N;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 섬의 개수 N
			N = Integer.parseInt(br.readLine().trim());
			
			double island[][] = new double[N][2];
			// 섬의 x 좌표 들
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0;idx<N; idx++) {
				island[idx][0] = Double.parseDouble(st.nextToken());
			}
			// 섬의 y 좌표 들
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0;idx<N; idx++) {
				island[idx][1] = Double.parseDouble(st.nextToken());
			}
			
			double E = Double.parseDouble(br.readLine().trim());
			
			// 가중치는 거리!
			List<Edge> edgeList = new ArrayList<>();
			for(int rIdx = 0; rIdx<N; rIdx++) {
				for(int cIdx = 0; cIdx<N; cIdx++) {
					if(rIdx == cIdx)
						continue;
					edgeList.add(new Edge(rIdx, cIdx, calculateDistance(island[rIdx], island[cIdx])));
				}
			}
			
			// 가중치 기준 오름차순 정렬
			Collections.sort(edgeList);
			
			make();
			
			double result = 0;
			int cnt = 0;
			for(Edge e : edgeList) {
				if(union(e.from, e.to)) {
					result+=e.weight;
					if(++cnt == N-1) break;
				}
			}
			//System.out.println(result);
			sb.append(Math.round(E*result)).append("\n");
		}
		
		
		System.out.println(sb);
	}

	static double calculateDistance(double[] from, double[] to){
		double a = Math.abs(from[0]-to[0]);
		double b = Math.abs(from[1]-to[1]);
		return a*a+b*b;
	}
}
