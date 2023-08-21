import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 1697_숨바꼭질
	 * 
	 * 	[설명]
	 * 수빈 위치 점 N
	 * 동생 위치 점 K
	 * 
	 * 수빈이는 걷거나 순간이동 (위치 X 기준)
	 * 	걷기 : X-1 또는 X+1
	 * 	순간이동 : 2*X
	 * 
	 * 수빈이가 동생을 찾을 수 있는 가장 빠른 시간은 몇초 후?
	 * 
	 * 	[입력]
	 * ===> 수빈위치N 동생위치K (공백구분)
	 * 
	 * 	[출력]
	 * 수빈이가 동생 찾는 가장 빠른 시간
	 * 
	 * 	[풀이방법]
	 * 
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int MAX_RANGE =100_000; 
	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 수빈 위치 N, 동생 위치 K
		st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int minTime = Integer.MAX_VALUE;
		boolean[] visited = new boolean[MAX_RANGE+1];

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { N, 0 });
		visited[N] = true;
		while (!q.isEmpty()) {
			int[] a = q.poll();
			//System.out.println(Arrays.toString(a));
			// 동생에게 도착했으면
			if(a[0] == K) {
				minTime = Math.min(minTime, a[1]);
			}
			// 순간이동 - 더 빠른 이동 가능
			if (a[0] * 2 <= MAX_RANGE && !visited[a[0] * 2]) {
				q.add(new int[] { a[0]*2, a[1] + 1 });
				visited[a[0] * 2]=true;
			}
			// 걷기
			if (a[0] - 1 >= 0 && !visited[a[0] - 1]) {
				q.add(new int[] { a[0] - 1, a[1] + 1 });
				visited[a[0] - 1] = true;
			}
			// 걷기
			if (a[0] + 1 <= MAX_RANGE && !visited[a[0] + 1]) {
				q.add(new int[] { a[0] + 1, a[1] + 1 });
				visited[a[0] + 1] = true;
			}
		}
		sb.append(minTime);
		System.out.println(sb);
	}

}
