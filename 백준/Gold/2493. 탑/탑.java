import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	#2493 탑
	 * 
	 * 	[ 설명 ]
	 * 일직선 위에 N개의 높이가 서로 다른 탑
	 * 레이저 송신기는 수평직선 왼쪽 방향으로 발사
	 * 가장 먼저 만나는 단 하나의 탑에서만 수신 가능
	 * 
	 * 탑들의 개수 N과 높이가 주어질 때,
	 * 각 탑이 발사한 레이저 신호를 어느 탑에서 수신하는 지 알아내라
	 * 
	 * 	[ 입력 ]
	 * ===> 탑 개수
	 * ===> 탑 높이
	 * 
	 * 	[ 출력 ]
	 * 각 탑의 신호를 수신한 탑의 번호 출력
	 * (탑 번호 1번부터 시작)
	 * 
	 *  [ 풀이 방법 ]
	 * 최근에 값 중에 나보다 큰 값이 내 레이저를 받는다
	 * 1. 스택의 최근 값과 비교해 나보다 크면 기록
	 * 2. 작으면 pop
	 * 3. 스택이 비어 비교할 값이 없으면 0
	 * 4. 다음 비교를 위해 현재 값 스택에 push
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 탑의 개수
		int topCnt = Integer.parseInt(br.readLine().trim());
		
		// 각 탑의 높이
		// 탑의 번호가 1번부터 시작하므로 +1 크기 배열
		int topHeight[] = new int[topCnt +1];
		// 최근값 비교를 위한 자료구조
		Stack<int[]> stack = new Stack<>();
		
		// 해당 인덱스의 레이저를 받은 탑의 인덱스 저장
		int receiveTop[] = new int[topCnt + 1];
		st = new StringTokenizer(br.readLine().trim());
		for(int tIdx = 1; tIdx<=topCnt; tIdx++) {
			topHeight[tIdx] = Integer.parseInt(st.nextToken());
			while(!stack.isEmpty()) {
				// 나보다 큰 값이면 레이저 받음
				if(stack.peek()[1] > topHeight[tIdx]) {
					receiveTop[tIdx] = stack.peek()[0];
					break;
				}else	// 나보다 작으면 빼기
					stack.pop();
			}
			// 비교할 값이 없으면 0
			if(stack.isEmpty())
				receiveTop[tIdx] = 0;
			// 다음 비교를 위해 push
			stack.push(new int[] {tIdx, topHeight[tIdx]});
		}
		
		for(int tIdx = 1; tIdx<=topCnt; tIdx++) {
			sb.append(receiveTop[tIdx]).append(" ");
		}
		
		System.out.println(sb);
	}

}
