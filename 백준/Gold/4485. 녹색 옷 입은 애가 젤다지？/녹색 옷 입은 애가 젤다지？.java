import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * (0,0)에서 출발 (크기-1,크기-1)로 나감
	 * 각 칸에 잃는 금액 적혀있음
	 * 상하좌우 인접한 1칸씩 이동할 수 있음
	 * 
	 * 최소로 잃는 금액?
	 * 
	 * 적혀있는 금액 = 가중치
	 * 가중치 최소 경로 찾기 ( 다익스트라 )
	 * 
	 */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int loop=1;
		while(true) {
			int mapSize = Integer.parseInt(br.readLine().trim());
			
			if(mapSize == 0)
				break;
			
			int[][] map = new int[mapSize][mapSize];
			
			// 맵정보
			for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
				
				st = new StringTokenizer(br.readLine().trim());
				
				for (int colIdx = 0; colIdx < mapSize; colIdx++) {
					map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 다익스트라에 필요한 배열
			// 시작점에서 자신으로 오는 최단 거리
			int[][] costs = new int[mapSize][mapSize];
			// 방문 처리
			boolean[][] visited = new boolean[mapSize][mapSize];

			// 못간다는 의미로 INF 채워두고
			final int INF = Integer.MAX_VALUE;
			for (int[] tmp : costs) {
				Arrays.fill(tmp, INF);
			}

			// 델타 배열
			int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

			PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					return o1[2] - o2[2];
				}
			});
			queue.add(new int[] { 0, 0, map[0][0] });
			
			while(!queue.isEmpty()) {
				
				int[] current = queue.poll();
				
				if(current[0] == mapSize-1 && current[1]== mapSize-1) {
					sb.append("Problem ").append(loop++).append(": ").append(current[2]).append("\n");
					break;
				}

				for (int direction = 0; direction < dr.length; direction++) {

					int nr = current[0] + dr[direction];
					int nc = current[1] + dc[direction];

					if (nr < 0 || nc < 0 || nr >= mapSize || nc >= mapSize)
						continue;

					if (current[2] + map[nr][nc] < costs[nr][nc]) {
						costs[nr][nc] = current[2] + map[nr][nc];
						queue.add(new int[] { nr, nc, current[2] + map[nr][nc] });
					}
				}
			}
			
		}
		
		System.out.println(sb);
	}

}
