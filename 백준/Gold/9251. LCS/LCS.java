import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	/* 
	 * 
	 * 최장 공통 부분 수열
	 * 두 수열이 주어졌을 때,
	 * 모두의 부분 수열되는 수열 중 가장 긴 것을 찾아라
	 * 
	 * DP
	 * 해당 문자열까지 비교했을 때 최장길이 저장
	 * */
		
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int lcs[][];
	static String firstStr, secondStr;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		firstStr = br.readLine().trim();
		int fLength = firstStr.length();
		secondStr = br.readLine().trim();
		int sLength = secondStr.length();

		lcs = new int[fLength + 1][sLength + 1];

		for (int fIdx = 1; fIdx <= fLength; fIdx++) {
			char fChar = firstStr.charAt(fIdx - 1);
			for (int sIdx = 1; sIdx <= sLength; sIdx++) {
				char sChar = secondStr.charAt(sIdx - 1);

				if (fChar == sChar) {
					lcs[fIdx][sIdx] = lcs[fIdx - 1][sIdx - 1] + 1;
				} else {
					lcs[fIdx][sIdx] = Math.max(lcs[fIdx - 1][sIdx], lcs[fIdx][sIdx - 1]);
				}
			}
		}
		
		System.out.println(lcs[fLength][sLength]);

	}
}
