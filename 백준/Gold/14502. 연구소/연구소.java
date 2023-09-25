import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	
	/**
	 * 	# 14502_연구소
	 * 
	 * 	[ 설명 ]
	 * 바이러스 확산을 막기 위한 벽 세우기
	 * 
	 * 연구소 크기는 NxM
	 * 빈칸(0) 또는 벽(1)
	 * 
	 * 일부 칸에 바이러스(2)
	 * 	상하좌우 인접한 빈칸으로 퍼져나감
	 * 
	 * 벽은 3개를 꼭 세워야 한다
	 * 
	 * 	[ 입력 ]
	 * ===> 연구소 크기
	 * ===> 연구소 상태
	 * 
	 * 	[ 출력 ]
	 * 안전 영역의 최대 크기
	 * 
	 * 	[ 풀이 ]
	 * 벽을 세운 뒤, 바이러스를 퍼트리고, 0이 적혀있는 칸의 개수 카운트
	 * */
	
	static BufferedReader br =null;
	static StringTokenizer st = null;
	static StringBuilder sb = null;
	
	static class Point implements Comparable<Point>{
		int row;
		int col;
		int breadth;
		
		Point(int row, int col, int breadth){
			this.row = row;
			this.col = col;
			this.breadth = breadth;
		}

		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			return this.breadth - o.breadth;
		}
	}
	
	static final int EMPTY = 0, WALL = 1, VIRUS = 2;
	static final int WALL_CNT = 3;
	
	//static boolean visited[][];
	static int height, width, map[][], safearea, maxSafearea;
	static List<Point> virusPoint;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 연구소 크기
		st= new StringTokenizer(br.readLine().trim());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		
		// 연구소 정보
		// 바이러스 위치
		virusPoint = new LinkedList<>();
		
		map = new int[height][width];
		safearea = 0;
		
		for(int rowIdx = 0; rowIdx<height; rowIdx++) {			
			st = new StringTokenizer(br.readLine().trim());
			
			for(int colIdx = 0; colIdx<width; colIdx++) {
				map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				if(map[rowIdx][colIdx] == VIRUS) {
					virusPoint.add(new Point(rowIdx, colIdx, 0));
				}else if(map[rowIdx][colIdx] == EMPTY) {
					safearea++;
				}
			}
		}
		
		// 벽 세우고
		// 바이러스 퍼트리기
		maxSafearea = Integer.MIN_VALUE;
		dfs(0, safearea);
		
		sb.append(maxSafearea);
		
		System.out.println(sb);
		
	}

	// 벽 세우기
	private static void dfs(int depth, int safearea) {
		if(depth == WALL_CNT) {
			bfs(safearea);
			return;
		}
		
		for(int rowIdx = 0; rowIdx<height; rowIdx++) {
			for (int colIdx = 0; colIdx < width; colIdx++) {
				if (map[rowIdx][colIdx] == EMPTY) {
					map[rowIdx][colIdx] = WALL;
					dfs(depth + 1, safearea - 1);
					map[rowIdx][colIdx] = EMPTY;
				}
			}
		}
	}

	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	// 바이러스 퍼트리기
	private static void bfs(int safearea) {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		
		// map 복사
		int[][] copyMap = new int[height][width];
		for(int idx = 0; idx< height; idx++) {
			copyMap[idx] = map[idx].clone();
			//System.out.println(Arrays.toString(copyMap[idx]));
		}
		
		// 방문 표시는 바이러스 번호로 하면 될듯!
		for(Point point : virusPoint) {
			queue.add(point);
		}
		
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			
			for(int direction=0; direction<dr.length; direction++) {
				int nr = current.row + dr[direction];
				int nc = current.col + dc[direction];
				
				if(nr < 0 || nc < 0 || nr >= height || nc >= width)
					continue;
				
				// 빈칸이 아니면 지나가기
				if (copyMap[nr][nc] != EMPTY)
					continue;
				
				queue.offer(new Point(nr, nc, current.breadth + 1));
				copyMap[nr][nc] = VIRUS;
				safearea -= 1;
			}
		}
		
		maxSafearea = Math.max(safearea, maxSafearea);
		
	}

}
