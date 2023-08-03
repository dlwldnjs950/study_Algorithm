import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	/**
	 * # 1979 어디에 단어가 들어갈 수 있을까
	 * 
	 * 	N*N 크기의 단어퍼즐
	 * 	단어 퍼즐 모양 주어짐
	 *  K 길이의 단어가 들어갈 수 있는 자리 수 출력
	 * */
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// 테스트 케이스 개수
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int testCase = 1; testCase<=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			st = new StringTokenizer(br.readLine().trim());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] puzzle = new int[N+2][N+2];
			
			for(int rowIdx=1; rowIdx<=N; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int colIdx=1; colIdx<=N; colIdx++) {
					puzzle[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}
			
			int cnt=0;
			int tmpCnt;
			// 가로 방향 확인
			for(int rowIdx=1; rowIdx<=N; rowIdx++) {
				tmpCnt=0;
				for(int colIdx=1; colIdx<=N; colIdx++) {
					if(puzzle[rowIdx][colIdx] == 1)
						tmpCnt++;
					else
						tmpCnt=0;
					if(tmpCnt == M && puzzle[rowIdx][colIdx+1] !=1) {
						cnt++;
						tmpCnt=0;						
					}
				}
			}
			
			// 세로 방향 확인
			for (int colIdx = 1; colIdx <= N; colIdx++) {
				tmpCnt=0;
				for (int rowIdx = 1; rowIdx <= N; rowIdx++) {
					if(puzzle[rowIdx][colIdx] == 1)
						tmpCnt++;
					else
						tmpCnt=0;
					if(tmpCnt == M && puzzle[rowIdx+1][colIdx] !=1) {
						cnt++;
						tmpCnt=0;					
					}
				}
			}
			sb.append(cnt).append("\n");
			
		}// 테스트 케이스 끝
		System.out.println(sb);
	}

}
