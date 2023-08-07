import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	/**
	 * 	# 1158 요세푸스 문제
	 * 
	 * 1번부터 N번까지 N명의 사람 원으로 앉아있음
	 * 양의 정수 k
	 * 순서대로 k번째 사람 제거
	 * 제거된 다음 사람이 1번이 되어
	 * 모두 제거될 때 까지
	 * 
	 * 원에서 사람이 제거되는 순서 = 요세푸스 순열
	 * */
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 사람 수 N과 k
		st = new StringTokenizer(br.readLine().trim());

		Queue<Integer> person = new LinkedList<>();
		int N = Integer.parseInt(st.nextToken());
		for (int idx = 1; idx <= N; idx++) {
			person.offer(idx);
		}
		sb.append("<");
		int k = Integer.parseInt(st.nextToken());
		while(!person.isEmpty()) {
			for(int loop=1; loop<k; loop++) {
				person.offer(person.poll());
			}
			sb.append(person.poll());
			if(person.size()>0)
				sb.append(", ");
		}
		
		sb.append(">");
		System.out.println(sb);
	}

}
