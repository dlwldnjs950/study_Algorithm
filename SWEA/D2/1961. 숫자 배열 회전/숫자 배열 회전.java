import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * # 1961 숫자 배열 회전
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
			sb.append("#").append(testCase).append("\n");
			// 배열의 크기 N
			int N = Integer.parseInt(br.readLine().trim());
			
			// 배열 숫자 입력
			int numberMap[][] = new int[N][N];
			for(int row=0; row<N; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int col=0; col<N; col++) {
					numberMap[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 90도, 180도, 270도 회전한 배열 출력
			String[] spinStr = new String[N];
			
			// 90도 : 역col이 row, row가 col
			for (int row = 0; row < N; row++) {
				spinStr[row]="";
				for (int col = 0; col < N; col++) {
					spinStr[row] += numberMap[N - col - 1][row];
				}
				spinStr[row]+=" ";
			}
			
			// 180도 : 역row가 row, 역col이 col
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					spinStr[row] += numberMap[N - row - 1][N - col - 1];
				}
				spinStr[row]+=" ";
			}
			
			// 270도 : 역row가 col, col이 row
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					spinStr[row] += numberMap[col][N - row - 1];
				}
				spinStr[row]+=" ";
			}			
			
			for(String str : spinStr) {
				sb.append(str).append("\n");
			}
		}
		System.out.println(sb);
	}

}
