import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * # 1288 새로운 불면증 치료법
	 * 
	 * 	N의 배수로 양을 샌다
	 * 	N번양, 2N번양, ...
	 * 	셌던 번호들의 각 자리수에서 0~9까지 모든 숫자를 보는건
	 * 	최소 몇번 양을 센 시점일까
	 * 		nN번을 셀 때 0~9까지 모든 숫자를 봤다면
	 * 		n을 출력
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static Map<Integer, Integer> numCnt;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			numCnt = new HashMap<>();
			
			//N 입력받기
			int N = Integer.parseInt(br.readLine().trim());
			// Map에 0~9까지가 채워졌을 때 끝내면 된다
			int multiNum = 1;
			while(numCnt.size()<10) {
				cntEachNum(N * multiNum++);
			}
			sb.append(--multiNum * N);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void cntEachNum(int number) {
		if(number==0)
			return;
		numCnt.put(number % 10, numCnt.getOrDefault(number % 10, 0) + 1);
		cntEachNum(number / 10);
	}
}
