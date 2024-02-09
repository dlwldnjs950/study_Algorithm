import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	static BufferedReader br;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 연산의 개수
		int operCnt = Integer.parseInt(br.readLine().trim());
		
		// 자연수면 힙에 추가, 0이면 가장 작은 값 출력
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		
		for(int oper = 0; oper < operCnt; oper++) {
			int input = Integer.parseInt(br.readLine().trim());
			
			if(input == 0) {
				if(heap.isEmpty())
					sb.append(0);
				else
					sb.append(heap.poll());
				sb.append("\n");
				continue;
			}
			heap.offer(input);
		}
		
		System.out.println(sb.toString());
	}

}
