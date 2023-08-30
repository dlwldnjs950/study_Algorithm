import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/**
	 * 	#11660 구간합 구하기 5
	 * 
	 * 	[ 설명 ]
	 * NxN크기의 표에 수가 채워져 있다
	 * (x1, y1)부터 (x2, y2)까지 합을 구하는 프로그램 작성
	 * 	두 좌표를 대각선 꼭짓점으로하는 사각형 구간에 있는 합을 구하는 것
	 * 
	 * 	[ 입력 ]
	 * ===> 표의 크기와 합을 구해야하는 횟수
	 * ===> 표에 채워져 있는 숫자들
	 * 
	 * 	[ 출력 ]
	 * 합을 구해야 하는 만큼 줄로 구분하여 출력
	 * 
	 * 	[ 풀이방법 ]
	 * 0. 행x1부터 행x2까지의, 열 y1부터 열 y2까지의 합을 구하는 것
	 * 1. 입력에 따라 구간 합을 구해야 하므로 배열에 값을 저장한다
	 * 	1-1. 좌표값이 1부터 시작하므로 행열모두 N+1 사이즈로 선언
	 * 2. 누적합을 저장해두고 반복문이 아닌 빼기 연산으로 구간 합을 구한다
	 * 	2-1. 열[y2] - 열[y1-1]
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 표의 크기와 합을 구하는 횟수
		st = new StringTokenizer(br.readLine().trim());
		int tableSize = Integer.parseInt(st.nextToken());
		int sumCnt = Integer.parseInt(st.nextToken());

		// 표 정보 입력 받기
		int numberTable[][] = new int[tableSize + 1][tableSize + 1];
		for(int rIdx = 1; rIdx<=tableSize; rIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int cIdx = 1; cIdx<=tableSize; cIdx++) {
				// 이전까지 누적합 + 구간합
				numberTable[rIdx][cIdx] = numberTable[rIdx][cIdx - 1] + Integer.parseInt(st.nextToken());
			}
		}
		
		// M만큼 구간 입력받아 구간합 구하기
		for(int sumLoop=0; sumLoop<sumCnt; sumLoop++) {
			// 여러 행이므로 누적합을 저장할 변수
			int resultSum = 0;
			// 시작 좌표 끝 좌표
			st = new StringTokenizer(br.readLine().trim());
			int startRow = Integer.parseInt(st.nextToken());
			int startCol = Integer.parseInt(st.nextToken());
			int endRow = Integer.parseInt(st.nextToken());
			int endCol = Integer.parseInt(st.nextToken());
			
			// 주어진 행 구간에 대해서만 살핀다
			for(int rIdx = startRow; rIdx<=endRow; rIdx++) {
				// 구간 끝값 - 구간 시작전값
				resultSum += numberTable[rIdx][endCol] - numberTable[rIdx][startCol-1]; 
			}
			sb.append(resultSum).append("\n");
		}
		System.out.println(sb);
	}

}
