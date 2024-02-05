import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 각 구멍 (빈칸)에 핀 하나 꽂을 수 있음
	 * 인접 = 수평, 수직
	 * 인접한 핀을 뛰어넘어 그 핀의 다음 칸으로 이동
	 * 		그 다음칸은 비어있어야 한다 (= 빈칸, 구멍이 없는 칸)
	 * 		그 인접한 핀은 제거
	 * 핀을 적절히 움직여
	 * 게임판에 남아있는 핀의 개수 최소
	 * 최소 이동 횟수 Math.min
	 * 
	 * . : 빈칸
	 * o : 핀
	 * # : 구멍 없는 칸
	 * 
	 * 핀은 최대 8
	 * 
	 * 새로 꽂는건 아니군!
	 * 핀이 꽂혀있는 칸이 출발점이 될 수 있다.
	 * 
	 * 게임판은 모두 같은 모양
	 * 게임판 9 * 5
	 * */
		
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final char EMPTY = '.';
	static final char PIN = 'o';
	static final char NOT = '#';

	static final int WIDTH = 9, HEIGHT = 5;

	static int minRemain = 0, minMove = 0;
	static char map[][];

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine().trim());

		for (int testCase = 0; testCase < tc; testCase++) {
			
			// 공간 정보
			map = new char[HEIGHT][WIDTH];
			// 핀 개수
			int pinCnt = 0;
			
			for (int rIdx = 0; rIdx < HEIGHT; rIdx++) {
				String str = br.readLine().trim();

				for (int cIdx = 0; cIdx < WIDTH; cIdx++) {
					map[rIdx][cIdx] = str.charAt(cIdx);
					if(map[rIdx][cIdx] == PIN) {
						pinCnt++;
					}
				}
			}
			
			minRemain = pinCnt;
			
			for (int rIdx = 0; rIdx < HEIGHT; rIdx++) {
				for (int cIdx = 0; cIdx < WIDTH; cIdx++) {
					// 핀이면 탐색 시작
					if(map[rIdx][cIdx] == PIN) {
						dfs(rIdx, cIdx, pinCnt, 0);
					}
				}
			}
			

			br.readLine();
			
			sb.append(minRemain).append(" ").append(minMove).append("\n");
		}
		
		System.out.println(sb);

	}
	
	// 우, 하, 좌, 상
	static int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };
	
	public static void dfs(int row, int col, int remainPin, int moveCnt) {
		if(minRemain >= remainPin) {
			minRemain = remainPin;
			minMove = moveCnt;
		}
		
		for(int direction = 0; direction <dr.length; direction++) {
			int nr = row + dr[direction];
			int nc = col + dc[direction];
			
			if(isValid(nr, nc) && map[nr][nc] == PIN) {
				int nextr = nr + dr[direction];
				int nextc = nc + dc[direction];
				
				if (nextr < 0 || nextc < 0 || nextr >= HEIGHT || nextc >= WIDTH)
					continue;
				
				if(isValid(nextr, nextc) && map[nextr][nextc] == EMPTY) {
					map[row][col] = map[nr][nc] = EMPTY;
					map[nextr][nextc] = PIN;
					
					for (int rIdx = 0; rIdx < HEIGHT; rIdx++) {
						for (int cIdx = 0; cIdx < WIDTH; cIdx++) {
							// 핀이면 탐색 시작
							if(map[rIdx][cIdx] == PIN) {
								dfs(rIdx, cIdx, remainPin -1, moveCnt +1);
							}
						}
					}
					
					map[row][col] = map[nr][nc] = PIN;
					map[nextr][nextc] = EMPTY;
				}
				
			}
		}
	}

	public static boolean isValid(int row, int col) {//map안에 있는지 확인
		return row>=0 && row<HEIGHT && col>=0 && col<WIDTH;
	}
}
