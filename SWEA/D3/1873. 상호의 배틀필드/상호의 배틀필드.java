import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 1873_상호의 배틀필드
	 * 
	 * 	[설명]
	 * 전차 1대
	 * 맵 정보는 평지, 벽돌벽, 강철벽, 물, 전차(바라보는 방향)
	 * 사용자 명령 5가지 : 방향 바꾸기 + (가능하다면) 이동, 포탄 쏘기
	 * 
	 * 맵 내에서만 이동
	 * 
	 * 포탄 : 벽 또는 맵 밖으로 나갈 때까지 직진
	 * 		벽돌 벽과 만나면 해당 벽 파괴(평지)
	 * 
	 * 	[입력]
	 * ===> 높이 H 너비 W (공백 구분)
	 * (H줄의 입력)
	 * ===> 맵정보 : 길이 W의 문자열(공백 구분 없음)
	 * ===> 사용자 입력 개수 N
	 * ===> 사용자입력 : 길이 N의 문자열
	 * 
	 * 	[출력]
	 * 모든 입력 처리 후 맵 상태 출력
	 * 
	 * 	[풀이방법]
	 * 전차의 이동은 해당 방향에 맞춰 이동하면 된다 (어느 경로로 이동하는지 찾는게 아님!)
	 * 포탄 이동 관련 함수를 따로 두고
	 * 		어디까지 이동할 수 있는지 확인
	 * 		벽돌벽이 있는 경우만 특수하게 다루면 된다.
	 * 전차는 방향을 바꾼 뒤 이동 가능한지 확인
	 * 전차의 위치 갱신
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int H, W, tank[];
	static char map[][];

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			// 높이 H와 너비 W 입력받기
			st = new StringTokenizer(br.readLine().trim());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			// 맵 정보 입력받기
			// 전차의 위치도 저장하기
			map = new char[H][W];
			for(int rIdx = 0; rIdx<H; rIdx++) {
				String tmp = br.readLine().trim();
				for(int cIdx = 0; cIdx<W; cIdx++) {
					map[rIdx][cIdx] = tmp.charAt(cIdx);
					// 전차일 경우 위치와 방향 저장
					// [0] : 전차위치 행, [1] : 전차위치 열, [2] : 전차의 방향
					switch (map[rIdx][cIdx]) {
					// 상:1, 하:2, 좌:3, 우:4
					case '^':
						tank = new int[] {rIdx,cIdx, 1};
						break;
					case 'v':
						tank = new int[] {rIdx,cIdx, 2};
						break;
					case '<':
						tank = new int[] {rIdx,cIdx, 3};
						break;
					case '>':
						tank = new int[] {rIdx,cIdx, 4};
						break;
					}
				}
			}
			// 전차의 위치 평지로 표시
			map[tank[0]][tank[1]] = '.';
			
			// 사용자 입력 개수 N
			int N = Integer.parseInt(br.readLine().trim());
			
			// 사용자 입력 문자열
			String command = br.readLine().trim();
			
			for(char cmnd : command.toCharArray()) {
				doCommand(cmnd);
			}

			// 전차의 위치 표시
			switch (tank[2]) {
			case 1:
				map[tank[0]][tank[1]] = '^';
				break;
			case 2:
				map[tank[0]][tank[1]] = 'v';
				break;
			case 3:
				map[tank[0]][tank[1]] = '<';
				break;
			case 4:
				map[tank[0]][tank[1]] = '>';
				break;
			}
			// 맵 상태 출력
			for(int rIdx = 0; rIdx<H; rIdx++) {
				for(int cIdx = 0; cIdx<W; cIdx++) {
					sb.append(map[rIdx][cIdx]);
				}
				sb.append("\n");
			}
			
		}
		System.out.println(sb);
	}
	
	// 명령어에 따라 일을 수행하는 함수
	private static void doCommand(char cmnd) {
		switch (cmnd) {	// 전차가 방향을 바꿨을 때, 평지라면 이동할 수 있다.
		// 상:1, 하:2, 좌:3, 우:4
		case 'U':	// 위쪽 방향을 바라봄
			tank[2] = 1;
			canMove(-1, 0);
			break;
		case 'D':	// 아래쪽을 바라봄
			tank[2] = 2;
			canMove(1, 0);
			break;
		case 'L':	// 왼쪽을 바라봄
			tank[2] = 3;
			canMove(0, -1);
			break;
		case 'R':	// 오른쪽을 바라봄
			tank[2] = 4;
			canMove(0, 1);
			break;
		case 'S':	// 포탄 쏘기
			moveBomb(tank[0], tank[1], tank[2]);
			break;
		}
	}
	
	// S명령어에서 포탄의 이동을 수행하는 함수
	private static void moveBomb(int r, int c, int direction) {	// 전차의 위치와 방향을 넘겨준다.
		// 움직일 방향 정하기
		int dirR = 0, dirC = 0;
		if(direction == 1) {
			dirR = -1;
		}else if(direction == 2) {
			dirR = 1;
		}else if(direction == 3) {
			dirC = -1;
		}else if(direction == 4) {
			dirC = 1;
		}
		// 이동하다가 벽돌벽을 만난 경우에 벽 지우기
		int nr = r;
		int nc = c;
		while(true) {
			nr += dirR;
			nc += + dirC;
			
			// 범위 밖을 벗어났으면 그만 확인
			if (nr < 0 || nc < 0 || nr >= H || nc >= W)
				return;
			// 강철벽을 만났으면 그만 확인
			if(map[nr][nc] == '#')
				return;
			// 벽돌벽을 만났으면 평지로 만들고 그만 확인
			if(map[nr][nc] == '*') {
				map[nr][nc] = '.';
				return;
			}
		}
	}
	
	private static void canMove(int r, int c) {
		int nr = tank[0] + r;
		int nc = tank[1] + c;
		// 현재 전차 위치에서 전달받은 방향으로 움직일 수 있는지
		// 범위 밖이면 못지나감
		if (nr < 0 || nc < 0 || nr >= H || nc >= W)
			return;
		// 벽이거나 물이면 못지나감
		if (map[nr][nc] == '*' || map[nr][nc] == '#' || map[nr][nc] == '-')
			return;

		// 움직일 수 있으면 전차 위치 갱신
		tank[0] = nr;
		tank[1] = nc;
	}

}
