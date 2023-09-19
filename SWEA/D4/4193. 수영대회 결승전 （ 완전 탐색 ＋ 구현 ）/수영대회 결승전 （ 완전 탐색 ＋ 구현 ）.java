import java.awt.geom.CubicCurve2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	/**
	 * 	# 4193_수영대회 결승전
	 * 
	 * 	[ 설명 ]
	 * 바다의 N*N 공간 사용
	 * 공간을 벗어나면 실격 처리
	 * 가장 빠른 길 찾기
	 * 
	 * 장애물 : 지나갈 수 없는 것(1)
	 * 		   사라졌다 나타나는 것(2) = 생성 후 2초유지, 1초잠잠
	 * 								초는 0초부터 시작
	 * 								한번 통과한 소용돌이 위에서는 머물러 있을 수 있다
	 * 
	 * 현재까지 지난 초 정보
	 * 
	 * 	[ 입력 ]
	 * ===> 테스트 케이스 수
	 * ===> 공간 크기
	 * ===> 공간 정보
	 * ===> 시작 좌표
	 * ===> 도착 좌표
	 * 
	 * 	[ 출력 ]
	 * 이동시간 출력 ( 도착할 수 없다면 -1 )
	 * 
	 * 	[ 풀이 방법 ]
	 * 가장 빠른 길 = 최단 경로 = BFS
	 * 소용돌이...시간...
	 * 방문 하려는 시간 : 방문하려는 타이밍에 소용돌이가 잠잠한 상태인지
	 * 
	 * */
	public static class Point implements Comparable<Point>{
		int row;
		int col;
		int time;	// 방문하려는 시간
		
		public Point() {}
		
		public Point(int row, int col, int time) {
			this.row = row;
			this.col = col;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Point [row=" + row + ", col=" + col + ", time=" + time + "]";
		}

		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			return this.time - o.time;
		}
		
		
	}
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int mapSize, map[][];
	static boolean visited[][];
	
	static final int EMPTY=0, ISLAND = 1, WHIRLPOOL = 2;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= tc; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 공간 크기
			mapSize = Integer.parseInt(br.readLine().trim());

			// 공간 정보
			map = new int[mapSize][mapSize];

			for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int colIdx = 0; colIdx < mapSize; colIdx++) {
					map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}

			// 출발 좌표
			st = new StringTokenizer(br.readLine().trim());
			int startRow = Integer.parseInt(st.nextToken());
			int startCol = Integer.parseInt(st.nextToken());

			// 도착 좌표
			st = new StringTokenizer(br.readLine().trim());
			int endRow = Integer.parseInt(st.nextToken());
			int endCol = Integer.parseInt(st.nextToken());

			// 출발 시키기
			visited = new boolean[mapSize][mapSize];
			bfs(startRow, startCol, endRow, endCol);
		}
		System.out.println(sb);
	}

	// 이동 델타 배열
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1};
	
	private static void bfs(int startRow, int startCol, int endRow, int endCol) {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		
		queue.offer(new Point(startRow, startCol, 0));
		visited[startRow][startCol] = true;
		
		while (!queue.isEmpty()) {
			Point current = queue.poll();
			
			// 도착지점이 되었다면
			if(current.row == endRow && current.col == endCol) {
				sb.append(current.time).append("\n");
				return;
			}
			
			// 다음 이동 좌표 찾기
			for(int direction = 0; direction < dr.length; direction++) {
				int nr = current.row + dr[direction];
				int nc = current.col + dc[direction];
				
				// 공간 넘어가면 안됨
				if (nr < 0 || nc < 0 || nr >= mapSize || nc >= mapSize)
					continue;
				
				// 장애물 1 있으면 안됨
				if(map[nr][nc] == ISLAND)
					continue;

				// 이미 방문 했으면 다른 방향
				if (visited[nr][nc])
					continue;

				// 소용돌이가 있다면
				if(map[nr][nc] == WHIRLPOOL) {
					int time = 2-(current.time%3);
					queue.offer(new Point(nr, nc, current.time + 1 + time));
					visited[nr][nc] = true;
					continue;
				}
				
				queue.offer(new Point(nr, nc, current.time + 1));
				visited[nr][nc] = true;
			}
		}
		
		// 중간에 도착지점 못만나고 여기까지 왔으면 길이 없는 것
		sb.append(-1).append("\n");

	}
}