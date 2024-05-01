import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		String originStr = br.readLine().trim();
		String targetStr = br.readLine().trim();

		while(originStr.length()<targetStr.length()) {
			// A로 끝나면 A 떼기
			// B로 끝나면 B 떼고 뒤집기
			int length = targetStr.length();
			if(targetStr.endsWith("A")) {
				targetStr = targetStr.substring(0, length-1);
			}else if(targetStr.endsWith("B")) {
				targetStr = targetStr.substring(0, length-1);
				StringBuilder tmp = new StringBuilder(targetStr);
				targetStr = tmp.reverse().toString();
			}
		}

		System.out.println(targetStr.equals(originStr) ? 1 : 0);

	}
}
