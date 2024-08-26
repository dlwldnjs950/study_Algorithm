import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static class Node {
        int x, y, direction, cost;

        public Node(int x, int y, int direction, int cost) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.cost = cost;
        }
    }

    public int solution(int[][] board) {
        int n = board.length;
        int[][][] cost = new int[n][n][4];
        int[] dx = {-1, 1, 0, 0};  // 상, 하, 좌, 우
        int[] dy = {0, 0, -1, 1};

        // 비용 배열 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    cost[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        Queue<Node> queue = new LinkedList<>();

        // 시작점에서 네 방향으로 모두 출발
        for (int i = 0; i < 4; i++) {
            queue.offer(new Node(0, 0, i, 0));
            cost[0][0][i] = 0;
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            int dir = node.direction;
            int curCost = node.cost;

            for (int newDir = 0; newDir < 4; newDir++) {
                int nx = x + dx[newDir];
                int ny = y + dy[newDir];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && board[nx][ny] == 0) {
                    int newCost = (dir == newDir) ? curCost + 100 : curCost + 600;

                    if (newCost < cost[nx][ny][newDir]) {
                        // 방문 관리가 아니라 비용으로 비교해서 지나갈 수 있나 없나 판단
                        cost[nx][ny][newDir] = newCost;
                        queue.offer(new Node(nx, ny, newDir, newCost));
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            // 도착 지점의 4방향 중 최소값 찾아서 정답
            answer = Math.min(answer, cost[n - 1][n - 1][i]);
        }

        return answer;
    }
}
