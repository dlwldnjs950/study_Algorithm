import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 1 x N 크기의 미로
	 * 각 칸에는 정수가 쓰여있다 = 한 번에 최대 점프할 수 있는 칸 수
	 * 		3번째칸에 3이 적혀있다면, 4,5,6번 칸 중 하나로 점프할 수 있다.
	 * 
	 * 가장 왼쪽에서 가장 오른쪽으로 갈 때, 최소 점프 횟수는?
	 * 갈 수 없는 경우엔 -1
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 미로 크기
		int size = Integer.parseInt(br.readLine().trim());
		
		// 미로 정보
		int map[] = new int[size + 1];
		
		st = new StringTokenizer(br.readLine().trim());
		
		for(int idx = 1; idx<= size; idx++) {
			map[idx] = Integer.parseInt(st.nextToken());
		}
		
		// int[]에서 0:갈 위치, 1:점프 횟수
		Queue<int[]> queue = new ArrayDeque<>();
        
		boolean visited[] = new boolean[size+1];
		
		int minCnt = Integer.MAX_VALUE;
		
		visited[1] = true;
		queue.add(new int[] {1,0});
		
		while (!queue.isEmpty()) {
			
			int[] current = queue.poll();
			
			// 마지막 칸이면 끝내기
			if(current[0] == size) {
				minCnt = Math.min(minCnt, current[1]);
				break;
			}
			
			for(int limit = 1; limit<= map[current[0]]; limit++) {
				
				int next = current[0] + limit;
				
				// 미로 범위 밖이면 넘어가기
				if(next > size)
					break;
				
				if(visited[next])
					continue;

				visited[next] = true;
				queue.add(new int[] { next, current[1] + 1 });
			}

		}
		
		// 다 돌고 나왔는데 마지막 칸이 아니면 -1 출력
		if(minCnt != Integer.MAX_VALUE)
			sb.append(minCnt);
		else
			sb.append(-1);
		
		System.out.println(sb);
		

	}

}
