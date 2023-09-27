import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	/**
	 * CC : 네트워크 상에서 한 사용자가 다른 모든 사람에게 얼마나 가까운가 노드 i에서 노드 j까지 최단 거리의 합으로 구한다
	 * 
	 * 사람 네트워크에서 사람들의 CC값 중 최솟값 출력
	 */

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= tc; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 사람수 사람네트워크인접행렬(행우선)
			st = new StringTokenizer(br.readLine().trim());

			int humanNum = Integer.parseInt(st.nextToken());

			// 사람네트워크 연결 정보
			int[][] network = new int[humanNum][humanNum];
			for (int rowIdx = 0; rowIdx < humanNum; rowIdx++) {
				for (int colIdx = 0; colIdx < humanNum; colIdx++) {
					network[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}

			int dist[][] = new int [humanNum][humanNum];
			for (int number = 0; number<humanNum; number++) {
				// 방문표시
				boolean visited[] = new boolean[humanNum];
				// 방문 순서 관리
				Queue<Integer> queue = new ArrayDeque<Integer>();
				// 각 정점까지 거리
//				int[] dist = new int[humanNum];
				
				queue.add(number);
				visited[number] = true;
				
				while(!queue.isEmpty()) {
					int current = queue.poll();
					
					for(int n = 0; n<humanNum; n++) {
						
						// 이미 방문 했거나 연결되어 있지 않으면 넘어감
						if(visited[n] || network[current][n] == 0)
							continue;
						queue.add(n);
						visited[n] = true;
						dist[number][n] = dist[number][current] + 1;
					}
				}
				
				
			}
			
			int min = Integer.MAX_VALUE;
			for (int rowIdx = 0; rowIdx < humanNum; rowIdx++) {
				int tmpSum = 0;
				for (int colIdx = 0; colIdx < humanNum; colIdx++) {
					tmpSum += dist[rowIdx][colIdx];
				}
				min = Math.min(min, tmpSum);
			}

			sb.append(min).append("\n");
		}
		System.out.println(sb);

	}

}
