import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));

		String[] NM = br.readLine().trim().split(" ");
		int num = Integer.parseInt(NM[0]);
		int target = Integer.parseInt(NM[1]);

		int numbers[] = new int[num];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < num; idx++) {
			numbers[idx] = Integer.parseInt(st.nextToken());

		}

		int left = 0, right = 0;
		int answer = 0;
		int sum = 0;
		for (; left < num; left++) {
			while (sum < target && right < num) {
				sum += numbers[right++];
			}

			if (sum == target) {
				answer++;
			}

			sum -= numbers[left];
		}

		System.out.println(answer);

	}

}
