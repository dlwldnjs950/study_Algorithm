import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	/*
	 * 2보다 큰 모든 짝수는 두 소수의 합으로 나타낼 수 있다
	 * 	= 골드바흐의 수
	 * n의 골드바흐 파티션을 출력해라
	 * 
	 * 숫자가 10000까지니까
	 * 다 표시하고 출력
	 * 
	 * 입력 개수
	 * 각 숫자
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		boolean isNotPrime[] = new boolean[10001];
		// 소수 표시
		IsitPrime(isNotPrime);

		int cnt = Integer.parseInt(br.readLine().trim());

		for (int loop = 0; loop < cnt; loop++) {
			int num = Integer.parseInt(br.readLine().trim());

			for (int otherNum = num / 2; otherNum > 0; otherNum--) {
				if (!isNotPrime[otherNum] && !isNotPrime[num - otherNum]) {
					sb.append(otherNum).append(" ").append(num - otherNum).append("\n");
					break;
				}
			}
		}
		System.out.println(sb);

	}

	private static void IsitPrime(boolean[] isNotPrime) {
		isNotPrime[0] = true;
		isNotPrime[1] = true;

		for (int num = 2; num * num <= 10000; num++) {
			if (isNotPrime[num])
				continue;
			for (int number = num * num; number <= 10000; number += num) {
				isNotPrime[number] = true;
			}
		}

	}

}
