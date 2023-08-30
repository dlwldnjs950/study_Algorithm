import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 1158_요세푸스문제
	 * 
	 * 	[설명]
	 * 1번부터 N번까지 N명의 사람이 원을 이루어 앉아있다
	 * 순서대로 K번째 사람을 제거한다
	 * 계속 반복한다
	 * N명의 사람이 모두 제거될 때 까지
	 * 
	 * 사람이 제거되는 순서를 출력해라
	 * 
	 * 	[입력]
	 * ===> 사람수 N 제거할 순번 K
	 * 
	 * 	[출력]
	 * < 사 람 제 거 순 서 번 호 >
	 * 
	 * 	[풀이방법]
	 * 원형 큐 원리!
	 * 큐를 이용하자
	 * 1. K-1번 poll 해서 다시 offer한 뒤에
	 * 2. poll 한 값이 k번째 사람
	 * 3. 한명 남을 때 까지 반복
	 * 
	 * */
	
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
