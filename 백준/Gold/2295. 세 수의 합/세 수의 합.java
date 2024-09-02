import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;

	static int numCnt, numbers[];

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));

		numCnt = Integer.parseInt(br.readLine().trim());
		numbers = new int[numCnt];

		for (int idx = 0; idx < numCnt; idx++) {
			numbers[idx] = Integer.parseInt(br.readLine().trim());
		}
		Arrays.sort(numbers);

		int maxAble = numbers[numCnt - 1];
		int maxSum = 0;
		// 가장 큰 원소
		for (int element3 = numCnt - 2; element3 >= 0; element3--) {
			// 그다음 큰 원소. 원소가 같아도 됨
			for (int element2 = element3; element2 >= 0; element2--) {
				if (numbers[element3] + numbers[element2] > maxAble)
					continue;

				// 가장 작은 원소
				int element1 = element2;
				int sumElement = numCnt - 1;

				// 합이 원소 값보다 작을 수는 없음
				while (element1 >= 0 && sumElement > element3) {
					int tmpSum = numbers[element1] + numbers[element2] + numbers[element3];

					// 가장 큰 수부터 확인하는데 max 보다 작으면 더 볼 필요 없음
					if (tmpSum < maxSum)
						break;

					// maxAble 보다 커서 값비교 불가
					if (tmpSum > maxAble)
						element1--;
					else {
						if (tmpSum == numbers[sumElement]) {
							maxSum = Math.max(maxSum, tmpSum);
							break;
						} else if (tmpSum > numbers[sumElement]) {
							element1--;
						} else { // tmpSum < numbers[sumElement]
							sumElement--;
						}
					}
				}
			}
		}
		System.out.println(maxSum);
	}
}
