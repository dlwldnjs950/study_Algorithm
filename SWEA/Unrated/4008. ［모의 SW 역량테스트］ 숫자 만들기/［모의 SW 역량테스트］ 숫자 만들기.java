import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 4008_숫자 만들기
	 * 
	 * 	[설명]
	 * 숫자 카드
	 * 사칙 연산 카드
	 * 숫자 카드 사이에 사칙 연산 카드 놓고
	 * 앞에서부터 계산
	 * 그 결과 최대와 최소 차이값?
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 개수
	 * ===> 숫자 개수
	 * ===> 각 연산자 카드 개수
	 * ===> 수식에 사용할 숫자
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final char[] OPER = new char[] {'+','-','*','/'};
	
	static int numNum, numCard[], max, min, operCnt[], operOrder[];

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 숫자 개수
			numNum = Integer.parseInt(br.readLine().trim());
			
			// 연산자 카드 저장 배열
			operCnt = new int[OPER.length];
			
			// 연산자 카드 개수 입력 받기
			st = new StringTokenizer(br.readLine());

			for(int oIdx = 0; oIdx< OPER.length; oIdx++) {
				operCnt[oIdx] = Integer.parseInt(st.nextToken());
			}

			// 숫자 카드 저장 배열
			numCard = new int[numNum];
			
			st = new StringTokenizer(br.readLine().trim());
			for(int numCardIdx= 0; numCardIdx<numNum; numCardIdx++) {
				numCard[numCardIdx] = Integer.parseInt(st.nextToken());
			}
			
			// 연산자 순서 정하기
			operOrder = new int[numNum-1];
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			// 순열
			per(0);

			sb.append(max-min).append("\n");
		}
		System.out.println(sb);
	}

	private static void per(int cnt) {
		if (cnt == numNum - 1) {

			// 순서가 다 정해졌으면 계산하기
			//System.out.println(Arrays.toString(operOrder));
			int result = calculate(numCard, operOrder);

			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}

		for (int idx = 0; idx < OPER.length; idx++) {
			if (operCnt[idx] == 0)
				continue;
			operOrder[cnt] = idx;
			operCnt[idx]--;
			per(cnt + 1);
			operCnt[idx]++;
		}		
		
	}

	private static int calculate(int[] numbers, int[] operations) {
		int result = numbers[0];
		for (int idx = 0; idx < numNum - 1; idx++) {
			if (operations[idx] == 0) {
				result += numbers[1 + idx];
			}else if (operations[idx] == 1) {
				result -= numbers[1 + idx];
			}else if (operations[idx] == 2) {
				result *= numbers[1 + idx];
			}else if (operations[idx] == 3) {
				result /= numbers[1 + idx];
			}
		}
		return result;
	}

}
