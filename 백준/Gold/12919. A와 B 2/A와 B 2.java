import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static String answer;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		String origStr = br.readLine().trim();
		String targetStr = br.readLine().trim();

		answer = "";
		makeString(origStr, targetStr);

		System.out.println(answer.equals(origStr) ? 1 : 0);

	}

	private static void makeString(String origStr, String targetStr) {
		// targetStr로 origStr을 만들자

		if (origStr.length() == targetStr.length()) {
			if (origStr.equals(targetStr)) {
				//System.out.print("같음 : ");
				answer = targetStr;
			}
			//System.out.println(origStr + " // " + targetStr);
			return;
		}

		// orig -> target
		// 뒤에 A 추가 : target이 A로 끝나는 지
		int length = targetStr.length();
		if (targetStr.endsWith("A"))
			makeString(origStr, targetStr.substring(0, length - 1));
		// 뒤에 B 추가 후 뒤집기 : target이 B로 시작하는 지
		if (targetStr.startsWith("B")) {
			StringBuilder tmp = new StringBuilder(targetStr.substring(1, length));
			makeString(origStr, tmp.reverse().toString());
		}

	}

}
