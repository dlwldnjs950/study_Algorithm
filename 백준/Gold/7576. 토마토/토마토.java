import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 7576_토마토
	 * 
	 * 	[설명]
	 * 익은 토마토 인접한 곳에 있는 익지않은 토마토는 
	 * 익은 토마토의 영향을 받아 익는다
	 * 인접한 : 왼쪽, 오른쪽, 앞, 뒤
	 * 
	 * 토마토는 며칠이 지나면 다 익게 되는가? (최소 일수)
	 * 
	 * 	[입력]
	 * ===> 상자의 크기 M N (공백 구분)
	 * ===> 공간 정보 (1 : 익은 토마토, 0 : 안익은 토마토, -1 : 토마토없)
	 * 
	 * 	[출력]
	 * 토마토가 모두 익을 때까지 최소 날짜 출력
	 * 저장될 때부터 모든 토마토가 익어있는 상태면 0출력
	 * 	// 처음에 탐색 시작할 지점을 찾는데, q에 하나도 안들어갔으면 0
	 * 토마토가 모두 익지 못하는 상황이면 -1 출력
	 * 	// q가 비어서 끝났는데 아직 익지 않은 토마토가 있는 경우
	 * 
	 * 	[풀이방법]
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static class Tomato{
		int row;
		int col;
		int day;
		
		public Tomato(int row, int col, int day) {
			this.row = row;
			this.col = col;
			this.day = day;
		}

		@Override
		public String toString() {
			return "Tomato [row=" + row + ", col=" + col + ", day=" + day + "]";
		}
		
		
	}
	
	static final int RED =1, GREEN = 0, NOTHING = -1;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		st = new StringTokenizer(br.readLine().trim());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		// 공간 정보 입력받기
		int[][] box = new int[N][M];
		// 공간 방문 여부
		boolean[][] visited = new boolean[N][M];
		// 방문할 순서 저장 자료구조
		PriorityQueue<Tomato> q = new PriorityQueue<>(new Comparator<Tomato>() {
			@Override
			public int compare(Tomato o1, Tomato o2) {
				// 날짜 기준 우선순위 저장 (오름차순 정렬)
				return o1.day - o2.day;
			}
		}); 
		int cnt = 0; // 익은 토마토 개수
		int noneCnt = 0;
		for (int rIdx = 0; rIdx < N; rIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int cIdx = 0; cIdx < M; cIdx++) {
				box[rIdx][cIdx] = Integer.parseInt(st.nextToken());
				if (box[rIdx][cIdx] == RED) {
					visited[rIdx][cIdx] = true;
					q.add(new Tomato(rIdx, cIdx, 1));
					cnt++;
				}
				if(box[rIdx][cIdx] == NOTHING)
					noneCnt++;
			}
		}
		
		// 상하좌우 탐색 델타 배열
		int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };
		
		int maxDay = 0;
		while (!q.isEmpty()) {
			if (cnt + noneCnt == N * M) { // 이동 전에 토마토가 다 익었다면 더 확인하지 않아도 된다.
//				sb.append(0);
				break;
			}

			Tomato current = q.poll();
			maxDay = Math.max(maxDay, current.day);

			// 최근의 토마토에 대해 4방 탐색한다
			for (int dIdx = 0; dIdx < dr.length; dIdx++) {
				int nr = current.row + dr[dIdx];
				int nc = current.col + dc[dIdx];

				// 범위 넘어가면 다음 좌표
				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;
				// 토마토가 없으면 다음 좌표
				if (box[nr][nc] == NOTHING)
					continue;
				if (!visited[nr][nc]) {
//					// 안익은 토마토 였는데 큐에 들어가는 경우면 cnt++
//					if (box[nr][nc] == GREEN)
						cnt++;
					q.offer(new Tomato(nr, nc, current.day + 1));
					visited[nr][nc] = true;
				}
			}

		}
		// 다 돌고 나왔는데 아직 다 익지 않았다면 -1 출력
		if (cnt + noneCnt != N * M)
			sb.append(-1);
		else
			sb.append(maxDay);
		System.out.println(sb);
	}

}
