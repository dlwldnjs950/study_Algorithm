import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4485_녹색옷입은애가젤다지 {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception, IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		for(int loop = 1; ; loop++) {
			int mapSize = Integer.parseInt(br.readLine().trim());
			
			if(mapSize == 0)
				break;
			
			// 맵정보
			int[][] map = new int[mapSize][mapSize];
			for(int rIdx = 0; rIdx<mapSize; rIdx++) {
				
				st = new StringTokenizer(br.readLine().trim());
				for(int cIdx = 0; cIdx<mapSize; cIdx++) {
					map[rIdx][cIdx] = Integer.parseInt(st.nextToken());
				}
			}
			
			// cost를 따로 선언한 이유 = 해당 map의 도둑루피값이 보존되어야 하니까
			int[][] costs = new int[mapSize][mapSize];
			final int INF = Integer.MAX_VALUE;
			for(int[] tmp : costs) {
				Arrays.fill(tmp, INF);
			}
			
			// delta array
			int[] dr = { -1, 1, 0, 0, }, dc = { 0, 0, -1, 1 };
			
			PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {

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
					sb.append("Problem ").append(loop).append(": ").append(current[2]).append("\n");
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
