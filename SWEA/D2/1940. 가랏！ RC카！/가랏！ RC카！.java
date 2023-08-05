import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * # 1940 가랏! RC카!
	 * 	
	 * 	입력은 매 초마다 주어진다
	 * 	0 : 현재 속도 유지
	 * 	1 : 가속. 가속도 값이 추가로 주어짐
	 * 	2 : 감속. 가속도 값이 추가로 주어짐
	 * 
	 * 	입력으로 주어진 N개의 command를 모두 수행했을 때, 
	 * 	N초동안 이동한 거리 계산
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

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			// command 개수 N 입력
			int N = Integer.parseInt(br.readLine().trim());
			
			int totalDistance = 0;	// 최종 이동한 거리. 해당 순간의 speed 만큼 + 된다
			int speed = 0;	//가속인 경우 + 감속인 경우 -
			
			for(int loop = 0; loop<N; loop++) {
				st = new StringTokenizer(br.readLine().trim());
				switch (Integer.parseInt(st.nextToken())){
				case 0:
					break;
				case 1:
					speed += Integer.parseInt(st.nextToken());
					break;
				case 2:
					int tmpSpeed = Integer.parseInt(st.nextToken());
					if (speed < tmpSpeed)
						speed = 0;
					else
						speed -= tmpSpeed;
					break;				
				}
				totalDistance += speed;
			}
			sb.append(totalDistance).append("\n");
		}
		System.out.println(sb);
	}

}
