import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 2563_색종이
	 * 
	 * 	[설명]
	 * 100*100인 흰색 도화지
	 * 10*10인 검은색 색종이
	 * 
	 * 색종이를 여러장 붙인 뒤 검은 영역의 넓이?
	 * 
	 * 	[입력]
	 * ===> 색종이의 수 N
	 * (N줄의 입력)
	 * ===> 색종이가 붙을 좌표 (색종이의 왼쪽아래 꼭짓점 기준)	 * 
	 * 
	 * 	[출력]
	 * 검은 영역 넓이
	 * 
	 * 	[풀이방법]
	 * 색종이가 붙은 좌표가 입력될 때 마다 검은 면적 카운트
	 * 이미 카운트한 영역은 표시해두고 다시 카운트하지 않는다
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int WHITE = 100;	// 도화지 크기
	static final int BLACK = 10;	// 색종이 크기
	
	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 색종이 수 N
		int N = Integer.parseInt(br.readLine().trim());
		// 카운트한 영역인지 표시할 배열
		boolean[][] isCounted = new boolean[WHITE + 1][WHITE + 1];		
		
		int cnt = 0; // 면적 카운트
		for (int loop = 0; loop < N; loop++) {
			st = new StringTokenizer(br.readLine().trim());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			for (int rIdx = row; rIdx < row + BLACK; rIdx++) {
				for (int cIdx = col; cIdx < col + BLACK; cIdx++) {
					// 아직 카운트하지 않은 칸이면 카운트하고 방문 표시
					if (!isCounted[rIdx][cIdx]) {
						cnt++;
						isCounted[rIdx][cIdx] = true;
					}
				}
			}
		}
		sb.append(cnt);
		System.out.println(sb);
	}

}
