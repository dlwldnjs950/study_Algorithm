import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 
	 * 
	 * 	[설명]
	 * 유니온 파인드 연산
	 * 
	 * 	[입력]
	 * ===> 집합의개수 연산의개수
	 * ===> 합집합(0) 집합a 집합b
	 * 		같은 집합 확인 연산(1)
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * 
	 * */
	
	static int setNum, parents[], rank[];

	public static void make() {
		parents = new int[setNum + 1];
		rank = new int[setNum + 1];
		for (int idx = 0; idx <= setNum; idx++) {
			parents[idx] = idx;
		}
	}
	
	public static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);
		
		if(aParent == bParent) return false;
		
		if(rank[aParent] > rank[bParent]) {
			parents[bParent] = aParent;
		}else {
			parents[aParent] = bParent;
			
			if(rank[aParent] == rank[bParent])
				rank[bParent]++;
		}
		
		return true;
	}
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		setNum = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		make();
		
		for(int idx =0; idx <M; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			int oper = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 합집합 - Union
			if(oper == 0) {
				
				union(a, b);
				
			// 같은 집합 - Find
			}else if(oper == 1) {
			
				sb.append((find(a)==find(b))?"YES":"NO").append("\n");
			}
		}
		System.out.println(sb);
	}

}
