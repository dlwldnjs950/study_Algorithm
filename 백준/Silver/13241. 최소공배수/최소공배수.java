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
		
		st = new StringTokenizer(br.readLine().trim());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());

		sb.append(a * b / gcd(a, b));
		System.out.println(sb);
	}

	private static long gcd(long a, long b) {

		while (b != 0) {
			long r = a % b;
			a = b;
			b = r;
		}
		return a;
	}
}
