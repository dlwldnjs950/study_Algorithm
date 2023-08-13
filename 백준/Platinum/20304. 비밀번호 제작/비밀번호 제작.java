import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 20304_비밀번호 제작
	 * 
	 * 	[설명]
	 * 서버 관리자 계정의 비밀번호는
	 * 		0 이상 N 이하의 정수 중 하나를 사용할 수 있다
	 * 로그인 시도에 사용된 비밀번호 목록
	 * 
	 * 	# 두 비밀번호의 안전 거리
	 * 이진법으로 표현한 두 비밀번호의 서로 다른 자리 개수
	 * 
	 * 	# 비밀번호의 안전도
	 * 지금까지 로그인 시도에 사용된 모든 비밀번호와의 안전 거리 중 최솟값
	 * 
	 * 안전도가 제일 높은 비밀번호의 안전도를 구하여라
	 * 
	 * 
	 * 	[입력]
	 * ===> 관리자 계정 비밀번호의 최댓값 N
	 * ===> 로그인 시도에 사용된 비밀번호 개수 M
	 * ===> 로그인 시도에 사용된 비밀번호들 (M개 한줄입력 공백구분)
	 * 
	 * 	[출력]
	 * 안전도가 제일 높은 비밀번호의 안전도 출력
	 * 
	 * 	[풀이방법]
	 * 시도된 비밀번호를 기준으로 한자리가 다른 비밀번호에 대해 확인하는 방식
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N, M;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 관리자 계정 비밀번호 최댓값 N
		N = Integer.parseInt(br.readLine().trim());
		
		// 시도된 비밀번호 개수 M
		M = Integer.parseInt(br.readLine().trim());

		// 각 비밀번호가 확인된 적 있는지 표시
		boolean[] visited = new boolean[N+1];
		// 확인해야하는 비밀번호 목록
		Queue<Integer> queue = new ArrayDeque<>();
		// 차이나는 자리 수
		int depth = 0;
		
		st = new StringTokenizer(br.readLine().trim());
		for(int idx = 0; idx<M; idx++) {
			int tmp = Integer.parseInt(st.nextToken());
			queue.offer(tmp);
			// 시도된 비밀번호와 같은 비밀번호는 사용 불가
			visited[tmp] = true;
		}
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			depth++;
			// 큐에 담긴 모든 비밀번호에 대해 확인해야하니까
			while(size-- > 0) {
				int tmp = queue.poll();
				
				// 해당 비밀번호(시도된 비밀번호)와 한자리만 차이나는 비밀번호(사용가능한 비밀번호)에 대해 확인
				int x=1;
				while(x<= N) {
					// 한자리만 다른 그 비밀번호가 범위 내에있고, 고려된적 없다면
					if((x^tmp) <= N && !visited[x^tmp]) {
						// 확인했음을 표시하고
						visited[x^tmp] = true;
						// 거기에서 한자리수 다른 비밀번호 고려해야하니까 큐에 넣기
						queue.offer(x^tmp);
					}
					x = x<<1;
				}
			}
		}
		
		sb.append(depth-1);
		System.out.println(sb);
	}

}
