import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Cluster implements Comparable<Cluster>{
		int num;
		int row;
		int col;
		int number;
		int direction;
		int time;
		
		public Cluster(int num, int row, int col, int number, int direction, int time) {
			this.num = num;
			
			this.row = row;
			this.col = col;
			
			this.number = number;
			this.direction = direction;
			this.time = time;
		}

		@Override
		public int compareTo(Cluster o) {
			if (this.time != o.time)
				return this.time - o.time;
			else {
				if (this.num != o.num)
					return this.num - o.num;
				else
					return o.number - this.number;
			}
		}

		@Override
		public String toString() {
			return "Cluster [num=" + num + ", row=" + row + ", col=" + col + ", number=" + number + ", direction="
					+ direction + ", time=" + time + "]";
		}
		
	}
	
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 셀의 개수, 격리 시간, 군집 개수
			st = new StringTokenizer(br.readLine().trim());
			
			int cellSize = Integer.parseInt(st.nextToken());
			int limitTime = Integer.parseInt(st.nextToken());
			int clusterNum = Integer.parseInt(st.nextToken());
			
			// 방문에 사용할 큐
			PriorityQueue<Cluster> q = new PriorityQueue<>();
			
			for(int loop=0; loop<clusterNum; loop++) {
				st = new StringTokenizer(br.readLine().trim());
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				int number = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken()) - 1;
				
				Cluster tmp = new Cluster(row * cellSize + col, row, col, number, direction, 0);
				q.add(tmp);
				
			}
			
			// 격리 시간 만큼 확인
			for(int loop = 0; loop<limitTime; loop++) {
				//System.out.println(loop+"..........");
				
				// 같은 시간 내에서 같은 셀에 가는지 확인해야 하기 때문
				int size = q.size();
				for(int sameTime = 0; sameTime<size; sameTime++) {
					
					// 지금 확인할 군집
					Cluster current = q.poll();
					
					// 다음 확인할 군집
					Cluster next = q.peek();
					
					// 둘이 같은 cell에 존재한다면
					// 군집수가 합쳐지고, 군집수가 많은 쪽의 방향
					if(current.num == next.num) {
						next.direction = current.number > next.number ? current.direction : next.direction;						
						next.number += current.number;
						
						// 같은 위치에 존재하려 했으면 군집만 합치기
						continue;
					}
					//System.out.println(current);
					int nr = current.row + dr[current.direction];
					int nc = current.col + dc[current.direction];
					
					// 가려는 방향이 맵의 가장자리면 방향 바뀜, 군집 절반으로 줄어듦
					if (nr == 0 || nc == 0 || nr == cellSize - 1 || nc == cellSize - 1) {
						current.direction = current.direction + (int)Math.pow(-1, current.direction);
						current.number /=2;
					}
					
					q.add(new Cluster(nr * cellSize + nc, nr, nc, current.number, current.direction, current.time +1));
				}
			}
			
			int result = 0;
			
			while(!q.isEmpty()) {
				//System.out.println(q.peek());
				result+=q.poll().number;
			}
			
			sb.append(result).append("\n");

		}
		System.out.println(sb);
	}

}
