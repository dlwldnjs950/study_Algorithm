import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static List<Integer> answer;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int numNum = Integer.parseInt(br.readLine().trim());

		int numbers[] = new int[numNum + 1];
		for (int idx = 1; idx <= numNum; idx++) {
			numbers[idx] = Integer.parseInt(br.readLine().trim());
		}

		answer = new ArrayList<>();
		boolean[] isVisited = new boolean[numNum + 1];
		for (int idx = 1; idx <= numNum; idx++) {
			// 각 인덱스로 출발해서 다시 인덱스로 돌아올 수 있는지
			isVisited[idx] = true;
			dfs(idx, idx, numbers, isVisited);
			isVisited[idx] = false;
		}

		Collections.sort(answer);
		sb.append(answer.size());
		for(int idx : answer) {
			sb.append("\n").append(idx);
		}
		
		System.out.println(sb);

	}

	private static void dfs(int startIdx, int targetIdx, int[] numbers, boolean[] isVisited) {

		int nextIdx = numbers[startIdx];

		if (isVisited[nextIdx]) {
			if (nextIdx == targetIdx)
				answer.add(targetIdx);
			return;
		}

		isVisited[nextIdx] = true;
		dfs(nextIdx, targetIdx, numbers, isVisited);
		isVisited[nextIdx] = false;

	}

}
