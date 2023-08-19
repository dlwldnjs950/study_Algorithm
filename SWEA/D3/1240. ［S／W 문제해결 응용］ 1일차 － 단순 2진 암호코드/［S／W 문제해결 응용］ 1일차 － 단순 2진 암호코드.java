import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 1240_단순 2진 암호코드
	 * 
	 * 	[설명]
	 * 	# 암호코드
	 * 1. 8개의 숫자로 되어있다
	 * 2. 올바른 암호코드는
	 * 		(홀수 자리 합 x 3) + (짝수 자리 합) = 10의 배수
	 * 3. 암호코드 숫자 하나는 7개의 비트로 되어있다 (따라서 가로 길이는 56)
	 * 
	 * 주어지는 입력에서 암호코드 부분을 찾아 올바른 암호코드인지 판별
	 * 		올바른 암호코드면 암호코드에 포함된 숫자 합 출력
	 * 		아니면 0 출력
	 * 
	 * 	[입력]
	 * ===>
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * 오른쪽부터 보면서 1이 나오는 열이 암호 코드의 마지막 열
	 * 		그 열 - 55가 시작 열
	 * 위에서부터 보면서 1이 나오는 행이 암호 코드의 시작 행
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());
		
		// 각 숫자의 암호화된 암호코드
		Map<String, Integer> codeNum = new HashMap<>();
		codeNum.put("0001101", 0);
		codeNum.put("0011001", 1);
		codeNum.put("0010011", 2);
		codeNum.put("0111101", 3);
		codeNum.put("0100011", 4);
		codeNum.put("0110001", 5);
		codeNum.put("0101111", 6);
		codeNum.put("0111011", 7);
		codeNum.put("0110111", 8);
		codeNum.put("0001011", 9);

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			st = new StringTokenizer(br.readLine().trim());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			// 입력 저장
			String[] input = new String[N];
			int startR = -1, endC = -1;
			for (int rIdx = 0; rIdx < N; rIdx++) {
				input[rIdx] = br.readLine().trim();
				for (int cIdx = M-1; cIdx >=0; cIdx--) {
					// 오른쪽 위부터 보면서 가장 먼저 나온 1의 위치가 암호코드의 오른쪽위 좌표
					if(input[rIdx].charAt(cIdx)=='1' && startR == -1 && endC == -1) {
						startR = rIdx;
						endC = cIdx;
					}
				}
			}
			
			//System.out.println(startR+" "+endC);
			String originCode = "";
			
			for(int startC = endC-55; startC<=endC; startC+=7) {
				String tmp = input[startR].substring(startC,startC+7);
				originCode+=codeNum.get(tmp);
			}
			int oddSum = 0, evenSum = 0;
			for (int idx = 0; idx < originCode.length(); idx++) {
				if ((idx & 1) == 1) // 홀수면
					evenSum += originCode.charAt(idx) - '0';
				else
					oddSum += originCode.charAt(idx) - '0';
			}
			if((oddSum*3+evenSum)%10==0) {	// 10의 배수면
				sb.append(oddSum+evenSum);
			}else
				sb.append(0);
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
