import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * # 1959 두 개의 숫자열
	 * 
	 * 길이 N의 숫자열
	 * 길이 M의 숫자열
	 * 
	 * 두 숫자열 중 하나를 움직여
	 * 서로 마주보는 숫자끼리 곱의 합의 최댓값을 구해라
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

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			// N과 M (공백)
			st = new StringTokenizer(br.readLine().trim());
			int N  = Integer.parseInt(st.nextToken());
			int M  = Integer.parseInt(st.nextToken());
			
			// 길이 N 숫자열 입력받기
			int nNum[] = new int[N];
			st = new StringTokenizer(br.readLine().trim());
			for (int idx = 0; idx < N; idx++) {
				nNum[idx] = Integer.parseInt(st.nextToken());
			}

			// 길이 M 숫자열 입력받기
			int mNum[] = new int[M];
			st = new StringTokenizer(br.readLine().trim());
			for (int idx = 0; idx < M; idx++) {
				mNum[idx] = Integer.parseInt(st.nextToken());
			}

			// 곱의 합의 최대값 구하기
			if (nNum.length > mNum.length)
				sb.append(findMaxSum(nNum, mNum));
			else
				sb.append(findMaxSum(mNum, nNum));
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int findMaxSum(int[] longNum, int[] shortNum) {
		// 짧은 숫자열은 항상 0번 인덱스부터 확인
		// 긴 숫자열은 시작 인덱스가 바뀐다
		int maxSum = Integer.MIN_VALUE;
		for (int startIdx = 0; startIdx + shortNum.length <= longNum.length; startIdx++) {
			int sum = 0;
			for (int shortIdx = 0; shortIdx < shortNum.length; shortIdx++) {
				sum += longNum[startIdx + shortIdx] * shortNum[shortIdx];
			}
			if (maxSum < sum)
				maxSum = sum;
		}
		return maxSum;
	}
}
