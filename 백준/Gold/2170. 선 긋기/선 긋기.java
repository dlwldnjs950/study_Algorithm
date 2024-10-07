import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringTokenizer st;

	static class Line {
		int start, end;

		Line(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));

		int lineNum = Integer.parseInt(br.readLine().trim());
		PriorityQueue<Line> lines = new PriorityQueue<Line>((a, b) -> a.start - b.start);

		for (int idx = 0; idx < lineNum; idx++) {
			st = new StringTokenizer(br.readLine().trim());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			lines.add(new Line(start, end));
		}

		Stack<Line> panels = new Stack<>();
		while (!lines.isEmpty()) {

			Line current = lines.poll();

			if (panels.isEmpty()) {
				panels.add(current);
				continue;
			}

			Line peek = panels.peek();

			if (current.start < peek.end) {
				peek.end = Math.max(peek.end, current.end);
			} else {
				panels.add(current);
			}
		}
		
		int answer = 0;
		while(!panels.isEmpty()) {
			Line current = panels.pop();
			
			answer += (current.end - current.start);
		}
		System.out.println(answer);

	}

}
