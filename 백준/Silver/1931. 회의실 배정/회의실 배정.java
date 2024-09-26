import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringTokenizer st;

	static class Class implements Comparable<Class> {
		int start, end;

		Class(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Class o) {
			if (this.end != o.end)
				return this.end - o.end;
			else
				return this.start - o.start;
		}
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));

		List<Class> classes = new ArrayList<>(); // 수업 목록

		int classNum = Integer.parseInt(br.readLine().trim());

		for (int loop = 0; loop < classNum; loop++) {
			String[] times = br.readLine().trim().split(" ");

			int start = Integer.parseInt(times[0]);
			int end = Integer.parseInt(times[1]);

			classes.add(new Class(start, end));
		}

		Collections.sort(classes);

		int answer = 0;
		int currentTime = 0;
		for (Class c : classes) {
			if (currentTime <= c.start) {
				answer++;
				currentTime = c.end;
			}
		}

		System.out.println(answer);

	}

}
