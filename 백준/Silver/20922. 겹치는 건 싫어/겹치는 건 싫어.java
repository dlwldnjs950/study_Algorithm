import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		int numNum = Integer.parseInt(st.nextToken());
		int limitCnt = Integer.parseInt(st.nextToken());

		int numbers[] = new int[numNum];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < numNum; idx++) {
			numbers[idx] = Integer.parseInt(st.nextToken());
		}

		int numCnt[] = new int[100001];
		int start = 0, end = 0, maxLength = 0;
		
		// 오른쪽으로 늘릴수있는곳까지 늘려보고
		// 더 못늘리면 길이 기억해두고 왼쪽 포인터 이동
		while (end < numNum) {
			while (end < numNum && numCnt[numbers[end]] < limitCnt) {
				numCnt[numbers[end]]++;
				end++;
			}

			// 왜냐면 위에서 미리 넘치게 ++ 되었으니까?
			maxLength = Math.max(end - start, maxLength);
			if (end == numNum)
				break;

			// 왼쪽 포인터를 옮겨보자
			// 기준 개수가 될 때까지 줄여봐야하니까
			// end가 이미 제한 개수에 도달했을 때니까
			while (numCnt[numbers[end]] == limitCnt) {
				numCnt[numbers[start]]--;
				start++;
			}
			// end가 제한 개수 이하여야 옮길 수 있으니까?

		}

		System.out.println(maxLength);

	}

}
