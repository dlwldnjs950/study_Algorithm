import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int N, W, H, result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int[][] map = new int[H][W];

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            result = Integer.MAX_VALUE;
            breakWall(0, map);

            System.out.println("#" + test_case + " " + result);
        }
    }

    static void breakWall(int depth, int[][] map) {
        if (depth == N) {
            result = Math.min(result, countBricks(map));
            return;
        }

        for (int i = 0; i < W; i++) {
            int[][] newMap = copyMap(map);
            newMap = destroyBricks(i, newMap);
            newMap = dropBricks(newMap);
            breakWall(depth + 1, newMap);
        }
    }

    static int[][] copyMap(int[][] origin) {
        int[][] newMap = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                newMap[i][j] = origin[i][j];
            }
        }
        return newMap;
    }

	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };
	
    static int[][] destroyBricks(int col, int[][] map) {
    	Queue<int[]> q = new ArrayDeque<>();

    	// 위에서부터 살펴보면서
		for (int rowIdx = 0; rowIdx < H; rowIdx++) {

			// 0이 아닌 값이 나오면 제거 시작
			if (map[rowIdx][col] != 0) {

				q.add(new int[] {rowIdx, col, map[rowIdx][col] });
				map[rowIdx][col] = 0;

				while (!q.isEmpty()) {
					int[] current = q.poll();

					for (int distance = 0; distance < current[2]; distance++) {
						for (int direction = 0; direction < dr.length; direction++) {
							int nr = current[0] + dr[direction] * distance;
							int nc = current[1] + dc[direction] * distance;

							// 범위 넘어가면 패스
							if (nr < 0 || nc < 0 || nr >= H || nc >= W)
								continue;

							// 0보다 크면 큐에 넣고 제거 표시
							if (map[nr][nc] > 0) {
								q.add(new int[] { nr, nc, map[nr][nc] });
								map[nr][nc] = 0;
							}
						}
					}
				}
				break;
			}
		}
		return map;
	}

	static int[][] dropBricks(int[][] map) {
		// 각 열의 맨 밑 행부터 살펴보면서
		for (int colIdx = 0; colIdx < W; colIdx++) {
			int idx = H - 1;

			// 최고 높은 행 까지만 살펴보면 된다
			for (int rowIdx = H - 1; rowIdx >= 0; rowIdx--) {

				if (map[rowIdx][colIdx] > 0) {
					map[idx--][colIdx] = map[rowIdx][colIdx];
					if (rowIdx <= idx)
						map[rowIdx][colIdx] = 0;
				}
			}
		}
		return map;
	}

    static int countBricks(int[][] map) {
        int count = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] > 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
