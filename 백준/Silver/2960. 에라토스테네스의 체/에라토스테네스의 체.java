import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/**
	 * 	[ 에라토스테네스의 체 ]
	 * 1. 2부터 N까지 모든 정수를 적는다.
	 * 2. 아직 지우지 않은 수 중 가장 작은 수를 찾는다. 
	 * 		이 수는 P이고, 소수이다.
	 * 3. P를 지우고, 아직 지우지 않은 P의 배수를 크기 순서대로 지운다.
	 * 4. 아직 모든 수를 지우지 않았다면, 다시 2번
	 * 
	 * 수의 범위를 저장할 수 있는 배열 선언
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		// 수 범위
		st = new StringTokenizer(br.readLine().trim());
		
		int range = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		boolean[] erased = new boolean[range + 1];

		// 확인은 2부터 시작
		int cnt = 0;

		// 숫자의 제곱이 범위와 같거나 작은 동안
		for (int num = 2; num <= range; num++) {

			// 지워진 수라면 넘어가기
			if (erased[num])
				continue;

			// 배수 지우기
			for (int number = 1; num * number <= range; number++) {

				// 이미 지워진 수라면 넘어가기
				if (erased[num * number])
					continue;
				cnt++;
				erased[num * number] = true;
				if (cnt == k)
					sb.append(num * number);
			}
		}
		System.out.println(sb);
		
	}

}
