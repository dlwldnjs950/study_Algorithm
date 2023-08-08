import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 6730_장애물 경주 난이도
	 * 
	 * 	[설명]
	 * 난이도 구하기
	 * 올라가는 난이도
	 * 내려가는 난이도
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 수 T
	 * ===> 블록개수 N
	 * ===> 블록의 높이
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
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

			// 블록 개수
			int N = Integer.parseInt(br.readLine().trim());
			// 블록 높이 (공백 구분)
			st = new StringTokenizer(br.readLine().trim());
			// 첫 블럭은 이전 블럭이 없으므로 일단 넣고 시작
			int preBlock = Integer.parseInt(st.nextToken());
			int upDiff = 0, downDiff = 0;
			for(int loop=1; loop<N; loop++) {
				int nowBlock = Integer.parseInt(st.nextToken());
				if(preBlock < nowBlock) {	// 올라가는 난이도
					int tmp = nowBlock - preBlock;
					// max 값을 저장한다
					upDiff = Math.max(upDiff, tmp);
				}
				else {	//내려가는 난이도
					int tmp = preBlock - nowBlock;
					// max 값을 저장한다
					downDiff = Math.max(downDiff, tmp);
				}
				// 이전 블럭 높이를 현재 블럭으로 바꾼다
				preBlock = nowBlock;
			}
			sb.append(upDiff).append(" ").append(downDiff).append("\n");
		}
		System.out.println(sb);
	}

}
