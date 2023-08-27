import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 4013_특이한자석
	 * 
	 * 	[설명]
	 * 날 8개가 달려있는 자석 4개
	 * 자석 회전할 때, 서로 붙어있는 날 자성이 서로 다르면 반대 방향으로 회전
	 * 
	 * 맨 위 날이 S극이면 각각 1,2,4,8 점 획득
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 개수
	 * ===> 회전 횟수 K
	 * ===> 4개의 자석 각각의 날 정보
	 * ===> 회전할자석번호 회전방향
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * LinkedList
	 * 날 번호가 0번부터라면, 2번과 6번이 붙어있게된다
	 * 회전되는지 확인하고 한번에 회전시키기
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int MAGNET_CNT = 4;
	static final int BLADE_CNT = 8;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 회전 횟수
			int K = Integer.parseInt(br.readLine().trim());
			
			// 자석 정보
			List<List<Integer>> magnet = new LinkedList<>();
			for (int rIdx = 0; rIdx < MAGNET_CNT; rIdx++) {
				magnet.add(new LinkedList<>());
				st = new StringTokenizer(br.readLine().trim());
				for (int cIdx = 0; cIdx < BLADE_CNT; cIdx++) {
					magnet.get(rIdx).add(Integer.parseInt(st.nextToken()));
				}
			}
			
			// 회전 시키기
			for (int turnCnt = 0; turnCnt < K; turnCnt++) {
				
				st = new StringTokenizer(br.readLine().trim());
				// 회전할 자석 번호와 회전 방향
				int mNum = Integer.parseInt(st.nextToken()) - 1;
				int direction = Integer.parseInt(st.nextToken());
				
				int[] dc = {-1, 1};
				
				// 돌리는 자석에 따라 돌아갈 방향 결정하기
				int[] turnState = new int[MAGNET_CNT];
				boolean[] turnPossible = new boolean[MAGNET_CNT];
				
				Queue<Integer> q = new ArrayDeque<>();
				q.offer(mNum);
				turnState[mNum] = direction;
				turnPossible[mNum] = true;
				
				while(!q.isEmpty()) {
					int tmp = q.poll();

					for(int idx = 0; idx<dc.length; idx++) {
						int nc = tmp + dc[idx];
						
						// 범위를 넘으면 넘어가고
						if(nc<0 || nc >= MAGNET_CNT)
							continue;
						// 이미 방문했으면 넘어가고
						if(turnState[nc] != 0)
							continue;
						// 해당 인덱스의 자석 방향 결정
						q.offer(nc);
						// 돌 수 있다면 돌 방향을 결정
						turnState[nc] = turnState[tmp] * -1;
						// 돌 수 있는지 판단
						int left = Math.min(tmp, nc);
						int right = Math.max(tmp, nc);
						// 서로 극이 다르고 근처도 돌아야 돌수있다
						if(magnet.get(left).get(2) != magnet.get(right).get(6) && turnPossible[tmp])
							turnPossible[nc] = true;
					}
				}
				//System.out.println(Arrays.toString(turnState));
				//System.out.println(Arrays.toString(turnPossible));
				
				// 실제로 돌리기
				for(int idx = 0; idx<MAGNET_CNT; idx++) {
					// 돌 수 있으면
					if(turnPossible[idx]) {
						// 오른쪽 회전
						if(turnState[idx] == 1) {
							// 맨 뒤 날이 맨 앞 날이 된다
							int last = magnet.get(idx).remove(BLADE_CNT-1);
							magnet.get(idx).add(0, last);
							
						// 왼쪽 회전 
						}else if(turnState[idx] == -1) {
							// 맨 앞 날이 맨 뒤 날이 된다
							int first = magnet.get(idx).remove(0);
							magnet.get(idx).add(first);
						}
					}
				}// 실제로 돌리기 끝
			}// 회전 명령 끝
			
			int result = 0;
			for(int idx = 0; idx<MAGNET_CNT; idx++) {
				// 맨 위 날이 S극이라면 점수 획득
				if(magnet.get(idx).get(0) == 1) {
					result+=Math.pow(2, idx);
				}
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}

}
