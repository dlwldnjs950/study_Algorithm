import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	// 만들 수 있는 가장 짧은 펠린드롬의 길이 출력

	static BufferedReader br;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		String input = br.readLine().trim();

		int length = input.length();
		for (int idx = 0; idx < input.length(); idx++) {
			if(isPalind(input.substring(idx)))
				break;
			length++;
		}
		sb.append(length);
		System.out.println(sb);

	}

	public static boolean isPalind(String str) {
		int startIdx = 0;
		int endIdx = str.length() - 1;
		while (startIdx <= endIdx) {
			if (str.charAt(startIdx) != str.charAt(endIdx))
				return false;
			startIdx++;
			endIdx--;
		}
		return true;
	}
}
