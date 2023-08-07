import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 1288 암호문
	 * 
	 * I x의위치바로다음에 y개의숫자넣기 넣을숫자들S
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
			// 암호문의 길이 N
			int N = Integer.parseInt(br.readLine().trim());

			// 원본 암호문 - 숫자 덩어리의 나열
			st = new StringTokenizer(br.readLine().trim());
			// 숫자 사이에 다른 숫자를 넣는 작업을 할 것이기 때문에 List에 저장
			List<Integer> originStr = new LinkedList<>();
			while (st.hasMoreTokens()) {
				originStr.add(Integer.parseInt(st.nextToken()));
			}

			// 명령어 개수 O
			int O = Integer.parseInt(br.readLine().trim());
			// 명령어들 : I 넣을위치 넣을숫자개수 넣을숫자들(공백구분)
			st = new StringTokenizer(br.readLine().trim());
			while (st.hasMoreTokens()) {
				// I를 만날때 마다 새로운 명령어
				if (st.nextToken().equals("I")) {
					// 넣을 위치
					int x = Integer.parseInt(st.nextToken());
					// 넣을 숫자 개수
					int y = Integer.parseInt(st.nextToken());
					List<Integer> insertNum = new ArrayList<>();
					for (int loop = 0; loop < y; loop++) {
						insertNum.add(Integer.parseInt(st.nextToken()));
					}
					// 원본 문자열에 넣기
					originStr.addAll(x, insertNum);
				}
			}
			// 처음 10개항만 출력
			for(int idx=0; idx<10; idx++) {
				sb.append(originStr.get(idx)).append(" ");
			}
		}
		System.out.println(sb);
	}

}
