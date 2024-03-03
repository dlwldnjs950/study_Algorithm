
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	// 10원 5개, 50원 1개를 받았어도 둘이 같은 금액으로 나눌 수 있음

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int TC = 3;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		for (int testCase = 0; testCase < TC; testCase++) {
			int coinKind = Integer.parseInt(br.readLine().trim());
			int[][] coins = new int[coinKind][2];
			boolean[] dp = new boolean[100001];

			// 동전 정보 저장
			int sum = 0;
			for (int idx = 0; idx < coinKind; idx++) {

				st = new StringTokenizer(br.readLine().trim());
				int amount = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());
				coins[idx][0] = amount;
				coins[idx][1] = count;

				sum += amount * count;

				// 방금 입력한 동전으로 만들 수 있는 금액 표시
				for (int cnt = 1; cnt <= count; cnt++) {
					dp[amount * cnt] = true;
				}
			}

			// 합이 홀수면 어차피 못나눔
			if (sum % 2 == 1) {
				sb.append(0).append("\n");
				continue;

				// 만들 수 있다고 이미 판별되었음
			} else if (dp[sum / 2]) {
				sb.append(1).append("\n");
				continue;
			}

			// 만들어낼 수 있는 금액인지 확인
			dp[0] = true;
			for (int coinIdx = 0; coinIdx < coinKind; coinIdx++) {
				int amount = coins[coinIdx][0];
				int count = coins[coinIdx][1];

				for (int value = sum / 2; value >= amount; value--) {
					// amount 동전을 1개 이상 사용할 수 있어야하므로
					// dp[value - amount]까지는 가능한 상황이어야한다.
					if (dp[value - amount]) {
						for (int cnt = 1; cnt <= count; cnt++) {
							if (value - amount + value * cnt > sum / 2)
								break;
							dp[value - amount + value * cnt] = true;
						}
					}
				}
			}
			sb.append(dp[sum / 2] ? 1 : 0).append("\n");

		}
		System.out.println(sb);

	}

}
