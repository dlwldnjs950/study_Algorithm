import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 2252_줄 세우기
	 * 
	 * 	[설명]
	 * N명의 학생을 키 순서로 줄세우기
	 * 두 학생의 키를 비교하는 방법을 사용
	 * 일부 학생들의 키를 비교해 보았다
	 * 
	 * 일부 학생들의 키를 비교한 결과가 주어질 때, 줄 세우는 프로그램 작성
	 * 
	 * 	[입력]
	 * ===> 학생수 N 키 비교 횟수 M (공백 구분)
	 * (M줄의 입력)
	 * ===> 키를 비교한 두 학생의 번호 A B(공백 구분) - A가 B 앞에 서야한다는 뜻
	 * 
	 * 	[출력]
	 * 첫째 줄에 학생들을 앞에서부터 줄을 세운 결과를 출력한다.
	 * 답이 여러 가지인 경우에는 아무거나 출력한다.
	 * 
	 * 	[풀이방법]
	 * 	# 위상 정렬
	 * 0. 간선 정보를 이용해 진입 차수 배열을 만든다
	 * 1. 진입 차수가 0인 노드를 큐에 모두 넣는다
	 * 2. 큐에서 노드를 하나 꺼내어 자신과 인접한 노드의 간선을 제거한다
	 * 			제거 = 진입 노드 차수를 1 감소시키기
	 * 3. 간선 제거 후 진입 차수가 0이 된 노드를 큐에 넣는다
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 학생수 N과 비교횟수 M
		st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 학생들의 번호는 1부터 시작하므로 인덱스 값을 맞추기 위해 +1
		// 탐색할 때 인덱스 1부터 탐색하도록 한다.
		// 인접 리스트
		List<List<Integer>> list = new ArrayList<>();
		// 2차원 리스트 초기화
		for(int idx=0; idx<=N; idx++) {
			list.add(new ArrayList<>());
		}
		// 진입 차수 배열
		int[] into = new int[N+1];
		// 비교 횟수만큼 학생들 간 선행관계 입력
		for(int loop = 0; loop<M; loop++) {
			st = new StringTokenizer(br.readLine().trim());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			// A에서 B로 연결됨
			list.get(A).add(B);
			// B의 진입차수 +1
			into[B]++;
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		for(int idx=1; idx<=N; idx++) {
			// 진입행렬을 보면서 0인 학생들을 큐에 넣는다
			if(into[idx]==0)
				queue.add(idx);
		}
		// 큐가 비어있지 않은 동안 실행
		while (!queue.isEmpty()) {
			// 큐에서 노드 하나를 꺼내고
			int tmp = queue.poll();
			// 큐에서 나온 순서로 줄세우면 된다.
			sb.append(tmp).append(" ");
			// 해당 노드의 인접 정점에 대해서
			for (int idx = 0; idx < list.get(tmp).size(); idx++) {
				int adj = list.get(tmp).get(idx);
				// 간선을 제거한다.
				into[adj]--;
				// 간선 제거 후 진입 차수가 0이 된 노드를 큐에 넣는다
				if (into[adj] == 0)
					queue.add(adj);
			}
		}
		System.out.println(sb);
	}

}
