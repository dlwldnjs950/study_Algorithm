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
		while (true) {
			// N
			int N = Integer.parseInt(br.readLine().trim());

			if (N == 0) {
				break;
			}
			sb.append(countPrime(N)).append("\n");
		}
		System.out.println(sb);
	}

	private static int countPrime(int number) {

		int cnt = 0;

		for (int num = number + 1; num <= 2 * number; num++) {

			if (isPrime(num))
				cnt++;
		}

		return cnt;
	}

	private static boolean isPrime(int num) {

		if (num <= 1)
			return false;

		for (int number = 2; number * number <= num; number++) {
			if (num % number == 0)
				return false;
		}

		return true;
	}

}
