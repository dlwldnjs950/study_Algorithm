import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	/**
	 * 	# 2493 탑
	 * 
	 * N개의 높이가 서로 다른 탑
	 * 수평 직선의 왼쪽부터 오른쪽 방향으로 차례로 세움
	 * 레이저 신호는 수평 직선의 '왼쪽 방향'으로 발사
	 * 하나의 탑에서 발사된 레이저 신호는 가장 먼저 만나는 단 하나의 탑에서만 수신 가능
	 * (가장 가까운 높이가 같거나 큰 탑이 수신)
	 * 
	 * 각 탑의 레이저 신호를 어느 탑에서 수신하는 지 알아내라
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 탑의 개수 N
		int N = Integer.parseInt(br.readLine().trim());
		// 각 탑의 높이를 저장할 배열
		// 탑의 번호는 1번부터 시작되므로 N+1
		int[] top = new int[N + 1];
		
		Stack<Integer> maxHeightIdx = new Stack<>();
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 1; idx <= N; idx++) {
			top[idx] = Integer.parseInt(st.nextToken());
			while(!maxHeightIdx.isEmpty()) {
				if(top[maxHeightIdx.peek()] >= top[idx]) {
					sb.append(maxHeightIdx.peek()).append(" ");
					break;
				}
				maxHeightIdx.pop();
			}
			if(maxHeightIdx.isEmpty()) {
				sb.append(0).append(" ");
			}
			maxHeightIdx.push(idx);
		}
		
		System.out.println(sb);
	}
}
