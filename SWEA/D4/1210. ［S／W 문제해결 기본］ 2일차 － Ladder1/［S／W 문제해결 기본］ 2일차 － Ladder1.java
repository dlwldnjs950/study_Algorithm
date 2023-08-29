import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 1210_Ladder1
	 * 
	 * 	[설명]
	 * 어느 사다리를 고르면 X 표시에 도착하는 지 궁금
	 * 
	 * 100X100 크기의 2차원 배열
	 * 지정된 도착점에 대응되는 출발점 x를 반환하는 코드 작성
	 * 0은 빈칸
	 * 1은 사다리
	 * 2는 도착지점
	 * 
	 * 총 10개의 테스트 케이스
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 번호
	 * ===> 사다리 정보
	 * 
	 * 	[출력]
	 * 출발점의 x좌표
	 * 
	 * 	[풀이방법]
	 * 1. 사다리 정보를 입력 받으며 출발점과 도착점을 저장한다
	 * 2. 수행 횟수를 줄이기 위해 도착점부터 반대로 출발점을 찾아간다
	 * 	2-1. 출발점은 행이 0이 되었을 때.
	 * 3. 길을 찾기 위한 탐색(DFS)
	 * 	3-1. 델타배열 : 좌우에 길이 있는지 먼저살펴야하기 때문에
	 * 				  좌우상 순서로 살펴본다
	 * 	3-2. 이미 확인한 길은 가지 않아도 된다.
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int mapSize = 100;
	static int map[][];

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = 10;

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 테스트 케이스 번호가 입력됨
			br.readLine();
			
			// 사다리 정보
			map = new int[mapSize][mapSize];
			// 도착점 정보
			int endPointCol = -1;
			for(int rIdx = 0; rIdx<mapSize; rIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int cIdx = 0; cIdx<mapSize; cIdx++) {
					map[rIdx][cIdx] = Integer.parseInt(st.nextToken());
					
					// 도착점이면 값 기억해두기
					if(map[rIdx][cIdx] ==2) {
						endPointCol = cIdx;
					}
				}
			}
			//System.out.println(endPointCol);
			// 도착점부터 탐색 시작하기
			dfs(mapSize-1, endPointCol);
		}
		System.out.println(sb);
	}

	// 탐색 델타 배열 - 좌우상
	static int[] dr = { 0, 0, -1 }, dc = { -1, 1, 0 };

	private static void dfs(int row, int col) {
		//System.out.println(row+"/"+col);
		// 지나온 길 표시
		map[row][col] = 0;
		// 기저 조건, 출발선인 행0 지점에 도착했음
		if(row==0) {
			sb.append(col).append("\n");
			return;
		}
		for (int dIdx = 0; dIdx < dr.length; dIdx++) {
			int nr = row + dr[dIdx];
			int nc = col + dc[dIdx];

			// 범위 내가 아니면 다른 방향
			if (nr < 0 || nc < 0 || nr >= mapSize || nc >= mapSize)
				continue;
			// 길이 아니면 다른 방향
			if (map[nr][nc] != 1)
				continue;
			dfs(nr, nc);
			break;
		}
	}

}
