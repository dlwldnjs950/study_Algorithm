import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	/**
	 * 	# 1233 사칙연산 유효성 검사
	 * 
	 * 사칙연산 식은 이진트리로 표현할 수 있다
	 * 사칙연산과 양의 정수로만 구성된 트리가 주어질 때
	 * 유효성 검사
	 * 단말 노드에는 숫자만 있어야 하고
	 * 단말 노드가 아닌 노드에는 연산자가 있어야 한다
	 * 
	 * 테스트 케이스 10개
	 * 트리는 완전 이진 트리 형식
	 * 
	 * 계산 가능하면 1, 계산 불가능하면 0 출력
	 * 
	 * 	# 입력
	 * 해당정점의 번호 알파벳 왼쪽자식번호 오른쪽자식번호
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스 10개
		int TC = 10;
		
		for(int testCase = 1; testCase<= TC; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			// 정점의 개수 N
			int N = Integer.parseInt(br.readLine().trim());
			String[] arithmetic = new String[N+1];
			
			// 각 정점 정보 입력받기
			for(int idx=1; idx<=N; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				st.nextToken();
				arithmetic[idx] = st.nextToken();
			}
			int flag = 1;
			for(int idx=1; idx<=N; idx++) {
				// 자식노드가 한개라도 있는 경우에는 숫자가 있어야 한다
				if (2 * idx <= N || 2 * idx + 1 <= N) {
					if(!"+-*/".contains(arithmetic[idx])) {
						flag = 0;
						break;
					}
				}
				// 자식 노드가 없는 경우에는 기호가 있어야하고
				else {
					if(arithmetic[idx].charAt(0)<'0' || arithmetic[idx].charAt(0)>'9') {
						flag = 0;
						break;
					}
				}
			}
			sb.append(flag).append("\n");
		}
		System.out.println(sb);

	}

}
