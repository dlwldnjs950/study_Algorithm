import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 11315_오목 판정
	 * 
	 * 	[설명]
	 * 돌이 있거나 없거나
	 * 가로 세로 대각선 하나의 방향으로
	 * 다섯개 이상 연속한 부분이 있는지 확인
	 * 
	 * o : 돌
	 * . : 없음
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 수 T
	 * (각 테스트 케이스)
	 * ===> 오목 판 크기 N
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int N;
	static char map[][];
	
	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 오목판 크기 N
			N = Integer.parseInt(br.readLine().trim());
			map = new char[N][];
			for(int rIdx = 0; rIdx<N; rIdx++) {
				map[rIdx] = br.readLine().trim().toCharArray();
			}
			
			// 왼쪽아래, 아래, 오른쪽아래, 오른쪽 방향만 확인
			int[] dr = { 1, 1, 1, 0}, dc = { -1, 0, 1, 1 };
			boolean flag = false;
			for (int rIdx = 0; rIdx < N; rIdx++) {
				for (int cIdx = 0; cIdx < N; cIdx++) {
					for (int idx = 0; idx < dr.length; idx++) {
						// 해당 칸이 오목일 경우에 확인
						if (map[rIdx][cIdx] == 'o') {
							int nr = rIdx + dr[idx];
							int nc = cIdx + dc[idx];

							// 범위 밖이면 다음 방향
							if (nr < 0 || nc < 0 || nr >= N || nc >= N)
								continue;
							// 오목이 놓여있지 않으면 다음 방향
							if (map[nr][nc] != 'o')
								continue;
							// 해당 방향에 대해 dfs
							if (dfs(rIdx, cIdx, dr[idx], dc[idx], 1)) {
								flag = true;
							}
						}
					}
				}
			}
			if(flag)
				sb.append("YES");
			else
				sb.append("NO");
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static boolean dfs(int row, int col, int dirR, int dirC, int cnt) {
		// 돌 5개 됐으면 합격~!
		if(cnt>=5)
			return true;
		
		int nr = row+dirR;
		int nc = col+dirC;
		
		// 범위 밖이면 다음 방향
		if(nr<0 || nc<0 || nr>=N || nc>=N)
			return false;
		if(map[nr][nc] != 'o')
			return false;
		// 해당 방향에 대해 마저 확인
		return dfs(nr, nc, dirR, dirC, cnt + 1);
	}

}
