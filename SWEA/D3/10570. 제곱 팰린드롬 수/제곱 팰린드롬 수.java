import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	/**
	 * 	# 10570 제곱팰린드롬수
	 * 
	 * 	[설명]
	 * 제곱 팰린드롬수란?
	 * 1. 어떤 실수 N이 양의 정수
	 * 2. 앞으로 읽어도 뒤로 읽어도 같은 팰린드롬
	 * 3. N과 N의 제곱근이 모두 팰린드롬
	 * 4. N과 N의 제곱근이 모두 범위 내에 있어야 하는것이 아니다~!
	 * 
	 * 	[입력]
	 * 테스트 케이스 TC
	 * 숫자의 범위
	 * 
	 * 	[풀이방법]
	 * 작은 수 부터 팰린드롬인지 판단
	 * 팰린드롬이면 그 수의 제곱이 팰린드롬인지
	 * 그 수의 제곱이 범위를 넘어가면 확인 끝내기
	 * 		=> N과 N의 제곱근이 모두 범위 내에 있어야 하는것이 아니다~!
	 * 숫자가 적으니까 전부다 구해버려..?는 오래 걸리는구나~
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스 수 T
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int testCase = 1; testCase<= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			st = new StringTokenizer(br.readLine().trim());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			int cnt = 0; // 제곱 팰린드롬 개수
			for (int number = 1; number * number <= B; number++) {
				// 해당 숫자가 팰린드롬이고
				if (isPalindrome(number)) {
					// 그 숫자의 제곱이 범위 내에 있는 팰린드롬 수 라면 ++
					if (number * number >= A && number * number <= B && isPalindrome(number*number)) {
						cnt++;
					}
				}
			}
			
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);

	}
	
	// 팰린드롬인지 확인하는 함수
	// 숫자를 문자열로 바꿔서 바깥쪽에서 안쪽으로 같은지 확인
	static boolean isPalindrome(int number) {
		String numStr = String.valueOf(number);
		for(int idx = 0; idx<numStr.length()/2 ; idx++) {
			if(numStr.charAt(idx) != numStr.charAt(numStr.length() - 1 - idx))
				return false;
		}
		return true;
	}

}
