import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 16935_배열 돌리기 3
	 * 
	 * 	[설명]
	 * 크기 N*M 배열
	 * 연산 R번 적용
	 * 	# 연산의 종류
	 * 1. 상하 반전
	 * 2. 좌우 반전
	 * 3. 오른쪽으로 90도 회전
	 * 4. 왼쪽으로 90도 회전
	 * 5. 사분면으로 나눠 1사분면 값을 기준으로 시계방향 이동
	 * 6. 사분면으로 나눠 3사분면 값을 기준으로 반시계방향 이동
	 * 
	 * 	[입력]
	 * ===> 배열 크기 N, M, 연산 개수 R (한 줄 입력 공백 구분)
	 * (N줄 입력)
	 * ===> 각 줄에 M개의 숫자 공백 구분
	 * ===> R개의 연산 공백 구분
	 * 
	 * 	[출력]
	 * 연산 수행 결과 배열 출력
	 * 
	 * 	[풀이방법]
	 * 각 연산에 대한 메서드를 선언해서 배열 변환
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N, M;
	// 2차원 배열
	static int[][] map;
	// 연산 결과 배열
	static int[][] resultMap;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 배열 크기와 연산 개수 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		// 2차원 배열 생성
		map = new int[N][M];
		
		for(int rIdx = 0; rIdx<N; rIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int cIdx = 0; cIdx<M; cIdx++) {
				map[rIdx][cIdx] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 연산 입력받기
		String[] operation = br.readLine().trim().split(" ");
		for (String oper : operation) {

			switch (oper) {
			case "1":
				one();
				break;
			case "2":
				two();
				break;
			case "3":
				three();
				break;
			case "4":
				four();
				break;
			case "5":
				five();
				break;
			case "6":
				six();
				break;
			}
		}

		// 배열 값 출력하기
		for (int[] row : map) { // 각 row의
			for (int col : row) { // col 값
				sb.append(col).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	// 각 연산 동작 메서드
	// 각 연산을 하고 나면 연산 결과 맵으로 기존 맵을 바꾼다.

	// 1번 연산 : 상하 반전 - row값
	static void one() {
		// resultMap = new int[N][M];
		int n = map.length;
		int m = map[0].length;
		resultMap = new int[n][m];

		for (int rIdx = 0; rIdx < n; rIdx++) {
			for (int cIdx = 0; cIdx < m; cIdx++) {
				resultMap[rIdx][cIdx] = map[n - 1 - rIdx][cIdx];
			}
		}
		map = resultMap;
	}

	// 2번 연산 : 좌우 반전 - col값
	static void two() {
		// resultMap = new int[N][M];
		int n = map.length;
		int m = map[0].length;
		resultMap = new int[n][m];

		for (int rIdx = 0; rIdx < n; rIdx++) {
			for (int cIdx = 0; cIdx < m; cIdx++) {
				resultMap[rIdx][cIdx] = map[rIdx][m - 1 - cIdx];
			}
		}
		map = resultMap;
	}

	// 3번 연산 : 오른쪽 90도 회전 - col값이 row값, 역row값이 col값
	static void three() {
		// resultMap = new int[M][N];
		int n = map.length;
		int m = map[0].length;
		resultMap = new int[m][n];

		for (int rIdx = 0; rIdx < m; rIdx++) {
			for (int cIdx = 0; cIdx < n; cIdx++) {
				resultMap[rIdx][cIdx] = map[n - 1 - cIdx][rIdx];
			}
		}
		map = resultMap;
	}

	// 4번 연산 : 왼쪽 90도 회전 - 역col값이 row값, row값이 col값
	static void four() {
		// resultMap = new int[M][N];
		int n = map.length;
		int m = map[0].length;
		resultMap = new int[m][n];

		for (int rIdx = 0; rIdx < m; rIdx++) {
			for (int cIdx = 0; cIdx < n; cIdx++) {
				resultMap[rIdx][cIdx] = map[cIdx][m - 1 - rIdx];
			}
		}
		map = resultMap;
	}

	// 5번 연산 : 사분면 연산 - 1사분면 값을 기준으로 시계방향 이동
	static void five() {
		// resultMap = new int[N][M];
		int n = map.length;
		int m = map[0].length;
		resultMap = new int[n][m];

		// map 의 인덱스가 넘어버리는중
		for (int rIdx = 0; rIdx < n / 2; rIdx++) { // N/2 ~
			for (int cIdx = 0; cIdx < m / 2; cIdx++) { // 0 ~
				resultMap[rIdx][cIdx] = map[n / 2 + rIdx][cIdx];
			}
		}
		for (int rIdx = 0; rIdx < n / 2; rIdx++) { // 0 ~
			for (int cIdx = m / 2; cIdx < m; cIdx++) { // 0 ~
				resultMap[rIdx][cIdx] = map[rIdx][cIdx - m / 2];
			}
		}
		for (int rIdx = n / 2; rIdx < n; rIdx++) { // N/2 ~
			for (int cIdx = 0; cIdx < m / 2; cIdx++) { // M/2 ~
				resultMap[rIdx][cIdx] = map[rIdx][m / 2 + cIdx];
			}
		}
		for (int rIdx = n / 2; rIdx < n; rIdx++) { // 0 ~
			for (int cIdx = m / 2; cIdx < m; cIdx++) { // M/2 ~
				resultMap[rIdx][cIdx] = map[rIdx - n / 2][cIdx];
			}
		}
		map = resultMap;
	}

	// 6번 연산 : 사분면 연산 - 3사분면 값을 기준으로 반시계방향 이동
	static void six() {
		// resultMap = new int[N][M];
		int n = map.length;
		int m = map[0].length;
		resultMap = new int[n][m];

		for (int rIdx = 0; rIdx < n / 2; rIdx++) { // 0 ~
			for (int cIdx = 0; cIdx < m / 2; cIdx++) { // M/2 ~
				resultMap[rIdx][cIdx] = map[rIdx][m / 2 + cIdx];
			}
		}
		for (int rIdx = 0; rIdx < n / 2; rIdx++) { // N/2 ~
			for (int cIdx = m / 2; cIdx < m; cIdx++) { // M/2 ~
				resultMap[rIdx][cIdx] = map[n / 2 + rIdx][cIdx];
			}
		}
		for (int rIdx = n / 2; rIdx < n; rIdx++) { // 0 ~
			for (int cIdx = 0; cIdx < m / 2; cIdx++) { // 0 ~
				resultMap[rIdx][cIdx] = map[rIdx - n / 2][cIdx];
			}
		}
		for (int rIdx = n / 2; rIdx < n; rIdx++) { // N/2 ~
			for (int cIdx = m / 2; cIdx < m; cIdx++) { // 0 ~
				resultMap[rIdx][cIdx] = map[rIdx][cIdx - m / 2];
			}
		}
		map = resultMap;
	}

}
