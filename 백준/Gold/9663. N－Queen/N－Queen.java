import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 9663_N-Queen
	 * 
	 * 	[설명]
	 * N*N 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓기
	 * 	퀸은 직선과 대각선으로 원하는 만큼 움직인다.
	 * 
	 * 	[입력]
	 * ===> N
	 * 
	 * 	[출력]
	 * 경우의 수 출력
	 * 
	 * 	[풀이방법]
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N, col[], ans;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 체스판 크기와 퀸 개수 N
		N = Integer.parseInt(br.readLine().trim());

		// 각 행에서 어떤 열에 퀸을 놓았는지 저장
		col = new int[N];
		
		setQueen(0);
		
		sb.append(ans);
		System.out.println(sb);
	}

	private static void setQueen(int row) {

		// 이전 선택지가 불가능했다면 더 확인하지 않기
		if(!isAvailable(row -1)) return;
		// 마지막 행까지 확인했다면 경우의 수 +1 하고 끝내기
		if (row > N - 1) {
			ans++;
			return;
		}		
		
		// 각 행에서 각 열에 대해 시도
		for (int c = 0; c < N; c++) {
			col[row] = c;
			setQueen(row + 1);
		}
	}
	
	private static boolean isAvailable(int row) {
		// 
		for(int rIdx=0; rIdx<row; rIdx++) {
			if(col[rIdx] == col[row] || row - rIdx == Math.abs(col[row] - col[rIdx]))	// 같은 열에 있거나 대각선에 있으면 불가능
				return false;
		}
		return true;
	}
}
