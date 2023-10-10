import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	/**
	 * 미세먼지 양 실시간 모니터링 시스템
	 * 
	 * 집 크기 RxC
	 * 
	 * 공기청정기
	 * 		항상 1번 열에 위치
	 * 		두 행 차지
	 * 		가장 윗행, 아랫행과 두 칸 이상 떨어져 있다
	 * 
	 * 미세먼지
	 * 		공기청정기 설치되지 않은 곳
	 * 		미해당 칸 미세먼지 양 Ar,c
	 * 
	 * 미세먼지 확산
	 * 		모든 칸 동시에
	 * 		인접한 네 방향
	 * 		공기청정기나 공간밖이면 확산X
	 * 		확산양 : A/5 (소수점버림)
	 * 		남은양 : A - (A/5)*확산된방향개수
	 * 
	 * 공기청정기 작동
	 * 		위쪽은 반시계방향 순환
	 * 		아래쪽은 시계방향 순환
	 * 		공기청정기로 들어가면 미세먼지 제거
	 * 
	 * T초가 지난 뒤 방에 남아있는 미세먼지 양?
	 * 
	 * 미세먼지 확산 : PriorityQueue & BFS
	 *				(r,c)에 남은 양 계산해야 하니까 
	 *				같은 시간만큼 반복문 먼저
	 *				이동 횟수로 남은 양 계산
	 *				확산된 합
	 * 
	 * 먼지 회전 = 중간은 돌지 않고, 가장 바깥 네모 구역만 회전
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static class Dust implements Comparable<Dust>{
		int r, c;
		//int amount;
		int time;

		public Dust(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Dust [r=" + r + ", c=" + c + ", time=" + time + "]";
		}

		// 우선순위큐, 시간기준 오름차순
		@Override
		public int compareTo(Dust o) {
			return this.time - o.time;
		}

	}
	
	static int height, width;
	static final int AIRCLEANER = -1;
	static PriorityQueue<Dust> queue;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 집 크기, 시간
		st = new StringTokenizer(br.readLine().trim());

		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		int limitTime = Integer.parseInt(st.nextToken());

		// 집 정보
		int[][] map = new int[height][width];
		// 공기청정기 위치
		int[] aircleanerRow = new int[2]; // 0:위쪽, 1:아래쪽

		// 탐색에 사용할 우선순위 큐
		queue = new PriorityQueue<>();
		
		int acIdx = 0;
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {

			st = new StringTokenizer(br.readLine().trim());

			for (int colIdx = 0; colIdx < width; colIdx++) {

				map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());

				// 공기청정기라면 행 위치 표시
				if (map[rowIdx][colIdx] == AIRCLEANER) {
					aircleanerRow[acIdx++] = rowIdx;
					//map[rowIdx][colIdx] = 0;
				}
				
			}
		} // 맵정보 입력 끝

		// 우, 하, 좌, 상
		int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };

		// T초간 활동
		for (int time = 0; time < limitTime; time++) {

			// 확산 전 미세먼지 정보 복사
			int[][] copyMap = copyMap(map);
			
			// 방문 여부
			boolean[][] visited = new boolean[height][width];
			
			// 미세먼지 확산
			int nowDustSize = queue.size();
			
			for (int loop = 0; loop < nowDustSize; loop++) {
				Dust current = queue.poll();
				visited[current.r][current.c] = true;
				
				// 확산 횟수
				int diffusionCnt = 0;
				for(int direction=0; direction < dr.length; direction++) {
					
					int nr = current.r + dr[direction];
					int nc = current.c + dc[direction];
					
					// 범위 넘어가면
					if(nr<0 || nc<0 || nr>=height || nc>=width)
						continue;

					// 만약 공기청정기가 있다면 확산하지 않음
					if(map[nr][nc] == AIRCLEANER)
						continue;
					
					// 확산양
					map[nr][nc] += copyMap[current.r][current.c] / 5;
					diffusionCnt++;

				}

				// 남은양 : A - (A/5)*확산된방향개수
				map[current.r][current.c] -= (copyMap[current.r][current.c] / 5) * diffusionCnt;
				if (map[current.r][current.c] < 0)
					map[current.r][current.c] = 0;
			}

			// 공기청정기 작동
			// 위쪽
			int row = aircleanerRow[0];
			int col = 0;
			int pre = 0;
			for (int direction = 0; direction < dr.length; direction++) {

				while (true) {
					int nr = row - dr[direction];
					int nc = col + dc[direction];
					
					// 범위 확인
					if(nr<0 || nc<0 || nr>aircleanerRow[0] || nc>=width)
						break;

					if (nr == aircleanerRow[0] && nc == 0)
						break;
					
					int next = map[nr][nc];
					map[nr][nc] = pre;
					pre = next;
					row = nr;
					col = nc;

				}
			}
			
			// 아래쪽
			row = aircleanerRow[1];
			col = 0;
			pre = 0;
			for (int direction = 0; direction < dr.length; direction++) {

				while (true) {
					int nr = row + dr[direction];
					int nc = col + dc[direction];
					
					// 범위 확인
					if(nr<0 || nc<0 || nr>=height || nc>=width)
						break;

					if (nr == aircleanerRow[1] && nc == 0)
						break;
					
					int next = map[nr][nc];
					map[nr][nc] = pre;
					pre = next;
					row = nr;
					col = nc;

				}
			}
		}

//		for (int[] tmp : map) {
//			System.out.println(Arrays.toString(tmp));
//		}

		int result = 0;
		for (int[] tmp : map) {
			for (int tmp2 : tmp) {
				if (tmp2 == -1)
					continue;
				result += tmp2;
			}
		}

		System.out.println(result);
	}

	private static int[][] copyMap(int[][] map) {
		int tmp[][] = new int[height][width];

		for (int row = 0; row < height; row++) {
			for (int col = 0; col<width; col++) {
				// 먼지 담기
				if(map[row][col]>0) {
					queue.add(new Dust(row, col, 0));
				}
				tmp[row][col] = map[row][col];
			}
		}
		return tmp;
	}

}
