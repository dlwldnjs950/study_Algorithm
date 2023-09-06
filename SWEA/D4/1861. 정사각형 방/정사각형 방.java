import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	/**
	 * # 1861_정사각형 방
	 * 
	 * [설명]
	 *  N*N개의 방 2차원 형태로 존재 
	 *  i번째 줄 j번째 방에 적혀있는 수 = Aij 
	 *  (방에 적혀있는 숫자는 서로 다르다)
	 * 
	 * 상하좌우로 방 이동 가능 단, 
	 * 방이 존재해야하고 
	 * 이동하려는 방에 적힌 숫자가 
	 * 현재 방에 적흰 숫자보다 정확히 1 더 커야한다.
	 * 
	 * 처음에 어떤 수가 적힌 방에 있어야 가장 많은 개수의 방을 이동하는지 구하여라
	 * 
	 * [입력] 
	 * ===> 테스트 케이스 수 
	 * ===> 방의 크기 N 
	 * ===> 각 방에 적힌 숫자 정보
	 * 
	 * [출력] 
	 * 처음 출발하는 방 번호와 이동한 방의 개수 공백 구분 출력
	 * 
	 * [풀이방법] 
	 * 출발점을 찾아야하는데 이동한 방의 개수는 도착점에서 알 수 있다 
	 * => 도착점을 기준으로 출발점을 찾는다 
	 * => 이동하려는 방에 적힌 숫자가 정확히 1 더 작은 방으로 이동한다
	 * 
	 * 1. 각 방을 기준으로 탐색 
	 * 2. 상하좌우
	 *  2-1. DFS 
	 *  3. 이동을 더이상 못할때, 이동한 방의 개수 확인 
	 *  4. 이동한 방의 개수가 더 커졌다면 저장 (이동한 방의 개수, 출발점) 
	 *  5. 이동한 방의 개수가 같다면 출발점은 더 작은 것으로 저장
	 * 
	 */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int roomSize, rooms[][], maxMove, startRoomNum;
	static boolean visited[][];

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 방의 크기
			roomSize = Integer.parseInt(br.readLine().trim());

			// 방에 적힌 번호 정보
			rooms = new int[roomSize][roomSize];
			
			for (int rowIdx = 0; rowIdx < roomSize; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				
				for (int colIdx = 0; colIdx < roomSize; colIdx++) {
					rooms[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 각 방에서 탐색하기
			maxMove = Integer.MIN_VALUE;
			startRoomNum = Integer.MAX_VALUE;
			
			for (int rowIdx = 0; rowIdx < roomSize; rowIdx++) {	
				
				// 방문 표시 배열 초기화
				visited = new boolean[roomSize][roomSize];	
				
				for (int colIdx = 0; colIdx < roomSize; colIdx++) {
					
					dfs(rowIdx, colIdx, 1);
				}
			}

			sb.append(startRoomNum).append(" ").append(maxMove).append("\n");
		}
		System.out.println(sb);
	}

	// 탐색 델타 배열
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	private static void dfs(int row, int col, int move) {
		visited[row][col] = true;
		
		// 이동한 방 개수와 출발점 비교
		if (maxMove < move) {
			maxMove = move;
			startRoomNum = rooms[row][col];
		} else if (maxMove == move) {
			startRoomNum = Math.min(startRoomNum, rooms[row][col]);
		}
		// 상하좌우 각 방향 탐색
		for (int direction = 0; direction < dr.length; direction++) {
			int nr = row + dr[direction];
			int nc = col + dc[direction];

			// 범위를 넘어가면 다른 방향
			if (nr < 0 || nc < 0 || nr >= roomSize || nc >= roomSize)
				continue;

			// 방문한 곳이면 다음 방향 탐색
			if (visited[nr][nc])
				continue;

			// 나보다 1 작은 곳이 아니면 다른 방향
			if (rooms[row][col] - 1 != rooms[nr][nc])
				continue;

			dfs(nr, nc, move + 1);
		}
		
		// 방문 표시 해제
		visited[row][col] = false;
		
	}

}
