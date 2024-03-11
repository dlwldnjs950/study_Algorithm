import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	/*
	 * NxM 맵
	 * 0은 이동할 수 있는 곳, 1은 이동할 수 없는 벽
	 * (1,1)에서 출발 (N,M)
	 * 최단 경로로 이동
	 * (시작, 끝 칸 포함)
	 * 벽을 부수고 이동하는 것이 더 경로가 짧다면
	 * K개까지 부수고 이동
	 * 
	 * 상하좌우 인접칸
	 * 
	 * N M K
	 * 
	 * */
		
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final char WAY='0', WALL='1';
	
	static int height, width, result;
	static char map[][];
	static boolean isVisited[][][];
	
	static class Point{
		int row;
		int col;
		int distance;
		int breakCnt;

		Point(int row, int col, int distance, int breakCnt) {
			this.row = row;
			this.col = col;
			this.distance = distance;
			this.breakCnt = breakCnt;
		}
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		
		// 공간 정보 저장
		map = new char[height][width];
		
		// 벽을 부수는 해당 횟수에 방문 여부
		isVisited = new boolean[height][width][2];
		for (int rIdx = 0; rIdx < height; rIdx++) {
			map[rIdx] = br.readLine().trim().toCharArray();
		}

		result = bfs(0,0);		
		
		sb.append(result);
		System.out.println(sb);

	}
	
	// 탐색 델타 배열
	static int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };

	private static int bfs(int row, int col) {

		Queue<Point> q = new ArrayDeque<>();

		q.add(new Point(row, col, 1,0));
		isVisited[row][col][0] = true;

		while (!q.isEmpty()) {

			Point current = q.poll();
			
			if(current.row == height-1 && current.col == width-1) {
				return current.distance;
			}

			for (int direction = 0; direction < dr.length; direction++) {
				int nr = current.row + dr[direction];
				int nc = current.col + dc[direction];

				if (nr < 0 || nc < 0 || nr >= height || nc >= width) {
					continue;
				}
				
				if(isVisited[nr][nc][current.breakCnt]) {
					continue;
				}
				
				// 그냥 길이면
				if(map[nr][nc] == WAY) {
					isVisited[nr][nc][current.breakCnt] = true;
					q.add(new Point(nr,nc,current.distance +1, current.breakCnt));
				}
				
				// 벽이면
				else if(map[nr][nc] == WALL) {
					if(current.breakCnt == 1)
						continue;
					isVisited[nr][nc][current.breakCnt +1] = true;
					q.add(new Point(nr,nc,current.distance +1, current.breakCnt +1));
				}
			}
					
		}
		return -1;
	}

}
