import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		map = new char[N][N];

		star(0, 0, N, false); // 탐색 시작 좌표, 크기, 공백여부

		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}

	static void star(int x, int y, int N, boolean blank) {
		// 해당 블럭이 공백을 출력할 차례라면
		if (blank) {
			for (int i = x; i < x + N; i++) {
				for (int j = y; j < y + N; j++) {
					map[i][j] = ' ';
				}
			}
			return;
		}

		// 별은 더이상 쪼갤 수 없는 블록일 때가 되서야 출력한다.
		if (N == 1) {
			map[x][y] = '*';
			return;
		}

		int size = N / 3;
		int count = 0; // 별 출력 누적 개수. 별 출력 전에 ++하기 때문에 5가 되었을 때가 공백이다
		// size 만큼 넘어가면서 해당 칸이 별인지 공백인지 확인
		// count가 ++이라는 건 재귀를 다 돌고 와서 다음 for문을 돌고 있다는 것임
		// x, y는 바깥 재귀에서는 사이즈에 따르고 있고
		// 맨 안쪽 재귀에 와서야 각 인덱스에 대해 돈다
		for (int i = x; i < x + N; i += size) {
			for (int j = y; j < y + N; j += size) {
				count++;
				if (count == 5) {
					star(i, j, size, true);
				} else {
					star(i, j, size, false);
				}
			}
		}
	}
}
