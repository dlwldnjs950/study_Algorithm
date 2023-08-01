import java.util.Scanner;

public class Main {

	/**
	 * 스위치 켜져있음 : 1
	 * 스위치 꺼져있음 : 0
	 * # 학생들의 스위치 조작 규칙
	 *   (성별 + 받은 수)
	 *   남학생(1) : 스위치 번호가 받은 수의 배수이면 상태 바꿈
	 *   여학생(2) : 받은 수 중심으로
	 *   					좌우 대칭이면 최대 구간
	 *   					좌우 대칭 아니면 받은 수 스위치만 바꿈
	 * */
	
	static int[] state;
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 스위치의 개수
		N = sc.nextInt();
		state = new int[N+1];

		for (int loop = 1; loop <= N; loop++) {
			state[loop] = sc.nextInt();
		}

		// 학생 수
		int sNum = sc.nextInt();
		for (int sIdx = 0; sIdx < sNum; sIdx++) {
			int gender = sc.nextInt();
			int getNum = sc.nextInt();
			switch (gender) {
			case 1:
				boy(getNum);
				break;
			case 2:
				girl(getNum);
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int idx = 1; idx <= N; idx++) {
			sb.append(state[idx]).append(" ");
			if (idx % 20 == 0)
				sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void boy(int num) {
		for (int idx = 1; idx <= N; idx++) {
			// 받은 수의 배수일 경우
			if (idx % num == 0) {
				state[idx] ^= 1;
			}
		}
	}

	public static void girl(int num) {
		state[num] ^= 1;
		for (int idx = 0; num - idx > 0 && num + idx <= N; idx++) {
			if (state[num - idx] == state[num + idx]) {
				state[num - idx] ^= 1;
				state[num + idx] ^= 1;
			}else
                break;
        }
	}

}
