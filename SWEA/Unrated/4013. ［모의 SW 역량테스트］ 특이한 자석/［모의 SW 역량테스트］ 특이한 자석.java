import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	/**
	 * 한 판에 4개의 자석,
	 * 각 자석에 날이 8개
	 * 각 날은 N극 또는 S극
	 * 
	 * 각 자석의 상단 날을 화살표가 가리키고 있다
	 * 
	 * K번 회전
	 * 	1칸 회전할 때, 서로 붙어있는 날의 자성이 다를 경우 반대 방향으로 회전
	 * 
	 * 점수 계산
	 * 	화살표가 가리키는 극이 S극이면 2^(자석인덱스번호)점 획득
	 * 
	 * "자석 회전 시키기"
	 * 	DFS
	 * 	회전방향 표시 배열
	 * 
	 * "점수 계산하기"
	 * 
	 * 날의 자성 정보는 인덱스값 비교를 위해 List 자료구조 사용
	 * 	시계방향 회전 : 맨 마지막 원소가 맨 첫번째 원소
	 * 	반시계방향 회전 : 첫번째 원소가 맨 마지막 원소
	 * 
	 * 회전에 따른 연쇄 회전
	 * 	회전하는 자석 기준으로 좌우
	 * 	인덱스 비교해서 자성 서로 다르면 회전
	 * 	인덱스가 0번부터라면 2번과 6번이 붙어있다.
	 * 
	 * 자성은
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int MAGNET_NUM=4, BLADE_NUM=8;
	static final int N=0, S=1;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스 개수
		int TC = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= TC; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 자석 회전 횟수
			int turnLimit = Integer.parseInt(br.readLine().trim());

			// 자석 날 자성 정보
			List<List<Integer>> magnetInfo = new ArrayList<>();
			for (int idx = 0; idx < MAGNET_NUM; idx++) {
				magnetInfo.add(new LinkedList<>());

				st = new StringTokenizer(br.readLine().trim());

				for (int magnetIdx = 0; magnetIdx < BLADE_NUM; magnetIdx++) {
					magnetInfo.get(idx).add(Integer.parseInt(st.nextToken()));
				}
			}

			// turnLimit개의 회전 정보 : 자석 번호와 방향 (시계방향 :1, 반시계방향 : -1);
			for (int loop = 0; loop < turnLimit; loop++) {
				st = new StringTokenizer(br.readLine().trim());

				int magnetNum = Integer.parseInt(st.nextToken()) - 1;
				int turnDirection = Integer.parseInt(st.nextToken());
				
				// 회전 방향 표시 배열
				int[] turnDirections = new int[MAGNET_NUM];
				turnDirections[magnetNum] = turnDirection;
				
				//DFS로 회전  시키기
				decideDirection(magnetNum, turnDirections, magnetInfo);
				
				// 정해진 회전 방향
				//System.out.println(Arrays.toString(turnDirections));
				
				// 회전방향 정하고 나서 실제로 돌리기
				turnMagnet(turnDirections, magnetInfo);
				
			}
			
			// 점수 계산
			int result = 0;
			for(int idx=0; idx<MAGNET_NUM; idx++) {
				if(magnetInfo.get(idx).get(0) == S)
				result += Math.pow(2, idx);
			}
			
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}

	// 자석 돌리기
	private static void turnMagnet(int[] turnDirections, List<List<Integer>> magnetInfo) {
		
		// 시계방향 회전 : 맨 마지막 원소가 맨 첫번째 원소
		// 반시계방향 회전 : 첫번째 원소가 맨 마지막 원소
		for (int idx = 0; idx < MAGNET_NUM; idx++) {
			// 시계방향 회전
			if (turnDirections[idx] == 1) {

				int last = magnetInfo.get(idx).remove(BLADE_NUM - 1);
				magnetInfo.get(idx).add(0, last);
			
			// 반 시계방향 회전
			}else if(turnDirections[idx] == -1) {
				
				int first = magnetInfo.get(idx).remove(0);
				magnetInfo.get(idx).add(first);
			}
		}
	}

	// 회전 방향 결정하기
	private static void decideDirection(int magnetNum, int[] turnDirections, List<List<Integer>> magnetInfo) {

		// 좌우 확인
		int[] dc = {-1, 1};
		
		for(int direction = 0; direction<dc.length; direction++) {
			int nc = magnetNum + dc[direction];

			// 인덱스 범위를 넘으면 넘어간다
			if (nc < 0 || nc >= MAGNET_NUM)
				continue;

			// 이미 회전이 결정 되었으면 넘어가기
			if (turnDirections[nc] != 0)
				continue;

			// 붙어있는 날의 자성이 서로 같으면 넘어가기
			int left = Math.min(magnetNum, nc);	// 왼쪽 자석
			int right = Math.max(magnetNum, nc); // 오른쪽 자석
			
			if (magnetInfo.get(left).get(2) == magnetInfo.get(right).get(6))
				continue;

			// 반대방향 회전이므로 -1을 곱해서 처리
			turnDirections[nc] = turnDirections[magnetNum] * -1;
			decideDirection(nc, turnDirections, magnetInfo);
			
		}
		
	}


}
