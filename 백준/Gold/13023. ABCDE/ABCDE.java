import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 13023_ABCDE
	 * 
	 * 	[설명]
	 * N명의 사람 (0번 ~ N-1번)
	 * 
	 * A는 B와 친구다.
	 * B는 C와 친구다.
	 * C는 D와 친구다.
	 * D는 E와 친구다.
	 * 의 관계가 존재하는지 안하는지 구하는 프로그램
	 * 
	 * 	[입력]
	 * ===> 사람 수 N 친구 관계 수 M
	 * (M줄의 입력)
	 * ===> 친구 관계인 a와 b
	 * 
	 * 	[출력]
	 * 조건에 맞는 친구 관계가 존재하면 1 없으면 0
	 * 
	 * 	[풀이방법]
	 * dfs로 길이 4의 경로 존재 여부
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static boolean visited[], flag;
	static List<List<Integer>> friends;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 친구관계 (그래프 형태/ 무향) 생성
		friends = new ArrayList<>();
		// 방문 표시 배열
		visited = new boolean[N];
		for(int idx = 0; idx<N; idx++) {
			friends.add(new ArrayList<>());
		}
		
		// 친구 관계 입력받아 표시하기
		for(int loop = 0; loop<M; loop++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			friends.get(a).add(b);
			friends.get(b).add(a);
		}
		
		for (int idx = 0; idx < N; idx++) {
			// 연결되어있지 않을수도 있기 때문에 시작점을 바꿔서 확인한다.
			dfs(idx, 0);
		}
		// 깊이 우선 탐색으로 4이상으로 이어지는 관계가 있는지 확인
		sb.append(flag?1:0);
		System.out.println(sb);
	}

	private static void dfs(int v, int depth) {
		if(!flag) {
			visited[v] = true;
			if(depth== 4)
				flag = true;
			// foreach 문으로 있는 요소만 탐색한다
			for(int a : friends.get(v)) {			
				if(!visited[a])
					dfs(a, depth+1);
			}
			visited[v] = false;
		}
	}
}
