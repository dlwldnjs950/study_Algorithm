import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	/*
	 * 주식을 산다 /원하는 만큼 판다/ 아무것도 안한다
	 * 
	 * 날별 주식의 가격
	 * 최대 이익이 얼마나 되는지 계산
	 * 
	 * 테스트 케이스 수
	 * 날의 수
	 * 날별 주가*/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine().trim());

		for (int testCase = 0; testCase < tc; testCase++) {
			int days = Integer.parseInt(br.readLine().trim());
			st = new StringTokenizer(br.readLine().trim());

			int stocks[] = new int[days];
			int idx = 0;
			while (st.hasMoreTokens()) {
				stocks[idx++] = Integer.parseInt(st.nextToken());
			}

			int max = stocks[days - 1];
			long income = 0;	// 답은 부호있는 64bit 정수형
			for (idx = days - 1; idx >= 0; idx--) {
				if (stocks[idx] <= max)
					income += max - stocks[idx];
				else
					max = stocks[idx];
			}

			sb.append(income).append("\n");
		}
		System.out.println(sb);

	}
}
