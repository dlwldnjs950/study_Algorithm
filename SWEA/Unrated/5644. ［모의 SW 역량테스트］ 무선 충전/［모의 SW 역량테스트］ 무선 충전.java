import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 5644_무선 충전
	 * 
	 * 	[설명]
	 * 10*10 영역의 지도
	 * 
	 * 	# BC (Battery Charger)
	 * 1. 위치 X Y : 범위와 연관
	 * 2. 충전 범위 C : 사용자의 이동 경로가 포함되는지
	 * 		 |XA – XB| + |YA – YB| 이하면 접속
	 * 		 여러 BC가 해당할 수 있음
	 * 		 여러 사용자가 위치할 수 있음
	 * 			BC 하나 사용자 둘 : 성능 나눠가짐
	 * 			BC 둘 사용자 둘 : 하나씩 접속하기
	 * 			BC 둘 사용자 하나 : 성능 좋은거
	 * 3. 성능 P : 충전량 결정
	 * 
	 * 사용자의 이동 궤적
	 * 	0 : 이동하지 않음
	 * 	1 : 상
	 * 	2 : 우
	 * 	3 : 하
	 * 	4 : 좌
	 * 
	 * 모든 사용자가 충전한 양의 합의 최댓값?
	 * 
	 * 사용자 2명
	 * A : (1,1)에서 출발
	 * B : (10,10)에서 출발
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 수 T
	 * ===> 총 이동시간 M, BC 개수 A (공백 구분)
	 * (2줄의 입력)
	 * ===> 사용자 A,B의 시간별 이동 정보 (공백 구분)
	 * (A줄의 입력)
	 * ===> 위치 X, Y, 범위 C, 성능 P (공백 구분)
	 * 
	 * 	[출력]
	 * 충전량의 최대값 출력
	 * 
	 * 	[풀이방법]
	 * 각 좌표에 영향을 끼치고 있는 BC의 리스트를 저장해두고 확인
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int M, A, totalCharge;
	static List<List<List<int[]>>> availableBC;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 총 이동시간 M, BC 개수 A
			st = new StringTokenizer(br.readLine().trim());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			// 사용자 A,B의 시간별 이동 정보
			String[] moveA = br.readLine().trim().split(" ");
			String[] moveB = br.readLine().trim().split(" ");
			
			// A개의 BC의 정보 : 위치 X, Y, 범위 C, 성능 P, BC의 인덱스 번호
			int[][] BC = new int[A][5];
			for(int idx = 0; idx<A; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				BC[idx][0] = Integer.parseInt(st.nextToken());
				BC[idx][1] = Integer.parseInt(st.nextToken());
				BC[idx][2] = Integer.parseInt(st.nextToken());
				BC[idx][3] = Integer.parseInt(st.nextToken());
				BC[idx][4] = idx;
			}

			availableBC = new ArrayList<>();
			// 10*10의 각 좌표에 해당하는 BC 리스트 저장
			for (int rIdx = 0; rIdx < 10; rIdx++) {
				availableBC.add(new ArrayList<>());
				for (int cIdx = 0; cIdx < 10; cIdx++) {
					availableBC.get(rIdx).add(new ArrayList<>());
					for (int idx = 0; idx < A; idx++) {
						// 인덱스에 해당하는 BC가 위치한 좌표
						int bcX = BC[idx][0] - 1;
						int bcY = BC[idx][1] - 1;

						// 인덱스에 해당하는 BC의 범위
						int scope = BC[idx][2];
						// 범위 내라면 ++
						if (calculateDistance(bcY, bcX, rIdx, cIdx) <= scope)
							availableBC.get(rIdx).get(cIdx).add(BC[idx]);
					}
				}
			}

			// 성능의 최대값이 앞으로 오도록 정렬
			for (int rIdx = 0; rIdx < 10; rIdx++) {
				for (int cIdx = 0; cIdx < 10; cIdx++) {
					availableBC.get(rIdx).get(cIdx).sort(new Comparator<int[]>() {
						@Override
						public int compare(int[] o1, int[] o2) {
							return o2[3] - o1[3];
						}
					});
				}
			}

			totalCharge = 0;
			// 사용자 이동시키기
			int[] userA = new int[] { 0, 0 };
			int[] userB = new int[] { 9, 9 };
			
			// 초기 위치에서도 충전 가능
			charging(userA, userB);
			for (int mIdx = 0; mIdx < M; mIdx++) {
				// 이동 했으면 충전 가능한 BC를 찾는다				
				moveUser(userA, moveA[mIdx]);
				moveUser(userB, moveB[mIdx]);
				charging(userA, userB);
				
			}
			sb.append(totalCharge).append("\n");
		}
		System.out.println(sb);
	}

	private static int calculateDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
	
	private static void moveUser(int[] user, String direction) {
		// 0 : 이동하지 않음  1 : 상  2 : 우  3 : 하  4 : 좌
		switch (direction) {
		case "0":			
			break;
		case "1":
			user[0]--;
			break;
		case "2":
			user[1]++;
			break;
		case "3":
			user[0]++;
			break;
		case "4":
			user[1]--;
			break;
		}
	}
	
	// 리스트가 1보다 큰 경우에만
	// BC 하나 사용자 둘 : 성능 나눠가짐
	// BC 둘 사용자 둘 : 하나씩 접속하기
	// BC 둘 사용자 하나 : 성능 좋은거
	
	static int chargeA, chargeB;

	private static void charging(int[] userA, int[] userB) {
		// 사용자둘 동시에 확인
		// available에 있는 값 중에서 큰값
		// A가 고른 인덱스는 저장하기
		chargeA = 0;
		chargeB = 0;

		// 둘다 가능한 BC가 1개이상인 경우엔 함수로 계산
		if(availableBC.get(userA[0]).get(userA[1]).size()>0 && availableBC.get(userB[0]).get(userB[1]).size() > 0)
			makeCombination(availableBC.get(userA[0]).get(userA[1]), availableBC.get(userB[0]).get(userB[1]));
		else {
			if(availableBC.get(userA[0]).get(userA[1]).size()>0)
				chargeA = availableBC.get(userA[0]).get(userA[1]).get(0)[3];
			if(availableBC.get(userB[0]).get(userB[1]).size() > 0)
				chargeB = availableBC.get(userB[0]).get(userB[1]).get(0)[3];
		}

		totalCharge += chargeA;
		totalCharge += chargeB;
		//System.out.println(chargeA+" "+chargeB);
	}
	
	private static void makeCombination(List<int[]> bcA, List<int[]> bcB) {
		int maxSum = Integer.MIN_VALUE;
		for(int aIdx = 0; aIdx<bcA.size(); aIdx++) {
			for(int bIdx = 0; bIdx<bcB.size(); bIdx++) {
				// 같은 BC는 안됨
				if(bcA.get(aIdx)[4] != bcB.get(bIdx)[4]) {
					int sum = bcA.get(aIdx)[3]+bcB.get(bIdx)[3];
					if(maxSum < sum) {
						maxSum = sum;
						chargeA = bcA.get(aIdx)[3];
						chargeB = bcB.get(bIdx)[3];
					}
				}else {
					int sum = bcA.get(aIdx)[3];
					if(maxSum < sum) {
						maxSum = sum;
						chargeA = bcA.get(aIdx)[3]/2;
						chargeB = bcB.get(bIdx)[3]/2;
					}
				}
			}
		}
	}
}
