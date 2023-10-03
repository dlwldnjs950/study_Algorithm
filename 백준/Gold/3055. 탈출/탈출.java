import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * # 3055_탈출
	 * 
	 * [설명] 
	 * 비어 있는 곳 : . 
	 * 물 : * 
	 * 돌 : X 
	 * 비버 굴 : D 
	 * 고슴도치 : S
	 * 
	 * 고슴도치는 상하좌우 이동 가능 
	 * 물은 인접한 비어있는 칸으로 확장
	 * 
	 * 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다
	 *  => 물을 더 먼저 채우고 그 다음에 고슴도치 이동
	 * 
	 * [입력] 
	 * ===> 지도 크기 R과 C 
	 * ===> 지도 정보
	 * 
	 * [출력] 
	 * 고슴도치가 안전하게 비버 굴로 이동하기 위한 최소 시간
	 * 
	 * [풀이방법] 백트래킹
	 * 
	 * 물의 위치 list 
	 * 고슴도치 현재 위치 
	 * 고슴도치의 방문이 큐가 비어서 끝났는데 비버굴 위치가 아니었다면 KAKTUS
	 * 
	 */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Point implements Comparable<Point>{
		int row;
		int col;
		int time;

		public Point(int row, int col, int time) {
			super();
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
			return this.time - o.time;
		}

	}

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 맵 크기
		st = new StringTokenizer(br.readLine().trim());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		// 맵
		char[][] map = new char[R][C];
		
		// 고슴도치 위치
		Point hedgehog = null;
		
		// 비버 굴 위치
		Point beaver = null;
		
		// 물 위치
		PriorityQueue<Point> waters = new PriorityQueue<>();
		
		// 맵 정보
		for(int rowIdx = 0; rowIdx<R; rowIdx++) {
			String line = br.readLine().trim();
			
			for(int colIdx = 0; colIdx<C; colIdx++) {
					map[rowIdx][colIdx] = line.charAt(colIdx);
					
					if(map[rowIdx][colIdx] == 'S') {
						hedgehog = new Point(rowIdx, colIdx, 0);
					}else if(map[rowIdx][colIdx] == 'D')
						beaver = new Point(rowIdx, colIdx, 0);
					else if(map[rowIdx][colIdx] == '*')
						waters.add(new Point(rowIdx, colIdx, 0));
			}
		}

		// 물과 고슴도치 이동시키기
		Queue<Point> q = new ArrayDeque<>();
		q.add(hedgehog);
		int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0};
		
		boolean flag = false;
		
		while (!q.isEmpty()) {

			// 물 먼저 퍼트리고
			int size = waters.size();
			for (int loop = 0; loop < size; loop++) {
				Point curWater = waters.poll();
				for (int direction = 0; direction < dr.length; direction++) {
					int nr = curWater.row + dr[direction];
					int nc = curWater.col + dc[direction];

					if (nr < 0 || nc < 0 || nr >= R || nc >= C)
						continue;

					// 빈칸이거나 고슴도치가 있던 자리 갈 수 있음
					if (map[nr][nc] == '.' || map[nr][nc] == 'S') {

						map[nr][nc] = '*';
						waters.add(new Point(nr, nc, curWater.time + 1));
					}
				}
			}

//			
//			for(char[] tmp : map) {
//				System.out.println(Arrays.toString(tmp));
//			}
//			System.out.println();
			
			// 고슴도치 이동
			// 지금 큐에 담긴건 지금 물 퍼진 상태로 모두 확인해야함
			int qSize = q.size();
			for (int loop = 0; loop < qSize; loop++) {
				// System.out.println("가자 도치야!");
				Point current = q.poll();

				// 굴에 도착했으면 끝내기
				if (current.row == beaver.row && current.col == beaver.col) {
					sb.append(current.time);
					flag = true;
					break;
				}

				for (int direction = 0; direction < dr.length; direction++) {
					int nr = current.row + dr[direction];
					int nc = current.col + dc[direction];

					if (nr < 0 || nc < 0 || nr >= R || nc >= C)
						continue;

					// 고슴도치는 빈칸이거나 D로 이동
					if (map[nr][nc] == '.' || map[nr][nc] == 'D') {

						q.add(new Point(nr, nc, current.time + 1));
						// 방문 예정 표시
						map[nr][nc] = 'S';
					}
				}
			}
		}
		
		
		if(!flag)
			sb.append("KAKTUS");
		System.out.println(sb);
	}

}
