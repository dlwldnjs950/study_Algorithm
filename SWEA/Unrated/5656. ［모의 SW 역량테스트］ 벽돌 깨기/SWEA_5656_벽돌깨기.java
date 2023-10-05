import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기 {

	/**
	 * 구슬 N번
	 * 벽돌 정보 W x H 배열
	 * 	0은 빈공간
	 * 	1~9는 벽돌
	 * 
	 * 구슬
	 * 	맨 위에 있는 벽돌만 깨트림
	 * 	명중한 벽돌은 상하좌우로 (벽돌에 적힌 숫자 -1) 만큼 같이 제거
	 * 	구슬에 맞지 않았어도 제거될 때 주변 벽돌 제거함
	 * 
	 * 해당 자리에서 벽돌 다 제거한 다음에 벽돌 떨어짐
	 * 
	 * 최대한 많은 벽돌 제거했을 때 남은 벽돌 개수?
	 * 
	 * 더이상의 폭발이 없는 경우 다음 구슬 확인해야하니까 flag 변수 필요
	 * 구슬 떨어트릴 열 고르기(중복 순열-순서에 의미 있음)
	 * 남아있는 벽돌 카운트 함수 (또는 제거하면서 카운트...)
	 * 폭발 후 맵 정보 갱신 함수 => 2개 같이하면 되겠네
	 * 각 열의 최대 높이 저장 배열
	 * 
	 */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int shotCnt, mapWidth, mapHeight, map[][], maxHeight[], minBricks;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 테스트 케이스 개수
		int TC = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= TC; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 구슬 쏘는 횟수, 벽돌맵 너비, 벽돌맵 높이
			st = new StringTokenizer(br.readLine().trim());
			shotCnt = Integer.parseInt(st.nextToken());
			mapWidth = Integer.parseInt(st.nextToken());
			mapHeight = Integer.parseInt(st.nextToken());

			// 벽돌맵 정보
			map = new int[mapHeight+1][mapWidth];

			// 맵 각 열의 최고 높이
			maxHeight = new int[mapWidth];
			Arrays.fill(maxHeight, -1);

			for (int rowIdx = 0; rowIdx < mapHeight; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());

				for (int colIdx = 0; colIdx < mapWidth; colIdx++) {
					map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());

					// 아직 max를 모르고 map에 0이 아닌 값이라면
					if (map[rowIdx][colIdx] != 0 && maxHeight[colIdx] == -1)
						maxHeight[colIdx] = rowIdx; // 최고높이 표시
				}
			}
			//System.out.println(Arrays.toString(maxHeight));

			// 구슬 떨어트리기
			minBricks = Integer.MAX_VALUE;
			shotMarble(0);

			sb.append(minBricks);
			sb.append("\n");
		}

		System.out.println(sb);
	}

	// 구슬 떨어트릴 위치 선택하기
	private static void shotMarble(int cnt) {

		if (cnt == shotCnt) {
			// 최대한 많은 벽돌을 제거하는게 목표
			// 남아있는 벽돌 카운트 = maxHeight 배열 값 합
			int result = 0;
			for (int tmp : maxHeight) {
				result += mapHeight - tmp;
			}

			minBricks = Math.min(minBricks, result);
			return;
		}

		for (int idx = 0; idx < mapWidth; idx++) {

			// 벽돌 제거하기 전에 맵 상태 복사
			int[][] copyMap = copyMap(map);
			int[] copyMaxHeight = maxHeight.clone();

			// 해당 열에 구슬 떨어트리고
			destroyBricks(idx);
			//System.out.println(Arrays.toString(maxHeight));
			// 다음 떨어트릴 위치
			shotMarble(cnt + 1);

			// 다른 열 확인해야 하니까 제거된 벽돌 되돌리기
			map = copyMap;
			maxHeight = copyMaxHeight;
		}
	}

	// 맵 복사하기
	private static int[][] copyMap(int[][] originMap) {
		int[][] tmp = new int[mapHeight][mapWidth];

		for (int rowIdx = 0; rowIdx < mapHeight; rowIdx++) {
			for (int colIdx = 0; colIdx < mapWidth; colIdx++) {
				tmp[rowIdx][colIdx] = originMap[rowIdx][colIdx];
			}
		}

		return tmp;
	}

	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	// 벽돌 제거하기
	private static void destroyBricks(int colIdx) {

		if (maxHeight[colIdx] == mapHeight)
			return;

		// 벽돌을 제거하다가 0이 아닌 숫자를 만나면 추가 제거 => 큐 (행0, 열1, 범위2)
		Queue<int[]> q = new ArrayDeque<>();

		q.add(new int[] { maxHeight[colIdx], colIdx, map[maxHeight[colIdx]][colIdx] });
		map[maxHeight[colIdx]][colIdx] = 0;

		while (!q.isEmpty()) {
			int[] current = q.poll();

			for (int distance = 0; distance < current[2]; distance++) {
				for (int direction = 0; direction < dr.length; direction++) {
					int nr = current[0] + dr[direction] * distance;
					int nc = current[1] + dc[direction] * distance;

					// 범위 넘어가면 패스
					if (nr < 0 || nc < 0 || nr >= mapHeight || nc >= mapWidth)
						continue;

					// 0보다 크면 큐에 넣고 제거 표시
					if (map[nr][nc] > 0) {
						q.add(new int[] { nr, nc, map[nr][nc] });
						map[nr][nc] = 0;
					}
				}
			}
		}

		// 다 제거했으면 벽돌 내리기
		// maxHeight 갱신하기
		downBricks();
	}

	private static void downBricks() {

		// 각 열의 맨 밑 행부터 살펴보면서
		for (int colIdx = 0; colIdx < mapWidth; colIdx++) {
			int idx = mapHeight - 1;

			// 최고 높은 행 까지만 살펴보면 된다
			for (int rowIdx = mapHeight - 1; rowIdx >= maxHeight[colIdx]; rowIdx--) {

				if (map[rowIdx][colIdx] > 0) {
					map[idx--][colIdx] = map[rowIdx][colIdx];
					if (rowIdx <= idx)
						map[rowIdx][colIdx] = 0;
				}
			}
			maxHeight[colIdx] = idx + 1;
		}
	}
}
