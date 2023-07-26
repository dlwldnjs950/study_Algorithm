import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Node {
	public int x;
	public int y;
	
	public Node(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}

public class Main {

	public static int dx[] = { -1, 1, 0, 0 };
	public static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] nums = br.readLine().split(" ");
		int[] num = { Integer.parseInt(nums[0]), Integer.parseInt(nums[1]) };
		int[][] map = new int[num[0]][num[1]];

		for (int i = 0; i < num[0]; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < num[1]; j++) {
				map[i][j] = tmp.charAt(j) - '0';
			}
		}

		System.out.println(bfs(0, 0, map, num[0], num[1]));
	}

	private static int bfs(int x, int y, int[][] graph, int n, int m) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 0));

		while (!q.isEmpty()) {
			Node node = q.poll();
			x = node.getX();
			y = node.getY();
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m)
					continue;
				if (graph[nx][ny] == 0)
					continue;
				if (graph[nx][ny] == 1) {
					graph[nx][ny] = graph[x][y] + 1;
					q.offer(new Node(nx, ny));
				}
			}
		}
		return graph[n - 1][m - 1];
	}
}
