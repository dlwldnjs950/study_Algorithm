import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine().trim());
		int num = Integer.parseInt(st.nextToken());
		int round = Integer.parseInt(st.nextToken());

		String[] numbersStr = br.readLine().trim().split(" ");
		PriorityQueue<Long> cards = new PriorityQueue<>();
		for (int idx = 0; idx < num; idx++) {
			cards.add(Long.parseLong(numbersStr[idx]));
		}

		for (int loop = 0; loop < round; loop++) {
			long cardX = cards.poll();
			long cardY = cards.poll();
			cards.add(cardX + cardY);
			cards.add(cardX + cardY);
		}

		long answer = 0;
		while (!cards.isEmpty()) {
			answer += cards.poll();
		}

		System.out.println(answer);

	}

}
