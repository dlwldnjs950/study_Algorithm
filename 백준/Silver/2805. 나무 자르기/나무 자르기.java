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

		st = new StringTokenizer(br.readLine().trim());
		int treeNum = Integer.parseInt(st.nextToken());
		int targetLength = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine().trim());
		int min = 0;
		int max = 0;
		int heights[] = new int[treeNum];
		for (int idx = 0; idx < treeNum; idx++) {
			heights[idx] = Integer.parseInt(st.nextToken());
			max = Math.max(max, heights[idx]);
		}

		while (min <= max) {
			int mid = (min + max) / 2;

			long tmpSum = 0;
			for (int h : heights) {
				if (h > mid)
					tmpSum += (h - mid);

				if (tmpSum > targetLength)
					break;
			}

			if (tmpSum > targetLength)
				min = mid + 1;
			else if (tmpSum < targetLength)
				max = mid - 1;
			else {
				max = mid;
				break;
			}
		}

		System.out.println(max);

	}

}
