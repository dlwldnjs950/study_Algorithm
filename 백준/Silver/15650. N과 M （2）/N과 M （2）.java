import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	/**
	 * # 조합
	 * 	1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
	 * 	고른 수열은 오름차순
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static int[] numbers;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. N과 M을 입력받는다
		String[] tmp = br.readLine().trim().split(" ");
		int N = Integer.parseInt(tmp[0]);	// 선택지. 1~N까지
		int M = Integer.parseInt(tmp[1]);	// 고를 개수
		
		// 2. 문제의 조건을 만족하는 수열을 만들고 출력한다
		// 만들어진 수열을 저장할 변수 초기화
		numbers = new int[M];
		combi(0, 1, N, M);
		System.out.println(sb);
	}

	private static void combi(int cnt, int start, int N, int M) {
		if (cnt == M) {
			for (int num : numbers) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = start; i <= N; i++) {
			numbers[cnt] = i;
			combi(cnt + 1, i + 1, N, M);
		}
	}

}
