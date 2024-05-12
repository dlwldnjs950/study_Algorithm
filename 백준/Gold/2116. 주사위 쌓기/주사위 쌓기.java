import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 마주보는 면 숫자 합 != 7
	 * 주사위도 순서대로 쌓는군...
	 * 	규칙
	 * 붙어있는 두 주사위 : 붙어있는 면 끼리 숫자가 같아야 한다
	 * 이렇게 쌓았을 때, 주사위 기둥의 한쪽 면 숫자의 합이 최대가 되도록
	 * 	그 숫자 합의 최댓값을 구하여라
	 * 
	 * A B C D E F
	 * 0 1 2 3 4 5
	 * 5 3 4 1 2 0
	 * */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int[][] dices;
	static int max = Integer.MIN_VALUE, diceCnt;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		diceCnt = Integer.parseInt(br.readLine().trim());
		dices = new int[diceCnt][6];
		for (int idx = 0; idx < diceCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int tmpIdx = 0;
			while (st.hasMoreTokens()) {
				dices[idx][tmpIdx++] = Integer.parseInt(st.nextToken());
			}
			// sb.append(Arrays.toString(dices[idx])).append("\n");
		}

		// 맨 아래 주사위의 윗면을 바꿔가며 최대값 찾기

		for (int idx = 0; idx < 6; idx++) {
			maxSum(0, idx, 0);
		}

		System.out.println(max);

	}

	public static void maxSum(int diceIdx, int downside, int midSum) {
		int upside = othersideIdx(downside);
//		System.out.println(downside+" "+ upside);

		int tmpMax = Integer.MIN_VALUE;
		for (int idx = 0; idx < 6; idx++) {
			if (idx == downside || idx == upside)
				continue;
			tmpMax = Math.max(tmpMax, dices[diceIdx][idx]);
		}

		midSum += tmpMax;

		if (diceIdx == diceCnt - 1) {
			max = Math.max(max, midSum);
			return;
		}

		for (int idx = 0; idx < 6; idx++) {
			if (dices[diceIdx][upside] == dices[diceIdx + 1][idx]) {
				maxSum(diceIdx + 1, idx, midSum);
				break;
			}
		}
	}

	public static int othersideIdx(int idx) {
		switch (idx) {
		case 0:
			return 5;
		case 1:
			return 3;
		case 2:
			return 4;
		case 3:
			return 1;
		case 4:
			return 2;
		case 5:
			return 0;
		default:
			return -1;
		}
	}
}
