import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

class Enemy{
	int row;
	int col;
	
	public Enemy(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	@Override
	public boolean equals(Object obj) {
		Enemy e = (Enemy)obj;
		return this.row == e.row && this.col == e.col;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(row,col);
	}
	
}

public class Main {
	
	/**
	 * 	# 17135_캐슬 디펜스
	 * 
	 * 	[설명]
	 * 성을 향해 몰려오는 적을 잡자
	 * 적이 있는 공간 : N*M
	 * 성이 있는 공간 : N+1행
	 * 
	 * 각 칸에 적은 최대 1
	 * 
	 * 성을 지키기 위한 궁수 3명 (N+1행에 배치)
	 * 	각 턴 마다 적 1명 공격
	 * 	모든 궁수 동시 공격
	 * 		거리가 D이하인 적 중에서 가장 가까운 적 (여러명이면 가장 왼쪽)
	 * 		공격 받은 적은 게임에서 제외
	 * 	공격이 끝나면 적 이동 (아래로 한칸)
	 * 		성으로 이동했으면 게임에서 제외
	 * 
	 * 적이 모두 제외되었을 때,
	 * 		궁수의 공격으로 제외된 적은 최대 몇명?
	 * 
	 * 	[입력]
	 * ===> 격자판 행수 N 열수 M 공격제한거리 D
	 * (N줄의 입력)
	 * ===> 공백구분된 M개의 수
	 * 
	 * 	[출력]
	 * 궁수의 공격으로 제외된 적은 최대 몇명인지
	 * 
	 * 	[풀이방법]
	 * 궁수의 위치는 조합
	 * 
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int MAXIMUM_ARCHER = 3;
	static int N, M, D, map[][], originMap[][], maxCnt;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 격자판 행수 N 열수 M 공격제한거리 D
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		// 격자판 정보
		map = new int[N+1][M];	// 성 위치이자 궁수가 위치할 행까지 선언
		// 궁수 조합에 따라 다시 초기 맵으로 바꿔야하므로 원본 저장해두기
		originMap = new int[N+1][M];
		for(int rIdx = 0; rIdx<N; rIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int cIdx = 0; cIdx<M; cIdx++) {
				map[rIdx][cIdx] = Integer.parseInt(st.nextToken());
				originMap[rIdx][cIdx] = map[rIdx][cIdx];
			}
		}
		
		// 어쨌든 궁수를 위치 시켰다 치고 그 다음을 생각해보자
		// 궁수 배치는 어차피 조합?일테니까
		maxCnt = Integer.MIN_VALUE;
		combinationArcher(0, 0);
		
		sb.append(maxCnt);
		System.out.println(sb);
	}
	
	private static void combinationArcher(int cnt, int start) {
		if(cnt == MAXIMUM_ARCHER) {
			// 궁수 배치
			//System.out.println("궁수\t" + Arrays.toString(map[N]));
			// 게임 진행 전 맵 초기화
			initMap();
			// 게임 진행
			startGame();
			
			return;
		}
		for(int idx = start; idx<M; idx++) {
			// 이미 궁수가 배치 되었다면 넘어가기
			if(map[N][idx]==1)
				continue;
			// 궁수 배치했다가
			map[N][idx] = 1;
			combinationArcher(cnt +1, idx+1);
			// 다시 해제하기
			map[N][idx] = 0;
		}
	}
	
	private static void startGame() {
		int tmpCnt = 0;
		// 적이 다 제외될 때까지니까 N번 이동 후에는 전부 사라지게 된다.
		for(int loop = 0; loop<N; loop++) {
			Set<Enemy> deletedEnemy = new HashSet<>();
			// 궁수가 배치되어있으면 거리 비교
			for (int aIdx = 0; aIdx < M; aIdx++) {
				// 궁수와 적을 비교해서 제외시킬 적 찾기
				int[] minDEnemy = new int[3]; // 거리, row, col
				minDEnemy[0] = Integer.MAX_VALUE;
				minDEnemy[1] = -1;
				minDEnemy[2] = -1;
				if (map[N][aIdx] == 1) {
					// 각 턴마다 적은 아래로 이동하니까 확인할 구간도 줄어든다
					for (int rIdx = loop; rIdx < N; rIdx++) {
						for (int cIdx = 0; cIdx < M; cIdx++) {
							// 적이 배치되어 있으면 거리 확인
							if (map[rIdx][cIdx] == 1) {
								int tmpDistance = calculateDistance(N, aIdx, rIdx, cIdx);
								//System.out.print(tmpDistance+" ");
								// 공격 가능 거리 D 이하이고
								if (tmpDistance <= D) {
									// 현재 최소거리보다 작으면
									if (tmpDistance < minDEnemy[0]) {
										// 여기 순서가 잘못되어 있었음...
										minDEnemy[0] = tmpDistance;
										minDEnemy[1] = rIdx;
										minDEnemy[2] = cIdx;
									// 거리 같으면 더 왼쪽 적 (열값)
									}else if(tmpDistance == minDEnemy[0]) {
										if(cIdx < minDEnemy[2]) {
											minDEnemy[1] = rIdx;
											minDEnemy[2] = cIdx;
										}
									}
								}
							}
						}
						//System.out.println();
					}
				}
				// 최소값을 찾았을 때만...
				if (minDEnemy[1] >= 0 && minDEnemy[2] >= 0) {
					if (map[minDEnemy[1]][minDEnemy[2]] != 0)
					// 찾은 최소 거리 적 제외 시키기
					//map[minDEnemy[1]][minDEnemy[2]] = 0;
					deletedEnemy.add(new Enemy(minDEnemy[1], minDEnemy[2]));
				}
			}
			// 적을 다 찾고 나서 한꺼번에 제외 표시
			// 즉, 적은 2명 없어졌는 데 죽인 적을 카운트는 3명이 될 수 있다
			 //System.out.println(deletedEnemy.size());
			for (Enemy e : deletedEnemy) {
				tmpCnt++;
				map[e.row][e.col] = 0;
			}

			// 적이 지워졌는지 확인
//			for (int idx = 0; idx < N; idx++) {
//				System.out.println("적\t" + Arrays.toString(map[idx]));
//			}
//			System.out.println();
			// 적 이동 시키고
			for (int rIdx =  N-1; rIdx > loop; rIdx--) {
				for (int cIdx = 0; cIdx < M; cIdx++) {
					map[rIdx][cIdx] = map[rIdx-1][cIdx];
				}
			}
		}
		maxCnt = Math.max(tmpCnt, maxCnt);
	}

	private static void initMap() {
		for(int rIdx = 0; rIdx<N; rIdx++) {
			for(int cIdx = 0; cIdx<M; cIdx++) {
				map[rIdx][cIdx] = originMap[rIdx][cIdx];
			}
		}
	}
	
	private static int calculateDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}


