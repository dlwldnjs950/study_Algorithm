import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	/**
	 * #
	 * 
	 * [설명] 2보다 큰 짝수는 두 소수의 합으로 나타낼 수 있다
	 * 
	 * 골드바흐 파티션의 개수?
	 * 
	 * [풀이방법] 주어진 수 보다 작은 소수를 찾고 소수와 주어진수 - 소수도 소수인지 확인한다
	 * 
	 */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static boolean isErased[];

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// N
		int N = Integer.parseInt(br.readLine().trim());
		
		int input[] = new int[N];
		int max = Integer.MIN_VALUE;

		for (int testCase = 0; testCase < N; testCase++) {

			input[testCase] = Integer.parseInt(br.readLine().trim());
			max = Math.max(max, input[testCase]);
		}
		
		for (int testCase = 0; testCase < N; testCase++) {
			
			isErased = new boolean[max + 1];

			findPrime(max);

			int cnt = cntPartition(input[testCase]);
			
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	private static int cntPartition(int number) {
		int cnt = 0;

		for (int num = 2; num <= number / 2; num++) {
			int otherNum = number - num;

			if (!isErased[num] && !isErased[otherNum])
				cnt++;
		}

		return cnt;
	}

	private static void findPrime(int number) {
		
		isErased[0] = true;
	    isErased[1] = true;

	    for (int num = 2; num * num <= number; num++) {
	        // 이미 확인한 숫자는 넘어가기
	        if (!isErased[num]) {
	            // 배수는 소수가 아님을 표시
	        	// 이미 체크한 작은 배수들은 이미 다른 소수의 배수로 체크
	            for (int n = num * num; n <= number; n += num) {
	                isErased[n] = true;
	            }
	        }
	    }
	}

}
