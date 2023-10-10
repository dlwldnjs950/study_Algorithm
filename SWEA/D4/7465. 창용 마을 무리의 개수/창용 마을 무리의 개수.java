import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	/**
	 * 사람 1번 ~ 4번
	 * 무리 = 서로 아는 사이 or 몇사람 거쳐서 알 수 있는 사이
	 * 창용마을에 몇개의 무리가 존재하는 가?
	 * 
	 * 탐색을 통해 서로 연결되어 있는 그래프의 개수
	 * BFS로 풀었으니 이번에는 DFS로 풀어보자
	 * 
	 * 연결된 두 사용자의 번호만이 주어진다 => 연결 리스트 //지만 최대 100명이니까 인접 행렬
	 * 
	 */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int personNum;
	static boolean checked[];
	static List<List<Integer>> relation;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스 개수
		int TC = Integer.parseInt(br.readLine().trim());
		
		for(int testCase = 1; testCase<=TC; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			// 마을에 사는 사람 수, 사람 관계 수
			st = new StringTokenizer(br.readLine().trim());
			personNum = Integer.parseInt(st.nextToken());
			int compareNum = Integer.parseInt(st.nextToken());
			
			// 사람 관계 저장할 자료구조
			relation = new LinkedList<>();
			for(int loop=0; loop<personNum; loop++) {
				relation.add(new LinkedList<>());
			}
			
			// 관계정보 저장
			for(int loop=0; loop<compareNum; loop++) {
				st = new StringTokenizer(br.readLine().trim());
				
				// 사람 번호는 1번부터 시작하므로 1 빼고 넣어준다
				int from = Integer.parseInt(st.nextToken()) -1;
				int to = Integer.parseInt(st.nextToken())-1;
				
				relation.get(from).add(to);
				relation.get(to).add(from);
			}
			
			// 탐색한 사람 표시 배열
			checked = new boolean[personNum];
			
			int result =0;
			// 각 사람에 대해 탐색
			for(int person=0; person < personNum; person++) {
				
				// 이미 무리확인 했으면 넘어감
				if(checked[person])
					continue;
				
				checkRelation(person);
				result++;
			}
			
			sb.append(result).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void checkRelation(int person) {
		
		// 파라미터로 넘어온 사람 번호에 대해 연결된 사람마다 탐색
		for(int number : relation.get(person)) {
			
			// 이미 무리 확인했으면 넘어감
			if(checked[number])
				continue;
			
			// 확인했음을 표시
			checked[number] = true;
			
			// 연결 확인하기
			checkRelation(number);
		}
	}

}
