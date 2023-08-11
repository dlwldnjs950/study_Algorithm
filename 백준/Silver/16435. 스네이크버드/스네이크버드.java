import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 16435_스테이크버드
	 * 
	 * 	[설명]
	 * 과일 1개 먹으면 길이 1만큼 늘어남
	 * i번째 과일의 높이는 hi
	 * 자신의 길이보다 작거나 같은 높이 과일 먹을 수 있음
	 * 처음 길이가 L일 때, 늘릴 수 있는 최대 길이는? 
	 * 
	 * 	[입력]
	 * ===> 과일개수 N과 스네이크버드 초기 길이 L (공백 구분)
	 * ===> N개의 과일 높이 (공백 구분)
	 * 
	 * 	[출력]
	 * 스네이크 버드 최대 길이
	 * 
	 * 	[풀이방법]
	 * 과일 높이 정렬
	 * 스네이크버드 길이와 같거나 작은 과일
	 * 과일은 큐에 저장해서 peek이 스네이크의 길이보다 작거나 같으면 먹기
	 * 		넣음과 동시에 정렬되어야하기 때문에 PriorityQueue를 사용한다
	 * 
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());	// 과일 개수
		int L = Integer.parseInt(st.nextToken());	// 스네이크버드 초기 길이
		
		// 과일의 높이가 입력되면서 오름차순으로 정렬된다
		PriorityQueue<Integer> fruits = new PriorityQueue<>();
		
		st = new StringTokenizer(br.readLine().trim());
		while(st.hasMoreTokens()) {
			fruits.offer(Integer.parseInt(st.nextToken()));
		}
		
		while(!fruits.isEmpty()) {
			// 가까운 과일의 높이가 스네이크버드보다 높아지면 먹지 못한다.
			if(fruits.peek()> L)
				break;
			L++;
			fruits.poll();
		}
		
		sb.append(L);
		
		System.out.println(sb);
	}

}
