import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 
	 * 
	 * 	[설명]
	 * 
	 * 	[입력]
	 * ===> 
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * 각 지점에서 탐색 시작
	 * 7명을 골랐을 때, 사람수 카운트
	 * 중간에 Y가 4이상이 되면 더 살펴보지 않는다
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int SIZE = 5;
	static final char SOM = 'S', YEON = 'Y';
	static char map[][], selectedChar[];
	static boolean visited[];
	static int selected[], result;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 자리 배치도
		map= new char[SIZE][SIZE];
		
		for(int rowidx =0; rowidx <SIZE; rowidx++) {
			String tmp = br.readLine().trim();
			for(int colidx =0; colidx <SIZE; colidx++) {
				map[rowidx][colidx] = tmp.charAt(colidx);
			}
		}
		
		// 칠공주 꾸리기 - 조합
		selected = new int[7];
		selectedChar = new char[7];
		visited = new boolean[SIZE*SIZE];
		result = 0;
		select7(0,0, 0);
		
		sb.append(result);
		
		System.out.println(sb);
	}

	private static void select7(int start, int cnt, int cntYeon) {
		
		// 임도연파가 과반수 이상이 되면 더 확인하지 않는다
		if(cntYeon>3)
			return;
		
		// 7명이 모두 선택되었으면 연결되어 있는 지 확인한다.
		if (cnt == 7) {
			// 연결되었는지 확인
			if (isConnected()) {
				// System.out.println(Arrays.toString(selected));
				result++;
			}

			return;
		}

		for (int idx = start; idx < SIZE * SIZE; idx++) {
			visited[idx] = true;
			selected[cnt] = idx;
			selectedChar[cnt] = map[idx/SIZE][idx%SIZE];

			int row = idx / SIZE;
			int col = idx % SIZE;

			if (map[row][col] == YEON)
				select7(idx + 1, cnt + 1, cntYeon + 1);
			else
				select7(idx + 1, cnt + 1, cntYeon);
			
			visited[idx] = false;
		}
	}

	static int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };
	
	private static boolean isConnected() {
		int cnt = 0;
		boolean isVisited[] = new boolean[SIZE*SIZE];
		Queue<Integer> q = new ArrayDeque<>();
		
		q.add(selected[0]);
		isVisited[selected[0]] = true;
		cnt++;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int direction = 0; direction < dr.length; direction++) {
				int nr = (current / SIZE) + dr[direction];
				int nc = (current % SIZE) + dc[direction];
				
				// 범위
				if(nr<0 || nc<0 || nr>=SIZE || nc>=SIZE)
					continue;
				
				// 이미 탐색한 칸이면 다음 방향
				if(isVisited[nr*SIZE + nc])
					continue;
				
				// 선택된 칸이 아니면 다음 방향
				if(!visited[nr*SIZE + nc])
					continue;
				
				q.add(nr*SIZE + nc);
				isVisited[nr*SIZE + nc] = true;
				cnt++;
			}
		}
		
		return cnt == 7;
	}

}
