import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String N = br.readLine();

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Map<String, Integer> card = new HashMap<>();

		while (st.hasMoreTokens()) {
			String tmp = st.nextToken();
			card.put(tmp, card.getOrDefault(tmp, 0) + 1);
		}

		String M = br.readLine();

		StringTokenizer Question = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		while (Question.hasMoreTokens()) {
			sb.append(card.getOrDefault(Question.nextToken(), 0)).append(" ");
		}
		System.out.println(sb);
	}
}
