import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 사람 수와 제거할 순번
		st = new StringTokenizer(br.readLine().trim());
		int peopleCnt = Integer.parseInt(st.nextToken());
		int deleteIdx = Integer.parseInt(st.nextToken());
		
		Queue<Integer> circle = new ArrayDeque<>();
		for(int number =1; number <=peopleCnt; number++) {
			circle.offer(number);
		}
		sb.append("<");
		while(circle.size()>1) {
			// k-1번 빼고 넣고
			for(int idx=1; idx<deleteIdx; idx++)
				circle.offer(circle.poll());
			// k번째 사람 배고
			sb.append(circle.poll()).append(", ");
		}
		// 마지막 남은 사람 뺴기
		sb.append(circle.poll()).append(">");
		System.out.println(sb);
	}

}
