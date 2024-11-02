import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Word implements Comparable<Word> {
		String word;
		int distance;

		Word(String word, int distance) {
			this.word = word; // 거리 같으면 사전순
			this.distance = distance; // 거리 가까운 순
		}

		@Override
		public int compareTo(Word o) {
			if (this.distance != o.distance)
				return this.distance - o.distance;
			else
				return this.word.compareTo(o.word);
		}
	}

	static class Spot {
		int row, col;

		Spot(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	// 각 알파벳의 위치
	static Map<Character, Spot> dictionary;
	static char[][] alpha = { { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p' },
			{ 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l' }, { 'z', 'x', 'c', 'v', 'b', 'n', 'm' } };

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		dictionary = new HashMap<>();
		makeDictionary();
		

		int tc = Integer.parseInt(br.readLine().trim());

		for (int testCase = 0; testCase < tc; testCase++) {

			// 사용자 입력과 사전 단어 개수
			st = new StringTokenizer(br.readLine().trim());

			String input = st.nextToken();
			int num = Integer.parseInt(st.nextToken());

			// 사전 단어
			String[] words = new String[num];

			for (int idx = 0; idx < num; idx++) {
				words[idx] = br.readLine().trim();
			}

			// 거리 계산 및 정렬
			PriorityQueue<Word> order = new PriorityQueue<>();

			for (String word : words) {
				order.add(distance(input, word));
			}

			while (!order.isEmpty()) {
				Word tmp = order.poll();
				sb.append(tmp.word).append(" ").append(tmp.distance).append("\n");
			}
		}

		System.out.println(sb);

	}

	private static Word distance(String input, String word) {
		int total = 0;

		for (int idx = 0; idx < input.length(); idx++) {
			char inputC = input.charAt(idx);
			char wordC = word.charAt(idx);
			//sb.append(inputC).append("<->").append(wordC).append("\n");

			total += Math.abs(dictionary.get(inputC).row - dictionary.get(wordC).row);
			total += Math.abs(dictionary.get(inputC).col - dictionary.get(wordC).col);
		}
		//sb.append(total).append("\n");
		return new Word(word, total);
	}

	static void makeDictionary() {
		for (int col = 0; col < alpha[0].length; col++) {
			dictionary.put(alpha[0][col], new Spot(0, col));
			//sb.append(dictionary.get(alpha[0][col]).row).append("//").append(dictionary.get(alpha[0][col]).col).append(" ");
		}
		//sb.append("\n");
		for (int col = 0; col < alpha[1].length; col++) {
			dictionary.put(alpha[1][col], new Spot(1, col));
			//sb.append(dictionary.get(alpha[1][col]).row).append("//").append(dictionary.get(alpha[1][col]).col).append(" ");
		}
		//sb.append("\n");
		for (int col = 0; col < alpha[2].length; col++) {
			dictionary.put(alpha[2][col], new Spot(2, col));
			//sb.append(dictionary.get(alpha[2][col]).row).append("//").append(dictionary.get(alpha[2][col]).col).append(" ");
		}
	}

}
