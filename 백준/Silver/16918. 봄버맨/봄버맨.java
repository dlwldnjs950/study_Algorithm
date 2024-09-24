import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	// 결과만 보여주면 되니까, 격자칸에 현재 폭탄 진행 시간 표시
	// 0 : 빈칸, 1~3 : 경과 시간

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		int limitTime = Integer.parseInt(st.nextToken());

		char[][] maps = new char[height][width];
		int[][] timeMaps = new int[height][width]; // 폭탄의 폭발 시간 설정

		for (int rIdx = 0; rIdx < height; rIdx++) {
			String rowStr = br.readLine().trim();

			for (int cIdx = 0; cIdx < width; cIdx++) {
				maps[rIdx][cIdx] = rowStr.charAt(cIdx);
				if (maps[rIdx][cIdx] == 'O') {
					// 3초 뒤에 터질 예정
					timeMaps[rIdx][cIdx] = 3;
				}
			}
		}

		int[] dr = { 0, -1, 0, 1 }, dc = { 1, 0, -1, 0 };
		// 반복이 2초부터
		// 짝수초 : 폭탄 설치
		// 홀수초 : 폭발
		for (int time = 2; time <= limitTime; time++) {

			// 짝수 - 폭탄 심기
			if ((time & 1) == 0) {
				for (int rIdx = 0; rIdx < height; rIdx++) {
					for (int cIdx = 0; cIdx < width; cIdx++) {
						
						// 빈칸에만
						if (maps[rIdx][cIdx] == '.') {
							maps[rIdx][cIdx] = 'O';
							timeMaps[rIdx][cIdx] = time + 3;
						}
					}
				}

			// 홀수 - 터트리기
			} else {
				for (int rIdx = 0; rIdx < height; rIdx++) {
					for (int cIdx = 0; cIdx < width; cIdx++) {
						if (timeMaps[rIdx][cIdx] == time) {

							// 해당 폭탄 제거
							maps[rIdx][cIdx] = '.';
							timeMaps[rIdx][cIdx] = 0;

							// 인접 폭탄 제거
							for (int direction = 0; direction < 4; direction++) {
								int nr = rIdx + dr[direction];
								int nc = cIdx + dc[direction];

								if (nr < 0 || nc < 0 || nr >= height || nc >= width)
									continue;

								// 폭탄이고 아직 터질때가 아닌것만
								if (maps[nr][nc] == 'O' && timeMaps[nr][nc] != time) {
									maps[nr][nc] = '.';
									timeMaps[nr][nc] = 0;
								}
							}
						}
					}
				}
			}
		}
		
		// 맵 상태 출력
		for (int rIdx = 0; rIdx < height; rIdx++) {
			for (int cIdx = 0; cIdx < width; cIdx++) {
				sb.append(maps[rIdx][cIdx]);
			}
			sb.append("\n");
		}

		System.out.println(sb);

	}

}
