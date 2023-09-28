import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	/**
	 * 상근이네서 출발
	 * 맥주 한박스 20개
	 * 50미터에 한 병씩 맥주 마심
	 * 
	 * 편의점에서 맥주 구매
	 * 박스에 20개까지만 가능
	 * 	편의점 들렀을 때 20개 채우기
	 * 
	 * 상근이와 친구들은 페스티벌에 행복하게 도착할 수 있을까?
	 * 
	 * 좌표를 기록할게 아니고 맨하탄 거리를 가중치로 생각하고 문제를 풀면 되겠다~!	 */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Point{
		int row;
		int col;
		
		public Point (int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine().trim());
		
		for(int testCase = 0; testCase < tc; testCase++) {
			
			// 편의점 개수
			int convCnt = Integer.parseInt(br.readLine().trim());

			// 각 지점의 좌표를 저장할 자료구조
			List<Point> spot = new ArrayList<>();

			// 상근이네 + 편의점 + 송도
			for (int loop = 0; loop < convCnt + 2; loop++) {
				st = new StringTokenizer(br.readLine().trim());
				spot.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			// 가중치 행렬
			int[][] dist = new int[convCnt + 2][convCnt + 2];
			int[][] possible = new int[convCnt + 2][convCnt + 2];

			for (int i = 0; i < convCnt + 2; i++) {
				for (int j = 0; j < convCnt + 2; j++) {
					dist[i][j] = Math.abs(spot.get(i).row - spot.get(j).row)
							+ Math.abs(spot.get(i).col - spot.get(j).col);
					// 1000 이상이면 어차피 못가니까 0으로 표시
					if(dist[i][j] > 1000)
						dist[i][j] = 0;
				}
			}
			
//			for(int[] tmp:dist) {
//				System.out.println(Arrays.toString(tmp));
//			}
			
			// 어차피 길은 다 이어져 있으니
			// 각 이동이 20*50 = 1000 이하로 이루어진 경로가 존재하면 된다.

			boolean[] visited = new boolean[convCnt + 2];
			Queue<Integer> q = new ArrayDeque<Integer>();
			q.add(0);
			visited[0] = true;
			
			// 집에서 펜타포트까지 갈 수 있는지 확인
			boolean flag = false;
			while(!q.isEmpty()) {
				int current = q.poll();
				if(current == convCnt +1)
					flag = true;
				for (int j = 0; j < convCnt + 2; j++) {
					if(visited[j] || dist[current][j] == 0)
						continue;
					q.add(j);
					visited[j] = true;
				}
				
			}
			
			sb.append(flag?"happy":"sad").append("\n");
		}
		System.out.println(sb);
	}

}
