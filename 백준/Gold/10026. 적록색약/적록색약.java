import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 10026_적록색약
	 * 
	 * 	[설명]
	 * N*N인 그리드의 각 칸에 
	 * RGB 중 하나를 색칠한 그림
	 * 
	 * 그림은 몇개의 구역으로 나누어 짐
	 * 구역은 같은 색
	 * 같은 색상이 상하좌우로 인접한 경우 두 글자는 같은 구역
	 * (색상 차이를 거의 느끼지 못하는 경우도 같은 색상 : 빨강-초록)
	 * 
	 * 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때, 구역의 수 
	 * 
	 * 	[입력]
	 * ===> 그림 크기 N
	 * (N줄의 입력)
	 * ===> 그림 정보 (길이 N의 문자열. 공백 없음)
	 * 
	 * 	[출력]
	 * 적록 색약 아닌 사람이 보는 구역 수
	 * 적록 색약인 사람이 보는 구역 수 공백으로 구분하여 출력
	 * 
	 * 	[풀이방법]
	 * 덩어리 개수 카운트
	 * 적록색약의 경우 R이나 G를 탐색할 때 G나 R도 같이 카운트
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static char painting[][];
	static int N, visited[][];
	
	// 인접한 경우가 연결된 경우
	// 우 하 좌 상
	static int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 그림 크기 N
		N = Integer.parseInt(br.readLine().trim());

		// 그림 정보
		painting = new char[N][];
		for(int rIdx = 0; rIdx<N; rIdx++) {
			painting[rIdx] = br.readLine().trim().toCharArray();
		}
		
		visited = new int[N][N];	// 구역 번호 표시. 0이면 아직 구역에 포함 X
		int areaNum=0;
		for(int rIdx = 0; rIdx<N; rIdx++) {
			for(int cIdx = 0; cIdx<N; cIdx++) {
				// 아직 방문하지 않았으면 DFS 돌기
				if(visited[rIdx][cIdx] == 0)
					dfs(rIdx, cIdx, ++areaNum);
				// 적록색맹 아닌거 돌 때, 문자 바꾸기
				if(painting[rIdx][cIdx]=='G')
					painting[rIdx][cIdx] = 'R';
			}
		}
		sb.append(areaNum).append(" ");
		
		areaNum=0;
		visited = new int[N][N];
		for(int rIdx = 0; rIdx<N; rIdx++) {
			for(int cIdx = 0; cIdx<N; cIdx++) {
				// 아직 방문하지 않았으면 DFS 돌기
				if(visited[rIdx][cIdx] == 0)
					dfs(rIdx, cIdx, ++areaNum);
			}
		}
		sb.append(areaNum);
		
		System.out.println(sb);
	}

	private static void dfs(int row, int col, int areaNum) {
		visited[row][col] = areaNum;
		// 4방 탐색해서 같은 색상이면 이동
		for (int dIdx = 0; dIdx < dr.length; dIdx++) {
			int nr = row + dr[dIdx];
			int nc = col + dc[dIdx];
			
			// 그림 구역 내가 아니면 다른 방향
			if(nr<0 || nc<0 || nr>=N || nc>=N)
				continue;
			// 같은 색상이 아니고, 이미 방문한 곳이면 다른 방향
			if(painting[row][col] != painting[nr][nc] || visited[nr][nc]!=0)
				continue;
			dfs(nr, nc, areaNum);
		}
	}
	
	
}
