import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 1229_암호문2
	 * 
	 * 	[설명]
	 * 숫자 덩어리가 암호문 한칸!?
	 * I연산 : x의 바로 다음에 y 개의 숫자(S) 삽입
	 * D연산 : x의 바로 다음부터 y개의 숫자 삭제
	 * 
	 * 	[입력]
	 * ===> 원본 암호문 길이 N 
	 * ===> 원본 암호문
	 * ===> 명령어의 개수 M
	 * ===> 명령어
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * List 자료구조를 활용한다
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = 10;

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 원본 암호문 길이
			int N = Integer.parseInt(br.readLine().trim());
			
			List<String> code = new LinkedList<>();
			st = new StringTokenizer(br.readLine().trim());
			while(st.hasMoreTokens()) {
				code.add(st.nextToken());
			}
			
			// 명령문 개수 M
			int M = Integer.parseInt(br.readLine().trim());
			st = new StringTokenizer(br.readLine().trim());
			while(st.hasMoreTokens()) {
				String oper = st.nextToken();
				// 삽입 연산일 경우
				if(oper.equals("I")) {
					int x = Integer.parseInt(st.nextToken());	// 삽입 위치
					int y = Integer.parseInt(st.nextToken());	// 삽입할 숫자 개수
					// 삽입할 숫자 리스트를 구성하고
					List<String> insertList = new LinkedList<String>();
					for(int loop = 0; loop<y; loop++) {
						insertList.add(st.nextToken());
					}
					// API를 이용해 삽입
					code.addAll(x, insertList);
					
				// 삭제 연산일 경우
				}else if(oper.equals("D")) {
					int x = Integer.parseInt(st.nextToken());	// 삭제 위치
					int y = Integer.parseInt(st.nextToken());	// 삭제할 숫자 개수
					// 반복문과 인덱스 값으로 삭제 진행
					for(int idx = 0; idx<y; idx++) {
						code.remove(x);	// 인덱스 값은 0부터 시작
										// x 자리가 지워지고 나면 그 다음 지우려던 인덱스가 x가 됨
										// y번 반복해서 x 자리 지우기
					}
				}
			}
			
			for (int rIdx = 0; rIdx < 10; rIdx++) {
				sb.append(code.get(rIdx)).append(" ");
			}
			sb.append("\n");

		}
		System.out.println(sb);
	}

}
