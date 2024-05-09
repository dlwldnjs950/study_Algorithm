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

		st = new StringTokenizer(br.readLine().trim());
		int tableCnt = Integer.parseInt(st.nextToken());
		long friendCnt = Long.parseLong(st.nextToken());

		// 각 입국심사대 걸리는 시간
		long[] times = new long[tableCnt];
		long min = Long.MAX_VALUE;
		long max = Long.MIN_VALUE;
		for (int idx = 0; idx < tableCnt; idx++) {
			times[idx] = Long.parseLong(br.readLine().trim());

			min = Math.min(min, times[idx]);
			max = Math.max(max, times[idx]);
		}

		// 최대로 오래 걸릴 수 있는 시간
		max = max * friendCnt;

		while (min <= max) {
			long mid = (min + max) / 2;

			long cnt = 0;
			for (long time : times) {
				cnt += mid / time;
				if(cnt>friendCnt)
					break;
			}

			if (cnt < friendCnt) {
				min = mid + 1;
			} else {
				max = mid - 1;
			}
		}
		System.out.println(min);
	}
}
