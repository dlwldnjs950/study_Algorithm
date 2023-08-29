import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 2805_농작물 수확하기
	 * 
	 * 	[설명]
	 * NxN 크기의 농장
	 * N은 항상 홀수
	 * 수확은 항상 농장 크기에 딱 맞는 정사각형 마름모 형태로 가능
	 * 
	 * 농장의 크기와 가치가 주어질 때, 수익을 구하여라
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 개수
	 * ===> 농장 크기
	 * ===> 농장 각 칸의 가치 정보 (N*N)
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * 각 좌표에서 중심으로부터 거리를 이용한다
	 *  그 거리가 농장크기/2의 올림 값 이내면 수확 가능하다
	 *  	중심 좌표 = (농장크기/2,농장크기/2)
	 * 좌표간 거리 |x1-x2| + |y1 - y2|
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int farmSize;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			farmSize = Integer.parseInt(br.readLine().trim());
			
			// 농장 정보
			int farm[][] = new int[farmSize][farmSize];
			
			// 수익
			int income = 0;
			// 거리 기준이 되는 값
			int limitDistance = (int)Math.ceil((double)farmSize/2);

			// 농장 정보 입력받기
			// 농장 정보 입력 받으면서 동시에 수확 가능한 칸인지 확인 가능
			for (int rIdx = 0; rIdx < farmSize; rIdx++) {
				// 가치 정보가 공백없이 들어온다
				String farmTmp = br.readLine().trim();
				for (int cIdx = 0; cIdx < farmSize; cIdx++) {
					// 해당 칸 가치 입력받고 나서
					farm[rIdx][cIdx] = farmTmp.charAt(cIdx) - '0';
					// 수확 가능한 칸이면
					if (calculate(rIdx, cIdx) < limitDistance) {
						income += farm[rIdx][cIdx];
					}
				}
			}
			
			sb.append(income).append("\n");
			
		}// 전체 테스트 케이스 반복 끝		
		
		System.out.println(sb);
	}

	private static int calculate(int rIdx, int cIdx) {
		// 농장 중심으로부터의 거리를 구할 것임
		int mid = farmSize / 2;

		return Math.abs(rIdx - mid) + Math.abs(cIdx - mid);
	}

}
