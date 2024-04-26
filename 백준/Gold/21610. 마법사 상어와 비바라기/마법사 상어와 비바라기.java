import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 파이어볼, 토네이도, 파이어스톰, 물복사버그, 비바라기
	 * 
	 * 비바라기 : 하늘에 비구름 만들기
	 * 
	 * NxN인 격자에서 연습할거임
	 * 각 칸에 바구니
	 * 바구니에 물 저장 (2차원 배열에 각 칸 물의양 저장)
	 * 
	 * 1번 행과 N번 행을 연결했고, 1번 열과 N번 열도 연결
	 * 
	 * 비바라기를 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름
	 * 
	 * 구름 이동 M번 명령 (이동 = 방향 + 거리)
	 * 		방향은 8가지 좌 ~>상 ~>우 ~>하 
	 * 
	 * 이동 -> 물의 양 증가 -> 구름 사라져
	 *  -> 물 양 증가한 칸에 물복사버그(대각선방향 물이 있는 바구니 수 만큼 물 양 증가)
	 *  -> 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름 / 물의 양 2 줄어듦 
	 *  	(구름이 사라졌던칸이 아니어야함)
	 *  
	 *  바구니에 들어있는 물의 양의 합
	 * */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	// 그 순간의 길이만큼만 돌아야할지도
	static class Cloud {
		int row;
		int col;

		Cloud(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	// 물의 양 관리
	// 구름 사라짐 여부...
	
	static boolean isDeleted[][];
	static int map[][];
	// 탐색 델타 배열 (좌 상 우 하)
	static final int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 }, dc = { -1, -1, 0, 1, 1, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		int mapSize = Integer.parseInt(st.nextToken());
		int orderNum = Integer.parseInt(st.nextToken());

		// 공간 정보 저장

		map = new int[mapSize][mapSize];

		for (int rIdx = 0; rIdx < mapSize; rIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int cIdx = 0; cIdx < mapSize; cIdx++) {
				map[rIdx][cIdx] = Integer.parseInt(st.nextToken());
			}
		}		

		Queue<Cloud> clouds = new ArrayDeque<>();
		for (int rIdx = 0; rIdx < 2; rIdx++) {
			for (int cIdx = 0; cIdx < 2; cIdx++) {
				clouds.add(new Cloud(mapSize - rIdx -1, cIdx));
			}
		}
		
		// 구름의 이동 명령 수행
		int currentCloudNum = 0;
		for (int order = 0; order < orderNum; order++) {
			isDeleted = new boolean[mapSize][mapSize];
			
			st = new StringTokenizer(br.readLine().trim());
			int direction = Integer.parseInt(st.nextToken()) - 1;
			int distance = Integer.parseInt(st.nextToken());
			currentCloudNum = clouds.size();
			while (currentCloudNum-- > 0) {

				Cloud current = clouds.poll();

				// 구름 이동은 칸이 연결되어있기 때문에...
				int nr = (current.row + (dr[direction] + mapSize) * distance) % mapSize;
				int nc = (current.col + (dc[direction] + mapSize) * distance) % mapSize;
				
				// 2번 행에서 -3이면 원래는 -1인데 3번칸이 됨 :  + mapSize
				// 2번 행에서 +3이면 원래는  5인데 1번칸이 됨 : % mapSize
				
				// 물양 증가, 구름 사라짐
				map[nr][nc] += 1;
				isDeleted[nr][nc] = true;
				clouds.add(new Cloud(nr, nc));
			}
			currentCloudNum = clouds.size();
			while (currentCloudNum-- > 0) {

				Cloud current = clouds.poll();

				// 물 복사 버그
				map[current.row][current.col] += cntOver2Water(current.row, current.col);
			}

			// 구름 생성
			for (int rIdx = 0; rIdx < mapSize; rIdx++) {
				for (int cIdx = 0; cIdx < mapSize; cIdx++) {
					if (!isDeleted[rIdx][cIdx] && map[rIdx][cIdx] >= 2) {
						// 구름 생성
						clouds.add(new Cloud(rIdx, cIdx));
						map[rIdx][cIdx] -= 2;
					}
				}
			}

		}

		int cnt = 0;
		for (int rIdx = 0; rIdx < mapSize; rIdx++) {
			for (int cIdx = 0; cIdx < mapSize; cIdx++) {
				cnt += map[rIdx][cIdx];
			}
		}
		sb.append(cnt);

		System.out.println(sb);

	}

	static int cntOver2Water(int row, int col) {
		int cnt = 0;
		// 델타 1,3,5,7
		for (int idx = 1; idx <= 7; idx += 2) {
			int nr = row + dr[idx];
			int nc = col + dc[idx];

			if (nr < 0 || nc < 0 || nr >= map.length || nc >= map.length)
				continue;
			if (map[nr][nc] > 0)
				cnt++;
		}
		
		return cnt;

	}

}
