import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static class Point<T>{
		T x;
		T y;
		
		Point(T x, T y){
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * 	# 13218 조별과제
	 * 
	 * 	[설명]
	 * 등산로 부지 크기 N*N
	 * 최대한 긴 등산로 만들 계획
	 * 각 지점의 높이가 담긴 2차원 배열이 주어진다
	 * 
	 * 	# 등산로 생성 규칙
	 * 1. 가장 높은 봉우리에서 시작
	 * 2. 높은 지형에서 낮은 지형으로 연결(가로 or 세로)
	 * 3. 긴 등산로를 위해 딱 한 곳을 K 깊이 만큼 깎을 수 있다
	 * 
	 * 	# 제약 사항
	 * 	3 <= N <= 8
	 * 	1 <= k <= 5
	 * 	가장 높은 봉우리 개수 최대 5개
	 * 	1보다 작게도 깎을 수 있다
	 * 
	 * 	[입력]
	 * ==> 테스트 케이스 개수 T
	 * ==> N과 k
	 * ==> N줄) 부지 정보 (공백 구분)
	 * 
	 * 	[풀이방법]
	 * 탐색! DFS!
	 * 
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int[][] map;
	static int N,k;
	static boolean[][] visited;
	static int maxLength;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스 수 T
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int testCase = 1; testCase<= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			// N과 k
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			// 부지 높이 정보 저장
			map = new int[N][N];
			// 방문 여부
			visited = new boolean[N][N];
			// 가장 높은 봉우리도 함께 찾는다
			int highest = 0;
			List<Point<Integer>> highestList = new ArrayList<>();
			for(int rowIdx = 0; rowIdx<N; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int colIdx = 0; colIdx < N; colIdx++) {
					map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
					// 가장 높은 봉우리가 변경되면 리스트를 초기화하고 추가한다
					if (highest < map[rowIdx][colIdx]) {
						highest = map[rowIdx][colIdx];
						highestList.clear();
						highestList.add(new Point<>(rowIdx, colIdx));
					// 높이가 같으면 리스트에 추가한다
					}else if(highest == map[rowIdx][colIdx]) {
						highestList.add(new Point<>(rowIdx, colIdx));
					}
				}
			}
			
			// 등산로 찾기
			maxLength = 0;
			for(int hIdx = 0; hIdx<highestList.size(); hIdx++) {
				visited[highestList.get(hIdx).x][highestList.get(hIdx).y] = true;
				dfs(highestList.get(hIdx).x, highestList.get(hIdx).y, highest, 1, 0);
				visited[highestList.get(hIdx).x][highestList.get(hIdx).y] = false;
			}
			sb.append(maxLength).append("\n");
		}
		System.out.println(sb);

	}

	private static int[] dx = {0, -1, 0, 1}, dy = {1, 0, -1, 0};
	
	private static void dfs(int x, int y, int height, int length, int shaveCount) {
		for (int d = 0; d < 4; d++) {
			if (maxLength < length)
				maxLength = length;

			int nx = x + dx[d];
			int ny = y + dy[d];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny])
				continue;

			if (height <= map[nx][ny]) { // 원래는 이동 불가능하지만
				if (shaveCount == 0) { // 아직 깎을 수 있고
					if (height > map[nx][ny] - k) { // 깎았을 때 지금보다 작아지면
						visited[nx][ny] = true;
						dfs(nx, ny, height - 1, length + 1, shaveCount + 1);
						visited[nx][ny] = false;
					}
				}

			} else { // 원래도 이동 가능했으면
				visited[nx][ny] = true;
				dfs(nx, ny, map[nx][ny], length + 1, shaveCount);
				visited[nx][ny] = false;
			}
		}

	}

}
