import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int INF = 9900001;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int cityCnt = Integer.parseInt(br.readLine().trim());
		int busCnt = Integer.parseInt(br.readLine().trim());

		// 버스 비용 정보
		int[][] costInfo = new int[cityCnt][cityCnt];
		for (int rIdx = 0; rIdx < cityCnt; rIdx++) {
			for (int cIdx = 0; cIdx < cityCnt; cIdx++) {
				if (rIdx == cIdx)
					continue;
				costInfo[rIdx][cIdx] = INF;
			}
		}

		for (int loop = 0; loop < busCnt; loop++) {
			st = new StringTokenizer(br.readLine().trim());

			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());

			// 시작도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
			costInfo[from][to] = Math.min(costInfo[from][to], cost);
		}

		for (int mid = 0; mid < cityCnt; mid++) {
			for (int start = 0; start < cityCnt; start++) {
				for (int end = 0; end < cityCnt; end++) {
					costInfo[start][end] = Math.min(costInfo[start][end], costInfo[start][mid] + costInfo[mid][end]);
				}
			}
		}

		for (int[] row : costInfo) {
			for (int col : row) {
				if (col == INF)
					sb.append(0);
				else
					sb.append(col);
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
