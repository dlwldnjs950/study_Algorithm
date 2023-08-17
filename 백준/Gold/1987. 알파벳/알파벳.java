import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 1987_알파벳
	 * 
	 * 	[설명]
	 * 세로 R 가로 C 보드 
	 * (행열 번호의 시작은 1)
	 * 1행 1열에 말 놓여있음
	 * 말은 상하좌우로 이동 가능
	 * 		단, 각 칸에 대문자 알파벳이 있는데
	 * 		지금까지 지나온 알파벳과는 달라야 한다
	 * 
	 * 	[입력]
	 * ===> R과 C (공백 구분)
	 * (R줄의 입력)
	 * ===> 보드 정보 (문자열 길이 C, 공백 구분 안됨)
	 * 
	 * 	[출력]
	 * 말이 지날 수 있는 최대 칸의 수 출력
	 * 
	 * 	[풀이방법]
	 * 알파벳 만났는지 표시할 배열
	 * 델타 배열
	 * 최대 칸 수니까 깊이 우선 탐색
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int R, C, maxCnt;
	static boolean met[];
	static char board[][];

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 세로 R 가로 C
		st = new StringTokenizer(br.readLine().trim());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 보드 정보 저장
		board = new char[R][C];

		for(int rIdx = 0; rIdx<R; rIdx++) {
			String tmp = br.readLine().trim();
			for(int cIdx = 0; cIdx<C; cIdx++) {
				board[rIdx][cIdx] = tmp.charAt(cIdx);
			}
		}
		
		// 만났던 알파벳 표시 배열
		met = new boolean['Z'-'A'+1];
		// 1행1열에서 출발하는데 2차원 배열에선 0행0열이다
		// 알파벳 방문표시하고 DFS
		met[board[0][0]-'A'] = true;
		maxCnt = Integer.MIN_VALUE;
		dfs(0,0,1);
		System.out.println(maxCnt);
		
		System.out.println(sb);
	}

	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	private static void dfs(int r, int c, int cnt) {
		// 가장 많이 이동한 경우 저장
		if(cnt>maxCnt)
			maxCnt = cnt;
		for (int dIdx = 0; dIdx < dr.length; dIdx++) {
			// 다음 이동할 칸 확인
			int nr = r + dr[dIdx];
			int nc = c + dc[dIdx];

			// 범위를 벗어나면 안됨
			if (nr < 0 || nc < 0 || nr >= R || nc >= C)
				continue;

			// 만났던 알파벳이면 안됨
			if (met[board[nr][nc] - 'A'])
				continue;

			met[board[nr][nc]-'A'] = true;
			dfs(nr, nc, cnt+1);
			// 다시 되돌아 왔으면 방문 표시 해제
			met[board[nr][nc]-'A'] = false;
			// 그냥 상하좌우를 확인하고 돌면 더 많이 움직이는 경우를 제대로 찾지 못한다.
		}
	}

}
