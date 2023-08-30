import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	#2164 카드2
	 * 
	 * 	[ 설명 ]
	 * N장의 카드
	 * 차례대로 1부터 N까지 번호가 붙어있다
	 * 1번 카드가 제일 위에, N번 카드가 제일 아래
	 * 
	 * 	# 반복 동작 (카드가 한장 남을 때 까지)
	 * 1) 제일 위에 있는 카드를 바닥에 버린다
	 * 2) 제일 위에 있는 카드를 제일 아래로 옮긴다
	 * 
	 * 	[ 입력 ]
	 * ===> 카드 개수 N
	 * 
	 * 	[ 출력 ]
	 * 남게 되는 카드 번호
	 * 
	 * 	[ 풀이 방법 ]
	 * 1. 카드를 위에서 빼고 아래로 넣음 : 큐 자료구조 사용
	 * 2. 동작을 계속 반복하다가 큐의 길이가 1이 되면 break
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 카드 개수
		int cardNum = Integer.parseInt(br.readLine().trim());

		// 초기 카드 구성하기
		Queue<Integer> cards = new ArrayDeque<>();
		for (int number = 1; number <= cardNum; number++) {
			cards.offer(number);
		}
		
		while(true) {
			// 카드가 한장만 남았으면 동작 반복 끝내기
			if(cards.size() == 1)
				break;
			
			// 맨 위 카드 버리고 -> 맨 위 카드 맨 밑으로 옮기기
			cards.poll();
			cards.offer(cards.poll());			
		}
		sb.append(cards.peek());
		System.out.println(sb);
		
	}
}
