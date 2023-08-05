import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			st = new StringTokenizer(br.readLine().trim());
			
			int P = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			int A = P * W;
			int B = R < W ? (W-R)*S + Q : Q;
			
			if (A > B) {
				sb.append(B);
			}
			else if (A < B){
				sb.append(A);
			}
			else {
				sb.append("동일함");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
