import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	/*
	 * 가로와 세로로만 움직일 수 있다
	 * 지도 크기 n,m
	 * 0은 갈 수 없는 땅, 1은 갈 수 있는 땅
	 * 2는 목표지점
	 * 
	 * 원래 갈수 없는 땅은 0, 갈수있었는데 갈수업는 땅 -1
	 * 배열을 2가지~
	 * 
	 * */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int CANT=0, CAN=1, TARGET=2, UNABLE=-1; 

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[height][width];
		int[][] result = new int[height][width];
		
		// 출발지점
		int startRow=-1, startCol=-1;
		
		for(int rowIdx=0; rowIdx<height; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int colIdx=0; colIdx<width; colIdx++) {
				map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				if(map[rowIdx][colIdx] == TARGET) {
					map[rowIdx][colIdx] = result[rowIdx][colIdx] = 0;
					startRow = rowIdx;
					startCol = colIdx;
				}
				if(map[rowIdx][colIdx] == CAN) {
					result[rowIdx][colIdx] = UNABLE;
				}
				
			}
		}

		// 탐색
		boolean[][] isVisited = new boolean[height][width];
		Queue<int[]> queue = new ArrayDeque<>();

		isVisited[startRow][startCol] = true;
		queue.add(new int[] { startRow, startCol });

		int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };

		while (!queue.isEmpty()) {
			int[] current = queue.poll();

			for(int direction=0; direction<dr.length; direction++) {
				int nr = current[0] + dr[direction];
				int nc = current[1] + dc[direction];

				if (nr < 0 || nc < 0 || nr >= height || nc >= width)
					continue;
				if (isVisited[nr][nc])
					continue;
				if(map[nr][nc] == CANT)
					continue;
				
				isVisited[nr][nc] = true;
				queue.add(new int[] { nr, nc });
				map[nr][nc] = result[nr][nc] = map[current[0]][current[1]] +1;
			}
		}
		
		for(int[] row:result) {
			for(int col : row) {
				sb.append(col).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
