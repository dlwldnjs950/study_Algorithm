import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));

		int ballNum = Integer.parseInt(br.readLine().trim());
		String balls = br.readLine().trim();

		List<Integer> redIdxes = new ArrayList<>();
		List<Integer> blueIdxes = new ArrayList<>();

		for (int idx = 0; idx < ballNum; idx++) {
			char tmp = balls.charAt(idx);
			if (tmp == 'R')
				redIdxes.add(idx);
			else
				blueIdxes.add(idx);
		}

		// 문자열에는 R 또는 B 중 한 종류만 주어질 수도 있으며, 이 경우 답은 0이 된다.
		if (redIdxes.size() == 0 || blueIdxes.size() == 0) {
			System.out.println(0);
			return;
		}

		// 빨간거 옮길 때
		int redMove = cntMove(redIdxes, blueIdxes);
		// 파란거 옮길 때
		int blueMove = cntMove(blueIdxes, redIdxes);

		// System.out.println(redMove + " // " + blueMove);
		System.out.println(Math.min(redMove, blueMove));

	}

	private static int cntMove(List<Integer> target, List<Integer> other) {

		Stack<Integer> targetIdxes = new Stack<Integer>();
		targetIdxes.addAll(target);
		Stack<Integer> otherIdxes = new Stack<Integer>();
		otherIdxes.addAll(other);

		int move = 0;

		while (!(targetIdxes.isEmpty() || otherIdxes.isEmpty())) {

			// 상대 인덱스보다 크면 이동 X
			if (targetIdxes.peek() > otherIdxes.peek()) {
				targetIdxes.pop();

				// 상대 인덱스가 더 크면 이동
			} else {
				move++;

				// 더 큰 인덱스 제거
				while (!otherIdxes.isEmpty() && targetIdxes.peek() < otherIdxes.peek()) {
					otherIdxes.pop();
				}

				// 자리 바꾸기 의미...
				otherIdxes.add(targetIdxes.pop());
			}
		}

		return move;
	}

}
