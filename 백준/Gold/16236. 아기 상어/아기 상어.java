import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 16236_아기 상어
	 * 
	 * 	[설명]
	 * N*N 공간
	 * 물고기 M마리와 아기 상어 1마리
	 * 한 칸에 물고기 최대 1마리 존재
	 * 
	 * 아기 상어와 물고기는 크기가 있다
	 * 아기 상어의 크기는 2, 
	 * 			1초에 상하좌우로 인접한 한 칸씩 이동
	 * 			자신보다 큰 물고기가 있는 칸은 지나갈 수 없다
	 * 			자신보다 작은 물고기 먹을 수 있음
	 * 				여러마리면 가까운 물고기부터 먹는다 (BFS)
	 * 			자신과 크기가 같은 물고기 먹을 순 없지만 지나갈 순 있다
	 * 			크기는 '자신의 크기와 같은 수'의 물고기를 먹으면 커진다
	 * 
	 * 몇초동안 엄마 상어에게 도움(먹을 물고기가 더이상 없음) 요청하지 않고 물고기 잡을 수 있는가?
	 * 
	 * 	[입력]
	 * ===> 공간의 크기 N
	 * (N줄의 입력)
	 * ===> 공간의 상태 (공백 구분)
	 * 
	 * 	0 : 빈칸
	 * 	1,2,3,4,5,6 : 물고기 크기
	 * 	9 : 아기 상어 위치
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 공간 크기 N
		int N = Integer.parseInt(br.readLine().trim());

		// 공간 정보 입력받기
		int[][] space = new int[N][N];
		// 아기 상어 위치도 저장 (탐색 시작 위치)
		int[] bCur=null;
		int fishCnt = 0;

		for (int rIdx = 0; rIdx < N; rIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int cIdx = 0; cIdx < N; cIdx++) {
				space[rIdx][cIdx] = Integer.parseInt(st.nextToken());
				// 값이 9라면 아기 상어 위치
				if (space[rIdx][cIdx] == 9) {
					bCur = new int[] {rIdx, cIdx};
					space[rIdx][cIdx] = 0;
				}
			}
		}

		int babyShark = 2; // 아기 상어의 크기
		int move = 0;	// 상어 이동 거리
		int eat = 0;	// 먹은 물고기 개수. 상어 크기와 같으면 상어 크기 +1

		// 델타 배열
		int dx[] = { -1, 1, 0, 0 }, dy[] = { 0, 0, -1, 1 };
		
		while(true) {
			PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1, o2) ->
            												o1[2] != o2[2] ? Integer.compare(o1[2], o2[2]) : // 거리가 다르면 거리순 오름차순
            												(o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]))); // y축 좌표가 다르면 y축 좌표 오름차순 / x축 좌표로 오름차순 
			boolean[][] isVisited = new boolean[N][N];
			
			q.add(new int[] {bCur[0], bCur[1], 0});
			isVisited[bCur[0]][bCur[1]] = true;
			
			boolean ate = false;
			
			while(!q.isEmpty()) {
				
				bCur = q.poll();
				
				if (space[bCur[0]][bCur[1]] != 0 && space[bCur[0]][bCur[1]] < babyShark) { // 먹이가 있으면서 상어의 사이즈보다 작다면?
                    space[bCur[0]][bCur[1]] = 0; // 물고기를 제거
                    eat++; 
                    move += bCur[2]; // 움직인 거리를 총 움직인 거리에 추가
                    ate = true; // 먹이 먹었다고 체크
                    break;
                }

                for (int k = 0; k < 4; k++) {
                    int nx = bCur[0] + dx[k];
                    int ny = bCur[1] + dy[k];

                    if (ny < 0 || nx < 0 || nx >= N || ny >= N || isVisited[nx][ny] || space[nx][ny] > babyShark)
                        continue;

                    q.add(new int[]{nx, ny, bCur[2] + 1});
                    isVisited[nx][ny] = true;
                }
				
			}
			if(!ate)
				break;
			if(babyShark == eat) {
				babyShark++;
				eat = 0;
			}
		}
		
		sb.append(move);
		System.out.println(sb);
	}

	
}
