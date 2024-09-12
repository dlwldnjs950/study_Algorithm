import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Node {
		int number, cost;

		Node(int number, int cost) {
			this.number = number;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		int videoNum = Integer.parseInt(st.nextToken());
		int questionNum = Integer.parseInt(st.nextToken());

		List<List<Node>> network = new ArrayList<>();
		for (int loop = 0; loop < videoNum; loop++) {
			network.add(new ArrayList<>());
		}

		for (int loop = 1; loop < videoNum; loop++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());

			network.get(from).add(new Node(to, cost));
			network.get(to).add(new Node(from, cost));
		}

		for (int loop = 0; loop < questionNum; loop++) {
			st = new StringTokenizer(br.readLine().trim());

			int costStandard = Integer.parseInt(st.nextToken());
			int startVideo = Integer.parseInt(st.nextToken()) - 1;

			// 시작과 각 비디오 USADO 계산
			int ableCnt = calculateCost(startVideo, costStandard, network);

			sb.append(ableCnt).append("\n");
		}

		System.out.println(sb);

	}

	private static int calculateCost(int startVideo, int costStandard, List<List<Node>> network) {

		Queue<Node> queue = new ArrayDeque<>();
		boolean isVisited[] = new boolean[network.size()];

		queue.add(new Node(startVideo, Integer.MAX_VALUE));
		isVisited[startVideo] = true;

		int cnt = 0;
		while (!queue.isEmpty()) {
			Node current = queue.poll();

			for (Node node : network.get(current.number)) {
				if (isVisited[node.number])
					continue;
				int newCost = Math.min(node.cost, current.cost);
				if (newCost >= costStandard) {
					cnt++;
					queue.add(new Node(node.number, newCost));
					isVisited[node.number] = true;
				}
			}
		}

		return cnt;
	}

}
