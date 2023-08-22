import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 3289_서로소 집합
	 * 
	 * 	[설명]
	 * 1 부터 n까지의 수가 n개의 집합을 이루고 있다
	 * 합집합 연산과, 두 원소가 같은 집합에 포함되어 있는지 확인하는 연산 수행
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 수 T
	 * ===> 집합 개수 n 연산 개수 m
	 * (m 줄의 입력)
	 * ===> 합집합 연산 = 0 a b : a가 있는 집합과 b가 있는 집합을 합친다
	 * 		포함 확인 연산 = 1 a b : 두 원소가 같은 집합에 포함되어 있는지 
	 * 
	 * 	[출력]
	 * 1 연산에 대해한 결과를 연속해서 출력
	 * 
	 * 	[풀이방법]
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N, parents[], rank[];
	
	// 초기 집합 형태를 만드는 함수
	private static void make() {
		
		// 자신이 속한 집합의 대표 원소를 저장하는 배열 초기화
		parents = new int[N+1];
		// 원소의 랭킹을 저장하는 배열
		rank = new int[N+1];

		// 자신이 속한 집합의 대표 원소가 자기 자신이다.
		for(int idx = 0; idx<=N; idx++) {
			parents[idx] = idx;
		}
	}
	
	// 속해있는 집합의 대표 원소를 찾는 함수
	private static int find(int a) {
		// 자신의 부모가 자기자신이면 대표 원소
		if(parents[a] == a) return a;
		// path compression
		return parents[a] = find(parents[a]);
	}
	
	// 같은 집합에 속해있는지 확인하는 함수
	// 대표 원소가 같으면 같은 집합
	private static boolean find(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		return aRoot == bRoot;
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		// 부모가 같다면 동일한 집합에 속해있다는것
		if(aRoot == bRoot)
			return false;
		
		// 부모가 다르다면, 서로 다른 집합에 속해있다
		// 합쳐줘야한다
		
		// 랭킹을 비교해서 합쳐주자
		
		// a부모 랭크가 더 높으면 b를 a 밑으로 넣는다
		if (rank[aRoot] > rank[bRoot]) {
			parents[bRoot] = aRoot;
		} else {

			// b 부모 랭크가 더 높으면 a를 b 밑으로 넣는다
			parents[aRoot] = bRoot;

			// 랭크가 같았다면
			if (rank[aRoot] == rank[aRoot]) {
				// b의 랭크를 높여 a를 그 밑으로 넣는다
				rank[b]++;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			st = new StringTokenizer(br.readLine().trim());
			// 집합 개수
			N = Integer.parseInt(st.nextToken());
			// 연산 개수
			int m = Integer.parseInt(st.nextToken());
			
			// N개수를 입력 받은 뒤 초기 집합 구성
			make();
			
			for(int oper = 0; oper<m; oper++) {
				st = new StringTokenizer(br.readLine().trim());
				// 0이면 합집합 연산
				int operNum = Integer.parseInt(st.nextToken());
				// 연산에 사용할 두 원소
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(operNum == 0) {
					union(a, b);
				// 1이면 포함 확인 연산
				}else if(operNum == 1) {
					sb.append(find(a, b)?1:0);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
