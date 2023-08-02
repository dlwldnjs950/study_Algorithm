import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @Date : 2023. 8. 2.
 * @Problem : # 2001 파리 퇴치
 * @Description : 
 * 	N*N 배열 안에 파리 개수가 주어질 때
 * 	M*M 크기의 파리채로 내려쳐 한번에 최대한 많은 파리 퇴치
 *
 */
public class Solution {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int testCase = 1; testCase<=T; testCase++) {
			// N과 M 의 값이 한 줄에 공백으로 구분되어 입력
			st = new StringTokenizer(br.readLine().trim());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			// N*N의 파리 마릿 수 데이터 입력
			int[][] fly = new int[N][N];
			for(int rowIdx = 0; rowIdx < N; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int colIdx = 0; colIdx < N; colIdx++) {
					fly[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}
			
			// M*M 만큼 마릿수 파악하기
			int maxFly = Integer.MIN_VALUE;
			for(int rowIdx = 0; rowIdx < N-M+1; rowIdx++) {
				for(int colIdx = 0; colIdx < N-M+1; colIdx++) {
					int totalFly=0;
					for (int row = rowIdx; row < rowIdx + M; row++) {
						for (int col = colIdx; col < colIdx + M; col++) {
							totalFly+=fly[row][col];
						}
					}
					if(totalFly >maxFly) maxFly = totalFly;
				}
			}
			sb.append("#").append(testCase).append(" " ).append(maxFly).append("\n");
		}
		System.out.println(sb);
	}
}
