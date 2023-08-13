import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// T
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			sb.append(a * b / gcd(a, b)).append("\n");
		}
		System.out.println(sb);
	}

	private static int gcd(int a, int b) {

		while (b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}
}
