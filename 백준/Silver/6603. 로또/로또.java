import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int k, numbers[], num[];

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		while (true) {
			st = new StringTokenizer(br.readLine().trim());
			k = Integer.parseInt(st.nextToken());

			// 0이 입력되었으면 끝내기
			if (k == 0)
				break;

			numbers = new int[k];
			for (int idx = 0; idx < k; idx++) {
				numbers[idx] = Integer.parseInt(st.nextToken());
			}

			// 조합을 찾는 문제~!
			num = new int[6];
			combination(0, 0);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	private static void combination(int start, int cnt) {
		if(cnt == 6) {
			for(int n : num) {
				sb.append(n).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int idx=start; idx<k; idx++) {
			num[cnt] = numbers[idx]; 
			combination(idx + 1, cnt + 1);
		}
	}

}
