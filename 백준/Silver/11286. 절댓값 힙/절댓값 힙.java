import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 11286_절댓값 힙
	 * 
	 * 	[설명]
	 * 절댓값 힙의 연산
	 * 1. 배열에 정수 x를 넣는다
	 * 2. 절댓값이 가장 작은 값을 출력 및 배열에서 제거
	 * 		가장 작은 값이 여러 개일 때는 가장 작은 수
	 * 프로그램은 비어있는 배열에서 시작
	 * 
	 * 입력된 정수 x가 0이 아니라면 x를 배열에 넣고
	 * 			  0이라면 출력 및 제거
	 * 
	 * 배열이 비어있는데 출력 및 제거 연산이라면 0 출력
	 * 
	 * 	[입력]
	 * ===> 연산의 개수 N
	 * (N개의 줄)
	 * ===> 정수 x
	 * 
	 * 	[출력]
	 * 0이 주어진 횟수만큼 출력
	 * 
	 * 	[풀이방법]
	 * 최소힙을 구현하고있는 PriorityQueue를 이용한다
	 * 절댓값을 기준으로 넣되, 출력시 원본 값을 출력해야하므로 쌍으로 값을 넣는다
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 연산의 개수 N
		int N = Integer.parseInt(br.readLine().trim());

		// 사용할 자료구조
		// int[0]의 값은 절댓값 int[1]의 값은 원본값
		PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) { // 절댓값이 서로 같으면 더 작은 수부터 출력 및 삭제
					return o1[1] - o2[1];
				} else {
					return o1[0] - o2[0];
				}
			}
		});

		for (int operation = 0; operation < N; operation++) {
			int number = Integer.parseInt(br.readLine().trim());
			if(number == 0) {	// 0이면 출력 및 삭제 연산
				// 이때, 힙이 비어있으면 0 출력
				if(heap.isEmpty())
					sb.append(0).append("\n");
				else
					sb.append(heap.poll()[1]).append("\n");
			}else {	// 아니면 힙에 넣기
				heap.offer(new int[] {Math.abs(number),number});
			}
		}
		System.out.println(sb);
	}

}
