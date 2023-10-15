import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// N
		st = new StringTokenizer(br.readLine().trim());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		boolean[] isErased = new boolean[N + 1];

		// 에라토스 테네스의 체
		for (int number = 0; number <= N; number++) {

			// 이미 지워진 숫자면 넘어가기
			if (isErased[number])
				continue;

			// 소수
			if (isPrime(number)) {
				for (int num = 2; num * number <= N; num++) {
					isErased[num * number] = true;
				}
			} else {
				isErased[number] = true;
			}
		}
		
		for(int number = M; number<=N; number++) {
			if(!isErased[number])
				sb.append(number).append("\n");
		}

		System.out.println(sb);
	}

	private static boolean isPrime(int number) {

		if (number <= 1)
			return false;

		for (int num = 2; num * num <= number; num++) {
			if (number % num == 0)
				return false;
		}

		return true;
	}

}
