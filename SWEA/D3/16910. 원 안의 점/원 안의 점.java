import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 16910_원안의 점
	 * 
	 * 	[설명]
	 * 원점을 중심으로 반지름이 N인 원 안에 포함된느 격자점의 개수를 구하여라
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 수
	 * (T줄의 입력)
	 * ===> 반지름 N
	 * 
	 * 	[출력]
	 * 격자점 개수
	 * 
	 * 	[풀이방법]
	 * 반복 횟수를 줄이기 위해 인덱스값의 제곱이 N보다 작거나 같은 경우만 살핌
	 * 4등분해서 한 구역의 개수만 구해서 4배
	 * 	x값은 0부터 확인하되, y값은 0부터 시작해서 축 위에 있는 값이 다중 카운트 되지 않도록 한다
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			int N = Integer.parseInt(br.readLine().trim());

			int cnt = 0;
			// x의 제곱이 N의 제곱보다 작은 경우만 확인
			for (int x = 0; x*x <= N*N; x++) {
				// y의 제곱이 N의 제곱보다 작은 경우만 확인
				// y축 위의 값은 카운트 하지 않는다
				for (int y = 1; y*y <= N*N; y++) {
					// 조건에 맞는 경우 카운트
					if(x*x + y*y <=N*N)
						cnt++;
				}
			}
			// 부등호만 다른 각 사분면의 개수를 세야하므로 4배
			// 원점 포함 +1
			sb.append(cnt*4+1).append("\n");
		}
		System.out.println(sb);
	}

}
