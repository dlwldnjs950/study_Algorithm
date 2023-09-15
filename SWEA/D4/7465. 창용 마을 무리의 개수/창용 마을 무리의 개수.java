import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	/**
	 * 	# 7465_창용 마을 무리의 개수
	 * 
	 * 	[ 설명 ]
	 * N명의 사람 (1번 ~ N번)
	 * 서로 아는 관계거나, 몇사람 거쳐 알 수 있는 관계라면 묶어서 하나의 무리
	 * 
	 * 창용 마을에 몇개의 무리가 존재하는가?
	 * 
	 * 	[ 입력 ]
	 * ===> 테스트 케이스 수
	 * ===> 창용마을 사람 수와 서로 알고 있는 사람 관계 수
	 * ===> 서로 알고있는 두 사람의 번호
	 * 
	 * 	[ 풀이 방법 ]
	 * 서로 알고있는 관계 = 무향 그래프 관계
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스 개수
		int tc = Integer.parseInt(br.readLine().trim());
		
		for(int testCase =1; testCase<=tc; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			// 사람 수와 관계 정보
			st = new StringTokenizer(br.readLine().trim());

			int pNumber = Integer.parseInt(st.nextToken());
			int compareCnt = Integer.parseInt(st.nextToken());

			// 사람 연결 정보 저장
			List<List<Integer>> connectMap = new ArrayList<>();

			for (int idx = 0; idx <= pNumber; idx++) {
				connectMap.add(new ArrayList<>());
			}
			
			// 연결 정보 저장
			for(int loop=0; loop<compareCnt; loop++) {
				st = new StringTokenizer(br.readLine().trim());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				connectMap.get(from).add(to);
				connectMap.get(to).add(from);
			}
			
			int visited[] = new int[pNumber + 1];
			
			Queue<Integer> queue = new ArrayDeque<>();
			
			int num = 0;
			for(int idx = 1; idx<=pNumber; idx++) {
				if(visited[idx] != 0)
					continue;
				queue.offer(idx);
				visited[idx] = ++num;
				
				while(!queue.isEmpty()) {
					int current = queue.poll();
					
					for(int person : connectMap.get(current)) {
						if(visited[person] != 0)
							continue;
						queue.offer(person);
						visited[person] = num;
					}
				}
			}
			sb.append(num).append("\n");
		}
		
		System.out.println(sb);
	}

}

