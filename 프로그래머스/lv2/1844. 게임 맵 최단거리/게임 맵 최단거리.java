
import java.util.LinkedList;
import java.util.Queue;

class Solution {
	
	public static int dx[] = { -1, 1, 0, 0 };
	public static int dy[] = { 0, 0, -1, 1 };
    
    public int solution(int[][] maps) {
        boolean[][] visited = new boolean[maps.length][maps[0].length];
		int answer = Bfs(0, 0, maps, visited);
        return answer;
    }
    public static int Bfs(int x, int y, int[][] maps, boolean[][] visited) {
		visited[x][y] = true;
		Queue<Nodes> q = new LinkedList<>();
		q.offer(new Nodes(x,y,1));
		int n=maps.length;
		int m=maps[0].length;

		while (!q.isEmpty()) {
			Nodes node = q.poll();
			if (node.x == n - 1 && node.y == m - 1)
				return node.cost;

			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
					if (maps[nx][ny] == 1 && !visited[nx][ny]) {
						visited[nx][ny] = true;
						q.offer(new Nodes(nx, ny, node.cost + 1));
					}
				}
			}
		}
		return -1;
	}
}
class Nodes {
	int x;
	int y;
	int cost;

	public Nodes(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}
}