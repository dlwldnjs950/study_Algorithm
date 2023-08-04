import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int[] numbers;	//암호 숫자
	static int front;
	static int back;
	static final int SIZE = 8;	// 암호 숫자는 9개

	// 큐에 넣는 함수
	static void offer(int insertNum) {
		if (isFull()) {
			System.out.println("큐 가득참");
			return;
		}
		numbers[back++ % SIZE] = insertNum;
	}

	// 큐에서 빼는 함수
	static int poll() {
		if (isEmpty())
			return -1;
		int tmp = numbers[front % SIZE];
		numbers[front % SIZE] = 0;
		front++;
		return tmp;
	}

	// 큐가 비었는지 확인하는 함수
	static boolean isEmpty() {
		if (front % SIZE == back % SIZE && numbers[front % SIZE] == 0)
			return true;
		return false;
	}

	// 큐가 가득 찼는지 확인하는 함수
	static boolean isFull() {
		if (front % SIZE == back % SIZE && numbers[front % SIZE] != 0)
			return true;
		return false;
	}
	
	// 맨 마지막에 들어간 원소 반환
	static int peek() {
		return numbers[(back-1) % SIZE];
	}

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스는 10개
		int T = 10;
		
		for(int testCase = 1; testCase<=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			br.readLine().trim();	// 테스트 케이스 번호 입력은 사용하지 않는다
			
			numbers = new int[SIZE];
			front = 0;
			back = 0;
			
			st = new StringTokenizer(br.readLine().trim());
			for(int idx=0; idx<SIZE; idx++) {
				offer(Integer.parseInt(st.nextToken()));
			}
			// 암호문 생성 한 사이클
			// "1 감소한 뒤 맨 뒤로, 2 감소한 뒤 맨 뒤로, ... , 5 감소한 뒤 맨뒤로"
			// 다시 1부터 감소
			// 어떤 숫자가 작아지다 0보다 작아지면 0으로 유지되며 프로그램 종료
			int minusNum = 1;
			while(true) {
				int tmp = poll()- minusNum;
				if(tmp<=0) {
					offer(0);
					break;
				}
				offer(tmp);
				minusNum++;
				if (minusNum > 5)
					minusNum = 1;
			}
			//System.out.println(Arrays.toString(numbers));
			while(!isEmpty()) {
				sb.append(poll()).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
