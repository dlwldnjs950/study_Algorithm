import java.io.*;
import java.util.*;

public class Main {
	
	/**
	 * 	# 16234_인구이동
	 * 
	 * 	[설명]
	 * NxN 땅
	 * r행 c열 인구 주어짐
	 * 인접한 나라 국경선
	 * 
	 * 하루동안 다음과 같은 인구 이동
	 * 1. 인구 차이가 L이상 R이하 국경선 열림
	 * 2. 국경선 모두 열었다면 인구 이동
	 * 3. 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수) 소수점 버림
	 * 
	 * 인구 이동 며칠동안?
	 * 
	 * 	[입력]
	 * ===> 땅크기 차이최소 차이최대
	 * 		(N개의 줄) 각 나라의 인구수
	 * 
	 * 	[출력]
	 * 인구 이동 며칠동안 발생
	 * 
	 * 	[풀이방법]
	 * 2차원 배열에 인구 수 저장
	 * 그래프 탐색으로 연합 구성
	 * 연합 구성 후 인구 이동 
	 * 
	 * */
	
	static class Point{
		int row;
		int col;
		
		Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int mapSize, minDiff, maxDiff, map[][];
	static List<Point> openCountry = new ArrayList<>();
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine().trim());
		mapSize = Integer.parseInt(st.nextToken());
		minDiff = Integer.parseInt(st.nextToken());
		maxDiff = Integer.parseInt(st.nextToken());
		
		// 인구 정보 저장
		map = new int[mapSize][mapSize];
		for(int rIdx = 0; rIdx < mapSize; rIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for(int cIdx = 0; cIdx < mapSize; cIdx++) {
				map[rIdx][cIdx] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 인구 이동 날짜 (결과값)
		int movingDay = 0;
		while(true){		
			
			visited = new boolean[mapSize][mapSize];
			boolean isMove = false;
			
			// 각 나라에서 연합 구성
			for(int rIdx = 0; rIdx < mapSize; rIdx++) {
				for(int cIdx = 0; cIdx < mapSize; cIdx++) {
					
					if(!visited[rIdx][cIdx]) {
						// 한 연합에 대해 인구 이동 실시
						int populSum = bfs(rIdx, cIdx);
						if(openCountry.size() > 1) {
							movePopul(populSum);
							isMove = true;
						}
					}
				}
			}
			
			if(!isMove)
				break;
			movingDay++;			
		}
		
		System.out.println(movingDay);
	}

	private static int bfs(int row, int col) {
		
		openCountry.clear();
		int populSum = 0;
		
		int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
		
		Queue<Point> queue = new ArrayDeque<>();
		
		queue.add(new Point(row, col));
		visited[row][col] = true;
		
		while(!queue.isEmpty()) {
			
			Point current = queue.poll();
			// 연합에 추가
			openCountry.add(current);
			int popul = map[current.row][current.col];
			// 연합 총 인구 수
			populSum += popul;
			
			for(int direction = 0; direction < dr.length; direction++) {
				int nr = current.row + dr[direction];
				int nc = current.col + dc[direction];
				
				if(nr < 0 || nc < 0 || nr >= mapSize || nc >= mapSize)
					continue;
				if(visited[nr][nc])
					continue;
				
				// 인구 차이가 조건에 맞으면 국경 열림
				int diff = Math.abs(popul - map[nr][nc]);
				if (diff >= minDiff && diff <= maxDiff) {
					queue.add(new Point(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}		
		
		return populSum;
	}

	private static void movePopul(int populSum) {
		
		int changePopul = populSum / openCountry.size();
		
		for(Point country : openCountry) {
			map[country.row][country.col] = changePopul;
		}
	}

	
}
