import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int cnt, N;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			N = Integer.parseInt(br.readLine().trim());
			
			cnt=0;
			int limit = (int)Math.ceil((double)N/2);
			for(int num=1; num<=N; num++) {
				dfs(num,num);
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int num, int sum) {
		// N이 되었으면 카운트하고 더 이상 확인하지 않는다
		if (sum == N) {
			cnt++;
			return;
		}
		// N보다 커졌으면 더이상 확인할 필요가 없다
		if (sum > N)
			return;
		dfs(num + 1, sum + num + 1);
	}
}
