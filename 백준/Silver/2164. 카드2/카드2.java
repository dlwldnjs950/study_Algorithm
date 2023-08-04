import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	/**
	 * # 2164 카드
	 * 	N장의 카드 (1~N까지)
	 * 	카드는 1번이 제일 위에, N번이 제일 아래인 상태로 순서대로 놓여있음
	 * 
	 * 	동작
	 * 	카드가 한장 남을 때 까지
	 * 	제일 위 카드 버림
	 * 	제일 위 카드 제일 아래 카드 밑으로
	 * 
	 * 	큐 (버리는건 위쪽에서만, 넣는건 아래쪽에서만)
	 * 	
	 * */
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		Queue<Integer> card = new LinkedList<>();
		
		// 초기 상태 카드 구현
		for(int num=1; num<=N; num++) {
			card.offer(num);
		}
		
		while(card.size()!=1) {
			// 맨 위에 카드 버리고
			card.poll();
			// 그 다음 카드를 빼서 아래에 다시 넣기
			card.offer(card.poll());
		}
		
		System.out.println(card.peek());
	}

}
