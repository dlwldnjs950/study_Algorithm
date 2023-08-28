import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 1074_Z
	 * 
	 * 	[설명]
	 * 2^N * 2^N 2차원 배열을 Z 모양으로 탐색
	 * 
	 * 	# Z 모양 탐색 순서
	 * 왼쪽위 -> 오른쪽위 -> 왼쪽아래 -> 오른쪽아래
	 * 
	 * r행 c열을 몇번째로 방문하는 지 출력
	 * 
	 * 	[입력]
	 * ===> N, r, c (공백 구분)
	 * 
	 * 	[출력]
	 * r행 c열을 몇번째로 방문했는가
	 * 
	 * 	[풀이방법]
	 * 순서대로 번호를 채우고 해당하는 배열 위치의 값을 출력?
	 * => 메모리 초과
	 * 계산만 하고 원하는 행열일때 sb에 append
	 * => 시간 초과
	 * rc가 어느 사분면에 위치하는지 파악해서
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int r, c, sequence;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		
		// 배열 크기 결정하는 N과 궁금한 위치 r과 c
		int N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		// 방문 순서
		sequence = 0;
		// 배열
		int N2 = (int) Math.pow(2, N);
		recursion(0, 0, N2);
		
		System.out.println(sb);
	}
	
	private static void recursion(int sRow, int sCol, int size) {
		
		if (sRow == r && sCol == c) {
			sb.append(sequence);
			return;
		}
		
		// 반복되는 동작은 Z 모양 방문
		// 절반 크기에 대한 재귀
		int tmpSize = size >> 1;
		if (r < sRow + tmpSize && c < sCol + tmpSize) {
			// 왼쪽 위
			recursion(sRow, sCol, tmpSize);
		} else if (r < sRow + tmpSize && c >= sCol + tmpSize) {
			sequence += (tmpSize * tmpSize);
			// 오른쪽 위
			recursion(sRow, sCol + tmpSize, tmpSize);
		} else if (r >= sRow + tmpSize && c < sCol + tmpSize) {
			sequence += (tmpSize * tmpSize) * 2;
			// 왼쪽 아래
			recursion(sRow + tmpSize, sCol, tmpSize);
		} else if (r >= sRow + tmpSize && c >= sCol + tmpSize) {
			sequence += (tmpSize * tmpSize) * 3;
			// 오른쪽 아래
			recursion(sRow + tmpSize, sCol + tmpSize, tmpSize);
		}

	}

}
