import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 11659_구간 합 구하기4
	 * 
	 * 	[설명]
	 * 수 N개가 주어졌을 때,
	 * i번째 수부터 j번째 수까지 합을 구하는 프로그램 작성
	 * 
	 * 	[입력]
	 * ===> 수의 개수 N 합을 구해야하는 횟수 M
	 * ===> N개의 수
	 * ===> 합을 구해야하는 구간
	 * 
	 * 	[출력]
	 * M개의 줄에 구간 합을 각각 출력
	 * 
	 * 	[풀이방법]
	 * 1. N개의 숫자를 입력받을 때, 숫자의 번호가 1부터 시작하므로 N+1 크기의 배열 선언
	 * 2. 각 열에는 해당 값 + 이전까지의 누적합을 저장한다
	 * 3. 주어진 구간에 대한 합을 구할 때,
	 * 	3-1. 반복문으로 매번 새로 합을 구하면 너무 오래걸린다
	 * 	3-2. 구간 i~j라면 숫자[j] - 숫자[i-1]을 계산하면
	 * 		  빼기 연산 만으로 구간 합을 구할 수 있다.
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 숫자 개수와 구간 합 구할 횟수
		st = new StringTokenizer(br.readLine().trim());
		int numberCnt = Integer.parseInt(st.nextToken());
		int sumCnt = Integer.parseInt(st.nextToken());

		// N개의 숫자 (누적합 저장)
		int[] numberSum = new int[numberCnt + 1];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 1; idx <= numberCnt; idx++) {
			// 이전까지 누적합 + 해당 값
			numberSum[idx] = numberSum[idx - 1] + Integer.parseInt(st.nextToken());
		}
		// 구간 합 구하기
		for (int sumLoop = 0; sumLoop < sumCnt; sumLoop++) {
			st = new StringTokenizer(br.readLine().trim());
			int startIdx = Integer.parseInt(st.nextToken());
			int endIdx = Integer.parseInt(st.nextToken());
			sb.append(numberSum[endIdx] - numberSum[startIdx -1]).append("\n");
		}
		
		System.out.println(sb);
	}

}
