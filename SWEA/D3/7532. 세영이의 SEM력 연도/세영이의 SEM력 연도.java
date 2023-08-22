import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 7532_세영이의 SEM력 연도
	 * 
	 * 	[설명]
	 * AD 1년에 SEM력을 1 1 1로 정의
	 * S는 365보다, E는 24보다, M은 29보다 커지면 1로 돌아온다.
	 * SEM력으로 이루어진 연도가 주어졌을 때, 
	 * 이를 만족하는 실제 연도 중 가능한 가장 빠른 연도
	 * 
	 * 	[입력]
	 * ===>
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * 365*a+S = 24*b+E = 29*c+M
	 * 
	 * 어떤 수식이 세워지지는 않고
	 * 한 조건에 만족하는 수에 대해서 다른 조건들도 만족하는지 확인하는 방식으로 찾는다
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int SUN = 365, EARTH = 24, MOON=29;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			st = new StringTokenizer(br.readLine().trim());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			// 가장 빠른 연도를 구해야 하므로 EARTH인 24를 기준으로 찾는다
			int year = E;
			while (true) {
				if ((year - S) % SUN == 0 && (year - E) % EARTH == 0 && (year - M) % MOON == 0)
					break;
				year += EARTH;
			}
			sb.append(year).append("\n");

		}
		System.out.println(sb);
	}

}
