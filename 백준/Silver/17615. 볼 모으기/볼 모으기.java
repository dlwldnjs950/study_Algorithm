import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 볼의 총 개수 입력
		String balls = br.readLine(); // 볼의 배열 문자열로 입력

		// 빨간 볼의 개수 세기
		int redCount = 0;
		for (char ball : balls.toCharArray()) {
			if (ball == 'R')
				redCount++;
		}
		int blueCount = n - redCount; // 파란 볼의 개수는 전체에서 빨간 볼 개수를 뺀 것

		// 네 가지 경우의 수를 모두 계산하고 그 중 최소값을 찾음
		// 1. 빨간 볼을 왼쪽으로 2. 빨간 볼을 오른쪽으로
		// 3. 파란 볼을 왼쪽으로 4. 파란 볼을 오른쪽으로
		int minMoves = Math.min(
				Math.min(countMoves(balls, 'R', true, redCount), countMoves(balls, 'R', false, redCount)),
				Math.min(countMoves(balls, 'B', true, blueCount), countMoves(balls, 'B', false, blueCount)));

		System.out.println(minMoves); // 최소 이동 횟수 출력
	}

	// 특정 색깔의 볼을 한쪽으로 모으는데 필요한 이동 횟수를 계산하는 메소드
	private static int countMoves(String balls, char color, boolean toLeft, int totalCount) {
		int count = 0; // 이미 올바른 위치에 있는 볼의 개수
		int moves = 0; // 이동해야 할 볼의 개수

		if (toLeft) {
			// 왼쪽으로 모을 때: 왼쪽부터 연속된 같은 색 볼의 개수를 셈
			for (char ball : balls.toCharArray()) {
				if (ball == color)
					count++;
				else
					break;
			}
		} else {
			// 오른쪽으로 모을 때: 오른쪽부터 연속된 같은 색 볼의 개수를 셈
			for (int i = balls.length() - 1; i >= 0; i--) {
				if (balls.charAt(i) == color)
					count++;
				else
					break;
			}
		}

		// 이동해야 할 볼의 개수 = 전체 개수 - 이미 올바른 위치에 있는 볼의 개수
		moves = totalCount - count;
		return moves;
	}
}