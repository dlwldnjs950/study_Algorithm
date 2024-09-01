import java.io.*;
import java.util.*;

public class Main {
	
	
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine().trim());
		
		int soobin = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		
		boolean[] visited = new boolean[100001];
		// 해당 지점까지 걸린 최소시간 관리
		int[] times = new int[100001];
		Arrays.fill(times, Integer.MAX_VALUE);
		Queue<Integer> queue = new ArrayDeque<>();

		// 출발 ㄱ지점
		visited[soobin] = true;
		times[soobin] = 0;
		queue.add(soobin);

		int time = 0;
		while (!queue.isEmpty()) {
			int current = queue.poll();

			if (current == target) {
				break;
			}

			// 순간 이동 먼저
			// 순간이동에는 시간이 걸리지 않는다.
			if (current * 2 < 100001 && !visited[current * 2]) {
				visited[current * 2] = true;
				times[current * 2] = Math.min(times[current], times[current * 2]);
				queue.add(current * 2);
			}
			// 걷기
			if (current - 1 >= 0 && !visited[current - 1]) {
				visited[current - 1] = true;
				times[current - 1] = Math.min(times[current] + 1, times[current - 1]);
				queue.add(current - 1);
			}
			//걷기
			if (current + 1 < 100001 && !visited[current + 1]) {
				visited[current + 1] = true;
				times[current + 1] = Math.min(times[current] + 1, times[current + 1]);
				queue.add(current + 1);
			}

		}
			
		System.out.println(times[target]);
	}


	
}
