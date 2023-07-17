import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		Set<String> card = new HashSet<>();
		while(st.hasMoreTokens()) {
			card.add(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());
		String[] checkCard = br.readLine().split(" ");
		
		StringBuilder sb = new StringBuilder();
		for(String str : checkCard) {
			if(card.contains(str))
				sb.append(1);
			else
				sb.append(0);
			sb.append(" ");
		}
		System.out.println(sb);
	}
}
