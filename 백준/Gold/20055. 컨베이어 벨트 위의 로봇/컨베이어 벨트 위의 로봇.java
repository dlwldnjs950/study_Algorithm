import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 길이 N 컨베이어 벨트 길이 2N인 벨트가 컨베이어 벨트 위아래 감싸서 도는 중
	 * 
	 * 1번위치 : 올리는 위치 n번위치 : 내리는 위치 i번 칸의 내구도 Ai
	 * 
	 * 1번위치에만 로봇을 올릴 수 있다 로봇이 내리는 위치 도달하면 그 즉시 내림 로봇이 올라간 칸의 내구도는 1 감소
	 * 
	 * 로봇들을 옮기는 과정 1. 벨트 이동 (with 로봇) 2. 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동 (로봇이 없어야하고,
	 * 내구도가 1 이상) 3. 내구도가 0인칸이 K개 이상이라면 과정 종료
	 * 
	 * 몇번쨰 단계가 진행 중일 때, 종료되었는지 출력
	 * 
	 */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		// 컨베이어 벨트 길이와 내구도 0 제한 개수
		int beltLength = Integer.parseInt(st.nextToken());
		int limit = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine().trim());
		int[] beltString = new int[beltLength * 2];
		for (int idx = 0; idx < beltLength * 2; idx++) {
			beltString[idx] = Integer.parseInt(st.nextToken());
		}

		int zeroCnt = 0;
		int step = 0;
		boolean[] belt = new boolean[beltLength];

		while (zeroCnt < limit) {
			step++;

			// 벨트 회전
			int temp = beltString[2 * beltLength - 1]; // 마지막꺼
			for (int idx = 2 * beltLength - 1; idx > 0; idx--)
				beltString[idx] = beltString[idx - 1];
			beltString[0] = temp;

			for (int idx = beltLength - 1; idx > 0; idx--) {
				belt[idx] = belt[idx - 1];
			}

			belt[0] = false;
			belt[beltLength - 1] = false;

			// 로봇 이동
			for (int idx = beltLength - 1; idx > 0; idx--) {
				if (!belt[idx - 1] || belt[idx] || beltString[idx] < 1)
					continue;
				beltString[idx]--;
				if (beltString[idx] == 0)
					zeroCnt++;
				belt[idx] = true;
				belt[idx - 1] = false;
			}

			// 로봇 올리기
			if (beltString[0] <= 0)
				continue;
			belt[0] = true;
			beltString[0]--;
			if (beltString[0] == 0)
				zeroCnt++;
		}

		System.out.println(step);

	}

}
