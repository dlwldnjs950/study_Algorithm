import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 17281_야구
	 * 
	 * 	[설명]
	 * 9명으로 이루어진 두 팀이 공격과 수비를 번갈아하는 게임
	 * 하나의 이닝은 공격과 수비로 이루어짐
	 * 총 N 이닝 동안 게임 진행
	 * 한 이닝에 3아웃 발생하면 이닝 종료 !!!
	 * 두 팀이 공격-수비 교체
	 * 
	 * 경기 시작 전 타순 정함 (중간에 변경 불가)
	 * 1번에서 9번까지 계속 돌아가면서 타석에 섬
	 * 이전 이닝에서 6번타자까지 섰으면
	 * 다음 이닝에서 7번타자가 타석에 선다
	 * 
	 * 공격팀의 투수가 1,2,3루를 거쳐 홈에 도착하면 1득점
	 * 
	 * 타자가 공을 치면
	 * (1) 안타 : 타자 및 모든 주자 한 루씩 진루
	 * (2) 2루타 : 타자 및 모든 주자 두 루씩 진루
	 * (3) 3루타 : 타자 및 모든 주자 세 루씩 진루
	 * (4) 홈런 : 타자와 모든 주자 홈까지 진루
	 * (0) 아웃 : 아웃 하나 증가
	 * 
	 * 1번 선수를 4번 타자로 미리 결정
	 * (감독은 각 선수가 각 이닝에서 어떤 결과를 얻는지 미리 알고있다)
	 * 가장 많은 득점을 하는 타순을 찾아 그때의 득점을 구해라
	 * 
	 * 각 이닝에는 아웃을 기록하는 타자가 적어도 한명 존재한다
	 * 
	 * 	[입력]
	 * ===> 이닝 수 N
	 * (N줄의 입력)
	 * ===> 각 선수가 각 이닝에서 얻는 결과 (9개의 정수 공백 구분)
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * 타자의 순서 정하기 (순열)
	 * 아웃 카운트
	 * 점수 누적
	 * 루 위에 사람이 최대한 많이 서있어야하므로 
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int PLAYER_CNT = 9;
	static final int FIRST_HITTER = 0;	//1번타자지만 인덱스 번호는 0이다
	static final int LIMIT_OUTCNT = 3;
	
	static int N, eachInning[][], maxScore;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 이닝 수 N
		N = Integer.parseInt(br.readLine().trim());

		// 각 이닝 각 선수 결과 저장
		eachInning = new int[N][PLAYER_CNT];
		for (int inningCnt = 0; inningCnt < N; inningCnt++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int playerNum = 0; playerNum < PLAYER_CNT; playerNum++) {
				eachInning[inningCnt][playerNum] = Integer.parseInt(st.nextToken());
			}
		}

		// 타자 순서 만들기
		// 1번 선수가 4번 타자할 것임
		hittingOrder[3] = FIRST_HITTER;
		selected[FIRST_HITTER] = true;
		maxScore = Integer.MIN_VALUE;
		perm(0);
		
		sb.append(maxScore);
		System.out.println(sb);
	}
	
	static int[] hittingOrder = new int[PLAYER_CNT];
	static boolean[] selected = new boolean[PLAYER_CNT];
	
	private static void perm(int cnt) {
		if(cnt == PLAYER_CNT) {
			// 타순을 정했으니 게임을 하자
			//System.out.println(Arrays.toString(hittingOrder));
			int score = playGame();
			//System.out.println(Arrays.toString(hittingOrder)+" : "+score);
			maxScore = Math.max(maxScore, score);
		}else {
			for(int num = 0; num<PLAYER_CNT; num++) {
				if(selected[num])
					continue;
				if(cnt == 3)
					cnt++;
				hittingOrder[cnt] = num;
				selected[num] = true;
				perm(cnt + 1);
				selected[num] = false;
				
			}
		}

	}

	private static int playGame() {
		int score = 0;
		int nowPlayerIdx = 0;
		// 이닝 만큼 경기
		// 각 이닝에서는 아웃 카운트가 3이 될때까지 진행
		for(int round = 0; round<N; round++) {
			int outCnt = 0;
			// 루 상태 초기화
			int[] base = new int[3];
			while(true) {
				// 현재 살필 타순의 타자
				int nowPlayer = hittingOrder[nowPlayerIdx];
				switch (eachInning[round][nowPlayer]) {
				case 1:
					// 지금 타자는 진루, 한명 밀려나옴
					if(base[2] == 1)
						score++;
					base[2] = base[1];
					base[1] = base[0];
					base[0] = 1;
					break;
				case 2:
					// 지금 타자 두루 진루, 두칸 밀림
					if(base[2] ==1)
						score++;
					if(base[1] ==1)
						score++;
					base[2] = base[0];
					base[1] = 1;
					base[0] = 0;
					break;
				case 3:
					// 지금 타자 세루 진루, 세칸 밀림
					if(base[2] ==1)
						score++;
					if(base[1] ==1)
						score++;
					if(base[0] ==1)
						score++;
					base[2] = 1;
					base[1] = 0;
					base[0] = 0;
					break;
				case 4:
					// 지금 타자 네루 진루, 네칸 밀림
					if(base[2]==1)
						score++;
					if(base[1]==1)
						score++;
					if(base[0]==1)
						score++;
					score++;
					Arrays.fill(base, 0);
					break;
				case 0:
					outCnt++;
					break;
				}
				 //System.out.println(Arrays.toString(base));
				// System.out.println("현재 타자 : "+nowPlayer+" 아웃카운트 : "+outCnt);
				nowPlayerIdx = (nowPlayerIdx + 1) % PLAYER_CNT;
				// 아웃카운트가 다 차면 다음 이닝으로 넘어간다
				if (outCnt == LIMIT_OUTCNT)
					break;
			}
		}
		
		return score;
	}
}
