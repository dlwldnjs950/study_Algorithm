import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int operCnt = Integer.parseInt(br.readLine().trim());
		
		PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		
		for(int oper = 0; oper<operCnt; oper++) {
			int input = Integer.parseInt(br.readLine().trim());
			
			if(input == 0 ) {
				if(heap.isEmpty()) {
					sb.append(0);
				}else {
					sb.append(heap.poll());
				}
				sb.append("\n");
			}else {
				heap.add(input);
			}
		}
		
		System.out.println(sb);
	}

}
