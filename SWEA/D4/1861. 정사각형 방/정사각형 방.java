import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 1861_정사각형 방
	 * 
	 * 	[설명]
	 * N^2개의 방이 NxN 형태로 늘어서 있다
	 * 각 방에는 1~N^2의 수가 적혀있다 (다 다름)
	 * 
	 * 	# 상하좌우 이동 규칙
	 * 1. 이동하려는 방 존재 (방 범위 내)
	 * 2. 이동방의 숫자 == 현재방 +1 
	 * 
	 * 가장 많은 방을 방문하려면 처음에 어떤 수가 적힌 방에 있어야 하는가?
	 * 방 개수는 시작방도 포함
	 * 
	 * 이동 가능한 방이 여러개라면 가장 작은 방
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 수 T
	 * (T개의 테스트 케이스)
	 * ===> 방 개수 결정하는 N
	 * (N줄의 방별 숫자, 공백 구분)
	 * ===> 방별 숫자
	 * 
	 * 	[출력]
	 * 처음 출발해야하는 방의 번호와
	 * 최대 몇개의 방을 이동할 수 있는지 출력
	 * 
	 * 	[풀이방법]
	 * 각 좌표에 대해 DFS
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N;
	static int[][] room;
	static boolean[][] visited;
	static int maxCnt, maxRoomNum;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			// 방 개수 결정 N 입력받기
			N = Integer.parseInt(br.readLine().trim());
			
			// 방별 숫자 저장 배열
			room = new int[N][N];
			// 방별 숫자 입력
			for (int rowIdx = 0; rowIdx < N; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int colIdx = 0; colIdx < N; colIdx++) {
					room[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}
			
			//각 좌표에 대해 DFS
			// 방문 여부 표시할 배열
			visited = new boolean[N][N];
			// 가장 많은 방 방문 횟수와 좌표값
			maxRoomNum = 0;
			maxCnt = 0;
			// 각 방에 대해 dfs를 돌린다
			for (int rowIdx = 0; rowIdx < N; rowIdx++) {
				for (int colIdx = 0; colIdx < N; colIdx++) {
					visited[rowIdx][colIdx] = true;
					dfs(rowIdx, colIdx, 1);
					visited[rowIdx][colIdx] = false;
				}
			}

			sb.append(maxRoomNum).append(" ").append(maxCnt).append("\n");
		}
		System.out.println(sb);
	}
	
	// 탐색에 사용할 델타 배열
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	
	// 시작점이 들어가야하는데 도착점이 들어간다 -> 역으로 돌려~!
	
	// 상하좌우에 대해, 딱 1만큼 작은 경우 이동
	static void dfs(int row, int col, int cnt) {
		for(int dIdx = 0; dIdx<4; dIdx++) {
			// 해당 지점까지는 도달한거니까 max 정보 갱신
			if (maxCnt < cnt) {	//더 큰 값이면
				maxRoomNum = room[row][col];
				maxCnt = cnt;
			}else if(maxCnt == cnt) {	// 같은 값인 경우, 방에 적힌 값이 더 작은걸 기억해두자
				if(maxRoomNum > room[row][col]) {
					maxRoomNum = room[row][col];
				}
			}
			// 다음 이동할 좌표 구하기
			int nx = row + dx[dIdx];
			int ny = col + dy[dIdx];
			
			// 좌표가 범위 밖이고, 이미 방문했다면 다음 for문
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny])
				continue;
			if(room[row][col] - 1 != room[nx][ny])
				continue;
			
			visited[nx][ny] = true;
			dfs(nx, ny, cnt + 1);
			visited[nx][ny] = false;
			
		}
	}
}
