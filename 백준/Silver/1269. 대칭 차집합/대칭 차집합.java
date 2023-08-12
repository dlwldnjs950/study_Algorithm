import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 1269_대칭 차집합
	 * 
	 * 	[설명]
	 * 두 집합 A와 B
	 * 대칭 차집합의 원소 개수를 출력
	 * (A-B)와 (B-A)의 합집합을 대칭 차집합이라 한다
	 * 
	 * 	[입력]
	 * ===> 집합 A와 집합 B의 원소 개수 주어짐 (공백 구분)
	 * ===> 집합 A의 원소들 (공백 구분)
	 * ===> 집합 B의 원소들 (공백 구분)
	 * 
	 * 	[출력]
	 * ---> 대칭 차집합 원소 개수
	 * 
	 * 	[풀이방법]
	 * Map에 넣어서 getOrDefault
	 * value가 2가 아닌 것만 카운트
	 * foreach문 keySet
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 집합 A와 집합 B의 원소 개수 (공백 구분)
		st = new StringTokenizer(br.readLine().trim());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		Map<Integer, Integer> cnt = new HashMap<>();

		// 집합 A 원소 입력받기 (한 줄 공백 구분)
		st = new StringTokenizer(br.readLine().trim());
		while(st.hasMoreTokens()) {
			int tmp = Integer.parseInt(st.nextToken());
			// A에서 몇번 등장했는지 표시
			cnt.put(tmp, cnt.getOrDefault(tmp, 0)+1);
		}
		
		// 집합 B 원소 입력받기 (한 줄 공백 구분)
		st = new StringTokenizer(br.readLine().trim());
		while(st.hasMoreTokens()) {
			int tmp = Integer.parseInt(st.nextToken());
			// B에서 몇번 등장했는지 표시
			cnt.put(tmp, cnt.getOrDefault(tmp, 0)+1);
		}
		
		int result = 0;
		for(int c: cnt.keySet()) {
			// 한번만 등장한 원소만 카운트
			if(cnt.get(c) == 1) {
				result++;
			}
		}
		
		sb.append(result);
		
		System.out.println(sb);
	}

}
