import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 2630_색종이 만들기
	 * 
	 * 	[설명]
	 * 종이 크기 N×N(N=2^k)
	 * 
	 * 	[입력]
	 * ===> 종이 크기 N
	 * (N줄의 입력)
	 * ===> 공간 정보 (공백 구분)
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N, spaces[][], white, blue;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 공간 크기 N
		N = Integer.parseInt(br.readLine().trim());

		spaces = new int[N][N];
		
		for(int rIdx=0; rIdx< N; rIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int cIdx=0; cIdx< N; cIdx++) {
				spaces[rIdx][cIdx] = Integer.parseInt(st.nextToken());
			}
		}
		
		makingPaper(0, 0, N);
		sb.append(white).append("\n").append(blue);
		System.out.println(sb);
	}
	
	private static void makingPaper(int sRow, int sCol, int size) {

		// 해당 공간이 같은 색으로 채워져 있는지 확인
		int sum = 0;
		for (int rIdx = sRow; rIdx < sRow + size; rIdx++) {
			for (int cIdx = sCol; cIdx < sCol + size; cIdx++) {
				sum += spaces[rIdx][cIdx];
			}
		}
		
		if(sum == 0) {	// 전부 흰색
			white++;
		}else if(sum == size*size) {	// 전부 녹색
			blue++;
		}else {	// 섞여있다는 뜻이니까 4분할해서 재귀
			int half = size >> 1;
			makingPaper(sRow, sCol, half);
			makingPaper(sRow, sCol + half, half);
			makingPaper(sRow + half, sCol, half);
			makingPaper(sRow + half, sCol + half, half);
		}
		
	}

}
