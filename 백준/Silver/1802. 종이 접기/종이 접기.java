import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Some {
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int loop = Integer.parseInt(br.readLine().trim());

		for (; loop > 0; loop--) {
			String str = br.readLine().trim();

			if (isAble(str)) {
				sb.append("YES");
			} else {
				sb.append("NO");
			}
			sb.append("\n");
		}

		System.out.println(sb);

	}

	private static boolean isAble(String str) {
		// 짝수 인덱스 가능한지 확인하고
		// 홀수 인덱스만 모은 다음에 짝수 인덱스 가능한지 확인

		while (str.length() > 1) {
			if (!isRotate(str))
				return false;
			str = remainOddIdx(str);
		}

		return true;
	}

	// 짝수 인덱스가 번갈아 가면서 나오는지
	private static boolean isRotate(String str) {
		int expectChar = str.charAt(0) - '0';

		for (int idx = 0; idx < str.length(); idx += 2) {
			int nowChar = str.charAt(idx) - '0';

			// 서로 다르면 false
			if ((expectChar ^ nowChar) == 1)
				return false;

			expectChar = (expectChar & 1) == 1 ? 0 : 1;
		}
		return true;
	}

	// 홀수 인덱스만 남기기
	private static String remainOddIdx(String str) {
		StringBuilder newStr = new StringBuilder();

		for (int idx = 1; idx < str.length(); idx += 2) {
			newStr.append(str.charAt(idx));
		}
		return newStr.toString();
	}

}
