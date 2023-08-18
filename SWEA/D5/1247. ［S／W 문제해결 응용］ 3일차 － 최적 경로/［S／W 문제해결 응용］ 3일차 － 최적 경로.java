import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 1247_최적 경로
	 * 
	 * 	[설명]
	 * N명의 고객 방문
	 * 회사, 집, 고객들 위치 좌표 (x,y)
	 * 두 위치 사이 거리는 |x1-x2| + |y1-y2|
	 * 
	 * 회사에서 출발 -[ 고 객 방 문 ] - 집으로 도착
	 * 
	 * 이때, 최소로 이동하는 경로의 이동거리를 출력
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 수
	 * (테스트 케이스 수 만큼)
	 * ===> 고객의 수 N
	 * ===> 회사좌표 집좌표 고객좌표*N (각각 공백 구분)
	 * 
	 * 	[출력]
	 * 최소 이동 거리 출력
	 * 
	 * 	[풀이방법]
	 * 방법 #1 : 고객을 방문할 수 있는 모든 경우의 수 중에서 최소값 찾기 (순열)
	 * 방법 #2 : 그래프, BFS로 방문(거리가 짧은 것 부터 방문해야하니까 PriorityQueue. 출발점, 거리, visited 2차원 배열)
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int N, order[], XY[][], minDistance;
	static boolean selected[];
	
	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			// 고객의 수 N
			N = Integer.parseInt(br.readLine().trim());
			
			st = new StringTokenizer(br.readLine().trim());
			// 좌표들 저장할 2차원 배열
			// 인덱스 0은 회사좌표, 인덱스 N+1은 집 좌표
			XY = new int[N+2][2];
			// 회사 좌표
			XY[0] = new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
			// 집 좌표
			XY[N+1] = new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
			// 고객들 좌표
			for(int idx = 1; idx<=N; idx++) {
				XY[idx][0] = Integer.parseInt(st.nextToken());
				XY[idx][1] = Integer.parseInt(st.nextToken());
			}
			
			// 방문 순서 배열
			order = new int[N+2];
			// 시작은 회사
			order[0] = 0;
			// 끝은 집
			order[N+1] = N+1;

			minDistance = Integer.MAX_VALUE;
			selected = new boolean[N+2];
			makeRoute(1, 0);
			
			sb.append(minDistance).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void makeRoute(int cnt, int distance) {
		if(cnt==N+1) {
			// 집까지의 거리를 구하고 거리 비교
			distance +=calculateDistance(XY[order[N]][0], XY[order[N]][1], XY[order[N+1]][0], XY[order[N+1]][1]);
			if(minDistance>distance)
				minDistance = distance;
		}else {
			for(int idx = 1; idx<=N; idx++) {
				if(selected[idx])
					continue;
				order[cnt] = idx;
				selected[idx] = true;
				makeRoute(cnt+1, distance + calculateDistance(XY[order[cnt-1]][0], XY[order[cnt-1]][1], XY[order[cnt]][0], XY[order[cnt]][1]));
				selected[idx] = false;
			}
		}
	}
	
	private static int calculateDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

}
