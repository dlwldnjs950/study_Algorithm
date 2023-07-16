import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] num = new int[N];
		int[] sorted = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			num[i] = sorted[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(sorted);

		HashMap<Integer, Integer> rank = new HashMap<>();
		int idx = 0;
		for (int str : sorted) {
			if (!rank.containsKey(str)) {
				rank.put(str, idx);
				idx++;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int str : num) {
			sb.append(rank.get(str)).append(" ");
		}
		System.out.println(sb);
	}
}
