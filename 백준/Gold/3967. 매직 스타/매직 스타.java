import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Tomato {
	}

	static final int WIDTH = 9, HEIGHT = 5, NUMBER = 12, SUM = 26;
	static final char EMPTY = '.', X = 'x';
	static boolean isUsed[], isEnd;
	static char map[][]; static int numbers[];

	// 전부 숫자로 간주
	// A = 65
	
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		map = new char[HEIGHT][WIDTH];
		isUsed = new boolean[NUMBER];
		numbers = new int[NUMBER];
		int idx=0;

		for (int rIdx = 0; rIdx < HEIGHT; rIdx++) {
			String str = br.readLine().trim();

			for (int cIdx = 0; cIdx < WIDTH; cIdx++) {
				map[rIdx][cIdx] = str.charAt(cIdx);

				// 숫자에 해당되는 칸이라면
				if (map[rIdx][cIdx] != EMPTY) {
					if (map[rIdx][cIdx] == X) {
						idx++;
					} else {

						int num = map[rIdx][cIdx] - 'A';
						numbers[idx++] = num + 1;
						isUsed[num] = true;
					}
				}
			}
		}

		// numbers 배열에 남은 숫자를 넣어보고 다 채워 넣었을 때,
		// 다 채워넣었을 때가 아니라 그 한줄이 채워졌으면 규칙에 맞는지 확인해서 아니면 넘겨야함
		// 근데 이건 어케할지를 잘 모르겠네
		// 규칙(합)에 맞는지 확인
		
		// 탐색 시작칸, 사용된 숫자 개수
		isEnd = false;
		dfs(0);
		
		System.out.println(sb);

	}
	
	static void dfs(int startIdx) {
		
		if(isEnd)
			return;
		
		if(startIdx == NUMBER) {	// 숫자를 전부 채웠을 때,
			if(correct()) {	// 조건을 만족하는지 확인
				//sb.append(Arrays.toString(numbers)).append("\n");
				// 다시 별모양으로 꾸미기
				makeStar();
				isEnd = true;
			}
			return;
		}

		if (numbers[startIdx] != 0) {
			dfs(startIdx + 1);

		} else {

			for (int num = 1; num <= NUMBER; num++) {
				// 이미 사용된 숫자면 다음 숫자
				if (isUsed[num - 1])
					continue;

				// 해당 숫자 사용해보기
				isUsed[num - 1] = true;
				numbers[startIdx] = num;
				dfs(startIdx + 1);
				isUsed[num - 1] = false;
				numbers[startIdx] = 0;

			}
		}
	}
	
	// 규칙 합 확인
	static boolean correct() {
		if (numbers[0] + numbers[2] + numbers[5] + numbers[7] != SUM)
			return false;
		if (numbers[1] + numbers[2] + numbers[3] + numbers[4] != SUM)
			return false;
		if (numbers[1] + numbers[5] + numbers[8] + numbers[11] != SUM)
			return false;
		if (numbers[7] + numbers[8] + numbers[9] + numbers[10] != SUM)
			return false;
		if (numbers[4] + numbers[6] + numbers[9] + numbers[11] != SUM)
			return false;
		if (numbers[0] + numbers[3] + numbers[6] + numbers[10] != SUM)
			return false;
		
		return true;
	}
	
	// 다시 별 모양으로 만들기
	static void makeStar() {
		int idx = 0;
		for (int rIdx = 0; rIdx < HEIGHT; rIdx++) {

			for (int cIdx = 0; cIdx < WIDTH; cIdx++) {
				
				// 숫자에 해당되는 칸이라면
				if(map[rIdx][cIdx] == EMPTY) {
					sb.append(EMPTY);
				}else {
					sb.append((char)(numbers[idx++]-1+'A'));
				}
			}
			sb.append("\n");
		}
	}

}
