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
		int N = Integer.parseInt(br.readLine().trim());

		for (int idx = 0; idx < N; idx++) {

			long number = Long.parseLong(br.readLine().trim());

			// number보다 크거나 같은 소수 중 가장 작은 소수 출력

			while (true) {
				if (isSosu(number)) {
					sb.append(number).append("\n");
					break;
				} else {
					number++;
				}
			}
		}
		System.out.println(sb);
	}

	private static boolean isSosu(long number) {

        // 입력이 0부터 시작임
		if(number <= 1)
			return false;
		
        // 제곱근 까지만 확인
		for (long num = 2; num * num <= number; num++) {

			if (number % num == 0)
				return false;
		}

		return true;
	}

}
